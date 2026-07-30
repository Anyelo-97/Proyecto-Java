package DAO;

import CONTROLADOR.Conexion;
import MODELO.Celular;
import MODELO.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CelularDAO {
    Conexion c = new Conexion();

    public void create(Celular ce) {
        try (Connection con = c.conectar()) {
            String sql = "insert into celular(modelo, precio, stock, gama, id_marca) values (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ce.getModelo());
            ps.setDouble(2, ce.getPrecio());
            ps.setInt(3, ce.getStock());
            ps.setString(4, ce.getGama().name());
            ps.setObject(5, ce.getMarca().getId());
            ps.executeUpdate();
            System.out.println("Celular creado correctamente!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(Celular ce) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update celular set modelo=?, precio=?, stock=?, gama=?, id_marca=?  where id=?");
            ps.setString(1, ce.getModelo());
            ps.setDouble(2, ce.getPrecio());
            ps.setInt(3, ce.getStock());
            ps.setString(4, ce.getGama().name());
            ps.setObject(5, ce.getMarca().getId());
            ps.setInt(6, ce.getId());    
            ps.executeUpdate();
            System.out.println("CELULAR ACTUALIZADO CORRECTAMENTE!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Celular> listar() {
        ArrayList<Celular> respuesta = new ArrayList<>();
        MarcaDAO md = new MarcaDAO(); 
        
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from celular");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idMarca = rs.getInt("id_marca");
                Marca m = md.buscar(idMarca);
                Celular.Gama gama = Celular.Gama.valueOf(rs.getString("gama").toUpperCase());
                
                Celular cel = new Celular(rs.getInt(1),rs.getString(2), rs.getDouble(3), rs.getInt(4), gama ,m);
                cel.setGama(Celular.Gama.valueOf(rs.getString("gama")));
                respuesta.add(cel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }    
    
    public Celular buscar(int id) {
        Celular celular = null;
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from celular where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idMarca = rs.getInt("id_marca");
                Marca m = new MarcaDAO().buscar(idMarca);
                Celular.Gama gama = Celular.Gama.valueOf(rs.getString("gama").toUpperCase());
                
                celular = new Celular(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), gama, m);
                celular.setGama(Celular.Gama.valueOf(rs.getString("gama")));
                celular.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return celular;
    }

    public void delete(Celular celular) {
        if (celular == null) {
            System.out.println("NO EXISTE DICHO CELULAR!");
        } else {
            int op = JOptionPane.showConfirmDialog(null, "¿Esta segur@ de eliminar a " + celular.getModelo()+ "?", null, JOptionPane.YES_NO_OPTION);
            if (op == 0) {
                try (Connection con = c.conectar()) {
                    PreparedStatement ps = con.prepareStatement("delete from celular where id=?");
                    ps.setInt(1, celular.getId());
                    ps.executeUpdate();
                    System.out.println("celular " + celular.getModelo() + " eliminada con exito!");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Operacion cancelada!");
            }
        }
    }
}
