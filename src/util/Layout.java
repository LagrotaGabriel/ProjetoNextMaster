package util;

// IMPORTAÇÕES
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Layout {

    // ATRIBUTOS
    Integer timer1, timer2, size;
    public static Scanner input = new Scanner(System.in);

    // CONSTRUTOR
    public Layout(Integer timer1,Integer  timer2){
        this.timer1 = timer1;
        this.timer2 = timer2;
    }

    // MÉTODO SLEEP
    public void sleep(Integer n){
        try{
            Thread.sleep(n);
        }
        catch(InterruptedException ignored){}
    }

    // LINHA SUPERIOR
    public void topLine(Integer sz){
        // SE O PARÂMETRO SIZE FOR 1
        if(sz == 1){
            // O TAMANHO DA LINHA É DE 20 CARACTERES
            size = 20;
        }
        // SE O PARÂMETRO SIZE FOR 2
        else if(sz == 2){
            // O TAMANHO DA LINHA É DE 40 CARACTERES
            size = 40;
        }
        // SE O PARÂMETRO SIZE FOR 3
        else{
            // O TAMANHO DA LINHA É DE 60 CARACTERES
            size = 60;
        }
        // COMEÇA A IMPRIMIR LINHA
        System.out.print("╔");
        sleep(timer1);
        // PRINTA A LINHA ATÉ DAR O TAMANHO DO SIZE
        for(int i = 0; i < size; i++){
            System.out.print("═");
            sleep(timer1);
        }
        // TERMINA DE IMPRIMIR A LINHA
        System.out.print("╗");
        sleep(150);
    }

    // LINHA CENTRAL
    public void centralLine(Integer sz){
        // SE O PARÂMETRO SIZE FOR 1
        if(sz == 1){
            // O TAMANHO DA LINHA É DE 20 CARACTERES
            size = 22;
        }
        // SE O PARÂMETRO SIZE FOR 2
        else if(sz == 2){
            // O TAMANHO DA LINHA É DE 40 CARACTERES
            size = 42;
        }
        // SE O PARÂMETRO SIZE FOR 3
        else{
            // O TAMANHO DA LINHA É DE 60 CARACTERES
            size = 62;
        }
        // PRINTA A LINHA ATÉ DAR O TAMANHO DO SIZE
        for(int i = 0; i < size; i++){
            System.out.print("═");
            sleep(timer1);
        }
    }

    // LINHA INFERIOR
    public void bottomLine(Integer sz){
        // SE O PARÂMETRO SIZE FOR 1
        if(sz == 1){
            // O TAMANHO DA LINHA É DE 20 CARACTERES
            size = 20;
        }
        // SE O PARÂMETRO SIZE FOR 2
        else if(sz == 2){
            // O TAMANHO DA LINHA É DE 40 CARACTERES
            size = 40;
        }
        // SE O PARÂMETRO SIZE FOR 3
        else{
            // O TAMANHO DA LINHA É DE 60 CARACTERES
            size = 60;
        }
        // COMEÇA A IMPRIMIR LINHA
        System.out.print("╚");
        sleep(timer1);
        // PRINTA A LINHA ATÉ DAR O TAMANHO DO SIZE
        for(int i = 0; i < size; i++){
            System.out.print("═");
            sleep(timer1);
        }
        // TERMINA DE IMPRIMIR A LINHA
        System.out.print("╝");
        sleep(150);
    }

    // QUEBRA DE LINHAS
    public void br(Integer n){
        // QUEBRA UMA LINHA ATÉ DAR O VALOR PASSADO NO PARÂMETRO
        for(int i = 0; i < n; i++) {
            System.out.println();
        }
    }

    //FORMATA PARA REAIS
    public static String convertToReais(double valor) {
        // INICIA A CLASSE LOCALE PASSANDO OS PARÂMETROS PT, BR
        Locale ptBr = new Locale("pt", "BR");
        // RETORNA O VALOR PASSADO NO PARÂMETRO FORMATADO EM REAIS (R$)
        return NumberFormat.getCurrencyInstance(ptBr).format(valor);
    }

    // ENTRADA DO USUÁRIO
    public static String entry(String texto) {
        // EXIBE O TEXTO PASSADO NO PARÂMETRO
        System.out.print(texto);
        // RETORNA O INPUT PARA QUE SEJA ENSERIDO UM VALOR DE TEXTO NA NEXT LINE
        return input.nextLine();
    }

    // BARRA DE LOADING
    public void loading(Integer sz){
        // SE O PARÂMETRO sz FOR 1 O SIZE É 22
        if(sz == 1){
            size = 22;
        }
        // SE O PARÂMETRO sz FOR 2 O SIZE É 42
        else if(sz == 2){
            size = 42;
        }
        // SE O PARÂMETRO sz FOR 3 O SIZE É 62
        else{
            size = 62;
        }

        sleep(timer2);
        // IMPRIME UMA BARRINHA ( | ) ATÉ DAR O TAMANHO DO SIZE
        for(int i = 0; i < size; i++){
            System.out.print("|");
            sleep(timer2);
        }
        // APAGA A BARRINHA ( | ) ATÉ DAR O TAMANHO DO SIZE
        for(int i = 0; i < size; i++){
            System.out.print("\b");
            sleep(timer2);
        }

        // PULA UMA LINHA
        System.out.println();
        sleep(150);
    }

    // LIMPA O CONSOLE
    public void limparTela(){
        // PULA 80 LINHAS DO CONSOLE, "LIMPANDO" ELE.
        for(int i = 0; i < 80; i++){
            System.out.println("-");
        }
    }

}
