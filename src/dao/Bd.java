package dao;
import model.cliente.Cliente;
import java.util.ArrayList;

public class Bd {

    public static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void addCliente(Cliente cliente){
        clientes.add(cliente);
    }
}
