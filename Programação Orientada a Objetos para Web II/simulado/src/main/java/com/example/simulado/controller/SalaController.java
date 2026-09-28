package com.example.simulado.controller;

import com.example.simulado.model.Sala;
import com.example.simulado.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/salas")
@Tag(name = "Salas")
public class SalaController {

    private final SalaService service;

    public SalaController(SalaService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastrar sala")
    public ResponseEntity<Sala> cadastrar(@RequestBody @Valid Sala sala) {
        Sala salaSalva = service.cadastrar(sala);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salaSalva.getId())
                .toUri();
        return ResponseEntity.created(location).body(salaSalva);
    }

    @GetMapping
    @Operation(summary = "Listar salas")
    public ResponseEntity<List<Sala>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar sala por ID")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar sala")
    public ResponseEntity<Sala> atualizar(@PathVariable Long id, @RequestBody @Valid Sala sala) {
        return ResponseEntity.ok(service.atualizar(id, sala));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir sala")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}