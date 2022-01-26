package view;

// IMPORTAÇÕES
import bo.*;
import dao.Bd;
import model.cartao.Cartao;
import model.cartao.TipoCartao;
import model.conta.ContaTipo;
import model.seguros.Apolice;
import model.seguros.SeguroDesemprego;
import model.seguros.SeguroInvalidez;
import model.seguros.SeguroVida;
import util.Layout;
import java.util.Map;
public class Main {

    // INSTANCIAÇÃO DE CLASSES
    public static Layout layout = new Layout(1, 1);
    public static LoginBo login = new LoginBo("", "");
    public static SeguroVida seguroVida = new SeguroVida();
    public static SeguroInvalidez seguroInvalidez = new SeguroInvalidez();
    public static SeguroDesemprego seguroDesemprego = new SeguroDesemprego();

    // MÉTODO MAIN
    public static void main(String[] args) {

        CadastroBo.cadastrarUsuario("Gabriel", "47153427821", "558468263",
                "1", "São Paulo", "SP", "Lauzane", "583",
                "Avenida Coronel Manuel Py", "02442090", "gabriellagrota23@gmail.com", "979815415", "3");

        CadastroBo.cadastrarUsuario("Henrique", "123456789012", "558468263",
                "1", "São Paulo", "SP", "Lauzane", "583",
                "Avenida Coronel Manuel Py", "02442090", "email@gmail.com", "961329075", "2");

        CadastroBo.cadastrarUsuario("Afonso", "210987654321", "558468263",
                "2", "São Paulo", "SP", "Lauzane", "583",
                "Avenida Coronel Manuel Py", "02442090", "blablabla@foursys.com", "992844948", "1");

        //menuCadastro();
        menuLogin();

    }

    /* -------------------------------------- INÍCIO - MENU ACESSO --------------------------------------------- */
    // MENU DE ACESSO
    private static void menuAcesso() {

        // Declaração de variáveis
        int choice = 0;

        // Repetidor para anular erros de entrada do usuário
        do {

            // Título
            layout.topLine(3);
            System.out.println("\n    =-=-=-=-=-=Seja bem-vindo(a) ao banco Next!=-=-=-=-=-=");
            layout.bottomLine(3);
            layout.br(1);

            // Menu de acesso
            System.out.println("           [1] Login     [2] Cadastro     [3] Sair");
            layout.bottomLine(3);
            layout.br(1);

            // Try - Se o usuário entrar com alguma informação incorreta, ele não irá proceder
            try {

                // Entrada do usuário
                choice = Integer.parseInt(Layout.entry("    Escolha: "));

                layout.loading(3);
                layout.limparTela();

                // Se o usuário quiser realizar login
                if(choice == 1){
                    menuLogin();
                }

                // Se o usuário quiser realizar cadastro
                else if(choice == 2){
                    menuCadastro();
                }

                // Se o usuário quiser encerrar o sistema
                else if(choice == 3){
                    System.exit(0);
                }

                // Se o usuário digitar algum número diferente de 1 2 ou 3
                else{
                    System.out.println("Erro: Entrada incorreta. Digite um valor entre [1 e 3]");
                }

            }

            // Se ocorrer alguma excessão na entrada do usuário
            catch(Exception e){

                layout.loading(3);
                layout.limparTela();
                System.out.println("Erro: Entrada incorreta. Digite um valor entre [1 e 3]");

            }

        }while(choice < 1 || choice > 3);
    }

