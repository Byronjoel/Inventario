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
public class Detalleprestamo  extends Prestamo{
    private int id_detalle;
    private int objPrestamo;
    private String codigoBarra;
    private String nombreProducto;
    private  Boolean estado;

    public Detalleprestamo() {
    }

    public Detalleprestamo(int id_detalle, int objPrestamo, String codigoBarra, String nombreProducto, Boolean estado) {
        this.id_detalle = id_detalle;
        this.objPrestamo = objPrestamo;
        this.codigoBarra = codigoBarra;
        this.nombreProducto = nombreProducto;
        this.estado = estado;
    }


  

  /*  public Detalleprestamo(int id_detalle, int objPrestamo, String codigoBarra, String nombreProducto, Boolean estado, int id_prestamo, String cedula_estudiante, Date fecha_prestamo, Date fecha_entrega, String observacion_prestamo) {
        super(id_prestamo, cedula_estudiante, fecha_prestamo, fecha_entrega, observacion_prestamo);
        this.id_detalle = id_detalle;
        this.objPrestamo = objPrestamo;
        this.codigoBarra = codigoBarra;
        this.nombreProducto = nombreProducto;
        this.estado = estado;
    }
    
    */
    
    
    
    
    
    
    
    
    
    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getObjPrestamo() {
        return objPrestamo;
    }

    public void setObjPrestamo(int objPrestamo) {
        this.objPrestamo = objPrestamo;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    
    
    
    
}
