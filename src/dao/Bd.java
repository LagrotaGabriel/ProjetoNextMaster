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
                Pix pix = new Pix(clienteBuscaContaPoupanca.getCliente(), cpf, TipoChavePix.CPF);
                pixsMap.put(pixsMap.size() + 1, cpf);
                System.out.println("Chave PIX cadastrada com sucesso!");
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
            // Imprime tudo só de zoas
            System.out.println("TODOS HASHMAP:");
            for (Map.Entry<Integer, String> entry : pixsMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            System.out.println("LISTAS DO CLIENTE:");
            for (Pix i : clienteBuscaContaCorrente.chavesPix) {
                System.out.println(i);
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
            // Imprime tudo só de zoas
            System.out.println("TODOS HASHMAP:");
            for (Map.Entry<Integer, String> entry : pixsMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            System.out.println("LISTAS DO CLIENTE:");
            for (Pix i : clienteBuscaContaPoupanca.chavesPix) {
                System.out.println(i);
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
            // Imprime tudo só de zoas
            System.out.println("TODOS HASHMAP:");
            for (Map.Entry<Integer, String> entry : pixsMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            System.out.println("LISTAS DO CLIENTE:");
            for (Pix i : clienteBuscaContaCorrente.chavesPix) {
                System.out.println(i);
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
            // Imprime tudo só de zoas
            System.out.println("TODOS HASHMAP:");
            for (Map.Entry<Integer, String> entry : pixsMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            System.out.println("LISTAS DO CLIENTE:");
            for (Pix i : clienteBuscaContaPoupanca.chavesPix) {
                System.out.println(i);
            }
        }
    }

    // Buscar chaves pix do cliente
    public static Map<Integer, String> buscarChavesPixCliente(Integer tipoConta){

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
                        System.out.println("    [" + cont + "] " +  "    Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave());
                        chavesOpc.put(cont, i.getConteudoChave());
                        cont++;
                    }
                }
                // SE AS CHAVES PIX DA CONTA CORRENTE NÃO RETORNAREM NADA
                else {
                    System.out.println("    Não há chaves PIX cadastradas nesta conta");
                }

            }
            // SE O CLIENTE TIVER CONTA CORRENTE E CONTA POUPANCA
            else{
                // SE AS CHAVES PIX DA CONTA CORRENTE E POUPANÇA RETORNAREM ALGO
                if (!clienteBuscaContaCorrente.getChavesPix().isEmpty() || !clienteBuscaContaPoupanca.getChavesPix().isEmpty()) {

                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS CORRENTES
                    try {
                        for (Pix i : clienteBuscaContaCorrente.getChavesPix()) {
                            System.out.println("    [" + cont + "] " + "    Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave());
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                    try {
                        for (Pix i : clienteBuscaContaPoupanca.getChavesPix()) {
                            System.out.println("    [" + cont + "] " + "Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave());
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                }
                // SE AS CHAVES PIX DA CONTA CORRENTE E POUPANÇA NÃO RETORNAREM NADA
                else {
                    temChave = false;
                    System.out.println("    Não há chaves PIX cadastradas nesta conta");
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
                        System.out.println("    [" + cont + "] " + "Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave());
                        chavesOpc.put(cont, i.getConteudoChave());
                        cont++;
                    }
                }
                // SE AS CHAVES PIX DA CONTA POUPANÇA NÃO RETORNAREM NADA
                else {
                    System.out.println("    Não há chaves PIX cadastradas nesta conta");
                }

            }

            // SE O CLIENTE TIVER CONTA CORRENTE E CONTA POUPANCA
            else{

                // SE AS CHAVES PIX DA CONTA CORRENTE E POUPANÇA RETORNAREM ALGO
                if (!clienteBuscaContaCorrente.getChavesPix().isEmpty() || !clienteBuscaContaPoupanca.getChavesPix().isEmpty()) {

                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS CORRENTES
                    try {
                        for (Pix i : clienteBuscaContaCorrente.getChavesPix()) {
                            System.out.println("    [" + cont + "] " + "Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave());
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                    // PASSANDO NO HASHMAP DE CHAVES PIX CONTAS POUPANÇAS
                    try {
                        for (Pix i : clienteBuscaContaPoupanca.getChavesPix()) {
                            System.out.println("    [" + cont + "] " + "Chave " + i.getTipoChavePix() + ": " + i.getConteudoChave());
                            chavesOpc.put(cont, i.getConteudoChave());
                            cont++;
                        }
                    }catch(Exception ignored){}

                }
                // SE AS CHAVES PIX DA CONTA CORRENTE E POUPANÇA NÃO RETORNAREM NADA
                else {
                    temChave = false;
                    System.out.println("    Não há chaves PIX cadastradas nesta conta");
                }

            }
        }

        if(!temChave){
            return(chavesOpc = null);
        }else{
            return(chavesOpc);
        }

    }

    // Apagar chaves pix do cliente
    public static void pixDelete(Integer tipoConta, Integer entrada){

        String valor = "";

        Map<Integer, String> chavesOpc = buscarChavesPixCliente(tipoConta);

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
        }



    }

}
