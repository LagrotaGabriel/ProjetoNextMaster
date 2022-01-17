package bo;
import dao.Bd;
import model.cartao.debito.Debito;
import model.conta.ContaTipo;

public class DebitoBo {

    public static Debito debito;

    // FAZ A VALIDAÇÃO DE INSERÇÃO DE CARTÃO DE DÉBITO
    public static String validaInsercaoDebito(Integer tipoConta){

        // VERIFICANDO TIPO DA CONTA PARA DEFINIR O LIMITE
        float limite = 0;
        if(Bd.clienteBuscaContaCorrente.getContaTipo().equals(ContaTipo.COMUM)){
            limite = 1000.00f;
        }else if(Bd.clienteBuscaContaCorrente.getContaTipo().equals(ContaTipo.PREMIUM)){
            limite = 5000.00f;
        }else if(Bd.clienteBuscaContaCorrente.getContaTipo().equals(ContaTipo.SUPER)){
            limite = 10000.00f;
        }

        // SE A CONTA FOR UMA CONTA CORRENTE
        if(tipoConta == 1) {
            // SE O CLIENTE AINDA NÃO TIVER CARTÕES DE DÉBITO CADASTRADOS
            if(Bd.clienteBuscaContaCorrente.cartoesDebitoCliente.isEmpty()){
                // MÉTODO DE INSERÇÃO DO CARTÃO NOS DBS
                Debito debito = new Debito(Bd.clienteBuscaContaCorrente.getCliente(), limite);
                Bd.insereCartaoDebito(debito, tipoConta);
                return("Cartão de débito cadastrado com sucesso!");
            }
            // SE O CLIENTE JÁ POSSUI CARTÃO DE DÉBITO CADASTRADO
            else {
                return ("Você já possui um cartão de débito cadastrado");
            }
        }
        // SE A CONTA FOR UMA CONTA POUPANÇA
        else{
            // SE O CLIENTE AINDA NÃO TIVER CARTÕES DE DÉBITO CADASTRADOS
            if(Bd.clienteBuscaContaPoupanca.cartoesDebitoCliente.isEmpty()){
                // MÉTODO DE INSERÇÃO DO CARTÃO NOS DBS
                Debito debito = new Debito(Bd.clienteBuscaContaPoupanca.getCliente(), limite);
                Bd.insereCartaoDebito(debito, tipoConta);
                return("Cartão de débito cadastrado com sucesso!");
            }
            // SE O CLIENTE JÁ POSSUI CARTÃO DE DÉBITO CADASTRADO
            else {
                return ("Você já possui um cartão de débito cadastrado");
            }
        }

    }
}
