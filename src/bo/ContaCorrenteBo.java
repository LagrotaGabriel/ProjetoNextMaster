package bo;

// IMPORTAÇÕES
import dao.Bd;
import util.Layout;

public class ContaCorrenteBo {

    // MÉTODO DE SAQUE
    public static Boolean Saque(String valor){

        // TENTA VALIDAR O VALOR DO SAQUE
        try {
            // TENTA CONVERTER O VALOR INSERIDO PARA FLOAT
            Float valorConvertido = Float.parseFloat(valor);

            // SE O VALOR SACADO FOR NEGATIVO
            if(valorConvertido < 0){
                System.out.println("* Erro: O valor do saque não pode\n  ser negativo");
                return false;
            }
            // SE O VALOR DO SAQUE NÃO FOR NEGATIVO
            else{
                // SE O VALOR NÃO FOR 0
                if(valorConvertido != 0.0f) {

                    // SE O VALOR DO SAQUE FOR MAIOR DO QUE O SALDO DO CLIENTE
                    if(valorConvertido > Bd.clienteBuscaContaCorrente.getSaldo()){
                        System.out.println("* Erro: Saque não autorizado.\n"
                                + "  Seu saldo é: R$" + Layout.convertToReais(Bd.clienteBuscaContaCorrente.getSaldo()));
                        return false;
                    }
                    // SE O VALOR DO SAQUE FOR MENOR OU IGUAL AO SALDO DO CLIENTE
                    else{
                        Bd.clienteBuscaContaCorrente.setSaldo(Bd.clienteBuscaContaCorrente.getSaldo() - valorConvertido);
                        System.out.println("  Saque efetuado.\n"
                                + "  Seu saldo atual é:"
                                + Layout.convertToReais(Bd.clienteBuscaContaCorrente.getSaldo()));
                        return true;
                    }
                }
                // SE O VALOR FOR 0
                else{
                    System.out.println("  Você escolheu sair do menu saque");
                    return true;
                }
            }
        }
        // SE O VALOR DO SAQUE TIVER ALGUM CARACTER NÃO NUMÉRICO
        catch(Exception e){
            System.out.println("* Erro: O valor inserido precisa ser\n  exclusivamente numérico");
            return false;
        }
    }

    // MÉTODO DE DEPÓSITO
    public static Boolean Deposito(String valor){

        // TENTA VALIDAR O VALOR DO DEPÓSITO
        try {
            // TENTA CONVERTER O VALOR INSERIDO PARA FLOAT
            Float valorConvertido = Float.parseFloat(valor);

            // SE O VALOR DEPOSITADO FOR NEGATIVO
            if(valorConvertido < 0){
                System.out.println("* Erro: O valor do depósito não pode\n  ser negativo");
                return false;
            }
            // SE O VALOR DO DEPÓSITO NÃO FOR NEGATIVO
            else{
                // SE O VALOR NÃO FOR 0
                if(valorConvertido != 0.0f) {
                    Bd.clienteBuscaContaCorrente.setSaldo(Bd.clienteBuscaContaCorrente.getSaldo() + valorConvertido);
                    System.out.println("  "
                            + Layout.convertToReais(valorConvertido)
                            + " adicionado à sua conta.\n  Saldo: "
                            + Layout.convertToReais(Bd.clienteBuscaContaCorrente.getSaldo()));
                    return true;
                }
                // SE O VALOR FOR 0
                else{
                    System.out.println("  Você escolheu sair do menu depósito");
                    return true;
                }
            }
        }
        // SE O VALOR DO DEPÓSITO TIVER ALGUM CARACTER NÃO NUMÉRICO
        catch(Exception e){
            System.out.println("* Erro: O valor inserido precisa ser\n  exclusivamente numérico");
            return false;
        }
    }

}
