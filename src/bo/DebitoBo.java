package bo;
import dao.Bd;
import model.cartao.TipoCartao;
import model.cartao.Transacao;
import model.cartao.debito.Debito;
import model.conta.ContaTipo;
import util.Layout;
import view.Main;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class DebitoBo {

    public static Debito debito;

    // FAZ A VALIDAÇÃO DE INSERÇÃO DE CARTÃO DE DÉBITO
    public static String validaInsercaoDebito(Integer tipoConta){

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
            // SE O CLIENTE AINDA NÃO TIVER CARTÕES DE DÉBITO CADASTRADOS
            if(Bd.clienteBuscaContaCorrente.cartoesDebitoCliente.isEmpty()){
                // MÉTODO DE INSERÇÃO DO CARTÃO NOS DBS
                Debito debito = new Debito(Bd.clienteBuscaContaCorrente.getCliente(), limite, TipoCartao.DEBITO);
                Bd.insereCartaoDebito(debito, tipoConta);
                return("Cartão de débito cadastrado com sucesso!");
            }
            // SE O CLIENTE JÁ POSSUI CARTÃO DE DÉBITO CADASTRADO
            else {
                return ("Você já possui um cartão de débito cadastrado");
            }
        }
        // SE A CONTA FOR UMA CONTA POUPANÇA
        else{
            // SE O CLIENTE AINDA NÃO TIVER CARTÕES DE DÉBITO CADASTRADOS
            if(Bd.clienteBuscaContaPoupanca.cartoesDebitoCliente.isEmpty()){
                // MÉTODO DE INSERÇÃO DO CARTÃO NOS DBS
                Debito debito = new Debito(Bd.clienteBuscaContaPoupanca.getCliente(), limite, TipoCartao.DEBITO);
                Bd.insereCartaoDebito(debito, tipoConta);
                return("Cartão de débito cadastrado com sucesso!");
            }
            // SE O CLIENTE JÁ POSSUI CARTÃO DE DÉBITO CADASTRADO
            else {
                return ("Você já possui um cartão de débito cadastrado");
            }
        }

    }

    // ATIVAR/DESATIVAR CARTÃO
    public static String ativaOuDesativaCartao(Integer tipoConta, Boolean status){

        // SE A CONTA FOR CORRENTE
        if(tipoConta == 1){

            // SE FOR PRA ATIVAR
            if(status){
                Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0).setAtivo(true);
                return("Cartão de débito ativado com sucesso");

            }
            // SE FOR PRA DESATIVAR
            else{
                Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0).setAtivo(false);
                return("Cartão de débito desativado com sucesso");
            }

        }
        // SE A CONTA FOR POUPANÇA
        else{

            // SE FOR PRA ATIVAR
            if(status){
                Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0).setAtivo(true);
                return("Cartão de débito ativado com sucesso");
            }
            // SE FOR PRA DESATIVAR
            else{
                Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0).setAtivo(false);
                return("Cartão de débito desativado com sucesso");
            }

        }

    }

    // COMPRAR
    public static String processaCompra(Integer tipoConta, String nomeItem, Float valorItem){

        // INSTANCIANDO NOVA DATA
        Date date = new Date();

        // SE FOR CONTA CORRENTE
        if(tipoConta == 1){

            //SE VALOR SE ADEQUAR AO LIMITE DE TRANSAÇÃO
            if(valorItem <= Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0).getLimiteTransacao()){
                // SE SALDO DISPONÍVEL FOR SUFICIENTE
                if(Bd.clienteBuscaContaCorrente.getSaldo() >= valorItem){

                    // ATUALIZANDO SALDO DO CLIENTE
                    Bd.clienteBuscaContaCorrente.setSaldo(Bd.clienteBuscaContaCorrente.getSaldo()-valorItem);

                    // INSTANCIANDO OBJETO TRANSAÇÃO
                    Transacao transacao = new Transacao(date, nomeItem, valorItem,
                            Bd.clienteBuscaContaCorrente.getCliente(),
                            Bd.clienteBuscaContaCorrente ,
                            Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0));

                    // ADICIONANDO TRANSAÇÃO AO BD
                    Debito.salvarTransacao(transacao);

                    return("Compra no valor de " + Layout.convertToReais(valorItem) + " realizada com sucesso. " +
                            "\nSeu saldo atual: " + Layout.convertToReais(Bd.clienteBuscaContaCorrente.getSaldo()));
                }
                // SE SALDO DISPONÍVEL NÃO FOR SUFICIENTE
                else{
                    return("Não foi possível realizar a compra, você não possui saldo suficiente.\nSeu saldo atual: " +
                            Layout.convertToReais(Bd.clienteBuscaContaCorrente.getSaldo()));
                }
            }
            // SE VALOR NÃO SE ADEQUA AO LIMITE DE TRANSAÇÃO
            else{
                return("Não foi possível realizar a compra.\nO valor excede seu limite para transações ("
                        + Layout.convertToReais(Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0).getLimiteTransacao()) +
                        ").");
            }
        }
        // SE FOR CONTA POUPANÇA
        if(tipoConta == 2){
            // SE VALOR SE ADEQUAR AO LIMITE DE TRANSAÇÃO
            if(valorItem <= Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0).getLimiteTransacao()){
                // SE SALDO DISPONÍVEL FOR SUFICIENTE
                if(Bd.clienteBuscaContaPoupanca.getSaldo() >= valorItem){

                    // ATUALIZANDO SALDO DO CLIENTE
                    Bd.clienteBuscaContaPoupanca.setSaldo(Bd.clienteBuscaContaPoupanca.getSaldo()-valorItem);

                    // INSTANCIANDO OBJETO TRANSAÇÃO
                    Transacao transacao = new Transacao(date, nomeItem, valorItem,
                            Bd.clienteBuscaContaPoupanca.getCliente(),
                            Bd.clienteBuscaContaPoupanca ,
                            Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0));

                    // ADICIONANDO TRANSAÇÃO AO BD
                    Debito.salvarTransacao(transacao);

                    return("Compra no valor de " + Layout.convertToReais(valorItem) + " realizada com sucesso. " +
                            "\nSeu saldo atual: " + Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getSaldo()));
                }
                // SE SALDO DISPONÍVEL NÃO FOR SUFICIENTE
                else{
                    return("Não foi possível realizar a compra, você não possui saldo suficiente.\nSeu saldo atual: " +
                            Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getSaldo()));
                }
            }
            // SE VALOR NÃO SE ADEQUA AO LIMITE DE TRANSAÇÃO
            else{
                return("Não foi possível realizar a compra.\nO valor excede seu limite para transações ("
                        + Layout.convertToReais(Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0).getLimiteTransacao()) +
                        ").");
            }
        }
        return("");
    }

    // EXTRATO
    public static void retornaExtrato(Integer tipoConta){

        Float soma = 0.00f;

        // SE A CONTA É CORRENTE
        if(tipoConta == 1) {
            // SE TIVER PELO MENOS UM ITEM NO EXTRATO
            if (!Debito.extrato.isEmpty()) {

                Main.layout.topLine(3);
                Main.layout.br(1);
                System.out.println("             EXTRATO DO CARTÃO DE DÉBITO "
                        + Bd.clienteBuscaContaCorrente.getCartoesDebitoCliente().get(0).getNumeroCartao());
                Main.layout.bottomLine(3);
                Main.layout.br(1);

                Main.layout.topLine(3);
                Main.layout.br(1);

                for (Map.Entry<Integer, Transacao> entry : Debito.extrato.entrySet()) {

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

            // SE NÃO TIVER NADA NO EXTRATO
            else{
                System.out.println(("Não existem transações realizadas neste cartão."));
            }

        }

        // SE A CONTA É POUPANÇA
        else{
            // SE TIVER PELO MENOS UM ITEM NO EXTRATO
            if (!Debito.extrato.isEmpty()) {

                Main.layout.topLine(3);
                Main.layout.br(1);
                System.out.println("EXTRATO DO CARTÃO DE DÉBITO "
                        + Bd.clienteBuscaContaPoupanca.getCartoesDebitoCliente().get(0).getNumeroCartao());
                Main.layout.bottomLine(3);
                Main.layout.br(1);

                Main.layout.topLine(3);
                Main.layout.br(1);

                for (Map.Entry<Integer, Transacao> entry : Debito.extrato.entrySet()) {

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

                System.out.println("Total: " + Layout.convertToReais(soma));

                Main.layout.bottomLine(3);
                Main.layout.br(1);

            }

            // SE NÃO TIVER NADA NO EXTRATO
            else{
                System.out.println(("Não existem transações realizadas neste cartão."));
            }

        }





    }
}
