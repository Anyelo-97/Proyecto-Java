package DAO;

import CONTROLADOR.Conexion;
import MODELO.Ventas;
import MODELO.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentasDAO {
    Conexion c = new Conexion();

    public void create(Ventas v ){
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into ventas(id_cliente, fecha_venta, total_pagar) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, v.getCliente().getId());
            ps.setObject(2, v.getFecha_venta());
            ps.setDouble(3, v.getTotal_pagar());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                v.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Ventas> listar() {
        ArrayList<Ventas> respuesta = new ArrayList<>();
        
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from ventas");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                Cliente cliente = new ClienteDAO().buscar(idCliente);
                Ventas v = new Ventas(rs.getInt(1), cliente, rs.getTimestamp("fecha_venta").toLocalDateTime(), rs.getDouble("total_pagar"));
            
                v.setId(rs.getInt("id"));
                respuesta.add(v);
            };
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }    
    
    public Ventas buscar(int id) {
        Ventas ventas = null;
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from ventas where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                Cliente cliente = new ClienteDAO().buscar(idCliente);
                
                ventas = new Ventas(rs.getInt(1), cliente,rs.getTimestamp("fecha_venta").toLocalDateTime(),rs.getDouble("total_pagar"));
                ventas.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ventas;
    }        
}
