/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidad.Color;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.colorModelo;
import org.primefaces.context.DefaultRequestContext;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class colorControlador {
    private Color objColor;
    private Color objcolorSel;
    private ArrayList<Color> lstcolor;

    public colorControlador() {
        this.reinit();
    }
    
    private void reinit()
    {
      
    this.objColor=new Color();
    this.objcolorSel=new Color();
    this.lstcolor=new ArrayList<>();
    this.cargarColor();
    
    }

    public Color getObjColor() {
        return objColor;
    }

    public void setObjColor(Color objColor) {
        this.objColor = objColor;
    }

    public Color getObjcolorSel() {
        return objcolorSel;
    }

    public void setObjcolorSel(Color objcolorSel) {
        this.objcolorSel = objcolorSel;
    }

    public ArrayList<Color> getLstcolor() {
        return lstcolor;
    }

    public void setLstcolor(ArrayList<Color> lstcolor) {
        this.lstcolor = lstcolor;
    }
    
    
    
     public void cargarColor()
   {
    try {
            this.lstcolor = colorModelo.obtenerTodosColores();
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
   }
 
    
     
     
     public void insertarColor() {
        try {
            if (colorModelo.insertar(objColor)) {
                this.reinit();
                DefaultRequestContext.getCurrentInstance().execute("wdlgNuevo.hide()");
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos Insertados", "Datos Insertados");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);

            } else {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Error");
                FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
            }
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());
            FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    } 
   
    
    
    public void modificarColor() {
       try {
          if (colorModelo.modificarColor(objcolorSel)) {
              
                this.reinit();
                DefaultRequestContext.getCurrentInstance().execute("wdlgModificarColor.hide()");
                 FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion guardada con exito", "Datos Insertados");
              //  Util.addSuccessMessage("Información guardada con éxito");
                //System.out.println("public void actualizarCausasBajoRendimiento dice: Información guardada con éxito!!");
            } else {
                 FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Error");
               
                // System.out.println("public void actualizarCausasBajoRendimiento dice: Error al guardar la información");
            }
        } catch (Exception e) {

             FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vuelva a intentar", "Error");
            // System.out.println("private void actualizarCausasBajoRendimiento dice: " + e.getMessage());
        }
    }
    
    public void eliminarColor() {
        try {
            if (colorModelo.eliminarColor(objcolorSel.getId_color())) {
                this.reinit();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarColor.hide()");
                 FacesContext context= FacesContext.getCurrentInstance();
               context.addMessage("Exito", new FacesMessage("Datos eliminados correctamente"));
                // System.out.println("public void eliminarCausasBajoRendimiento dice: Información eliminada.");
            } else {
                 FacesContext context= FacesContext.getCurrentInstance();
             context.addMessage("Fracaso", new FacesMessage("Datos no eliminados"));
                //  System.out.println("public void eliminarCausasBajoRendimiento dice: Error al eliminar la información");
            }
        } catch (Exception e) {
           FacesContext context= FacesContext.getCurrentInstance();
             context.addMessage("Verifique la informacion", new FacesMessage(e.getMessage()));
            // System.out.println("private void eliminarCausasBajoRendimiento dice: " + e.getMessage());
        }
    }
    
     
     
     
    
}
