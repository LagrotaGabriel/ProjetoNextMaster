package model.cartao.debito;
import model.cartao.Cartao;
import model.cartao.Transacao;
import model.cartao.credito.Limite;
import model.cliente.Cliente;
import java.util.ArrayList;

public class Debito extends Cartao {

    // Atributos
    private float limiteTransacao;

    // Construtor
    public Debito(Cliente cliente) {
        super(cliente);
        this.limiteTransacao = 1000.00f;
    }

    // Getters e setters
    public float getLimiteTransacao() {
        return limiteTransacao;
    }
    public void setLimiteTransacao(float limiteTransacao) {
        this.limiteTransacao = limiteTransacao;
    }


}
