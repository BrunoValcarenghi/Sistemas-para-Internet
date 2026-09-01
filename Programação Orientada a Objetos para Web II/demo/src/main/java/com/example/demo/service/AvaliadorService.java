package com.example.demo.service;

import com.example.demo.dto.AvaliadorRequestDTO;
import com.example.demo.dto.AvaliadorResponseDTO;
import com.example.demo.model.Avaliador;
import com.example.demo.repository.AvaliadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AvaliadorService {

    private final AvaliadorRepository repository;

    public AvaliadorService(AvaliadorRepository repository) {
        this.repository = repository;
    }

    public AvaliadorResponseDTO criar(AvaliadorRequestDTO dto) {
        Avaliador avaliador = new Avaliador();
        avaliador.setNome(dto.nome());
        avaliador.setEspecialidade(dto.especialidade());

        Avaliador salvo = repository.save(avaliador);
        return toResponseDTO(salvo);
    }

    public List<AvaliadorResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public AvaliadorResponseDTO buscarPorUuid(UUID uuid) {
        Avaliador avaliador = repository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Avaliador não encontrado!"));
        return toResponseDTO(avaliador);
    }

    public void deletar(UUID uuid) {
        Avaliador avaliador = repository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Avaliador não encontrado!"));
        repository.delete(avaliador);
    }

    private AvaliadorResponseDTO toResponseDTO(Avaliador avaliador) {
        return new AvaliadorResponseDTO(
                avaliador.getUuid(),
                avaliador.getNome(),
                avaliador.getEspecialidade()
        );
    }
}