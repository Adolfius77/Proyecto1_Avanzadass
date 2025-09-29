/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;
import model.autoridad;
import model.ciudadano;

/**
 *
 * @author USER
 */
public interface ICiudadanoDAO {

    boolean insertarCiudadano(ciudadano ciudadano);

    ciudadano obetenerPorId(int id_Ciudadano);

    List<ciudadano> obtenerTodos();

    boolean actualizarCiudadano(ciudadano ciudadano);

    boolean eliminarCiudadano(int id_ciudadano);

    List<ciudadano> obtenerTodosPorFiltro(String filtro);

}
