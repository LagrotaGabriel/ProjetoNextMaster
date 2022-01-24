package bo;

import dao.Bd;
import model.cartao.Cartao;
import model.seguros.Apolice;

import java.util.HashMap;
import java.util.Map;

public class ApoliceBo {

    // Retorna os cartões do cliente
    public static Map<Integer, Apolice> listarSegurosDoCliente(Integer tipoConta){

        Map<Integer, Apolice> segurosList = new HashMap<>();

        // SE A CONTA DO CLIENTE FOR CONTA CORRENTE
        if(tipoConta == 1){
            // SE TIVER SEGUROS CADASTRADOS
            if(!Bd.clienteBuscaContaCorrente.getSeguros().isEmpty()){

                for(Map.Entry<Integer, Apolice> entry: Bd.clienteBuscaContaCorrente.getSeguros().entrySet()){

                    // ADICIONA AO ARRAYLIST SEGUROS (SE TIVER)
                    if(segurosList.isEmpty()){
                        segurosList.put(1, Bd.clienteBuscaContaCorrente.getSeguros()
                                .get(entry.getKey()));
                    }else {
                        segurosList.put(segurosList.size() + 1,  Bd.clienteBuscaContaCorrente
                                .getSeguros().get(entry.getKey()));
                    }

                }

            }

        }
        // SE A CONTA DO CLIENTE FOR CONTA POUPANÇA
        else if(tipoConta == 2){

            // SE TIVER SEGUROS CADASTRADOS
            if(!Bd.clienteBuscaContaPoupanca.getSeguros().isEmpty()){

                for(Map.Entry<Integer, Apolice> entry: Bd.clienteBuscaContaPoupanca.getSeguros().entrySet()){

                    // ADICIONA AO ARRAYLIST SEGUROS (SE TIVER)
                    if(segurosList.isEmpty()){
                        segurosList.put(1, Bd.clienteBuscaContaPoupanca.getSeguros()
                                .get(entry.getKey()));
                    }else {
                        segurosList.put(segurosList.size() + 1,  Bd.clienteBuscaContaPoupanca
                                .getSeguros().get(entry.getKey()));
                    }

                }

            }
        }

        return(segurosList);

    }

    // Desativar seguro
    public static String cancelarSeguro(Integer tipoConta, Apolice tpSeguro){

        Boolean cancelado = false;

        // SE A CONTA FOR CORRENTE
        if(tipoConta == 1) {

            for(Map.Entry<Integer, Apolice> entry: Bd.clienteBuscaContaCorrente.getSeguros().entrySet()){

                if(tpSeguro.getNome().equals(entry.getValue().getNome())) {
                    Bd.clienteBuscaContaCorrente.getSeguros().remove(entry.getKey());
                    cancelado = true;
                }
            }
        }

        // SE A CONTA FOR POUPANÇA
        else{

            for(Map.Entry<Integer, Apolice> entry: Bd.clienteBuscaContaPoupanca.getSeguros().entrySet()){

                if(tpSeguro.getNome().equals(entry.getValue().getNome())) {
                    Bd.clienteBuscaContaPoupanca.getSeguros().remove(entry.getKey());
                    cancelado = true;
                }
            }
        }

        if(cancelado){
            return ("Seguro cancelado com sucesso");
        }else{
            return("O seguro não pode ser cancelado");

        }
    }

