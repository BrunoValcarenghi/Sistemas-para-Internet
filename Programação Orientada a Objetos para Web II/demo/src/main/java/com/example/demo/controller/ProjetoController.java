package com.example.demo.controller;

import com.example.demo.dto.ProjetoRequestDTO;
import com.example.demo.dto.ProjetoResponseDTO;
import com.example.demo.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projetos")
@CrossOrigin(origins = "*")
public class ProjetoController {

    private final ProjetoService service;

    public ProjetoController(ProjetoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> criar(@RequestBody @Valid ProjetoRequestDTO dto) {
        ProjetoResponseDTO response = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProjetoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProjetoResponseDTO> buscarPorUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.buscarPorUuid(uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable UUID uuid) {
        service.deletar(uuid);
        return ResponseEntity.noContent().build();
    }

}