package model.cartao;
import model.cliente.*;

public abstract class Cartao {

    // Atributos
    protected Integer idCartao;
    protected String numeroCartao;
    protected String bandeira;
    protected String senha;
    protected Boolean ativo;
    protected Cliente cliente;

    public Cartao(Integer idCartao, String numeroCartao, String bandeira, String senha, Boolean ativo, Cliente cliente) {
        this.idCartao = idCartao;
        this.numeroCartao = numeroCartao;
        this.bandeira = bandeira;
        this.senha = senha;
        this.ativo = ativo;
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
    public Integer getIdCartao() {
        return idCartao;
    }
    public void setIdCartao(Integer idCartao) {
        this.idCartao = idCartao;
    }
}
