package com.bpb.bigplayerbrasil.candidato.controller;

import com.bpb.bigplayerbrasil.avaliacao.model.Avaliacao;
import com.bpb.bigplayerbrasil.candidato.dto.CandidatoDTO;
import com.bpb.bigplayerbrasil.candidato.exceptions.CandidatoInvalidoException;
import com.bpb.bigplayerbrasil.candidato.model.Candidato;
import com.bpb.bigplayerbrasil.candidato.service.CandidatoService;
import com.bpb.bigplayerbrasil.candidato.view.CandidatoView;
import com.bpb.bigplayerbrasil.constants.PaginacaoConstantes;
import com.bpb.bigplayerbrasil.utils.JsonPage;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/candidato")
public class CandidatoController {
    private final CandidatoService candidatoService;

    @Autowired
    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @PostMapping(value = "/registro", consumes = {"multipart/form-data"})
    public ResponseEntity<HttpStatus> registrarCandidato(@RequestPart CandidatoDTO candidatoDTO,
                                                         @RequestPart("video") MultipartFile video) throws IOException, CandidatoInvalidoException {
        candidatoService.registrarInscricao(candidatoDTO, video);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/situacao")
    public ResponseEntity<Avaliacao> verificarSituacaoCandidato(@RequestBody CandidatoDTO candidatoDTO) throws CandidatoInvalidoException {
        return new ResponseEntity<>(candidatoService.checarSituacao(candidatoDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/video/{cpf}", produces = {"video/mp4"})
    public ResponseEntity<byte[]> baixarVideoCandidato(@PathVariable String cpf) throws CandidatoInvalidoException {
        return new ResponseEntity<>(candidatoService.baixarVideo(cpf), HttpStatus.FOUND);
    }

    @GetMapping(value = "/lista")
    @JsonView(CandidatoView.listagem.class)
    public ResponseEntity<JsonPage<Candidato>> verificarSituacaoCandidato(
            @RequestParam(required = false, name = "page", defaultValue = PaginacaoConstantes.PAGE)
                    int page,
            @RequestParam(required = false, name = "size", defaultValue = PaginacaoConstantes.SIZE)
                    int size
    ) {
        return new ResponseEntity<>(candidatoService.listarCandidatos(page, size), HttpStatus.OK);
    }


}
