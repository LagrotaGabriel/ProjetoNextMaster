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
    public Debito(Cliente cliente, float limite) {
        super(cliente);
        this.limiteTransacao = limite;
    }

    // Getters e setters
    public float getLimiteTransacao() {
        return limiteTransacao;
    }
    public void setLimiteTransacao(float limiteTransacao) {
        this.limiteTransacao = limiteTransacao;
    }


}
