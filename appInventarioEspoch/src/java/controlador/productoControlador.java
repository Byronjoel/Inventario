/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import entidad.Color;
import entidad.Marca;
import entidad.Producto;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.codigobarras;
import modelo.generarPDF;
import modelo.productoModelo;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;
import org.primefaces.context.DefaultRequestContext;
import java.util.concurrent.ThreadLocalRandom;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class productoControlador {

    private Producto objProducto;
    private Producto objproductoSel;
    private ArrayList<Producto> lstproducto;
    private String dato;

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    private void reinit() {

        this.objProducto = new Producto();
        this.objproductoSel = new Producto();
        this.lstproducto = new ArrayList<>();

        Color objcolor = new Color();
        this.objProducto.setObjColor(objcolor);
        this.objproductoSel.setObjColor(objcolor);

        Marca objmarca = new Marca();
        this.objProducto.setObjMarca(objmarca);
        this.objproductoSel.setObjMarca(objmarca);

        this.cargarProductos();

    }

    public productoControlador() {

        this.reinit();
    }

    public Producto getObjProducto() {
        return objProducto;
    }

    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }

    public Producto getObjproductoSel() {
        return objproductoSel;
    }

    public void setObjproductoSel(Producto objproductoSel) {
        this.objproductoSel = objproductoSel;
    }

    public ArrayList<Producto> getLstproducto() {
        return lstproducto;
    }

    public void setLstproducto(ArrayList<Producto> lstproducto) {
        this.lstproducto = lstproducto;
    }

    public void cargarProductos() {
        try {
            this.lstproducto = productoModelo.obtenerTodosProductos();
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("Información", facesMsg);
        }
    }

    public void insertarProducto() {
        try {
            String cadena = "";
            productoControlador ob = new productoControlador();
            String var = ob.consultaCodigo();
            int num = Integer.parseInt(var);
            num++;

            cadena = String.valueOf(num);

            objProducto.setCodigo_barra(cadena);

            if (productoModelo.insertar(objProducto)) {
                //generarPDF g= new generarPDF();
                // g.generarPDF("/Users/Joel/Documents/NetBeansProjects/appInventarioEspoch/prueba1.pdf",objProducto.getCodigo_barra());

                codigobarras c = new codigobarras();
                c.generarBarra(objProducto.getCodigo_barra());

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

    public String consultaCodigo() throws SQLException, Exception {
        String var = "9999";
        try {
            Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BDINVENTARIOELECTRONICA", "postgres", "12345");
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM t_producto ORDER BY id_producto DESC LIMIT 1");
            ResultSet result1 = consulta.executeQuery();

            while (result1.next()) {

                var = result1.getString(3);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", e.getMessage());

        }
        return var;
    }

    public void generadorCodigos() {
        codigobarras c = new codigobarras();
        c.generarBarra(dato);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Codigo de Barras Creado", "Codigo de Barras Creado");

    }

    public void modificarProducto() {
        try {
            if (productoModelo.update(objproductoSel)) {

                this.reinit();
                DefaultRequestContext.getCurrentInstance().execute("wdlgModificarProducto.hide()");
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion guardada con exito", "Datos Modificados");
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

    public void eliminarProducto() {
        try {
            if (productoModelo.eliminarProducto(objproductoSel.getId_producto())) {
                this.reinit();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarProducto.hide()");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("Exito", new FacesMessage("Datos eliminados correctamente"));
                // System.out.println("public void eliminarCausasBajoRendimiento dice: Información eliminada.");
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("Fracaso", new FacesMessage("Datos no eliminados"));
                //  System.out.println("public void eliminarCausasBajoRendimiento dice: Error al eliminar la información");
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Verifique la informacion", new FacesMessage(e.getMessage()));
            // System.out.println("private void eliminarCausasBajoRendimiento dice: " + e.getMessage());
        }
    }

  
    
     public void resetFail() {
        this.objProducto = new Producto();
        this.objproductoSel = new Producto();
        this.lstproducto = new ArrayList<>();
          Color objcolor = new Color();
        this.objProducto.setObjColor(objcolor);
        this.objproductoSel.setObjColor(objcolor);

        Marca objmarca = new Marca();
        this.objProducto.setObjMarca(objmarca);
        this.objproductoSel.setObjMarca(objmarca);

        this.cargarProductos();
       
    }
}
