package com.stefanini.dto;

import javax.persistence.Column;
import java.time.LocalDate;
public class UserForCreateDTO {
    private String nome;
    private String login;
    private String email;
    private String senha;
    private LocalDate dataDeNascimento;

    public UserForCreateDTO() {

    }

    public UserForCreateDTO(String nome, String login, String email, String senha, LocalDate dataDeNascimento) {
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.dataDeNascimento = dataDeNascimento;
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
        //return LocalDate.parse(dataDeNascimento);
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
