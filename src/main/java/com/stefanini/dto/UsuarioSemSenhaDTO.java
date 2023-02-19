package com.stefanini.dto;

import com.stefanini.entities.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioSemSenhaDTO {
    private Long id;
    private String nome;
    private String login;
    private String email;
    private LocalDate dataDeNascimento;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAtualizacao;

    public UsuarioSemSenhaDTO(Long id, String nome, String login,
                              String email, LocalDate dataDeNascimento,
                              LocalDateTime dataDeCriacao, LocalDateTime dataDeAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.dataDeNascimento = dataDeNascimento;
        this.dataDeCriacao = dataDeCriacao;
        this.dataDeAtualizacao = dataDeAtualizacao;
    }

    public UsuarioSemSenhaDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.email = usuario.getEmail();
        this.dataDeNascimento = usuario.getDataDeNascimento();
        this.dataDeCriacao = usuario.getDataDeCriacao();
        this.dataDeAtualizacao = usuario.getDataDeAtualizacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public LocalDateTime getDataDeAtualizacao() {
        return dataDeAtualizacao;
    }

    public void setDataDeAtualizacao(LocalDateTime dataDeAtualizacao) {
        this.dataDeAtualizacao = dataDeAtualizacao;
    }
}
