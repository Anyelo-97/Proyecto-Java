package CONTROLADOR;

import DAO.CelularDAO;
import DAO.Detalle_VentaDAO;
import MODELO.Celular;
import MODELO.Detalle_Venta;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Reportes {

    public void alertaStockTxt() {
        CelularDAO cdao = new CelularDAO();
        List<Celular> lista = cdao.listar();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Reporte_stock.txt"))) {

            writer.write("=== REPORTE STOCK BAJO ===");

            lista.stream().filter(c -> c.getStock() < 5).forEach(c -> {String stockBajo = "Stock bajo: " + c.getModelo() + " | Cantidad: " + c.getStock();
            System.out.println(stockBajo);
            try {
                writer.write(stockBajo + "\n");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });

            System.out.println("Reporte generado: Reporte_stock.txt");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void topVentasTxt() {
        Detalle_VentaDAO dvdao = new Detalle_VentaDAO();
        List<Detalle_Venta> lista = dvdao.listar();

        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay datos de ventas.");
            return;
        }

        Map<String, Integer> top = lista.stream().filter(dv -> dv.getCelular() != null).collect(Collectors.groupingBy(dv -> dv.getCelular().getModelo(),Collectors.summingInt(Detalle_Venta::getCantidad)));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Reporte_top_ventas.txt"))) {

            writer.write("=== TOP 3 VENTAS ===\n");

            if (top.isEmpty()) {
                writer.write("No hay datos suficientes para generar el reporte.\n");
                System.out.println("No hay datos suficientes para generar el reporte.");
                return;
            }

            top.entrySet().stream().sorted((a, b) -> b.getValue().compareTo(a.getValue())).limit(3).forEach(e -> {
                
                String modelo = (e.getKey() != null) ? e.getKey() : "Desconocido";String linea = "Celular: " + modelo + " | Vendidos: " + e.getValue();
                
                System.out.println(linea);
                
                try {
                    writer.write(linea);
                    writer.newLine();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });

            System.out.println("Reporte generado: Reporte_top_ventas.txt");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}