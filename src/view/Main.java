package view;
import dao.Bd;
import model.conta.Conta;
import model.conta.ContaPoupanca;
import model.conta.ContaTipo;
import util.Layout;

import java.util.Objects;
import java.util.Scanner;
import model.cliente.Cliente;
import controller.*;
import model.cliente.Endereco;

public class Main {

    // Instâncias
    public static Layout layout = new Layout(5, 10);
    public static Scanner input = new Scanner(System.in);
    public static Cadastro cadastro = new Cadastro();
    public static Bd bd = new Bd();

    public static void main(String[] args) {

        //menuAcesso();

        Cadastro.cadastrarUsuario("Gabriel", "47153427821", "558468263",
                "1234", "São Paulo", "SP", "Lauzane", "583",
                "Avenida Coronel Manuel Py", "02442090", "3");

        menuLogin();

        // menuCadastro();

        //menuContas();
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
        System.out.println("\n           =-=-=-=-=-=Login de usuário=-=-=-=-=-=");
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
            menuContas();
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
        layout.TopLine(3);
        String tipoDeConta = entry("  Escolha: ");
        layout.BottomLine(3);
        layout.br(1);
        Cadastro.cadastrarUsuario(nome, cpf, rg, senha, cidade, estado, bairro, numeroRua, rua, cep, tipoDeConta);
        layout.Loading();
        layout.LimparTela();
        menuAcesso();

    }

    // Menu Contas
    public static void menuContas(){

        String n = "";

        layout.TopLine(2);
        layout.br(1);
        System.out.println("    =-=-=-=-=-=Suas contas=-=-=-=-=-=");

        layout.BottomLine(2);
        layout.br(1);
        layout.TopLine(2);
        layout.br(1);

        // DUAS CONTAS
        if(Bd.contaPoupancas.size() != 0 && Bd.contaCorrentes.size() != 0){

            while(!n.equals("1") && !n.equals("2") && !n.equals("3")) {
                System.out.println("    [1] Conta corrente n° " + Bd.clienteBuscaContaCorrente.getConta());
                System.out.println("    [2] Conta poupança n° " + Bd.clienteBuscaContaPoupanca.getConta());
                System.out.println("    [3] Logout");
                layout.BottomLine(2);
                layout.br(1);
                layout.TopLine(2);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.BottomLine(2);
                layout.br(1);
                layout.Loading();
                layout.LimparTela();
                if(n.equals("1")){
                    menuPrincipal("1");
                }else if(n.equals("2")){
                    menuPrincipal("2");
                }else{
                    menuAcesso();
                }
            }
        }

        // CONTA POUPANÇA
        else if(Bd.contaPoupancas.size() != 0){
            while(!n.equals("1") && !n.equals("2")) {
                System.out.println("    [1] Conta poupança n° " + Bd.clienteBuscaContaPoupanca.getConta());
                System.out.println("    [2] Logout");
                layout.BottomLine(2);
                layout.br(1);
                layout.TopLine(2);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.BottomLine(2);
                layout.br(1);
                layout.Loading();
                layout.LimparTela();
                if(n.equals("1")){
                    menuPrincipal("2");
                }else{
                    menuAcesso();
                }
            }

        }

        // CONTA CORRENTE
        else if(Bd.contaCorrentes.size() != 0){
            while(!n.equals("1") && !n.equals("2")) {
                System.out.println("    [1] Conta corrente n° " + Bd.clienteBuscaContaCorrente.getConta());
                System.out.println("    [2] Logout");
                layout.BottomLine(2);
                layout.br(1);
                layout.TopLine(2);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.BottomLine(2);
                layout.br(1);
                layout.Loading();
                layout.LimparTela();
                if(n.equals("1")){
                    menuPrincipal("1");
                }else{
                    menuAcesso();
                }
            }
        }

    }

