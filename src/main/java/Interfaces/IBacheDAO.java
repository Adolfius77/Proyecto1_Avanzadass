/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;
import model.bache;

/**
 *
 * @author USER
 */
public interface IBacheDAO {

    boolean insertarBache(bache bache);

    bache obtenerPorId(int id_bache);

    List<bache> obtenerTodos();

    boolean actualizarBache(bache bache);

    boolean eliminarBache(int id_bache);
    
    List<bache> obtenerTodosPorFiltro(String filtro);
    
    List<bache> obtenerBachesSinAtender();

}
