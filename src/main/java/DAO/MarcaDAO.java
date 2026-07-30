package DAO;

import CONTROLADOR.Conexion;
import MODELO.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MarcaDAO {
    Conexion c = new Conexion();

    public void create(Marca m) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into marca(nombre, sistema_operativo) values (?,?)");
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getSistema_operativo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Marca marca) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update marca set nombre=?, sistema_operativo=?, gama=?  where id=?");
            ps.setString(1, marca.getNombre());
            ps.setString(2, marca.getSistema_operativo());
            ps.executeUpdate();
            System.out.println("MARCA ACTUALIZADA CORRECTAMENTE!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public ArrayList<Marca> listar() {
        ArrayList<Marca> respuesta = new ArrayList<>();
        
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from marca");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                respuesta.add(new Marca(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }    
     
    public Marca buscar(int id) {
        Marca marca = null;
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from marca where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                marca = new Marca(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return marca;
    }

    public void delete(Marca marca) {
        if (marca == null) {
            System.out.println("NO EXISTE DICHA MARCA!");
        } else {
            int op = JOptionPane.showConfirmDialog(null, "¿Esta segur@ de eliminar a " + marca.getNombre()+ "?", null, JOptionPane.YES_NO_OPTION);
            if (op == 0) {
                try (Connection con = c.conectar()) {
                    PreparedStatement ps = con.prepareStatement("delete from marca where id=?");
                    ps.setInt(1, marca.getId());
                    ps.executeUpdate();
                    System.out.println("marca " + marca.getNombre() + " eliminada con exito!");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Operacion cancelada!");
            }
        }
    }
     
 

}
