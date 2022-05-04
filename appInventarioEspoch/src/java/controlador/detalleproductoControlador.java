/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidad.Detalleprestamo;
import entidad.Prestamo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.detalleModelo;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class detalleproductoControlador {

    private Prestamo objprestamo;
    private Detalleprestamo objdetalleprestamo;
    private Detalleprestamo objprestamoSel;
    private List<Detalleprestamo> lstprestamo;
     private String cedula;
    private String codigo_Barra;

  
    public String getCodigo_Barra() {
        return codigo_Barra;
    }

    public void setCodigo_Barra(String codigo_Barra) {
        this.codigo_Barra = codigo_Barra;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public detalleproductoControlador() {
        this.reinit();
    }

    private void reinit() {

        this.objdetalleprestamo = new Detalleprestamo();
        this.objprestamoSel = new Detalleprestamo();
        this.objprestamo = new Prestamo();
        this.lstprestamo = new ArrayList<>();
        //  this.cargarPrestamo();
    }

    public Prestamo getObjprestamo() {
        return objprestamo;
    }

    public void setObjprestamo(Prestamo objprestamo) {
        this.objprestamo = objprestamo;
    }

    public Detalleprestamo getObjdetalleprestamo() {
        return objdetalleprestamo;
    }

    public void setObjdetalleprestamo(Detalleprestamo objdetalleprestamo) {
        this.objdetalleprestamo = objdetalleprestamo;
    }

    public Detalleprestamo getObjprestamoSel() {
        return objprestamoSel;
    }

    public void setObjprestamoSel(Detalleprestamo objprestamoSel) {
        this.objprestamoSel = objprestamoSel;
    }

    public List<Detalleprestamo> getLstprestamo() {
        return lstprestamo;
    }

    public void setLstprestamo(List<Detalleprestamo> lstprestamo) {
        this.lstprestamo = lstprestamo;
    }
    
    
    

    public void cargarPrestamo() {
        try {
            this.objprestamo = detalleModelo.obtenerPrestamo(cedula);
            if (objprestamo != null) {
                this.cargarDetalles();
            }
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("Información", facesMsg);

        }

    }

    public void cargarDetalles() {
        try {
            
            this.lstprestamo = detalleModelo.obtenerdetallesPrestamo(objprestamo.getId_prestamo());
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("Información", facesMsg);

        }

    }

    
    
    
     public void modificarDetallePrestamo() {
       try {
           if(lstprestamo!= null)
          detalleModelo.modificarDetallePrestamo(objprestamo.getId_prestamo(),codigo_Barra); 
              
          
        } catch (Exception e) {

             FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vuelva a intentar", "Error");
            // System.out.println("private void actualizarCausasBajoRendimiento dice: " + e.getMessage());
        }
    }
    

}
