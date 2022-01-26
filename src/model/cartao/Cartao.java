package model.cartao;

// IMPORTAÇÕES
import model.cliente.*;
import java.util.Random;
import java.util.UUID;

public abstract class Cartao {

    // ATRIBUTOS
    private String idCartao;
    private String numeroCartao;
    private String bandeira;
    private String senha;
    private Boolean ativo;
    private Cliente cliente;
    private TipoCartao tipoCartao;

    // CONSTRUTOR
    public Cartao(Cliente cliente, TipoCartao tipoCartao) {
        this.idCartao = UUID.randomUUID().toString();
        int randomNumeroCartao = new Random().nextInt(111111, 999999);
        this.numeroCartao = Integer.toString(randomNumeroCartao);
        int randomNumeroSenha = new Random().nextInt(1111, 9999);
        this.senha = Integer.toString(randomNumeroSenha);
        this.bandeira = "VISA";
        this.ativo = true;
        this.cliente = cliente;
        this.tipoCartao = tipoCartao;
    }

    // GETTERS E SETTERS
    public String getNumeroCartao() {
        return numeroCartao;
    }
    public String getBandeira() {
        return bandeira;
    }
    public Boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public TipoCartao getTipoCartao() {
        return tipoCartao;
    }
}
