package bo;
import util.Layout;
import view.Main;

public class EntradasBo {

    // Faz a validação do nome do CADASTRO do cliente
    public Boolean cadastraNomeBo(String nome){

        // SE O NOME POSSUIR ALGUM CARACTERE QUE NÃO SEJA LETRA
        if (!nome.matches("[A-Z]*")) {

            // SE O NOME POSSUIR ESPAÇOS EM BRANCO
            if(nome.contains(" ")){
                System.out.println("*** Erro: O nome não deve conter espaços em branco");
            }
            // SE O NOME CONTER NÚMEROS OU CARACTÉRES ESPECIAIS
            else {
                System.out.println("*** Erro: Digite apenas letras.");
            }
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);

        }

        // SE O NOME POSSUIR APENAS LETRAS
        else {
            return(true);
        }

    }

    // Faz a validação do CPF do CADASTRO do cliente
    public Boolean cadastraCpfBo(String cpf){

        long cpfConvertido = 0L;

        // Try de validação se o CPF inserido é numérico
        try {

            // Tenta converter CPF (INTEIRO) para CPF STRING
            cpfConvertido = Long.parseLong(cpf);

            // SE A ENTRADA DO CPF NÃO TIVER 11 DÍGITOS
            if((cpf).length() != 11){
                System.out.println("*** Erro: O CPF inserido deve possuir 11 dígitos");
                Main.layout.centralLine(3);
                Main.layout.br(1);
                return(false);
            }

            // SE A ENTRADA DO CPF TIVER 11 DÍGITOS
            else{
                return(true);
            }

        }
        // Se a entrada do usuário para o CPF no login for inválida
        catch (Exception e) {
            System.out.println("*** Erro: Digite apenas valores numéricos para a entrada do CPF");
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);
        }
    }

    // Faz a validação do RG do CADASTRO do cliente

}
