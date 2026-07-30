package CONTROLADOR;

import DAO.MarcaDAO;
import MODELO.Marca;
import VISTA.Validaciones;
import java.util.ArrayList;

public class MarcaController {

    MarcaDAO marcaDao = new MarcaDAO();
    
    public void insert(Marca marca) {
        marcaDao.create(marca);
    }
    
    public void delete(int id){
        Marca c = marcaDao.buscar(id);
        marcaDao.delete(c);
    }
    
    public void listar() {
        marcaDao.listar().forEach(System.out::println);
    }
    
    public void buscar(int id) {
       Marca c = marcaDao.buscar(id);
       System.out.println(c);
    }
    
    public void mostrar() {
        ArrayList<Marca> lista = marcaDao.listar();
        for (Marca cl : lista) {
            System.out.println(cl.getId() + " - " + cl.getNombre() + " - " + cl.getSistema_operativo());
        }
    }
    
   public void update(int id) {
        Marca c = marcaDao.buscar(id);
        Validaciones v = new Validaciones();
        if (c == null) {
            System.out.println("NO SE ENCUENTRA DICHA CATEGORIA A ACTUALIZAR!");
        } else {
            int op = v.validarEnteroRango("""
                                        Digite la opcion:
                                        1. Nombre
                                        2. Sistema operativo
                                        3. Cancela
                                        """, 1, 3);
            switch (op) {
                case 1:
                    c.setNombre(v.validarTexto("Ingrese el nuevo nombre"));
                    break;
                case 2:
                    c.setSistema_operativo(v.validarTexto("Ingrese el nuevo sistema operativo"));
                    break;
                case 3:
                    System.out.println("Operacion cancelada!");
                    break;
            }
            marcaDao.update(c);
        }
    }
}
