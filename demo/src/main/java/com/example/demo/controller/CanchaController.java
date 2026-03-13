package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cancha;
import com.example.demo.repository.CanchaRepository;

@RestController
@RequestMapping("/canchas")
public class CanchaController {

    private final CanchaRepository canchaRepository;

    public CanchaController(CanchaRepository canchaRepository) {
        this.canchaRepository = canchaRepository;
    }

    // GET /canchas
    @GetMapping
    public List<Cancha> getAllCanchas() {
        return canchaRepository.findAll();
    }

    // GET /canchas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Cancha> getCanchaById(@PathVariable Long id) {
        return canchaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /canchas
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cancha createCancha(@RequestBody Cancha cancha) {
        if (cancha.getEstado() == null || cancha.getEstado().isBlank()) {
            cancha.setEstado("Disponible");
        }
        return canchaRepository.save(cancha);
    }
}
