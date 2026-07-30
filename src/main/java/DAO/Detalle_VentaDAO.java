package DAO;

import CONTROLADOR.Conexion;
import MODELO.Celular;
import MODELO.Detalle_Venta;
import MODELO.Ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Detalle_VentaDAO {
    Conexion c = new Conexion();

    public void create(Detalle_Venta dv){
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into detalle_venta(id_venta, id_celular, cantidad, precio_unitario) values (?,?,?,?)");
            ps.setInt(1, dv.getVenta().getId());
            ps.setInt(2, dv.getCelular().getId());
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getPrecio_unitario());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<Detalle_Venta> listar() {
        ArrayList<Detalle_Venta> lista = new ArrayList<>();

        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from detalle_venta where id_venta=?");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ventas venta = new VentasDAO().buscar(rs.getInt("id_venta"));
                Celular celular = new CelularDAO().buscar(rs.getInt("id_celular"));

                Detalle_Venta dv = new Detalle_Venta(rs.getInt(1),venta,celular,rs.getInt(2),rs.getDouble(3));

                lista.add(dv);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }    
    
    public Detalle_Venta buscar(int id) {
        Detalle_Venta dv = null;
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from detalle_venta where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                int idVenta = rs.getInt("id_venta");
                Ventas ventas = new VentasDAO().buscar(idVenta);
                
                int idCelular = rs.getInt("id_celular");
                Celular celular = new CelularDAO().buscar(idCelular);
                
                dv = new Detalle_Venta(rs.getInt(1), ventas, celular, rs.getInt(4), rs.getDouble(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dv;
    }

}
