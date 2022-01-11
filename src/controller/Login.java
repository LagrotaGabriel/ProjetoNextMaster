package controller;
import dao.Bd;
import model.cliente.Cliente;

public class Login {

    // Atributos
    private final String cpf;
    private final String senha;
    private Boolean ativo;
    private static Cliente clienteAcesso;

    // Construtor
    public Login(String cpf, String senha){
        this.cpf = cpf;
        this.senha = senha;
        this.ativo = false;
    }

    // Setters e Getters
    public String getCpf() {
        return cpf;
    }
    public String getSenha() {
        return senha;
    }
    public Boolean isAtivo(){
        return(this.ativo);
    }
    public void setAtivo(Boolean ativo){
        this.ativo = ativo;
    }

    // Acessar
    public String Acessar(){

        for(Cliente i: Bd.clientesMap.values()) {

            if(i.getCpf().equals(getCpf())){

                if(i.getSenha().equals(getSenha())){

                    setAtivo(true);
                    setClienteAcesso(i);
                    Bd.buscarContaCorrentePorCpf(i.getCpf());
                    Bd.buscarContaPoupancaPorCpf(i.getCpf());
                    break;

                }

            }

        }


        if(isAtivo()){
            return("  Acesso concedido");
        }else{
            return("  Acesso negado");
        }

    }
    public void setClienteAcesso(Cliente cliente){
        clienteAcesso = cliente;
    }
    public static Cliente getClienteAcesso(){
        return(clienteAcesso);
    }

}
