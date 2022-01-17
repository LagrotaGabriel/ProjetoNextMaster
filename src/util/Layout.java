package util;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Layout {

    // Atributos
    Integer timer1, timer2, size;
    public static Scanner input = new Scanner(System.in);

    // Construtor
    public Layout(Integer timer1,Integer  timer2){
        this.timer1 = timer1;
        this.timer2 = timer2;
    }

    // Método Sleep
    public void sleep(Integer n){
        try{
            Thread.sleep(n);
        }catch(InterruptedException ignored){}
    }

    // Linhas
    public void topLine(Integer sz){
        if(sz == 1){
            size = 20;
        }else if(sz == 2){
            size = 40;
        }else{
            size = 60;
        }
        System.out.print("╔");
        sleep(timer1);
        for(int i = 0; i < size; i++){
            System.out.print("═");
            sleep(timer1);
        }
        System.out.print("╗");
        sleep(150);
    }

    public void centralLine(Integer sz){
        if(sz == 1){
            size = 22;
        }else if(sz == 2){
            size = 42;
        }else{
            size = 62;
        }
        sleep(timer1);
        for(int i = 0; i < size; i++){
            System.out.print("═");
            sleep(timer1);
        }
        System.out.print("");
        sleep(150);
    }

    public void bottomLine(Integer sz){
        if(sz == 1){
            size = 20;
        }else if(sz == 2){
            size = 40;
        }else{
            size = 60;
        }
        System.out.print("╚");
        sleep(timer1);
        for(int i = 0; i < size; i++){
            System.out.print("═");
            sleep(timer1);
        }
        System.out.print("╝");
        sleep(150);
    }

    // Quebra de linhas
    public void br(Integer n){
        for(int i = 0; i < n; i++) {
            System.out.println();
        }
    }

    //FORMATA PARA REAIS
    public static String convertToReais(double valor) {
        Locale ptBr = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(ptBr).format(valor);
    }

    // Entry
    public static String entry(String texto) {
        System.out.print(texto);
        return input.next();
    }

    // Barrinha de carregamento
    public void loading(Integer sz){
        if(sz == 1){
            size = 22;
        }else if(sz == 2){
            size = 42;
        }else{
            size = 62;
        }
        sleep(timer2);
        for(int i = 0; i < size; i++){
            System.out.print("|");
            sleep(timer2);
        }
        for(int i = 0; i < size; i++){
            System.out.print("\b");
            sleep(timer2);
        }
        System.out.println();
        sleep(150);
    }

    // Limpeza de tela
    public void limparTela(){
        for(int i = 0; i < 80; i++){
            System.out.println("-");
        }
    }

}
