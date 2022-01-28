package bo;

// IMPORTAÇÕES
import dao.Bd;
import model.cliente.Cliente;
import model.cliente.Endereco;
import model.conta.ContaCorrente;
import model.conta.ContaPoupanca;
import java.util.Random;

public class CadastroBo {

    // MÉTODO QUE CADASTRA UM NOVO USUÁRIO
    public static void cadastrarUsuario(String nome, String cpf, String rg, String senha, String cidade,
                                        String estado, String bairro, String numeroRua, String rua, String cep,
                                        String email, String telefone, String tipoDeConta){

        // CRIA UM VALOR ALEATÓRIO PARA SER UTILIZADO NA CONTA DO CLIENTE ( DE 111111 ATÉ 999999)
        Integer random = new Random().nextInt(111111,999999);
        // INSTANCIA A CLASSE ENDEREÇO RECEBENDO AS INFORMAÇÕES DE CADASTRO DO USUÁRIO COMO PARÂMETROS
        Endereco endereco = new Endereco(cidade, estado, bairro, Integer.parseInt(numeroRua), rua, cep);
        // INSTANCIA A CLASSE CLIENTE RECEBENDO AS INFORMAÇÕES DE CADASTRO DO USUÁRIO COMO PARÂMETROS
        Cliente cliente = new Cliente(nome, cpf, rg, endereco, senha, email, telefone);
        // SWITCH QUE VARIA DE ACORDO COM O TIPO DE CONTA QUE O USUÁRIO DESEJA CRIAR
        // (1 - CONTA CORRENTE || 2 - CONTA POUPANÇA || 3 - CONTA CORRENTE E CONTA POUPANÇA)
        switch (tipoDeConta) {
            // SE O USUÁRIO QUER CRIAR SOMENTE UMA CONTA CORRENTE
            case "1" -> {
                // INSTANCIA UMA NOVA CONTA CORRENTE PARA O USUÁRIO
                ContaCorrente cc = new ContaCorrente(cliente, 1, random, 0.0f, 30.0f);
                idContaCorrenteIncrement(cc);
            }
            // SE O USUÁRIO QUER CRIAR SOMENTE UMA CONTA POUPANÇA
            case "2" -> {
                // INSTANCIA UMA NOVA CONTA POUPANÇA PARA O USUÁRIO
                ContaPoupanca cp = new ContaPoupanca(cliente, 1, random, 0.0f);
                idContaPoupancaIncrement(cp);
            }
            // SE O USUÁRIO QUER CRIAR UMA CONTA POUPANÇA E UMA CONTA CORRENTE
            case "3" -> {
                // INSTANCIA UMA NOVA CONTA CORRENTE PARA O USUÁRIO
                ContaCorrente cc = new ContaCorrente(cliente, 1, random, 0.0f, 30.0f);

                // CRIA UM NOVO VALOR ALEATÓRIO PARA SER UTILIZADO NA CONTA POUPANÇA
                random = new Random().nextInt(111111, 999999);

                // INSTANCIA UMA NOVA CONTA POUPANÇA PARA O USUÁRIO
                ContaPoupanca cp = new ContaPoupanca(cliente, 1, random, 0.0f);

                idContaCorrenteIncrement(cc);
                idContaPoupancaIncrement(cp);
            }
        }

        // INCREMENTA ID DO CLIENTE
        idClienteIncrement(cliente);
    }

    // MÉTODO QUE INCREMENTA ID DO CLIENTE
    public static void idClienteIncrement(Cliente cliente){

        // DECLARAÇÃO DE VARIÁVEIS
        Integer valMax = -999999999;
        // SE O BANCO DE DADOS DE CLIENTE ESTIVER VAZIO
        if(dao.Bd.clientesMap.isEmpty()){
            // INSERE O NOVO CLIENTE NO BANCO DE DADOS COM O ID 1
            dao.Bd.clientesMap.put(1, cliente);
        }
        // SE O BANCO DE DADOS JÁ POSSUIR ALGUM CLIENTE CADASTRADO
        else {
            // DETECTA ÚLTIMO CLIENTE CADASTRADO
            for (Integer key : dao.Bd.clientesMap.keySet()) {
                if (key > valMax) {
                    valMax = key;
                }
            }

            // INSERE NOVO CLIENTE NO BANCO DE DADOS COM ID INCREMENTADO
            dao.Bd.clientesMap.put(valMax+1, cliente);
        }
    }

    // MÉTODO QUE INCREMENTA ID DA CONTA CORRENTE
    public static void idContaCorrenteIncrement(ContaCorrente contaCorrente){

        // DECLARAÇÃO DE VARIÁVEIS
        Integer valMax = -999999999;
        // SE O BANCO DE DADOS DA CONTA CORRENTE ESTIVER VAZIO
        if(Bd.contaCorrentesMap.isEmpty()){
            // INSERE A NOVA CONTA CORRENTE NO BANCO DE DADOS COM O ID 1
            Bd.contaCorrentesMap.put(0, contaCorrente);
        }
        // SE O BANCO DE DADOS JÁ POSSUIR ALGUMA CONTA CORRENTE CADASTRADO
        else {
            // DETECTA ÚLTIMA CONTA CORRENTE CADASTRADA
            for (Integer key : Bd.contaCorrentesMap.keySet()) {
                if (key > valMax) {
                    valMax = key;
                }
            }

            // INSERE NOVA CONTA CORRENTE NO BANCO DE DADOS COM ID INCREMENTADO
            Bd.contaCorrentesMap.put(valMax+1, contaCorrente);
        }
    }

    // Incremento de ID CONTA POUPANCA
    public static void idContaPoupancaIncrement(ContaPoupanca contaPoupanca){

        // DECLARAÇÃO DE VARIÁVEIS
        Integer valMax = -999999999;
        // SE O BANCO DE DADOS DA CONTA POUPANÇA ESTIVER VAZIO
        if(Bd.contaPoupancasMap.isEmpty()){
            // INSERE A NOVA CONTA POUPANÇA NO BANCO DE DADOS COM O ID 1
            Bd.contaPoupancasMap.put(0, contaPoupanca);
        }
        // SE O BANCO DE DADOS JÁ POSSUIR ALGUMA CONTA POUPANÇA CADASTRADA
        else {
            // DETECTA ÚLTIMA CONTA CORRENTE POUPANÇA
            for (Integer key : Bd.contaPoupancasMap.keySet()) {
                if (key > valMax) {
                    valMax = key;
                }
            }
            // INSERE NOVA CONTA POUPANÇA NO BANCO DE DADOS COM ID INCREMENTADO
            Bd.contaPoupancasMap.put(valMax+1, contaPoupanca);
        }
    }

}
