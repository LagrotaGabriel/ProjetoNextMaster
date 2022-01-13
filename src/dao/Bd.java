package dao;
import model.cliente.Cliente;
import model.conta.ContaCorrente;
import model.conta.ContaPoupanca;
import model.pix.Pix;
import model.pix.TipoChavePix;

import java.util.HashMap;
import java.util.Map;

public class Bd {

    public static ContaCorrente clienteBuscaContaCorrente;
    public static ContaPoupanca clienteBuscaContaPoupanca;
    public static Pix clientesBuscaPix;
    public static Pix pix;
    public static ContaCorrente contaCorrenteTransf = null;
    public static ContaPoupanca contaPoupancaTransf = null;

    public static Map<Integer, Cliente> clientesMap = new HashMap<>();
    public static Map<Integer, ContaCorrente> contaCorrentesMap = new HashMap<>();
    public static Map<Integer, ContaPoupanca> contaPoupancasMap = new HashMap<>();

    // Zerar
    public static void zerarInstancias(){

        clienteBuscaContaCorrente = null;
        clienteBuscaContaPoupanca = null;

    }

    // Todos os pix
    public static Map<Integer, String> pixsMap = new HashMap<>();

    // BuscarPorCpf
    public static void buscarContaCorrentePorCpf(String cpf){

        for(ContaCorrente key: contaCorrentesMap.values()){
            if(key.getCliente().getCpf().equals(cpf)){
                clienteBuscaContaCorrente = key;
            }
        }
    }

    // BuscarPorCpf
    public static void buscarContaPoupancaPorCpf(String cpf){

        for(ContaPoupanca key: contaPoupancasMap.values()){

            if(key.getCliente().getCpf().equals(cpf)){
                clienteBuscaContaPoupanca = key;
            }
        }

    }

    // Validar e inserir chave pix de cpf
    public static void insereChavePixCpf(String cpf, Integer tipoConta){

        // Se a conta é conta corrente
        if(tipoConta == 1) {

            // Se HashMap não estiver vazio
            if (!pixsMap.isEmpty()) {
                // Se pixsmap NÃO conter a chave pix que será cadastrada
                if (!pixsMap.containsValue(cpf)) {
                    Pix pix = new Pix(clienteBuscaContaCorrente.getCliente(), cpf, TipoChavePix.CPF);
                    pixsMap.put(pixsMap.size() + 1, cpf);
                    System.out.println("    Chave PIX cadastrada com sucesso!");
                    clienteBuscaContaCorrente.chavesPix.add(pix);
                }
                // Se pixsMap já CONTER a chave pix
                else {
                    System.out.println(("    A chave PIX já existe em nosso sistema"));
                }

            }
            // Se HashMap estiver vazio
            else {
                Pix pix = new Pix(clienteBuscaContaCorrente.getCliente(), cpf, TipoChavePix.CPF);
                pixsMap.put(pixsMap.size() + 1, cpf);
                System.out.println("Chave PIX cadastrada com sucesso!");
                clienteBuscaContaCorrente.chavesPix.add(pix);
            }

        }

        // Se a conta for conta poupança
        else{
            // Se HashMap não estiver vazio
            if (!pixsMap.isEmpty()) {

                // Se pixsmap NÃO conter a chave pix que será cadastrada
                if (!pixsMap.containsValue(cpf)) {
                    Pix pix = new Pix(clienteBuscaContaPoupanca.getCliente(), cpf, TipoChavePix.CPF);
                    pixsMap.put(pixsMap.size() + 1, cpf);
                    System.out.println("    Chave PIX cadastrada com sucesso!");
                    clienteBuscaContaPoupanca.chavesPix.add(pix);
                }
                // Se pixsMap já CONTER a chave pix
                else {
                    System.out.println(("    A chave PIX já existe em nosso sistema"));
                }
            }
            // Se HashMap estiver vazio
            else {
                Pix pix = new Pix(clienteBuscaContaPoupanca.getCliente(), cpf, TipoChavePix.CPF);
                pixsMap.put(pixsMap.size() + 1, cpf);
                System.out.println("    Chave PIX cadastrada com sucesso!");
                clienteBuscaContaPoupanca.chavesPix.add(pix);
            }
        }

    }