    // Contratar seguro
    public static String contratarSeguro(Integer tipoConta, Apolice apolice){

        // SE A CONTA FOR CORRENTE
        if(tipoConta == 1) {
            // SE O CLIENTE POSSUIR CARTÃO DE CRÉDITO
            if(!Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().isEmpty()) {

                // VALIDA SE O CLIENTE TEM LIMITE NA FATURA DO CARTÃO PARA CONTRATAR O SEGURO
                if(Bd.clienteBuscaContaCorrente.cartoesCreditoCliente
                        .get(0).getLimite().getLimiteDisponivel() > apolice.getValorApolice()) {

                    // SE O CLIENTE JÁ POSSUIR ALGUM TIPO DE SEGURO CONTRATADO
                    if (!Bd.clienteBuscaContaCorrente.getSeguros().isEmpty()) {

                        // SE O SEGURO QUE O CLIENTE INSERIR TIVER O MESMO NOME QUE UM QUE JÁ EXISTA
                        if(Bd.clienteBuscaContaCorrente.getSeguros().containsValue(apolice)){
                            return ("    Você já possui este seguro");
                        }

                        // SE O SEGURO QUE O CLIENTE INSERIR NÃO TIVER O MESMO NOME DE ALGUM QUE JÁ EXISTA
                        else {

                            // INSERE O SEGURO NO BD DA CONTA E NA FATURA DO CARTÃO
                            Bd.clienteBuscaContaCorrente.addSeguros(Bd.clienteBuscaContaCorrente
                                    .getSeguros().size() + 1, apolice);
                            CreditoBo.processaCompra
                                    (tipoConta, apolice.getNome(), apolice.getValorApolice()/12);
                            return("    Você contratou o " + apolice.getNome() + " com sucesso!");

                        }

                    }

                    // SE O CLIENTE NÃO POSSUIR NENHUM TIPO DE SEGURO CONTRATADO
                    else {

                        // INSERE O SEGURO NO BD DA CONTA E NA FATURA DO CARTÃO
                        Bd.clienteBuscaContaCorrente.addSeguros(1, apolice);
                        CreditoBo.processaCompra
                                (tipoConta, apolice.getNome(), apolice.getValorApolice()/12);
                        return("    Você contratou o " + apolice.getNome() + " com sucesso!");

                    }

                }

                // SE O CLIENTE NÃO POSSUIR LIMITE NO CARTÃO DE CRÉDITO PARA CONTRATAR O SEGURO
                return("    Você não possui limite no cartão para contratar o seguro");
            }
            // SE O CLIENTE NÃO POSSUIR CARTÃO DE CRÉDITO
            else {
                return("    Só é possível contratar um seguro utilizando um cartão de crédito");
            }
        }
        // SE A CONTA FOR POUPANÇA
        else{
            // SE O CLIENTE POSSUIR CARTÃO DE CRÉDITO
            if(!Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().isEmpty()) {

                // VALIDA SE O CLIENTE TEM LIMITE NA FATURA DO CARTÃO PARA CONTRATAR O SEGURO
                if(Bd.clienteBuscaContaPoupanca.cartoesCreditoCliente
                        .get(0).getLimite().getLimiteDisponivel() > apolice.getValorApolice()) {

                    // SE O CLIENTE JÁ POSSUIR ALGUM TIPO DE SEGURO CONTRATADO
                    if (!Bd.clienteBuscaContaPoupanca.getSeguros().isEmpty()) {

                        // PERCORRE LISTA DE SEGUROS JÁ CONTRATADOS PELO CLIENTE
                        for (Map.Entry<Integer, Apolice> entry : Bd.clienteBuscaContaPoupanca.getSeguros().entrySet()) {

                            // SE O SEGURO QUE VOCÊ FOR INSERIR TIVER O MESMO NOME QUE UM QUE JÁ EXISTA
                            if (apolice.getNome().equals(entry.getValue().getNome())) {

                                return ("    Você já possui este seguro");

                            }

                            // SE O SEGURO QUE VOCê FOR INSERIR NÃO TIVER O MESMO NOME DE UM QUE JÁ EXISTA
                            else {

                                // INSERE O SEGURO NO BD DA CONTA E NA FATURA DO CARTÃO
                                Bd.clienteBuscaContaPoupanca.addSeguros(Bd.clienteBuscaContaPoupanca
                                        .getSeguros().size() + 1, apolice);
                                CreditoBo.processaCompra
                                        (tipoConta, apolice.getNome(), apolice.getValorApolice()/12);
                                return("    Você contratou o " + apolice.getNome() + " com sucesso!");

                            }
                        }

                    }

                    // SE O CLIENTE NÃO POSSUIR NENHUM TIPO DE SEGURO CONTRATADO
                    else {

                        // INSERE O SEGURO NO BD DA CONTA E NA FATURA DO CARTÃO
                        Bd.clienteBuscaContaPoupanca.addSeguros(1, apolice);
                        CreditoBo.processaCompra
                                (tipoConta, apolice.getNome(), apolice.getValorApolice()/12);
                        return("    Você contratou o " + apolice.getNome() + " com sucesso!");

                    }

                }

                // SE O CLIENTE NÃO POSSUIR LIMITE NO CARTÃO DE CRÉDITO PARA CONTRATAR O SEGURO
                return("    Você não possui limite no cartão para contratar o seguro");
            }
            // SE O CLIENTE NÃO POSSUIR CARTÃO DE CRÉDITO
            else {
                return("    Só é possível contratar um seguro utilizando um cartão\nde crédito");
            }
        }
    }

}
