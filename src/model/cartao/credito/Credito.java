package model.cartao.credito;

import model.cartao.Cartao;
import model.cartao.Transacao;
import model.cliente.Cliente;

import java.util.ArrayList;

public class Credito extends Cartao {

    // Atributos
    private Limite limite;
    private ArrayList<Transacao> fatura;
    private Float totalFatura;

    // Construtor
    public Credito(String numeroCartao, String codigoDeSeguranca, Cliente cliente, String validade, String senha,
                   Limite limite, ArrayList<Transacao> fatura, Float totalFatura) {
        this.numeroCartao = numeroCartao;
        this.codigoDeSeguranca = codigoDeSeguranca;
        this.cliente = cliente;
        this.validade = validade;
        this.senha = senha;
        this.limite = limite;
        this.fatura = fatura;
        this.totalFatura = totalFatura;
    }

    // Getters and Setters
    public Limite getLimite() {
        return limite;
    }
    public void setLimite(Limite limite) {
        this.limite = limite;
    }
    public ArrayList<Transacao> getFatura() {
        return fatura;
    }
    public void setFatura(ArrayList<Transacao> fatura) {
        this.fatura = fatura;
    }
    public Float getTotalFatura() {
        return totalFatura;
    }
    public void setTotalFatura(Float totalFatura) {
        this.totalFatura = totalFatura;
    }

    // Solicitar aumento de limite
    public void solicitarLimite(){

    }

    // Ver limite
    public void verLimite(){

    }

    // Ver Fatura
    public void verFatura(){

    }

    // Parcelar Fatura
    public void parcelarFatura(){

    }

    // Pagar Fatura
    public void pagarFatura(){

    }
}
