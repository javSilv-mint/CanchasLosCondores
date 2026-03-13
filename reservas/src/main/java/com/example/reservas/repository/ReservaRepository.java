package com.example.reservas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reservas.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByCanchaId(Long canchaId);
}
