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
        //menuLogin();
    }

    // Menu de acesso
    private static void menuAcesso() {
        Integer choice;

        do {
            layout.TopLine(3);
            System.out.println("\n    =-=-=-=-=-=Seja bem-vindo(a) ao banco Next!=-=-=-=-=-=");
            layout.CentralLine(3);
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

        layout.CentralLine(3);
        layout.br(1);
        layout.TopLine(3);
        layout.br(1);
        entry("Digite seu CPF: ");
        entry("Digite sua senha: ");
    }

    // Menu de cadastro
    private static void menuCadastro(){

        layout.TopLine(3);

        System.out.println("\n         =-=-=-=-=-=Cadastro de usuário=-=-=-=-=-=");

        layout.CentralLine(3);
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
        System.out.println(Cadastro.cadastrarUsuario(nome, cpf, rg, senha, cidade, estado, bairro, numeroRua, rua, cep));
        layout.Loading();
        layout.LimparTela();
        //System.out.println(dao.Bd.clientes);
    }

    public static String entry(String texto) {
        System.out.print(texto);
        return input.nextLine();
    }








}
