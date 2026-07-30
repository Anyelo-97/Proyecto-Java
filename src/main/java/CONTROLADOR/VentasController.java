package CONTROLADOR;

import DAO.CelularDAO;
import DAO.Detalle_VentaDAO;
import DAO.VentasDAO;
import MODELO.Celular;
import MODELO.Detalle_Venta;
import MODELO.Ventas;
import java.util.ArrayList;

public class VentasController {

    VentasDAO ventasDao = new VentasDAO();
    
    CelularDAO celularDao = new CelularDAO();
    
    public void insert(Ventas ventas) {
        Detalle_VentaDAO detalleDAO = new Detalle_VentaDAO();

        ventasDao.create(ventas);

        for (Detalle_Venta dv : ventas.getDetalle_venta()) {
            dv.setVenta(ventas);
            detalleDAO.create(dv);
            
            Celular cel = celularDao.buscar(dv.getCelular().getId());
            cel.setStock(cel.getStock() - dv.getCantidad());
            celularDao.update(cel);
        }
        

        
    }
    
    public void listar() {
        ventasDao.listar().forEach(System.out::println);
    }
    
    public Ventas buscar(int id) {
        return ventasDao.buscar(id);
    }
    
    public void mostrar() {
        ArrayList<Ventas> lista = ventasDao.listar();
        for (Ventas cl : lista) {
            System.out.println(cl.getId() + " - " + cl.getCliente()+ " - " + cl.getFecha_venta() + " - " + cl.getTotal_pagar());
        }
    }
    
   
}
