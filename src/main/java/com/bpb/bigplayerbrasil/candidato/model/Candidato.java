package com.bpb.bigplayerbrasil.candidato.model;

import com.bpb.bigplayerbrasil.avaliacao.model.Avaliacao;
import com.bpb.bigplayerbrasil.candidato.view.CandidatoView;
import com.fasterxml.jackson.annotation.JsonView;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Candidato {
    @Id
    @JsonView({CandidatoView.listagem.class})
    private String cpf;
    @JsonView({CandidatoView.listagem.class})
    private String nome;
    @JsonView({CandidatoView.listagem.class})
    private String telefone;
    @JsonView({CandidatoView.listagem.class})
    private String email;
    private Binary video;
    @JsonView(CandidatoView.listagem.class)
    private Avaliacao avaliacao;
    @JsonView({CandidatoView.listagem.class})
    private LocalDateTime dataHoraRegistro;

    public Candidato(String cpf, String nome, String telefone, String email, Binary video, Avaliacao avaliacao, LocalDateTime dataHoraRegistro) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.video = video;
        this.avaliacao = avaliacao;
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Binary getVideo() {
        return video;
    }

    public void setVideo(Binary video) {
        this.video = video;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDateTime getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(LocalDateTime dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }
}
