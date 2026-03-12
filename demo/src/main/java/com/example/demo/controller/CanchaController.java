package com.example.demo.controller;

import com.example.demo.model.Cancha;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/canchas")
public class CanchaController {

    private final List<Cancha> canchas = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public CanchaController() {
        canchas.add(new Cancha(counter.incrementAndGet(), "Cancha A", "Fútbol",      "Disponible",   12000));
        canchas.add(new Cancha(counter.incrementAndGet(), "Cancha B", "Tenis",        "Disponible",    8000));
        canchas.add(new Cancha(counter.incrementAndGet(), "Cancha C", "Básquetbol",  "Mantenimiento", 10000));
    }

    // GET /canchas
    @GetMapping
    public List<Cancha> getAllCanchas() {
        return canchas;
    }

    // GET /canchas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Cancha> getCanchaById(@PathVariable Long id) {
        return canchas.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /canchas
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cancha createCancha(@RequestBody Cancha cancha) {
        cancha.setId(counter.incrementAndGet());
        if (cancha.getEstado() == null || cancha.getEstado().isBlank()) {
            cancha.setEstado("Disponible");
        }
        canchas.add(cancha);
        return cancha;
    }
}
