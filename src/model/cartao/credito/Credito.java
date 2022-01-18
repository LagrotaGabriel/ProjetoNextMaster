package model.cartao.credito;
import model.cartao.Cartao;
import model.cartao.TipoCartao;
import model.cartao.Transacao;
import model.cliente.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Credito extends Cartao {

    // Atributos
    private Limite limite;
    public static Map<Integer, Transacao> fatura = new HashMap<>();


    // Construtor
    public Credito(Cliente cliente, Float limite, TipoCartao tipoCartao) {
        super(cliente, tipoCartao);
        this.limite = new Limite(limite , 0.00f);
    }

    // Getters and Setters
    public Limite getLimite() {
        return limite;
    }
    public void setLimite(Limite limite) {
        this.limite = limite;
    }
    public static Map<Integer, Transacao> getFatura() {
        return fatura;
    }
    public static void setFatura(Map<Integer, Transacao> fatura) {
        Credito.fatura = fatura;
    }


    // Adicionar na fatura
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
