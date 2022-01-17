package model.conta;
import model.cartao.Cartao;
import model.cartao.credito.Credito;
import model.cartao.debito.Debito;
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
    public ArrayList<Debito> cartoesDebitoCliente = new ArrayList<>();
    public ArrayList<Credito> cartoesCreditoCliente = new ArrayList<>();

    public Conta(Cliente cliente) {
        this.cliente = cliente;
    }

    // Getters e Setters
    public Integer getAgencia() {
        return agencia;
    }
    public Integer getConta() {
        return conta;
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

    public ArrayList<Debito> getCartoesDebitoCliente(){
        return(cartoesDebitoCliente);
    }
    public void addCartoesDebitoCliente(Debito cartaoDebito){
        cartoesDebitoCliente.add(cartaoDebito);
    }

    public ArrayList<Credito> getCartoesCreditoCliente(){
        return(cartoesCreditoCliente);
    }
    public void addCartoesCreditoCliente(Credito cartaoCredito){
        cartoesCreditoCliente.add(cartaoCredito);
    }

}
