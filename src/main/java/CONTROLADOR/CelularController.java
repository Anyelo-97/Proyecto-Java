package CONTROLADOR;

import DAO.CelularDAO;
import DAO.MarcaDAO;
import MODELO.Celular;
import MODELO.Marca;
import VISTA.Validaciones;
import java.util.ArrayList;

public class CelularController {
    
    public CelularDAO celularDao = new CelularDAO();
    MarcaDAO marcaDAO = new MarcaDAO();
    Validaciones validaciones = new Validaciones();
    
    public Marca validarmarca() {
        ArrayList<Marca> m = marcaDAO.listar();
        for (Marca marca : m) {
            System.out.println("\n" + marca.getId() + " - " + marca.getNombre() + " - " + marca.getSistema_operativo());
        }
        int codigoMar = validaciones.validarEntero("Ingrese el codigo de la marca.");
        while (marcaDAO.buscar(codigoMar) == null) {
            codigoMar = validaciones.validarEntero("marca no encontrada, intente nuevamente.");
        }
        return marcaDAO.buscar(codigoMar);
    }
    
    public void insert(Celular celular) {
        celularDao.create(celular);
    }

    public void update(Celular celular) {
        celularDao.update(celular);
    }
    
    public void delete(Celular celular){
        celularDao.delete(celular);
    }

    public void listar() {
        celularDao.listar().forEach(System.out::println);
    }

    public void eliminar(int id) {
        Celular ce = celularDao.buscar(id);
        celularDao.delete(ce);
    }

    public void buscar(int id) {
        System.out.println(celularDao.buscar(id));
    }

    public void mostrar() {
        ArrayList<Celular> ce = celularDao.listar();
        for (Celular celular : ce) {
            System.out.println("\n" + celular.getId() + " - " + celular.getModelo() + " - " + celular.getPrecio() + " - " + celular.getMarca() );
        }
    }

    public void update(int id) {
        Celular celular = celularDao.buscar(id);
        Validaciones v = new Validaciones();
        if (celular == null) {
            System.out.println("NO SE ENCUENTRA DICHO PRODUCTO A ACTUALIZAR!");
        } else {
            int op = v.validarEnteroRango("""
                                        Digite la opcion:
                                        1. Modelo
                                        2. Precio
                                        3. stock
                                        4. Cancelar
                                        """, 1, 4);
            switch (op) {
                case 1:
                    celular.setModelo(v.validarTexto("Ingrese el nuevo nombre"));
                    break;
                case 2:
                    celular.setPrecio(v.validarDecimal("Ingrese el nuevo precio de compra"));
                    break;
                case 3:
                    celular.setStock(v.validarEntero("Ingrese el nuevo stock"));
                    break;
                case 4:
                    System.out.println("Operacion cancelada!");
                    break;
            }
            celularDao.update(celular);
        }
    }
}
