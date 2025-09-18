/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class intervencion {

    private int id_bache;
    private int id_atencion;

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

    @Override
    public String toString() {
        return "intervencion{" + "id_bache=" + id_bache + ", id_atencion=" + id_atencion + '}';
    }

}
