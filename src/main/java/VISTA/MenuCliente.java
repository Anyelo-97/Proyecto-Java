    package VISTA;

import CONTROLADOR.ClienteController;
import MODELO.Cliente;

public class MenuCliente {
    
    public void Menu() {
        int op;
        Validaciones v = new Validaciones();
        ClienteController mc = new ClienteController();
        
        do {

            op = v.validarEnteroRango("""
                                    ==========MENU CLIENTES==========
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
                    String identificacion  = v.validarTexto("Ingrese la identificacion");
                    String correo = v.validarCorreo("Ingrese el correo");
                    String telefono = v.validarTexto("Ingrese el telefono");
                    Cliente cl = new Cliente(0, nombre, identificacion, correo, telefono);
                    mc.insert(cl);
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
