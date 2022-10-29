package ejemploitext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Principal
 */
public class EjemploiText {

   public static void main(String[] args) {
       EjemploiText obj=new EjemploiText();
       obj.reporteUsandoParagraph();
    }
    
    
    public void reporte(){
        Document documento=new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("Hola.pdf"));
            documento.open();
            documento.add(new Paragraph("Hola esto es una prueba"));
            documento.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(EjemploiText.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void reporteConFormato(){
        try {
            Document documento=new Document();
            PdfWriter.getInstance(documento, new FileOutputStream("HolaFormato.pdf"));
            documento.open();
            documento.add(new Paragraph("\n\n Aqui va el titulo"));            
            documento.add(new Paragraph("\n\n Fecha:"+new java.util.Date()));
            PdfPTable tabla=new PdfPTable(2);
            tabla.setWidthPercentage(100);
            PdfPCell celda1=new PdfPCell(new Paragraph (
                    "Clave", FontFactory.getFont("arial",10,Font.BOLD,BaseColor.BLUE)));
            PdfPCell celda2=new PdfPCell(new Paragraph (
                    "Nombre", FontFactory.getFont("arial",10,Font.BOLD,BaseColor.GREEN)));
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            documento.add(tabla);
            String ruta=System.getProperty("user.dir");
            Image imagen=Image.getInstance(ruta+"/src/Reportes/pastelito.jpg");
            imagen.setAlignment(Image.ALIGN_RIGHT);
            documento.add(new Paragraph ("\n\n\n"));
            documento.add(imagen);                             
            documento.close();
        } catch (DocumentException ex) {
            Logger.getLogger(EjemploiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EjemploiText.class.getName()).log(Level.SEVERE, null, ex);        
        } catch (MalformedURLException ex) {
            Logger.getLogger(EjemploiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EjemploiText.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      public void reporteUsandoParagraph(){
        try {
            Document documento=new Document();
            PdfWriter.getInstance(documento, new FileOutputStream("HolaFormatodeParrafo.pdf"));
            documento.open();
            String ruta=System.getProperty("user.dir");
            Image imagen=Image.getInstance(ruta+"/src/Reportes/pastelito.jpg");
            imagen.setAlignment(Image.ALIGN_CENTER);                  
            documento.add(imagen);        
            Paragraph interlineado=new Paragraph(3.5f);
            //interlineado.setSpacingAfter(3.5f);
            interlineado.setSpacingBefore(15.5f);
            documento.add(interlineado);
            PdfPTable tabla=new PdfPTable(2);
            tabla.setWidthPercentage(100);
            PdfPCell celda1=new PdfPCell(new Paragraph (
                    "Clave", FontFactory.getFont("arial",10,Font.BOLD,BaseColor.BLUE)));
            PdfPCell celda2=new PdfPCell(new Paragraph (
                    "Nombre", FontFactory.getFont("arial",10,Font.BOLD,BaseColor.GREEN)));
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            documento.add(tabla);
            documento.add(new Paragraph("mensaje sin formato"));
            Paragraph parrafo1=new Paragraph("texto centrado");
            parrafo1.setAlignment(Element.ALIGN_CENTER);
            Paragraph parrafo2=new Paragraph("texto con identacion a la izquiera de 150 pts");
            parrafo2.setIndentationLeft(150);
            Paragraph parrafo3=new Paragraph("texto alineado a la izquierda");
            parrafo3.setIndentationLeft(50);
            Paragraph parrafo4=new Paragraph("texto alineado a la derecha");
            parrafo4.setAlignment(Element.ALIGN_RIGHT);
            documento.add(parrafo1);
            documento.add(parrafo2);
            documento.add(parrafo3);
            documento.add(parrafo4);        
            
          
            documento.close();
        } catch (DocumentException ex) {
            Logger.getLogger(EjemploiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EjemploiText.class.getName()).log(Level.SEVERE, null, ex);        
        } catch (MalformedURLException ex) {
            Logger.getLogger(EjemploiText.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EjemploiText.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
