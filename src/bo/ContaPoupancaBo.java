package bo;

// IMPORTAÇÕES
import dao.Bd;

public class ContaPoupancaBo{

    // MÉTODO DE SAQUE
    public static String Saque(Float valor){

        // SE O VALOR DO SAQUE FOR MAIOR DO QUE O SALDO DO CLIENTE
        if(valor > Bd.clienteBuscaContaPoupanca.getSaldo()){
            return("  Saque não autorizado. Seu saldo é: R$" + Bd.clienteBuscaContaPoupanca.getSaldo());
        }
        // SE O VALOR DO SAQUE FOR MENOR OU IGUAL AO SALDO DO CLIENTE
        else{
            Bd.clienteBuscaContaPoupanca.setSaldo(Bd.clienteBuscaContaPoupanca.getSaldo() - valor);
            return("  Saque efetuado. Seu saldo atual é: R$ " + Bd.clienteBuscaContaPoupanca.getSaldo());
        }
    }

    // MÉTODO DE DEPÓSITO
    public static void Deposito(Float valor){
        Bd.clienteBuscaContaPoupanca.setSaldo(Bd.clienteBuscaContaPoupanca.getSaldo() + valor);
    }

}
