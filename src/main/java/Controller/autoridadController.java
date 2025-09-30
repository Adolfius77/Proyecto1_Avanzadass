package Controller;

import Interfaces.IAutoridadDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.autoridad;
import model.ciudadano;

public class autoridadController {

    private IAutoridadDAO autoridadDAO;

    public autoridadController(IAutoridadDAO autoridadDAO) {
        this.autoridadDAO = autoridadDAO;
    }

    public boolean agregarAutoridad(String nombre, String dependencia, String telefono, String correo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("el nombre no puede estar vacio");
            return false;
        }
        if (dependencia == null || dependencia.trim().isEmpty()) {
            System.out.println("la dependencia no puede estar vacia");
            return false;
        }
        String telefonoNumerico = "";
        if (telefono != null && !telefono.trim().isEmpty()) {
            telefonoNumerico = telefono.replaceAll("[^\\d]", "");
        }

        if (telefonoNumerico.length() != 10) {
            System.err.println("El telefono debe contener exactamente 10 numeros.");
            return false;
        }
        if (correo == null || correo.trim().isEmpty()) {
            System.out.println("el corrreo no puede estar vacio");
            return false;
        }

        autoridad autoridad = new autoridad();
        autoridad.setNombre(nombre.trim());
        autoridad.setDependencia(dependencia.trim());
        autoridad.setTelefono(telefono.trim());
        autoridad.setCorreo(correo.trim());

        return autoridadDAO.insertarAutoridad(autoridad);
    }

    public autoridad obtenerAutoridad(int id_autoridad) {
        if (id_autoridad <= 0) {
            System.err.println("id de la autoridad no puede estar vacia");
            return null;
        }
        return autoridadDAO.obtenerPorId(id_autoridad);
    }

    public List<autoridad> listarAutoridades() {
        return autoridadDAO.obtenerTodos();
    }

    public boolean actualizarAutoridad(int id_autoridad, String nombre, String dependencia, String telefono, String correo) {
        if (id_autoridad <= 0) {
            System.err.println("id de la autoridad no puede estar vacio");
            return false;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("el nombre no puede estar vacio");
            return false;
        }
        if (dependencia == null || dependencia.trim().isEmpty()) {
            System.err.println("la dependencia no puede estar vacia");
            return false;
        }
        String telefonoNumerico = "";
        if (telefono != null && !telefono.trim().isEmpty()) {
            telefonoNumerico = telefono.replaceAll("[^\\d]", "");
        }

        if (telefonoNumerico.length() != 10) {
            System.err.println("El telefono debe contener exactamente 10 numeros.");
            return false;
        }
        if (correo == null || correo.trim().isEmpty()) {
            System.err.println("el correo no puede estar vacio");
            return false;
        }

        autoridad autoridad = new autoridad();
        autoridad.setId_autoridad(id_autoridad);
        autoridad.setNombre(nombre.trim());
        autoridad.setDependencia(dependencia.trim());
        autoridad.setTelefono(telefono.trim());
        autoridad.setCorreo(correo.trim());

        return autoridadDAO.actualizarAutoridad(autoridad);
    }

    public boolean eliminarAutoridad(int id_autoridad) {
        if (id_autoridad <= 0) {
            System.err.println("la id de la autoridad no puede estar vacia");
            return false;
        }
        return autoridadDAO.eliminarAutoridad(id_autoridad);
    }

    public DefaultTableModel obtenerTablaProblemas() {
        String[] columnas = {"ID", "Nombre", "Dependencia", "Telefono", "Correo"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<autoridad> lista = autoridadDAO.obtenerTodos();
        for (autoridad a : lista) {
            modelo.addRow(new Object[]{a.getId_autoridad(), a.getNombre(), a.getDependencia(), a.getTelefono(), a.getCorreo()});
        }
        return modelo;
    }

    public DefaultTableModel obtenerTablaClientesPorFiltroModal(String filtro) {
        String[] columnas = {"ID", "Nombre", "Dependencia", "Telefono", "Correo"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<autoridad> lista = autoridadDAO.obtenerTodosPorFiltro(filtro);
        for (autoridad c : lista) {
            modelo.addRow(new Object[]{c.getId_autoridad(), c.getNombre(), c.getDependencia(), c.getTelefono(), c.getCorreo()});
        }
        return modelo;
    }
}
