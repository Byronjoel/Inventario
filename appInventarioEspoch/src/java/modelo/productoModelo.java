/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import entidad.Color;
import entidad.Marca;
import entidad.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.postgresql.core.Query;

/**
 *
 * @author Joel
 */
public class productoModelo {
    
         ///////insertar
    public static boolean insertar(Producto objp) throws Exception {
        boolean bandera = false;
        try {
            ////declaro un arreglo de parametros para llenar la informacion que se insertara en la tabla dependencia
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            ////declaro una variable de tipo String que guardar el llamado a la funcion de postgres con los parametros representados por signo de integrracion
            String sql = "select * from public.f_insert_t_producto(?,?,?,?,?,?,?,?,?,?)";
            
           // java.sql.Date Fechaingreso = new java.sql.Date(objp.getFecha_ingreso().getTime());
           java.sql.Date fecha= new java.sql.Date(objp.getFecha_ingreso().getTime());
            //////lenamos cada parametro de acuerdo al nuimer y orden establecido en la funcion
            lstP.add(new Parametro(1, objp.getCodigo_interno()));
            lstP.add(new Parametro(2, objp.getCodigo_barra()));            
            lstP.add(new Parametro(3, objp.getNombre_producto()));
            lstP.add(new Parametro(4, objp.getSerie_producto()));
            
            lstP.add(new Parametro(5, fecha));
            lstP.add(new Parametro(6, objp.getEstado_producto()));            
            lstP.add(new Parametro(7, objp.getDescripcion()));
            lstP.add(new Parametro(8, objp.getObjColor().getId_color()));
            lstP.add(new Parametro(9, objp.getObjMarca().getId_marca()));
            lstP.add(new Parametro(10, objp.getObjModelo()));            
            
            //////declaro Un resultSet para que me guarde los resultados ejecudatos por el query
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            ////recorre el registro en memoria
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                bandera = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        ///retoramos true si inserta caso contrario devuelve un mensaje de error
        return bandera;
    }
    
    
    
     //////////////////////////////////////////////////////////////
    //para  llenar en memoria los datos que provienen de la BD hacemos lo siguiente
    public static ArrayList<Producto> llenarProducto(ConjuntoResultado rs) throws Exception {
        ArrayList<Producto> lst = new ArrayList<Producto>();
        Producto productos= null;
        try {
             
            while (rs.next()) {
             
                Color obj =new Color(); 
                obj.setNombre_color(rs.getString(8));
                  Marca obj1 =new Marca(); 
                obj1.setNombre_marca(rs.getString(9));
               
                
                productos = new Producto(rs.getInt(0), rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6),rs.getString(7),obj,obj1,rs.getString(10));
                lst.add(productos);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
    
    
    
    ///////seleccionar todas las dependencias
    public static ArrayList<Producto> obtenerTodosProductos() throws Exception {
        ArrayList<Producto> lst = new ArrayList<Producto>();
        try {
            String sql = "select * from public.f_select_t_producto()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarProducto(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
    
    
    
    
    
    
    ///////eliminar
    public static boolean eliminarProducto(int idProducto) throws Exception {
        boolean bandera = false;
        try {
            ////declaro un arreglo de parametros para llenar la informacion que se insertara en la tabla dependencia
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            ////declaro una variable de tipo String que guardar el llamado a la funcion de postgres con los parametros representados por signo de integrracion
            String sql = "select * from public.f_delete_t_producto(?)";
            //////lenamos cada parametro de acuerdo al nuimer y orden establecido en la funcion
            lstP.add(new Parametro(1, idProducto));
            //////declaro Un resultSet para que me guarde los resultados ejecudatos por el query
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            ////recorre el registro en memoria
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                bandera = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        ///retoramos true si inserta caso contrario devuelve un mensaje de error
        return bandera;
    }

    
 
        
      ///////update
    public static boolean update(Producto producto) throws Exception {
        boolean bandera = false;
        try {
            ////declaro un arreglo de parametros para llenar la informacion que se insertara en la tabla dependencia
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();

            ////declaro una variable de tipo String que guardar el llamado a la funcion de postgres con los parametros representados por signo de integrracion
            String sql = "select * from public.fn_update_t_producto(?,?,?,?,?,?,?,?,?,?,?)";
            //////lenamos cada parametro de acuerdo al nuimer y orden establecido en la funcion
             java.sql.Date fecha= new java.sql.Date(producto.getFecha_ingreso().getTime());    
            lstP.add(new Parametro(1, producto.getId_producto()));
            lstP.add(new Parametro(2, producto.getCodigo_interno()));
            lstP.add(new Parametro(3, producto.getCodigo_barra()));
            lstP.add(new Parametro(4, producto.getNombre_producto()));
            lstP.add(new Parametro(5, producto.getSerie_producto()));            
            lstP.add(new Parametro(6, fecha));
            lstP.add(new Parametro(7, producto.getEstado_producto()));
            lstP.add(new Parametro(8, producto.getDescripcion()));
            lstP.add(new Parametro(9, producto.getObjColor().getId_color()));
            lstP.add(new Parametro(10, producto.getObjMarca().getId_marca()));
            lstP.add(new Parametro(11, producto.getObjModelo()));
            //////declaro Un resultSet para que me guarde los resultados ejecudatos por el query
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            ////recorre el registro en memoria
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                bandera = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        ///retoramos true si inserta caso contrario devuelve un mensaje de error
        return bandera;
    }
    
 
     
     public static Producto obtenerProducto(String codBarra) throws Exception {
       Producto obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from public.f_select_producto_id(?)";
            lstP.add(new Parametro(1, codBarra));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new Producto();
            obj = llenarProducto(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }
    
    
    
    
}
