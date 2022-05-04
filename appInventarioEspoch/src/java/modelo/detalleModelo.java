/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import entidad.Detalleprestamo;
import entidad.Prestamo;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joel
 */
public class detalleModelo {
 
   // public static int codigo;
         ///////insertar
    public static boolean insertar(Detalleprestamo objdp) throws Exception {
        boolean bandera = false;
        try {
            ////declaro un arreglo de parametros para llenar la informacion que se insertara en la tabla dependencia
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            ////declaro una variable de tipo String que guardar el llamado a la funcion de postgres con los parametros representados por signo de integrracion
            String sql = "select * from public.f_insert_t_detalleprestamo(?,?,?,?)";
            
            //////lenamos cada parametro de acuerdo al nuimer y orden establecido en la funcion
            lstP.add(new Parametro(1, objdp.getObjPrestamo()));
            lstP.add(new Parametro(2, objdp.getCodigoBarra()));
            lstP.add(new Parametro(3, objdp.getNombreProducto()));
            lstP.add(new Parametro(4, objdp.getEstado()));
            
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
    
    
    
    
    
    
    public static ArrayList<Prestamo> llenarPrestamo(ConjuntoResultado rs) throws Exception {
        ArrayList<Prestamo> lst = new ArrayList<Prestamo>();
        Prestamo prestamos= null;
        try {
             
            while (rs.next()) {
               
                prestamos = new Prestamo(rs.getInt(0), rs.getString(1),rs.getDate(2), rs.getDate(3),rs.getString(4));
              //  codigo=prestamos.getId_prestamo();
                lst.add(prestamos);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
    
    
    
    
    
      
     public static  Prestamo  obtenerPrestamo(String cedula) throws Exception {
       Prestamo obj;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from public.f_select_prestamo_cedula(?)";
            lstP.add(new Parametro(1, cedula));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            obj = new Prestamo();
            obj = llenarPrestamo(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return obj;
    }
    
     
         
      public static List<Detalleprestamo> obtenerdetallesPrestamo(int codigo)throws Exception{
        List<Detalleprestamo> prestamo= new ArrayList<>();
        try {
            ArrayList<Parametro> lstMaterialpedido= new ArrayList<>();
            String sql="SELECT *from public.f_select_t_detalleprestamo_id(?)";
            lstMaterialpedido.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql,lstMaterialpedido);
            while (rs.next()) {
                Detalleprestamo objDetalle =new Detalleprestamo();
                objDetalle.setId_detalle(rs.getInt(0));
                objDetalle.setObjPrestamo(rs.getInt(1));
                objDetalle.setCodigoBarra(rs.getString(2));
                objDetalle.setNombreProducto(rs.getString(3));
                objDetalle.setEstado(rs.getBoolean(4));
                prestamo.add(objDetalle);
              }
           rs=null; 
            
        } catch (Exception e) {
             prestamo.clear();
            throw e;
        }
      return prestamo;
    
    }
   
  
     
    public static ArrayList<Detalleprestamo> llenarDetallePrestamo(ConjuntoResultado rs) throws Exception {
        ArrayList<Detalleprestamo> lst = new ArrayList<Detalleprestamo>();
        Detalleprestamo prestamos= null;
        try {
             
            while (rs.next()) {
               
                prestamos = new Detalleprestamo(rs.getInt(0), rs.getInt(1),rs.getString(2), rs.getString(3),rs.getBoolean(4));
                lst.add(prestamos);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
  
     
    
    
     ///////update
    public static boolean modificarDetallePrestamo(int i,String codigo) throws Exception {
        boolean bandera = false;
        try {
            ////declaro un arreglo de parametros para llenar la informacion que se insertara en la tabla dependencia
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();

            ////declaro una variable de tipo String que guardar el llamado a la funcion de postgres con los parametros representados por signo de integrracion
            String sql = "select * from public.f_update_t_detalleprestamo(?,?)";
            //////lenamos cada parametro de acuerdo al nuimer y orden establecido en la funcion
            lstP.add(new Parametro(1, i));
            lstP.add(new Parametro(2, codigo));
            //////declaro Un resultSet para que me guarde los resultados ejecudatos por el query
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            ////recorre el registro en memoria
           
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        ///retoramos true si inserta caso contrario devuelve un mensaje de error
        return bandera;
    }
    
    
    
    
    
    
   
    
    
    
    
  
}
