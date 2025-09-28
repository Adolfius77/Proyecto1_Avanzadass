/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.ConexionDB;
import Interfacez.ICiudadanoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.autoridad;
import model.ciudadano;

/**
 *
 * @author USER
 */
public class ciudadanoDAO implements ICiudadanoDAO {

    @Override
    public boolean insertarCiudadano(ciudadano ciudadano) {
        String sql = "INSERT INTO ciudadano (nombre,apellido_paterno,apellido_materno,telefono,correo) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ciudadano.getNombre());
            ps.setString(2, ciudadano.getApellido_paterno());
            ps.setString(3, ciudadano.getApellido_materno());
            ps.setString(4, ciudadano.getTelefono());
            ps.setString(5, ciudadano.getCorreo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("error al agregar un ciudadano: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ciudadano obetenerPorId(int id_Ciudadano) {
        String sql = "SELECT * FROM ciudadano WHERE id_ciudadano = ?";
        ciudadano ciudadano = null;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_Ciudadano);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ciudadano = new ciudadano();
                ciudadano.setId_ciudadano(rs.getInt("id_ciudadano"));
                ciudadano.setNombre(rs.getString("nombre"));
                ciudadano.setApellido_paterno(rs.getString("apellido_paterno"));
                ciudadano.setApellido_materno(rs.getString("apellido_materno"));
                ciudadano.setTelefono(rs.getString("telefono"));
                ciudadano.setCorreo(rs.getString("correo"));

            }
        } catch (SQLException e) {
            System.err.println("error al obetener el ciuadadano por id: " + e.getMessage());
        }
        return ciudadano;
    }

    @Override
    public List<ciudadano> obtenerTodos() {
        String sql = "SELECT * FROM ciudadano";
        List<ciudadano> listaCiuadadanos = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ciudadano ciudadano = new ciudadano();
                ciudadano.setId_ciudadano(rs.getInt("id_ciudadano"));
                ciudadano.setNombre(rs.getString("nombre"));
                ciudadano.setApellido_paterno(rs.getString("apellido_paterno"));
                ciudadano.setApellido_materno(rs.getString("apellido_materno"));
                ciudadano.setTelefono(rs.getString("telefono"));
                ciudadano.setCorreo(rs.getString("correo"));
                listaCiuadadanos.add(ciudadano);

            }
        } catch (SQLException e) {
            System.err.println("error al obetener la lista de ciudadanos: " + e.getMessage());
        }
        return listaCiuadadanos;
    }

    @Override
    public boolean actualizarCiudadano(ciudadano ciudadano) {
        String sql = "UPDATE ciudadano SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, telefono = ?, correo = ? WHERE id_ciudadano = ?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ciudadano.getNombre());
            ps.setString(2, ciudadano.getApellido_paterno());
            ps.setString(3, ciudadano.getApellido_materno());
            ps.setString(4, ciudadano.getTelefono());
            ps.setString(5, ciudadano.getCorreo());
            ps.setInt(6, ciudadano.getId_ciudadano());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("errpr al actualizar ciudadano: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarCiudadano(int id_ciudadano) {
        String sql = "DELETE FROM ciudadano WHERE id_ciudadano = ?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_ciudadano);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("error al eliminar el ciudadano: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<ciudadano> obtenerTodosPorFiltro(String filtro) {
        String sql = "SELECT * FROM ciudadano WHERE nombre LIKE ? OR apellido_paterno LIKE ? OR apellido_materno LIKE ? LIMIT 100";
        List<ciudadano> lista = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + filtro + "%");
            ps.setString(2, "%" + filtro + "%");
            ps.setString(3, "%" + filtro + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ciudadano au = new ciudadano();
                au.setId_ciudadano(rs.getInt("id_ciudadano"));
                au.setNombre(rs.getString("nombre"));
                au.setApellido_paterno(rs.getString("apellido_paterno"));
                au.setApellido_materno(rs.getString("apellido_materno"));
                au.setTelefono(rs.getString("telefono"));
                au.setCorreo(rs.getString("correo"));
                lista.add(au);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener autoridades por filtro: " + e.getMessage());
        }
        return lista;
    }
}
