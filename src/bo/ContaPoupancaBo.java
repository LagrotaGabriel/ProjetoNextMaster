package bo;

import dao.Bd;

public class ContaPoupancaBo{

    // Saque
    public static String Saque(Float valor){
        if(valor > Bd.clienteBuscaContaPoupanca.getSaldo()){
            return("  Saque não autorizado. Seu saldo é: R$" + Bd.clienteBuscaContaPoupanca.getSaldo());
        }else{
            Bd.clienteBuscaContaPoupanca.setSaldo(Bd.clienteBuscaContaPoupanca.getSaldo() - valor);
            return("  Saque efetuado. Seu saldo atual é: R$ " + Bd.clienteBuscaContaPoupanca.getSaldo());
        }
    }

    // Deposito
    public static void Deposito(Float valor){
        Bd.clienteBuscaContaPoupanca.setSaldo(Bd.clienteBuscaContaPoupanca.getSaldo() + valor);
    }

}
