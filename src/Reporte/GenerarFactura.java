/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author an3-r
 */
public class GenerarFactura {
    static Connection conn=null;
    static Statement st=null;
    static ResultSet rs=null;
    
    public void Factura(){
        
        try {
            JasperReport factura=(JasperReport) JRLoader.loadObject("Reporte.jasper");
            Map parametro=new HashMap();
            parametro.put("id", factura);
            
            JasperPrint P= JasperFillManager.fillReport(factura, parametro, Conexion.Enlace(conn));
            JasperViewer jv=new JasperViewer(P,false);
            jv.setTitle("Factura");
            jv.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al generar el reporte"+ e);
        }
                
    }
    
}
