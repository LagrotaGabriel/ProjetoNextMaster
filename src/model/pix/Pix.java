package model.pix;
import model.cliente.Cliente;

public class Pix {

    private Cliente cliente;
    private final String conteudoChave;
    private final TipoChavePix tipoChavePix;

    public Pix(Cliente cliente, String conteudoChave, TipoChavePix tipoChavePix){
        this.cliente = cliente;
        this.conteudoChave = conteudoChave;
        this.tipoChavePix = tipoChavePix;
    }

    // Setters and Getters
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
