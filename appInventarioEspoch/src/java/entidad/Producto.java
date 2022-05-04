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
public class Producto {

    private int id_producto;

    private String codigo_interno;
    private String codigo_barra;
    private String nombre_producto; 
    private String objModelo;
    private String serie_producto;
    private Date fecha_ingreso;
    private int estado_producto;
    private String descripcion;
    private Color objColor;
      private Marca objMarca;

    public Producto() {
    }

    public Producto(int id_producto, String codigo_interno, String codigo_barra, String nombre_producto, String serie_producto, Date fecha_ingreso, int estado_producto, String descripcion, Color objColor, Marca objMarca, String objModelo) {
        this.id_producto = id_producto;
        this.codigo_interno = codigo_interno;
        this.codigo_barra = codigo_barra;
        this.nombre_producto = nombre_producto;
        this.objMarca = objMarca;
        this.objModelo = objModelo;
        this.serie_producto = serie_producto;
        this.fecha_ingreso = fecha_ingreso;
        this.estado_producto = estado_producto;
        this.descripcion = descripcion;
        this.objColor = objColor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCodigo_interno() {
        return codigo_interno;
    }

    public void setCodigo_interno(String codigo_interno) {
        this.codigo_interno = codigo_interno;
    }

    public String getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Marca getObjMarca() {
        return objMarca;
    }

    public void setObjMarca(Marca objMarca) {
        this.objMarca = objMarca;
    }

    public String getObjModelo() {
        return objModelo;
    }

    public void setObjModelo(String objModelo) {
        this.objModelo = objModelo;
    }

    public String getSerie_producto() {
        return serie_producto;
    }

    public void setSerie_producto(String serie_producto) {
        this.serie_producto = serie_producto;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(int estado_producto) {
        this.estado_producto = estado_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Color getObjColor() {
        return objColor;
    }

    public void setObjColor(Color objColor) {
        this.objColor = objColor;
    }

    
    
   
}
