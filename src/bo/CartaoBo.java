package bo;

// IMPORTAÇÕES
import dao.Bd;
import model.cartao.Cartao;
import java.util.HashMap;
import java.util.Map;

public class CartaoBo {

    // MÉTODO QUE VALIDA SE A CRIAÇÃO DE CARTÃO DE CRÉDITO PELO USUÁRIO É POSSÍVEL
    public static String validaInserirCartoesCredito(Integer tipoConta){

        // SE O CLIENTE ESTIVER UTILIZANDO UMA CONTA CORRENTE
        if(tipoConta == 1) {
            // VERIFICA AMBOS OS CARTÕES NÃO ESTÃO VAZIOS
            if(!Bd.clienteBuscaContaCorrente.cartoesDebitoCliente.isEmpty() && !Bd.clienteBuscaContaCorrente.cartoesCreditoCliente.isEmpty()) {
                return("Não é possível cadastrar mais cartões!");
            }
            // SE UM OU MAIS CARTÕES ESTIVEREM DISPONÍVEIS PARA SEREM SOLICITADOS
            else {
                return(CreditoBo.validaInsercaoCredito(tipoConta));
            }
        }
        // SE O CLIENTE ESTIVER UTILIZANDO UMA CONTA POUPANÇA
        else{
            // VERIFICA AMBOS OS CARTÕES NÃO ESTÃO VAZIOS
            if (!Bd.clienteBuscaContaPoupanca.cartoesDebitoCliente.isEmpty() && !Bd.clienteBuscaContaPoupanca.cartoesCreditoCliente.isEmpty()) {
                return("Não é possível cadastrar mais cartões!");
            }
            // SE UM OU MAIS CARTÕES ESTIVEREM DISPONÍVEIS PARA SEREM SOLICITADOS
            else {
                return(CreditoBo.validaInsercaoCredito(tipoConta));
            }
        }

    }

    // MÉTODO QUE VALIDA SE A CRIAÇÃO DE CARTÃO DE CRÉDITO PELO USUÁRIO É POSSÍVEL
    public static String validaInserirCartoesDebito(Integer tipoConta){

        // SE O CLIENTE ESTIVER UTILIZANDO UMA CONTA CORRENTE
        if(tipoConta == 1) {
            // VERIFICA AMBOS OS CARTÕES NÃO ESTÃO VAZIOS
            if(!Bd.clienteBuscaContaCorrente.cartoesDebitoCliente.isEmpty() && !Bd.clienteBuscaContaCorrente.cartoesCreditoCliente.isEmpty()) {
                return("Não é possível cadastrar mais cartões!");
            }
            // SE UM OU MAIS CARTÕES ESTIVEREM DISPONÍVEIS PARA SEREM SOLICITADOS
            else {
                return(DebitoBo.validaInsercaoDebito(tipoConta));
            }
        }
        // SE O CLIENTE ESTIVER UTILIZANDO UMA CONTA POUPANÇA
        else{
            // VERIFICA AMBOS OS CARTÕES NÃO ESTÃO VAZIOS
            if (!Bd.clienteBuscaContaPoupanca.cartoesDebitoCliente.isEmpty() && !Bd.clienteBuscaContaPoupanca.cartoesCreditoCliente.isEmpty()) {
                return("Não é possível cadastrar mais cartões!");
            }
            // SE UM OU MAIS CARTÕES ESTIVEREM DISPONÍVEIS PARA SEREM SOLICITADOS
            else {
                return(DebitoBo.validaInsercaoDebito(tipoConta));
            }
        }

    }

    // MÉTODO QUE RETORNA A LISTAGEM COM OS CARTÕES CADASTRADOS NA CONTA DO CLIENTE
    public static Map<Integer, Cartao> listarCartoesDoCliente(Integer tipoConta){

        // DECLARAÇÃO DA VARIÁVEL DA LISTA DOS CARTÕES QUE O CLIENTE POSSUI
        Map<Integer, Cartao> cartoesList = new HashMap<>();

        // SE A CONTA DO CLIENTE FOR CONTA CORRENTE
        if(tipoConta == 1){
            // SE TIVER CARTÕES DE DÉBITO CADASTRADOS
            if(!Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().isEmpty()){
                cartoesList.put(1, Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0));
            }
            // SE TIVER CARTÕES DE CRÉDITO CADASTRADOS
            if(!Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().isEmpty()){
                // SE A LISTA DE CARTÕES DO CLIENTE ESTIVER VAZIA
                if(cartoesList.isEmpty()){
                    cartoesList.put(1, Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0));
                }
                // SE A LISTA DE CARTÕES DO CLIENTE POSSUIR ALGO
                else {
                    // PEGA O TAMANHO DA LISTA + 1 PARA CRIAR O ID DO ITEM ADICIONADO
                    cartoesList.put(cartoesList.size() + 1, Bd.clienteBuscaContaCorrente
                            .getCartoesCreditoCliente()
                            .get(0));
                }
            }
        }
        // SE A CONTA DO CLIENTE FOR CONTA POUPANÇA
        else if(tipoConta == 2){
            // SE TIVER CARTÕES DE DÉBITO CADASTRADOS
            if(!Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().isEmpty()){
                // ADICIONA AO ARRAYLIST CARTÃO DE DÉBITO (SE TIVER)
                cartoesList.put(1, Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0));
            }
            // SE TIVER CARTÕES DE CRÉDITO CADASTRADOS
            if(!Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().isEmpty()){
                // SE A LISTA DE CARTÕES DO CLIENTE ESTIVER VAZIA
                if(cartoesList.isEmpty()){
                    cartoesList.put(1, Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0));
                }
                // SE A LISTA DE CARTÕES DO CLIENTE POSSUIR ALGO
                else {
                    // PEGA O TAMANHO DA LISTA + 1 PARA CRIAR O ID DO ITEM ADICIONADO
                    cartoesList.put(cartoesList.size() + 1, Bd.clienteBuscaContaPoupanca
                            .getCartoesCreditoCliente()
                            .get(0));
                }
            }
        }

        // RETORNA A LISTA DE CARTÕES
        return(cartoesList);

    }
}
