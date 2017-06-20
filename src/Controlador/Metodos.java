/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.Login;
import java.awt.Font;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;



/**
 *
 * @author an3-r
 */
public class Metodos implements Runnable{
    static Connection conn=null;
    static Statement st=null;
    static ResultSet rs=null;
    public String NombreCli;
    
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    
    java.util.Date now=new java.util.Date();
    java.text.SimpleDateFormat sdf=new
    java.text.SimpleDateFormat("dd-MM-yy");
    String fecha="";
    String FechaHora="";
    
    
    public Metodos(){
        fecha=sdf.format(now);
        NombreCli="";
        h1 = new Thread(this);
        h1.start();
}
    
    
    
    public boolean ValidarContrasena(String Contrasena1,String Contrasena2){
        
        boolean Salida=false;
       
        
        
        if(Contrasena1.equals(Contrasena2)){
            Salida=true;
           
        }
        return Salida;
        
    }
    
    public String IdAutomaticos(String id,String Tabla){
        
        int NRegistros=0;
        String Secuencia="";
        String idTable="";
        
        
        try {
            conn=Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT max(cast("+id+" as integer)) FROM "+Tabla+"");
            rs.next();
                 
            
            
            Secuencia=rs.getString(1);
            
            
        } catch (Exception e) {
            System.out.println("Error consultando el valor maximo de la tabla "+Tabla+" \n"+e);
        }
            NRegistros=Integer.parseInt(Secuencia);
            
            NRegistros++;
            idTable=Integer.toString(NRegistros);
            
       
        
        return idTable;
        
        
    }
    
    public String IdTable(String id,String Tabla){
        
        
        String Secuencia="";
        
        
        
        try {
            conn=Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT max("+id+") FROM "+Tabla+"");
            rs.next();
                 
            
            
            Secuencia=rs.getString(1);
            
            
        } catch (Exception e) {
            System.err.println("Error consultando la tabla"+e);
        }
           
        return Secuencia;
        
    }
    
    public String NombreUser(String id,String table){
        String nombre="";
       try {
            conn=Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT nombres FROM "+table+" WHERE numero_documento="+id+"");
            rs.next();
               
            nombre=rs.getString(1);
            
            
        } catch (Exception e) {
            System.err.println("Error consultando la tabla"+e);
        } 
        return nombre;
    }
    
     public void CalcularTotal(){
        
        
        
    }
    
    

    @Override
    public void run() {
         Thread ct= Thread.currentThread();
        
        while(ct==h1){
            calcula();
            FechaHora=fecha+"  "+hora+":"+minutos+":"+segundos+" "+ampm;
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
        
    }
    
     public void calcula() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date fechaHoraActual = new java.util.Date();
        
        calendario.setTime(fechaHoraActual);
        ampm= calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
        
        if(ampm.equals("PM")){
            int h=calendario.get(Calendar.HOUR_OF_DAY)-12;
            hora = h>9?""+h:"0"+h;
        }else{
            hora= calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY); 
        }
        minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }
    
}
