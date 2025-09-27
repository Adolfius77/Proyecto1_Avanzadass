/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.ConexionDB;
import Interfacez.IBacheDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bache;

/**
 *
 * @author USER
 */
public class bacheDAO implements IBacheDAO {

    @Override
    public bache obtenerPorId(int id_bache) {
        String sql = "SELECT * FROM WHERE id_bache = ? ";
        bache bache = null;

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_bache);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bache = new bache();
                bache.setId_ciudadano(rs.getInt("id_ciudadano"));
                bache.setFecha_reporte(rs.getDate("fecha_reporte"));
                bache.setTamano_aproximado(rs.getInt("tamaño_aprox"));
                bache.setSeveridad(rs.getString("severidad"));
                bache.setEstado_actual(rs.getString("estado_actual"));
                bache.setCalle(rs.getString("calle"));
                bache.setColonia(rs.getString("colonia"));
                bache.setCodigo_postal(rs.getString("codigo_postal"));
                bache.setLongitud(rs.getDouble("longitud"));
                bache.setLatitud(rs.getDouble("latitud"));
            }
        } catch (SQLException e) {
            System.err.println("error al obtener el bache por id: " + e.getMessage());
        }
        return bache;
    }

    @Override
    public List<bache> obtenerTodos() {
        String sql = "SELECT * FROM bache";
        List<bache> listaBaches = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bache bache = new bache();
                bache.setId_ciudadano(rs.getInt("id_ciudadano"));
                bache.setFecha_reporte(rs.getDate("fecha_reporte"));
                bache.setTamano_aproximado(rs.getInt("tamaño_aprox"));
                bache.setSeveridad(rs.getString("severidad"));
                bache.setEstado_actual(rs.getString("estado_actual"));
                bache.setCalle(rs.getString("calle"));
                bache.setColonia(rs.getString("colonia"));
                bache.setCodigo_postal(rs.getString("codigo_postal"));
                bache.setLatitud(rs.getDouble("longitud"));
                bache.setLongitud(rs.getDouble("latitud"));
                listaBaches.add(bache);
            }
        } catch (SQLException e) {
            System.err.println("error al obtener todos los baches: " + e.getMessage());
        }
        return listaBaches;
    }

    @Override
    public boolean insertarBache(bache bache) {
        String sql = "INSERT INTO bache(id_ciudadano,fecha_reporte,tamaño_aprox,severidad,estado_actual,calle,colonia,codigo_postal,latitud,longitud) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bache.getId_ciudadano());
            ps.setDate(2, bache.getFecha_reporte());
            ps.setInt(3, bache.getTamano_aproximado());
            ps.setString(4, bache.getSeveridad());
            ps.setString(5, bache.getEstado_actual());
            ps.setString(6, bache.getCalle());
            ps.setString(7, bache.getColonia());
            ps.setString(8, bache.getCodigo_postal());
            ps.setDouble(9, bache.getLatitud());
            ps.setDouble(10, bache.getLongitud());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("error al insertar bache: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarBache(bache bache) {
        String sql = "UPDATE bache SET fecha_reporte = ?,tamaño_aprox = ?,severidad = ?,estado_actual = ?, = ?,colonia = ?,codigo_postal = ?,latitud= ?,longitud = ? WHERE id_bache = ? ";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, bache.getFecha_reporte());
            ps.setInt(2, bache.getTamano_aproximado());
            ps.setString(3, bache.getSeveridad());
            ps.setString(4, bache.getEstado_actual());
            ps.setString(5, bache.getCalle());
            ps.setString(6, bache.getColonia());
            ps.setString(7, bache.getCodigo_postal());
            ps.setDouble(8, bache.getLatitud());
            ps.setDouble(9, bache.getLongitud());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("error al actualizar el bache: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarBache(int id_bache) {
        String sql = "DELETE FROM bache WHERE id_bache = ?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_bache);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("error al eliminar bache: " + e.getMessage());
            return false;
        }
    }

}
