package model.cartao.credito;

// IMPORTAÇÕES
import model.cartao.Cartao;
import model.cartao.TipoCartao;
import model.cartao.Transacao;
import model.cliente.Cliente;
import java.util.HashMap;
import java.util.Map;

public class Credito extends Cartao {

    // ATRIBUTOS
    private final Limite limite;
    public static Map<Integer, Transacao> fatura = new HashMap<>();

    // CONSTRUTOR
    public Credito(Cliente cliente, Float limite, TipoCartao tipoCartao) {
        super(cliente, tipoCartao);
        this.limite = new Limite(limite , 0.00f);
    }

    // GETTERS E SETTERS
    public Limite getLimite() {
        return limite;
    }
    public static Map<Integer, Transacao> getFatura() {
        return fatura;
    }

    // ADICIONAR NA FATURA
    public static void salvarTransacao(Transacao transacao){
        // ID AUTOINCREMENT
        int pos;
        if(fatura.size() == 0){
            pos = 1;
        }
        else{
            pos = fatura.size()+1;
        }
        // ADICIONAR AO BD INTERNO DO CARTÃO
        fatura.put(pos, transacao);

    }
}
