package util;

import java.text.NumberFormat;
import java.util.Locale;

public class Layout {

    // Atributos
    Integer timer1, timer2, size;

    // Construtor
    public Layout(Integer timer1,Integer  timer2){
        this.timer1 = timer1;
        this.timer2 = timer2;
    }

    // Método Sleep
    public void Sleep(Integer n){
        try{
            Thread.sleep(n);
        }catch(InterruptedException ignored){}
    }

    // Linhas
    public void TopLine(Integer sz){
        if(sz == 1){
            size = 20;
        }else if(sz == 2){
            size = 40;
        }else{
            size = 60;
        }
        System.out.print("╔");
        Sleep(timer1);
        for(int i = 0; i < size; i++){
            System.out.print("═");
            Sleep(timer1);
        }
        System.out.print("╗");
        Sleep(150);
    }

    public void CentralLine(Integer sz){
        if(sz == 1){
            size = 22;
        }else if(sz == 2){
            size = 42;
        }else{
            size = 62;
        }
        Sleep(timer1);
        for(int i = 0; i < size; i++){
            System.out.print("═");
            Sleep(timer1);
        }
        System.out.print("");
        Sleep(150);
    }

    public void BottomLine(Integer sz){
        if(sz == 1){
            size = 20;
        }else if(sz == 2){
            size = 40;
        }else{
            size = 60;
        }
        System.out.print("╚");
        Sleep(timer1);
        for(int i = 0; i < size; i++){
            System.out.print("═");
            Sleep(timer1);
        }
        System.out.print("╝");
        Sleep(150);
    }

    // Quebra de linhas
    public void br(Integer n){
        for(int i = 0; i < n; i++) {
            System.out.println();
        }
    }

    // FORMATA PARA REAIS
    public static String convertToReais(double valor) {
        Locale ptBr = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(ptBr).format(valor);
    }

    // Barrinha de carregamento
    public void Loading(Integer sz){
        if(sz == 1){
            size = 22;
        }else if(sz == 2){
            size = 42;
        }else{
            size = 62;
        }
        Sleep(timer2);
        for(int i = 0; i < size; i++){
            System.out.print("|");
            Sleep(timer2);
        }
        for(int i = 0; i < size; i++){
            System.out.print("\b");
            Sleep(timer2);
        }
        System.out.println();
        Sleep(150);
    }

    // Limpeza de tela
    public void LimparTela(){
        for(int i = 0; i < 80; i++){
            System.out.println("-");
        }
    }

}
