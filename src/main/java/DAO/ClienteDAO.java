package DAO;

import CONTROLADOR.Conexion;
import MODELO.Cliente;
import VISTA.Validaciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDAO {
    Conexion c = new Conexion();
    Validaciones v = new Validaciones();
    
    public void create(Cliente cl) {
        try (Connection con = c.conectar()) {
            String sql = "insert into cliente(nombre, identificacion, correo, telefono) values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());
            ps.executeUpdate();
            System.out.println("Cliente creado correctamente!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Cliente ce) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update cliente set nombre=?, identificacion=?, correo=?, celular=?  where id=?");
            ps.setString(1, ce.getNombre());
            ps.setString(2, ce.getIdentificacion());
            ps.setString(3, ce.getCorreo());
            ps.setString(4, ce.getTelefono());
            ps.executeUpdate();
            System.out.println("CLIENTE ACTUALIZAD@ CORRECTAMENTE!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> respuesta = new ArrayList<>();
        
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from cliente");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                respuesta.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }    
    
    public Cliente buscar(int id) {
        Cliente cliente = null;
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from cliente where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
 
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }

    public void delete(Cliente cliente) {
        if (cliente == null) {
            System.out.println("NO EXISTE DICHO CELULAR!");
        } else {
            int op = JOptionPane.showConfirmDialog(null, "¿Esta segur@ de eliminar a " + cliente.getNombre()+ "?", null, JOptionPane.YES_NO_OPTION);
            if (op == 0) {
                try (Connection con = c.conectar()) {
                    PreparedStatement ps = con.prepareStatement("delete from cliente where id=?");
                    ps.setInt(1, cliente.getId());
                    ps.executeUpdate();
                    System.out.println("Cliente " + cliente.getNombre() + " eliminad@ con exito!");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Operacion cancelada!");
            }
        }
    }
}
