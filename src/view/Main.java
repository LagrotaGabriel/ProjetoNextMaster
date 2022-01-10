package view;
import dao.Bd;
import model.conta.ContaTipo;
import util.Layout;
import java.util.Scanner;
import controller.*;

public class Main {

    // Instâncias
    public static Layout layout = new Layout(5, 5);
    public static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {

       //menuAcesso();

        Cadastro.cadastrarUsuario("Gabriel", "47153427821", "558468263",
                "1234", "São Paulo", "SP", "Lauzane", "583",
                "Avenida Coronel Manuel Py", "02442090", "gabriellagrota23@gmail.com", "97981-5415", "3");

        Cadastro.cadastrarUsuario("Henrique", "123456789012", "558468263",
                "1", "São Paulo", "SP", "Lauzane", "583",
                "Avenida Coronel Manuel Py", "02442090", "email@gmail.com", "961329075", "2");

        Cadastro.cadastrarUsuario("Afonso", "210987654321", "558468263",
                "2", "São Paulo", "SP", "Lauzane", "583",
                "Avenida Coronel Manuel Py", "02442090", "blablabla@foursys.com", "992844948", "1");

        menuLogin();

        //menuPix(1);

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
            layout.Loading(3);
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
        layout.Loading(3);
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
        String email = entry("  Digite seu Email: ");
        String telefone = entry("  Digite seu Telefone: ");
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
        System.out.println("              Digite o tipo de conta desejado: ");
        System.out.println("         [1] Corrente     [2] Poupança     [3] Ambas");
        layout.BottomLine(3);
        layout.br(1);
        layout.TopLine(3);
        layout.br(1);
        String tipoDeConta = entry("  Escolha: ");
        layout.BottomLine(3);
        layout.br(1);
        Cadastro.cadastrarUsuario(nome, cpf, rg, senha, cidade, estado, bairro, numeroRua, rua, cep, email, telefone,
                tipoDeConta);
        layout.Loading(3);
        layout.LimparTela();
        System.out.println("Você foi cadastrado com sucesso!");
        if(Bd.contaCorrentesMap.size() > 0 && Bd.contaPoupancasMap.size() > 0){
            System.out.println("O número gerado para sua conta corrente é: " + Bd.contaCorrentesMap.get((Bd.contaCorrentesMap.size()-1)).getConta());
            System.out.println("O número gerado para sua conta poupança é: " + Bd.contaPoupancasMap.get((Bd.contaPoupancasMap.size()-1)).getConta());
        }else if(Bd.contaCorrentesMap.size() > 0){
            System.out.println("O número gerado para sua conta corrente é: " + Bd.contaCorrentesMap.get((Bd.contaCorrentesMap.size()-1)).getConta());
        }else if(Bd.contaPoupancasMap.size() > 0){
            System.out.println("O número gerado para sua conta corrente é: " + Bd.contaPoupancasMap.get((Bd.contaPoupancasMap.size()-1)).getConta());
        }
        menuLogin();
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
        if(!Bd.contaPoupancasMap.isEmpty() && !Bd.contaCorrentesMap.isEmpty()){

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
                layout.Loading(2);
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
        else if(!Bd.contaPoupancasMap.isEmpty()){
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
                layout.Loading(2);
                layout.LimparTela();
                if(n.equals("1")){
                    menuPrincipal("2");
                }else{
                    menuAcesso();
                }
            }

        }

        // CONTA CORRENTE
        else if(!Bd.contaCorrentesMap.isEmpty()){
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
                layout.Loading(2);
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

        String n = "-1";

        // Conta corrente
        if(Integer.parseInt(ct) == 1){

            while(Integer.parseInt(n) < 1 || Integer.parseInt(n) > 6) {

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
                System.out.println("      [1] Depositar      [2] Sacar          [3] Transferir");
                System.out.println("      [4] Menu Pix       [5] Consulta       [6] Voltar");
                layout.BottomLine(3);
                layout.br(1);

                // Entrada do usuário
                layout.TopLine(3);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.BottomLine(3);
                layout.br(1);

                layout.Loading(3);
                layout.LimparTela();

                // Direcionamento de opções

                // Depósito
                if(Integer.parseInt(n) == 1) {
                    Bd.clienteBuscaContaCorrente.Deposito(menuDeposito());
                    layout.BottomLine(2);
                    layout.br(1);
                    layout.Loading(2);
                    layout.LimparTela();
                    n = "";
                }

                // Saque
                else if (Integer.parseInt(n) == 2) {
                    System.out.println(Bd.clienteBuscaContaCorrente.Saque(menuSaque()));
                    layout.BottomLine(2);
                    layout.br(1);
                    layout.Loading(2);
                    layout.LimparTela();
                    n = "";
                }

                // Transferência
                else if (Integer.parseInt(n) == 3) {
                    System.out.println("");
                }

                // Menu Pix
                else if (Integer.parseInt(n) == 4) {
                    menuPix(1);
                }

                // Menu Cartões
                else if (Integer.parseInt(n) == 5) {
                    System.out.println("");
                }

                // Voltar
                else if (Integer.parseInt(n) == 6) {
                    menuContas();
                }

                // Entrada inválida
                else {
                    System.out.println("Entrada incorreta!");
                }

                // Desconto da taxa de manutenção
                System.out.println("- " + Bd.clienteBuscaContaCorrente.descontarTaxa());

                // Atualizando tipo de conta

                // CONTA COMUM
                if(Bd.clienteBuscaContaCorrente.getSaldo() < 5000){
                    Bd.clienteBuscaContaCorrente.setContaTipo(ContaTipo.COMUM);
                }
                // CONTA PREMIUM
                else if(Bd.clienteBuscaContaCorrente.getSaldo() >= 5000 && Bd.clienteBuscaContaCorrente.getSaldo() < 10000){
                    Bd.clienteBuscaContaCorrente.setContaTipo(ContaTipo.PREMIUM);
                }
                // CONTA SUPER
                else{
                    Bd.clienteBuscaContaCorrente.setContaTipo(ContaTipo.SUPER);
                }

            }

        }

        // Conta poupança
        else if(Integer.parseInt(ct) == 2){

            while(Integer.parseInt(n) < 1 || Integer.parseInt(n) > 6) {

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
                System.out.println("      [1] Depositar      [2] Sacar          [3] Transferir");
                System.out.println("      [4] Menu Pix       [5] Consulta       [6] Voltar");
                layout.BottomLine(3);
                layout.br(1);

                // Entrada do usuário
                layout.TopLine(3);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.BottomLine(3);
                layout.br(1);

                layout.Loading(3);
                layout.LimparTela();

                // Direcionamento de opções

                // Depósito
                if (Integer.parseInt(n) == 1) {
                    Bd.clienteBuscaContaPoupanca.Deposito(menuDeposito());
                    layout.BottomLine(2);
                    layout.br(1);
                    layout.Loading(2);
                    layout.LimparTela();
                    n = "";
                }

                // Saque
                else if (Integer.parseInt(n) == 2) {
                    System.out.println(Bd.clienteBuscaContaPoupanca.Saque(menuSaque()));
                    layout.BottomLine(2);
                    layout.br(1);
                    layout.Loading(2);
                    layout.LimparTela();
                    n = "";
                }

                // Transferir
                else if (Integer.parseInt(n) == 3) {
                    System.out.println("");
                }

                // Menu Pix
                else if (Integer.parseInt(n) == 4) {
                    System.out.println("");
                }

                // Menu Cartões
                else if (Integer.parseInt(n) == 5) {
                    System.out.println("");
                }

                // Voltar
                else if (Integer.parseInt(n) == 6) {
                    menuContas();
                }

                // Entrada incorreta
                else {
                    System.out.println("Entrada incorreta!");
                }

                // Desconto da taxa de manutenção
                System.out.println("- " + Bd.clienteBuscaContaPoupanca.acrescentarRendimento());

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

    public static void menuPix(Integer contaTipo){

        String n = "-1";

        // Layout
        layout.TopLine(3);
        layout.br(1);
        System.out.println("                   =-=-= Menu PIX=-=-=");
        layout.BottomLine(3);
        layout.br(1);

        // Opções
        layout.TopLine(3);
        layout.br(1);
        System.out.println("    [1] Cadastrar      [2] Apagar chave      [3] Transferir");
        System.out.println("    [4] Histórico      [5] Voltar");
        layout.BottomLine(3);
        layout.br(1);

        // Repetição
        while(Integer.parseInt(n) < 0 || Integer.parseInt(n) > 5) {

            // Layout
            layout.TopLine(3);
            layout.br(1);
            n = entry("    Escolha: ");
            layout.BottomLine(3);
            layout.br(1);

            // Cadastrar PIX
            if(Integer.parseInt(n) == 1){
                menuCadastroPix(contaTipo);
                menuPix(contaTipo);
            }
            // Apagar PIX
            else if(Integer.parseInt(n) == 2){
                menuApagaPix();
            }
            // Transferir PIX
            else if(Integer.parseInt(n) == 3){
                menuTransferePix();
            }
            // Histórico PIX
            else if(Integer.parseInt(n) == 4){
                menuHistoricoPix();
            }else{
                menuPix(contaTipo);
            }

        }
    }

    // Menu de cadastro de Pix
    public static void menuCadastroPix(Integer contaTipo){

        String n = "-1";

        // Interface
        layout.TopLine(3);
        layout.br(1);
        System.out.println("              =-=-= Menu cadastro PIX=-=-=");
        layout.BottomLine(3);
        layout.br(1);

        // Opções
        layout.TopLine(3);
        layout.br(1);
        System.out.println("        [1] CPF      [2] Email      [3] Telefone");
        System.out.println("        [4] Voltar");
        layout.BottomLine(3);
        layout.br(1);

        // Repetição
        while(Integer.parseInt(n) < 0 || Integer.parseInt(n) > 4) {

            String chave;

            // Layout
            layout.TopLine(3);
            layout.br(1);
            n = entry("    Escolha: ");
            layout.BottomLine(3);
            layout.br(1);
            layout.Loading(3);
            layout.LimparTela();

            // CPF
            if(Integer.parseInt(n) == 1){

                // CONTA CORRENTE
                if(contaTipo == 1) {
                    Bd.insereChavePixCpf(Bd.clienteBuscaContaCorrente.getCliente().getCpf());
                }
                // CONTA POUPANÇA
                else if(contaTipo == 2){
                    Bd.insereChavePixCpf(Bd.clienteBuscaContaPoupanca.getCliente().getCpf());
                }

            }

            // EMAIL
            else if(Integer.parseInt(n) == 2){

                // CONTA CORRENTE
                if(contaTipo == 1) {
                    Bd.insereChavePixEmail(Bd.clienteBuscaContaCorrente.getCliente().getEmail());
                }
                // CONTA POUPANÇA
                else if(contaTipo == 2){
                    Bd.insereChavePixEmail(Bd.clienteBuscaContaPoupanca.getCliente().getEmail());
                }

            }

            // TELEFONE
            else if(Integer.parseInt(n) == 3){

                // CONTA CORRENTE
                if(contaTipo == 1) {
                    Bd.insereChavePixTelefone(Bd.clienteBuscaContaCorrente.getCliente().getTelefone());
                }
                // CONTA POUPANÇA
                else if(contaTipo == 2){
                    Bd.insereChavePixTelefone(Bd.clienteBuscaContaPoupanca.getCliente().getTelefone());
                }

            }

            // VOLTAR
            else{
                menuPix(contaTipo);
            }
        }
    }

    public static void menuApagaPix(){

    }

    public static void menuTransferePix(){

    }

    public static void menuHistoricoPix(){

    }




    public static String entry(String texto) {
        System.out.print(texto);
        return input.next();
    }








}
