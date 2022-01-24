package bo;
import dao.Bd;
import model.cartao.TipoCartao;
import model.cartao.Transacao;
import model.cartao.credito.Credito;
import model.conta.ContaTipo;
import util.Layout;
import view.Main;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class CreditoBo {

    public static Credito credito;

    // FAZ A VALIDAÇÃO DE INSERÇÃO DE CARTÃO DE CRÉDITO
    public static String validaInsercaoCredito(Integer tipoConta){


        // VERIFICANDO TIPO DA CONTA PARA DEFINIR O LIMITE
        float limite = 0;
        if(Bd.clienteBuscaContaCorrente.getContaTipo().equals(ContaTipo.COMUM)){
            limite = 1000.00f;
        }else if(Bd.clienteBuscaContaCorrente.getContaTipo().equals(ContaTipo.PREMIUM)){
            limite = 5000.00f;
        }else if(Bd.clienteBuscaContaCorrente.getContaTipo().equals(ContaTipo.SUPER)){
            limite = 10000.00f;
        }

        // SE A CONTA FOR UMA CONTA CORRENTE
        if(tipoConta == 1) {
            // SE O CLIENTE AINDA NÃO TIVER CARTÕES DE CRÉDITO CADASTRADOS
            if(Bd.clienteBuscaContaCorrente.cartoesCreditoCliente.isEmpty()){
                // MÉTODO DE INSERÇÃO DO CARTÃO NOS DBS
                Credito credito = new Credito(Bd.clienteBuscaContaCorrente.getCliente(), limite, TipoCartao.CREDITO);
                Bd.insereCartaoCredito(credito, tipoConta);
                return("Cartão de Crédito cadastrado com sucesso!");
            }
            // SE O CLIENTE JÁ POSSUI CARTÃO DE CRÉDITO CADASTRADO
            else {
                return ("Você já possui um cartão de Crédito cadastrado");
            }
        }
        // SE A CONTA FOR UMA CONTA POUPANÇA
        else{
            // SE O CLIENTE AINDA NÃO TIVER CARTÕES DE CRÉDITO CADASTRADOS
            if(Bd.clienteBuscaContaPoupanca.cartoesCreditoCliente.isEmpty()){
                // MÉTODO DE INSERÇÃO DO CARTÃO NOS DBS
                Credito credito = new Credito(Bd.clienteBuscaContaPoupanca.getCliente(), limite, TipoCartao.CREDITO);
                Bd.insereCartaoCredito(credito, tipoConta);
                return("Cartão de Crédito cadastrado com sucesso!");
            }
            // SE O CLIENTE JÁ POSSUI CARTÃO DE CRÉDITO CADASTRADO
            else {
                return ("Você já possui um cartão de crédito cadastrado");
            }
        }
    }

    // ATIVAR/DESATIVAR CARTÃO
    public static String ativaOuDesativaCartao(Integer tipoConta, Boolean status){

        // SE A CONTA FOR CORRENTE
        if(tipoConta == 1){

            // SE FOR PRA ATIVAR
            if(status){
                Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0).setAtivo(true);
                return("Cartão de crédito ativado com sucesso");

            }
            // SE FOR PRA DESATIVAR
            else{
                Bd.clienteBuscaContaCorrente.getCartoesCreditoCliente().get(0).setAtivo(false);
                return("Cartão de crédito desativado com sucesso");
            }

        }
        // SE A CONTA FOR POUPANÇA
        else{

            // SE FOR PRA ATIVAR
            if(status){
                Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0).setAtivo(true);
                return("Cartão de crédito ativado com sucesso");
            }
            // SE FOR PRA DESATIVAR
            else{
                Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0).setAtivo(false);
                return("Cartão de crédito desativado com sucesso");
            }

        }

    }

    // COMPRAR
    public static String processaCompra(Integer tipoConta, String nomeItem, Float valorItem){

        // INSTANCIANDO NOVA DATA
        Date date = new Date();

        // SE FOR CONTA CORRENTE
        if(tipoConta == 1){

            //SE VALOR SE ADEQUAR AO LIMITE DA FATURA
            if(valorItem <= Bd.clienteBuscaContaCorrente
                    .getCartoesCreditoCliente()
                    .get(0)
                    .getLimite()
                    .getLimiteDisponivel()){

                // ATUALIZANDO UTILIZADO DO CARTÃO DE CRÉDITO DO CLIENTE
                Bd.clienteBuscaContaCorrente
                        .getCartoesCreditoCliente()
                        .get(0)
                        .getLimite()
                        .setLimiteUtilizado(
                                Bd.clienteBuscaContaCorrente
                                    .getCartoesCreditoCliente()
                                    .get(0)
                                    .getLimite()
                                    .getLimiteUtilizado()
                                            + valorItem);

                // ATUALIZANDO LIMITE DO CARTÃO DE CRÉDITO DO CLIENTE
                Bd.clienteBuscaContaCorrente
                        .getCartoesCreditoCliente()
                        .get(0)
                        .getLimite().atualizarLimite();

                    // INSTANCIANDO OBJETO TRANSAÇÃO
                Transacao transacao = new Transacao(date, nomeItem, valorItem,
                        Bd.clienteBuscaContaCorrente
                                .getCliente(),
                        Bd.clienteBuscaContaCorrente ,
                        Bd.clienteBuscaContaCorrente
                                .getCartoesCreditoCliente().get(0));

                    // ADICIONANDO TRANSAÇÃO AO BD
                Credito.salvarTransacao(transacao);

                return("Compra no valor de " + Layout.convertToReais(valorItem) + " realizada com sucesso. " +
                            "\nSeu limite atual: " + Layout.convertToReais(Bd.clienteBuscaContaCorrente
                        .getCartoesCreditoCliente()
                        .get(0)
                        .getLimite()
                        .getLimiteDisponivel()));


            }
            // SE VALOR NÃO SE ADEQUA AO LIMITE DE TRANSAÇÃO
            else{
                return("Não foi possível realizar a compra.\nO valor excede seu limite disponível ("
                        + Layout.convertToReais(Bd.clienteBuscaContaCorrente
                        .getCartoesCreditoCliente()
                        .get(0).getLimite()
                        .getLimiteDisponivel()) +
                        ").");
            }
        }
        // SE FOR CONTA POUPANÇA
        if(tipoConta == 2){
            //SE VALOR SE ADEQUAR AO LIMITE DA FATURA
            if(valorItem <= Bd.clienteBuscaContaPoupanca
                    .getCartoesCreditoCliente()
                    .get(0).getLimite()
                    .getLimiteDisponivel()){

                // ATUALIZANDO LIMITE DO CARTÃO DE CRÉDITO DO CLIENTE
                Bd.clienteBuscaContaPoupanca.getCartoesCreditoCliente().get(0)
                        .getLimite()
                        .setLimiteDisponivel(Bd.clienteBuscaContaPoupanca
                                .getCartoesCreditoCliente()
                                .get(0)
                                .getLimite()
                                .getLimiteDisponivel() - valorItem);

                // INSTANCIANDO OBJETO TRANSAÇÃO
                Transacao transacao = new Transacao(date, nomeItem, valorItem,
                        Bd.clienteBuscaContaPoupanca
                                .getCliente(),
                        Bd.clienteBuscaContaPoupanca ,
                        Bd.clienteBuscaContaPoupanca
                                .getCartoesCreditoCliente().get(0));

                // ADICIONANDO TRANSAÇÃO AO BD
                Credito.salvarTransacao(transacao);

                return("Compra no valor de " + Layout.convertToReais(valorItem) + " realizada com sucesso. " +
                        "\nSeu limite atual: " + Layout.convertToReais(Bd.clienteBuscaContaPoupanca
                        .getCartoesCreditoCliente().get(0)
                        .getLimite()
                        .getLimiteDisponivel()));

            }

            // SE VALOR NÃO SE ADEQUA AO LIMITE DE TRANSAÇÃO
            else{
                return("Não foi possível realizar a compra.\nO valor excede seu limite disponível ("
                        + Layout.convertToReais(Bd.clienteBuscaContaPoupanca
                        .getCartoesCreditoCliente()
                        .get(0).getLimite()
                        .getLimiteDisponivel()) +
                        ").");
            }
        }
        return("");
    }

    // FATURA
    public static void retornaFatura(Integer tipoConta){

        Float soma = 0.00f;

        // SE A CONTA É CORRENTE
        if(tipoConta == 1) {
            // SE TIVER PELO MENOS UM ITEM NA FATURA
            if (!Credito.fatura.isEmpty()) {

                Main.layout.topLine(3);
                Main.layout.br(1);

                // TÍTULO
                System.out.println("             FATURA DO CARTÃO DE CRÉDITO "
                        + Bd.clienteBuscaContaCorrente
                        .getCartoesCreditoCliente()
                        .get(0)
                        .getNumeroCartao());

                Main.layout.bottomLine(3);
                Main.layout.br(1);

                Main.layout.topLine(3);
                Main.layout.br(1);

                // RODANDO NO FOR OS ITENS DA FATURA DO CLIENTE
                for (Map.Entry<Integer, Transacao> entry : Credito.fatura.entrySet()) {

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(entry.getValue().getDataCompra());

                    System.out.println("    [" + entry.getKey() + "] "
                            + entry.getValue().getDescricao() + " || "
                            + cal.get(Calendar.DAY_OF_MONTH) + "/"
                            + cal.get(Calendar.MONTH)+1 + "/"
                            + cal.get(Calendar.YEAR) + " || "
                            + cal.get(Calendar.HOUR_OF_DAY) + ":"
                            + cal.get(Calendar.MINUTE) + "hrs || "
                            + Layout.convertToReais(entry.getValue().getValor()));

                    soma += entry.getValue().getValor();
                }

                Main.layout.centralLine(3);
                Main.layout.br(1);

                System.out.println("    Total: " + Layout.convertToReais(soma));

                Main.layout.bottomLine(3);
                Main.layout.br(1);

            }

            // SE NÃO TIVER NADA NA FATURA
            else{
                System.out.println(("Não existem transações realizadas neste cartão."));
            }

        }

        // SE A CONTA É POUPANÇA
        else{
            // SE TIVER PELO MENOS UM ITEM NA FATURA
            if (!Credito.fatura.isEmpty()) {

                Main.layout.topLine(3);
                Main.layout.br(1);

                // TÍTULO
                System.out.println("             FATURA DO CARTÃO DE CRÉDITO "
                        + Bd.clienteBuscaContaPoupanca
                        .getCartoesCreditoCliente()
                        .get(0)
                        .getNumeroCartao());

                Main.layout.bottomLine(3);
                Main.layout.br(1);

                Main.layout.topLine(3);
                Main.layout.br(1);

                // RODANDO NO FOR OS ITENS DA FATURA DO CLIENTE
                for (Map.Entry<Integer, Transacao> entry : Credito.fatura.entrySet()) {

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(entry.getValue().getDataCompra());

                    System.out.println("    [" + entry.getKey() + "] "
                            + entry.getValue().getDescricao() + " || "
                            + cal.get(Calendar.DAY_OF_MONTH) + "/"
                            + cal.get(Calendar.MONTH)+1 + "/"
                            + cal.get(Calendar.YEAR) + " || "
                            + cal.get(Calendar.HOUR_OF_DAY) + ":"
                            + cal.get(Calendar.MINUTE) + "hrs || "
                            + Layout.convertToReais(entry.getValue().getValor()));

                    soma += entry.getValue().getValor();
                }

                Main.layout.centralLine(3);
                Main.layout.br(1);

                System.out.println("    Total: " + Layout.convertToReais(soma));

                Main.layout.bottomLine(3);
                Main.layout.br(1);

            }

            // SE NÃO TIVER NADA NA FATURA
            else{
                System.out.println(("Não existem transações realizadas neste cartão."));
            }

        }





    }

    // PAGAR FATURA
    public static String pagarFatura(Integer tipoConta){

        int n = 0 ;

        // SE A CONTA É UMA CONTA CORRENTE
        if(tipoConta == 1){

            // SE EXISTE ALGO PARA SER PAGO
            if(!Credito.getFatura().isEmpty()){

                // EXIBE A FATURA
                retornaFatura(tipoConta);

                // LAYOUT
                Main.layout.topLine(3);
                Main.layout.br(1);

                System.out.println("        [1] Pagar    [2] Voltar");

                Main.layout.bottomLine(3);
                Main.layout.br(1);

                // REPETIDOR PARA EVITAR ERROS DE ENTRADA DO USUÁRIO
                do{

                    // LAYOUT
                    Main.layout.topLine(3);
                    Main.layout.br(1);

                    // OPÇÕES
                    n = Integer.parseInt(Layout.entry("    Escolha: "));

                    // LAYOUT
                    Main.layout.bottomLine(3);
                    Main.layout.br(1);

                    // CARREGA E LIMPA A TELA
                    Main.layout.loading(3);
                    Main.layout.limparTela();

                    // SE O USUÁRIO ESCOLHER PAGAR
                    if(n == 1){

                        // SE SALDO DISPONÍVEL FOR O SUFICIENTE
                        if(Bd.clienteBuscaContaCorrente
                                .getSaldo()
                                >= Bd.clienteBuscaContaCorrente
                                .getCartoesCreditoCliente()
                                .get(0)
                                .getLimite()
                                .getLimiteUtilizado()){

                            // ATUALIZA SALDO DO CLIENTE
                            Bd.clienteBuscaContaCorrente
                                    .setSaldo(Bd.clienteBuscaContaCorrente
                                            .getSaldo()
                                            - Bd.clienteBuscaContaCorrente
                                            .getCartoesCreditoCliente()
                                            .get(0)
                                            .getLimite()
                                            .getLimiteUtilizado());

                            // ZERA VALOR UTILIZADO PELO CLIENTE
                            Bd.clienteBuscaContaCorrente
                                    .getCartoesCreditoCliente()
                                    .get(0).getLimite()
                                    .setLimiteUtilizado(0.00f);

                            // ZERA A FATURA DO CARTÃO
                            Credito
                                    .getFatura()
                                    .clear();

                            // ATUALIZANDO LIMITE
                            Bd.clienteBuscaContaCorrente
                                    .getCartoesCreditoCliente()
                                    .get(0)
                                    .getLimite()
                                    .atualizarTotal();

                            return(("Você pagou a sua fatura com sucesso.\nSeu saldo atual é: " +
                                    Layout.convertToReais(Bd.clienteBuscaContaCorrente.getSaldo())));
                        }

                        // SE O CLIENTE NÃO TIVER SALDO SUFICIENTE
                        else{

                            return("Você não tem saldo suficiente.\nSeu saldo atual é: " +
                                    Layout.convertToReais(Bd.clienteBuscaContaCorrente.getSaldo()));

                        }

                    }

                    // SE O USUÁRIO ESCOLHER VOLTAR PARA O MENU
                    else if(n == 2){
                        Main.menuCartaoSelecionado(tipoConta, 2);
                    }

                }while(n < 1 || n > 2);

            }
            // SE NÃO EXISTE NADA PARA SER PAGO
            else{
                return("Não existe nada para ser pago");
            }

        }

        // SE A CONTA É UMA CONTA POUPANÇA
        else{

            // SE EXISTE ALGO PARA SER PAGO
            if(!Credito.getFatura().isEmpty()){

                // EXIBE A FATURA
                retornaFatura(tipoConta);

                // LAYOUT
                Main.layout.topLine(3);
                Main.layout.br(1);

                System.out.println("        [1] Pagar    [2] Voltar");

                Main.layout.bottomLine(3);
                Main.layout.br(1);

                // REPETIDOR PARA EVITAR ERROS DE ENTRADA DO USUÁRIO
                do{

                    // LAYOUT
                    Main.layout.topLine(3);
                    Main.layout.br(1);

                    // OPÇÕES
                    n = Integer.parseInt(Layout.entry("    Escolha: "));

                    // LAYOUT
                    Main.layout.bottomLine(3);
                    Main.layout.br(1);

                    // CARREGA E LIMPA A TELA
                    Main.layout.loading(3);
                    Main.layout.limparTela();

                    // SE O USUÁRIO ESCOLHER PAGAR
                    if(n == 1){

                        // SE SALDO DISPONÍVEL FOR O SUFICIENTE
                        if(Bd.clienteBuscaContaPoupanca
                                .getSaldo()
                                >= Bd.clienteBuscaContaPoupanca
                                .getCartoesCreditoCliente()
                                .get(0)
                                .getLimite()
                                .getLimiteUtilizado()){

                            // ATUALIZA SALDO DO CLIENTE
                            Bd.clienteBuscaContaPoupanca
                                    .setSaldo(Bd.clienteBuscaContaPoupanca
                                            .getSaldo()
                                            - Bd.clienteBuscaContaPoupanca
                                            .getCartoesCreditoCliente()
                                            .get(0)
                                            .getLimite()
                                            .getLimiteUtilizado());

                            // ZERA VALOR UTILIZADO PELO CLIENTE
                            Bd.clienteBuscaContaPoupanca
                                    .getCartoesCreditoCliente()
                                    .get(0).getLimite()
                                    .setLimiteUtilizado(0.00f);

                            // ZERA A FATURA DO CARTÃO
                            Credito
                                    .getFatura()
                                    .clear();

                            // ATUALIZANDO LIMITE
                            Bd.clienteBuscaContaPoupanca
                                    .getCartoesCreditoCliente()
                                    .get(0)
                                    .getLimite()
                                    .atualizarTotal();

                            return(("Você pagou a sua fatura com sucesso.\nSeu saldo atual é: " +
                                    Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getSaldo())));
                        }

                        // SE O CLIENTE NÃO TIVER SALDO SUFICIENTE
                        else {

                            return ("Você não tem saldo suficiente.\nSeu saldo atual é: " +
                                    Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getSaldo()));
                        }

                    }

                    // SE O USUÁRIO ESCOLHER VOLTAR PARA O MENU
                    else if(n == 2){
                        Main.menuCartaoSelecionado(tipoConta, 2);
                    }

                }while(n < 1 || n > 2);

            }
            // SE NÃO EXISTE NADA PARA SER PAGO
            else{
                return("Não existe nada para ser pago");

            }

        }

        return("");
    }

}