    // Validar e inserir chave pix de email
    public static void insereChavePixEmail(String email, Integer tipoConta){

        // Se a conta é conta corrente
        if(tipoConta == 1) {
            // Se HashMap não estiver vazio
            if (!pixsMap.isEmpty()) {

                // Se pixsmap NÃO conter a chave pix que será cadastrada
                if (!pixsMap.containsValue(email)) {
                    Pix pix = new Pix(clienteBuscaContaCorrente.getCliente(), email, TipoChavePix.EMAIL);
                    pixsMap.put(pixsMap.size() + 1, email);
                    System.out.println("Chave PIX cadastrada com sucesso!");
                    clienteBuscaContaCorrente.chavesPix.add(pix);
                }
                // Se pixsMap já CONTER a chave pix
                else {
                    System.out.println(("A chave PIX já existe em nosso sistema"));
                }
            }
            // Se HashMap estiver vazio
            else {
                Pix pix = new Pix(clienteBuscaContaCorrente.getCliente(), email, TipoChavePix.EMAIL);
                pixsMap.put(pixsMap.size() + 1, email);
                System.out.println("Chave PIX cadastrada com sucesso!");
                clienteBuscaContaCorrente.chavesPix.add(pix);
            }
        }

        // Se a conta for conta poupança
        else{
            // Se HashMap não estiver vazio
            if (!pixsMap.isEmpty()) {

                // Se pixsmap NÃO conter a chave pix que será cadastrada
                if (!pixsMap.containsValue(email)) {
                    Pix pix = new Pix(clienteBuscaContaPoupanca.getCliente(), email, TipoChavePix.EMAIL);
                    pixsMap.put(pixsMap.size() + 1, email);
                    System.out.println("Chave PIX cadastrada com sucesso!");
                    clienteBuscaContaPoupanca.chavesPix.add(pix);
                }
                // Se pixsMap já CONTER a chave pix
                else {
                    System.out.println(("A chave PIX já existe em nosso sistema"));
                }
            }
            // Se HashMap estiver vazio
            else {
                Pix pix = new Pix(clienteBuscaContaPoupanca.getCliente(), email, TipoChavePix.EMAIL);
                pixsMap.put(pixsMap.size() + 1, email);
                System.out.println("Chave PIX cadastrada com sucesso!");
                clienteBuscaContaPoupanca.chavesPix.add(pix);
            }
        }


    }

    // Validar e inserir chave pix de telefone
    public static void insereChavePixTelefone(String telefone, Integer tipoConta){

        // Se a conta é conta corrente
        if(tipoConta == 1) {
            // Se HashMap não estiver vazio
            if (!pixsMap.isEmpty()) {

                // Se pixsmap NÃO conter a chave pix que será cadastrada
                if (!pixsMap.containsValue(telefone)) {
                    Pix pix = new Pix(clienteBuscaContaCorrente.getCliente(), telefone, TipoChavePix.TELEFONE);
                    pixsMap.put(pixsMap.size() + 1, telefone);
                    System.out.println("Chave PIX cadastrada com sucesso!");
                    clienteBuscaContaCorrente.chavesPix.add(pix);
                }
                // Se pixsMap já CONTER a chave pix
                else {
                    System.out.println(("A chave PIX já existe em nosso sistema"));
                }
            }
            // Se HashMap estiver vazio
            else {
                Pix pix = new Pix(clienteBuscaContaCorrente.getCliente(), telefone, TipoChavePix.TELEFONE);
                pixsMap.put(pixsMap.size() + 1, telefone);
                System.out.println("Chave PIX cadastrada com sucesso!");
                clienteBuscaContaCorrente.chavesPix.add(pix);
            }
        }

        // Se a conta for conta poupança
        else{
            // Se HashMap não estiver vazio
            if (!pixsMap.isEmpty()) {

                // Se pixsmap NÃO conter a chave pix que será cadastrada
                if (!pixsMap.containsValue(telefone)) {
                    Pix pix = new Pix(clienteBuscaContaPoupanca.getCliente(), telefone, TipoChavePix.TELEFONE);
                    pixsMap.put(pixsMap.size() + 1, telefone);
                    System.out.println("Chave PIX cadastrada com sucesso!");
                    clienteBuscaContaPoupanca.chavesPix.add(pix);
                }
                // Se pixsMap já CONTER a chave pix
                else {
                    System.out.println(("A chave PIX já existe em nosso sistema"));
                }
            }
            // Se HashMap estiver vazio
            else {
                Pix pix = new Pix(clienteBuscaContaPoupanca.getCliente(), telefone, TipoChavePix.TELEFONE);
                pixsMap.put(pixsMap.size() + 1, telefone);
                System.out.println("Chave PIX cadastrada com sucesso!");
                clienteBuscaContaPoupanca.chavesPix.add(pix);
            }
        }
    }

