package model.cliente;

public class Endereco {

    private String cidade;
    private String estado;
    private String bairro;
    private Integer numero;
    private String rua;
    private String cep;

    // Construtor
    public Endereco(String cidade, String estado, String bairro, Integer numero, String rua, String cep) {
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.numero = numero;
        this.rua = rua;
        this.cep = cep;
    }

    // Getters e Setters
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    // Buscar Endereco
    public void BuscarEndereco(String cep){
        
    }
}
