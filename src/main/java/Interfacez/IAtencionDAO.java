/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfacez;

import java.util.List;
import model.atencion;

/**
 *
 * @author USER
 */
public interface IAtencionDAO {

    boolean insertarAtencion(atencion atencion);

    atencion obtenerPorId(int id_atencion);

    List<atencion> obtenerTodos();

    boolean actualizarAtencion(atencion atencion);

    boolean eliminarAtencion(int id_atencion);

    List<atencion> obtenerTodosPorFiltro(String filtro);

}
