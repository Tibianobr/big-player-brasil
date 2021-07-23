package com.bpb.bigplayerbrasil.candidato.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CandidatoInvalidoException extends Throwable {
    public CandidatoInvalidoException(String message) {
        super(message);
    }
}
