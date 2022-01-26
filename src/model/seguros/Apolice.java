package model.seguros;

public abstract class Apolice {

    // ATRIBUTOS
    private final Float valorApolice;
    private final String nome;
    private final String regras;
    private Boolean ativo;

    // CONSTRUTOR
    public Apolice(Float valorApolice, String nome, Boolean ativo, String regras) {
        this.valorApolice = valorApolice;
        this.nome = nome;
        this.ativo = ativo;
        this.regras = regras;
    }

    // GETTERS E SETTERS
    public Float getValorApolice() {
        return valorApolice;
    }
    public String getNome() {
        return nome;
    }
    public String getRegras() {
        return regras;
    }
}
