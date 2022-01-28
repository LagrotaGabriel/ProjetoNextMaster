package bo;

// IMPORTAÇÕES
import dao.Bd;
import model.seguros.Apolice;
import java.util.HashMap;
import java.util.Map;

public class ApoliceBo {

    // MÉTODO QUE RETORNA TODOS OS SEGUROS DO CLIENTE
    public static Map<Integer, Apolice> listarSegurosDoCliente(Integer tipoConta){

        // DECLARAÇÃO DE VARIÁVEL QUE CONTÉM TODOS OS SEGUROS DO CLIENTE
        Map<Integer, Apolice> segurosList = new HashMap<>();

        // SE A CONTA DO CLIENTE FOR CONTA CORRENTE
        if(tipoConta == 1){
            // SE TIVER SEGUROS CADASTRADOS
            if(!Bd.clienteBuscaContaCorrente.getSeguros().isEmpty()){
                // PERCORRE O FOR PROCURANDO PELOS SEGUROS DO CLIENTE
                for(Map.Entry<Integer, Apolice> entry: Bd.clienteBuscaContaCorrente.getSeguros().entrySet()){
                    // SE A LISTA DE SEGUROS DO CLIENTE ESTIVER VAZIA
                    if(segurosList.isEmpty()){
                        // ADICIONA NA LISTA DE SEGUROS DO CLIENTE O SEGURO COM O ID 1
                        segurosList.put(1, Bd.clienteBuscaContaCorrente.getSeguros()
                                .get(entry.getKey()));
                    }
                    // SE A LISTA DE SEGUROS DO CLIENTE TIVER ALGUM VALOR
                    else {
                        // ADICIONA NA LISTA DE SEGUROS DO CLIENTE O SEGURO COM O ID DO ÚLTIMO VALOR DA LISTA + 1
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
                // PERCORRE O FOR PROCURANDO PELOS SEGUROS DO CLIENTE
                for(Map.Entry<Integer, Apolice> entry: Bd.clienteBuscaContaPoupanca.getSeguros().entrySet()){
                    // SE A LISTA DE SEGUROS DO CLIENTE ESTIVER VAZIA
                    if(segurosList.isEmpty()){
                        // ADICIONA NA LISTA DE SEGUROS DO CLIENTE O SEGURO COM O ID 1
                        segurosList.put(1, Bd.clienteBuscaContaPoupanca.getSeguros()
                                .get(entry.getKey()));
                    }
                    // SE A LISTA DE SEGUROS DO CLIENTE TIVER ALGUM VALOR
                    else {
                        // ADICIONA NA LISTA DE SEGUROS DO CLIENTE O SEGURO COM O ID DO ÚLTIMO VALOR DA LISTA + 1
                        segurosList.put(segurosList.size() + 1,  Bd.clienteBuscaContaPoupanca
                                .getSeguros().get(entry.getKey()));
                    }
                }
            }
        }

        // RETORNA A LISTA COM OS SEGUROS DO CLIENTE
        return(segurosList);
    }

    // DESATIVAR SEGURO
    public static String cancelarSeguro(Integer tipoConta, Apolice tpSeguro){

        // DECLARAÇÃO DE VARIÁVEIS
        boolean cancelado = false;

        // SE A CONTA FOR CORRENTE
        if(tipoConta == 1) {
            // PERCORRE A LISTA DE SEGUROS DO CLIENTE NO BANCO DE DADOS
            for(Map.Entry<Integer, Apolice> entry: Bd.clienteBuscaContaCorrente.getSeguros().entrySet()){
                // SE NO BD DO USUÁRIO EXISTIR O SEGURO QUE O USUÁRIO QUER DELETAR
                if(tpSeguro.getNome().equals(entry.getValue().getNome())) {
                    Bd.clienteBuscaContaCorrente.getSeguros().remove(entry.getKey());
                    cancelado = true;
                }
            }
        }
        // SE A CONTA FOR POUPANÇA
        else{
            // PERCORRE A LISTA DE SEGUROS DO CLIENTE NO BANCO DE DADOS
            for(Map.Entry<Integer, Apolice> entry: Bd.clienteBuscaContaPoupanca.getSeguros().entrySet()){
                // SE NO BD DO USUÁRIO EXISTIR O SEGURO QUE O USUÁRIO QUER DELETAR
                if(tpSeguro.getNome().equals(entry.getValue().getNome())) {
                    Bd.clienteBuscaContaPoupanca.getSeguros().remove(entry.getKey());
                    cancelado = true;
                }
            }
        }

        // SE A VARIÁVEL CANCELADO FOR TRUE
        if(cancelado){
            return ("Seguro cancelado com sucesso");
        }
        // SE A VARIÁVEL CANCELADO FOR FALSE
        else{
            return("O seguro não pode ser cancelado");

        }
    }

    // CONTROLAR SEGURO
    public static String contratarSeguro(Integer tipoConta, Apolice apolice){

        // SE A CONTA FOR CORRENTE
        if(tipoConta == 1) {
            // SE O CLIENTE POSSUIR CARTÃO DE CRÉDITO
            if(!Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().isEmpty()) {
                // SE O CARTÃO DO CLIENTE ESTÁ ATIVO
                if(Bd.clienteBuscaContaCorrente.cartoesCreditoCliente.get(0).isAtivo()) {
                    // VALIDA SE O CLIENTE TEM LIMITE NA FATURA DO CARTÃO PARA CONTRATAR O SEGURO
                    if (Bd.clienteBuscaContaCorrente.cartoesCreditoCliente
                            .get(0).getLimite().getLimiteDisponivel() > apolice.getValorApolice()) {
                        // SE O CLIENTE JÁ POSSUIR ALGUM TIPO DE SEGURO CONTRATADO
                        if (!Bd.clienteBuscaContaCorrente.getSeguros().isEmpty()) {
                            // SE O SEGURO QUE O CLIENTE INSERIR TIVER O MESMO NOME QUE UM QUE JÁ EXISTA
                            if (Bd.clienteBuscaContaCorrente.getSeguros().containsValue(apolice)) {
                                return ("    Você já possui este seguro");
                            }
                            // SE O SEGURO QUE O CLIENTE INSERIR NÃO TIVER O MESMO NOME DE ALGUM QUE JÁ EXISTA
                            else {
                                // INSERE O SEGURO NO BD DA CONTA E NA FATURA DO CARTÃO
                                Bd.clienteBuscaContaCorrente.addSeguros(Bd.clienteBuscaContaCorrente
                                        .getSeguros().size() + 1, apolice);
                                CreditoBo.processaCompra
                                        (tipoConta, apolice.getNome(), apolice.getValorApolice() / 12);
                                return ("Você contratou o " + apolice.getNome() + " com sucesso!");
                            }
                        }

                        // SE O CLIENTE NÃO POSSUIR NENHUM TIPO DE SEGURO CONTRATADO
                        else {
                            // INSERE O SEGURO NO BD DA CONTA E NA FATURA DO CARTÃO
                            Bd.clienteBuscaContaCorrente.addSeguros(1, apolice);
                            CreditoBo.processaCompra
                                    (tipoConta, apolice.getNome(), apolice.getValorApolice() / 12);
                            return ("Você contratou o " + apolice.getNome() + " com sucesso!");
                        }
                    }
                    // SE O CLIENTE NÃO POSSUIR LIMITE NO CARTÃO DE CRÉDITO PARA CONTRATAR O SEGURO
                    else {
                        return ("Você não possui limite no cartão para contratar o seguro");
                    }
                }
                // SE O CARTÃO DO USUÁRIO ESTIVER DESATIVADO
                else {

                    return ("Não é possível contratar um seguro com o cartão desativado");
                }
            }
            // SE O CLIENTE NÃO POSSUIR CARTÃO DE CRÉDITO
            else {
                return("Só é possível contratar um seguro utilizando um cartão\nde crédito");
            }
        }
        // SE A CONTA FOR POUPANÇA
        else{
            // SE O CLIENTE POSSUIR CARTÃO DE CRÉDITO
            if(!Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().isEmpty()) {
                // SE O CARTÃO DO CLIENTE ESTÁ ATIVO
                if(Bd.clienteBuscaContaPoupanca.cartoesCreditoCliente.get(0).isAtivo()) {
                    // VALIDA SE O CLIENTE TEM LIMITE NA FATURA DO CARTÃO PARA CONTRATAR O SEGURO
                    if (Bd.clienteBuscaContaPoupanca.cartoesCreditoCliente
                            .get(0).getLimite().getLimiteDisponivel() > apolice.getValorApolice()) {
                        // SE O CLIENTE JÁ POSSUIR ALGUM TIPO DE SEGURO CONTRATADO
                        if (!Bd.clienteBuscaContaPoupanca.getSeguros().isEmpty()) {
                            // SE O SEGURO QUE O CLIENTE INSERIR TIVER O MESMO NOME QUE UM QUE JÁ EXISTA
                            if (Bd.clienteBuscaContaPoupanca.getSeguros().containsValue(apolice)) {
                                return ("    Você já possui este seguro");
                            }
                            // SE O SEGURO QUE O CLIENTE INSERIR NÃO TIVER O MESMO NOME DE ALGUM QUE JÁ EXISTA
                            else {
                                // INSERE O SEGURO NO BD DA CONTA E NA FATURA DO CARTÃO
                                Bd.clienteBuscaContaPoupanca.addSeguros(Bd.clienteBuscaContaPoupanca
                                        .getSeguros().size() + 1, apolice);
                                CreditoBo.processaCompra
                                        (tipoConta, apolice.getNome(), apolice.getValorApolice() / 12);
                                return ("Você contratou o " + apolice.getNome() + " com sucesso!");
                            }
                        }

                        // SE O CLIENTE NÃO POSSUIR NENHUM TIPO DE SEGURO CONTRATADO
                        else {
                            // INSERE O SEGURO NO BD DA CONTA E NA FATURA DO CARTÃO
                            Bd.clienteBuscaContaPoupanca.addSeguros(1, apolice);
                            CreditoBo.processaCompra
                                    (tipoConta, apolice.getNome(), apolice.getValorApolice() / 12);
                            return ("Você contratou o " + apolice.getNome() + " com sucesso!");
                        }
                    }
                    // SE O CLIENTE NÃO POSSUIR LIMITE NO CARTÃO DE CRÉDITO PARA CONTRATAR O SEGURO
                    else {
                        return ("Você não possui limite no cartão para contratar o seguro");
                    }
                }
                // SE O CARTÃO DO USUÁRIO ESTIVER DESATIVADO
                else {

                    return ("Não é possível contratar um seguro com o cartão desativado");
                }
            }
            // SE O CLIENTE NÃO POSSUIR CARTÃO DE CRÉDITO
            else {
                return("Só é possível contratar um seguro utilizando um cartão\nde crédito");
            }
        }
    }

}
