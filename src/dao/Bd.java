package dao;

import bo.PixBo;
import model.cartao.credito.Credito;
import model.cartao.debito.Debito;
import model.cliente.Cliente;
import model.conta.ContaCorrente;
import model.conta.ContaPoupanca;
import model.pix.Pix;
import model.pix.TipoChavePix;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bd {

    public static ContaCorrente clienteBuscaContaCorrente;
    public static ContaPoupanca clienteBuscaContaPoupanca;
    public static ContaCorrente contaCorrenteTransf = null;
    public static ContaPoupanca contaPoupancaTransf = null;

    public static Map<Integer, Cliente> clientesMap = new HashMap<>();

    public static Map<Integer, ContaCorrente> contaCorrentesMap = new HashMap<>();
    public static Map<Integer, ContaPoupanca> contaPoupancasMap = new HashMap<>();

    public static Map<Integer, Debito> cartoesDebitoMap = new HashMap<>();
    public static Map<Integer, Credito> cartoesCreditoMap = new HashMap<>();

    // Zerar
    public static void zerarInstancias() {

        clienteBuscaContaCorrente = null;
        clienteBuscaContaPoupanca = null;

    }

    // Todos os pix
    public static Map<Integer, String> pixsMap = new HashMap<>();

    // BuscarPorCpf
    public static void buscarContaCorrentePorCpf(String cpf) {

        for (ContaCorrente key : contaCorrentesMap.values()) {
            if (key.getCliente().getCpf().equals(cpf)) {
                clienteBuscaContaCorrente = key;
            }
        }
    }

    // BuscarPorCpf
    public static void buscarContaPoupancaPorCpf(String cpf) {

        for (ContaPoupanca key : contaPoupancasMap.values()) {

            if (key.getCliente().getCpf().equals(cpf)) {
                clienteBuscaContaPoupanca = key;
            }
        }

    }

    // Validar e inserir chave pix de cpf
    public static void insereChavePixCpf(String cpf, Integer tipoConta) {

        // Se a conta é conta corrente
        if (tipoConta == 1) {

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
        else {
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
    public static void insereChavePixEmail(String email, Integer tipoConta) {

        // Se a conta é conta corrente
        if (tipoConta == 1) {
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
        else {
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
    public static void insereChavePixTelefone(String telefone, Integer tipoConta) {

        // Se a conta é conta corrente
        if (tipoConta == 1) {
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
        else {
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

    // Busca chave pix por entrada NO BD
    public static void buscarChavePix(String entrada) {

        contaCorrenteTransf = null;
        contaPoupancaTransf = null;

        // RODA TODOS OS PIX CADASTRADOS
        for (Map.Entry<Integer, String> entry : Bd.pixsMap.entrySet()) {

            // SE O VALOR DE ENTRADA BATER COM ALGUM PIX CADASTRADO
            if (entry.getValue().equals(entrada)) {

                // SE TIVER CONTAS CORRENTES CADASTRADAS
                if (!contaCorrentesMap.isEmpty()) {

                    // RODA TODAS AS CONTAS CORRENTES
                    for (Map.Entry<Integer, ContaCorrente> entry2 : contaCorrentesMap.entrySet()) {

                        // RODA TODAS AS CHAVES PIX DE CADA CONTA CORRENTE
                        for (Pix entry3 : entry2.getValue().getChavesPix()) {
                            // SE O VALOR DE ENTRADA BATER COM ALGUM PIX CADASTRADO
                            if (entry3.getConteudoChave().equals(entrada)) {
                                contaCorrenteTransf = entry2.getValue();
                                break;
                            }
                        }
                    }
                }
                // SE TIVER CONTAS POUPANÇAS CADASTRADAS
                if (!contaPoupancasMap.isEmpty()) {
                    // RODA TODAS AS CONTAS POUPANÇAS
                    for (Map.Entry<Integer, ContaPoupanca> entry2 : contaPoupancasMap.entrySet()) {
                        // RODA TODAS AS CHAVES PIX DE CADA CONTA POUPANÇA
                        for (Pix entry3 : entry2.getValue().getChavesPix()) {
                            // SE O VALOR DE ENTRADA BATER COM ALGUM PIX CADASTRADO
                            if (Objects.equals(entry3.getConteudoChave(), entrada)) {
                                contaPoupancaTransf = entry2.getValue();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    // Apagar chaves pix do cliente
    public static void pixDelete(Integer tipoConta, Integer entrada) {

        String valor = "";

        Map<Integer, String> chavesOpc = PixBo.consultarChavesPixCliente(tipoConta, false);

        if (chavesOpc != null) {
            try {
                for (Map.Entry<Integer, String> entry : chavesOpc.entrySet()) {
                    if (Objects.equals(entry.getKey(), entrada)) {
                        pixsMap.remove(entry.getKey());
                        chavesOpc.remove(entry.getKey());
                        valor = entry.getValue();
                    }
                }
            } catch (Exception ignored) {
            }
            try {
                for (Pix i : clienteBuscaContaCorrente.getChavesPix()) {
                    if (Objects.equals(i.getConteudoChave(), valor)) {
                        clienteBuscaContaCorrente.getChavesPix().remove(i);
                        break;
                    }
                }
            } catch (Exception ignored) {
            }
            try {
                for (Pix i : clienteBuscaContaPoupanca.getChavesPix()) {
                    if (Objects.equals(i.getConteudoChave(), valor)) {
                        clienteBuscaContaPoupanca.getChavesPix().remove(i);
                        break;
                    }
                }
            } catch (Exception ignored) {
            }
            pixsMap.remove(entrada);
        }

    }

    // Insere novo cartão de débito
    public static void insereCartaoDebito(Debito debito, Integer tipoConta){

        // SE A CONTA DO CLIENTE FOR UMA CONTA CORRENTE
        if(tipoConta == 1){
            // SE A CONTA DO CLIENTE ATUAL NÃO TIVER NENHUM CARTÃO DE DÉBITO CADASTRADO
            if(clienteBuscaContaCorrente.cartoesDebitoCliente.isEmpty()) {
                // SE NÃO TIVER NENHUM CARTÃO DE DÉBITO CADASTRADO NO SISTEMA
                if (cartoesDebitoMap.size() == 0) {
                    // CADASTRA CARTÃO DE DÉBITO NO GLOBAL COM ID 1
                    cartoesDebitoMap.put(1, debito);
                }
                // SE TIVER PELO MENOS UM CARTÃO DE DÉBITO CADASTRADO NO SISTEMA
                else {
                    // CADASTRA CARTÃO DE DÉBITO NO GLOBAL COM ID SIZE + 1
                    cartoesDebitoMap.put(cartoesDebitoMap.size() + 1, debito);
                }
                // CADASTRA CARTÃO DE DÉBITO NO ARRAYLIST DO CLIENTE ATUAL
                clienteBuscaContaCorrente.cartoesDebitoCliente.add(debito);
            }
        }
        // SE A CONTA DO CLIENTE FOR UMA CONTA POUPANÇA
        else if(tipoConta == 2){
            // SE A CONTA DO CLIENTE ATUAL NÃO TIVER NENHUM CARTÃO DE DÉBITO CADASTRADO
            if(clienteBuscaContaPoupanca.cartoesDebitoCliente.isEmpty()) {
                // SE NÃO TIVER NENHUM CARTÃO DE DÉBITO CADASTRADO NO SISTEMA
                if (cartoesDebitoMap.size() == 0) {
                    // CADASTRA CARTÃO DE DÉBITO NO GLOBAL COM ID 1
                    cartoesDebitoMap.put(1, debito);
                }
                // SE TIVER PELO MENOS UM CARTÃO DE DÉBITO CADASTRADO NO SISTEMA
                else {
                    // CADASTRA CARTÃO DE DÉBITO NO GLOBAL COM ID SIZE + 1
                    cartoesDebitoMap.put(cartoesDebitoMap.size() + 1, debito);
                }
                // CADASTRA CARTÃO DE DÉBITO NO ARRAYLIST DO CLIENTE ATUAL
                clienteBuscaContaPoupanca.cartoesDebitoCliente.add(debito);
            }
        }
    }

    // Insere novo cartão de crédito
    public static void insereCartaoCredito(Credito credito, Integer tipoConta){

        // SE A CONTA DO CLIENTE FOR UMA CONTA CORRENTE
        if(tipoConta == 1){
            // SE A CONTA DO CLIENTE ATUAL NÃO TIVER NENHUM CARTÃO DE CRÉDITO CADASTRADO
            if(clienteBuscaContaCorrente.cartoesCreditoCliente.isEmpty()) {
                // SE NÃO TIVER NENHUM CARTÃO DE CRÉDITO CADASTRADO NO SISTEMA
                if (cartoesCreditoMap.size() == 0) {
                    // CADASTRA CARTÃO DE CRÉDITO NO GLOBAL COM ID 1
                    cartoesCreditoMap.put(1, credito);
                }
                // SE TIVER PELO MENOS UM CARTÃO DE CRÉDITO CADASTRADO NO SISTEMA
                else {
                    // CADASTRA CARTÃO DE CRÉDITO NO GLOBAL COM ID SIZE + 1
                    cartoesCreditoMap.put(cartoesCreditoMap.size() + 1, credito);
                }
                // CADASTRA CARTÃO DE DÉBITO NO ARRAYLIST DO CLIENTE ATUAL
                clienteBuscaContaCorrente.cartoesCreditoCliente.add(credito);
            }
        }
    }


}
