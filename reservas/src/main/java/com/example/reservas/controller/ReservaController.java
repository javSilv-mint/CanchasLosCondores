package com.example.reservas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservas.model.Reserva;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final List<Reserva> reservas = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public ReservaController() {
        reservas.add(new Reserva(counter.incrementAndGet(), 1L, "Juan Pérez",   "2025-06-18", "09:00", "10:00"));
        reservas.add(new Reserva(counter.incrementAndGet(), 2L, "María López",  "2025-06-19", "11:00", "12:00"));
        reservas.add(new Reserva(counter.incrementAndGet(), 1L, "Carlos Ruiz",  "2025-06-20", "14:00", "15:00"));
    }

    // GET /reservas
    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservas;
    }

    // GET /reservas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        return reservas.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /reservas/cancha/{canchaId}  — filtrar por cancha
    @GetMapping("/cancha/{canchaId}")
    public List<Reserva> getReservasByCanchaId(@PathVariable Long canchaId) {
        return reservas.stream()
                .filter(r -> r.getCanchaId().equals(canchaId))
                .collect(Collectors.toList());
    }

    // POST /reservas
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva createReserva(@RequestBody Reserva reserva) {
        reserva.setId(counter.incrementAndGet());
        reservas.add(reserva);
        return reserva;
    }
}
