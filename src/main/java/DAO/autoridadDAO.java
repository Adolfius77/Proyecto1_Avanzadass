/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.ConexionDB;
import Interfaces.IAutoridadDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.autoridad;

/**
 * DAO para la tabla AUTORIDAD
 */
public class autoridadDAO implements IAutoridadDAO {

    @Override
    public boolean insertarAutoridad(autoridad autoridad) {
        String sql = "INSERT INTO autoridad (nombre, dependencia, telefono, correo) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, autoridad.getNombre());
            ps.setString(2, autoridad.getDependencia());
            ps.setString(3, autoridad.getTelefono());
            ps.setString(4, autoridad.getCorreo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar una autoridad: " + e.getMessage());
            return false;
        }
    }

    @Override
    public autoridad obtenerPorId(int id_autoridad) {
        String sql = "SELECT * FROM autoridad WHERE id_autoridad = ?";
        autoridad autoridad = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_autoridad);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                autoridad = new autoridad();
                autoridad.setId_autoridad(rs.getInt("id_autoridad"));
                autoridad.setNombre(rs.getString("nombre"));
                autoridad.setDependencia(rs.getString("dependencia"));
                autoridad.setTelefono(rs.getString("telefono"));
                autoridad.setCorreo(rs.getString("correo"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la autoridad por id: " + e.getMessage());
        }

        return autoridad;
    }

    @Override
    public List<autoridad> obtenerTodos() {
        String sql = "SELECT * FROM autoridad";
        List<autoridad> listaAutoridades = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                autoridad autoridad = new autoridad();
                autoridad.setId_autoridad(rs.getInt("id_autoridad"));
                autoridad.setNombre(rs.getString("nombre"));
                autoridad.setDependencia(rs.getString("dependencia"));
                autoridad.setTelefono(rs.getString("telefono"));
                autoridad.setCorreo(rs.getString("correo"));

                listaAutoridades.add(autoridad);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de autoridades: " + e.getMessage());
        }

        return listaAutoridades;
    }

    @Override
    public boolean actualizarAutoridad(autoridad autoridad) {
        String sql = "UPDATE autoridad SET nombre = ?, dependencia = ?, telefono = ?, correo = ? WHERE id_autoridad = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, autoridad.getNombre());
            ps.setString(2, autoridad.getDependencia());
            ps.setString(3, autoridad.getTelefono());
            ps.setString(4, autoridad.getCorreo());
            ps.setInt(5, autoridad.getId_autoridad());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar la autoridad: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarAutoridad(int id_autoridad) {
        String sql = "DELETE FROM autoridad WHERE id_autoridad = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_autoridad);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar la autoridad: " + e.getMessage());
            return false;
        }
    }
    @Override
    public List<autoridad> obtenerTodosPorFiltro(String filtro){
        String sql = "SELECT * FROM autoridad WHERE nombre LIKE ? OR dependencia LIKE ? LIMIT 100";
        List<autoridad> lista = new ArrayList<>();
        try(Connection conn = ConexionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + filtro + "%");
            ps.setString(2, "%" + filtro + "%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                autoridad au = new autoridad();
                au.setId_autoridad(rs.getInt("id_autoridad"));
                au.setNombre(rs.getString("nombre"));
                au.setDependencia(rs.getString("dependencia"));
                au.setTelefono(rs.getString("telefono"));
                au.setCorreo(rs.getString("correo"));
                lista.add(au);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener autoridades por filtro: "+ e.getMessage());
        }
        return lista;
    }
}
