package CONTROLADOR;

import DAO.ClienteDAO;
import MODELO.Cliente;
import VISTA.Validaciones;
import java.util.ArrayList;

public class ClienteController {
    Conexion c = new Conexion();
    ClienteDAO clienteDao = new ClienteDAO();
    
    public void insert(Cliente cliente) {
        clienteDao.create(cliente);
    }
    
    public void delete(int id){
        Cliente c = clienteDao.buscar(id);
        clienteDao.delete(c);
    }
    
    public void listar() {
        clienteDao.listar().forEach(System.out::println);
    }
    
    public Cliente buscar(int id) {
        return clienteDao.buscar(id);
    }
    
    public void mostrar() {
        ArrayList<Cliente> lista = clienteDao.listar();
        for (Cliente cl : lista) {
            System.out.println(cl.getId() + " - " + cl.getNombre() + " - " + cl.getIdentificacion()+ " - " + cl.getCorreo() + " - " + cl.getTelefono());
        }
    }
    
   public void update(int id) {
        Cliente c = clienteDao.buscar(id);
        Validaciones v = new Validaciones();
        if (c == null) {
            System.out.println("NO SE ENCUENTRA DICHA CATEGORIA A ACTUALIZAR!");
        } else {
            int op = v.validarEnteroRango("""
                                        Digite la opcion:
                                        1. Nombre
                                        2. Correo
                                        3. Telefono
                                        4. Cancela
                                        """, 1, 4);
            switch (op) {
                case 1:
                    c.setNombre(v.validarTexto("Ingrese el nuevo nombre"));
                    break;
                case 2:
                    c.setCorreo(v.validarTexto("Ingrese el nuevo correo"));
                    break;
                case 3:
                    c.setTelefono(v.validarTexto("Ingrese el nuevo telefono"));
                    break;
                case 4:
                    System.out.println("Operacion cancelada!");
                    break;
            }
            clienteDao.update(c);
        }
    }    
}
