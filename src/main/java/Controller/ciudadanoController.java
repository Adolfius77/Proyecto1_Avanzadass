package Controller;

import Interfacez.ICiudadanoDAO;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import model.ciudadano;

public class ciudadanoController {

    private ICiudadanoDAO ciudadanoDAO;
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    public ciudadanoController(ICiudadanoDAO ciudadanoDAO) {
        this.ciudadanoDAO = ciudadanoDAO;
    }

    private boolean esCorreoValido(String correo) {
        if (correo == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(correo).matches();
    }

    public boolean agregarCiudadano(String nombre, String apellido_Paterno, String apellido_Materno, String telefono, String correo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("el nombre no puede estar vacio");
            return false;
        }
        if (apellido_Paterno == null || apellido_Paterno.trim().isEmpty()) {
            System.err.println("el apellido paterno no puede estar vacio");
            return false;
        }
        if (apellido_Materno == null || apellido_Materno.trim().isEmpty()) {
            System.err.println("el apellido materno no puede estar vacio");
            return false;
        }
        if (correo == null || correo.trim().isEmpty()) {
            System.err.println("el corrreo no puede estar vacio");
            return false;
        }
        if (!esCorreoValido(correo.trim())) {
            System.err.println("El formato del correo electronico no es valido.");
            return false;
        }
        
        ciudadano ciudadanoNuevo = new ciudadano();
        ciudadanoNuevo.setNombre(nombre.trim());
        ciudadanoNuevo.setApellido_paterno(apellido_Paterno.trim());
        ciudadanoNuevo.setApellido_materno(apellido_Materno.trim());
        ciudadanoNuevo.setTelefono(telefono != null ? telefono.trim() : "");
        ciudadanoNuevo.setCorreo(correo.trim());

        return ciudadanoDAO.insertarCiudadano(ciudadanoNuevo);
    }
    
    public ciudadano obtenerCiudadano(int id_ciudadano){
        if(id_ciudadano <= 0 ){
            System.err.println("id del ciudadano no puede ser vacia o negativa");
            return null;
        }
        return ciudadanoDAO.obetenerPorId(id_ciudadano);
    }
    
    public List<ciudadano> listarCiudadanos(){
        return ciudadanoDAO.obtenerTodos();
    }
    
    
    public boolean actualizarCiudadano(int id_ciudadano,String nombre, String apellido_Paterno, String apellido_Materno, String telefono, String correo){
        if(id_ciudadano <= 0){
            System.err.println("id del ciudadano no puede ser vacio o negativo");
            return false;
        }
        if(nombre == null || nombre.trim().isEmpty()){
            System.err.println("el nombre no puede estar vacio");
            return false;
        }
        if(apellido_Paterno == null || apellido_Paterno.trim().isEmpty()){
            System.err.println("el apellido paterno no puede estar vacio");
            return false;
        }
        if(apellido_Materno == null || apellido_Materno.trim().isEmpty()){
            System.err.println("el apellido materno no puede estar vacio");
            return false;
        }
        if(correo == null || correo.trim().isEmpty()){
            System.err.println("el correo no puede estar vacio");
            return false;
        }
        if (!esCorreoValido(correo.trim())) {
            System.err.println("El formato del correo electronico no es valido.");
            return false;
        }
        
        ciudadano ciudadanoActualizado = new ciudadano();
        ciudadanoActualizado.setId_ciudadano(id_ciudadano);
        ciudadanoActualizado.setNombre(nombre.trim());
        ciudadanoActualizado.setApellido_paterno(apellido_Paterno.trim());
        ciudadanoActualizado.setApellido_materno(apellido_Materno.trim());
        ciudadanoActualizado.setTelefono(telefono != null ? telefono.trim() : "");
        ciudadanoActualizado.setCorreo(correo.trim());
        
        return ciudadanoDAO.actualizarCiudadano(ciudadanoActualizado);
    }

    public boolean eliminarCiudadano(int id_ciudadano){
        if(id_ciudadano <=0){
            System.err.println("la id del ciudadano no puede ser vacia o negativa");
            return false;
        }
        return  ciudadanoDAO.eliminarCiudadano(id_ciudadano);
    }
    
     public DefaultTableModel obtenerTablaProblemas() {
        String[] columnas = {"ID", "Nombre", "A. Paterno", "A. Materno", "Teléfono", "Correo"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<ciudadano> lista = ciudadanoDAO.obtenerTodos();
        for (ciudadano c : lista) {
            modelo.addRow(new Object[]{c.getId_ciudadano(),c.getNombre(), c.getApellido_paterno(), c.getApellido_materno(), c.getTelefono(), c.getCorreo()});
        }
        return modelo;
    }
}