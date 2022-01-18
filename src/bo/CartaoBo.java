package bo;
import dao.Bd;
import model.cartao.Cartao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartaoBo {

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

    // Retorna os cartões do cliente
    public static Map<Integer, Cartao> listarCartoesDoCliente(Integer tipoConta){

        Map<Integer, Cartao> cartoesList = new HashMap<>();

        // SE A CONTA DO CLIENTE FOR CONTA CORRENTE
        if(tipoConta == 1){
            // SE TIVER CARTÕES DE DÉBITO CADASTRADOS
            if(!Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().isEmpty()){
                // ADICIONA AO ARRAYLIST CARTÃO DE DÉBITO (SE TIVER)
                if(cartoesList.isEmpty()){
                    cartoesList.put(1, Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0));
                }else {
                    cartoesList.put(cartoesList.size() + 1, Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0));
                }
            }
            // SE TIVER CARTÕES DE CRÉDITO CADASTRADOS
            if(!Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().isEmpty()){
                // ADICIONA AO ARRAYLIST CARTÃO DE CRÉDITO (SE TIVER)
                if(cartoesList.isEmpty()){
                    cartoesList.put(1, Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0));
                }else {
                    cartoesList.put(cartoesList.size() + 1, Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0));
                }
            }
        }
        // SE A CONTA DO CLIENTE FOR CONTA POUPANÇA
        else if(tipoConta == 2){
            // SE TIVER CARTÕES DE DÉBITO CADASTRADOS
            if(!Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().isEmpty()){
                // ADICIONA AO ARRAYLIST CARTÃO DE DÉBITO (SE TIVER)
                if(cartoesList.isEmpty()){
                    cartoesList.put(1, Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0));
                }else {
                    cartoesList.put(cartoesList.size() + 1, Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0));
                }
            }
            // SE TIVER CARTÕES DE CRÉDITO CADASTRADOS
            if(!Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().isEmpty()){
                // ADICIONA AO ARRAYLIST CARTÃO DE CRÉDITO (SE TIVER)
                if(cartoesList.isEmpty()){
                    cartoesList.put(1, Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0));
                }else {
                    cartoesList.put(cartoesList.size() + 1, Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0));
                }
            }
        }

        return(cartoesList);

    }
}
