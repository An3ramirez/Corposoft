/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Model.Conexion;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author an3-r
 */
public class Insert {
    static Connection conn=null;
    static Statement st=null;
    static ResultSet rs=null;
    
    
    
    
    public String Insercion(String[] data, String Table){
        String Mensaje="";
       try {
           // Conexion miconexion = new Conexion();
            conn=Conexion.Enlace(conn);
            String cant="";
            String Datos="";
            for (int i = 0; i < data.length; i++) {
               cant+="?,"; 
            }
            Datos=cant.substring(0, data.length*2-1);
            
            String sqlinsertar="insert into "+Table+" values ("+Datos+")";
            
            PreparedStatement psta=conn.prepareStatement(sqlinsertar);
           
           int c=1;
            for (int i = 0; i < data.length; i++) {
                
                psta.setString(c, data[i]);
                c++;
                
            }
           
           psta.execute();
           psta.close();
           Mensaje="Exito al guardar los datos";
         
        }catch (Exception e){
            
            JOptionPane.showMessageDialog(null,"A ocurrido un error al guardar los datos\nEl error arrojado por el sistema es\n"+e);
        }
       return Mensaje;
        
    }
    
    public void Update(String Table,String[] data){
        try {
            // actualizando tabla
            for(int i=1; i<=data.length; i++){
            PreparedStatement pst = conn.prepareStatement("UPDATE "+Table+" SET "+data[i]+" ");
            pst.executeUpdate();
             }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,""+e);
        }
       
    }
    
    public void Select(){
        String[] data;
        data=new String[18];
        
        try {
        conn=Conexion.Enlace(conn);
         Statement st = conn.createStatement();
         
         ResultSet rs= st.executeQuery("SELECT id FROM cliente WHERE idcliente= ");
         rs.next();
         
        /* TextIdCliente.setText(rs.getString(1));
         TextNombre.setText(rs.getString(2));
         TxtBarrio.setText(rs.getString(3));
         TxtDireccion.setText(rs.getString(4));
         TxtTelefono.setText(rs.getString(5));
         TxtRecomienda.setText(rs.getString(6));
         TextFieldCedula.setText(rs.getString(7));
         */
         
         
        
    } catch (Exception e) {
    }
    }
     
    
}
