package com.bpb.bigplayerbrasil.avaliacao.service;

import com.bpb.bigplayerbrasil.avaliacao.enums.SituacaoAvaliacao;
import com.bpb.bigplayerbrasil.avaliacao.model.Avaliacao;
import com.bpb.bigplayerbrasil.candidato.exceptions.CandidatoInvalidoException;
import com.bpb.bigplayerbrasil.candidato.model.Candidato;
import com.bpb.bigplayerbrasil.candidato.repository.CandidatoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final CandidatoRepository candidatoRepository;

    public AvaliacaoService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    public Avaliacao gerarAvaliacaoPendente() {
        return new Avaliacao(SituacaoAvaliacao.PENDENTE, null);
    }

    public void avaliarCandidato(String cpf, Avaliacao avaliacao) throws CandidatoInvalidoException {
        Optional<Candidato> candidato = candidatoRepository.findById(cpf);
        if (candidato.isPresent()) {
            avaliacao.setHorarioAvaliacao(LocalDateTime.now());
            candidato.get().setAvaliacao(avaliacao);
            candidatoRepository.save(candidato.get());
        } else {
            throw new CandidatoInvalidoException("Não existe um candidato com o CPF informado.");
        }
    }
}
