package model.pix;

public enum TipoChavePix {

    CPF, EMAIL, TELEFONE, ALEATORIO;
    private Integer id;

    public Integer getId(){
        return(id);
    }
    public void setId(Integer id){
        this.id = id;
    }

}
