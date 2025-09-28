/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author USER
 */
public class intervencion {

    private int id_bache;
    private int id_atencion;
    
    private String ubicacionBache;
    private String nombreAutoridad;
    private Timestamp fechaSolucion;

    public intervencion() {
    }

    public intervencion(int id_bache, int id_atencion) {
        this.id_bache = id_bache;
        this.id_atencion = id_atencion;
        
    }
    
    public int getId_bache() {
        return id_bache;
    }

    public void setId_bache(int id_bache) {
        this.id_bache = id_bache;
    }

    public int getId_atencion() {
        return id_atencion;
    }

    public void setId_atencion(int id_atencion) {
        this.id_atencion = id_atencion;
    }

    public String getUbicacionBache() {
        return ubicacionBache;
    }

    public void setUbicacionBache(String ubicacionBache) {
        this.ubicacionBache = ubicacionBache;
    }

    public String getNombreAutoridad() {
        return nombreAutoridad;
    }

    public void setNombreAutoridad(String nombreAutoridad) {
        this.nombreAutoridad = nombreAutoridad;
    }

    public Timestamp getFechaSolucion() {
        return fechaSolucion;
    }

    public void setFechaSolucion(Timestamp fechaSolucion) {
        this.fechaSolucion = fechaSolucion;
    }

    @Override
    public String toString() {
        return "intervencion{" + "id_bache=" + id_bache + ", id_atencion=" + id_atencion + '}';
    }

}
