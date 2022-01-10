package model.pix;

import dao.Bd;
import model.cliente.Cliente;

public class Pix {

    String id;
    Cliente cliente;
    String conteudoChave;
    Boolean ativo;
    TipoChavePix TipoChavePix;

    // Setters and Getters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getConteudoChave() {
        return conteudoChave;
    }
    public void setConteudoChave(String conteudoChave) {
        this.conteudoChave = conteudoChave;
    }
    public Boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public model.pix.TipoChavePix getTipoChavePix() {
        return TipoChavePix;
    }
    public void setTipoChavePix(model.pix.TipoChavePix tipoChavePix) {
        TipoChavePix = tipoChavePix;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Cadastra pix
    public void cadastraPix(){

    }

    // Deleta Pix
    public void deletaPix(){

    }

    // Transfere Pix
    public void transferePix(){

    }

    // Histórico Pix
    public void historicoPix(){

    }


}
