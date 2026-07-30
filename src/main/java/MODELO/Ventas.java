package MODELO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ventas {
    private int id;
    private Cliente cliente;
    private LocalDateTime fecha_venta;
    private Double total_pagar;
    private ArrayList<Detalle_Venta> detalle_venta  = new ArrayList<>();
    

    public Ventas(int id, Cliente cliente, LocalDateTime fecha_venta, Double total_pagar) {
        this.id = id;
        this.cliente = cliente;
        this.fecha_venta = fecha_venta;
        this.total_pagar = total_pagar;
        this.detalle_venta = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Double getTotal_pagar() {
        return total_pagar;
    }

    public void setTotal_pagar(Double total_pagar) {
        this.total_pagar = total_pagar;
    }

    public ArrayList<Detalle_Venta> getDetalle_venta() {
        return detalle_venta;
    }

    public void setDetalle_venta(ArrayList<Detalle_Venta> detalle_venta) {
        this.detalle_venta = detalle_venta;
    }

    
    
    @Override
    public String toString() {
        return """
               Id cliente:       %s
               Fecha venta:      %s
               Total a pagar:    %s 
               
               """.formatted(cliente, fecha_venta, total_pagar);
    }
}

