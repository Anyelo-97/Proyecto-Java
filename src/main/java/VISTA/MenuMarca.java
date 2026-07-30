package VISTA;

import CONTROLADOR.MarcaController;
import MODELO.Marca;

public class MenuMarca {

    public void Menu() {
        int op;
        Validaciones v = new Validaciones();
        MarcaController mc = new MarcaController();
        
        do {

            op = v.validarEnteroRango("""
                                    ==========MENU MARCA==========
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
                    String nombre = v.validarTexto("Ingrese el nombre");
                    String sistema_operativo  = v.validarTexto("Ingrese el sistema operativo");
                    Marca mar = new Marca(0, nombre, sistema_operativo);
                    mc.insert(mar);
                    break;
                case 2:
                    mc.listar();
                    int id = v.validarEntero("Ingrese el id a eliminar");
                    mc.delete(id);
                    break;
                case 3:
                    mc.listar();
                    int id_actualizar = v.validarEntero("Ingrese el id a actualizar");
                    mc.update(id_actualizar);
                    break;
                case 4:
                    mc.mostrar();
                    int id_buscar = v.validarEntero("Ingrese el id a buscar");
                    mc.buscar(id_buscar);
                    break;
                case 5:
                    mc.listar();
                    break;
            }
        } while (op != 6);
    }
}
