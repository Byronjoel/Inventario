/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import entidad.Prestamo;
import entidad.Producto;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Joel
 */
public class prestamoModelo {
 
    
    
    
         ///////insertar
    public static boolean insertarPrestamo(Prestamo objp) throws Exception {
        boolean bandera = false;
        try {
            ////declaro un arreglo de parametros para llenar la informacion que se insertara en la tabla dependencia
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            ////declaro una variable de tipo String que guardar el llamado a la funcion de postgres con los parametros representados por signo de integrracion
            String sql = "select * from public.f_insert_t_prestamo(?,?,?,?)";
            
           // java.sql.Date Fechaingreso = new java.sql.Date(objp.getFecha_ingreso().getTime());
           java.sql.Date fecha= new java.sql.Date(objp.getFecha_prestamo().getTime());
           java.sql.Date fecha1= new java.sql.Date(objp.getFecha_entrega().getTime());
                   
            //////lenamos cada parametro de acuerdo al nuimer y orden establecido en la funcion
            lstP.add(new Parametro(1, objp.getCedula_estudiante()));
            lstP.add(new Parametro(2, fecha));
            lstP.add(new Parametro(3, fecha1));            
            lstP.add(new Parametro(4, objp.getObservacion_prestamo()));
          
            
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
    
    
    ///////eliminar
    public static boolean eliminarPrestamo(int idPrestamo) throws Exception {
        boolean bandera = false;
        try {
            ////declaro un arreglo de parametros para llenar la informacion que se insertara en la tabla dependencia
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            ////declaro una variable de tipo String que guardar el llamado a la funcion de postgres con los parametros representados por signo de integrracion
            String sql = "select * from public.f_delete_t_prestamo(?)";
            //////lenamos cada parametro de acuerdo al nuimer y orden establecido en la funcion
            lstP.add(new Parametro(1, idPrestamo));
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
    public static boolean update(Prestamo prestamo) throws Exception {
        boolean bandera = false;
        try {
            ////declaro un arreglo de parametros para llenar la informacion que se insertara en la tabla dependencia
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();

            ////declaro una variable de tipo String que guardar el llamado a la funcion de postgres con los parametros representados por signo de integrracion
            String sql = "select * from public.fn_update_t_prestamo(?,?,?,?,?)";
            //////lenamos cada parametro de acuerdo al nuimer y orden establecido en la funcion
             java.sql.Date fecha= new java.sql.Date(prestamo.getFecha_entrega().getTime());
             java.sql.Date fecha1= new java.sql.Date(prestamo.getFecha_entrega().getTime());
            lstP.add(new Parametro(1, prestamo.getId_prestamo()));
            lstP.add(new Parametro(2, prestamo.getCedula_estudiante()));
            lstP.add(new Parametro(3, fecha));
            lstP.add(new Parametro(4, fecha1));            
           // lstP.add(new Parametro(5, prestamo.getEstado()));
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
    
    
  
       
    ///////seleccionar todas las dependencias
    public static ArrayList<Prestamo> obtenerTodosPrestamos() throws Exception {
        ArrayList<Prestamo> lst = new ArrayList<Prestamo>();
        try {
            String sql = "select * from public.f_select_t_prestamo()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarPrestamo(rs);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }
    
    
    
     //////////////////////////////////////////////////////////////
    //para  llenar en memoria los datos que provienen de la BD hacemos lo siguiente
    public static ArrayList<Prestamo> llenarPrestamo(ConjuntoResultado rs) throws Exception {
        ArrayList<Prestamo> lst = new ArrayList<Prestamo>();
        Prestamo prestamos= null;
        try {
             
            while (rs.next()) {
               
                prestamos = new Prestamo(rs.getInt(0), rs.getString(1),rs.getDate(2), rs.getDate(3),rs.getString(4));
                lst.add(prestamos);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }
    
    
   
    
    
    
    
    
}
