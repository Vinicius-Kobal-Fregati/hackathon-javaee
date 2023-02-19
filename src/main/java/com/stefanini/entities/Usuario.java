package com.stefanini.entities;

import com.stefanini.dto.UsuarioCriacaoDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.stefanini.interfaces.MensagensConstantes.*;
import static com.stefanini.interfaces.RegexConstantes.SENHA_REGEX;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false)
    @Size(max = 50, message = MAXIMO_50_CARACTERES)
    @NotEmpty(message = NAO_VAZIO)
    @NotNull(message = NAO_NULO)
    private String nome;
    @Column(nullable = false, unique = true)
    @NotNull(message = NAO_NULO)
    @Size(min = 5, max = 20, message = ENTRE_5_A_20_CARACTERES)
    private String login;
    @Column(nullable = false)
    @NotNull(message = NAO_NULO)
    @NotEmpty(message = NAO_VAZIO)
    private String email;
    @Column(nullable = false)
    @NotNull(message = NAO_NULO)
    @NotEmpty(message = NAO_VAZIO)
    @Size(min = 4, max = 10, message = ENTRE_4_A_10_CARACTERES)
    @Pattern(regexp = SENHA_REGEX)
    private String senha;
    @Column(name = "data_de_nascimento")
    private LocalDate dataDeNascimento;
    @Column(name = "data_de_criacao", nullable = false)
    @NotNull(message = NAO_NULO)
    private LocalDateTime dataDeCriacao;
    @Column(name = "data_de_atualizacao")
    private LocalDateTime dataDeAtualizacao;

    public Usuario() {
        dataDeCriacao = LocalDateTime.now();
    }

    public Usuario(UsuarioCriacaoDTO user) {
        this.login = user.getLogin();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.senha = user.getSenha();
        this.dataDeNascimento = user.getDataDeNascimento();
        this.dataDeCriacao = LocalDateTime.now();
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