    // Buscar chaves pix do cliente
    public static Map<Integer, String> buscarChavesPixCliente(Integer tipoConta, Boolean printa){

        int cont = 1;
        Boolean temChave = true;
        Map<Integer, String> chavesOpc = new HashMap<>();

        //String[] chavesOpc = new String[3];

        // SE A CONTA FOR CORRENTE
        if(tipoConta == 1){

            // SE O CLIENTE SÓ TIVER CONTA CORRENTE
            if(clienteBuscaContaPoupanca == null) {
                // SE AS CHAVES PIX DA CONTA CORRENTE RETORNAREM ALGO
                if (!clienteBuscaContaCorrente.getChavesPix().isEmpty()) {
                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS CORRENTES
                    for (Pix i : clienteBuscaContaCorrente.getChavesPix()) {
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
                if (!clienteBuscaContaCorrente.getChavesPix().isEmpty() || !clienteBuscaContaPoupanca.getChavesPix().isEmpty()) {

                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS CORRENTES
                    try {
                        for (Pix i : clienteBuscaContaCorrente.getChavesPix()) {
                            if(printa) {
                                System.out.println("    [" + cont + "] " + " Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave() + " - CC");
                            }
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                    try {
                        for (Pix i : clienteBuscaContaPoupanca.getChavesPix()) {
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
            if(clienteBuscaContaCorrente == null) {

                // SE AS CHAVES PIX DA CONTA POUPANCA RETORNAREM ALGO
                if (!clienteBuscaContaPoupanca.getChavesPix().isEmpty()) {
                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS POUPANCAS
                    for (Pix i : clienteBuscaContaPoupanca.getChavesPix()) {
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
                if (!clienteBuscaContaCorrente.getChavesPix().isEmpty() || !clienteBuscaContaPoupanca.getChavesPix().isEmpty()) {

                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS CORRENTES
                    try {
                        for (Pix i : clienteBuscaContaCorrente.getChavesPix()) {
                            if(printa) {
                                System.out.println("    [" + cont + "] " + "Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave() + " - CC");
                            }
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS POUPANÇAS
                    try {
                        for (Pix i : clienteBuscaContaPoupanca.getChavesPix()) {
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

    // Busca chave pix por entrada
    public static void buscarChavePix(String entrada){

        contaCorrenteTransf = null;
        contaPoupancaTransf = null;

        // RODA TODOS OS PIX CADASTRADOS
        for(Map.Entry<Integer, String> entry: Bd.pixsMap.entrySet()){

            // SE O VALOR DE ENTRADA BATER COM ALGUM PIX CADASTRADO
            if(entry.getValue().equals(entrada)){

                // SE TIVER CONTAS CORRENTES CADASTRADAS
                if(!contaCorrentesMap.isEmpty()) {

                    // RODA TODAS AS CONTAS CORRENTES
                    for (Map.Entry<Integer, ContaCorrente> entry2 : contaCorrentesMap.entrySet()) {

                        // RODA TODAS AS CHAVES PIX DE CADA CONTA CORRENTE
                        for (Pix entry3 : entry2.getValue().getChavesPix()) {
                            // SE O VALOR DE ENTRADA BATER COM ALGUM PIX CADASTRADO
                            if(entry3.getConteudoChave().equals(entrada)){
                                contaCorrenteTransf = entry2.getValue();
                                System.out.println(contaCorrenteTransf);
                            }
                        }
                    }
                }
                // SE TIVER CONTAS POUPANÇAS CADASTRADAS
                if(!contaPoupancasMap.isEmpty()) {
                    // RODA TODAS AS CONTAS POUPANÇAS
                    for (Map.Entry<Integer, ContaPoupanca> entry2 : contaPoupancasMap.entrySet()) {
                        // RODA TODAS AS CHAVES PIX DE CADA CONTA POUPANÇA
                        for (Pix entry3 : entry2.getValue().getChavesPix()) {
                            // SE O VALOR DE ENTRADA BATER COM ALGUM PIX CADASTRADO
                            if(entry3.getConteudoChave() == entrada){
                                contaPoupancaTransf = entry2.getValue();
                                System.out.println(contaPoupancaTransf);
                            }
                        }
                    }
                }
            }
        }
    }

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
                    return("Transferência realizada. Seu saldo atual é: R$ " + Bd.clienteBuscaContaCorrente.getSaldo());
                }

                // SE A CONTA DE TRANSFERÊNCIA FOR POUPANÇA
                if (Bd.contaPoupancaTransf != null) {
                    Bd.contaPoupancaTransf.setSaldo(Bd.contaPoupancaTransf.getSaldo() + transfValor);
                    return("Transferência realizada. Seu saldo atual é: R$ " + Bd.clienteBuscaContaCorrente.getSaldo());
                }
            }
            // SEM SALDO SUFICIENTE PARA TRANSFERÊNCIA
            else{
                return("Não há saldo suficiente. Seu saldo disponível é: R$ " + Bd.clienteBuscaContaCorrente.getSaldo());
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
                    return("Transferência realizada. Seu saldo atual é: R$ " + Bd.clienteBuscaContaPoupanca.getSaldo());
                }

                // SE A CONTA DE TRANSFERÊNCIA FOR POUPANÇA
                if (Bd.contaPoupancaTransf != null) {
                    Bd.contaPoupancaTransf.setSaldo(Bd.contaPoupancaTransf.getSaldo() + transfValor);
                    return("Transferência realizada. Seu saldo atual é: R$ " + Bd.clienteBuscaContaPoupanca.getSaldo());
                }

            }
            // SEM SALDO SUFICIENTE PARA TRANSFERÊNCIA
            else{
                return("Não há saldo suficiente. Seu saldo disponível é: R$ " + Bd.clienteBuscaContaPoupanca.getSaldo());
            }
        }

        return("Retornando ao menu PIX");
    }

    // Apagar chaves pix do cliente
    public static String pixDelete(Integer tipoConta, Integer entrada){

        String valor = "";

        Map<Integer, String> chavesOpc = buscarChavesPixCliente(tipoConta, false);

        if(chavesOpc != null) {
            try {
                for (Map.Entry<Integer, String> entry : chavesOpc.entrySet()) {
                    if (entry.getKey() == entrada) {
                        pixsMap.remove(entry.getKey());
                        chavesOpc.remove(entry.getKey());
                        valor = entry.getValue();
                    }
                }
            }catch(Exception ignored){}
            try {
                for (Pix i : clienteBuscaContaCorrente.getChavesPix()) {
                    if (i.getConteudoChave() == valor) {
                        clienteBuscaContaCorrente.getChavesPix().remove(i);
                        break;
                    }
                }
            }catch(Exception ignored){}
            try{
                for (Pix i : clienteBuscaContaPoupanca.getChavesPix()) {
                    if (i.getConteudoChave() == valor) {
                        clienteBuscaContaPoupanca.getChavesPix().remove(i);
                        break;
                    }
                }
            }catch(Exception ignored){}
            pixsMap.remove(entrada);
            return("    Chave removida com sucesso");
        }else{
            return("");
        }



    }

}
