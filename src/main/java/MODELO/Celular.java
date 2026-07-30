package MODELO;

public class Celular {
    private int id;
    private String modelo;
    private double precio;
    private int stock;
    public enum Gama {
        BAJA,
        MEDIA,
        ALTA
    };
    private Gama gama;
    private Marca marca;

    public Celular(int id, String modelo, double precio, int stock, Gama gama, Marca marca) {
        this.id = id;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.gama = gama;
        this.marca = marca;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public Gama getGama() { 
        return gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }


    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return """
               Modelo:      %s  
               Precio:      %s
               Stock:       %s
               Gama:        %s 
               %s
               
               """.formatted(modelo, precio, stock, gama, marca);
    }
}
