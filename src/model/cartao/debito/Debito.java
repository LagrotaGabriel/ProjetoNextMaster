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
    public Debito(Integer idCartao, String numeroCartao, String bandeira, String senha, Boolean ativo, Cliente cliente, float limiteTransacao) {
        super(idCartao, numeroCartao, bandeira, senha, ativo, cliente);
        this.limiteTransacao = limiteTransacao;
    }

    // Getters e setters
    public float getLimiteTransacao() {
        return limiteTransacao;
    }
    public void setLimiteTransacao(float limiteTransacao) {
        this.limiteTransacao = limiteTransacao;
    }


}
