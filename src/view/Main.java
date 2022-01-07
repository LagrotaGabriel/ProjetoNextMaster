package view;
import dao.Bd;
import model.conta.Conta;
import model.conta.ContaPoupanca;
import util.Layout;
import java.util.Scanner;
import model.cliente.Cliente;
import controller.*;
import model.cliente.Endereco;

public class Main {

    // Instâncias
    public static Layout layout = new Layout(10, 20);
    public static Scanner input = new Scanner(System.in);
    public static Cadastro cadastro = new Cadastro();
    public static Bd bd = new Bd();

    public static void main(String[] args) {

        menuAcesso();

        /*Cadastro.cadastrarUsuario("Gabriel", "47153427821", "558468263",
                "1234", "São Paulo", "SP", "Lauzane", "583",
                "Avenida Coronel Manuel Py", "02442090", "1");*/

        //menuLogin();

        // menuCadastro();

        //menuPrincipal();
    }

    // Menu de acesso
    private static void menuAcesso() {
        Integer choice;

        do {
            layout.TopLine(3);
            System.out.println("\n    =-=-=-=-=-=Seja bem-vindo(a) ao banco Next!=-=-=-=-=-=");
            layout.BottomLine(3);
            layout.br(1);
            System.out.println("           [1] Login     [2] Cadastro     [3] Sair");
            layout.BottomLine(3);
            layout.br(1);
            System.out.print("           Escolha: ");
            choice = input.nextInt();
            layout.Loading();
            layout.LimparTela();
            if(choice == 1){
                menuLogin();
            }else if(choice == 2){
                menuCadastro();
            }else if(choice == 3){
                System.exit(0);
            }else{
                System.out.println("Erro: Entrada incorreta. Digite um valor entre [1 e 3]");
            }
        }while(choice < 1 || choice > 3);
    }

    // Menu de login
    private static void menuLogin(){

        layout.TopLine(3);
        System.out.println("\n          =-=-=-=-=-=Login de usuário=-=-=-=-=-=");
        layout.BottomLine(3);
        layout.br(1);
        layout.TopLine(3);
        layout.br(1);
        String loginCpf = entry("  Digite seu CPF: ");
        String loginSenha = entry("  Digite sua senha: ");
        Login login = new Login(loginCpf, loginSenha);
        System.out.println(login.Acessar());
        layout.BottomLine(3);
        layout.br(1);
        layout.Loading();
        layout.LimparTela();
        if(!login.isAtivo()){
            menuLogin();
        }else{
            menuPrincipal();
        }

    }

    // Menu de cadastro
    private static void menuCadastro(){

        layout.TopLine(3);

        System.out.println("\n         =-=-=-=-=-=Cadastro de usuário=-=-=-=-=-=");

        layout.BottomLine(3);
        layout.br(1);
        layout.TopLine(3);
        layout.br(1);

        String nome = entry("  Digite seu nome: ");

        String cpf = entry("  Digite seu CPF: ");
        String rg = entry("  Digite seu RG: ");
        String senha = entry("  Digite a sua senha: ");
        String estado = entry("  Digite o seu estado: ");
        String cidade = entry("  Digite a sua cidade: ");
        String bairro = entry("  Digite o nome do seu bairro: ");
        String rua = entry("  Digite o nome da sua rua: ");
        String numeroRua = entry("  Digite o número da casa: ");
        String cep = entry("  Digite o CEP: ");
        layout.BottomLine(3);
        layout.br(1);
        layout.TopLine(3);
        layout.br(1);
        System.out.println("  Digite o tipo de conta desejado: ");
        System.out.println("  [1] Corrente     [2] Poupança");
        layout.BottomLine(3);
        layout.br(1);
        layout.TopLine(1);
        layout.br(3);
        String tipoDeConta = entry("  Escolha: ");
        layout.BottomLine(3);
        layout.br(1);
        Cadastro.cadastrarUsuario(nome, cpf, rg, senha, cidade, estado, bairro, numeroRua, rua, cep, tipoDeConta);
        layout.Loading();
        layout.LimparTela();
        menuAcesso();

    }

    // Menu Principal
    public static void menuPrincipal(){

        layout.TopLine(3);
        layout.br(1);
        System.out.println("              =-=-=-=-=-=Banco Next=-=-=-=-=-=");
        System.out.println("    Agencia: " + Bd.clienteBuscaContaCorrente.getAgencia() +
                "  Conta: " + Bd.clienteBuscaContaCorrente.getConta() + "  Saldo: R$ " +
                Bd.clienteBuscaContaCorrente.getSaldo());

        layout.BottomLine(3);
        layout.br(1);
        layout.TopLine(3);
        layout.br(1);
        System.out.println("           [1]     [2]      [3] Sair");
        System.out.println("           [2] Login     [2] Cadastro     [3] Sair");
        layout.BottomLine(3);

    }

    public static String entry(String texto) {
        System.out.print(texto);
        return input.next();
    }
    public static String entryComposto(String texto) {
        System.out.print(texto);
        return input.nextLine();
    }








}
