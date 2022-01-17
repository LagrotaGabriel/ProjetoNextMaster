package bo;
import dao.Bd;
import model.cartao.credito.Credito;
import model.conta.ContaTipo;

public class CreditoBo {

    public static Credito credito;

    // FAZ A VALIDAÇÃO DE INSERÇÃO DE CARTÃO DE CRÉDITO
    public static String validaInsercaoCredito(Integer tipoConta){


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
            // SE O CLIENTE AINDA NÃO TIVER CARTÕES DE CRÉDITO CADASTRADOS
            if(Bd.clienteBuscaContaCorrente.cartoesCreditoCliente.isEmpty()){
                // MÉTODO DE INSERÇÃO DO CARTÃO NOS DBS
                Credito credito = new Credito(Bd.clienteBuscaContaCorrente.getCliente(), limite);
                Bd.insereCartaoCredito(credito, tipoConta);
                return("Cartão de Crédito cadastrado com sucesso!");
            }
            // SE O CLIENTE JÁ POSSUI CARTÃO DE CRÉDITO CADASTRADO
            else {
                return ("Você já possui um cartão de Crédito cadastrado");
            }
        }
        // SE A CONTA FOR UMA CONTA POUPANÇA
        else{
            // SE O CLIENTE AINDA NÃO TIVER CARTÕES DE CRÉDITO CADASTRADOS
            if(Bd.clienteBuscaContaPoupanca.cartoesCreditoCliente.isEmpty()){
                // MÉTODO DE INSERÇÃO DO CARTÃO NOS DBS
                Credito credito = new Credito(Bd.clienteBuscaContaPoupanca.getCliente(), limite);
                Bd.insereCartaoCredito(credito, tipoConta);
                return("Cartão de Crédito cadastrado com sucesso!");
            }
            // SE O CLIENTE JÁ POSSUI CARTÃO DE CRÉDITO CADASTRADO
            else {
                return ("Você já possui um cartão de crédito cadastrado");
            }
        }
    }
}
