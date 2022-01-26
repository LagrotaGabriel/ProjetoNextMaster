package model.cartao;

// IMPORTAÇÕES
import model.cliente.Cliente;
import model.conta.Conta;
import java.util.Date;

// CLASSE TRANSAÇÃO
public class Transacao {

    // ATRIBUTOS
    private final Date dataCompra;
    private final String descricao;
    private Float valor;
    private Cartao cartao;
    private Conta contaPagante;
    private Cliente clientePagante;

    // CONSTRUTOR
    public Transacao(Date dataCompra, String descricao, Float valor, Cliente clientePagante, Conta contaPagante, Cartao cartao) {
        this.dataCompra = dataCompra;
        this.descricao = descricao;
        this.valor = valor;
        this.clientePagante = clientePagante;
        this.cartao = cartao;
        this.contaPagante = contaPagante;
    }

    // GETTERS E SETTERS
    public Date getDataCompra() {
        return dataCompra;
    }
    public String getDescricao() {
        return descricao;
    }
    public Float getValor() {
        return valor;
    }
    public void setValor(Float valor) {
        this.valor = valor;
    }
    public Cliente getClientePagante() {
        return clientePagante;
    }
    public void setClientePagante(Cliente clientePagante) {
        this.clientePagante = clientePagante;
    }
    public Cartao getCartao() {
        return cartao;
    }
    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
    public Conta getContaPagante() {
        return contaPagante;
    }
    public void setContaPagante(Conta contaPagante) {
        this.contaPagante = contaPagante;
    }

}
