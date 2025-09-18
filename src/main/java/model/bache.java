/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class bache {

    private int id_bache;
    private int id_ciudadano;
    private Date fecha_reporte;
    private int tamano_aproximado;
    private String severidad;
    private String estado_actual;
    private String calle;
    private String colonia;
    private String codigo_postal;
    private double latitud;
    private double longitud;

    public bache() {
    }

    public bache(int id_bache, int id_ciudadano, Date fecha_reporte, int tamano_aproximado, String severidad, String estado_actual, String calle, String colonia, String codigo_postal, double latitud, double longitud) {
        this.id_bache = id_bache;
        this.id_ciudadano = id_ciudadano;
        this.fecha_reporte = fecha_reporte;
        this.tamano_aproximado = tamano_aproximado;
        this.severidad = severidad;
        this.estado_actual = estado_actual;
        this.calle = calle;
        this.colonia = colonia;
        this.codigo_postal = codigo_postal;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    
    public int getId_bache() {
        return id_bache;
    }

    public void setId_bache(int id_bache) {
        this.id_bache = id_bache;
    }

    public int getId_ciudadano() {
        return id_ciudadano;
    }

    public void setId_ciudadano(int id_ciudadano) {
        this.id_ciudadano = id_ciudadano;
    }

    public Date getFecha_reporte() {
        return fecha_reporte;
    }

    public void setFecha_reporte(Date fecha_reporte) {
        this.fecha_reporte = fecha_reporte;
    }

    public int getTamano_aproximado() {
        return tamano_aproximado;
    }

    public void setTamano_aproximado(int tamano_aproximado) {
        this.tamano_aproximado = tamano_aproximado;
    }

    public String getSeveridad() {
        return severidad;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public String getEstado_actual() {
        return estado_actual;
    }

    public void setEstado_actual(String estado_actual) {
        this.estado_actual = estado_actual;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "bache{" + "id_bache=" + id_bache + ", id_ciudadano=" + id_ciudadano + ", fecha_reporte=" + fecha_reporte + ", tamano_aproximado=" + tamano_aproximado + ", severidad=" + severidad + ", estado_actual=" + estado_actual + ", calle=" + calle + ", colonia=" + colonia + ", codigo_postal=" + codigo_postal + ", latitud=" + latitud + ", longitud=" + longitud + '}';
    }

}
