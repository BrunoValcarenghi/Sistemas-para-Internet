package com.example.demo.controller;

import com.example.demo.dto.AlunoRequestDTO;
import com.example.demo.dto.AlunoResponseDTO;
import com.example.demo.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = "*")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criar(@RequestBody @Valid AlunoRequestDTO dto) {
        AlunoResponseDTO response = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AlunoResponseDTO> buscarPorUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.buscarPorUuid(uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable UUID uuid) {
        service.deletar(uuid);
        return ResponseEntity.noContent().build();
    }

}