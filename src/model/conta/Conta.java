package model.conta;

// IMPORTAÇÕES
import model.cartao.Cartao;
import model.cartao.credito.Credito;
import model.cartao.debito.Debito;
import model.cliente.Cliente;
import model.pix.Pix;
import model.seguros.Apolice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// CLASSE CONTA
public abstract class Conta {

    // ATRIBUTOS
    protected Integer agencia;
    protected Integer conta;
    protected Cartao cartao;
    protected Float saldo;
    protected ContaTipo contaTipo;
    protected Cliente cliente;
    public ArrayList<Pix> chavesPix = new ArrayList<>();
    public ArrayList<Debito> cartoesDebitoCliente = new ArrayList<>();
    public ArrayList<Credito> cartoesCreditoCliente = new ArrayList<>();
    private final Map<Integer, Apolice> seguros = new HashMap<>();
    public Conta(Cliente cliente) {
        this.cliente = cliente;
    }

    // GETTERS E SETTERS
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
    public ArrayList<Credito> getCartoesCreditoCliente(){
        return(cartoesCreditoCliente);
    }
    public Map<Integer, Apolice> getSeguros(){
        return seguros;
    }
    public void addSeguros(Integer key, Apolice value){
        this.seguros.put(key, value);
    }

}
