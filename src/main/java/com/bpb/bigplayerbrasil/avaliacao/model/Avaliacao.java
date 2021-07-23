package com.bpb.bigplayerbrasil.avaliacao.model;

import com.bpb.bigplayerbrasil.avaliacao.enums.SituacaoAvaliacao;
import com.bpb.bigplayerbrasil.candidato.view.CandidatoView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Avaliacao {
    @JsonView(CandidatoView.listagem.class)
    private SituacaoAvaliacao situacao;
    @JsonView(CandidatoView.listagem.class)
    private LocalDateTime horarioAvaliacao;
    // Avaliado por
}
