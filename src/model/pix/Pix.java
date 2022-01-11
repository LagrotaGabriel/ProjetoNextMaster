package model.pix;

import dao.Bd;
import model.cliente.Cliente;

public class Pix {

    String id;
    Cliente cliente;
    String conteudoChave;
    Boolean ativo;
    TipoChavePix tipoChavePix;

    public Pix(Cliente cliente, String conteudoChave, TipoChavePix tipoChavePix){
        this.cliente = cliente;
        this.conteudoChave = conteudoChave;
        this.tipoChavePix = tipoChavePix;
    }

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
        return tipoChavePix;
    }
    public void setTipoChavePix(model.pix.TipoChavePix tipoChavePix) {
        tipoChavePix = tipoChavePix;
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
