package Controller;

import Interfacez.IAtencionDAO;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.atencion;
import model.autoridad;

public class atencionController {

    private IAtencionDAO atencionDAO;

    public atencionController(IAtencionDAO atencionDAO) {
        this.atencionDAO = atencionDAO;
    }

    public boolean agregarAtencion(int id_autoridad, Timestamp fecha_inicio, Timestamp fecha_solucion) {
        if (id_autoridad <= 0) {
            System.err.println("El ID de la autoridad no puede ser cero o negativo.");
            return false;
        }
        if (fecha_inicio == null) {
            System.err.println("La fecha de inicio no puede ser nula.");
            return false;
        }

        atencion atencion = new atencion();
        atencion.setId_autoridad(id_autoridad);
        atencion.setFecha_inicio(fecha_inicio);
        atencion.setFecha_solucion(fecha_solucion);

        return atencionDAO.insertarAtencion(atencion);
    }

    public atencion obtenerAtencion(int id_atencion) {
        if (id_atencion <= 0) {
            System.err.println("ID de atencion invalido.");
            return null;
        }
        return atencionDAO.obtenerPorId(id_atencion);
    }

    public List<atencion> listarAtenciones() {
        return atencionDAO.obtenerTodos();
    }

    public boolean actualizarAtencion(int id_atencion,int id_autoridad, Timestamp fecha_inicio, Timestamp fecha_solucion) {
        if (id_autoridad <= 0) {
            System.err.println("El ID de la autoridad no puede ser cero o negativo.");
            return false;
        }
        if (fecha_inicio == null) {
            System.err.println("La fecha de inicio no puede ser nula.");
            return false;
        }
       
        atencion atencion = new atencion();
        atencion.setId_atencion(id_atencion);
        atencion.setId_autoridad(id_autoridad);
        atencion.setFecha_inicio(fecha_inicio);
        atencion.setFecha_solucion(fecha_solucion);

        return atencionDAO.actualizarAtencion(atencion);
    }

    public boolean eliminarAtencion(int id_atencion) {
        if (id_atencion <= 0) {
            System.err.println("El ID de la atencion no puede ser vacio.");
            return false;
        }
        return atencionDAO.eliminarAtencion(id_atencion);
    }
    
    public DefaultTableModel obtenerTablaAtencion() {
        String[] columnas = {"ID", "Autoridad","Fecha Inicio", "Fecha Solución", "Telefono"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<atencion> lista = atencionDAO.obtenerTodos();
        for (atencion a : lista) {
            modelo.addRow(new Object[]{a.getId_atencion(), a.getId_autoridad(),a.getFecha_inicio(),a.getFecha_inicio()});
        }
        return modelo;
    }
}