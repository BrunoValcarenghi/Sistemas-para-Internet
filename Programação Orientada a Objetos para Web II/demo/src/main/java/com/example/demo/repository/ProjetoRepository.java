package com.example.demo.repository;

import com.example.demo.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    Optional<Projeto> findByUuid(UUID uuid);
}