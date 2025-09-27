package Controller;

import Interfacez.IAutoridadDAO;
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
        if (telefono == null) {
            telefono = "";
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
        if (telefono == null) {
            telefono = "";
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
        String[] columnas = {"ID", "Nombre","Dependencia", "Correo", "Telefono"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<autoridad> lista = autoridadDAO.obtenerTodos();
        for (autoridad a : lista) {
            modelo.addRow(new Object[]{a.getId_autoridad(), a.getNombre(),a.getDependencia(),a.getCorreo(), a.getTelefono()});
        }
        return modelo;
    }
}