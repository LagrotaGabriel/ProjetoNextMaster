package controller;

public class Login {

    // Atributos
    private String cpf;
    private String senha;
    private Boolean ativo;

    // Construtor
    public Login(String cpf, String senha){
        this.cpf = cpf;
        this.senha = senha;
    }

    // Setters e Getters
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Acessar
    public void Acessar(){

    }

}