    // Menu Principal
    // Parametro String 1 - CONTA CORRENTE , String 2 - CONTA POUPANÇA
    public static void menuPrincipal(String ct){

        String n = "";

        // Conta corrente
        if(ct.equals("1")){

            while(!n.equals("1") && !n.equals("2") && !n.equals("3") && !n.equals("4") && !n.equals("5") &&
                    !n.equals("6")) {

                layout.TopLine(3);
                layout.br(1);

                System.out.println("        =-=-=-=-=-=Conta corrente de " +
                        Bd.clienteBuscaContaCorrente.getCliente().getNome() + "=-=-=-=-=-=");

                layout.BottomLine(3);
                layout.br(1);
                layout.TopLine(3);
                layout.br(1);

                // Informações da conta
                System.out.print("  ");
                System.out.print("AG: " + Bd.clienteBuscaContaCorrente.getAgencia());
                System.out.print(" || CT: " + Bd.clienteBuscaContaCorrente.getConta());
                System.out.print(" || Tipo: " + Bd.clienteBuscaContaCorrente.getContaTipo());
                // Informações do saldo
                System.out.print(" || Saldo: ");
                System.out.printf("R$ %.2f", Bd.clienteBuscaContaCorrente.getSaldo());

                layout.br(1);
                layout.CentralLine(3);
                layout.br(1);

                // Opções do menu
                System.out.println("    [1] Depositar         [2] Sacar          [3] Transferir");
                System.out.println("    [4] Menu Pix          [5] Menu Cartões   [6] Voltar");
                layout.BottomLine(3);
                layout.br(1);

                // Entrada do usuário
                layout.TopLine(3);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.BottomLine(3);
                layout.br(1);

                layout.Loading();
                layout.LimparTela();

                // Direcionamento de opções

                // Depósito
                if(n.equals("1")) {
                    Bd.clienteBuscaContaCorrente.Deposito(menuDeposito());
                    layout.BottomLine(2);
                    layout.br(1);
                    layout.Loading();
                    layout.LimparTela();
                    n = "";
                }

                // Saque
                else if (n.equals("2")) {
                    Bd.clienteBuscaContaCorrente.Saque(menuSaque());
                    layout.BottomLine(2);
                    layout.br(1);
                    layout.Loading();
                    layout.LimparTela();
                    n = "";
                }

                // Transferência
                else if (n.equals("3")) {
                    System.out.println("");
                }

                // Menu Pix
                else if (n.equals("4")) {
                    System.out.println("");
                }

                // Menu Cartões
                else if (n.equals("5")) {
                    System.out.println("");
                }

                // Voltar
                else if (n.equals("6")) {
                    menuContas();
                }

                // Entrada inválida
                else {
                    System.out.println("Entrada incorreta!");
                }

                // Atualizando tipo de conta

                // CONTA COMUM
                if(Bd.clienteBuscaContaCorrente.getSaldo() < 5000){
                    Bd.clienteBuscaContaCorrente.setContaTipo(ContaTipo.COMUM);
                }
                // CONTA PREMIUM
                else if(Bd.clienteBuscaContaCorrente.getSaldo() > 5000 && Bd.clienteBuscaContaCorrente.getSaldo() < 10000){
                    Bd.clienteBuscaContaCorrente.setContaTipo(ContaTipo.PREMIUM);
                }
                // CONTA SUPER
                else{
                    Bd.clienteBuscaContaCorrente.setContaTipo(ContaTipo.SUPER);
                }

            }

        }

        // Conta poupança
        else if(ct.equals("2")){

            while(!n.equals("1") && !n.equals("2") && !n.equals("3") && !n.equals("4") && !n.equals("5") &&
                    !n.equals("6")) {

                layout.TopLine(3);
                layout.br(1);

                System.out.println("        =-=-=-=-=-=Conta poupança de " +
                        Bd.clienteBuscaContaPoupanca.getCliente().getNome() + "=-=-=-=-=-=");

                layout.BottomLine(3);
                layout.br(1);
                layout.TopLine(3);
                layout.br(1);

                // Informações da conta
                System.out.print("  ");
                System.out.print("AG: " + Bd.clienteBuscaContaPoupanca.getAgencia());
                System.out.print(" || Conta: " + Bd.clienteBuscaContaPoupanca.getConta());
                System.out.print(" || Tipo: " + Bd.clienteBuscaContaPoupanca.getContaTipo());
                // Informações do saldo
                System.out.print(" || Saldo: ");
                System.out.printf("R$ %.2f", Bd.clienteBuscaContaPoupanca.getSaldo());

                layout.br(1);
                layout.CentralLine(3);
                layout.br(1);

                // Opções do menu
                System.out.println("    [1] Depositar         [2] Sacar          [3] Transferir");
                System.out.println("    [4] Menu Pix          [5] Menu Cartões   [6] Voltar");
                layout.BottomLine(3);
                layout.br(1);

                // Entrada do usuário
                layout.TopLine(3);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.BottomLine(3);
                layout.br(1);

                layout.Loading();
                layout.LimparTela();

                // Direcionamento de opções

                // Depósito
                if (n.equals("1")) {
                    Bd.clienteBuscaContaPoupanca.Deposito(menuDeposito());
                    layout.BottomLine(2);
                    layout.br(1);
                    layout.Loading();
                    layout.LimparTela();
                    n = "";
                }

                // Saque
                else if (n.equals("2")) {
                    Bd.clienteBuscaContaPoupanca.Saque(menuSaque());
                    layout.BottomLine(2);
                    layout.br(1);
                    layout.Loading();
                    layout.LimparTela();
                    n = "";
                }

                // Transferir
                else if (n.equals("3")) {
                    System.out.println("");
                }

                // Menu Pix
                else if (n.equals("4")) {
                    System.out.println("");
                }

                // Menu Cartões
                else if (n.equals("5")) {
                    System.out.println("");
                }

                // Voltar
                else if (n.equals("6")) {
                    menuContas();
                }

                // Entrada incorreta
                else {
                    System.out.println("Entrada incorreta!");
                }

                // Atualizando tipo de conta

                // CONTA COMUM
                if(Bd.clienteBuscaContaPoupanca.getSaldo() < 5000){
                    Bd.clienteBuscaContaPoupanca.setContaTipo(ContaTipo.COMUM);
                }
                // CONTA PREMIUM
                else if(Bd.clienteBuscaContaPoupanca.getSaldo() > 5000 &&
                        Bd.clienteBuscaContaPoupanca.getSaldo() < 10000){
                    Bd.clienteBuscaContaPoupanca.setContaTipo(ContaTipo.PREMIUM);
                }
                // CONTA SUPER
                else{
                    Bd.clienteBuscaContaPoupanca.setContaTipo(ContaTipo.SUPER);
                }

            }

        }

    }

    public static Float menuDeposito(){

        layout.TopLine(2);
        layout.br(1);

        System.out.println("      =-=-=-=-=-=Depósito=-=-=-=-=-=");

        layout.BottomLine(2);
        layout.br(1);
        layout.TopLine(2);
        layout.br(1);
        System.out.println("  [Digite 0 para sair]");
        return(Float.valueOf(entry("  Digite o valor do depósito: R$ ")));

    }

    public static Float menuSaque(){

        layout.TopLine(2);
        layout.br(1);

        System.out.println("         =-=-=-=-=-=Saque=-=-=-=-=-=");

        layout.BottomLine(2);
        layout.br(1);
        layout.TopLine(2);
        layout.br(1);
        System.out.println("  [Digite 0 para sair]");
        return(Float.valueOf(entry("  Digite o valor do saque: R$ ")));

    }



    public static String entry(String texto) {
        System.out.print(texto);
        return input.next();
    }








}
