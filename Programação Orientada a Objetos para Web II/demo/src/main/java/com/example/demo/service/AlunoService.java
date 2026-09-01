package com.example.demo.service;

import com.example.demo.dto.AlunoRequestDTO;
import com.example.demo.dto.AlunoResponseDTO;
import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public AlunoResponseDTO criar(AlunoRequestDTO dto) {
        if (repository.existsByMatricula(dto.matricula())) {
            throw new RuntimeException("Matrícula já cadastrada!");
        }
        if (repository.existsByEmail(dto.email())) {
            throw new RuntimeException("E-mail já cadastrado!");
        }

        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setMatricula(dto.matricula());
        aluno.setEmail(dto.email());

        Aluno salvo = repository.save(aluno);
        return toResponseDTO(salvo);
    }

    public List<AlunoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public AlunoResponseDTO buscarPorUuid(UUID uuid) {
        Aluno aluno = repository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));
        return toResponseDTO(aluno);
    }

    public void deletar(UUID uuid) {
        Aluno aluno = repository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));
        repository.delete(aluno);
    }

    private AlunoResponseDTO toResponseDTO(Aluno aluno) {
        return new AlunoResponseDTO(
                aluno.getUuid(),
                aluno.getNome(),
                aluno.getMatricula(),
                aluno.getEmail()
        );
    }
}