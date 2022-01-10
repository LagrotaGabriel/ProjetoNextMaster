package dao;
import model.cliente.Cliente;
import model.conta.ContaCorrente;
import model.conta.ContaPoupanca;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bd {

    public static ContaCorrente clienteBuscaContaCorrente;
    public static ContaPoupanca clienteBuscaContaPoupanca;

    public static Map<Integer, Cliente> clientesMap = new HashMap<>();
    public static Map<Integer, ContaCorrente> contaCorrentesMap = new HashMap<>();
    public static Map<Integer, ContaPoupanca> contaPoupancasMap = new HashMap<>();

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

}
