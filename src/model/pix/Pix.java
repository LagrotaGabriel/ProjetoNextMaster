package model.pix;

// IMPORTAÇÕES
import model.cliente.Cliente;

public class Pix {

    // DECLARAÇÃO DE ATRIBUTOS
    private Cliente cliente;
    private final String conteudoChave;
    private final TipoChavePix tipoChavePix;

    // CONSTRUTOR
    public Pix(Cliente cliente, String conteudoChave, TipoChavePix tipoChavePix){
        this.cliente = cliente;
        this.conteudoChave = conteudoChave;
        this.tipoChavePix = tipoChavePix;
    }

    // GETTERS E SETTERS
    public String getConteudoChave() {
        return conteudoChave;
    }
    public model.pix.TipoChavePix getTipoChavePix() {
        return tipoChavePix;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
