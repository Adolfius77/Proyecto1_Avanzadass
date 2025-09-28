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
        // Instancias de los DAOs
        ciudadanoDAO ciudadanoDAO = new ciudadanoDAO();
        bacheDAO bacheDAO = new bacheDAO();
        autoridadDAO autoridadDAO = new autoridadDAO();
        atencionDAO atencionDAO = new atencionDAO();
        intervencionDAO intervencionDAO = new intervencionDAO();

        // Instancias de los Controllers
        ciudadanoController ciudadanoCtrl = new ciudadanoController(ciudadanoDAO);
        bacheController bacheCtrl = new bacheController(bacheDAO);
        autoridadController autoridadCtrl = new autoridadController(autoridadDAO);
        atencionController atencionCtrl = new atencionController(atencionDAO);
        //intervencionController intervencionCtrl = new intervencionController(intervencionDAO);

        // --- Pruebas para Ciudadano ---
        System.out.println("--- Pruebas Ciudadano ---");
        ciudadanoCtrl.agregarCiudadano("Juan", "Perez", "Gomez", "6441234567", "juan.perez@example.com");
        System.out.println("Listado de ciudadanos:");
        ciudadanoCtrl.listarCiudadanos().forEach(System.out::println);

        // --- Pruebas para Bache ---
        System.out.println("\n--- Pruebas Bache ---");
        long milisegundos = System.currentTimeMillis();
        Date fechaActual = new Date(milisegundos);
        //bacheCtrl.agregarBache(fechaActual, 5, "Alta", "Reportado", "Guerrero", "Centro", "85000", 27.48, -109.93);
        System.out.println("Listado de baches:");
        bacheCtrl.listarBaches().forEach(System.out::println);

        // --- Pruebas para Autoridad ---
        System.out.println("\n--- Pruebas Autoridad ---");
        autoridadCtrl.agregarAutoridad("Obras Publicas", "Ayuntamiento", "6444105000", "obras.publicas@cajeme.gob.mx");
        System.out.println("Listado de autoridades:");
        autoridadCtrl.listarAutoridades().forEach(System.out::println);

        // --- Pruebas para Atencion ---
        System.out.println("\n--- Pruebas Atencion ---");
        Timestamp fechaInicio = Timestamp.valueOf(LocalDateTime.now());
        //atencionCtrl.agregarAtencion(1, fechaInicio, );
        System.out.println("Listado de atenciones:");
        //atencionCtrl.listarAtencion().forEach(System.out::println);

        // --- Pruebas para Intervencion ---
        System.out.println("\n--- Pruebas Intervencion ---");
        //intervencionCtrl.agregarIntervencion(1, 1);
        System.out.println("Intervenciones por bache 1:");
        //intervencionCtrl.obtenerIntervencionesPorBache(1).forEach(System.out::println);
    }
}