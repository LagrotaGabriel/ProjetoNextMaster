package bo;
import dao.Bd;
import model.cliente.Cliente;
import model.cliente.Endereco;
import model.conta.ContaCorrente;
import model.conta.ContaPoupanca;
import java.util.Random;

public class CadastroBo {

    public static void cadastrarUsuario(String nome, String cpf, String rg, String senha, String cidade,
                                        String estado, String bairro, String numeroRua, String rua, String cep,
                                        String email, String telefone, String tipoDeConta){



        Integer random = new Random().nextInt(111111,999999);
        Endereco endereco = new Endereco(cidade, estado, bairro, Integer.parseInt(numeroRua), rua, cep);
        Cliente cliente = new Cliente(nome, cpf, rg, endereco, senha, email, telefone);

        switch (tipoDeConta) {
            case "1" -> {
                ContaCorrente cc = new ContaCorrente(cliente, 1, random, 0.0f, 30.0f);
                idContaCorrenteIncrement(cc);
            }
            case "2" -> {
                ContaPoupanca cp = new ContaPoupanca(cliente, 1, random, 0.0f);
                idContaPoupancaIncrement(cp);
            }
            case "3" -> {
                ContaCorrente cc = new ContaCorrente(cliente, 1, random, 0.0f, 30.0f);
                random = new Random().nextInt(111111, 999999);
                ContaPoupanca cp = new ContaPoupanca(cliente, 1, random, 0.0f);
                idContaCorrenteIncrement(cc);
                idContaPoupancaIncrement(cp);
            }
        }

        idClienteIncrement(cliente);

    }

    // Incremento de ID CLIENTE
    public static void idClienteIncrement(Cliente cliente){
        Integer valMax = -999999999;
        if(dao.Bd.clientesMap.isEmpty()){
            dao.Bd.clientesMap.put(1, cliente);
        }else {
            for (Integer key : dao.Bd.clientesMap.keySet()) {
                if (key > valMax) {
                    valMax = key;
                }
            }
            dao.Bd.clientesMap.put(valMax+1, cliente);

        }
    }

    // Incremento de ID CONTA CORRENTE
    public static void idContaCorrenteIncrement(ContaCorrente contaCorrente){
        Integer valMax = -999999999;
        if(Bd.contaCorrentesMap.isEmpty()){
            Bd.contaCorrentesMap.put(1, contaCorrente);
        }else {
            for (Integer key : Bd.contaCorrentesMap.keySet()) {
                if (key > valMax) {
                    valMax = key;
                }
            }
            Bd.contaCorrentesMap.put(valMax+1, contaCorrente);
        }
    }

    // Incremento de ID CONTA POUPANCA
    public static void idContaPoupancaIncrement(ContaPoupanca contaPoupanca){
        Integer valMax = -999999999;
        if(Bd.contaPoupancasMap.isEmpty()){
            Bd.contaPoupancasMap.put(1, contaPoupanca);
        }else {
            for (Integer key : Bd.contaPoupancasMap.keySet()) {
                if (key > valMax) {
                    valMax = key;
                }
            }
            Bd.contaPoupancasMap.put(valMax+1, contaPoupanca);
        }
    }

}
