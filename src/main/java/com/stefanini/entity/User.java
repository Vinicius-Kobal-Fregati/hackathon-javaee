package com.stefanini.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@Table(name = "tb_usuario")
public class User {
    @Id
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    @Max(50)
    @NotEmpty
    @NotNull
    private String nome;
    @Column(nullable = false, unique = true)
    @NotNull
    @Max(20)
    private String login;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String email;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 10)
    private String senha;
    @Column(name = "data_de_nascimento")
    private Date dataDeNascimento;
    @Column(name = "data_de_criacao", nullable = false)
    @NotNull
    @NotEmpty
    private Date dataDeCriacao;
    @Column(name = "data_de_atualizacao")
    private Date dataDeAtualizacao;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Date getDataDeAtualizacao() {
        return dataDeAtualizacao;
    }

    public void setDataDeAtualizacao(Date dataDeAtualizacao) {
        this.dataDeAtualizacao = dataDeAtualizacao;
    }
}