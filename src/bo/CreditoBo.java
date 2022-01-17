package bo;
import dao.Bd;
import model.cartao.credito.Credito;
import model.cartao.debito.Debito;

public class CreditoBo {

    public static Credito credito;

    // FAZ A VALIDAÇÃO DE INSERÇÃO DE CARTÃO DE CRÉDITO
    public static String validaInsercaoCredito(Integer tipoConta){

        // SE A CONTA FOR UMA CONTA CORRENTE
        if(tipoConta == 1) {
            // SE O CLIENTE AINDA NÃO TIVER CARTÕES DE CRÉDITO CADASTRADOS
            if(Bd.clienteBuscaContaCorrente.cartoesCreditoCliente.isEmpty()){
                // MÉTODO DE INSERÇÃO DO CARTÃO NOS DBS
                Credito credito = new Credito(Bd.clienteBuscaContaCorrente.getCliente());
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
                Credito credito = new Credito(Bd.clienteBuscaContaPoupanca.getCliente());
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
