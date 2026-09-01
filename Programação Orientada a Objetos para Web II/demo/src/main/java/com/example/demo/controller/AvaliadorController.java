package com.example.demo.controller;

import com.example.demo.dto.AvaliadorRequestDTO;
import com.example.demo.dto.AvaliadorResponseDTO;
import com.example.demo.service.AvaliadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/avaliadores")
@CrossOrigin(origins = "*")
public class AvaliadorController {

    private final AvaliadorService service;

    public AvaliadorController(AvaliadorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AvaliadorResponseDTO> criar(@RequestBody @Valid AvaliadorRequestDTO dto) {
        AvaliadorResponseDTO response = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AvaliadorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AvaliadorResponseDTO> buscarPorUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.buscarPorUuid(uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable UUID uuid) {
        service.deletar(uuid);
        return ResponseEntity.noContent().build();
    }
}