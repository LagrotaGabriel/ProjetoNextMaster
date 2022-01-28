package bo;

// IMPORTAÇÕES
import dao.Bd;
import model.pix.Pix;
import util.Layout;

import java.util.HashMap;
import java.util.Map;

public class PixBo {

    /* MÉTODO DE TRANSFERÊNCIA VIA PIX
    *  PARAMS:
    *  transfChave -> CHAVE DE TRANSFERÊNCIA PARA QUAL O USUÁRIO QUER ENVIAR DINHEIRO
    *  transfValor -> VALOR QUE O USUÁRIO QUER TRANSFERIR
    *  tipoConta   -> QUAL O TIPO DE CONTA QUE O USUÁRIO ESTÁ USANDO ATUALMENTE */
    public static String transferir(String transfChave, Float transfValor, Integer tipoConta){

        Bd.buscarChavePix(transfChave);

        // SE A CONTA USADA AGORA É CONTA CORRENTE
        if(tipoConta == 1) {

            // VERIFICA SE SALDO É SUFICIENTE PARA TRANSAÇÃO
            if(Bd.clienteBuscaContaCorrente.getSaldo() > transfValor) {


                // SE A CONTA DE TRANSFERÊNCIA FOR CORRENTE
                if (Bd.contaCorrenteTransf != null) {

                    // RETIRANDO SALDO DA CONTA DO CLIENTE
                    Bd.clienteBuscaContaCorrente.setSaldo(Bd.clienteBuscaContaCorrente.getSaldo() - (transfValor));
                    // ADICIONANDO SALDO NA CONTA A RECEBER
                    Bd.contaCorrenteTransf.setSaldo(Bd.contaCorrenteTransf.getSaldo() + transfValor);
                    // RETORNANDO SALDO ATUAL PÓS-TRANSFERÊNCIA
                    return("    Transferência realizada. Seu saldo é: "
                            + Layout.convertToReais(Bd.clienteBuscaContaCorrente.getSaldo()));
                }

                // SE A CONTA DE TRANSFERÊNCIA FOR POUPANÇA
                if (Bd.contaPoupancaTransf != null) {

                    // VERIFICA SE O CLIENTE ATUAL POSSUI SALDO PARA DEBITAR A TAXA
                    if(Bd.clienteBuscaContaCorrente.getSaldo() > transfValor + 5.60f) {

                        // RETIRANDO SALDO DA CONTA DO CLIENTE
                        Bd.clienteBuscaContaCorrente.setSaldo(Bd.clienteBuscaContaCorrente.getSaldo() - (transfValor + 5.60f));
                        // ADICIONANDO SALDO NA CONTA A RECEBER
                        Bd.contaPoupancaTransf.setSaldo(Bd.contaPoupancaTransf.getSaldo() + transfValor);
                        // RETORNANDO SALDO ATUAL PÓS-TRANSFERÊNCIA
                        return ("    Transferência realizada. Seu saldo é: "
                                + Layout.convertToReais(Bd.clienteBuscaContaCorrente.getSaldo()));

                    }
                    // SE O CLIENTE NÃO POSSUIR SALDO COM O INCREMENTO DA TAXA DE TRANSFERÊNCIA
                    else{
                        return("    Não há saldo suficiente. Seu saldo é: "
                                + Layout.convertToReais(Bd.clienteBuscaContaCorrente.getSaldo()));
                    }

                }
            }
            // SEM SALDO SUFICIENTE PARA TRANSFERÊNCIA
            else{
                return("    Não há saldo suficiente. Seu saldo é: "
                        + Layout.convertToReais(Bd.clienteBuscaContaCorrente.getSaldo()));
            }
        }

        // SE A CONTA USADA AGORA É POUPANÇA
        else if(tipoConta == 2){

            // VERIFICA SE SALDO É SUFICIENTE PARA TRANSAÇÃO
            if(Bd.clienteBuscaContaPoupanca.getSaldo() > transfValor) {



                // SE A CONTA DE TRANSFERÊNCIA FOR CORRENTE
                if (Bd.contaCorrenteTransf != null) {

                    // VERIFICA SE APÓS INCREMENTO DE TAXA CLIENTE AINDA POSSUI SALDO SUFICIENTE
                    if(Bd.clienteBuscaContaPoupanca.getSaldo() > (transfValor + 5.60f)) {
                        // RETIRA O SALDO DA CONTA DO CLIENTE
                        Bd.clienteBuscaContaPoupanca.setSaldo(Bd.clienteBuscaContaPoupanca.getSaldo() - (transfValor + 5.60f));
                        // INCREMENTANDO O SALDO DO QUE RECEBEU
                        Bd.contaCorrenteTransf.setSaldo(Bd.contaCorrenteTransf.getSaldo() + transfValor);
                        return ("    Transferência realizada. Seu saldo é: "
                                + Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getSaldo()));
                    }
                    // SE NÃO POSSUIR SALDO COM O INCREMENTO DA TAXA
                    else{
                        return("    Não há saldo suficiente. Seu saldo é: "
                                + Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getSaldo()));
                    }
                }

                // SE A CONTA DE TRANSFERÊNCIA FOR POUPANÇA
                if (Bd.contaPoupancaTransf != null) {
                    Bd.contaPoupancaTransf.setSaldo(Bd.contaPoupancaTransf.getSaldo() + transfValor);
                    return("    Transferência realizada. Seu saldo é: "
                            + Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getSaldo()));
                }

            }
            // SEM SALDO SUFICIENTE PARA TRANSFERÊNCIA
            else{
                return("    Não há saldo suficiente. Seu saldo é: "
                        + Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getSaldo()));
            }
        }

        return("    Retornando ao menu PIX");
    }

    // RETORNA TODAS AS CHAVES PIX QUE O CLIENTE LOGADO POSSUI
    public static Map<Integer, String> consultarChavesPixCliente(Integer tipoConta, Boolean printa){

        // DECLARAÇÃO DE VARIÁVEIS
        int cont = 1;
        boolean temChave = true;
        Map<Integer, String> chavesOpc = new HashMap<>();

        // SE A CONTA FOR CORRENTE
        if(tipoConta == 1){

            // SE O CLIENTE SÓ TIVER CONTA CORRENTE
            if(Bd.clienteBuscaContaPoupanca == null) {
                // SE AS CHAVES PIX DA CONTA CORRENTE RETORNAREM ALGO
                if (!Bd.clienteBuscaContaCorrente.getChavesPix().isEmpty()) {
                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS CORRENTES
                    for (Pix i : Bd.clienteBuscaContaCorrente.getChavesPix()) {
                        if(printa) {
                            System.out.println("    [" + cont + "] " + " Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave() + " - CC - " + i.getTipoChavePix());
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
                        // BUSCA NA CONTA CORRENTE DO CLIENTE POR ALGUM CHAVE PIX
                        for (Pix i : Bd.clienteBuscaContaCorrente.getChavesPix()) {

                            // SE PARÂMETRO DE PRINTA FOR TRUE ELE VAI PRINTAR NA TELA
                            if(printa) {
                                // EXIBE NA TELA NO SEGUINTE MODELO: [ORDEM] TIPO DA CHAVE PIX: CONTEÚDO DA CHAVE PIX
                                System.out.println("    [" + cont + "] " + " Chave "
                                        + i.getTipoChavePix()
                                        + ": " + i.getConteudoChave()
                                        + " - CC - " + i.getTipoChavePix());
                            }
                            // INSERE NO ARRAYLIST CHAVES OPC O ITEM ENCONTRADO
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS CORRENTES
                    try {
                        // BUSCA NA CONTA CORRENTE DO CLIENTE POR ALGUM CHAVE PIX
                        for (Pix i : Bd.clienteBuscaContaPoupanca.getChavesPix()) {
                            // SE PARÂMETRO DE PRINTA FOR TRUE ELE VAI PRINTAR NA TELA
                            if(printa) {
                                // EXIBE NA TELA NO SEGUINTE MODELO: [ORDEM] TIPO DA CHAVE PIX: CONTEÚDO DA CHAVE PIX
                                System.out.println("    [" + cont + "] "
                                        + " Chave " + i.getTipoChavePix()
                                        + ": " + i.getConteudoChave()
                                        + " - CP - " + i.getTipoChavePix());
                            }
                            // INSERE NO ARRAYLIST CHAVES OPC O ITEM ENCONTRADO
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
                        // SE PARÂMETRO DE PRINTA FOR TRUE ELE VAI PRINTAR NA TELA
                        if(printa) {
                            // EXIBE NA TELA NO SEGUINTE MODELO: [ORDEM] TIPO DA CHAVE PIX: CONTEÚDO DA CHAVE PIX
                            System.out.println("    [" + cont + "] " + "Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave()  + " - CP - " + i.getTipoChavePix());
                        }
                        // INSERE NO ARRAYLIST CHAVES OPC O ITEM ENCONTRADO
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
                            // SE PARÂMETRO DE PRINTA FOR TRUE ELE VAI PRINTAR NA TELA
                            if(printa) {
                                // EXIBE NA TELA NO SEGUINTE MODELO: [ORDEM] TIPO DA CHAVE PIX: CONTEÚDO DA CHAVE PIX
                                System.out.println("    [" + cont + "] " + "Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave() + " - CC - " + i.getTipoChavePix());
                            }
                            // INSERE NO ARRAYLIST CHAVES OPC O ITEM ENCONTRADO
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS POUPANÇAS
                    try {
                        for (Pix i : Bd.clienteBuscaContaPoupanca.getChavesPix()) {
                            // SE PARÂMETRO DE PRINTA FOR TRUE ELE VAI PRINTAR NA TELA
                            if(printa) {
                                // EXIBE NA TELA NO SEGUINTE MODELO: [ORDEM] TIPO DA CHAVE PIX: CONTEÚDO DA CHAVE PIX
                                System.out.println("    [" + cont + "] " + "Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave() + " - CP - " + i.getTipoChavePix());
                            }
                            // INSERE NO ARRAYLIST CHAVES OPC O ITEM ENCONTRADO
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
            return null;
        }

        else{
            return(chavesOpc);
        }

    }

}
