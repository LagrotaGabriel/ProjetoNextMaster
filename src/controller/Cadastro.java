package controller;
import model.cliente.Cliente;
import model.cliente.Endereco;

public class Cadastro {

    Cliente cliente;

    public static String cadastrarUsuario(String nome, String cpf, String rg, String senha, String cidade,
                                          String estado, String bairro, String numeroRua, String rua, String cep){

        Endereco endereco = new Endereco(cidade, estado, bairro, Integer.parseInt(numeroRua), rua, cep);
        Cliente cliente = new Cliente(nome, cpf, rg, endereco, senha);
        dao.Bd.addCliente(cliente);
        return("Usuário cadastrado com sucesso!");
    }
}
