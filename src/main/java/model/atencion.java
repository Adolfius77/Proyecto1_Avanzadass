/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author USER
 */
public class atencion {

    private int id_atencion;
    private int id_autoridad;
    private Timestamp fecha_inicio;
    private Timestamp fecha_solucion;
    private String estatus_final;

    public atencion() {
    }

    public atencion(int id_atencion, int id_autoridad, Timestamp fecha_inicio, Timestamp fecha_solucion, String estatus_final) {
        this.id_atencion = id_atencion;
        this.id_autoridad = id_autoridad;
        this.fecha_inicio = fecha_inicio;
        this.fecha_solucion = fecha_solucion;
        this.estatus_final = estatus_final;
    }
    
    public int getId_atencion() {
        return id_atencion;
    }

    public void setId_atencion(int id_atencion) {
        this.id_atencion = id_atencion;
    }

    public int getId_autoridad() {
        return id_autoridad;
    }

    public void setId_autoridad(int id_autoridad) {
        this.id_autoridad = id_autoridad;
    }

    public Timestamp getFecha_inicio() {
        return fecha_inicio;
    }

    //Esto antes estaba solo como date
    public void setFecha_inicio(Timestamp fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Timestamp getFecha_solucion() {
        return fecha_solucion;
    }

    public void setFecha_solucion(Timestamp fecha_solucion) {
        this.fecha_solucion = fecha_solucion;
    }

    public String getEstatus_final() {
        return estatus_final;
    }

    public void setEstatus_final(String estatus_final) {
        this.estatus_final = estatus_final;
    }

    @Override
    public String toString() {
        return "atencion{" + "id_atencion=" + id_atencion + ", id_autoridad=" + id_autoridad + ", fecha_inicio=" + fecha_inicio + ", fecha_solucion=" + fecha_solucion + ", estatus_final=" + estatus_final + '}';
    }

}
