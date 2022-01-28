package model.conta;

// IMPORTAÇÕES
import model.cartao.Cartao;
import model.cliente.Cliente;
import util.Layout;

public class ContaCorrente extends Conta{

    // ATRIBUTOS
    private Float taxaManutencao;

    // CONSTRUTOR
    public ContaCorrente(Cliente cliente, Integer agencia, Integer conta, Float saldo, Float taxaManutencao) {

        super(cliente);
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.taxaManutencao = taxaManutencao;
        this.contaTipo = ContaTipo.COMUM;

    }

    // GETTERS E SETTERS
    public Integer getAgencia() {
        return agencia;
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

    // DESCONTAR TAXA DE MANUTENÇÃO DA CONTA
    public String descontarTaxa(){
        // SE O SALDO DA CONTA É MAIOR QUE 0
        if(getSaldo() > 0) {
            // CALCULA O DESCONTO DA TAXA DE 0.45
            Float descontoTaxa = (getSaldo() / 100) * 0.45f;
            // SUBTRAI A TAXA DO SALDO
            setSaldo(getSaldo() - descontoTaxa);
            // RETORNA VALOR DO DESCONTO
            return ("Foi descontada a taxa de 0.45% (" + Layout.convertToReais(descontoTaxa) + ")");
        }
        // SE NÃO HOUVER DINHEIRO NA CONTA
        else{
            return("Não foi cobrada taxa de manutenção da sua conta");
        }
    }
}
