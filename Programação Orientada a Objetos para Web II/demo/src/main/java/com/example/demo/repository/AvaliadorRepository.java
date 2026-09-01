package com.example.demo.repository;

import com.example.demo.model.Avaliador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvaliadorRepository extends JpaRepository<Avaliador, Long> {
    Optional<Avaliador> findByUuid(UUID uuid);
}