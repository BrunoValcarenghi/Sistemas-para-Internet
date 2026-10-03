package com.example.simulado2.service;

import com.example.simulado2.model.Sala;
import com.example.simulado2.model.Situacao;
import com.example.simulado2.repository.SalaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public Sala salvar(Sala sala){

        return salaRepository.save(sala);

    }

    public List<Sala> buscarTodas(){

        return salaRepository.findAll();

    }

    public Sala buscarId(int id){

        return salaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Id da sala nao encontrado"
        ));

    }

    public Sala atualizar(int id, Sala novaSala){

        Sala antigaSala = buscarId(id);

        antigaSala.setNome(novaSala.getNome());
        antigaSala.setQnt_aluno(novaSala.getQnt_aluno());
        antigaSala.setQnt_pc(novaSala.getQnt_pc());
        antigaSala.setAno(novaSala.getAno());
        antigaSala.setArea(novaSala.getArea());
        antigaSala.setSituacao(novaSala.getSituacao());

        return salaRepository.save(antigaSala);

    }

    public void deletar(int id){

        Sala excluirSala = buscarId(id);

        if (excluirSala.getSituacao() == Situacao.DISPONIVEL){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sala está disponivel");
        }

        salaRepository.delete(excluirSala);

    }

}
