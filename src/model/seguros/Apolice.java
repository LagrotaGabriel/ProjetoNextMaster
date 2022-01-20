package model.seguros;

public abstract class Apolice {

    // Atributos
    private Float valorApolice;
    private String nome;
    private String regras;
    private Boolean ativo;

    // Construtor
    public Apolice(Float valorApolice, String nome, Boolean ativo, String regras) {
        this.valorApolice = valorApolice;
        this.nome = nome;
        this.ativo = ativo;
        this.regras = regras;
    }

    // Getters and Setters
    public Float getValorApolice() {
        return valorApolice;
    }
    public void setValorApolice(Float valorApolice) {
        this.valorApolice = valorApolice;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getRegras() {
        return regras;
    }
    public void setRegras(String regras) {
        this.regras = regras;
    }
    public Boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
