/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.util.Date;

/**
 *
 * @author Joel
 */
public class Mantenimiento {
    private int id_fecha_mantenimiento;
    Producto objProducto;
    private Date fecha;
    private String descripcion;

    public Mantenimiento() {
    }

    public Mantenimiento(int id_fecha_mantenimiento, Producto objProducto, Date fecha, String descripcion) {
        this.id_fecha_mantenimiento = id_fecha_mantenimiento;
        this.objProducto = objProducto;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public int getId_fecha_mantenimiento() {
        return id_fecha_mantenimiento;
    }

    public void setId_fecha_mantenimiento(int id_fecha_mantenimiento) {
        this.id_fecha_mantenimiento = id_fecha_mantenimiento;
    }

    public Producto getObjProducto() {
        return objProducto;
    }

    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
