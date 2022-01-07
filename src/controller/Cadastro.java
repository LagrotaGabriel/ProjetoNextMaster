package controller;
import model.cliente.Cliente;
import model.cliente.Endereco;
import model.conta.Conta;
import model.conta.ContaCorrente;
import model.conta.ContaPoupanca;
import java.util.Random;

public class Cadastro {

    static ContaCorrente cc;
    static ContaPoupanca cp;

    public static void cadastrarUsuario(String nome, String cpf, String rg, String senha, String cidade,
                                          String estado, String bairro, String numeroRua, String rua, String cep,
                                        String tipoDeConta){

        Integer random = new Random().nextInt(111111,999999);
        Endereco endereco = new Endereco(cidade, estado, bairro, Integer.parseInt(numeroRua), rua, cep);
        Cliente cliente = new Cliente(nome, cpf, rg, endereco, senha);
        if(tipoDeConta.equals("1")) {
            ContaCorrente cc = new ContaCorrente(cliente, 1, random, 0.0f, 30.0f);
            dao.Bd.addContaCorrente(cc);
        }else if(tipoDeConta.equals("2")){
            ContaPoupanca cp = new ContaPoupanca(cliente, 1, random, 0.0f);
            dao.Bd.addContaPoupanca(cp);
        }else{
            ContaCorrente cc = new ContaCorrente(cliente, 1, random, 0.0f, 30.0f);
            ContaPoupanca cp = new ContaPoupanca(cliente, 1, random, 0.0f);
            dao.Bd.addContaCorrente(cc);
            dao.Bd.addContaPoupanca(cp);
        }
        dao.Bd.addCliente(cliente);

    }
}
