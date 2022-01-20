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
}
