package view;
import bo.*;
import dao.Bd;
import model.cartao.Cartao;
import model.conta.Conta;
import model.conta.ContaTipo;
import util.Layout;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
public class Main {

    // Instâncias
    public static Layout layout = new Layout(5, 5);
    public static LoginBo login = new LoginBo("", "");

    // Método main
    public static void main(String[] args) {

        CadastroBo.cadastrarUsuario("Gabriel", "471", "558468263",
                "1", "São Paulo", "SP", "Lauzane", "583",
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
            choice = Integer.parseInt(Layout.entry("           Escolha: "));
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
        String loginCpf = Layout.entry("  Digite seu CPF: ");
        String loginSenha = Layout.entry("  Digite sua senha: ");
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

        String nome = Layout.entry("  Digite seu nome: ");

        String cpf = Layout.entry("  Digite seu CPF: ");
        String rg = Layout.entry("  Digite seu RG: ");
        String email = Layout.entry("  Digite seu Email: ");
        String telefone = Layout.entry("  Digite seu Telefone: ");
        String senha = Layout.entry("  Digite a sua senha: ");
        String estado = Layout.entry("  Digite o seu estado: ");
        String cidade = Layout.entry("  Digite a sua cidade: ");
        String bairro = Layout.entry("  Digite o nome do seu bairro: ");
        String rua = Layout.entry("  Digite o nome da sua rua: ");
        String numeroRua = Layout.entry("  Digite o número da casa: ");
        String cep = Layout.entry("  Digite o CEP: ");
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
        String tipoDeConta = Layout.entry("  Escolha: ");
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
                n = Layout.entry("    Escolha: ");
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
                n = Layout.entry("    Escolha: ");
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
                n = Layout.entry("    Escolha: ");
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
                n = Layout.entry("    Escolha: ");
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
                    menuCartoes(1);
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
                n = Layout.entry("    Escolha: ");
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
                    menuCartoes(1);
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
        return(Float.valueOf(Layout.entry("  Digite o valor do depósito: R$ ")));

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
        return(Float.valueOf(Layout.entry("  Digite o valor do saque: R$ ")));

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
            n = Layout.entry("    Escolha: ");
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

    // Menu Cartões
    public static void menuCartoes(Integer tipoConta){

        int n;

        // Layout
        layout.topLine(3);
        layout.br(1);
        System.out.println("                  =-=-= Menu Cartões=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // Opções
        layout.topLine(3);
        layout.br(1);
        System.out.println("       [1] Meus cartões   [2] Novo cartão   [3] Sair");
        layout.bottomLine(3);
        layout.br(1);

        do {
            layout.topLine(3);
            layout.br(1);
            n = Integer.parseInt(Layout.entry("    Escolha: "));
            layout.bottomLine(3);
            layout.br(1);
            layout.loading(3);
            layout.limparTela();
            if(n > 0 && n < 4){
                if(n == 1){
                    menuMeusCartoes(tipoConta);
                }else if(n == 2){
                    menuNovoCartao(tipoConta);

                }else{
                    menuPrincipal(String.valueOf(tipoConta));
                }
            }else{
                System.out.println("    Entrada incorreta!");
            }
        }while(n < 1 || n > 3);

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
            n = Layout.entry("    Escolha: ");
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
            String n = Layout.entry("    Escolha: ");
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
        String transfChave = Layout.entry("    Chave de transferência: ");
        Float transfValor = Float.parseFloat(Layout.entry("    Valor da transferência: R$ "));

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

    /* -----------------------------------=- INÍCIO - PARTE CARTÕES ------------------------------------------ */

    // SELEÇÃO DE CARTÕES DO CLIENTE
    public static void menuMeusCartoes(Integer tipoConta){

        int n = 0, cont = 0;
        Map<Cartao, String> listar = (CartaoBo.listarCartoesDoCliente(tipoConta)); // HASHMAP COM TIPO DE CARTÕES E CTS

        // LAYOUT
        layout.topLine(3);
        layout.br(1);
        System.out.println("                 =-=-= Meus Cartões =-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // LAYOUT
        layout.topLine(3);
        layout.br(1);

        // LISTAR CARTÕES DO CLIENTE SE ELE TIVER ALGUM CARTÃO CADASTRADO
        if(!listar.isEmpty()) {

            // RODA A LISTAGEM DE CARTÕES DO CLIENTE
            for (Map.Entry<Cartao, String> entry : listar.entrySet()) {
                cont++;
                System.out.println("    [" + cont + "] " + entry.getValue() + " - " + entry.getKey().getNumeroCartao());
            }

            cont++;
            System.out.println("    [" + cont + "]" + " Voltar");

            // LAYOUT
            layout.bottomLine(3);
            layout.br(1);

            // DO WHILE DE ESCOLHA DO USUÁRIO
            do {
                // ESCOLHA DO USUÁRIO
                layout.topLine(3);
                layout.br(1);
                n = Integer.parseInt(Layout.entry("    Escolha: "));
                layout.bottomLine(3);
                layout.br(1);
                layout.loading(3);
                layout.limparTela();

                // SE TIVER CARTÃO DE CRÉDITO E DÉBITO APARECENDO NA LISTAGEM
                if(cont == 3) {

                    // SE A SELEÇÃO FOR OUT OF BOUNDS
                    if (n < 1 || n > cont) {
                        System.out.println("    Valor inválido.");
                    }

                    // SE O ITEM SELECIONADO FOR O PRIMEIRO CARTÃO ( CRÉDITO )
                    else if (n == 1) {
                        menuCartaoSelecionado(tipoConta, 2);
                    }

                    // SE O ITEM SELECIONADO FOR O SEGUNDO CARTÃO ( DÉBITO )
                    else if (n == 2) {
                        menuCartaoSelecionado(tipoConta, 1);
                    }

                    // VOLTA PRO MENU CARTÕES
                    else if (n == cont) {

                        menuCartoes(tipoConta);

                    }

                }

                // SE TIVER SÓ UM CARTÃO APARECENDO NA LISTAGEM
                else if(cont == 2){

                    // SE O CLIENTE SELECIONAR A OPÇÃO 1
                    if(n == 1) {
                        // RODA A LISTAGEM
                        for (Map.Entry<Cartao, String> entry : listar.entrySet()) {

                            // SE FOR CRÉDITO
                            if (entry.getValue().equals("Crédito")) {
                                menuCartaoSelecionado(tipoConta, 2);
                            }
                            // SE FOR DÉBITO
                            else if (entry.getValue().equals("Débito")) {
                                menuCartaoSelecionado(tipoConta, 1);
                            }

                        }
                    }
                    // VOLTA AO MENU DE CARTÕES
                    else if(n == 2){
                        menuCartoes(tipoConta);
                    }

                }

            }while(n < 1 || n > cont);

        }
        // SE CLIENTE NÃO TIVER NENHUM CARTÃO
        else{
            System.out.println("    Você não tem nenhum cartão cadastrado no momento");
            layout.loading(3);
            layout.limparTela();
            menuCartoes(tipoConta);
        }

    }

    // CRIAÇÃO DE NOVO CARTÃO
    public static void menuNovoCartao(Integer tipoConta){

        int n = 0;

        layout.topLine(3);
        layout.br(1);
        System.out.println("               =-=-= Cadastrar novo cartão =-=-=");
        layout.bottomLine(3);
        layout.br(1);

        layout.topLine(3);
        layout.br(1);
        System.out.println("            [1] Débito   [2] Crédito   [3] Sair");
        layout.bottomLine(3);
        layout.br(1);

        do {

            layout.topLine(3);
            layout.br(1);
            n = Integer.parseInt(Layout.entry("    Escolha: "));
            layout.bottomLine(3);
            layout.br(1);
            layout.loading(3);
            layout.limparTela();

            if(n < 1 || n > 3){
                System.out.println("Entrada inválida");
            }
            else if(n == 1){
                System.out.println(CartaoBo.validaInserirCartoesDebito(tipoConta));
            }
            else if(n == 2){
                System.out.println(CartaoBo.validaInserirCartoesCredito(tipoConta));
            }else{
                menuMeusCartoes(tipoConta);
            }
        }while(n < 1 || n > 3);
        menuCartoes(tipoConta);
    }

    // MENU DO CARTÃO SELECIONADO ( PARAMS: TIPO CARTÃO 1 - DÉBITO || TIPO CARTÃO 2 - CRÉDITO)
    public static void menuCartaoSelecionado(Integer tipoConta, Integer tipoCartao){

        int n = 0;

        // LAYOUT
        layout.topLine(3);
        layout.br(1);

        // VALIDA O TIPO DO CARTÃO PARA FAZER APARECER O TIPO DELE NO TÍTULO
        if(tipoCartao == 1) {
            System.out.println("             =-=-= Meu cartão de débito =-=-=");
        }
        else if(tipoCartao == 2){
            System.out.println("             =-=-= Meu cartão de crédito =-=-=");
        }

        layout.bottomLine(3);
        layout.br(1);

        // SE O CARTÃO FOR DE DÉBITO
        if(tipoCartao == 1){

            // SE A CONTA FOR CORRENTE
            if(tipoConta == 1) {

                // LAYOUT
                layout.topLine(3);
                layout.br(1);

                // LAYOUT INFORMAÇÕES DO CARTÃO DE DÉBITO CC
                System.out.print("    Numero: " + Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0).getNumeroCartao());
                System.out.print("  ||  Bandeira: " + Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0).getBandeira());
                // SE O CARTÃO DE DÉBITO CC ESTIVER ATIVADO
                if(Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0).isAtivo()) {
                    System.out.println("  ||  Status: ATIVO");
                    layout.centralLine(3);
                    layout.br(1);

                    // OPÇÕES
                    System.out.println("     [1] Desativar   [2] Comprar   [3] Extrato   [4] Sair");

                    // Layout
                    layout.bottomLine(3);
                    layout.br(1);

                    // ESCOLHA DO USUÁRIO
                    do {

                        // LAYOUT
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);
                        layout.loading(3);
                        layout.limparTela();

                        // DESATIVAR CARTÃO DE DÉBITO CC
                        if(n == 1){
                            System.out.println(DebitoBo.ativaOuDesativaCartao(tipoConta, false));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // COMPRAR COM O CARTÃO DE DÉBITO CC
                        else if(n == 2){
                            menuCartaoDebitoCompra(tipoConta);
                            layout.loading(3);
                            layout.limparTela();
                            menuCartaoSelecionado(1, 1);
                        }
                        // SOLICITAR EXTRATO DO CARTÃO DE DÉBITO CC
                        else if(n == 3){
                            DebitoBo.retornaExtrato(tipoConta);
                            layout.loading(3);
                            layout.limparTela();
                            menuCartaoSelecionado(1, 1);
                        }
                        // SAIR DO MENU DO CARTÃO DE DÉBITO CC
                        else if(n == 4){
                            menuCartoes(tipoConta);
                        }

                    }while(n < 1 || n > 4);

                }

                // SE O CARTÃO DE DÉBITO CC ESTIVER DESATIVADO
                else{
                    System.out.println("  ||  Status: INATIVO");
                    layout.centralLine(3);
                    layout.br(1);

                    // OPÇÕES
                    System.out.println("       [1] Ativar   [2] Sair");

                    // Layout
                    layout.bottomLine(3);
                    layout.br(1);

                    // ESCOLHA DO USUÁRIO
                    do {

                        // LAYOUT
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);
                        layout.loading(3);
                        layout.limparTela();

                        // ATIVAR CARTÃO DE DÉBITO CC
                        if(n == 1){
                            System.out.println(DebitoBo.ativaOuDesativaCartao(tipoConta, true));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // SAIR DO MENU DO CARTÃO DE DÉBITO CC
                        else if(n == 2){
                            menuCartoes(tipoConta);
                        }

                    }while(n < 1 || n > 2);

                }

            }

            // SE A CONTA FOR POUPANÇA
            else if(tipoConta == 2){

                // LAYOUT
                layout.topLine(3);
                layout.br(1);

                // LAYOUT INFORMAÇÕES DO CARTÃO DE DÉBITO CP
                System.out.print("    Numero: " + Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0).getNumeroCartao());
                System.out.print("  ||  Bandeira: " + Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0).getBandeira());
                System.out.print("  ||  Limite: " + Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0).getLimiteTransacao()));

                // SE O CARTÃO DE DÉBITO DA CP ESTIVER ATIVADO
                if(Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0).isAtivo()) {
                    System.out.println("  ||  Status: ATIVO");
                    layout.centralLine(3);
                    layout.br(1);

                    // OPÇÕES
                    System.out.println("       [1] Desativar   [2] Comprar   [3] Extrato   [4] Sair");

                    // Layout
                    layout.bottomLine(3);
                    layout.br(1);

                    // ESCOLHA DO USUÁRIO
                    do {

                        // LAYOUT
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);
                        layout.loading(3);
                        layout.limparTela();

                        // DESATIVAR CARTÃO DE DÉBITO CP
                        if(n == 1){
                            System.out.println(DebitoBo.ativaOuDesativaCartao(tipoConta, false));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // COMPRAR COM O CARTÃO DE DÉBITO CP
                        else if(n == 2){
                            menuCartaoDebitoCompra(tipoConta);
                            layout.loading(3);
                            layout.limparTela();
                            menuCartaoSelecionado(2, 1);
                        }
                        // SOLICITAR EXTRATO DO CARTÃO DE DÉBITO CP
                        else if(n == 3){
                            DebitoBo.retornaExtrato(tipoConta);
                            layout.loading(3);
                            layout.limparTela();
                            menuCartaoSelecionado(2, 1);
                        }
                        // SAIR DO MENU DO CARTÃO DE DÉBITO CP
                        else if(n == 4){
                            menuCartoes(tipoConta);
                        }

                    }while(n < 1 || n > 4);

                }

