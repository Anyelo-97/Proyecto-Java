package MODELO;

public class Marca {
    private int id;
    private String nombre;
    private String sistema_operativo;
    
    public Marca(int id, String nombre, String sistema_operativo) {
        this.id = id;
        this.nombre = nombre;
        this.sistema_operativo = sistema_operativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSistema_operativo() {
        return sistema_operativo;
    }

    public void setSistema_operativo(String sistema_operativo) {
        this.sistema_operativo = sistema_operativo;
    }

   @Override
    public String toString() {
        return """
               Nombre marca:        %s
               Sistema operativo:   %s
               
               """.formatted(nombre, sistema_operativo);
    }
    
}
