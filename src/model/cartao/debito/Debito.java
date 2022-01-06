package model.cartao.debito;

import model.cartao.Cartao;
import model.cartao.Transacao;
import model.cartao.credito.Limite;
import model.cliente.Cliente;

import java.util.ArrayList;

public class Debito extends Cartao {

    // Atributos
    private ArrayList<Transacao> extrato = new ArrayList<>();
    private Float totalExtrato;

    // Construtor
    public Debito(String numeroCartao, String codigoDeSeguranca, Cliente cliente, String validade, String senha,
                  ArrayList<Transacao> extrato, Float totalExtrato) {
        this.numeroCartao = numeroCartao;
        this.codigoDeSeguranca = codigoDeSeguranca;
        this.cliente = cliente;
        this.validade = validade;
        this.senha = senha;
        this.extrato = extrato;
        this.totalExtrato = totalExtrato;
    }

    // Getters e Setters
    public ArrayList<Transacao> getExtrato() {
        return extrato;
    }
    public void setExtrato(ArrayList<Transacao> extrato) {
        this.extrato = extrato;
    }

    // Ver Saldo
    public void saldo(){

    }
    // Ver Extrato
    public void extrato(){

    }
}
