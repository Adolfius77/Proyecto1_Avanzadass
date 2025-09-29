package Controller;

import Interfaces.IAtencionDAO;
import Interfaces.IBacheDAO;
import Interfaces.IIntervencionDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.atencion;
import model.bache;
import model.intervencion;

public class intervencionController {

    private final IIntervencionDAO intervencionDAO;
    private final IBacheDAO bacheDAO;
    private final IAtencionDAO atencionDAO;

    public intervencionController(IIntervencionDAO intervencionDAO, IBacheDAO bacheDAO, IAtencionDAO atencionDAO) {
        this.intervencionDAO = intervencionDAO;
        this.bacheDAO = bacheDAO;
        this.atencionDAO = atencionDAO;
    }

    public boolean asignarIntervencion(int id_bache, int id_atencion) {
        if (id_bache <= 0 || id_atencion <= 0) {
            System.err.println("Error: El ID del bache y de la atención deben ser válidos.");
            return false;
        }

        bache b = bacheDAO.obtenerPorId(id_bache);
        if (b != null) {
            b.setEstado_actual("En proceso");
            bacheDAO.actualizarBache(b);
        }
        intervencion nuevaIntervencion = new intervencion(id_bache, id_atencion);
        return intervencionDAO.insertarIntervencion(nuevaIntervencion);
    }

    public boolean eliminarIntervencion(int id_bache, int id_atencion) {
      
        if (id_bache <= 0 || id_atencion <= 0) {
            System.err.println("Error: El ID del bache y de la atenciOn deben ser vAlidos.");
            return false;
        }

        bache b = bacheDAO.obtenerPorId(id_bache);
        if (b != null) {
            b.setEstado_actual("Reportado");
            bacheDAO.actualizarBache(b);
        }
        return intervencionDAO.eliminarIntervencion(id_bache, id_atencion);
    }

    public boolean completarIntervencion(int bacheId, int atencionId) {
   
        if (bacheId <= 0 || atencionId <= 0) {
            System.err.println("Error: El ID del bache y de la atención deben ser validos.");
            return false;
        }

        bache b = bacheDAO.obtenerPorId(bacheId);
        atencion a = atencionDAO.obtenerPorId(atencionId);

        if (b != null && a != null) {
            b.setEstado_actual("Reparado");
            a.setFecha_solucion(new java.sql.Timestamp(System.currentTimeMillis()));
            
            bacheDAO.actualizarBache(b);
            atencionDAO.actualizarAtencion(a);
            return true;
        }
        return false;
    }
    
    public DefaultTableModel getBachesSinAtenderModel() {
        String[] columnas = {"ID Bache", "Calle", "Colonia", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas){
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        List<bache> baches = bacheDAO.obtenerBachesSinAtender();
        for (bache b : baches) {
            modelo.addRow(new Object[]{b.getId_bache(), b.getCalle(), b.getColonia(), b.getEstado_actual()});
        }
        return modelo;
    }

    public DefaultTableModel getAtencionesDisponiblesModel() {
        String[] columnas = {"ID Atención", "Autoridad", "Fecha Inicio"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas){
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        List<atencion> atenciones = atencionDAO.obtenerAtencionesDisponibles();
        for (atencion a : atenciones) {
            modelo.addRow(new Object[]{a.getId_atencion(), a.getNombre_autoridad(), a.getFecha_inicio()});
        }
        return modelo;
    }

    public DefaultTableModel getIntervencionesModel(String... filtro) {
        String[] columnas = {"ID Bache", "Ubicación", "ID AtenciOn", "Autoridad", "Fecha Solución"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas){
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        
        List<intervencion> intervenciones;
        if (filtro.length > 0 && !filtro[0].isEmpty()) {
            intervenciones = intervencionDAO.obtenerTodasConFiltro(filtro[0]);
        } else {
            intervenciones = intervencionDAO.obtenerTodas();
        }

        for (intervencion i : intervenciones) {
            modelo.addRow(new Object[]{
                i.getId_bache(),
                i.getUbicacionBache(),
                i.getId_atencion(),
                i.getNombreAutoridad(),
                i.getFechaSolucion()
            });
        }
        return modelo;
    }
}