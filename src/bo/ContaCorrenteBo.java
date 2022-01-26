package bo;

// IMPORTAÇÕES
import dao.Bd;

public class ContaCorrenteBo {

    // MÉTODO DE SAQUE
    public static String Saque(Float valor){
        // SE O VALOR DO SAQUE FOR MAIOR DO QUE O SALDO DO CLIENTE
        if(valor > Bd.clienteBuscaContaCorrente.getSaldo()){
            return("  Saque não autorizado. Seu saldo é: R$" + Bd.clienteBuscaContaCorrente.getSaldo());
        }
        // SE O VALOR DO SAQUE FOR MENOR OU IGUAL AO SALDO DO CLIENTE
        else{
            Bd.clienteBuscaContaCorrente.setSaldo(Bd.clienteBuscaContaCorrente.getSaldo() - valor);
            return("  Saque efetuado. Seu saldo atual é: R$ " + Bd.clienteBuscaContaCorrente.getSaldo());
        }
    }

    // MÉTODO DE DEPÓSITO
    public static void Deposito(Float valor){
        Bd.clienteBuscaContaCorrente.setSaldo(Bd.clienteBuscaContaCorrente.getSaldo() + valor);
    }

}
