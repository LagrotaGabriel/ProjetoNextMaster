package view;
import bo.*;
import dao.Bd;
import model.conta.ContaTipo;
import util.Layout;
import java.util.Scanner;

public class Main {

    // Instâncias
    public static Layout layout = new Layout(5, 5);
    public static Scanner input = new Scanner(System.in);
    public static LoginBo login = new LoginBo("", "");

    // Método main
    public static void main(String[] args) {

       //menuAcesso();

        CadastroBo.cadastrarUsuario("Gabriel", "47153427821", "558468263",
                "1234", "São Paulo", "SP", "Lauzane", "583",
                "Avenida Coronel Manuel Py", "02442090", "gabriellagrota23@gmail.com", "979815415", "3");

        CadastroBo.cadastrarUsuario("Henrique", "123456789012", "558468263",
                "1", "São Paulo", "SP", "Lauzane", "583",
                "Avenida Coronel Manuel Py", "02442090", "email@gmail.com", "961329075", "2");

        CadastroBo.cadastrarUsuario("Afonso", "210987654321", "558468263",
                "2", "São Paulo", "SP", "Lauzane", "583",
                "Avenida Coronel Manuel Py", "02442090", "blablabla@foursys.com", "992844948", "1");

        menuLogin();

    }

    /* -------------------------------------- INÍCIO - MENU ACESSO --------------------------------------------- */
    // Menu de acesso
    private static void menuAcesso() {
        int choice;

        do {
            layout.topLine(3);
            System.out.println("\n    =-=-=-=-=-=Seja bem-vindo(a) ao banco Next!=-=-=-=-=-=");
            layout.bottomLine(3);
            layout.br(1);
            System.out.println("           [1] Login     [2] Cadastro     [3] Sair");
            layout.bottomLine(3);
            layout.br(1);
            System.out.print("           Escolha: ");
            choice = input.nextInt();
            layout.loading(3);
            layout.limparTela();
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

        layout.topLine(3);
        System.out.println("\n           =-=-=-=-=-=Login de usuário=-=-=-=-=-=");
        layout.bottomLine(3);
        layout.br(1);
        layout.topLine(3);
        layout.br(1);
        String loginCpf = entry("  Digite seu CPF: ");
        String loginSenha = entry("  Digite sua senha: ");
        login = new LoginBo(loginCpf, loginSenha);
        System.out.println(login.Acessar());
        layout.bottomLine(3);
        layout.br(1);
        layout.loading(3);
        layout.limparTela();
        if(!login.isAtivo()){
            menuLogin();
        }else{
            menuContas();
        }

    }

    // Menu de cadastro
    private static void menuCadastro(){

        layout.topLine(3);

        System.out.println("\n         =-=-=-=-=-=Cadastro de usuário=-=-=-=-=-=");

        layout.bottomLine(3);
        layout.br(1);
        layout.topLine(3);
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
        layout.bottomLine(3);
        layout.br(1);
        layout.topLine(3);
        layout.br(1);
        System.out.println("              Digite o tipo de conta desejado: ");
        System.out.println("         [1] Corrente     [2] Poupança     [3] Ambas");
        layout.bottomLine(3);
        layout.br(1);
        layout.topLine(3);
        layout.br(1);
        String tipoDeConta = entry("  Escolha: ");
        layout.bottomLine(3);
        layout.br(1);
        CadastroBo.cadastrarUsuario(nome, cpf, rg, senha, cidade, estado, bairro, numeroRua, rua, cep, email, telefone,
                tipoDeConta);
        layout.loading(3);
        layout.limparTela();
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

        String n = "0";

        layout.topLine(2);
        layout.br(1);
        System.out.println("    =-=-=-=-=-=Suas contas=-=-=-=-=-=");

        layout.bottomLine(2);
        layout.br(1);
        layout.topLine(2);
        layout.br(1);

        // DUAS CONTAS
        if(Bd.clienteBuscaContaCorrente != null && Bd.clienteBuscaContaPoupanca != null){

            while(Integer.parseInt(n) < 1 || Integer.parseInt(n) > 3) {
                System.out.println("    [1] Conta corrente n° " + Bd.clienteBuscaContaCorrente.getConta());
                System.out.println("    [2] Conta poupança n° " + Bd.clienteBuscaContaPoupanca.getConta());
                System.out.println("    [3] Logout");
                layout.bottomLine(2);
                layout.br(1);
                layout.topLine(2);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.bottomLine(2);
                layout.br(1);
                layout.loading(2);
                layout.limparTela();
                if(n.equals("1")){
                    menuPrincipal("1");
                }else if(n.equals("2")){
                    menuPrincipal("2");
                }else{
                    Bd.zerarInstancias();
                    login = null;
                    menuAcesso();
                }
            }
        }

        // CONTA POUPANÇA
        else if(Bd.clienteBuscaContaCorrente == null){
            while(Integer.parseInt(n) < 1 || Integer.parseInt(n) > 2) {
                System.out.println("    [1] Conta poupança n° " + Bd.clienteBuscaContaPoupanca.getConta());
                System.out.println("    [2] Logout");
                layout.bottomLine(2);
                layout.br(1);
                layout.topLine(2);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.bottomLine(2);
                layout.br(1);
                layout.loading(2);
                layout.limparTela();
                if(n.equals("1")){
                    menuPrincipal("2");
                }else{
                    login.setAtivo(false);
                    Bd.zerarInstancias();
                    login = null;
                    menuAcesso();
                }
            }

        }

        // CONTA CORRENTE
        else {
            while(Integer.parseInt(n) < 1 || Integer.parseInt(n) > 2) {
                System.out.println("    [1] Conta corrente n° " + Bd.clienteBuscaContaCorrente.getConta());
                System.out.println("    [2] Logout");
                layout.bottomLine(2);
                layout.br(1);
                layout.topLine(2);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.bottomLine(2);
                layout.br(1);
                layout.loading(2);
                layout.limparTela();
                if(n.equals("1")){
                    menuPrincipal("1");
                }else{
                    login.setAtivo(false);
                    Bd.zerarInstancias();
                    login = null;
                    menuAcesso();
                }
            }
        }

    }
    /* --------------------------------------- FIM - MENU ACESSO --------------------------------------------- */

    /* ----------------------------------- INÍCIO - MENU PRINCIPAL ------------------------------------------ */
    // Menu Principal
    public static void menuPrincipal(String ct){

        String n = "0";

        // Conta corrente
        if(Integer.parseInt(ct) == 1){

            while(Integer.parseInt(n) < 1 || Integer.parseInt(n) > 6) {

                layout.topLine(3);
                layout.br(1);

                System.out.println("        =-=-=-=-=-=Conta corrente de " +
                        Bd.clienteBuscaContaCorrente.getCliente().getNome() + "=-=-=-=-=-=");

                layout.bottomLine(3);
                layout.br(1);
                layout.topLine(3);
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
                layout.centralLine(3);
                layout.br(1);

                // Opções do menu
                System.out.println("      [1] Depositar      [2] Sacar          [3] Cartões");
                System.out.println("      [4] Menu Pix       [5] Consulta       [6] Voltar");
                layout.bottomLine(3);
                layout.br(1);

                // Entrada do usuário
                layout.topLine(3);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.bottomLine(3);
                layout.br(1);

                layout.loading(3);
                layout.limparTela();

                // Direcionamento de opções

                // Depósito
                if(Integer.parseInt(n) == 1) {
                    ContaCorrenteBo.Deposito(menuDeposito());
                    layout.bottomLine(2);
                    layout.br(1);
                    layout.loading(2);
                    layout.limparTela();
                    n = "0";
                }

                // Saque
                else if (Integer.parseInt(n) == 2) {
                    System.out.println(ContaCorrenteBo.Saque(menuSaque()));
                    layout.bottomLine(2);
                    layout.br(1);
                    layout.loading(2);
                    layout.limparTela();
                    n = "0";
                }

                // Transferência
                else if (Integer.parseInt(n) == 3) {
                    n = "0";
                }

                // Menu Pix
                else if (Integer.parseInt(n) == 4) {
                    menuPix(1);
                }

                // Menu Cartões
                else if (Integer.parseInt(n) == 5) {
                    System.out.println("Em construção");
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

                layout.topLine(3);
                layout.br(1);

                System.out.println("        =-=-=-=-=-=Conta poupança de " +
                        Bd.clienteBuscaContaPoupanca.getCliente().getNome() + "=-=-=-=-=-=");

                layout.bottomLine(3);
                layout.br(1);
                layout.topLine(3);
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
                layout.centralLine(3);
                layout.br(1);

                // Opções do menu
                System.out.println("      [1] Depositar      [2] Sacar          [3] Cartões");
                System.out.println("      [4] Menu Pix       [5] Consulta       [6] Voltar");
                layout.bottomLine(3);
                layout.br(1);

                // Entrada do usuário
                layout.topLine(3);
                layout.br(1);
                n = entry("    Escolha: ");
                layout.bottomLine(3);
                layout.br(1);

                layout.loading(3);
                layout.limparTela();

                // Direcionamento de opções

                // Depósito
                if (Integer.parseInt(n) == 1) {
                    ContaPoupancaBo.Deposito(menuDeposito());
                    layout.bottomLine(2);
                    layout.br(1);
                    layout.loading(2);
                    layout.limparTela();
                    n = "0";
                }

                // Saque
                else if (Integer.parseInt(n) == 2) {
                    System.out.println(ContaPoupancaBo.Saque(menuDeposito()));
                    layout.bottomLine(2);
                    layout.br(1);
                    layout.loading(2);
                    layout.limparTela();
                    n = "0";
                }

                // Transferir
                else if (Integer.parseInt(n) == 3) {
                    n = "0";
                }

                // Menu Pix
                else if (Integer.parseInt(n) == 4) {
                    menuPix(2);
                }

                // Menu Cartões
                else if (Integer.parseInt(n) == 5) {
                    System.out.println("Em construção");
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

    // Menu Deposito
    public static Float menuDeposito(){

        layout.topLine(2);
        layout.br(1);

        System.out.println("      =-=-=-=-=-=Depósito=-=-=-=-=-=");

        layout.bottomLine(2);
        layout.br(1);
        layout.topLine(2);
        layout.br(1);
        System.out.println("  [Digite 0 para sair]");
        return(Float.valueOf(entry("  Digite o valor do depósito: R$ ")));

    }

    // Menu Saque
    public static Float menuSaque(){

        layout.topLine(2);
        layout.br(1);

        System.out.println("         =-=-=-=-=-=Saque=-=-=-=-=-=");

        layout.bottomLine(2);
        layout.br(1);
        layout.topLine(2);
        layout.br(1);
        System.out.println("  [Digite 0 para sair]");
        return(Float.valueOf(entry("  Digite o valor do saque: R$ ")));

    }

    // Menu Pix
    public static void menuPix(Integer contaTipo){

        String n = "-1";

        // Layout
        layout.topLine(3);
        layout.br(1);
        System.out.println("                   =-=-= Menu PIX=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // Opções
        layout.topLine(3);
        layout.br(1);
        System.out.println("    [1] Cadastrar      [2] Apagar chave      [3] Transferir");
        System.out.println("    [4] Consultar      [5] Voltar");
        layout.bottomLine(3);
        layout.br(1);

        // Repetição
        while(Integer.parseInt(n) < 0 || Integer.parseInt(n) > 5) {

            // Layout
            layout.topLine(3);
            layout.br(1);
            n = entry("    Escolha: ");
            layout.bottomLine(3);
            layout.br(1);
            layout.loading(3);
            layout.limparTela();

            // Cadastrar PIX
            if(Integer.parseInt(n) == 1){
                menuCadastroPix(contaTipo);
                layout.loading(3);
                layout.limparTela();
                menuPix(contaTipo);
            }
            // Apagar PIX
            else if(Integer.parseInt(n) == 2){
                menuApagaPix(contaTipo);
                layout.loading(3);
                layout.limparTela();
                menuPix(contaTipo);
            }
            // Transferir PIX
            else if(Integer.parseInt(n) == 3){

                menuTransferePix(contaTipo);
                layout.loading(3);
                layout.limparTela();
                menuPix(contaTipo);
            }
            // Consultar PIX
            else if(Integer.parseInt(n) == 4){
                menuConsultaPix(contaTipo);
                layout.loading(3);
                layout.limparTela();
                menuPix(contaTipo);
            }
            // Voltar PIX
            else if(Integer.parseInt(n) == 5){
                layout.loading(3);
                layout.limparTela();
                menuPrincipal(contaTipo.toString());
            }

        }
    }
    /* -------------------------------------- FIM - MENU PRINCIPAL -------------------------------------------- */

    /* --------------------------------------- INÍCIO - PARTE PIX --------------------------------------------- */
    // Menu de cadastro de Pix
    public static void menuCadastroPix(Integer contaTipo){

        String n = "-1";

        // Interface
        layout.topLine(3);
        layout.br(1);
        System.out.println("              =-=-= Menu cadastro PIX=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // Opções
        layout.topLine(3);
        layout.br(1);
        System.out.println("        [1] CPF      [2] Email      [3] Telefone");
        System.out.println("        [4] Voltar");
        layout.bottomLine(3);
        layout.br(1);

        // Repetição
        while(Integer.parseInt(n) < 0 || Integer.parseInt(n) > 4) {

            // Layout
            layout.topLine(3);
            layout.br(1);
            n = entry("    Escolha: ");
            layout.bottomLine(3);
            layout.br(1);

            // CPF
            if(Integer.parseInt(n) == 1){

                // CONTA CORRENTE
                if(contaTipo == 1) {
                    Bd.insereChavePixCpf(Bd.clienteBuscaContaCorrente.getCliente().getCpf(), 1);
                }
                // CONTA POUPANÇA
                else if(contaTipo == 2){
                    Bd.insereChavePixCpf(Bd.clienteBuscaContaPoupanca.getCliente().getCpf(), 2);
                }

            }

            // EMAIL
            else if(Integer.parseInt(n) == 2){

                // CONTA CORRENTE
                if(contaTipo == 1) {
                    Bd.insereChavePixEmail(Bd.clienteBuscaContaCorrente.getCliente().getEmail(), 1);
                }
                // CONTA POUPANÇA
                else if(contaTipo == 2){
                    Bd.insereChavePixEmail(Bd.clienteBuscaContaPoupanca.getCliente().getEmail(), 2);
                }

            }

            // TELEFONE
            else if(Integer.parseInt(n) == 3){

                // CONTA CORRENTE
                if(contaTipo == 1) {
                    Bd.insereChavePixTelefone(Bd.clienteBuscaContaCorrente.getCliente().getTelefone(), 1);
                }
                // CONTA POUPANÇA
                else if(contaTipo == 2){
                    Bd.insereChavePixTelefone(Bd.clienteBuscaContaPoupanca.getCliente().getTelefone(), 2);
                }

            }

            // VOLTAR
            else{
                layout.loading(3);
                layout.limparTela();
                menuPix(contaTipo);
            }
        }

    }

    // Menu Apaga PIX
    public static void menuApagaPix(Integer contaTipo){

        // Interface
        layout.topLine(3);
        layout.br(1);
        System.out.println("                =-=-= Deletar chave PIX=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        if(PixBo.consultarChavesPixCliente(contaTipo, true) != null) {

            layout.topLine(3);
            layout.br(1);
            String n = entry("    Escolha: ");
            layout.bottomLine(3);
            layout.br(1);
            if (contaTipo == 1) {
                System.out.println(Bd.pixDelete(1, Integer.parseInt(n)));
            } else {
                System.out.println(Bd.pixDelete(2, Integer.parseInt(n)));
            }
        }



    }

    // Menu Transfere PIX
    public static void menuTransferePix(Integer contaTipo){

        // Interface
        layout.topLine(3);
        layout.br(1);
        System.out.println("              =-=-= Transferência via PIX =-=-=");
        layout.bottomLine(3);
        layout.br(1);
        layout.topLine(3);
        layout.br(1);

        // Entrada
        String transfChave = entry("    Chave de transferência: ");
        Float transfValor = Float.parseFloat(entry("    Valor da transferência: R$ "));

        layout.bottomLine(3);
        layout.br(1);

        layout.topLine(3);
        layout.br(1);
        System.out.println(PixBo.transferir(transfChave, transfValor, contaTipo));
        layout.bottomLine(3);
        layout.br(1);

        layout.loading(3);
        layout.limparTela();

    }

    // Menu Consulta PIX
    public static void menuConsultaPix(Integer contaTipo){

        // Interface
        layout.topLine(3);
        layout.br(1);
        System.out.println("              =-=-= Menu consulta PIX=-=-=");
        layout.bottomLine(3);
        layout.br(1);
        layout.topLine(3);
        layout.br(1);
        PixBo.consultarChavesPixCliente(contaTipo, true);
        layout.bottomLine(3);
        layout.br(1);

    }
    /* ---------------------------------------- FIM - PARTE PIX ---------------------------------------------- */

    // Entry
    public static String entry(String texto) {
        System.out.print(texto);
        return input.next();
    }

}
