package VISTA;


import CONTROLADOR.CelularController;
import CONTROLADOR.ClienteController;
import CONTROLADOR.VentasController;
import MODELO.Celular;
import MODELO.Cliente;
import MODELO.Detalle_Venta;
import MODELO.Ventas;
import java.time.LocalDateTime;

public class MenuVentas {
    public void Menu() {
        int op;
        do {
            Validaciones v = new Validaciones();
            VentasController vc = new VentasController();
            ClienteController cc = new ClienteController();
            op = v.validarEnteroRango("""
                                    ==========MENU Venta==========
                                    Digite la opcion:
                                    1. Agregar.
                                    2. Buscar.
                                    3. Listar.
                                    4. Salir
                                    """, 1, 4);
            switch (op) {
                case 1:
                    System.out.println("====MODULO AGREGAR====");
                    int idcliente = v.validarEntero("Ingrese el id del cliente");
                    
                    Cliente c = cc.buscar(idcliente);
                    while (c == null) {
                        idcliente = v.validarEntero("Cliente no existe, intente nuevamente");
                        c = cc.buscar(idcliente);
                    }
                    
                    LocalDateTime fecha_venta = LocalDateTime.now();

                    Ventas venta = new Ventas(0, c, fecha_venta, 0.0);
                    CelularController celularController = new CelularController();

                    int continuar = 0;

                    do {
                        System.out.println("==== AGREGAR CELULAR ====");

                        celularController.mostrar();

                        int idCelular = v.validarEntero("Ingrese el id del celular");
                        Celular cel = celularController.celularDao.buscar(idCelular);

                        if (cel != null) {
                            int cantidad = v.validarEntero("Cantidad");

                            if (cantidad <= 0 || cantidad > cel.getStock()) {
                                System.out.println("Cantidad inválida o stock insuficiente");
                                continue;
                            }

                            double precio = cel.getPrecio();

                            Detalle_Venta dv = new Detalle_Venta(0, venta, cel, cantidad, precio);
                            venta.getDetalle_venta().add(dv);

                            venta.setTotal_pagar(
                                venta.getTotal_pagar() + ((cantidad * precio) * 1.19)
                            );
                        } else {
                            System.out.println("Celular no existe");
                        }

                        continuar = v.validarEnteroRango("¿Agregar otro producto? 1=Si 2=No", 1, 2);

                    } while (continuar == 1);
;  

                    vc.insert(venta);
                    break;
                case 2:
                    int id = v.validarEntero("Ingrese el id a buscar");
                    vc.buscar(id);
                    break;
                case 3:
                    vc.listar();
                    break;

            }
        } while (op != 4);
    }
}
