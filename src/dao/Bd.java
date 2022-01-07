package dao;
import model.cliente.Cliente;
import model.conta.ContaCorrente;
import model.conta.ContaPoupanca;

import java.util.ArrayList;

public class Bd {

    public static ContaCorrente clienteBuscaContaCorrente;
    public static ContaPoupanca clienteBuscaContaPoupanca;

    public static ArrayList<Cliente> clientes = new ArrayList<>();
    public static ArrayList<ContaCorrente> contaCorrentes = new ArrayList<>();
    public static ArrayList<ContaPoupanca> contaPoupancas = new ArrayList<>();

    public static void addCliente(Cliente cliente){
        clientes.add(cliente);
    }
    public static void addContaCorrente(ContaCorrente conta){
        contaCorrentes.add(conta);
    }

    public static void buscarContaCorrentePorCpf(String cpf){
        for(ContaCorrente i: contaCorrentes){
            if(i.getCliente().getCpf().equals(cpf)){
                clienteBuscaContaCorrente = i;
            }
        }
    }

    public static void addContaPoupanca(ContaPoupanca conta){
        contaPoupancas.add(conta);
    }
    public static void buscarContaPoupancaPorCpf(String cpf){
        for(ContaPoupanca i: contaPoupancas){
            if(i.getCliente().getCpf().equals(cpf)){
                clienteBuscaContaPoupanca = i;
            }
        }
    }



}
