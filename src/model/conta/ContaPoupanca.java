package model.conta;
import model.cartao.Cartao;
import model.cliente.Cliente;

public class ContaPoupanca extends Conta{

    // Atributos
    private Float rendimentoMensal;
    private Float limiteDiario;

    // Construtor
    public ContaPoupanca(Cliente cliente, Integer agencia, Integer conta, Float saldo){

        super(cliente);
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
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

    public String acrescentarRendimento(){

        Float rendimento = (getSaldo()/100)*0.03f;
        setSaldo(getSaldo() + rendimento);
        return ("Foi adicionado o rendimento de 0.03% (R$ " + rendimento + ")");

    }
}
