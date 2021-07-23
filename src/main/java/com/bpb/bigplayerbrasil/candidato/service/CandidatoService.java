package com.bpb.bigplayerbrasil.candidato.service;

import com.bpb.bigplayerbrasil.avaliacao.model.Avaliacao;
import com.bpb.bigplayerbrasil.avaliacao.service.AvaliacaoService;
import com.bpb.bigplayerbrasil.candidato.dto.CandidatoDTO;
import com.bpb.bigplayerbrasil.candidato.exceptions.CandidatoInvalidoException;
import com.bpb.bigplayerbrasil.candidato.model.Candidato;
import com.bpb.bigplayerbrasil.candidato.repository.CandidatoRepository;
import com.bpb.bigplayerbrasil.utils.JsonPage;
import com.bpb.bigplayerbrasil.utils.VideoUtils;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CandidatoService {
    private final CandidatoRepository candidatoRepository;
    private final AvaliacaoService avaliacaoService;

    @Autowired
    public CandidatoService(CandidatoRepository candidatoRepository, AvaliacaoService avaliacaoService) {
        this.candidatoRepository = candidatoRepository;
        this.avaliacaoService = avaliacaoService;
    }

    public void registrarInscricao(CandidatoDTO candidatoForm, MultipartFile video) throws IOException, CandidatoInvalidoException {
        Candidato candidato = gerarInscricaoCandidato(candidatoForm, video);
        salvarInscricao(candidato);
    }

    private void salvarInscricao(Candidato candidato) {
        candidatoRepository.save(candidato);
    }

    private Binary converterVideoParaSalvar(MultipartFile video) throws IOException {
        return VideoUtils.converterVideoMultiPartParaBinary(video);
    }

    private Candidato gerarInscricaoCandidato(CandidatoDTO candidatoForm, MultipartFile video) throws CandidatoInvalidoException, IOException {
        if (candidatoRepository.existsById(candidatoForm.getCpf())) {
            throw new CandidatoInvalidoException("Já existe um candidato com esse CPF.");
        } else {
            Binary videoConvertido = converterVideoParaSalvar(video);
            return new Candidato(candidatoForm.getCpf(),
                    candidatoForm.getNome(),
                    candidatoForm.getTelefone(),
                    candidatoForm.getEmail(),
                    videoConvertido,
                    avaliacaoService.gerarAvaliacaoPendente(),
                    LocalDateTime.now());
        }
    }

    public Avaliacao checarSituacao(CandidatoDTO candidatoDTO) throws CandidatoInvalidoException {
        Optional<Candidato> candidato = candidatoRepository.findById(candidatoDTO.getCpf());
        if (candidato.isPresent()) {
            return candidato.get().getAvaliacao();
        }
        throw new CandidatoInvalidoException("Não existe um candidato com o CPF informado.");
    }

    public JsonPage<Candidato> listarCandidatos(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return new JsonPage<>(candidatoRepository.findAll(paging), paging);
    }

    public byte[] baixarVideo(String cpf) throws CandidatoInvalidoException {
        Optional<Candidato> candidato = candidatoRepository.findById(cpf);
        return candidato.map(cand -> VideoUtils.converterParaDownload(cand.getVideo()))
                .orElseThrow(() -> new CandidatoInvalidoException("Não existe um candidato com o CPF informado."));
    }
}
