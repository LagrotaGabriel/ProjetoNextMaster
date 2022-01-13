package bo;

import dao.Bd;

public class ContaCorrenteBo {

    // Saque
    public static String Saque(Float valor){
        if(valor > Bd.clienteBuscaContaCorrente.getSaldo()){
            return("  Saque não autorizado. Seu saldo é: R$" + Bd.clienteBuscaContaCorrente.getSaldo());
        }else{
            Bd.clienteBuscaContaCorrente.setSaldo(Bd.clienteBuscaContaCorrente.getSaldo() - valor);
            return("  Saque efetuado. Seu saldo atual é: R$ " + Bd.clienteBuscaContaCorrente.getSaldo());
        }
    }

    // Deposito()
    public static void Deposito(Float valor){
        Bd.clienteBuscaContaCorrente.setSaldo(Bd.clienteBuscaContaCorrente.getSaldo() + valor);
    }

}
