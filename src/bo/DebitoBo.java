package bo;
import dao.Bd;
import model.cartao.credito.Credito;
import model.cartao.debito.Debito;

public class DebitoBo {

    public static void validaInsercaoDebito(Integer tipoConta, Debito debito){

        if(Bd.buscaCartoesDebitoCliente(tipoConta) != null){
            Bd.insereCartaoDebito(debito);
        }

    }

    public static void validaInsercaoCredito(Integer tipoConta, Credito credito){


    }

}
