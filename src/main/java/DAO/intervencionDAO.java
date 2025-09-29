/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.ConexionDB;
import Interfaces.IIntervencionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.intervencion;

public class intervencionDAO implements IIntervencionDAO {

    @Override
    public boolean insertarIntervencion(intervencion intervencion) {
        String sql = "INSERT INTO intervencion (id_bache, id_atencion) VALUES (?, ?)";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, intervencion.getId_bache());
            ps.setInt(2, intervencion.getId_atencion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar intervencion: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarIntervencion(int id_bache, int id_atencion) {
        String sql = "DELETE FROM intervencion WHERE id_bache = ? AND id_atencion = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_bache);
            ps.setInt(2, id_atencion);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar intervención: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<intervencion> obtenerPorIdBache(int id_bache) {
        String sql = "SELECT * FROM intervencion WHERE id_bache = ?";
        List<intervencion> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_bache);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                intervencion intervencion = new intervencion();
                intervencion.setId_bache(rs.getInt("id_bache"));
                intervencion.setId_atencion(rs.getInt("id_atencion"));

                lista.add(intervencion);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener intervenciones por bache: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<intervencion> obtenerPorIdAtencion(int id_atencion) {
        String sql = "SELECT * FROM intervencion WHERE id_atencion = ?";
        List<intervencion> lista = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_atencion);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                intervencion intervencion = new intervencion();
                intervencion.setId_bache(rs.getInt("id_bache"));
                intervencion.setId_atencion(rs.getInt("id_atencion"));

                lista.add(intervencion);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener intervenciones por atención: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<intervencion> obtenerTodas() {
        return obtenerTodasConFiltro("");
    }

    @Override
    public List<intervencion> obtenerTodasConFiltro(String filtro) {
        List<intervencion> lista = new ArrayList<>();
        String sql = "SELECT i.id_bache, i.id_atencion, "
                + "CONCAT(b.calle, ', ', b.colonia) AS ubicacion, "
                + "au.nombre AS autoridad, a.fecha_solucion "
                + "FROM intervencion i "
                + "JOIN bache b ON i.id_bache = b.id_bache "
                + "JOIN atencion a ON i.id_atencion = a.id_atencion "
                + "JOIN autoridad au ON a.id_autoridad = au.id_autoridad "
                + "WHERE b.calle LIKE ? OR au.nombre LIKE ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            String filtroLike = "%" + filtro + "%";
            ps.setString(1, filtroLike);
            ps.setString(2, filtroLike);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                intervencion i = new intervencion();
                i.setId_bache(rs.getInt("id_bache"));
                i.setId_atencion(rs.getInt("id_atencion"));
                i.setUbicacionBache(rs.getString("ubicacion"));
                i.setNombreAutoridad(rs.getString("autoridad"));
                i.setFechaSolucion(rs.getTimestamp("fecha_solucion"));
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener intervenciones: " + e.getMessage());
        }
        return lista;
    }
}
