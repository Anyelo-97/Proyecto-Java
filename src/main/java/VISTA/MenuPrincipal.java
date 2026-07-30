package VISTA;

import CONTROLADOR.Reportes;

public class MenuPrincipal {

    public void Menu() {
        Validaciones v = new Validaciones();
        int op;
        do {
            op = v.validarEnteroRango("""
                                    BIENVENIDO A NUESTRO SISTEMA
                                    Digite la opcion a escoger:
                                    1. Marcas.
                                    2. Celulares.
                                    3. Clientes
                                    4. Ventas
                                    5. Reportes
                                    6. Salir
                                    """, 1, 6);
            switch (op) {
                case 1:
                    MenuMarca m = new MenuMarca();
                    m.Menu();
                    break;
                case 2:
                    MenuCelular ml = new MenuCelular();
                    ml.Menu();
                    break;
                case 3:
                    MenuCliente cl = new MenuCliente();
                    cl.Menu();
                    break;
                case 4:
                    MenuVentas venta = new MenuVentas();
                    venta.Menu();
                    break;
                    
                case 5:
                    Reportes repo = new Reportes();
                    repo.alertaStockTxt();

                    break;
                case 6:
                    System.out.println("Gracias por usar nuestra aplicacion.");
                    break;
            }
        } while (op != 6);
    }
}
