package model.cartao.credito;

public class Limite {

    // ATRIBUTOS
    private float limiteDisponivel;
    private float limiteUtilizado;
    private final float limiteTotal;

    // CONSTRUTOR
    public Limite( float limiteTotal, float limiteUtilizado) {

        this.limiteTotal = limiteTotal;
        this.limiteUtilizado = limiteUtilizado;
        this.limiteDisponivel = getLimiteTotal() - getLimiteUtilizado();

    }

    // GETTERS E SETTERS
    public float getLimiteDisponivel() {
        return limiteDisponivel;
    }
    public void setLimiteDisponivel(float limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }
    public float getLimiteUtilizado() {
        return limiteUtilizado;
    }
    public void setLimiteUtilizado(float limiteUtilizado) {
        this.limiteUtilizado = limiteUtilizado;
    }
    public float getLimiteTotal() {
        return limiteTotal;
    }

    // ATUALIZA LIMITE
    public void atualizarLimite(){
        this.limiteDisponivel -= getLimiteUtilizado();
    }

    // ATUALIZA LIMITE COM O TOTAL
    public void atualizarTotal(){
        this.limiteDisponivel = getLimiteTotal() - getLimiteUtilizado();
    }
}
