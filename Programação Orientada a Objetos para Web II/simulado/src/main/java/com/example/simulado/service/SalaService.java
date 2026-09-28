package com.example.simulado.service;

import com.example.simulado.model.Sala;
import com.example.simulado.model.Situacao;
import com.example.simulado.repository.SalaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SalaService {

    private final SalaRepository repository;

    public SalaService(SalaRepository repository) {
        this.repository = repository;
    }

    public Sala cadastrar(Sala sala) {
        return repository.save(sala);
    }

    public List<Sala> listarTodas() {
        return repository.findAll();
    }

    public Sala buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada."));
    }

    public Sala atualizar(Long id, Sala salaAtualizada) {
        Sala salaExistente = buscarPorId(id);

        salaExistente.setNome(salaAtualizada.getNome());
        salaExistente.setCodigo(salaAtualizada.getCodigo());
        salaExistente.setCapacidadeAlunos(salaAtualizada.getCapacidadeAlunos());
        salaExistente.setQuantidadeComputadores(salaAtualizada.getQuantidadeComputadores());
        salaExistente.setAnoConstrucao(salaAtualizada.getAnoConstrucao());
        salaExistente.setArea(salaAtualizada.getArea());
        salaExistente.setSituacao(salaAtualizada.getSituacao());

        return repository.save(salaExistente);
    }

    public void excluir(Long id) {
        Sala sala = buscarPorId(id);

        if (Situacao.DISPONIVEL.equals(sala.getSituacao())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salas com situação DISPONIVEL não podem ser excluídas.");
        }

        repository.delete(sala);
    }
}