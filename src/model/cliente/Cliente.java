package model.cliente;

// IMPORTAÇÕES
import model.pix.Pix;

public class Cliente {

    // ATRIBUTOS
    private String nome;
    private String cpf;
    private String rg;
    private Endereco endereco;
    private String senha;
    private Pix pix;
    private String email;
    private String telefone;

    // CONSTRUTOR
    public Cliente(String nome, String cpf, String rg, Endereco endereco, String senha, String email, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.pix = null;
    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getSenha() {
        return senha;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
}
