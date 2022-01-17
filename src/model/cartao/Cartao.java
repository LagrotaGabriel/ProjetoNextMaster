package model.cartao;
import model.cliente.*;

import java.util.Random;
import java.util.UUID;

public abstract class Cartao {

    // Atributos
    protected String idCartao;
    protected String numeroCartao;
    protected String bandeira;
    protected String senha;
    protected Boolean ativo;
    protected Cliente cliente;

    Integer randomNumeroCartao = new Random().nextInt(111111,999999);
    Integer randomNumeroSenha = new Random().nextInt(1111, 9999);

    public Cartao(Cliente cliente) {
        this.idCartao = UUID.randomUUID().toString();
        this.numeroCartao = randomNumeroCartao.toString();
        this.senha = randomNumeroSenha.toString();
        this.bandeira = "VISA";
        this.ativo = true;
        this.cliente = cliente;
    }

    // Getters e Setters
    public String getNumeroCartao() {
        return numeroCartao;
    }
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    public String getBandeira() {
        return bandeira;
    }
    public void setBandeira(String codigoDeSeguranca) {
        this.bandeira = codigoDeSeguranca;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public String getIdCartao() {
        return idCartao;
    }
    public void setIdCartao(String idCartao) {
        this.idCartao = idCartao;
    }
}
