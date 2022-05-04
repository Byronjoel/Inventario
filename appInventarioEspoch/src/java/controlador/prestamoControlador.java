/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidad.Detalleprestamo;
import entidad.Prestamo;
import entidad.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.detalleModelo;
import modelo.prestamoModelo;
import modelo.productoModelo;
import org.primefaces.context.DefaultRequestContext;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class prestamoControlador {

    private Prestamo objprestamo;
    private Producto objproducto;
    private String codigoBarra;
    private ArrayList<Detalleprestamo> listaDetallePrestamo;

    
    private void reinit() {
    this.objprestamo= new Prestamo();
        this.listaDetallePrestamo = new ArrayList<>();

    }

    public prestamoControlador() {

        this.reinit();
    }

    public ArrayList<Detalleprestamo> getListaDetallePrestamo() {
        return listaDetallePrestamo;
    }

    public void setListaDetallePrestamo(ArrayList<Detalleprestamo> listaDetallePrestamo) {
        this.listaDetallePrestamo = listaDetallePrestamo;
    }

    public Prestamo getObjprestamo() {
        return objprestamo;
    }

    public void setObjprestamo(Prestamo objprestamo) {
        this.objprestamo = objprestamo;
    }

    public Producto getObjproducto() {
        return objproducto;
    }

    public void setObjproducto(Producto objproducto) {
        this.objproducto = objproducto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public void agregarDatosProducto() {
        try {

            this.objproducto = productoModelo.obtenerProducto(codigoBarra);
            if (this.objproducto != null) {
                this.listaDetallePrestamo.add(new Detalleprestamo(0, 0, this.objproducto.getCodigo_barra(), this.objproducto.getNombre_producto(), Boolean.TRUE));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PRODUCTO NO ENCONTRADO"));
            }

            // this.lstdetalle.add(new detalleFactura(33, null, this.objproducto, this.objproducto.getNombre(), this.cantidad, this.objproducto.getPreioVenta(),this.cantidad*this.objproducto.getPreioVenta()));
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("Información", facesMsg);

        }

    }

    public void insertarPrestamo() {
        try {
         
            if (prestamoModelo.insertarPrestamo(objprestamo)) {
                
                prestamoControlador objpr= new prestamoControlador();
                int val= objpr.consultaNumeroPrestamo();
                
                
               for (Detalleprestamo item : listaDetallePrestamo) {
                    this.objproducto = productoModelo.obtenerProducto(item.getCodigoBarra());
                    item.setObjPrestamo(val);
                    item.setNombreProducto(objproducto.getNombre_producto());
                    item.setCodigoBarra(objproducto.getCodigo_barra());
                    item.setEstado(Boolean.TRUE);

                    detalleModelo.insertar(item);
                }

               FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "PRESTAMO INGRESADO", "PRESTAMO INGRESADO");
                //FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
               
            } else {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Error");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            }
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
            FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }

    }
    
    //Obtiene el numero de factura para asignar al detall prestamo
     public int consultaNumeroPrestamo() throws SQLException, Exception {
        int var=1;
        try {
            Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BDINVENTARIOELECTRONICA", "postgres", "12345");
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM t_prestamo ORDER BY id_prestamo DESC LIMIT 1");
            ResultSet result1 = consulta.executeQuery();
            
            while (result1.next()) {

                var = result1.getInt(1);
            }
            

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());

        }
        return var;
    }

    
    
  
    public void limpiarPrestamo()
    {
    this.objprestamo= new Prestamo();
    this.objproducto= new Producto();
    this.listaDetallePrestamo= new ArrayList<>();
    this.codigoBarra=null;
    
    
    
    }
    
    
}
