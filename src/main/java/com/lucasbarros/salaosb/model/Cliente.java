package com.lucasbarros.salaosb.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank // diz que o valor nao pode ser null, vazio ("") ou conter somente espaços//
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ter um formato válido")
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(
            regexp = "\\d{10,11}",
            message = "O telefone deve conter 10 ou 11 números"
    )
    private String telefone;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern( // compara o valor com um padrão de texto //
            regexp = "\\d{11}", // significa "11" digitos //
            message = "O CPF deve conter exatamente 11 números"
    )
    @Column(nullable = false, unique = true)
    private String cpf;

    public Cliente(){

    }

    public Cliente(String nome, String email, String telefone, String cpf){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getCpf(){
        return this.cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }









}
