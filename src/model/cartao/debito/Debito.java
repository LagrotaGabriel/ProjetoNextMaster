package model.cartao.debito;
import model.cartao.Cartao;
import model.cartao.Transacao;
import model.cartao.credito.Limite;
import model.cliente.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Debito extends Cartao {

    // Atributos
    private float limiteTransacao;
    public static Map<Integer, Transacao> extrato = new HashMap<>();

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

    // Adicionar ao extrato
    public static void salvarTransacao(Transacao transacao){

        // ID AUTOINCREMENT
        int pos;
        if(extrato.size() == 0){
            pos = 1;
        }
        else{
            pos = extrato.size()+1;
        }

        // ADICIONAR AO BD INTERNO DO CARTÃO
        extrato.put(pos, transacao);

    }


}
