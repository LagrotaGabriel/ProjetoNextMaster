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
    public static Pix pix = new Pix();

    public static Map<Integer, Cliente> clientesMap = new HashMap<>();
    public static Map<Integer, ContaCorrente> contaCorrentesMap = new HashMap<>();
    public static Map<Integer, ContaPoupanca> contaPoupancasMap = new HashMap<>();
    public static Map<Pix, String> pixsMap = new HashMap<>();
    public static Map<Integer, Pix> historicoPixsMap = new HashMap<>();

    public static void buscarContaCorrentePorCpf(String cpf){
        for(ContaCorrente key: contaCorrentesMap.values()){
            if(key.getCliente().getCpf().equals(cpf)){
                clienteBuscaContaCorrente = key;
            }
        }
    }

    public static void buscarContaPoupancaPorCpf(String cpf){
        for(ContaPoupanca key: contaPoupancasMap.values()){
            if(key.getCliente().getCpf().equals(cpf)){
                clienteBuscaContaPoupanca = key;
            }
        }
    }

    // Validar e inserir chave pix de cpf
    public static void insereChavePixCpf(String cpf){
        if(!pixsMap.isEmpty()) {
            for (String key : pixsMap.values()) {
                if (key.contains(cpf)) {
                    pixsMap.put(pix, cpf);
                    System.out.println("Chave PIX cadastrada com sucesso!");
                    break;
                } else {
                    System.out.println(("A chave PIX já existe em nosso sistema"));
                }
            }
        }
        else{
            pixsMap.put(pix, cpf);
            System.out.println("Chave PIX cadastrada com sucesso!");
        }
        for(Map.Entry<Pix,String> entry: pixsMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    // Validar e inserir chave pix de email
    public static void insereChavePixEmail(String email){
        if(!pixsMap.isEmpty()) {
            for (String key : pixsMap.values()) {
                if (key.contains(email)) {
                    pixsMap.put(pix, email);
                    System.out.println("Chave PIX cadastrada com sucesso!");
                    break;
                } else {
                    System.out.println(("A chave PIX já existe em nosso sistema"));
                }
            }
        }
        else{
            pixsMap.put(pix, email);
            System.out.println("Chave PIX cadastrada com sucesso!");
        }
        for(Map.Entry<Pix,String> entry: pixsMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    // Validar e inserir chave pix de telefone
    public static void insereChavePixTelefone(String telefone){
        if(!pixsMap.isEmpty()) {
            for (String key : pixsMap.values()) {
                if (key.contains(telefone)) {
                    pixsMap.put(pix, telefone);
                    System.out.println("Chave PIX cadastrada com sucesso!");
                    break;
                } else {
                    System.out.println(("A chave PIX já existe em nosso sistema"));
                }
            }
        }
        else{
            pixsMap.put(pix, telefone);
            System.out.println("Chave PIX cadastrada com sucesso!");
        }
        for(Map.Entry<Pix,String> entry: pixsMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }


    /* Buscar chave PIX pelo CPF
    public static boolean buscarPixCpfCliente(String cpf, Integer tipoConta){

        if(tipoConta == 1) {

            if (pixsMap.containsValue(clienteBuscaContaCorrente.getCliente().getPix().getConteudoChave()){
                return (true);
            } else {
                return (false);
            }
        }else{


        }

    }*/

    //

}
