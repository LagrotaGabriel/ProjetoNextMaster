package bo;
import dao.Bd;
import model.cartao.debito.Debito;

public class DebitoBo {

    public static Debito debito;

    // FAZ A VALIDAÇÃO DE INSERÇÃO DE CARTÃO DE DÉBITO
    public static String validaInsercaoDebito(Integer tipoConta){

        // SE A CONTA FOR UMA CONTA CORRENTE
        if(tipoConta == 1) {
            // SE O CLIENTE AINDA NÃO TIVER CARTÕES DE DÉBITO CADASTRADOS
            if(Bd.clienteBuscaContaCorrente.cartoesDebitoCliente.isEmpty()){
                // MÉTODO DE INSERÇÃO DO CARTÃO NOS DBS
                Debito debito = new Debito(Bd.clienteBuscaContaCorrente.getCliente());
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
                Debito debito = new Debito(Bd.clienteBuscaContaPoupanca.getCliente());
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
