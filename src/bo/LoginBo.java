package bo;

// IMPORTAÇÕES
import dao.Bd;
import model.cliente.Cliente;

public class LoginBo {

    // ATRIBUTOS
    private final String cpf;
    private final String senha;
    private Boolean ativo;
    private static Cliente clienteAcesso;

    // CONSTRUTOR DA CLASSE RECEBENDO NOS PARÂMETROS O CPF E SENHA DO USUÁRIO NO ATO DO LOGIN
    public LoginBo(String cpf, String senha){
        this.cpf = cpf;
        this.senha = senha;
        this.ativo = false;
    }

    // SETTERS E GETTERS
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

    // MÉTODO QUE CONCEDE OU NEGA ACESSO AO USUÁRIO
    public String Acessar(){

        // FOR QUE PERCORRE O BANCO DE DADOS DE CLIENTES DO SISTEMA
        for(Cliente i: Bd.clientesMap.values()) {

            // SE O CPF DO for FOR IGUAL AO CPF INSERIDO PELO USUÁRIO
            if(i.getCpf().equals(getCpf())){

                // SE A SENHA DO CPF DO for FOR IGUAL À SENHA INSERIDA PELO USUÁRIO
                if(i.getSenha().equals(getSenha())){
                    // USUÁRIO É SETADO COMO ATIVO
                    setAtivo(true);
                    // MÉTODO DE ACESSO INVOCADO COM CLIENTE DO for COMO PARÂMETRO
                    setClienteAcesso(i);
                    // MÉTODO DE BUSCA DE CONTA POR CPF PASSANDO O CPF DO CLIENTE DO for COMO PARÂMETRO
                    Bd.buscarContaCorrentePorCpf(i.getCpf());
                    Bd.buscarContaPoupancaPorCpf(i.getCpf());
                    // ENCERRA O for SE PARADIGMA CHEGAR ATÉ AQUI
                    break;
                }
            }
        }

        // SE BOOLEANO ativo FOR TRUE
        if(isAtivo()){
            return("[Acesso concedido]");
        }
        // SE BOOLEANO ativo FOR FALSE
        else{
            return("[Acesso negado]");
        }
    }

    // SETA O CLIENTE ACESSANDO O SISTEMA
    public void setClienteAcesso(Cliente cliente){
        clienteAcesso = cliente;
    }
    // RETORNA O CLIENTE ACESSANDO O SISTEMA
    public static Cliente getClienteAcesso(){
        return(clienteAcesso);
    }

}
