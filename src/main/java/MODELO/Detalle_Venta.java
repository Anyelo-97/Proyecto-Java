    package MODELO;

    public class Detalle_Venta {
        private int id;
        private Ventas venta;
        private Celular celular;
        private int cantidad;
        private double precio_unitario;

        public Detalle_Venta(int id, Ventas venta, Celular celular, int cantidad, double precio_unitario) {
            this.id = id;
            this.venta = venta;
            this.celular = celular;
            this.cantidad = cantidad;
            this.precio_unitario = precio_unitario;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Ventas getVenta() {
            return venta;
        }

        public void setVenta(Ventas venta) {
            this.venta = venta;
        }

        public Celular getCelular() {
            return celular;
        }

        public void setCelular(Celular celular) {
            this.celular = celular;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getPrecio_unitario() {
            return precio_unitario;
        }

        public void setPrecio_unitario(double precio_unitario) {
            this.precio_unitario = precio_unitario;
        }

        @Override
        public String toString() {
            return """
                   Id venta:              %s
                   Id celular:            %s
                   Cantidad:              %s 
                   Precio unitario:       %s

                   """.formatted(venta, celular, cantidad, precio_unitario);
        }

    }
