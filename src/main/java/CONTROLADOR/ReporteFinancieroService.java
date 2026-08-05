package CONTROLADOR;

import DAO.Detalle_VentaDAO;
import DAO.VentasDAO;
import MODELO.Detalle_Venta;
import MODELO.Ventas;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReporteFinancieroService {
    public void ReportedeIngresosMensualesporMarca(){
        VentasDAO vdao = new VentasDAO();
        List<Ventas> lista = vdao.listar();
        
        Detalle_VentaDAO dvdao = new Detalle_VentaDAO();
        List<Detalle_Venta> listamarca = dvdao.listar();

        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay datos de ventas.");
                    System.out.println(listamarca);
            return;
        }
            
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reporte_ingresos_marcas.txt"))) {

            writer.write("=== Reporte de ingresos por marca ===\n");
            
            listamarca.stream().filter(dv -> dv.getVenta() != null).forEach(dv -> {String marcas = "Marca" + listamarca + " total: " + dv.getCantidad();
       
            try {
                writer.write(marcas + "\n");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            

            System.out.println("Reporte generado reporte_ingresos_marcas.txt");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
