package VISTA;

import java.util.Scanner;

public class Validaciones {

    public int validarEntero(String mensaje) {
        int dato = 0;
        do {
            try {
                System.out.println(mensaje);
                dato = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.println("Error de ingreso de datos.");
            }
        } while (dato < 1);
        return dato;
    }

    public double validarDecimal(String mensaje) {
        double dato = 0;
        do {
            try {
                System.out.println(mensaje);
                dato = new Scanner(System.in).nextDouble();
            } catch (Exception e) {
                System.out.println("Error de ingreso de datos.");
            }
        } while (dato < 1);
        return dato;
    }

    public int validarEnteroRango(String mensaje, int minimo, int maximo) {
        int dato = 0;
        do {
            try {
                System.out.println(mensaje);
                dato = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.println("Error de ingreso de datos.");
            }
        } while (dato < minimo || dato > maximo);
        return dato;
    }

    public String validarTexto(String mensaje) {
        String dato = "";
        do {
            try {
                System.out.println(mensaje);
                dato = new Scanner(System.in).nextLine();
            } catch (Exception e) {
                System.out.println("Error de ingreso de datos.");
            }
        } while (dato == null || dato.isBlank());
        return dato;
    }
    
    public String validarCorreo(String mensaje) {
        Scanner sc = new Scanner(System.in);
        String correo;

        String regex = "^[\\w.-]+@gmail+\\.com$";
        
        

        do {
            System.out.println(mensaje);
            correo = sc.nextLine();

            if (correo == null || correo.isBlank()) {
                System.out.println("El correo no puede estar vacío.");
                continue;
            }

            if (!correo.matches(regex)) {
                System.out.println("Formato de correo invalido. Ejemplo: usuario@gmail.com");
                correo = "";
            }

        } while (correo.isBlank());

        return correo;
    }
}
