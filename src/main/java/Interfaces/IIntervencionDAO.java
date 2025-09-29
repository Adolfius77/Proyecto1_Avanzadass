/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;
import model.intervencion;

/**
 *
 * @author USER
 */
public interface IIntervencionDAO {

    boolean insertarIntervencion(intervencion relacion);

    List<intervencion> obtenerPorIdBache(int id_bache);

    List<intervencion> obtenerPorIdAtencion(int id_atencion);

    boolean eliminarIntervencion(int id_bache, int id_atencion);

    List<intervencion> obtenerTodas();

    List<intervencion> obtenerTodasConFiltro(String filtro);

}
