package model.conta;
import model.cartao.Cartao;

public abstract class Conta {

    // Atributos
    protected Integer agencia;
    protected Integer conta;
    protected Cartao cartao;
    protected Float saldo;
    protected ContaTipo contaTipo;

    // Getters e Setters
    public Integer getAgencia() {
        return agencia;
    }
    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }
    public Integer getConta() {
        return conta;
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

    // Saldo
    public void Saldo(){

    }

    // Saque
    public void Saque(){

    }

    // Deposito()
    public void Deposito(){

    }

}
