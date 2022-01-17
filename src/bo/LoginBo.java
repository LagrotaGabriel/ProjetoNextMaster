package bo;
import dao.Bd;
import gui.TelaContas;
import model.cliente.Cliente;

public class LoginBo {

    // Atributos
    private final String cpf;
    private final String senha;
    private Boolean ativo;
    private static Cliente clienteAcesso;

    // Construtor
    public LoginBo(String cpf, String senha){
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
    public Boolean Acessar(){
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
            return(true);
        }else{
            return(false);
        }

    }
    public void setClienteAcesso(Cliente cliente){
        clienteAcesso = cliente;
    }
    public static Cliente getClienteAcesso(){
        return(clienteAcesso);
    }

}
