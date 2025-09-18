/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Interfacez.IBacheDAO;
import java.sql.Date;
import java.util.List;
import model.bache;

/**
 *
 * @author USER
 */
public class bacheController {
   
   private IBacheDAO bacheDAO;

    public bacheController(IBacheDAO bacheDAO) {
        this.bacheDAO = bacheDAO;
    }
   
    public boolean agregarBache(Date fecha_reporte,int tamaño_aprox,String severidad,String estado_actual,String calle,String colonia, String codigo_postal,double latitud, double longitud){
        if(fecha_reporte == null){
            System.err.println("la fecha no puede ser nula");
            return false;
        }
        if(tamaño_aprox <= 0){
            System.err.println("el tamano no puede ser negativo");
            return false;
        }
        if(severidad == null || severidad.trim().isEmpty()){
            System.err.println("la severidad no puede estar vacia");
            return false;
        }
        if(estado_actual == null || estado_actual.trim().isEmpty()){
            System.err.println("el estado no puede estar vacio");
            return false;
        }
        if(calle == null || calle.trim().isEmpty()){
            System.err.println("la calle no puede estar vacia");
            return false;
        }
        if(colonia == null || colonia.trim().isEmpty()){
            System.err.println("la colonia no puede estar vacia");
            return false;
        }
        if(codigo_postal == null || codigo_postal.trim().isEmpty()){
            System.err.println("el codigo postal no puede estar vacio");
            return false;
        }
        if(latitud <= 0){
            System.err.println("la latitud no puede ser negativa");
            return false;
        }
        if(longitud <=0){
            System.err.println("la longitud no pue4de ser negativa");
            return false;
        }
        
        bache bache = new bache();
        bache.setFecha_reporte(fecha_reporte);
        bache.setTamano_aproximado(tamaño_aprox);
        bache.setSeveridad(severidad.trim());
        bache.setEstado_actual(estado_actual.trim());
        bache.setCalle(calle.trim());
        bache.setColonia(colonia.trim());
        bache.setCodigo_postal(codigo_postal);
        bache.setLatitud(latitud);
        bache.setLongitud(longitud);
        
        return bacheDAO.insertarBache(bache);
        
    }
    
    public bache obetenerBache(int id_bache){
        if(id_bache <= 0){
            System.err.println("id del bache invalido");
            return null;
        }
        return bacheDAO.obtenerPorId(id_bache);
    }
   
    public List<bache> listarBaches(){
        return bacheDAO.obtenerTodos();
    }
    
    public boolean actualizarBache(int id_bache,Date fecha_reporte,int tamaño_aprox,String severidad,String estado_actual,String calle,String colonia, String codigo_postal,double latitud, double longitud){
        if(id_bache <= 0){
            System.out.println("el id del bache no puede ser nulo");
            return false;
        }
        if(fecha_reporte == null){
            System.err.println("la fecha no puede ser nula");
            return false;
        }
        if(tamaño_aprox <= 0){
            System.err.println("el tamano no puede ser negativo");
            return false;
        }
        if(severidad == null || severidad.trim().isEmpty()){
            System.err.println("la severidad no puede estar vacia");
            return false;
        }
        if(estado_actual == null || estado_actual.trim().isEmpty()){
            System.err.println("el estado no puede estar vacio");
            return false;
        }
        if(calle == null || calle.trim().isEmpty()){
            System.err.println("la calle no puede estar vacia");
            return false;
        }
        if(colonia == null || colonia.trim().isEmpty()){
            System.err.println("la colonia no puede estar vacia");
            return false;
        }
        if(codigo_postal == null || codigo_postal.trim().isEmpty()){
            System.err.println("el codigo postal no puede estar vacio");
            return false;
        }
        if(latitud <= 0){
            System.err.println("la latitud no puede ser negativa");
            return false;
        }
        if(longitud <=0){
            System.err.println("la longitud no pue4de ser negativa");
            return false;
        }
        bache bache = new bache();
        bache.setFecha_reporte(fecha_reporte);
        bache.setTamano_aproximado(tamaño_aprox);
        bache.setSeveridad(severidad.trim());
        bache.setEstado_actual(estado_actual.trim());
        bache.setCalle(calle.trim());
        bache.setColonia(colonia.trim());
        bache.setCodigo_postal(codigo_postal);
        bache.setLatitud(latitud);
        bache.setLongitud(longitud);
        
        return bacheDAO.actualizarBache(bache);
    }
    
    public boolean eliminarBache(int id_bache){
        if(id_bache <= 0){
            System.err.println("la id del bache no puede ser vacio");
            return false;
        }
        return bacheDAO.eliminarBache(id_bache);
    }
}
