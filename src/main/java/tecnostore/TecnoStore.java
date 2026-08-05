package tecnostore;

import CONTROLADOR.Conexion;
import VISTA.MenuPrincipal;
import CONTROLADOR.ReporteFinancieroService;

public class TecnoStore {

    public static void main(String[] args) {
        Conexion c = new Conexion();
        c.conectar();
        
        MenuPrincipal mp = new MenuPrincipal();
        mp.Menu();
        
        ReporteFinancieroService r = new ReporteFinancieroService();
        r.ReportedeIngresosMensualesporMarca();
        
    }
}
