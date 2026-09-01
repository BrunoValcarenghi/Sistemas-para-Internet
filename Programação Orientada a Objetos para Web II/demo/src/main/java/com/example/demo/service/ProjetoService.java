package com.example.demo.service;

import com.example.demo.dto.AlunoResponseDTO;
import com.example.demo.dto.AvaliadorResponseDTO;
import com.example.demo.dto.ProjetoRequestDTO;
import com.example.demo.dto.ProjetoResponseDTO;
import com.example.demo.model.Aluno;
import com.example.demo.model.Avaliador;
import com.example.demo.model.Projeto;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.AvaliadorRepository;
import com.example.demo.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final AlunoRepository alunoRepository;
    private final AvaliadorRepository avaliadorRepository;

    public ProjetoService(ProjetoRepository projetoRepository,
                          AlunoRepository alunoRepository,
                          AvaliadorRepository avaliadorRepository) {
        this.projetoRepository = projetoRepository;
        this.alunoRepository = alunoRepository;
        this.avaliadorRepository = avaliadorRepository;
    }

    public List<ProjetoResponseDTO> listarTodos() {
        return projetoRepository.findAll().stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public ProjetoResponseDTO buscarPorUuid(UUID uuid) {
        Projeto projeto = projetoRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        return converterParaDTO(projeto);
    }

    public ProjetoResponseDTO criar(ProjetoRequestDTO dto) {
        Aluno aluno = alunoRepository.findByUuid(dto.alunoUuid())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Avaliador avaliador = avaliadorRepository.findByUuid(dto.avaliadorUuid())
                .orElseThrow(() -> new RuntimeException("Avaliador não encontrado"));

        Projeto projeto = new Projeto();
        projeto.setTitulo(dto.titulo());
        projeto.setDescricao(dto.descricao());
        projeto.setAluno(aluno);
        projeto.setAvaliador(avaliador);

        Projeto salvo = projetoRepository.save(projeto);
        return converterParaDTO(salvo);
    }

    public void deletar(UUID uuid) {
        Projeto projeto = projetoRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        projetoRepository.delete(projeto);
    }

    private ProjetoResponseDTO converterParaDTO(Projeto projeto) {
        var alunoDTO = projeto.getAluno() != null ?
                new AlunoResponseDTO(projeto.getAluno().getUuid(), projeto.getAluno().getNome(), projeto.getAluno().getMatricula(), projeto.getAluno().getEmail()) : null;

        var avaliadorDTO = projeto.getAvaliador() != null ?
                new AvaliadorResponseDTO(projeto.getAvaliador().getUuid(), projeto.getAvaliador().getNome(), projeto.getAvaliador().getEspecialidade()) : null;

        return new ProjetoResponseDTO(
                projeto.getUuid(),
                projeto.getTitulo(),
                projeto.getDescricao(),
                alunoDTO,
                avaliadorDTO
        );
    }
}