package com.stefanini.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.stefanini.interfaces.EntityMessageConstants.*;
import static com.stefanini.interfaces.RegexConstants.*;

@Entity
@Table(name = "tb_usuario")
public class User {
    @Id
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    @Max(value = 50, message = MAX_50_CHARACTERS)
    @NotEmpty(message = NOT_EMPTY)
    @NotNull(message = NOT_NULL)
    private String nome;
    @Column(nullable = false, unique = true)
    @NotNull(message = NOT_NULL)
    @Size(min = 5, max = 20, message = BETWEEN_5_TO_20_CHARACTERS)
    private String login;
    @Column(nullable = false)
    @NotNull(message = NOT_NULL)
    @NotEmpty(message = NOT_EMPTY)
    private String email;
    @Column(nullable = false)
    @NotNull(message = NOT_NULL)
    @NotEmpty(message = NOT_EMPTY)
    @Size(min = 4, max = 10, message = BETWEEN_4_TO_10_CARACTERS)
    @Pattern(regexp = PASSWORD_REGEX)
    private String senha;
    @Column(name = "data_de_nascimento")
    @Pattern(regexp = DAY_OF_BIRTH_REGEX)
    private LocalDate dataDeNascimento;
    @Column(name = "data_de_criacao", nullable = false)
    @NotNull(message = NOT_NULL)
    @NotEmpty(message = NOT_EMPTY)
    @Pattern(regexp = LOCAL_DATE_TIME_REGEX, message = PATTERN_DATE_TIME)
    private LocalDateTime dataDeCriacao;
    @Column(name = "data_de_atualizacao")
    @Pattern(regexp = LOCAL_DATE_TIME_REGEX, message = PATTERN_DATE_TIME)
    private LocalDateTime dataDeAtualizacao;

    public User() {
        dataDeCriacao = LocalDateTime.now();
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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