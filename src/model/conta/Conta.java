package model.conta;
import model.cartao.Cartao;
import model.cliente.Cliente;
import model.pix.Pix;
import java.util.ArrayList;

public abstract class Conta {

    // Atributos
    protected Integer agencia;
    protected Integer conta;
    protected Cartao cartao;
    protected Float saldo;
    protected ContaTipo contaTipo;
    protected Cliente cliente;
    public ArrayList<Pix> chavesPix = new ArrayList<>();

    public Conta(Cliente cliente) {
        this.cliente = cliente;
    }

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
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public ArrayList<Pix> getChavesPix() {
        return chavesPix;
    }
    public void setChavesPix(ArrayList<Pix> chavesPix) {
        this.chavesPix = chavesPix;
    }

    // Saldo
    public void Saldo(){

    }

    // Saque
    public String Saque(Float valor){
        if(valor > getSaldo()){
            return("  Saque não autorizado. Seu saldo é: R$" + getSaldo());
        }else{
            setSaldo(getSaldo() - valor);
            return("  Saque efetuado. Seu saldo atual é: R$ " + getSaldo());
        }
    }

    // Deposito()
    public void Deposito(Float valor){
        setSaldo(getSaldo() + valor);
    }

}
