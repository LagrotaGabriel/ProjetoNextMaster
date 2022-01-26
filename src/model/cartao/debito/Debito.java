package model.cartao.debito;

// IMPORTAÇÕES
import model.cartao.Cartao;
import model.cartao.TipoCartao;
import model.cartao.Transacao;
import model.cliente.Cliente;
import java.util.HashMap;
import java.util.Map;

public class Debito extends Cartao {

    // ATRIBUTOS
    private final float limiteTransacao;
    public static Map<Integer, Transacao> extrato = new HashMap<>();

    // CONSTRUTOR
    public Debito(Cliente cliente, float limite, TipoCartao tipoCartao) {
        super(cliente, tipoCartao);
        this.limiteTransacao = limite;
    }

    // GETTERS AND SETTERS
    public float getLimiteTransacao() {
        return limiteTransacao;
    }

    // SALVAR TRANSAÇÃO
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
