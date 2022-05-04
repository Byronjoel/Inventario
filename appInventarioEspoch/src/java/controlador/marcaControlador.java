/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidad.Marca;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.marcaModelo;
import org.primefaces.context.DefaultRequestContext;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class marcaControlador {
    private Marca objMarca;
    private Marca objmarcaSel;
    private ArrayList<Marca> lstmarca;

    public marcaControlador() {
        
          this.reinit();
    }
    
    
       private void reinit()
    {
      
   
        
    this.objMarca=new Marca();
    this.objmarcaSel=new Marca();
    this.lstmarca=new ArrayList<>();
    this.cargarMarca();
    
    }

    public Marca getObjMarca() {
        return objMarca;
    }

    public void setObjMarca(Marca objMarca) {
        this.objMarca = objMarca;
    }

    public Marca getObjmarcaSel() {
        return objmarcaSel;
    }

    public void setObjmarcaSel(Marca objmarcaSel) {
        this.objmarcaSel = objmarcaSel;
    }

    public ArrayList<Marca> getLstmarca() {
        return lstmarca;
    }

    public void setLstmarca(ArrayList<Marca> lstmarca) {
        this.lstmarca = lstmarca;
    }

      
     public void cargarMarca()
   {
    try {
            this.lstmarca = marcaModelo.obtenerTodosMarcas();
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
   } 
     
    
     
       
     public void insertarMarca() {
        try {
            if (marcaModelo.insertar(objMarca)) {
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
   
    
    
    public void modificarMarca() {
       try {
          if (marcaModelo.modificarMarca(objmarcaSel)) {
              
                this.reinit();
                DefaultRequestContext.getCurrentInstance().execute("wdlgModificarMarca.hide()");
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
    
    public void eliminarMarca() {
        try {
            if (marcaModelo.eliminarMarca(objmarcaSel.getId_marca())) {
                this.reinit();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarMarca.hide()");
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
