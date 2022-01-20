package model.seguros;

public class SeguroVida extends Apolice{

    // Construtor
    public SeguroVida() {
        super(36.00f, "    Seguro de vida", true,
                ("""
                        I. Indenização por despesas médico-hospitalares, ou por\s
                        perda parcial ou total do funcionamento dos membros ou\s
                        órgãos;
                        
                        II. Reembolso de custos em diagnóstico de doenças graves,\s
                        como infarto, acidente vascular cerebral e câncer;
                        
                        III. Assistência funeral, para você e a sua família.\s
                        
                        IV. O valor do seguro individual é de R$36,00 ao ano""".indent(4)));
    }
}
