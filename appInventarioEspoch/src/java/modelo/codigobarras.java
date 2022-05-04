/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

/**
 *
 * @author Joel
 */
public class codigobarras {
    
    public void generarBarra(String dato)
            
    {
    
        
        
       /* Barcode barcode = null;
         try {
            barcode = BarcodeFactory.createCode39(dato, true);
        } catch (Exception e) {
        }
          barcode.setDrawingText(false);
         
            barcode.setBarWidth(2);
            barcode.setBarHeight(60);
        try {
            FileOutputStream fos = new FileOutputStream("D:/Etiquetas/etiqueta.png");
               try {
                   BarcodeImageHandler.writePNG(barcode, fos);
               } catch (OutputException ex) {
                   Logger.getLogger(codigobarras.class.getName()).log(Level.SEVERE, null, ex);
               }
             
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(codigobarras.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        
        
        
        
        
        Barcode barcode = null;
         try {
            barcode = BarcodeFactory.createCode128(dato);
        } catch (Exception e) {
        }
          barcode.setDrawingText(false);
         
            barcode.setBarWidth(2);
            barcode.setBarHeight(60);
        try {
          //  FileOutputStream fos = new FileOutputStream("D:/Etiquetas/etiqueta.png");
          FileOutputStream fos = new FileOutputStream("D:/Etiquetas/"+dato+".png");     
          try {
                   BarcodeImageHandler.writePNG(barcode, fos);
               } catch (OutputException ex) {
                   Logger.getLogger(codigobarras.class.getName()).log(Level.SEVERE, null, ex);
               }
             
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(codigobarras.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
        
            
        
    
    }
}