    // MENU DE LOGIN
    private static void menuLogin(){

        // Inicialização de variáveis
        long loginCpf = 0L;
        int n = 0;

        // Layout do título
        layout.topLine(3);
        System.out.println("\n           =-=-=-=-=-=Login de usuário=-=-=-=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // Entrada do CPF COMO VALOR INTEIRO
        do {
            // Try de validação se o CPF inserido é numérico
            try {

                // Layout
                layout.topLine(3);
                layout.br(1);

                // Tenta converter CPF (INTEIRO) para CPF STRING
                loginCpf = Long.parseLong(Layout.entry("    Digite o seu CPF: ")
                        .replace("-", "")
                        .replace(".", ""));

                // SE A ENTRADA DO CPF NÃO TIVER 11 DÍGITOS
                if(Long.toString(loginCpf)
                        .replace("-", "").replace(".", "").length() != 11){

                    // Layout
                    layout.bottomLine(3);
                    layout.br(1);
                    layout.loading(3);
                    layout.limparTela();
                    layout.br(1);
                    System.out.println("Erro: O CPF inserido deve possuir 11 dígitos");

                }

                // SE A ENTRADA DO CPF TIVER 11 DÍGITOS
                else{
                    // Variável de saída da estrutura de repetição APROVADO
                    n = 1;
                }

            }
            // Se a entrada do usuário para o CPF no login for inválida
            catch (Exception e) {

                // Layout
                layout.bottomLine(3);
                layout.br(1);
                layout.loading(3);
                layout.limparTela();
                layout.br(1);
                System.out.println("Erro: Digite apenas valores numéricos para a entrada do CPF");

            }
        }while(n != 1);

        // Entrada da SENHA
        String loginSenha = Layout.entry("    Digite sua senha: ");

        // Instanciação do objeto de login
        login = new LoginBo(Long.toString(loginCpf), loginSenha);

        // Layout delay
        layout.sleep(1600);

        // Invocação do método de acesso do login
        System.out.println(login.Acessar());

        // Layout de delay
        layout.sleep(1600);

        // Layout
        layout.bottomLine(3);
        layout.br(1);
        layout.loading(3);
        layout.limparTela();

        // Se o login não for validado ele retorna ao menu de login novamente
        if(!login.isAtivo()){
            menuLogin();
        }
        // Se o login for validado ele avança para o menu de seleção de contas
        else{
            menuContas();
        }

    }

    // MENU DE CADASTRO
    private static void menuCadastro(){

        // Declaração de variáveis
        Boolean passa;
        String nome, cpf, rg, email, telefone, senha, estado, cidade, bairro, rua, numeroRua, cep;

        // Instanciações
        EntradasBo entradasBo = new EntradasBo();

        // Layout de título
        layout.topLine(3);
        System.out.println("\n         =-=-=-=-=-=Cadastro de usuário=-=-=-=-=-=");
        layout.bottomLine(3);
        layout.br(1);
        layout.topLine(3);
        layout.br(1);

        /* --------------------------------- INÍCIO - VALIDAÇÕES DE CADASTRO -------------------------------------- */

        // Validação de entrada do nome
        do {

            nome = Layout.entry("    Digite seu nome: ").toUpperCase();

            passa = entradasBo.cadastraNomeBo(nome);

        }while(!passa);

        // Validação de entrada do CPF
        do{

            cpf = Layout.entry("    Digite seu CPF: ")
                    .replaceAll(" ", "")
                    .replaceAll("\\.", "")
                    .replaceAll("-", "");

            passa = entradasBo.cadastraCpfBo(cpf);

        }while(!passa);

        // Validação de entrada do RG
        do{

            rg = Layout.entry("    Digite seu RG: ")
                    .replace(".", "")
                    .replace("-", "")
                    .replace(" ", "");

            passa = entradasBo.cadastraRgBo(rg);

        }while(!passa);

        // Validação de entrada do EMAIL
        do{

            email = Layout.entry("    Digite seu Email: ")
                    .replace(" ", "")
                    .toLowerCase();

            passa = entradasBo.cadastraEmailBo(email);

        }while(!passa);

        // Validação de entrada do TELEFONE
        do{

            telefone = Layout.entry("    Digite seu telefone sem DDD [XXXXX-XXXX]: ")
                    .replaceAll(" ", "")
                    .replaceAll("-", "");
            passa = entradasBo.cadastraTelefoneBo(telefone);

        }while(!passa);

        // Validação de entrada da SENHA
        do{

            senha = Layout.entry("    Crie uma senha de 6 números: ")
                    .replaceAll(" ", "");
            passa = entradasBo.cadastraSenhaBo(senha);

        }while(!passa);

        // Validação de entrada do ESTADO
        do{

            estado = Layout.entry("    Digite a sua unidade federativa (XX): ")
                    .toUpperCase()
                    .replaceAll(" ", "");

            passa = entradasBo.cadastraEstadoBo(estado);

        }while(!passa);

        // Validação de entrada da CIDADE
        do{

            cidade = Layout.entry("    Digite seu cidade: ")
                    .replaceAll(" ", "")
                    .toUpperCase();

            passa = entradasBo.cadastraCidadeBo(cidade);

        }while(!passa);

        // Validação de entrada do BAIRRO
        do{

            bairro = Layout.entry("    Digite seu bairro: ")
                    .replaceAll(" ", "")
                    .toUpperCase();

            passa = entradasBo.cadastraBairroBo(bairro);

        }while(!passa);

        // Validação de entrada da RUA
        do{

            rua = Layout.entry("    Digite seu rua: ")
                    .replaceAll(" ", "")
                    .toUpperCase();

            passa = entradasBo.cadastraRuaBo(rua);

        }while(!passa);

        // Validação de entrada do NUMERO DA RUA
        do{
            numeroRua = Layout.entry("    Digite seu numero da rua: ")
                    .replaceAll(" ", "");

            passa = entradasBo.cadastraNumeroRuaBo(numeroRua);
        }while(!passa);

        // Validação de entrada do CEP
        do{
            cep = Layout.entry("    Digite seu CEP: ")
                    .replaceAll(" ", "")
                    .replace("-", "");

            passa = entradasBo.cadastraCepBo(cep);

        }while(!passa);

        /* ---------------------------------- FIM - VALIDAÇÕES DE CADASTRO ---------------------------------------- */

        // LAYOUT TÍTULO
        layout.bottomLine(3);
        layout.br(1);
        layout.topLine(3);
        layout.br(1);
        System.out.println("              Digite o tipo de conta desejado: ");
        System.out.println("         [1] Corrente     [2] Poupança     [3] Ambas");
        layout.bottomLine(3);
        layout.br(1);

        // LAYOUT ESCOLHA
        layout.topLine(3);
        layout.br(1);
        String tipoDeConta = Layout.entry("  Escolha: ");
        layout.bottomLine(3);
        layout.br(1);

        //CADASTRO DE USUÁRIO COM AS VARIÁVEIS DECLARADAS NO ATO DO CADASTRO
        CadastroBo.cadastrarUsuario
                        (nome, cpf, rg, senha, cidade, estado, bairro, numeroRua,
                                rua, cep, email, telefone, tipoDeConta);

        // INFORME DE CADASTRO
        layout.loading(3);
        layout.limparTela();
        System.out.println("Você foi cadastrado com sucesso!");

        // INFORMA QUAL O NÚMERO RANDOMICO GERADO PARA AS CONTAS CORRENTE E POUPANÇA
        if(Bd.contaCorrentesMap.size() > 0 && Bd.contaPoupancasMap.size() > 0){
            System.out.println("O número gerado para sua conta corrente é: "
                    + Bd.contaCorrentesMap.get((Bd.contaCorrentesMap.size()-1)).getConta());

            System.out.println("O número gerado para sua conta poupança é: "
                    + Bd.contaPoupancasMap.get((Bd.contaPoupancasMap.size()-1)).getConta());

        }

        // INFORMA QUAL O NÚMERO RANDÔMICO GERADO PARA A CONTA CORRENTE
        else if(Bd.contaCorrentesMap.size() > 0){
            System.out.println("O número gerado para sua conta corrente é: "
                    + Bd.contaCorrentesMap.get((Bd.contaCorrentesMap.size()-1)).getConta());

        }

        // INFORMA QUAL O NÚMERO RANDÔMICO GERADO PARA A CONTA POUPANÇA
        else if(Bd.contaPoupancasMap.size() > 0){
            System.out.println("O número gerado para sua conta corrente é: "
                    + Bd.contaPoupancasMap.get((Bd.contaPoupancasMap.size()-1)).getConta());
        }

        // ENCAMINHA O USUÁRIO PARA O MENU LOGIN
        menuLogin();
    }

    // MENU CONTAS
    public static void menuContas(){

        // VARIÁVEIS DO menuContas()
        String n;

        // LAYOUT DE TÍTULO
        layout.topLine(2);
        layout.br(1);
        System.out.println("    =-=-=-=-=-=Suas contas=-=-=-=-=-=");

        // LAYOUT
        layout.bottomLine(2);
        layout.br(1);
        layout.topLine(2);
        layout.br(1);

        // SE O CLIENTE TIVER CONTA CORRENTE E CONTA POUPANÇA
        if(Bd.clienteBuscaContaCorrente != null && Bd.clienteBuscaContaPoupanca != null){

            // MENU DE OPÇÕES
            System.out.println("    [1] Conta corrente n° " + Bd.clienteBuscaContaCorrente.getConta());
            System.out.println("    [2] Conta poupança n° " + Bd.clienteBuscaContaPoupanca.getConta());
            System.out.println("    [3] Logout");

            do{

                // LAYOUT
                layout.bottomLine(2);
                layout.br(1);
                layout.topLine(2);
                layout.br(1);

                // CAMPO DE ENTRADA DO USUÁRIO
                n = Layout.entry("    Escolha: ");

                // LAYOUT
                layout.bottomLine(2);
                layout.br(1);
                layout.loading(2);
                layout.limparTela();

                // SE O CLIENTE ACESSAR O SISTEMA UTILIZANDO SUA CONTA CORRENTE
                if(n.equals("1")){
                    // MENU PRINCIPAL ACIONADO COM CONTA DE TIPO 1 (CORRENTE)
                    menuPrincipal("1");
                }
                // SE O CLIENTE ACESSAR O SISTEMA UTILIZANDO SUA CONTA POUPANÇA
                else if(n.equals("2")){
                    // MENU PRINCIPAL ACIONADO COM CONTA DE TIPO 2 (POUPANÇA)
                    menuPrincipal("2");
                }
                // SE O CLIENTE RESOLVER RETORNAR AO MENU DE ACESSO DO SISTEMA
                else{
                    // INSTÂNCIAS SÃO SETADAS COMO NULL
                    Bd.zerarInstancias();
                    login = null;
                    menuAcesso();
                }
            }while(Integer.parseInt(n) < 1 || Integer.parseInt(n) > 3);

        }

        // SE O CLIENTE TIVER SOMENTE CONTA POUPANÇA
        else if(Bd.clienteBuscaContaCorrente == null){

            // MENU DE OPÇÕES
            System.out.println("    [1] Conta poupança n° " + Bd.clienteBuscaContaPoupanca.getConta());
            System.out.println("    [2] Logout");

            do {

                // LAYOUT
                layout.bottomLine(2);
                layout.br(1);
                layout.topLine(2);
                layout.br(1);

                // ENTRADA DO USUÁRIO
                n = Layout.entry("    Escolha: ");

                // LAYOUT
                layout.bottomLine(2);
                layout.br(1);
                layout.loading(2);
                layout.limparTela();

                // SE O CLIENTE RESOLVER ACESSAR O SISTEMA COM SUA CONTA POUPANÇA
                if(n.equals("1")){
                    menuPrincipal("2");
                }
                // SE O CLIENTE RESOLVER RETORNAR AO MENU DE ACESSO DO SISTEMA
                else{
                    // ZERA AS INSTÂNCIAS DO USUÁRIO, DECLARANDO OS VALORES COMO NULL
                    login.setAtivo(false);
                    Bd.zerarInstancias();
                    login = null;
                    menuAcesso();
                }
            }while(Integer.parseInt(n) < 1 || Integer.parseInt(n) > 2);

        }

        // SE O CLIENTE TIVER SOMENTE CONTA CORRENTE
        else {

            // MENU DE OPÇÕES
            System.out.println("    [1] Conta corrente n° " + Bd.clienteBuscaContaCorrente.getConta());
            System.out.println("    [2] Logout");

             do {

                 // LAYOUT
                layout.bottomLine(2);
                layout.br(1);
                layout.topLine(2);
                layout.br(1);

                // ENTRADA DO USUÁRIO
                n = Layout.entry("    Escolha: ");

                // LAYOUT
                layout.bottomLine(2);
                layout.br(1);
                layout.loading(2);
                layout.limparTela();

                // SE O USUÁRIO ESCOLHER ACESSAR O SISTEMA UTILIZANDO SUA CONTA CORRENTE
                if(n.equals("1")){
                    menuPrincipal("1");
                }
                // SE O USUÁRIO ESCOLHER RETORNAR AO MENU DE ACESSO DO SISTEMA
                else{
                    // ZERA AS INSTÂNCIAS, DECLARANDO OS VALORES COMO NULL
                    login.setAtivo(false);
                    Bd.zerarInstancias();
                    login = null;
                    menuAcesso();
                }
             } while(Integer.parseInt(n) < 1 || Integer.parseInt(n) > 2);
        }

    }

    /* --------------------------------------- FIM - MENU ACESSO --------------------------------------------- */

    /* ----------------------------------- INÍCIO - MENU PRINCIPAL ------------------------------------------ */

    // MENU PRINCIPAL
    public static void menuPrincipal(String ct){

        // VARIÁVEIS DO menuPrincipal()
        String n;

        // Conta corrente
        if(Integer.parseInt(ct) == 1){

            do {

                // LAYOUT DO TÍTULO
                layout.topLine(3);
                layout.br(1);
                System.out.println("        =-=-=-=-=-=Conta corrente de " +
                        Bd.clienteBuscaContaCorrente.getCliente().getNome() + "=-=-=-=-=-=");

                // LAYOUT
                layout.bottomLine(3);
                layout.br(1);
                layout.topLine(3);
                layout.br(1);

                // INFORMAÇÕES DA CONTA DO CLIENTE
                System.out.print("  AG: " + Bd.clienteBuscaContaCorrente.getAgencia()); // MOSTRA AGENCIA
                System.out.print(" || CT: " + Bd.clienteBuscaContaCorrente.getConta()); // MOSTRA N° DA CONTA
                System.out.print(" || Tipo: " + Bd.clienteBuscaContaCorrente.getContaTipo()); // MOSTRA TIPO DA CONTA
                System.out.print(" || Saldo: ");
                System.out.printf("R$ %.2f", Bd.clienteBuscaContaCorrente.getSaldo()); // MOSTRA SALDO

                // LAYOUT
                layout.br(1);
                layout.centralLine(3);
                layout.br(1);

                // OPÇÕES DO MENU
                System.out.println("      [1] Depositar      [2] Sacar          [3] Cartões");
                System.out.println("      [4] Pix            [5] Seguros        [6] Voltar");
                layout.bottomLine(3);
                layout.br(1);

                // ENTRADA DO USUÁRIO
                layout.topLine(3);
                layout.br(1);
                n = Layout.entry("    Escolha: ");
                layout.bottomLine(3);
                layout.br(1);

                // APÓS O USUÁRIO ENTRAR UM VALOR, MOSTRA BARRA DE CARREGAMENTO E LIMPA A TELA
                layout.loading(3);
                layout.limparTela();

                // SE O CLIENTE ESCOLHER REALIZAR UM DEPÓSITO
                if(Integer.parseInt(n) == 1) {
                    ContaCorrenteBo.Deposito(menuDeposito());
                    layout.bottomLine(2);
                    layout.br(1);
                    layout.loading(2);
                    layout.limparTela();
                    n = "0";
                }

                // SE O CLIENTE ESCOLHER REALIZAR UM SAQUE
                else if (Integer.parseInt(n) == 2) {
                    System.out.println(ContaCorrenteBo.Saque(menuSaque()));
                    layout.bottomLine(2);
                    layout.br(1);
                    layout.loading(2);
                    layout.limparTela();
                    n = "0";
                }

                // SE O CLIENTE ESCOLHER REALIZAR UMA TRANSFERÊNCIA
                else if (Integer.parseInt(n) == 3) {
                    menuCartoes(1);
                    n = "0";
                }

                // SE O CLIENTE ESCOLHER ACESSAR O MENU PIX
                else if (Integer.parseInt(n) == 4) {
                    menuPix(1);
                }

                // SE O CLIENTE ESCOLHER ACESSAR O MENU DE CARTÕES
                else if (Integer.parseInt(n) == 5) {
                    menuSeguros(1);
                }

                // SE O CLIENTE ESCOLHER VOLTAR PARA O MENU DE SELEÇÃO DE CONTAS
                else if (Integer.parseInt(n) == 6) {
                    menuContas();
                }

                // SE O CLIENTE INSERIR ALGUM VALOR NUMÉRICO INVÁLIDO
                else {
                    System.out.println("Entrada incorreta!");
                }

                // DESCONTO DA TAXA DE MANUTENÇÃO DA CONTA CORRENTE
                System.out.println("- " + Bd.clienteBuscaContaCorrente.descontarTaxa());

                // ATUALIZA TIPO DE CONTA PARA A QUE CORRESPONDER AO SALDO ATUAL

                // SE O SALDO FOR MENOR QUE 5K CONVERTE PARA CONTA COMUM
                if(Bd.clienteBuscaContaCorrente.getSaldo() < 5000){
                    Bd.clienteBuscaContaCorrente.setContaTipo(ContaTipo.COMUM);
                }
                // SE O SALDO FOR > QUE 5K E < QUE 10K CONVERTE PARA CONTA PREMIUM
                else if(Bd.clienteBuscaContaCorrente.getSaldo() >= 5000 && Bd.clienteBuscaContaCorrente.getSaldo() < 10000){
                    Bd.clienteBuscaContaCorrente.setContaTipo(ContaTipo.PREMIUM);
                }
                // SE O SALDO FOR MAIOR QUE 10K CONVERTE PARACONTA SUPER
                else{
                    Bd.clienteBuscaContaCorrente.setContaTipo(ContaTipo.SUPER);
                }

            }while(Integer.parseInt(n) < 1 || Integer.parseInt(n) > 6);

        }

        // Conta poupança
        else if(Integer.parseInt(ct) == 2){

            do {

                // LAYOUT DO TÍTULO
                layout.topLine(3);
                layout.br(1);
                System.out.println("        =-=-=-=-=-=Conta poupança de " +
                        Bd.clienteBuscaContaPoupanca.getCliente().getNome() + "=-=-=-=-=-=");

                // LAYOUT
                layout.bottomLine(3);
                layout.br(1);
                layout.topLine(3);
                layout.br(1);

                // INFORMAÇÕES DA CONTA POUPANÇA DO CLIENTE
                System.out.print("  AG: " + Bd.clienteBuscaContaPoupanca.getAgencia()); // MOSTRA A AGENCIA
                System.out.print(" || Conta: " + Bd.clienteBuscaContaPoupanca.getConta()); // MOSTRA O N° DA CONTA
                System.out.print(" || Tipo: " + Bd.clienteBuscaContaPoupanca.getContaTipo()); // MOSTRA O TIPO DA CONTA
                // Informações do saldo
                System.out.print(" || Saldo: ");
                System.out.printf("R$ %.2f", Bd.clienteBuscaContaPoupanca.getSaldo()); // MOSTRA O SALDO

                // LAYOUT
                layout.br(1);
                layout.centralLine(3);
                layout.br(1);

                // OPÇÕES DO MENU
                System.out.println("      [1] Depositar      [2] Sacar          [3] Cartões");
                System.out.println("      [4] Menu Pix       [5] Seguros        [6] Voltar");
                layout.bottomLine(3);
                layout.br(1);

                // ENTRADA DO USUÁRIO
                layout.topLine(3);
                layout.br(1);
                n = Layout.entry("    Escolha: ");
                layout.bottomLine(3);
                layout.br(1);

                // APÓS O USUÁRIO ENTRAR UM VALOR, FAZ APARECER UMA BARRINHA DE LOADING E LIMPA A TELA DO SISTEMA
                layout.loading(3);
                layout.limparTela();

                // SE O CLIENTE ESCOLHER REALIZAR UM DEPÓSITO
                if (Integer.parseInt(n) == 1) {
                    ContaPoupancaBo.Deposito(menuDeposito());
                    layout.bottomLine(2);
                    layout.br(1);
                    layout.loading(2);
                    layout.limparTela();
                    n = "0";
                }

                // SE O CLIENTE ESCOLHER REALIZAR UM SAQUE
                else if (Integer.parseInt(n) == 2) {
                    System.out.println(ContaPoupancaBo.Saque(menuDeposito()));
                    layout.bottomLine(2);
                    layout.br(1);
                    layout.loading(2);
                    layout.limparTela();
                    n = "0";
                }

                // SE O CLIENTE ESCOLHER REALIZAR UMA TRANSFERÊNCIA
                else if (Integer.parseInt(n) == 3) {
                    menuCartoes(1);
                    n = "0";
                }

                // SE O CLIENTE ESCOLHER ACESSAR O MENU PIX
                else if (Integer.parseInt(n) == 4) {
                    menuPix(2);
                }

                // SE O CLIENTE ESCOLHER ACESSAR O MENU CARTÕES
                else if (Integer.parseInt(n) == 5) {
                    menuSeguros(2);
                }

                // SE O CLIENTE ESCOLHER VOLTAR AO MENU DE SELEÇÃO DE CONTAS
                else if (Integer.parseInt(n) == 6) {
                    menuContas();
                }

                // SE O CLIENTE ENTRAR COM ALGUM VALOR NUMÉRICO INVÁLIDO
                else {
                    System.out.println("Entrada incorreta!");
                }

                // ACRESCENTA RENDIMENTO À SUA CONTA POUPANÇA
                System.out.println("- " + Bd.clienteBuscaContaPoupanca.acrescentarRendimento());

                // ATUALIZA TIPO DE CONTA PARA A QUE CORRESPONDER AO SALDO ATUAL

                // SE O SALDO FOR MENOR QUE 5K CONVERTE PARA CONTA COMUM
                if(Bd.clienteBuscaContaPoupanca.getSaldo() < 5000){
                    Bd.clienteBuscaContaPoupanca.setContaTipo(ContaTipo.COMUM);
                }
                // SE O SALDO FOR > QUE 5K E < QUE 10K CONVERTE PARA CONTA PREMIUM
                else if(Bd.clienteBuscaContaPoupanca.getSaldo() > 5000 &&
                        Bd.clienteBuscaContaPoupanca.getSaldo() < 10000){
                    Bd.clienteBuscaContaPoupanca.setContaTipo(ContaTipo.PREMIUM);
                }
                // SE O SALDO FOR MAIOR QUE 10K CONVERTE PARACONTA SUPER
                else{
                    Bd.clienteBuscaContaPoupanca.setContaTipo(ContaTipo.SUPER);
                }

            } while(Integer.parseInt(n) < 1 || Integer.parseInt(n) > 6);

        }

    }

    // MENU DEPOSITO
    public static Float menuDeposito(){

        // LAYOUT DO TÍTULO
        layout.topLine(2);
        layout.br(1);
        System.out.println("      =-=-=-=-=-=Depósito=-=-=-=-=-=");
        layout.bottomLine(2);
        layout.br(1);

        // ENTRADA DO USUÁRIO
        layout.topLine(2);
        layout.br(1);
        System.out.println("  [Digite 0 para sair]");
        return(Float.valueOf(Layout.entry("  Digite o valor do depósito: R$ ")));


    }

    // MENU SAQUE
    public static Float menuSaque(){

        // LAYOUT DO TÍTULO
        layout.topLine(2);
        layout.br(1);
        System.out.println("         =-=-=-=-=-=Saque=-=-=-=-=-=");
        layout.bottomLine(2);
        layout.br(1);

        // ENTRADA DO USUÁRIO
        layout.topLine(2);
        layout.br(1);
        System.out.println("  [Digite 0 para sair]");
        return(Float.valueOf(Layout.entry("  Digite o valor do saque: R$ ")));

    }

    // MENU PIX
    public static void menuPix(Integer contaTipo){

        // VARIÁVEIS DO MENU PIX
        String n;

        // LAYOUT DO TÍTULO
        layout.topLine(3);
        layout.br(1);
        System.out.println("                   =-=-= Menu PIX=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // OPÇÕES DO USUÁRIO
        layout.topLine(3);
        layout.br(1);
        System.out.println("    [1] Cadastrar      [2] Apagar chave      [3] Transferir");
        System.out.println("    [4] Consultar      [5] Voltar");
        layout.bottomLine(3);
        layout.br(1);

        // REPETE CASO A ENTRADA DO USUÁRIO SEJA UM VALOR NUMÉRICO INVÁLIDO
        do {

            // ENTRADA DO USUÁRIO
            layout.topLine(3);
            layout.br(1);
            n = Layout.entry("    Escolha: ");
            layout.bottomLine(3);
            layout.br(1);

            // APÓS O USUÁRIO ENTRAR UM VALOR APARECE UMA BARRA DE LOADING E LIMPA A TELA DO SISTEMA
            layout.loading(3);
            layout.limparTela();

            // SE O CLIENTE ESCOLHER ACESSAR O MENU DE CADASTRO PIX
            if(Integer.parseInt(n) == 1){
                menuCadastroPix(contaTipo);
                layout.loading(3);
                layout.limparTela();
                menuPix(contaTipo);
            }
            // SE O CLIENTE ESCOLHER ACESSAR O MENU DE APAGAR PIX
            else if(Integer.parseInt(n) == 2){
                menuApagaPix(contaTipo);
                layout.loading(3);
                layout.limparTela();
                menuPix(contaTipo);
            }
            // SE O CLIENTE ESCOLHER ACESSAR O MENU DE TRANSFERÊNCIA PIX
            else if(Integer.parseInt(n) == 3){
                menuTransferePix(contaTipo);
                layout.loading(3);
                layout.limparTela();
                menuPix(contaTipo);
            }
            // SE O CLIENTE ESCOLHER ACESSAR O MENU DE CONSULTA PIX
            else if(Integer.parseInt(n) == 4){
                menuConsultaPix(contaTipo);
                layout.loading(3);
                layout.limparTela();
                menuPix(contaTipo);
            }
            // SE O CLIENTE ESCOLHER VOLTAR AO MENU PRINCIPAL
            else if(Integer.parseInt(n) == 5){
                layout.loading(3);
                layout.limparTela();
                menuPrincipal(contaTipo.toString());
            }

        } while(Integer.parseInt(n) < 0 || Integer.parseInt(n) > 5);
    }

    // MENU CARTÕES
    public static void menuCartoes(Integer tipoConta){

        // VARIÁVEIS DO menuCartoes()
        int n;

        // LAYOUT DO TÍTULO
        layout.topLine(3);
        layout.br(1);
        System.out.println("                  =-=-= Menu Cartões=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // OPÇÕES DO USUÁRIO
        layout.topLine(3);
        layout.br(1);
        System.out.println("       [1] Meus cartões   [2] Novo cartão   [3] Sair");
        layout.bottomLine(3);
        layout.br(1);

        // REPETE CASO O USUÁRIO INSIRA UM VALOR NUMÉRICO INVÁLIDO
        do {

            // ENTRADA DO USUÁRIO
            layout.topLine(3);
            layout.br(1);
            n = Integer.parseInt(Layout.entry("    Escolha: "));
            layout.bottomLine(3);
            layout.br(1);

            // APÓS O USUÁRIO ENTRAR UM DADO, CARREGA UMA BARRINHA DE LOADING E LIMPA A TELA
            layout.loading(3);
            layout.limparTela();

            // SE A ENTRADA DO USUÁRIO FOR VÁLIDA, SISTEMA DIRECIONA PARA QUAL MENU SERÁ ACESSADO
            if(n > 0 && n < 4){

                // SE USUÁRIO ESCOLHER ACESSAR MENU QUE LISTA SEUS CARTÕES
                if(n == 1){
                    menuMeusCartoes(tipoConta);
                }
                // SE USUÁRIO ESCOLHER ACESSAR MENU QUE CADASTRA NOVO CARTÃO
                else if(n == 2){
                    menuNovoCartao(tipoConta);
                }
                // SE USUÁRIO ESCOLHER RETORNAR AO MENU PRINCIPAL
                else{
                    menuPrincipal(String.valueOf(tipoConta));
                }
            }
            // SE A ENTRADA DO USUÁRIO FOR INVÁLIDA RETORNA MENSAGEM DE ERRO NO CONSOLE
            else{
                System.out.println("    Entrada incorreta!");
            }
        }while(n < 1 || n > 3);

    }

    // MENU SEGUROS
    public static void menuSeguros(Integer tipoConta){

        // VARIÁVEIS DE menuSeguros()
        int n;

        // LAYOUT DO TÍTULO
        layout.topLine(3);
        layout.br(1);
        System.out.println("                  =-=-= Menu Seguros=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // OPÇÕES DO USUÁRIO
        layout.topLine(3);
        layout.br(1);
        System.out.println("         [1] Meus seguros   [2] Contratar   [3] Sair");
        layout.bottomLine(3);
        layout.br(1);

        // REPETE SE USUÁRIO INSERIR VALOR NUMÉRICO INVÁLIDO
        do {

            // ESCOLHA DO USUÁRIO
            layout.topLine(3);
            layout.br(1);
            n = Integer.parseInt(Layout.entry("    Escolha: "));
            layout.bottomLine(3);
            layout.br(1);

            // AO USUÁRIO INSERIR VALOR, CARREGA UMA BARRINHA E LIMPA A TELA
            layout.loading(3);
            layout.limparTela();

            // SE O VALOR INSERIDO PELO USUÁRIO FOR VÁLIDO, USUÁRIO É LIBERADO PARA O MENU ESCOLHIDO
            if(n > 0 && n < 4){

                // SE USUÁRIO ESCOLHER ACESSAR MENU QUE LISTA SEUS SEGUROS
                if(n == 1){
                    menuMeusSeguros(tipoConta);
                    layout.loading(3);
                    layout.limparTela();
                    menuSeguros(tipoConta);
                }
                // SE USUÁRIO ESCOLHER ACESSAR MENU DE CONTRATAÇÃO DE SEGUROS
                else if(n == 2){
                    menuContratarSeguros(tipoConta);
                    layout.loading(3);
                    layout.limparTela();
                    menuSeguros(tipoConta);
                }
                // SE USUÁRIO ESCOLHER RETORNAR AO MENU PRINCIPAL DO SISTEMA
                else{
                    menuPrincipal(String.valueOf(tipoConta));
                }
            }
            // SE O VALOR INSERIDO PELO USUÁRIO FOR INVÁLIDO, SISTEMA IRÁ RETORNAR MENSAGEM DE ERRO NO CONSOLE
            else{
                System.out.println("    Entrada incorreta!");
            }
        }while(n < 1 || n > 3);

    }

    /* --------------------------------------- FIM - MENU PRINCIPAL ------------------------------------------- */

    /* --------------------------------------- INÍCIO - PARTE PIX --------------------------------------------- */

    // MENU DE CADASTRO DE PIX
    public static void menuCadastroPix(Integer contaTipo){

        // VARIÁVEIS
        String n;

        // LAYOUT TÍTULO
        layout.topLine(3);
        layout.br(1);
        System.out.println("              =-=-= Menu cadastro PIX=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // LAYOUT OPÇÕES
        layout.topLine(3);
        layout.br(1);
        System.out.println("        [1] CPF      [2] Email      [3] Telefone");
        System.out.println("        [4] Voltar");
        layout.bottomLine(3);
        layout.br(1);

        // SE O USUÁRIO INSERIR UMA ENTRADA NUMÉRICA INVÁLIDA
        do {

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

        }while(Integer.parseInt(n) < 0 || Integer.parseInt(n) > 4);
    }

    // MENU APAGA PIX
    public static void menuApagaPix(Integer contaTipo){

        // LAYOUT DO TÍTULO
        layout.topLine(3);
        layout.br(1);
        System.out.println("                =-=-= Deletar chave PIX=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // SE O MÉTODO DE CONSULTAR PIX DO CLIENTE DETECTAR QUE O CLIENTE TEM UM PIX, ELE SEGUE EM FRENTE
        if(PixBo.consultarChavesPixCliente(contaTipo, true) != null) {

            // ESCOLHA DO USUÁRIO
            layout.topLine(3);
            layout.br(1);
            String n = Layout.entry("    Escolha: ");
            layout.bottomLine(3);
            layout.br(1);

            // SE A CONTA DO CLIENTE É CONTA CORRENTE
            if (contaTipo == 1) {
                // INVOCA MÉTODO DE APAGAR PIX DE ACORDO COM ESCOLHA DO USUÁRIO PRINTANDO O RETORNO DO MÉTODO
                System.out.println(Bd.pixDelete(1, Integer.parseInt(n)));
            }

            // SE A CONTA DO CLIENTE É CONTA POUPANÇA
            else {
                // INVOCA MÉTODO DE APAGAR PIX DE ACORDO COM ESCOLHA DO USUÁRIO PRINTANDO O RETORNO DO MÉTODO
                System.out.println(Bd.pixDelete(2, Integer.parseInt(n)));
            }
        }



    }

    // MENU TRANSFERE PIX
    public static void menuTransferePix(Integer contaTipo){

        // LAYOUT DO TÍTULO
        layout.topLine(3);
        layout.br(1);
        System.out.println("              =-=-= Transferência via PIX =-=-=");
        layout.bottomLine(3);
        layout.br(1);
        layout.topLine(3);
        layout.br(1);

        // ENTRADAS DO USUÁRIO
        String transfChave = Layout.entry("    Chave de transferência: ");
        Float transfValor = Float.parseFloat(Layout.entry("    Valor da transferência: R$ "));

        // LAYOUT
        layout.bottomLine(3);
        layout.br(1);

        // INVOCA MÉTODO DE TRANSFERÊNCIA VIA PIX E PRINTA SEU RETORNO COM BASE NAS ENTRADAS DE CHAVE E VALOR DO USUÁRIO
        layout.topLine(3);
        layout.br(1);
        System.out.println(PixBo.transferir(transfChave, transfValor, contaTipo));
        layout.bottomLine(3);
        layout.br(1);

        // CARREGA BARRA DE LOADING E LIMPA A TELA
        layout.loading(3);
        layout.limparTela();

    }

    // MENU CONSULTA PIX
    public static void menuConsultaPix(Integer contaTipo){

        // LAYOUT DO TÍTULO
        layout.topLine(3);
        layout.br(1);
        System.out.println("              =-=-= Menu consulta PIX=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // CONSULTA AS CHAVES PIX DO CLIENTE ATUAL
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

        // DECLARAÇÃO DE VARIÁVEIS
        int n, tpCartao = 0;
        Map<Integer, Cartao> listar = (CartaoBo.listarCartoesDoCliente(tipoConta)); // HASHMAP COM TIPO DE CARTÕES E CTS

        // LAYOUT DO TÍTULO
        layout.topLine(3);
        layout.br(1);
        System.out.println("                 =-=-= Meus Cartões =-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // LAYOUT
        layout.topLine(3);
        layout.br(1);

        // SE A LISTA DE CARTÕES DO CLIENTE NÃO ESTIVER VAZIA
        if(!listar.isEmpty()) {

            // RODA A LISTA UM POR UM E IMPRIME NA TELA
            for (Map.Entry<Integer, Cartao> entry : listar.entrySet()) {

                // PRINTA NESSA ORDEM: [ORDEM] CRÉDITO/DÉBITO || N° NÚMERO DO CARTÃO || ATIVO/INATIVO
                System.out.print("    [" + entry.getKey() + "] "
                        + entry.getValue().getTipoCartao() + " || n° "
                        + entry.getValue().getNumeroCartao() + " || "
                        + "Status: ");
                        // SE O CARTÃO ESTIVER ATIVO PRINTA ATIVO
                        if(entry.getValue().isAtivo()){
                            System.out.println("ATIVO");
                        }
                        // SE O CARTÃO NÃO ESTIVER ATIVO, PRINTA INATIVO
                        else{
                            System.out.println("INATIVO");
                        }
            }

            // LAYOUT
            layout.bottomLine(3);
            layout.br(1);

            // REPETIÇÃO PARA ANULAR ENTRADAS INCORRETAS
            do {
                // OPÇÃO DO USUÁRIO
                layout.topLine(3);
                layout.br(1);
                n = Integer.parseInt(Layout.entry("    Escolha: "));
                layout.bottomLine(3);
                layout.br(1);

                // CARREGA BARRA DE LOADING E LIMPA A TELA
                layout.loading(3);
                layout.limparTela();

                // SE O CARTÃO FOR DE DÉBITO, ELE É TIPO 1
                if(listar.get(n).getTipoCartao().equals(TipoCartao.DEBITO)){
                    tpCartao = 1;
                }
                // SE O CARTÃO FOR DE CRÉDITO, ELE É TIPO 2
                else if(listar.get(n).getTipoCartao().equals(TipoCartao.CREDITO)){
                    tpCartao = 2;
                }

                /* -> INVOCA O MENU DE OPÇÕES DO CARTÃO SELECIONADO, PASSANDO COMO PARÂMETROS OS TIPOS DA CONTA E O TIPO
                   -> DO CARTÃO                                                                                       */
                menuCartaoSelecionado(tipoConta, tpCartao);

            }while(n < 1 || n > listar.size());

        }
        // SE A LISTA DE CARTÕES DO CLIENTE ESTIVER VAZIA
        else{
            System.out.println("    Você não tem nenhum cartão cadastrado no momento");

            // CARREGA BARRA DE LOADING E LIMPA A TELA
            layout.loading(3);
            layout.limparTela();

            // RETORNA AO MENU DOS CARTÕES
            menuCartoes(tipoConta);
        }

    }

    // CRIAÇÃO DE NOVO CARTÃO
    public static void menuNovoCartao(Integer tipoConta){

        // DECLARAÇÃO DE VARIÁVEIS
        int n;

        // LAYOUT DO TÍTULO
        layout.topLine(3);
        layout.br(1);
        System.out.println("               =-=-= Cadastrar novo cartão =-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // OPÇÕES DO CLIENTE
        layout.topLine(3);
        layout.br(1);
        System.out.println("            [1] Débito   [2] Crédito   [3] Sair");
        layout.bottomLine(3);
        layout.br(1);

        // REPETIDOR PARA ANULAR ENTRADAS NUMÉRICAS INCORRETAS
        do {

            // ESCOLHA DO CLIENTE
            layout.topLine(3);
            layout.br(1);
            n = Integer.parseInt(Layout.entry("    Escolha: "));
            layout.bottomLine(3);
            layout.br(1);

            // CARREGA BARRA DE LOADING E LIMPA A TELA
            layout.loading(3);
            layout.limparTela();

            // SE O USUÁRIO DIGITAR ALGUM VALOR NUMÉRICO MENOR DO QUE 1 E MAIOR DO QUE 3
            if(n < 1 || n > 3){
                System.out.println("Entrada inválida");
            }
            // SE O USUÁRIO ESCOLHER CRIAR UM CARTÃO DE DÉBITO
            else if(n == 1){
                System.out.println(CartaoBo.validaInserirCartoesDebito(tipoConta));
            }
            // SE O USUÁRIO ESCOLHER CRIAR UM CARTÃO DE CRÉDITO
            else if(n == 2){
                System.out.println(CartaoBo.validaInserirCartoesCredito(tipoConta));
            }
            // SE O USUÁRIO ESCOLHER RETORNAR AO MENU DOS CARTÕES
            else{
                menuCartoes(tipoConta);
            }
        }while(n < 1 || n > 3);
        menuCartoes(tipoConta);
    }

    // MENU DO CARTÃO SELECIONADO ( PARAMS: TIPO CARTÃO 1 - DÉBITO || TIPO CARTÃO 2 - CRÉDITO)
    public static void menuCartaoSelecionado(Integer tipoConta, Integer tipoCartao){

        // DECLARAÇÃO DE VARIÁVEIS
        int n;

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

                    // layout
                    layout.bottomLine(3);
                    layout.br(1);

                    // REPETIDOR PARA ANULAR ENTRADAS NUMÉRICAS INCORRETAS
                    do {

                        // ESCOLHA DO USUÁRIO
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);

                        // CARREGA BARRA DE LOADING E LIMPA A TELA
                        layout.loading(3);
                        layout.limparTela();

                        // DESATIVAR CARTÃO DE DÉBITO CC
                        if(n == 1){
                            System.out.println(DebitoBo.ativaOuDesativaCartao(tipoConta, false));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // COMPRAR COM O CARTÃO DE DÉBITO CC
                        else if(n == 2){
                            menuCartaoCompra(tipoConta, tipoCartao);
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

                    // OPÇÕES DO USUÁRIO
                    System.out.println("       [1] Ativar   [2] Sair");

                    // LAYOUT
                    layout.bottomLine(3);
                    layout.br(1);

                    // REPETIDOR PARA ANULAR ENTRADAS NUMÉRICAS INVÁLIDAS
                    do {

                        // ESCOLHAS DO USUÁRIO
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);

                        // CARREGA BARRA DE LOADING EL IMPA A TELA
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
                System.out.print("    Numero: " + Bd.clienteBuscaContaPoupanca
                        .getCartoesDebitoCliente()
                        .get(0)
                        .getNumeroCartao());

                System.out.print("  ||  Bandeira: " + Bd.clienteBuscaContaPoupanca
                        .getCartoesDebitoCliente()
                        .get(0)
                        .getBandeira());

                System.out.print("  ||  Limite: " + Layout.convertToReais(Bd.clienteBuscaContaPoupanca
                        .getCartoesDebitoCliente()
                        .get(0)
                        .getLimiteTransacao()));

                // SE O CARTÃO DE DÉBITO DA CP ESTIVER ATIVADO
                if(Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0).isAtivo()) {
                    System.out.println("  ||  Status: ATIVO");
                    layout.centralLine(3);
                    layout.br(1);

                    // OPÇÕES DO USUÁRIO
                    System.out.println("       [1] Desativar   [2] Comprar   [3] Extrato   [4] Sair");

                    // LAYOUT
                    layout.bottomLine(3);
                    layout.br(1);

                    // REPETIDOR PARA ANULAR ENTRADAS NUMÉRICAS INVÁLIDAS
                    do {

                        // ESCOLHA DO USUÁRIO
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);

                        // CARREGA BARRA DE LOADING E LIMPA A TELA
                        layout.loading(3);
                        layout.limparTela();

                        // DESATIVAR CARTÃO DE DÉBITO CP
                        if(n == 1){
                            System.out.println(DebitoBo.ativaOuDesativaCartao(tipoConta, false));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // COMPRAR COM O CARTÃO DE DÉBITO CP
                        else if(n == 2){
                            menuCartaoCompra(tipoConta, tipoCartao);
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

                    // ESCOLHA DO USUÁRIO
                    System.out.println("       [1] Ativar   [2] Sair");

                    // LAYOUT
                    layout.bottomLine(3);
                    layout.br(1);

                    // REPETIDOR PARA ANULAR ENTRADAS NUMÉRICAS INVÁLIDAS
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

                // INFORMAÇÕES DO CARTÃO DE CRÉDITO CC
                System.out.print("    Limite total: " + Layout.convertToReais(Bd.clienteBuscaContaCorrente
                        .getCartoesCreditoCliente().get(0).getLimite().getLimiteTotal()));

                System.out.println("  ||  Disponível: " + Layout.convertToReais(Bd.clienteBuscaContaCorrente
                        .getCartoesCreditoCliente().get(0).getLimite().getLimiteDisponivel()));

                // LAYOUT
                layout.centralLine(3);
                layout.br(1);

                // INFORMAÇÕES DO CARTÃO DE CRÉDITO CC
                System.out.print("    Numero: " + Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0)
                        .getNumeroCartao());

                System.out.print("  ||  Bandeira: " + Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0)
                        .getBandeira());

                // SE O CARTÃO DE CRÉDITO CC ESTIVER ATIVADO
                if(Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0).isAtivo()) {

                    // PRINTA STATUS ATIVO
                    System.out.println("  ||  Status: ATIVO");
                    layout.bottomLine(3);
                    layout.br(1);

                    // LAYOUT
                    layout.topLine(3);
                    layout.br(1);

                    // OPÇÕES DO USUÁRIO
                    System.out.println("     [1] Desativar       [2] Comprar     [3] Ver Fatura");
                    System.out.println("     [4] Pagar fatura    [5] Sair");

                    // LAYOUT
                    layout.bottomLine(3);
                    layout.br(1);

                    // REPETIDOR PARA ANULAR ENTRADAS NUMÉRICAS INVÁLIDAS
                    do {

                        // ESCOLHA DO USUÁRIO
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);

                        // CARREGA BARRA DE LOADING E LIMPA A TELA
                        layout.loading(3);
                        layout.limparTela();

                        // DESATIVAR CARTÃO DE CRÉDITO CC
                        if(n == 1){
                            System.out.println(CreditoBo.ativaOuDesativaCartao(tipoConta, false));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // COMPRAR COM O CARTÃO DE CRÉDITO CC
                        else if(n == 2){
                            menuCartaoCompra(tipoConta, tipoCartao);
                            layout.loading(3);
                            layout.limparTela();
                            menuCartaoSelecionado(1, 2);
                        }
                        // VER FATURA DO CARTÃO DE CRÉDITO CC
                        else if(n == 3){
                            CreditoBo.retornaFatura(tipoConta);
                            layout.loading(3);
                            layout.limparTela();
                            menuCartaoSelecionado(1, 2);
                        }
                        // PAGAR FATURA DO CARTÃO DE CRÉDITO CC
                        else if(n == 4){
                            System.out.println(CreditoBo.pagarFatura(tipoConta));
                            layout.loading(3);
                            layout.limparTela();
                            menuCartaoSelecionado(1, 2);
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
                    layout.bottomLine(3);
                    layout.br(1);

                    // LAYOUT
                    layout.topLine(3);
                    layout.br(1);

                    // OPÇÕES DO USUÁRIO
                    System.out.println("       [1] Ativar   [2] Sair");

                    // LAYOUT
                    layout.bottomLine(3);
                    layout.br(1);

                    // REPETIDOR PARA ANULAR ENTRADAS NUMÉRICAS INVÁLIDAS
                    do {

                        // ESCOLHA DO USUÁRIO
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);

                        // CARREGA BARRA DE LOADING E LIMPA A TELA
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

                // EXIBE INFORMAÇÕES DO CARTÃO DE CRÉDITO CP
                System.out.print("    Limite total: " + Layout.convertToReais(Bd.clienteBuscaContaPoupanca
                        .getCartoesCreditoCliente().get(0).getLimite().getLimiteTotal()));

                System.out.println("  ||  Disponível: " + Layout.convertToReais(Bd.clienteBuscaContaPoupanca
                        .getCartoesCreditoCliente().get(0).getLimite().getLimiteDisponivel()));

                // LAYOUT
                layout.centralLine(3);
                layout.br(1);

                // EXIBE INFORMAÇÕES DO CARTÃO DE CRÉDITO CP
                System.out.print("    Numero: " + Bd.clienteBuscaContaPoupanca
                        .getCartoesCreditoCliente().get(0).getNumeroCartao());

                System.out.print("  ||  Bandeira: " + Bd.clienteBuscaContaPoupanca
                        .getCartoesCreditoCliente().get(0).getBandeira());


                // SE O CARTÃO DE CRÉDITO CP ESTIVER ATIVADO
                if(Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0).isAtivo()) {
                    System.out.println("  ||  Status: ATIVO");
                    layout.bottomLine(3);
                    layout.br(1);

                    // LAYOUT
                    layout.topLine(3);
                    layout.br(1);

                    // OPÇÕES
                    System.out.println("     [1] Desativar       [2] Comprar     [3] Ver Fatura");
                    System.out.println("     [4] Pagar fatura    [5] Sair");

                    // LAYOUT
                    layout.bottomLine(3);
                    layout.br(1);

                    // REPETIDOR PARA ANULAR ENTRADAS NUMÉRICAS INCORRETAS
                    do {

                        // ESCOLHA DO USUÁRIO
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);

                        // CARREGA BARRA DE LOADING E LIMPA A TELA
                        layout.loading(3);
                        layout.limparTela();

                        // DESATIVAR CARTÃO DE CRÉDITO CP
                        if(n == 1){
                            System.out.println(CreditoBo.ativaOuDesativaCartao(tipoConta, false));
                            menuCartaoSelecionado(tipoConta, tipoCartao);
                        }
                        // COMPRAR COM O CARTÃO DE CRÉDITO CP
                        else if(n == 2){
                            menuCartaoCompra(tipoConta, tipoCartao);
                            layout.loading(3);
                            layout.limparTela();
                            menuCartaoSelecionado(2, 2);
                        }
                        // VER FATURA DO CARTÃO DE CRÉDITO CP
                        else if(n == 3){
                            CreditoBo.retornaFatura(tipoConta);
                            layout.loading(3);
                            layout.limparTela();
                            menuCartaoSelecionado(2, 2);
                        }
                        // PAGAR FATURA DO CARTÃO DE CRÉDITO CP
                        else if(n == 4){
                            System.out.println(CreditoBo.pagarFatura(tipoConta));
                            layout.loading(3);
                            layout.limparTela();
                            menuCartaoSelecionado(2, 2);
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
                    layout.bottomLine(3);
                    layout.br(1);

                    // LAYOUT
                    layout.topLine(3);
                    layout.br(1);

                    // OPÇÕES DO USUÁRIO
                    System.out.println("       [1] Ativar   [2] Sair");

                    // LAYOUT
                    layout.bottomLine(3);
                    layout.br(1);

                    // REPETIDOR PARA ANULAR ENTRADAS NUMÉRICAS INCORRETAS
                    do {

                        // LAYOUT
                        layout.topLine(3);
                        layout.br(1);
                        n = Integer.parseInt(Layout.entry("    Escolha: "));
                        layout.bottomLine(3);
                        layout.br(1);

                        // CARREGA BARRA DE LOADING E LIMPA A TELA
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

    // MENU DE COMPRA DOS CARTÕES
    public static void menuCartaoCompra(Integer tipoConta, Integer tipoCartao){

        // TÍTULO DO MENU COMPRA
        layout.topLine(3);
        layout.br(1);
        System.out.println("                 =-=-= Inserir Compra =-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // ENTRADAS DO USUÁRIO
        layout.topLine(3);
        layout.br(1);
        String nomeProduto = Layout.entry("    Digite o nome do produto: ");
        Float valorProduto = Float.parseFloat(Layout.entry("    Digite o valor do produto: R$ "));
        layout.bottomLine(3);
        layout.br(1);


        // SE O CARTÃO DA COMPRA FOR DE DÉBITO
        if(tipoCartao == 1) {
            System.out.println(DebitoBo.processaCompra(tipoConta, nomeProduto, valorProduto));
        }
        // SE O CARTÃO DA COMPRA FOR DE CRÉDITO
        else if(tipoCartao == 2){
            System.out.println(CreditoBo.processaCompra(tipoConta, nomeProduto, valorProduto));
        }

    }

    /* -------------------------------------- FIM - PARTE CARTÕES -------------------------------------------- */

    /* ------------------------------------- INÍCIO - PARTE SEGUROS ------------------------------------------ */

    // MENU DE CONTRATAÇÃO DE SEGUROS
    public static void menuContratarSeguros(Integer tipoConta){

        // DECLARAÇÃO DE VARIÁVEIS
        int n;

        // TÍTULO DO menuContratarSeguros
        layout.topLine(3);
        layout.br(1);
        System.out.println("                  =-=-= Menu Seguros=-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // SEGUROS E SUAS REGRAS -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

        // SEGURO DE VIDA
        layout.topLine(3);
        layout.br(1);
        layout.sleep(1000);
        System.out.println("    " + seguroVida.getNome());
        layout.br(1);
        layout.sleep(1000);
        System.out.println(seguroVida.getRegras());
        layout.sleep(1000);
        System.out.println("    " + "Preço: " + Layout.convertToReais(seguroVida.getValorApolice()) + " a.a");
        layout.sleep(1000);

        layout.centralLine(3);
        layout.br(1);
        layout.sleep(1000);

        // SEGURO INVALIDEZ
        layout.sleep(1000);
        System.out.println("    " + seguroInvalidez.getNome());
        layout.br(1);
        layout.sleep(1000);
        System.out.println(seguroInvalidez.getRegras());
        layout.sleep(1000);
        System.out.println("    " + "Preço: " + Layout.convertToReais(seguroInvalidez.getValorApolice()) + " a.a");
        layout.sleep(1000);

        layout.centralLine(3);
        layout.br(1);
        layout.sleep(1000);

        // SEGURO DESEMPREGO
        layout.sleep(1000);
        System.out.println("    " + seguroDesemprego.getNome());
        layout.br(1);
        layout.sleep(1000);
        System.out.println(seguroDesemprego.getRegras());
        layout.sleep(1000);
        System.out.println("    " + "Preço: " + Layout.convertToReais(seguroDesemprego.getValorApolice()) + " a.a");
        layout.sleep(1000);

        layout.bottomLine(3);
        layout.br(1);
        layout.sleep(1000);

        // OPÇÕES DO USUÁRIO
        layout.topLine(3);
        layout.br(1);
        System.out.println("         [1] Seguro de vida       [2] Seguro Invalidez   ");
        System.out.println("         [3] Seguro Desemprego    [4] Voltar");
        layout.bottomLine(3);
        layout.br(1);

        // REPETIDOR PARA ANULAR ENTRADAS INVÁLIDAS DO USUÁRIO
        do {

            // ESCOLHA DO USUÁRIO
            layout.topLine(3);
            layout.br(1);
            n = Integer.parseInt(Layout.entry("    Escolha: "));
            layout.bottomLine(3);
            layout.br(1);

            // CARREGA BARRA DE LOADING E LIMPA A TELA
            layout.loading(3);
            layout.limparTela();

            // SE O USUÁRIO DIGITAR UM VALOR MAIOR QUE 0 E MENOR QUE 5
            if(n > 0 && n < 5){

                //SE O USUÁRIO ESCOLHER CONTRATAR UM SEGURO DE VIDA
                if(n == 1){
                    System.out.println(ApoliceBo.contratarSeguro(tipoConta, seguroVida));
                    menuSeguros(tipoConta);
                }
                // SE O USUÁRIO ESCOLHER CONTRATAR UM SEGURO INVALIDEZ
                else if(n == 2){
                    System.out.println(ApoliceBo.contratarSeguro(tipoConta, seguroInvalidez));
                    menuSeguros(tipoConta);
                }
                // SE O USUÁRIO ESCOLHER CONTRATAR UM SEGURO DESEMPREGO
                else if(n == 3){
                    System.out.println(ApoliceBo.contratarSeguro(tipoConta, seguroDesemprego));
                    menuSeguros(tipoConta);
                }
                // SE O USUÁRIO ESCOLHER VOLTAR PARA O MENU DE SEGUROS
                else{
                    menuSeguros(tipoConta);
                }
            }
            // SE O USUÁRIO DIGITAR ALGUM VALOR <= 0 OU >= 5
            else{
                System.out.println("    Entrada incorreta!");
            }
        }while(n < 1 || n > 4);

    }

    // MENU EM QUE OS SEGUROS DO CLIENTE APARECEM NA TELA
    public static void menuMeusSeguros(Integer tipoConta){

        // DECLARAÇÃO DE VARIÁVEIS
        int n, tpSeguro = 0;
        Map<Integer, Apolice> listar = (ApoliceBo.listarSegurosDoCliente(tipoConta)); // HASHMAP COM SEGUROS DO CLIENTE

        // TÍTULO DO menuMeusSeguros
        layout.topLine(3);
        layout.br(1);
        System.out.println("                 =-=-= Meus seguros =-=-=");
        layout.bottomLine(3);
        layout.br(1);

        // LAYOUT
        layout.topLine(3);
        layout.br(1);

        // SE A LISTA DE SEGUROS DO CLIENTE NÃO ESTIVER VAZIA
        if(!listar.isEmpty()) {

            // RODA A LISTA UM POR UM
            for (Map.Entry<Integer, Apolice> entry : listar.entrySet()) {

                //   -> IMPRIME NO CONSOLE UM POR UM NO SEGUINTE MODELO: [ ORDEM ] NOME DO SEGURO
                System.out.println("    [" + entry.getKey() + "] " + entry.getValue().getNome());
            }

            // LAYOUT
            layout.bottomLine(3);
            layout.br(1);

            // REPETIÇÃO PARA ANULAR ENTRADAS NUMÉRICAS INCORRETAS
            do {

                // ESCOLHA DO USUÁRIO
                layout.topLine(3);
                layout.br(1);
                n = Integer.parseInt(Layout.entry("    Escolha: "));
                layout.bottomLine(3);
                layout.br(1);

                // CARREGA BARRA DE LOADING E LIMPA A TELA
                layout.loading(3);
                layout.limparTela();

                // INVOCA MENU DE OPÇÕES DO SEGURO SELECIONADO PELO CLIENTE
                menuSeguroSelecionado(tipoConta, listar.get(n));

            }while(n < 1 || n > listar.size());

        }
        // SE A LISTA DE CARTÕES DO CLIENTE ESTIVER VAZIA
        else{
            System.out.println("    Você não tem nenhum seguro contratado no momento");
            layout.bottomLine(3);
            layout.br(1);
        }

    }

    // MENU QUE APARECE NA TELA APÓS SELECIONAR ALGUM SEGURO
    public static void menuSeguroSelecionado(Integer tipoConta, Apolice tpSeguro){

        // DECLARAÇÃO DE VARIÁVEIS
        int n = 0;

        // LAYOUT TÍTULO DO SEGURO SELECIONADO
        layout.topLine(3);
        layout.br(1);
        System.out.printf("                 =-=-= %s =-=-=\n", tpSeguro.getNome());
        layout.bottomLine(3);
        layout.br(1);

        // Layout opções
        layout.topLine(3);
        layout.br(1);
        System.out.println("      [1] Cancelar      [2] Ver descrição      [3] Voltar");
        layout.bottomLine(3);
        layout.br(1);

        // REPETIDOR PARA ANULAR ENTRADAS NUMÉRICAS INCORRETAS
        do {

            // ESCOLHA DO USUÁRIO
            layout.topLine(3);
            layout.br(1);
            n = Integer.parseInt(Layout.entry("    Escolha: "));
            layout.bottomLine(3);
            layout.br(1);

            // CARREGA BARRA DE LOADING E LIMPA A TELA
            layout.loading(3);
            layout.limparTela();

            // CANCELAR O SEGURO
            if(n == 1){
                System.out.println(ApoliceBo.cancelarSeguro(tipoConta, tpSeguro));
                menuSeguros(tipoConta);
            }
            // VER A DESCRIÇÃO DO SEGURO
            else if(n == 2){
                menuSeguroVerDescricao(tpSeguro);
                layout.loading(3);
                layout.limparTela();
                menuSeguroSelecionado(tipoConta, tpSeguro);
            }
            // VOLTA AO MENU DE SEGUROS
            else if(n == 3){
                menuSeguros(tipoConta);
            }

        }while(n < 1 || n > 3);

    }

    // MENU ONDE VOCÊ PODE VER A DESCRIÇÃO DO SEGURO SELECIONADO PELO USUÁRIO
    public static void menuSeguroVerDescricao(Apolice tpSeguro){

        // LAYOUT TÍTULO DO SEGURO SELECIONADO
        layout.topLine(3);
        layout.br(1);
        System.out.printf("                 =-=-= %s =-=-=\n", tpSeguro.getNome());
        layout.bottomLine(3);
        layout.br(1);

        // EXIBE AS REGRAS DO SEGURO DO USUÁRIO
        layout.topLine(3);
        layout.br(1);
        System.out.println(tpSeguro.getRegras());
        layout.bottomLine(3);
        layout.br(1);

    }

    /* -------------------------------------- FIM - PARTE SEGUROS -------------------------------------------- */

}
