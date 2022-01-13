package bo;

import dao.Bd;
import model.pix.Pix;

import java.util.HashMap;
import java.util.Map;

public class PixBo {

    // Transferir
    public static String transferir(String transfChave, Float transfValor, Integer tipoConta){

        Bd.buscarChavePix(transfChave);

        // SE A CONTA USADA AGORA É CONTA CORRENTE
        if(tipoConta == 1) {

            // VERIFICA SE SALDO É SUFICIENTE PARA TRANSAÇÃO
            if(Bd.clienteBuscaContaCorrente.getSaldo() > transfValor) {
                // RETIRA O SALDO DA CONTA DO CLIENTE
                Bd.clienteBuscaContaCorrente.setSaldo(Bd.clienteBuscaContaCorrente.getSaldo() - transfValor);

                // SE A CONTA DE TRANSFERÊNCIA FOR CORRENTE
                if (Bd.contaCorrenteTransf != null) {
                    Bd.contaCorrenteTransf.setSaldo(Bd.contaCorrenteTransf.getSaldo() + transfValor);
                    return("    Transferência realizada. Seu saldo é: R$ " + Bd.clienteBuscaContaCorrente.getSaldo());
                }

                // SE A CONTA DE TRANSFERÊNCIA FOR POUPANÇA
                if (Bd.contaPoupancaTransf != null) {
                    Bd.contaPoupancaTransf.setSaldo(Bd.contaPoupancaTransf.getSaldo() + transfValor);
                    return("    Transferência realizada. Seu saldo é: R$ " + Bd.clienteBuscaContaCorrente.getSaldo());
                }
            }
            // SEM SALDO SUFICIENTE PARA TRANSFERÊNCIA
            else{
                return("    Não há saldo suficiente. Seu saldo é: R$ " + Bd.clienteBuscaContaCorrente.getSaldo());
            }
        }

        // SE A CONTA USADA AGORA É POUPANÇA
        else if(tipoConta == 2){

            // VERIFICA SE SALDO É SUFICIENTE PARA TRANSAÇÃO
            if(Bd.clienteBuscaContaPoupanca.getSaldo() > transfValor) {

                // RETIRA O SALDO DA CONTA DO CLIENTE
                Bd.clienteBuscaContaPoupanca.setSaldo(Bd.clienteBuscaContaPoupanca.getSaldo() - transfValor);

                // SE A CONTA DE TRANSFERÊNCIA FOR CORRENTE
                if (Bd.contaCorrenteTransf != null) {
                    Bd.contaCorrenteTransf.setSaldo(Bd.contaCorrenteTransf.getSaldo() + transfValor);
                    return("    Transferência realizada. Seu saldo é: R$ " + Bd.clienteBuscaContaPoupanca.getSaldo());
                }

                // SE A CONTA DE TRANSFERÊNCIA FOR POUPANÇA
                if (Bd.contaPoupancaTransf != null) {
                    Bd.contaPoupancaTransf.setSaldo(Bd.contaPoupancaTransf.getSaldo() + transfValor);
                    return("    Transferência realizada. Seu saldo é: R$ " + Bd.clienteBuscaContaPoupanca.getSaldo());
                }

            }
            // SEM SALDO SUFICIENTE PARA TRANSFERÊNCIA
            else{
                return("    Não há saldo suficiente. Seu saldo é: R$ " + Bd.clienteBuscaContaPoupanca.getSaldo());
            }
        }

        return("Retornando ao menu PIX");
    }

