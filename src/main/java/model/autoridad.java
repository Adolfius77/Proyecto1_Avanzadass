/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class autoridad {

    private int id_autoridad;
    private String nombre;
    private String dependencia;
    private String telefono;
    private String correo;

    public autoridad() {
    }

    public autoridad(int id_autoridad, String nombre, String dependencia, String telefono, String correo) {
        this.id_autoridad = id_autoridad;
        this.nombre = nombre;
        this.dependencia = dependencia;
        this.telefono = telefono;
        this.correo = correo;
    }
    
    public int getId_autoridad() {
        return id_autoridad;
    }

    public void setId_autoridad(int id_autoridad) {
        this.id_autoridad = id_autoridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "autoridad{" + "id_autoridad=" + id_autoridad + ", nombre=" + nombre + ", dependencia=" + dependencia + ", telefono=" + telefono + ", correo=" + correo + '}';
    }

}
