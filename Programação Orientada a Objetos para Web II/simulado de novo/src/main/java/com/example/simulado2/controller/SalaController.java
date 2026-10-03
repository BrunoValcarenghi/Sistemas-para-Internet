package com.example.simulado2.controller;

import com.example.simulado2.model.Sala;
import com.example.simulado2.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
@Tag(name="salas", description = "endpoints para salas")
public class SalaController {

    private SalaService salaService;

    public SalaController(SalaService salaService){

        this.salaService = salaService;

    }

    @PostMapping
    @Operation(summary = "Cadastrar sala")
    public ResponseEntity<Sala> salvar(@Valid @RequestBody Sala sala) {

        Sala novaSala = salaService.salvar(sala);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSala);

    }

    @GetMapping
    @Operation(summary = "Buscar todas salas")
    public ResponseEntity<List<Sala>> buscarTodas(){

        List<Sala> salas = salaService.buscarTodas();
        return ResponseEntity.ok(salas);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar sala especifica por id")
    public ResponseEntity<Sala> buscarId(@PathVariable int id) {

        Sala sala = salaService.buscarId(id);
        return ResponseEntity.ok(sala);

    }

    @PutMapping("/{id}")
    @Operation(summary = "atualizar sala ja existente")
    public ResponseEntity<Sala> atualizar(@PathVariable int id, Sala novasala) {

        Sala sala = salaService.atualizar(id, novasala);
        return ResponseEntity.ok(sala);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "excluir sala")
    public ResponseEntity<Void> deletar(@PathVariable int id){

        salaService.deletar(id);
        return ResponseEntity.noContent().build();

    }

}
