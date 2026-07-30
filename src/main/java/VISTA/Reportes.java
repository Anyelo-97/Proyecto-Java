package VISTA;

import DAO.CelularDAO;
import DAO.Detalle_VentaDAO;
import MODELO.Celular;
import MODELO.Detalle_Venta;
import java.util.List;
import java.util.stream.Collectors;

public class Reportes {
    
    public void alertaStock(List<Celular> listaCelulares) {
        CelularDAO dvdao = new CelularDAO();

        dvdao.listar().stream().filter(c -> c.getStock() < 5).forEach(c -> System.out.println("Stock bajo: " + c.getModelo()));
    }
    
    public void topVentas(){
        Detalle_VentaDAO dvdao = new Detalle_VentaDAO();
        List<Detalle_Venta> lista = dvdao.listar();

        lista.stream()
            .collect(Collectors.groupingBy( 
                dv -> dv.getCelular().getModelo(),
                Collectors.summingInt(Detalle_Venta::getCantidad)
            ))
            .entrySet().stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .limit(3)
            .forEach(e -> System.out.println(
                "Celular: " + e.getKey() + " | Vendidos: " + e.getValue()
            ));
   }
}
