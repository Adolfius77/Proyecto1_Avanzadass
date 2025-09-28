/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.ConexionDB;
import Interfacez.IAtencionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.atencion;

/**
 *
 * @author luisb
 */
public class atencionDAO implements IAtencionDAO {

    @Override
    public boolean insertarAtencion(atencion atencion) {
        String sql = "INSERT INTO atencion (id_autoridad, fecha_inicio, fecha_solucion) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, atencion.getId_autoridad());
            ps.setTimestamp(2, atencion.getFecha_inicio());
            ps.setTimestamp(3, atencion.getFecha_solucion());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar una atención: " + e.getMessage());
            return false;
        }
    }

    @Override
    public atencion obtenerPorId(int id_atencion) {
        String sql = "SELECT * FROM atencion WHERE id_atencion = ?";
        atencion atencion = null;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_atencion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                atencion = new atencion();
                atencion.setId_atencion(rs.getInt("id_atencion"));
                atencion.setId_autoridad(rs.getInt("id_autoridad"));
                atencion.setFecha_inicio(rs.getTimestamp("fecha_inicio"));
                atencion.setFecha_solucion(rs.getTimestamp("fecha_solucion"));

            }
        } catch (SQLException e) {
            System.err.println("error al obetener la atencion por id: " + e.getMessage());
        }
        return atencion;
    }

    @Override
    public List<atencion> obtenerTodos() {
        String sql = "SELECT a.id_atencion, a.id_autoridad, a.fecha_inicio, a.fecha_solucion, a.estatus_final, au.nombre AS nombre_autoridad "
                + "FROM atencion a "
                + "JOIN autoridad au ON a.id_autoridad = au.id_autoridad";
        List<atencion> listaAtenciones = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                atencion atencion = new atencion();
                atencion.setId_atencion(rs.getInt("id_atencion"));
                atencion.setId_autoridad(rs.getInt("id_autoridad"));
                atencion.setFecha_inicio(rs.getTimestamp("fecha_inicio"));
                atencion.setFecha_solucion(rs.getTimestamp("fecha_solucion"));
                atencion.setNombre_autoridad(rs.getString("nombre_autoridad")); 

                listaAtenciones.add(atencion);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de atenciones: " + e.getMessage());
        }
        return listaAtenciones;
    }

    @Override
    public boolean actualizarAtencion(atencion atencion) {
        String sql = "UPDATE atencion SET id_autoridad = ?, fecha_inicio = ?, fecha_solucion = ? WHERE id_atencion = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, atencion.getId_autoridad());
            ps.setTimestamp(2, atencion.getFecha_inicio());
            ps.setTimestamp(3, atencion.getFecha_solucion());
            ps.setInt(4, atencion.getId_atencion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar la atención: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarAtencion(int id_atencion) {
        String sql = "DELETE FROM atencion WHERE id_atencion = ?";

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_atencion);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar la atención: " + e.getMessage());
            return false;
        }
    }
    @Override
    public List<atencion> obtenerTodosPorFiltro(String filtro) {
        String sql = "SELECT a.id_atencion, a.id_autoridad, a.fecha_inicio, a.fecha_solucion, au.nombre AS nombre_autoridad "
                + "FROM atencion a "
                + "JOIN autoridad au ON a.id_autoridad = au.id_autoridad "
                + "WHERE au.nombre LIKE ?";
        List<atencion> listaAtenciones = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + filtro + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                atencion atencion = new atencion();
                atencion.setId_atencion(rs.getInt("id_atencion"));
                atencion.setId_autoridad(rs.getInt("id_autoridad"));
                atencion.setFecha_inicio(rs.getTimestamp("fecha_inicio"));
                atencion.setFecha_solucion(rs.getTimestamp("fecha_solucion"));
                atencion.setNombre_autoridad(rs.getString("nombre_autoridad"));

                listaAtenciones.add(atencion);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de atenciones por filtro: " + e.getMessage());
        }
        return listaAtenciones;
    }
}
