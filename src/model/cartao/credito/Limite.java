package model.cartao.credito;

public class Limite {

    private float limiteDisponivel;
    private float limiteUtilizado;
    private float limiteTotal;

    // Construtor
    public Limite( float limiteTotal, float limiteUtilizado) {

        this.limiteTotal = limiteTotal;
        this.limiteUtilizado = limiteUtilizado;
        this.limiteDisponivel = getLimiteTotal() - getLimiteUtilizado();
    }

    // Getters
    public float getLimiteDisponivel() {
        return limiteDisponivel;
    }
    public void setLimiteDisponivel(float limiteDisponivel) {
        limiteDisponivel = limiteDisponivel;
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
    public void setLimiteTotal(float limiteTotal) {
        this.limiteTotal = limiteTotal;
    }

    // Exibir Limite
    public void exibirLimite(){

    }

    // AjustarLimite
    public void ajustarLimite(Float limiteTotal){

    }
}
