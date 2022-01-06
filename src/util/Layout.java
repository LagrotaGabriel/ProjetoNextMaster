package util;

public class Layout {

    public void Sleep(Integer n){
        try{
            Thread.sleep(n);
        }catch(InterruptedException ignored){}
    }

    public void ULine(Integer m){
        System.out.print("╔");
        Sleep(m);
        for(int i = 0; i < 60; i++){
            System.out.print("═");
            Sleep(m);
        }
        System.out.println("╗");
        Sleep(150);
    }

    public void BLine(Integer m){
        System.out.print("╚");
        Sleep(m);
        for(int i = 0; i < 60; i++){
            System.out.print("═");
            Sleep(m);
        }
        System.out.println("╝");
        Sleep(150);
    }

    public void CLine(Integer m){
        Sleep(m);
        for(int i = 0; i < 62; i++){
            System.out.print("═");
            Sleep(m);
        }
        System.out.println("");
        Sleep(150);
    }

    public void Loading(Integer m){
        Sleep(m);
        for(int i = 0; i < 62; i++){
            System.out.print("|");
            Sleep(m);
        }
        for(int i = 0; i < 62; i++){
            System.out.print("\b");
            Sleep(m);
        }
        System.out.println();
        Sleep(150);
    }

    public void LimparTela(){
        for(int i = 0; i < 80; i++){
            System.out.println("-");
        }
    }

}
