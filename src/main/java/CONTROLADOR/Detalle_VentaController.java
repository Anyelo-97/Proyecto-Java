package CONTROLADOR;

import DAO.Detalle_VentaDAO;
import DAO.VentasDAO;
import DAO.CelularDAO;
import MODELO.Celular;
import MODELO.Detalle_Venta;
import MODELO.Ventas;
import java.util.ArrayList;

public class Detalle_VentaController { 

    VentasDAO ventasDAO = new VentasDAO();
    Detalle_VentaDAO detalle_VentaDao = new Detalle_VentaDAO();
    CelularDAO celularDao = new CelularDAO();

    public void insert(Ventas venta) {

        ventasDAO.create(venta);

        for (Detalle_Venta dv : venta.getDetalle_venta()) {
            dv.setVenta(venta);
            detalle_VentaDao.create(dv);

            Celular cel = celularDao.buscar(dv.getCelular().getId());
            cel.setStock(cel.getStock() - dv.getCantidad());
            celularDao.update(cel);
        }
    }

    public void listar() {
        detalle_VentaDao.listar().forEach(System.out::println);
    }
    
    public Detalle_Venta buscar(int id) {
        return detalle_VentaDao.buscar(id);
    }
    
    public void mostrar() {
        ArrayList<Detalle_Venta> lista = detalle_VentaDao.listar();
        for (Detalle_Venta cl : lista) {
            System.out.println(cl.getId() + " - " + cl.getVenta() + " - " + cl.getCelular() + " - " + cl.getPrecio_unitario());
        }
    }
}
