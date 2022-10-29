/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.Ventas;
import static vista.Ventas.txtId;

/**
 *
 * @author CB09
 */
public class Reporte {
    DefaultTableModel model;
    Document documento=new Document();
    int filas=0;

    public Reporte(DefaultTableModel model) {
        this.model=model;
    }
    
    
    Rectangle tam=new Rectangle(300,500);
        Document ticket=new Document(tam);
    
    public void abrirPdf(String ruta){
        File path=new File(ruta);
        try {
            
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reporteConEncabezado(){
        
        try {
            String r=System.getProperty("user.dir")+("/src/facturas/");
            PdfWriter.getInstance(documento, new FileOutputStream(r+"FacturaPapeleria"+Ventas.idVenta+".pdf"));
            documento.open();
            Paragraph par0=new Paragraph("FOLIO N°: 00"+Ventas.idVenta);
            //par1.getFont().getColor(Color.BROWN);
            par0.setAlignment(Element.ALIGN_RIGHT);
            documento.add(par0);
            Paragraph par1=new Paragraph("Papelerias P@peli S.A. DE C.V.");
            //par1.getFont().getColor(Color.BROWN);
            par1.setAlignment(Element.ALIGN_CENTER);
            documento.add(par1);
            try {
                Image logo = Image.getInstance("E:/pape.jpg");
                logo.scaleAbsoluteWidth(160);
                logo.scaleAbsoluteHeight(110);
                logo.setAlignment(Element.ALIGN_RIGHT);
                //logo.setAbsolutePosition(150, 150);
                documento.add(logo);
            } catch (BadElementException ex) {
                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            }
            Paragraph parrafo1=new Paragraph("**P@PELI**");
            parrafo1.setAlignment(Element.ALIGN_LEFT);
            
            Paragraph parrafo2=new Paragraph("San Pedro");
            parrafo2.setAlignment(Element.ALIGN_LEFT);
            Paragraph parrafo3=new Paragraph("Humberto Lobo No. 540 B Col del Valle, San Pedro Garza García N.L.");
            parrafo3.setAlignment(Element.ALIGN_LEFT);
            Paragraph parrafo4=new Paragraph("Tel. 5572692900");
            parrafo4.setAlignment(Element.ALIGN_LEFT);
            documento.add(parrafo1);
            documento.add(parrafo2);
            documento.add(parrafo3);
            documento.add(parrafo4);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            agregarTablaDos();
            documento.close();
            String ruta=System.getProperty("user.dir")+("/src/facturas/")+("/FacturaPapeleria"+Ventas.idVenta+".pdf");
            abrirPdfConEncabezado(ruta);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Error al crear el documento");
        } catch (DocumentException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrirPdfConEncabezado(String ruta){
        File path=new File(ruta);
        try {
            
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void agregarTablaDos(){
        try {
            PdfPTable tabla=new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float [] {10,50,15,3,20});
            
            /*PdfPCell celda1=new PdfPCell();
            Paragraph columna1=new Paragraph("Cantidad");
            celda1.addElement(columna1);
            celda1.setHorizontalAlignment(1);*/
            
            PdfPCell celda1=new PdfPCell(new Paragraph("Codigo"));
            celda1.setHorizontalAlignment(1);
            celda1.setBorder(0);
            tabla.addCell(celda1);
            
            PdfPCell celda2=new PdfPCell(new Paragraph("Descripción"));
            celda2.setHorizontalAlignment(1);
            celda2.setBorder(0);
            tabla.addCell(celda2);
            
            PdfPCell celda3=new PdfPCell(new Paragraph("Cantidad"));
            celda3.setHorizontalAlignment(1);
            celda3.setBorder(0);
            tabla.addCell(celda3);
            
            PdfPCell celda4=new PdfPCell(new Paragraph(" "));
            celda4.setHorizontalAlignment(1);
            celda4.setBorder(0);
            tabla.addCell(celda4);
            
            PdfPCell celda5=new PdfPCell(new Paragraph("Importe"));
            celda5.setHorizontalAlignment(Element.ALIGN_LEFT);
            celda5.setBorder(0);
            tabla.addCell(celda5);
           
            
                //Ventas.model.addRow(new Object[filas]);
                for (int x=0;x<Ventas.tablaVenta.getRowCount();x++){
                PdfPCell ce0=new PdfPCell(new Paragraph(Ventas.tablaVenta.getValueAt(filas,0).toString()));
                PdfPCell ce1=new PdfPCell(new Paragraph(Ventas.tablaVenta.getValueAt(filas,1).toString()));
                PdfPCell ce2=new PdfPCell(new Paragraph(Ventas.tablaVenta.getValueAt(filas,3).toString()));
                PdfPCell ce3=new PdfPCell(new Paragraph(Ventas.tablaVenta.getValueAt(filas,4).toString()));
                PdfPCell ce4=new PdfPCell(new Paragraph(Ventas.tablaVenta.getValueAt(filas,2).toString()));
                ce0.setHorizontalAlignment(1);
                ce0.setBorder(0);
                tabla.addCell(ce0);
                ce1.setHorizontalAlignment(1);
                ce1.setBorder(0);
                tabla.addCell(ce1);
                ce2.setHorizontalAlignment(1);
                ce2.setBorder(0);
                tabla.addCell(ce2);
                ce3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                ce3.setBorder(0);
                tabla.addCell(ce3);
                ce4.setHorizontalAlignment(Element.ALIGN_LEFT);
                ce4.setBorder(0);
                tabla.addCell(ce4);
                Ventas.model=(DefaultTableModel)Ventas.tablaVenta.getModel();
                filas++;
        }
        
            
            
            documento.add(tabla);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            Paragraph st=new Paragraph("Subtotal "+"       "+"\t"+"$"+Ventas.txtSubtotal.getText());
            st.getFont().setColor(BaseColor.BLACK);
            st.setAlignment(Element.ALIGN_LEFT);
            documento.add(st);
            
            Paragraph iva=new Paragraph("IVA "+"              "+"\t"+"$"+Ventas.txtIVA.getText());
            iva.getFont().setColor(BaseColor.BLACK);
            iva.setAlignment(Element.ALIGN_LEFT);
            documento.add(iva);
            Paragraph tot=new Paragraph("Total "+"            "+"\t"+"$"+Ventas.txtTotal.getText());
            tot.getFont().setColor(BaseColor.BLACK);
            tot.setAlignment(Element.ALIGN_LEFT);
            documento.add(tot);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            agregarPie();
        } catch (DocumentException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarVenta(){
        model=(DefaultTableModel)Ventas.tablaVenta.getModel();
        model.addRow(new Object[filas]);
        for (int x=0;x<Ventas.tablaVenta.getColumnCount()-1;x++){
            model.getValueAt(filas,0);
            model.getValueAt(filas,1);
            model.getValueAt(filas,3);
            model.getValueAt(filas,4);
            model.getValueAt(filas,2);
            
        }
        filas++;
        
    }
    
    public void agregarPie(){
            try {
                documento.add(new Paragraph("**********************************************************************************************************"));
                Paragraph pie=new Paragraph("Gracias por su compra");
                pie.setAlignment(Element.ALIGN_CENTER);
                documento.add(pie);
                Paragraph pie2=new Paragraph("Visitanos en Facebook e Instagram");
                pie2.setAlignment(Element.ALIGN_CENTER);
                documento.add(pie2);
                Paragraph pie3=new Paragraph("Brindandote calidad y servicios desde 2010");
                pie3.setAlignment(Element.ALIGN_CENTER);
                documento.add(pie3);
                Paragraph pie4=new Paragraph("Compra en nuestras tiendas los 365 dias del año");
                pie4.setAlignment(Element.ALIGN_CENTER);
                documento.add(pie4);
                Image logoPie = Image.getInstance("E:/pape2.jpg");
                logoPie.scaleAbsoluteWidth(300);
                logoPie.scaleAbsoluteHeight(40);
                logoPie.setAlignment(Element.ALIGN_CENTER);
                //logo.setAbsolutePosition(150, 150);
                documento.add(logoPie);
            } catch (DocumentException ex) {
                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void ticketConEncabezado(){
        
        try {
            String r=System.getProperty("user.dir")+("/src/tickets/");
            PdfWriter.getInstance(ticket, new FileOutputStream(r+"ticket"+Ventas.idVenta+".pdf"));
            ticket.open();
            Paragraph parrafo0=new Paragraph("Folio : 00"+Ventas.idVenta);
            parrafo0.setAlignment(Element.ALIGN_CENTER);
            Paragraph parrafo1=new Paragraph("**Papeleria P@peli**");
            parrafo1.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph parrafo2=new Paragraph("CCO-860523-NA");
            parrafo2.setAlignment(Element.ALIGN_CENTER);
            Paragraph parrafo3=new Paragraph("IDICA VER");
            parrafo3.setAlignment(Element.ALIGN_CENTER);
            Paragraph parrafo4=new Paragraph("Edison Nto No.235 Col. Talleres Mty Nuevo Leon C.P. 56480");
            parrafo4.setAlignment(Element.ALIGN_CENTER);
            ticket.add(parrafo0);
            ticket.add(parrafo1);
            ticket.add(parrafo2);
            ticket.add(parrafo3);
            ticket.add(parrafo4);
            ticket.add(new Paragraph("=============================="));
            ticket.add(new Paragraph(" "));
            agregarTablaTicket();
            ticket.close();
            String ruta=System.getProperty("user.dir")+("/src/tickets/")+("/ticket"+Ventas.idVenta+".pdf");
            abrirPdfticket(ruta);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Error al crear el documento");
        } catch (DocumentException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrirPdfticket(String ruta){
        File path=new File(ruta);
        try {
            
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarTablaTicket(){
        try {
            PdfPTable tabla=new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float [] {10,40,15,5,20});
            
            /*PdfPCell celda1=new PdfPCell();
            Paragraph columna1=new Paragraph("Cantidad");
            celda1.addElement(columna1);
            celda1.setHorizontalAlignment(1);*/
            
            PdfPCell celda1=new PdfPCell(new Paragraph("Id"));
            celda1.setHorizontalAlignment(1);
            celda1.setBorder(0);
            tabla.addCell(celda1);
            
            PdfPCell celda2=new PdfPCell(new Paragraph("Descripción"));
            celda2.setHorizontalAlignment(1);
            celda2.setBorder(0);
            tabla.addCell(celda2);
            
            PdfPCell celda3=new PdfPCell(new Paragraph("Cantidad"));
            celda3.setHorizontalAlignment(1);
            celda3.setBorder(0);
            tabla.addCell(celda3);
            
            PdfPCell celda4=new PdfPCell(new Paragraph(" "));
            celda4.setHorizontalAlignment(1);
            celda4.setBorder(0);
            tabla.addCell(celda4);
            
            PdfPCell celda5=new PdfPCell(new Paragraph("Importe"));
            celda5.setHorizontalAlignment(1);
            celda5.setBorder(0);
            tabla.addCell(celda5);
            
            
            for (int x=0;x<Ventas.tablaVenta.getRowCount();x++){
                PdfPCell ce0=new PdfPCell(new Paragraph(Ventas.tablaVenta.getValueAt(filas,0).toString()));
                PdfPCell ce1=new PdfPCell(new Paragraph(Ventas.tablaVenta.getValueAt(filas,1).toString()));
                PdfPCell ce2=new PdfPCell(new Paragraph(Ventas.tablaVenta.getValueAt(filas,3).toString()));
                PdfPCell ce3=new PdfPCell(new Paragraph(Ventas.tablaVenta.getValueAt(filas,4).toString()));
                PdfPCell ce4=new PdfPCell(new Paragraph(Ventas.tablaVenta.getValueAt(filas,2).toString()));
                ce0.setHorizontalAlignment(1);
                ce0.setBorder(0);
                tabla.addCell(ce0);
                ce1.setHorizontalAlignment(1);
                ce1.setBorder(0);
                tabla.addCell(ce1);
                ce2.setHorizontalAlignment(1);
                ce2.setBorder(0);
                tabla.addCell(ce2);
                ce3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                ce3.setBorder(0);
                tabla.addCell(ce3);
                ce4.setHorizontalAlignment(Element.ALIGN_LEFT);
                ce4.setBorder(0);
                tabla.addCell(ce4);
                Ventas.model=(DefaultTableModel)Ventas.tablaVenta.getModel();
                filas++;
        }
            
            ticket.add(tabla);
            ticket.add(new Paragraph(" "));
            ticket.add(new Paragraph(" "));
            ticket.add(new Paragraph(" "));
            Paragraph st=new Paragraph("Subtotal "+"       "+"\t"+"$"+Ventas.txtSubtotal.getText());
            st.getFont().setColor(BaseColor.BLACK);
            st.setAlignment(Element.ALIGN_LEFT);
            ticket.add(st);
            
            Paragraph iva=new Paragraph("IVA "+"              "+"\t"+"$"+Ventas.txtIVA.getText());
            iva.getFont().setColor(BaseColor.BLACK);
            iva.setAlignment(Element.ALIGN_LEFT);
            ticket.add(iva);
            Paragraph tot=new Paragraph("Total "+"            "+"\t"+"$"+Ventas.txtTotal.getText());
            tot.getFont().setColor(BaseColor.BLACK);
            tot.setAlignment(Element.ALIGN_LEFT);
            ticket.add(tot);
            agregarPieTicket();
        } catch (DocumentException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void agregarPieTicket(){
            try {
                ticket.add(new Paragraph("=============================="));
                Paragraph pieT=new Paragraph("Gracias por su compra");
                pieT.setAlignment(Element.ALIGN_CENTER);
                ticket.add(pieT);
                Paragraph pieT2=new Paragraph("Visitanos en Facebook e Instagram");
                pieT2.setAlignment(Element.ALIGN_CENTER);
                ticket.add(pieT2);
                Paragraph pieT4=new Paragraph("Compra en nuestras tiendas los 365 dias del año");
                pieT4.setAlignment(Element.ALIGN_CENTER);
                ticket.add(pieT4);
            } catch (DocumentException ex) {
                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
   
    
    
}
