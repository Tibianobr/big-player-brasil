package com.bpb.bigplayerbrasil.avaliacao.controller;

import com.bpb.bigplayerbrasil.avaliacao.model.Avaliacao;
import com.bpb.bigplayerbrasil.avaliacao.service.AvaliacaoService;
import com.bpb.bigplayerbrasil.candidato.exceptions.CandidatoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @Autowired
    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping("/{cpf}")
    public ResponseEntity<?> julgarCandidato(@RequestBody Avaliacao avaliacao, @PathVariable String cpf)
            throws CandidatoInvalidoException {
        avaliacaoService.avaliarCandidato(cpf, avaliacao);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}