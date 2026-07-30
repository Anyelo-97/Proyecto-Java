package VISTA;

import CONTROLADOR.CelularController;
import CONTROLADOR.MarcaController;
import MODELO.Marca;
import MODELO.Celular;

public class MenuCelular {

    public void Menu() {
        int op;
        do {
            Validaciones v = new Validaciones();
            CelularController cc = new CelularController();
            MarcaController mc = new MarcaController();
            op = v.validarEnteroRango("""
                                    ==========MENU CELULAR==========
                                    Digite la opcion:
                                    1. Agregar.
                                    2. Eliminar.
                                    3. Actualizar.
                                    4. Buscar.
                                    5. Listar.
                                    6. Salir
                                    """, 1, 6);
            switch (op) {
                case 1:
                    System.out.println("====MODULO AGREGAR====");
                    String modelo = v.validarTexto("Ingrese el modelo");
                    double precio = v.validarDecimal("Ingrese el precio");
                    int stock = v.validarEntero("Ingrese el stock");
                    Celular.Gama gama = null;

                    while (gama == null) {
                        try {
                            String gamav = v.validarTexto("Ingrese la gama (BAJA, MEDIA, ALTA)");
                            gama = Celular.Gama.valueOf(gamav.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Gama inválida. Intente nuevamente.");
                        }
                    }
                    Marca marca = cc.validarmarca();
 
                    cc.insert(new Celular(0, modelo, precio, stock, gama, marca));
                    break;
                case 2:
                    cc.listar();
                    int id = v.validarEntero("Ingrese el id a eliminar");
                    cc.eliminar(id);
                    break;
                case 3:
                    cc.mostrar();
                    int id_actualizar = v.validarEntero("Ingrese el id a actualizar");  
                    cc.update(id_actualizar);
                    break;
                case 4:
                    int id_buscar = v.validarEntero("Ingrese el id a buscar");
                    cc.buscar(id_buscar);
                    break;
                case 5:
                    cc.listar();
                    break;
            }
        } while (op != 6);
    }
}
