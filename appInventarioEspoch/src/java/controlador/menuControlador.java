/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ADMINISTRADOR-LR
 */
@ManagedBean
@ViewScoped
public class menuControlador {
      private String ruta;
     
        public menuControlador() {
    }
     
      public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
     public  void frmColor()
    {
    this.ruta="colorVista.xhtml";
    }
    
    public void frmMarca()
    {
    this.ruta="marcaVista.xhtml";
    }    
    
    public void frmProducto()
    {
    
    this.ruta="productoVista.xhtml";
    }
    
    
       public void frmPrestamo()
    {
    
    this.ruta="prestamo.xhtml";
    }
       
       
         public void frmDevolucion()
    {
    
    this.ruta="devolucionVista.xhtml";
    }
}
