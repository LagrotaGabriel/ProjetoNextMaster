package model.cliente;

import model.conta.Conta;

public class Cliente {

    // Atributos
    private String nome;
    private String cpf;
    private String rg;
    private Endereco endereco;
    private Conta conta;
    private String senha;

    // Construtor
    public Cliente(String nome, String cpf, String rg, Endereco endereco, String senha){
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.senha = senha;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Cadastrar Cliente
    public void cadastrarCliente(){

    }

    // Gerar Conta
    public void gerarConta(){

    }
}
