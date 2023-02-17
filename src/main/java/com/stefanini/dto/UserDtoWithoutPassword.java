package com.stefanini.dto;

import com.stefanini.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDtoWithoutPassword {
    private Long id;
    private String nome;
    private String login;
    private String email;
    private LocalDate dataDeNascimento;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAtualizacao;

    public UserDtoWithoutPassword(Long id, String nome, String login,
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

    public UserDtoWithoutPassword(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.dataDeNascimento = user.getDataDeNascimento();
        this.dataDeCriacao = user.getDataDeCriacao();
        this.dataDeAtualizacao = user.getDataDeAtualizacao();
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
