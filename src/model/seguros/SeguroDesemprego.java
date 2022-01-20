package model.seguros;

public class SeguroDesemprego extends Apolice{

    // Construtor
    public SeguroDesemprego() {
        super(16.00f, "    Seguro desemprego", true,
                """
                        I. Necessário trabalhar com registro CLT, com o tempo\s
                        mínimo de estabilidade de 12 meses.
                        
                        II. Válido apenas para desligamento involuntários e sem\s
                        justa causa.
                        
                        III. Não é valido em caso de demissão em massa (10% ou\s
                        mais de demissões simultâneas) ou falência/encerramento\s
                        das atividades.
                        
                        IV. O valor do seguro individual é de R$16,00 ao ano.""".indent(4));

    }


}
