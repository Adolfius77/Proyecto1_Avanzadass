// adolfius77/proyecto1_avanzadass/Proyecto1_Avanzadass-efa5249733c0a77b27bf3c20dc9431858aa99eb3/src/main/java/Controller/atencionController.java
package Controller;

import Interfacez.IAtencionDAO;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.atencion;

public class atencionController {

    private IAtencionDAO atencionDAO;

    public atencionController(IAtencionDAO atencionDAO) {
        this.atencionDAO = atencionDAO;
    }

    public boolean agregarAtencion(int id_autoridad, Timestamp fecha_inicio, Timestamp fecha_solucion) {
        atencion atencion = new atencion();
        atencion.setId_autoridad(id_autoridad);
        atencion.setFecha_inicio(fecha_inicio);
        atencion.setFecha_solucion(fecha_solucion);

        return atencionDAO.insertarAtencion(atencion);
    }

    public boolean actualizarAtencion(int id_atencion, int id_autoridad, Timestamp fecha_inicio, Timestamp fecha_solucion) {
        atencion atencion = new atencion();
        atencion.setId_atencion(id_atencion);
        atencion.setId_autoridad(id_autoridad);
        atencion.setFecha_inicio(fecha_inicio);
        atencion.setFecha_solucion(fecha_solucion);

        return atencionDAO.actualizarAtencion(atencion);
    }

    public boolean eliminarAtencion(int id_atencion) {
        return atencionDAO.eliminarAtencion(id_atencion);
    }

    public DefaultTableModel obtenerTablaAtenciones() {
        String[] columnas = {"ID", "Autoridad", "Fecha Inicio", "Fecha Solucion"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<atencion> lista = atencionDAO.obtenerTodos();
        for (atencion a : lista) {
            modelo.addRow(new Object[]{
                a.getId_atencion(),
                a.getNombre_autoridad(),
                a.getFecha_inicio(),
                a.getFecha_solucion(),});
        }
        return modelo;
    }
}
