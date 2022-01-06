package model.cartao;
import model.conta.Conta;
import java.util.ArrayList;
import java.util.Date;

public class Transacao {

    // Atributos
    private Date dataCompra;
    private String descricao;
    private Float valor;
    private Cartao cartao;
    private Conta contaPagante;
    private Conta contaRecebimento;

    // Construtor
    public Transacao(Date dataCompra, String descricao, Float valor, Conta contaPagante, Conta contaRecebimento) {
        this.dataCompra = dataCompra;
        this.descricao = descricao;
        this.valor = valor;
        this.contaPagante = contaPagante;
        this.contaRecebimento = contaRecebimento;
    }

    // Getters e Setters
    public Date getDataCompra() {
        return dataCompra;
    }
    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Float getValor() {
        return valor;
    }
    public void setValor(Float valor) {
        this.valor = valor;
    }
    public Conta getContaPagante() {
        return contaPagante;
    }
    public void setContaPagante(Conta contaPagante) {
        this.contaPagante = contaPagante;
    }
    public Conta getContaRecebimento() {
        return contaRecebimento;
    }
    public void setContaRecebimento(Conta contaRecebimento) {
        this.contaRecebimento = contaRecebimento;
    }
    public Cartao getCartao() {
        return cartao;
    }
    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    // Add Transação
    public void addTransacao(Transacao transacao){

    }
}
