package Controller;

import Interfacez.IIntervencionDAO;
import java.util.List;
import model.intervencion;

public class intervencionController {

    private IIntervencionDAO intervencionDAO;

    public intervencionController(IIntervencionDAO intervencionDAO) {
        this.intervencionDAO = intervencionDAO;
    }

    public boolean agregarIntervencion(int id_bache, int id_atencion) {
        if (id_bache <= 0) {
            System.err.println("ID de bache invalido.");
            return false;
        }
        if (id_atencion <= 0) {
            System.err.println("ID de atencion invalido.");
            return false;
        }

        intervencion intervencion = new intervencion();
        intervencion.setId_bache(id_bache);
        intervencion.setId_atencion(id_atencion);

        return intervencionDAO.insertarIntervencion(intervencion);
    }

    public List<intervencion> obtenerIntervencionesPorBache(int id_bache) {
        if (id_bache <= 0) {
            System.err.println("ID de bache invalido.");
            return null;
        }
        return intervencionDAO.obtenerPorIdBache(id_bache);
    }

    public List<intervencion> obtenerIntervencionesPorAtencion(int id_atencion) {
        if (id_atencion <= 0) {
            System.err.println("ID de atencion invalido.");
            return null;
        }
        return intervencionDAO.obtenerPorIdAtencion(id_atencion);
    }

    public boolean eliminarIntervencion(int id_bache, int id_atencion) {
        if (id_bache <= 0) {
            System.err.println("ID de bache invalido.");
            return false;
        }
        if (id_atencion <= 0) {
            System.err.println("ID de atencion invalido.");
            return false;
        }
        return intervencionDAO.eliminarIntervencion(id_bache, id_atencion);
    }
}