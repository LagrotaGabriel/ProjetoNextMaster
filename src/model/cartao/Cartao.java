package model.cartao;
import model.cliente.*;

public abstract class Cartao {

    // Atributos
    protected String numeroCartao;
    protected String codigoDeSeguranca;
    protected Cliente cliente;
    protected String validade;
    protected String senha;

    // Getters e Setters
    public String getNumeroCartao() {
        return numeroCartao;
    }
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    public String getCodigoDeSeguranca() {
        return codigoDeSeguranca;
    }
    public void setCodigoDeSeguranca(String codigoDeSeguranca) {
        this.codigoDeSeguranca = codigoDeSeguranca;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public String getValidade() {
        return validade;
    }
    public void setValidade(String validade) {
        this.validade = validade;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
