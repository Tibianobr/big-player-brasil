package com.bpb.bigplayerbrasil.candidato.repository;

import com.bpb.bigplayerbrasil.candidato.model.Candidato;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandidatoRepository extends MongoRepository<Candidato, String> {
}
