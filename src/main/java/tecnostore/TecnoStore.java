package tecnostore;

import CONTROLADOR.Conexion;
import VISTA.MenuPrincipal;


public class TecnoStore {

    public static void main(String[] args) {
        Conexion c = new Conexion();
        c.conectar();
        
        MenuPrincipal mp = new MenuPrincipal();
        mp.Menu();
    }
}
