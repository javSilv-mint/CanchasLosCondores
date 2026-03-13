package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cancha;

public interface CanchaRepository extends JpaRepository<Cancha, Long> {
}
