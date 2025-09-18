/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfacez;

import java.util.List;
import model.autoridad;

/**
 *
 * @author USER
 */
public interface IAutoridadDAO {

    boolean insertarAutoridad(autoridad autoridad);

    autoridad obtenerPorId(int id_autoridad);

    List<autoridad> obtenerTodos();

    boolean actualizarAutoridad(autoridad autoridad);

    boolean eliminarAutoridad(int id_autoridad);

}
