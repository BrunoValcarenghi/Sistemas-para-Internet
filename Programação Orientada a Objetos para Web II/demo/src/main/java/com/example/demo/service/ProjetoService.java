package com.example.demo.service;

import com.example.demo.dto.ProjetoRequestDTO;
import com.example.demo.dto.ProjetoResponseDTO;
import com.example.demo.model.Projeto;
import com.example.demo.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjetoService {

    private final ProjetoRepository repository;

    public ProjetoService(ProjetoRepository repository) {
        this.repository = repository;
    }

    public ProjetoResponseDTO criar(ProjetoRequestDTO dto) {
        Projeto projeto = new Projeto();
        projeto.setTitulo(dto.titulo());
        projeto.setDescricao(dto.descricao());

        Projeto salvo = repository.save(projeto);
        return toResponseDTO(salvo);
    }

    public List<ProjetoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ProjetoResponseDTO buscarPorUuid(UUID uuid) {
        Projeto projeto = repository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado!"));
        return toResponseDTO(projeto);
    }

    public void deletar(UUID uuid) {
        Projeto projeto = repository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado!"));
        repository.delete(projeto);
    }

    private ProjetoResponseDTO toResponseDTO(Projeto projeto) {
        return new ProjetoResponseDTO(
                projeto.getUuid(),
                projeto.getTitulo(),
                projeto.getDescricao()
        );
    }
}