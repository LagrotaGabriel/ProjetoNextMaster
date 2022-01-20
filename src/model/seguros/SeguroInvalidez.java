package model.seguros;

public class SeguroInvalidez extends Apolice{

    // Construtor
    public SeguroInvalidez() {
        super(26.00f, "Seguro invalidez", true,
                """
                        I. Invalidez parcial: ocorre quando há uma perda parcial\s
                        das funções. Por exemplo, uma pessoa que sofre um\s
                        acidente e perda a visão em um dos olhos.
                        
                        II. Invalidez total: ocorre quando há uma perda total das\s
                        funções. Intuitivamente, um bom exemplo seria o caso onde\s
                        a pessoa sofre um acidente e perde a visão em ambos os\s
                        olhos.
                        
                        III. O valor do seguro individual é de R$26,00 ao ano.""".indent(4));
    }
}
