package model.conta;

import model.cartao.Cartao;

public class ContaCorrente extends Conta{

    // Atributos
    private Float taxaManutencao;
    private Float limiteCredito;

    // Construtor
    public ContaCorrente(Integer agencia, Integer conta, Cartao cartao, Float saldo, Float taxaManutencao, Float limiteCredito) {

        this.agencia = agencia;
        this.conta = conta;
        this.cartao = cartao;
        this.saldo = saldo;
        this.taxaManutencao = taxaManutencao;
        this.limiteCredito = limiteCredito;
        this.contaTipo = ContaTipo.COMUM;

    }

    // Getters e Setters
    public Float getTaxaManutencao() {
        return taxaManutencao;
    }
    public void setTaxaManutencao(Float taxaManutencao) {
        this.taxaManutencao = taxaManutencao;
    }
    public Float getLimiteCredito() {
        return limiteCredito;
    }
    public void setLimiteCredito(Float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

}