                // SE O CARTÃO DE DÉBITO DA CP ESTIVER DESATIVADO
                else{
                    System.out.println("  ||  Status: INATIVO");
                    layout.centralLine(3);
                    layout.br(1);

                    // OPÇÕES
                    System.out.println("       [1] Ativar   [2] Sair");

                    // Layout
                    layout.bottomLine(3);
                    layout.br(1);

                    // ESCOLHA DO USUÁRIO
                    do {

                        // LAYOUT
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);
                        layout.loading(3);
                        layout.limparTela();

                        // ATIVAR CARTÃO DE DÉBITO CP
                        if(n == 1){
                            System.out.println(DebitoBo.ativaOuDesativaCartao(tipoConta, true));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // SAIR DO MENU DO CARTÃO DE DÉBITO CP
                        else if(n == 2){
                            menuCartoes(tipoConta);
                        }

                    }while(n < 1 || n > 2);

                }

            }
        }

        // SE O CARTÃO FOR DE CRÉDITO
        else if(tipoCartao == 2){

            // SE A CONTA FOR CORRENTE
            if(tipoConta == 1){

                // LAYOUT
                layout.topLine(3);
                layout.br(1);

                // LAYOUT INFORMAÇÕES DO CARTÃO DE CRÉDITO CC
                System.out.print("    Limite total: " + Layout.convertToReais(Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0).getLimite().getLimiteTotal()));
                System.out.println("  ||  Disponível: " + Layout.convertToReais(Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0).getLimite().getLimiteDisponivel()));
                System.out.print("    Numero: " + Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0).getNumeroCartao());
                System.out.print("  ||  Bandeira: " + Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0).getBandeira());
                // SE O CARTÃO DE CRÉDITO CC ESTIVER ATIVADO
                if(Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0).isAtivo()) {
                    System.out.println("  ||  Status: ATIVO");
                    layout.centralLine(3);
                    layout.br(1);

                    // OPÇÕES
                    System.out.println("       [1] Desativar     [2] Comprar     [3] Ver Fatura");
                    System.out.println("       [4] Pagar fatura  [5] Sair");

                    // Layout
                    layout.bottomLine(3);
                    layout.br(1);

                    // ESCOLHA DO USUÁRIO
                    do {

                        // LAYOUT
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);
                        layout.loading(3);
                        layout.limparTela();

                        // DESATIVAR CARTÃO DE CRÉDITO CC
                        if(n == 1){
                            System.out.println(CreditoBo.ativaOuDesativaCartao(tipoConta, false));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // COMPRAR COM O CARTÃO DE CRÉDITO CC
                        else if(n == 2){

                        }
                        // VER FATURA DO CARTÃO DE CRÉDITO CC
                        else if(n == 3){

                        }
                        // PAGAR FATURA DO CARTÃO DE CRÉDITO CC
                        else if(n == 4){

                        }
                        // SAIR DO MENU DO CARTÃO DE CREDITO CC
                        else if(n == 5){
                            menuCartoes(tipoConta);
                        }
                    }while(n < 1 || n > 5);

                }

                // SE O CARTÃO DE CRÉDITO CC ESTIVER DESATIVADO
                else{
                    System.out.println("  ||  Status: INATIVO");
                    layout.centralLine(3);
                    layout.br(1);

                    // OPÇÕES
                    System.out.println("       [1] Ativar   [2] Sair");

                    // Layout
                    layout.bottomLine(3);
                    layout.br(1);

                    // ESCOLHA DO USUÁRIO
                    do {

                        // LAYOUT
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);
                        layout.loading(3);
                        layout.limparTela();

                        // ATIVAR CARTÃO DE CRÉDITO CC
                        if(n == 1){
                            System.out.println(CreditoBo.ativaOuDesativaCartao(tipoConta, true));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // SAIR DO MENU DO CARTÃO DE CRÉDITO CC
                        else if(n == 2){
                            menuCartoes(tipoConta);
                        }

                    }while(n < 1 || n > 2);

                }

            }
            // SE A CONTA FOR POUPANÇA
            else if(tipoConta == 2){

                // LAYOUT
                layout.topLine(3);
                layout.br(1);

                // LAYOUT INFORMAÇÕES DO CARTÃO DE CRÉDITO CP
                System.out.print("    Limite total: " + Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0).getLimite().getLimiteTotal()));
                System.out.println("  ||  Disponível: " + Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0).getLimite().getLimiteDisponivel()));
                System.out.print("    Numero: " + Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0).getNumeroCartao());
                System.out.print("  ||  Bandeira: " + Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0).getBandeira());

                // SE O CARTÃO DE CRÉDITO CP ESTIVER ATIVADO
                if(Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0).isAtivo()) {
                    System.out.println("  ||  Status: ATIVO");
                    layout.centralLine(3);
                    layout.br(1);

                    // OPÇÕES
                    System.out.println("       [1] Desativar     [2] Comprar     [3] Ver Fatura");
                    System.out.println("       [4] Pagar fatura  [5] Sair");

                    // Layout
                    layout.bottomLine(3);
                    layout.br(1);

                    // ESCOLHA DO USUÁRIO
                    do {

                        // LAYOUT
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);
                        layout.loading(3);
                        layout.limparTela();

                        // DESATIVAR CARTÃO DE CRÉDITO CP
                        if(n == 1){
                            System.out.println(CreditoBo.ativaOuDesativaCartao(tipoConta, false));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // COMPRAR COM O CARTÃO DE CRÉDITO CP
                        else if(n == 2){

                        }
                        // VER FATURA DO CARTÃO DE CRÉDITO CP
                        else if(n == 3){

                        }
                        // PAGAR FATURA DO CARTÃO DE CRÉDITO CP
                        else if(n == 4){

                        }
                        // SAIR DO MENU DO CARTÃO DE CREDITO CP
                        else if(n == 5){
                            menuCartoes(tipoConta);
                        }
                    }while(n < 1 || n > 5);

                }

                // SE O CARTÃO DE CRÉDITO CP ESTIVER DESATIVADO
                else{
                    System.out.println("  ||  Status: INATIVO");
                    layout.centralLine(3);
                    layout.br(1);

                    // OPÇÕES
                    System.out.println("       [1] Ativar   [2] Sair");

                    // Layout
                    layout.bottomLine(3);
                    layout.br(1);

                    // ESCOLHA DO USUÁRIO
                    do {

                        // LAYOUT
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);
                        layout.loading(3);
                        layout.limparTela();

                        // ATIVAR CARTÃO DE CRÉDITO CP
                        if(n == 1){
                            System.out.println(CreditoBo.ativaOuDesativaCartao(tipoConta, true));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // SAIR DO MENU DO CARTÃO DE CRÉDITO CP
                        else if(n == 2){
                            menuCartoes(tipoConta);
                        }

                    }while(n < 1 || n > 2);

                }

            }
        }

    }

    public static void menuCartaoDebitoCompra(Integer tipoConta){

        layout.topLine(3);
        layout.br(1);
        System.out.println("                 =-=-= Inserir Compra =-=-=");
        layout.bottomLine(3);
        layout.br(1);

        layout.topLine(3);
        layout.br(1);
        String nomeProduto = Layout.entry("    Digite o nome do produto: ");
        Float valorProduto = Float.parseFloat(Layout.entry("    Digite o valor do produto: R$ "));
        layout.bottomLine(3);
        layout.br(1);
        System.out.println(DebitoBo.processaCompra(tipoConta, nomeProduto, valorProduto));
    }

    /* -------------------------------------- FIM - PARTE CARTÕES -------------------------------------------- */
















}
