package bo;
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
    public Boolean cadastraRgBo(String rg){

        long rgConvertido = 0L;

        // Try de validação se o RG inserido é numérico
        try {

            // Tenta converter RG (INTEIRO) para RG STRING
            rgConvertido = Long.parseLong(rg);

            // SE A ENTRADA DO RG NÃO TIVER 9 DÍGITOS
            if((rg).length() != 9){
                System.out.println("*** Erro: O RG inserido deve possuir 9 dígitos");
                Main.layout.centralLine(3);
                Main.layout.br(1);
                return(false);
            }

            // SE A ENTRADA DO RG TIVER 9 DÍGITOS
            else{
                return(true);
            }

        }
        // Se a entrada do usuário para o rg no login for inválida
        catch (Exception e) {
            System.out.println("*** Erro: Digite apenas valores numéricos para a entrada do rg");
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);
        }

    }

    // Faz a validação do EMAIL do CADASTRO do cliente
    public Boolean cadastraEmailBo(String email){

        // SE O E-MAIL NÃO TIVER @ OU NÃO TIVER .COM
        if(!email.contains("@") || !email.contains(".com")){
            System.out.println("*** Erro: O e-mail inserido não apresenta formato válido.");
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);
        }
        // SE O E-MAIL TIVER @ E .COM
        else{
            // SE O .COM VIER ANTES DO @
            if(email.indexOf("@") > email.indexOf(".com")){
                System.out.println("*** Erro: O e-mail inserido não apresenta formato válido.");
                Main.layout.centralLine(3);
                Main.layout.br(1);
                return(false);
            }
            // SE O @ VIER ANTES DO .COM
            else{

                // SE NÃO TIVER NADA ANTES DO @
                if(email.indexOf("@") == 0){
                    System.out.println("*** Erro: O e-mail inserido não apresenta formato válido.");
                    Main.layout.centralLine(3);
                    Main.layout.br(1);
                    return(false);
                }
                // SE TIVER ALGO ANTES DO @
                else{
                    return(true);
                }
            }
        }
    }

    // Faz a validação do TELEFONE do CADASTRO do cliente
    public Boolean cadastraTelefoneBo(String telefone){

        // Atribuição de variáveis
        long telefoneConvertido = 0L;

        // Tenta converter a string telefone pra long através da variável telefoneConvertido
        try {
            telefoneConvertido = Long.parseLong(telefone);
            // CELULAR
            if(telefone.indexOf("9") == 0){
                // SE TIVER 9 DÍGITOS
                if(telefone.length() == 9){
                    return(true);
                }
                // SE NÃO TIVER 9 DÍGITOS
                else{
                    System.out.println("*** Erro: O telefone celular precisa possuir 9 dígitos (SEM DDD)");
                    Main.layout.centralLine(3);
                    Main.layout.br(1);
                    return(false);
                }
            }
            // FIXO
            else{
                // SE TIVER 8 DÍGITOS
                if(telefone.length() == 8){
                    return(true);
                }
                // SE NÃO TIVER 8 DÍGITOS
                else{
                    System.out.println("*** Erro: O telefone fixo precisa possuir 8 dígitos (SEM DDD)");
                    Main.layout.centralLine(3);
                    Main.layout.br(1);
                    return(false);
                }
            }
        }
        // Se existirem entradas não numéricas
        catch (Exception e){
            System.out.println("*** Erro: Digite apenas valores numéricos para a entrada do TELEFONE");
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);
        }

    }

    // Faz a validação da SENHA do CADASTRO do cliente
    public Boolean cadastraSenhaBo(String senha){

        int senhaConvertida = 0;

        // Valida se a senha é numérica ou não
        try{

            // Testa se o valor é numérico através da tentativa de conversão de entrada do usuário para inteiro
            senhaConvertida = Integer.parseInt(senha);

            // Se o tamanho da senha for igual a 6
            if(senha.length() == 6){
                return(true);
            }
            // Se o tamanho da senha não for igual a 6
            else{
                System.out.println("*** Erro: A senha precisa possuir 6 números");
                Main.layout.centralLine(3);
                Main.layout.br(1);
                return(false);

            }
        }
        // Senha não é unicamente numérica
        catch(Exception e){

            System.out.println("*** Erro: Digite apenas valores numéricos para a entrada da SENHA");
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);

        }
    }

    // Faz a validação do ESTADO do CADASTRO do cliente
    public Boolean cadastraEstadoBo(String estado){

        // SE O ESTADO SÓ POSSUIR LETRAS
        if(estado.matches("[A-Z]*")){
            // SE O ESTADO TIVER 2 DÍGITOS
            if(estado.length() == 2){
                return(true);
            }
            // SE O ESTADO NÃO TIVER 2 DÍGITOS
            else{
                System.out.println("*** Erro: A unidade federativa deve possuir dois dígitos");
                Main.layout.centralLine(3);
                Main.layout.br(1);
                return(false);
            }
        }
        // SE O ESTADO POSSUIR ALGO ALÉM DE LETRAS
        else{
            System.out.println("*** Erro: Digite apenas letras na unidade federativa");
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);
        }
    }

    // Faz a validação da CIDADE do CADASTRO do cliente
    public Boolean cadastraCidadeBo(String cidade){

        // SE CIDADE TIVER APENAS LETRAS
        if(cidade.matches("[A-Z]*")){
            return(true);
        }
        // SE CIDADE TIVER NUMEROS
        else{
            System.out.println("*** Erro: Digite apenas letras em cidade");
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);
        }
    }

    // Faz a validação do BAIRRO do CADASTRO do cliente
    public Boolean cadastraBairroBo(String bairro){

        // SE BAIRRO TIVER APENAS LETRAS
        if(bairro.matches("[A-Z]*")){
            return(true);
        }
        // SE BAIRRO TIVER NUMEROS
        else{
            System.out.println("*** Erro: Digite apenas letras em bairro");
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);
        }
    }

    // Faz a validação da RUA do CADASTRO do cliente
    public Boolean cadastraRuaBo(String rua){

        // Se rua for somente letras
        if(rua.matches("[A-Z]*")){
            return(true);
        }
        // Se rua tiver algo além de letras
        else{
            System.out.println("*** Erro: Digite apenas letras em rua");
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);
        }
    }

    // Faz a validação do NUMERO DA RUA do CADASTRO do cliente
    public Boolean cadastraNumeroRuaBo(String numeroRua){

        // Declaração de variável
        int numeroRuaConvertido = 0;

        // Caso numero da rua seja composto apenas por números
        try{
            // Tenta fazer conversão de String para Integer
            numeroRuaConvertido = Integer.parseInt(numeroRua);

            // Se numero da rua tiver mais de 4 dígitos
            if(numeroRua.length() > 4){
                System.out.println("*** Erro: Numero da rua deve possuir até 4 dígitos");
                Main.layout.centralLine(3);
                Main.layout.br(1);
                return(false);
            }
            // Se numero da rua tiver menos de 4 dígitos
            else{
                return(true);
            }
        }
        // Caso numero da rua possua algo além de números
        catch(Exception e){
            System.out.println("*** Erro: Digite apenas números em numero da rua");
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);
        }
    }

    // Faz a validação do CEP do CADASTRO do cliente
    public Boolean cadastraCepBo(String cep){

        int cepConvertido = 0;

        // Se cep tiver apenas números ele completa o try
        try{
            // Faz a validação se cep é numérico através da tentativa de conversão para int
            cepConvertido = Integer.parseInt(cep);

            // Se cep tiver 8 dígitos
            if(cep.length() == 8){
                return(true);
            }
            // Se cep não tiver 8 dígitos
            else{
                System.out.println("*** Erro: CEP deve possuir 8 dígitos");
                Main.layout.centralLine(3);
                Main.layout.br(1);
                return(false);
            }
        }
        // Caso cep tenha algo além de números
        catch(Exception e){
            System.out.println("*** Erro: Digite apenas números em CEP");
            Main.layout.centralLine(3);
            Main.layout.br(1);
            return(false);
        }
    }

}
