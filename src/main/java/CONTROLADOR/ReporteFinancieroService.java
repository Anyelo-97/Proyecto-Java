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
        
        Detalle_VentaDAO dvdao = new Detalle_VentaDAO();
        List<Detalle_Venta> lista = dvdao.listar();

        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay datos de ventas.");
                    System.out.println(lista);
            return;
        }
    
        Map<Double, Integer> top = lista.stream().filter(dv -> dv.getCelular().getMarca() != null).collect(Collectors.groupingBy(dv -> dv.getPrecio_unitario(),Collectors.summingInt(Detalle_Venta::getCantidad)));

       
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reporte_ingresos_marcas.txt"))) {

            writer.write("=== Reporte de ingresos por marca ===\n");
            
            topm.entrySet().stream().sorted((a, b) -> b.getValue().compareTo(a.getValue())).limit(3).forEach(e -> {
                
                String marca = (e.getKey() != null) ? e.getKey() : "Desconocido";String linea = "Marca: " + marca + " | Vendidos: " + e.getValue();
                
                System.out.println(linea);
                
                try {
                    writer.write(linea);
                    writer.newLine();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });

            System.out.println("Reporte generado reporte_ingresos_marcas.txt");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
