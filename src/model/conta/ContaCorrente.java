package model.conta;

import model.cartao.Cartao;
import model.cliente.Cliente;

public class ContaCorrente extends Conta{

    // Atributos
    private Float taxaManutencao;
    private Float limiteCredito;

    // Construtor
    public ContaCorrente(Cliente cliente, Integer agencia, Integer conta, Float saldo, Float taxaManutencao) {

        super(cliente);
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.taxaManutencao = taxaManutencao;
        this.contaTipo = ContaTipo.COMUM;

    }

    // Getters e Setters
    @Override
    public Integer getAgencia() {
        return agencia;
    }
    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }
    public Integer getConta() {
        return this.conta;
    }
    public void setConta(Integer conta) {
        this.conta = conta;
    }
    public Cartao getCartao() {
        return cartao;
    }
    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
    public Float getSaldo() {
        return saldo;
    }
    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
    public ContaTipo getContaTipo() {
        return contaTipo;
    }
    public void setContaTipo(ContaTipo contaTipo) {
        this.contaTipo = contaTipo;
    }
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

    // Descontar taxa
    public String descontarTaxa(){

        if(getSaldo() > 0) {
            Float descontoTaxa = (getSaldo() / 100) * 0.45f;
            setSaldo(getSaldo() - descontoTaxa);
            return ("Foi descontada a taxa de 0.45% (R$ " + descontoTaxa + ")");
        }else{
            return("Não foi cobrada taxa de manutenção da sua conta");
        }
    }
}
