package model.conta;
import model.cartao.Cartao;

public class ContaPoupanca extends Conta{

    // Atributos
    private Float rendimentoMensal;
    private Float limiteDiario;

    // Construtor
    public ContaPoupanca(Integer agencia, Integer conta, Cartao cartao,
                         Float saldo, Float rendimentoMensal, float limiteDiario){

        this.agencia = agencia;
        this.conta = conta;
        this.cartao = cartao;
        this.saldo = saldo;
        this.rendimentoMensal = rendimentoMensal;
        this.limiteDiario = limiteDiario;
        this.contaTipo = ContaTipo.COMUM;

    }

    // Getters e Setters
    public Float getRendimentoMensal() {
        return rendimentoMensal;
    }
    public void setRendimentoMensal(Float rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }
    public Float getLimiteDiario() {
        return limiteDiario;
    }
    public void setLimiteDiario(Float limiteDiario) {
        this.limiteDiario = limiteDiario;
    }

    public void acrescenteRendimento(){

    }
}
