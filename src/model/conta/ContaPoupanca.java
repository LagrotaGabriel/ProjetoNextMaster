package model.conta;
import model.cliente.Cliente;

public class ContaPoupanca extends Conta{

    // Construtor
    public ContaPoupanca(Cliente cliente, Integer agencia, Integer conta, Float saldo){

        super(cliente);
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.contaTipo = ContaTipo.COMUM;

    }

    // MÉTODO QUE ACRESCENTA RENDIMENTO NA CONTA POUPANÇA DO CLIENTE
    public String acrescentarRendimento(){
        // CALCULA O RENDIMENTO DE 0.03%
        Float rendimento = (getSaldo()/100)*0.03f;
        // ADICIONA VALOR AO SALDO
        setSaldo(getSaldo() + rendimento);
        // RETORNA VALOR DE RENDIMENTO ADICIONADO
        return ("Foi adicionado o rendimento de 0.03% (R$ " + rendimento + ")");
    }
}
