/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;



/**
 *
 * @author Joel
 */
public class generarPDF {
    
    private Font FuenteBold= new Font(Font.FontFamily.COURIER,10,Font.BOLD);
    private Font FuenteNormal= new Font(Font.FontFamily.COURIER,8,Font.BOLD);
    private Font FuenteItalic= new Font(Font.FontFamily.COURIER,8,Font.BOLD);
    
    public void generarPDF( String Salida,String codigo)
    {
    
    try
    {
        Document documento=new Document(PageSize.A7,36,36,10,10);
        PdfWriter pw=PdfWriter.getInstance(documento, new FileOutputStream(Salida));
        documento.open();
       // documento.add(getHeader(header));
       // Image imagen= Image.getInstance(rutaImagen);
       // imagen.scaleAbsolute(100, 100);
        //imagen.setAlignment(Element.ALIGN_CENTER);
       // documento.add(imagen);
        //documento.add(getInfo(informacion));
        documento.add(getBarcode(documento, pw, codigo));
        //documento.add(getFooter(footer));
        documento.close();
        
    }catch(Exception e){
        
        
    }}
    
    private Paragraph getHeader(String texto)
    {
    Paragraph p= new Paragraph();
        Chunk c=new Chunk();
    p.setAlignment(Element.ALIGN_CENTER);
    c.append(texto);
    c.setFont(FuenteBold);
    p.add(c);
    return p;
    }
    
    
    private Paragraph getInfo(String texto)
    {
    Paragraph p= new Paragraph();
        Chunk c=new Chunk();
    p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
    c.append(texto);
    c.setFont(FuenteNormal);
    p.add(c);
    return p;
    }
    
       private Paragraph getFooter(String texto)
    {
    Paragraph p= new Paragraph();
        Chunk c=new Chunk();
    p.setAlignment(Element.ALIGN_RIGHT);
    c.append(texto);
    c.setFont(FuenteItalic);
    p.add(c);
    return p;
    }
    
       private Image getBarcode(Document docuemnto, PdfWriter pw, String codigo)
       {
       PdfContentByte cimg=pw.getDirectContent();
           Barcode128 code128= new Barcode128();
           code128.setCode(formatearCodigo(codigo));
           code128.setCodeType(Barcode128.CODE128);
           code128.setTextAlignment(Element.ALIGN_CENTER);
           Image imagen= code128.createImageWithBarcode(cimg, BaseColor.BLACK, BaseColor.YELLOW);
           float scaler= ((docuemnto.getPageSize().getWidth()-docuemnto.leftMargin()- docuemnto.rightMargin()-0)/imagen.getWidth()*60);
       imagen.scalePercent(scaler);
       imagen.setAlignment(Element.ALIGN_CENTER);
       return imagen;
       
       }
       
       
       private  String formatearCodigo(String num)
       {
       NumberFormat form= new DecimalFormat("00000");
       return form.format((form!=null)? Integer.parseInt(num):00000);
       
       
       
       }
       
       
       
}