    // Buscar chaves pix do cliente
    public static Map<Integer, String> consultarChavesPixCliente(Integer tipoConta, Boolean printa){

        int cont = 1;
        Boolean temChave = true;
        Map<Integer, String> chavesOpc = new HashMap<>();

        //String[] chavesOpc = new String[3];

        // SE A CONTA FOR CORRENTE
        if(tipoConta == 1){

            // SE O CLIENTE SÓ TIVER CONTA CORRENTE
            if(Bd.clienteBuscaContaPoupanca == null) {
                // SE AS CHAVES PIX DA CONTA CORRENTE RETORNAREM ALGO
                if (!Bd.clienteBuscaContaCorrente.getChavesPix().isEmpty()) {
                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS CORRENTES
                    for (Pix i : Bd.clienteBuscaContaCorrente.getChavesPix()) {
                        if(printa) {
                            System.out.println("    [" + cont + "] " + " Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave() + " - CC");
                        }
                        chavesOpc.put(cont, i.getConteudoChave());
                        cont++;
                    }
                }
                // SE AS CHAVES PIX DA CONTA CORRENTE NÃO RETORNAREM NADA
                else {
                    if(printa) {
                        System.out.println("    Não há chaves PIX cadastradas nesta conta");
                    }
                }

            }
            // SE O CLIENTE TIVER CONTA CORRENTE E CONTA POUPANCA
            else{
                // SE AS CHAVES PIX DA CONTA CORRENTE E POUPANÇA RETORNAREM ALGO
                if (!Bd.clienteBuscaContaCorrente.getChavesPix().isEmpty() || !Bd.clienteBuscaContaPoupanca.getChavesPix().isEmpty()) {

                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS CORRENTES
                    try {
                        for (Pix i : Bd.clienteBuscaContaCorrente.getChavesPix()) {
                            if(printa) {
                                System.out.println("    [" + cont + "] " + " Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave() + " - CC");
                            }
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                    try {
                        for (Pix i : Bd.clienteBuscaContaPoupanca.getChavesPix()) {
                            if(printa) {
                                System.out.println("    [" + cont + "] " + " Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave() + " - CP");
                            }
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                }
                // SE AS CHAVES PIX DA CONTA CORRENTE E POUPANÇA NÃO RETORNAREM NADA
                else {
                    temChave = false;
                    if(printa) {
                        System.out.println("    Não há chaves PIX cadastradas nesta conta");
                    }
                }
            }


        }

        // SE A CONTA FOR POUPANÇA
        else if(tipoConta == 2){

            // SE O CLIENTE SÓ TIVER CONTA POUPANÇA
            if(Bd.clienteBuscaContaCorrente == null) {

                // SE AS CHAVES PIX DA CONTA POUPANCA RETORNAREM ALGO
                if (!Bd.clienteBuscaContaPoupanca.getChavesPix().isEmpty()) {
                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS POUPANCAS
                    for (Pix i : Bd.clienteBuscaContaPoupanca.getChavesPix()) {
                        if(printa) {
                            System.out.println("    [" + cont + "] " + "Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave()  + " - CP");
                        }
                        chavesOpc.put(cont, i.getConteudoChave());
                        cont++;
                    }
                }
                // SE AS CHAVES PIX DA CONTA POUPANÇA NÃO RETORNAREM NADA
                else {
                    if(printa) {
                        System.out.println("    Não há chaves PIX cadastradas nesta conta");
                    }
                }

            }

            // SE O CLIENTE TIVER CONTA CORRENTE E CONTA POUPANCA
            else{

                // SE AS CHAVES PIX DA CONTA CORRENTE E POUPANÇA RETORNAREM ALGO
                if (!Bd.clienteBuscaContaCorrente.getChavesPix().isEmpty() || !Bd.clienteBuscaContaPoupanca.getChavesPix().isEmpty()) {

                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS CORRENTES
                    try {
                        for (Pix i : Bd.clienteBuscaContaCorrente.getChavesPix()) {
                            if(printa) {
                                System.out.println("    [" + cont + "] " + "Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave() + " - CC");
                            }
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS POUPANÇAS
                    try {
                        for (Pix i : Bd.clienteBuscaContaPoupanca.getChavesPix()) {
                            if(printa) {
                                System.out.println("    [" + cont + "] " + "Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave() + " - CP");
                            }
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                }
                // SE AS CHAVES PIX DA CONTA CORRENTE E POUPANÇA NÃO RETORNAREM NADA
                else {
                    temChave = false;
                    if(printa) {
                        System.out.println("    Não há chaves PIX cadastradas nesta conta");
                    }
                }

            }
        }

        if(!temChave){
            return(chavesOpc = null);
        }

        else{
            return(chavesOpc);
        }

    }

}
