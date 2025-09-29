
import Controller.atencionController;
import Controller.autoridadController;
import Controller.bacheController;
import Controller.ciudadanoController;
import Controller.intervencionController;
import DAO.atencionDAO;
import DAO.autoridadDAO;
import DAO.bacheDAO;
import DAO.ciudadanoDAO;
import DAO.intervencionDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        ciudadanoDAO ciudadanoDAO = new ciudadanoDAO();
        bacheDAO bacheDAO = new bacheDAO();
        autoridadDAO autoridadDAO = new autoridadDAO();
        atencionDAO atencionDAO = new atencionDAO();
        intervencionDAO intervencionDAO = new intervencionDAO();

        ciudadanoController ciudadanoCtrl = new ciudadanoController(ciudadanoDAO);
        bacheController bacheCtrl = new bacheController(bacheDAO);
        autoridadController autoridadCtrl = new autoridadController(autoridadDAO);
        atencionController atencionCtrl = new atencionController(atencionDAO);
        intervencionController intervencionCtrl = new intervencionController(intervencionDAO, bacheDAO, atencionDAO);

        // --- Pruebas para Ciudadano ---
        System.out.println("--- Pruebas Ciudadano ---");
        if (ciudadanoCtrl.agregarCiudadano("Juan", "Perez", "Gomez", "6441234567", "juan.perez@example.com")) {
            System.out.println("Ciudadano agregado con exito.");
        } else {
            System.out.println("Fallo al agregar ciudadano.");
        }
        System.out.println("Listado de ciudadanos:");
        ciudadanoCtrl.listarCiudadanos().forEach(c -> System.out.println(c.toString()));

        // --- Pruebas para Bache ---
        System.out.println("\n--- Pruebas Bache ---");
        long milisegundos = System.currentTimeMillis();
        Date fechaActual = new Date(milisegundos);
        if (bacheCtrl.agregarBache(1, fechaActual, 5, "Alta", "Reportado", "Guerrero", "Centro", "85000", 27.48, -109.93)) {
            System.out.println("Bache agregado con exito.");
        } else {
            System.out.println("Fallo al agregar bache.");
        }
        System.out.println("Listado de baches:");
        bacheCtrl.listarBaches().forEach(b -> System.out.println(b.toString()));

        // --- Pruebas para Autoridad ---
        System.out.println("\n--- Pruebas Autoridad ---");
        if (autoridadCtrl.agregarAutoridad("Obras Publicas", "Ayuntamiento", "6444105000", "obras.publicas@cajeme.gob.mx")) {
            System.out.println("Autoridad agregada con exito.");
        } else {
            System.out.println("Fallo al agregar autoridad.");
        }
        System.out.println("Listado de autoridades:");
        autoridadCtrl.listarAutoridades().forEach(a -> System.out.println(a.toString()));

        // --- Pruebas para Atencion ---
        System.out.println("\n--- Pruebas Atencion ---");
        Timestamp fechaInicio = Timestamp.valueOf(LocalDateTime.now());
        if (atencionCtrl.agregarAtencion(1, fechaInicio, null, "Pendiente")) {
            System.out.println("Atencion agregada con exito.");
        } else {
            System.out.println("Fallo al agregar atencion.");
        }
        System.out.println("Listado de atenciones:");
        atencionDAO.obtenerTodos().forEach(at -> System.out.println(at.toString()));

        // --- Pruebas para Intervencion ---
        System.out.println("\n--- Pruebas Intervencion ---");
        if (intervencionCtrl.asignarIntervencion(1, 1)) {
            System.out.println("Intervencion asignada con exito.");
        } else {
            System.out.println("Fallo al asignar intervencion.");
        }
        System.out.println("Intervenciones por bache 1:");
        intervencionDAO.obtenerPorIdBache(1).forEach(i -> System.out.println(i.toString()));
    }
}
