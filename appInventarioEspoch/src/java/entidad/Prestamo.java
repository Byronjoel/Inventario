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
public class Prestamo {
    private int id_prestamo;
    private String cedula_estudiante; 
    private Date fecha_prestamo;
    private Date fecha_entrega;
    private String observacion_prestamo;
   // private Boolean estado;

    public Prestamo() {
    }

    public Prestamo(int id_prestamo, String cedula_estudiante, Date fecha_prestamo, Date fecha_entrega, String observacion_prestamo) {
        this.id_prestamo = id_prestamo;
        this.cedula_estudiante = cedula_estudiante;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_entrega = fecha_entrega;
        this.observacion_prestamo = observacion_prestamo;
      
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public String getCedula_estudiante() {
        return cedula_estudiante;
    }

    public void setCedula_estudiante(String cedula_estudiante) {
        this.cedula_estudiante = cedula_estudiante;
    }

    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getObservacion_prestamo() {
        return observacion_prestamo;
    }

    public void setObservacion_prestamo(String observacion_prestamo) {
        this.observacion_prestamo = observacion_prestamo;
    }

  
}
