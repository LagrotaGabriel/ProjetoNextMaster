package model.cliente;
import model.conta.Conta;
import model.pix.Pix;
import model.seguros.Apolice;

import java.util.HashMap;
import java.util.Map;

public class Cliente {

    // Atributos
    private String nome;
    private String cpf;
    private String rg;
    private Endereco endereco;
    private String senha;
    private Pix pix;
    private String email;
    private String telefone;

    // Construtor
    public Cliente(String nome, String cpf, String rg, Endereco endereco, String senha, String email, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.pix = null;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getSenha() {
        return senha;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setNome(String nome) {
        this.nome = nome;
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
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Pix getPix() {
        return pix;
    }
    public void setPix(Pix pix) {
        this.pix = pix;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
