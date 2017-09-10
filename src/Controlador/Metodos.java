/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Model.Conexion;
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
        
        //Primero se valida si hay datos
        try {
            conn=Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT count("+id+") FROM "+Tabla+"");
            rs.next();
            
            Secuencia=rs.getString(1);
            
            
        } catch (Exception e) {
            System.err.println("Error consultando si hay datos en tabla"+e);
        }
        
        if(Integer.parseInt(Secuencia)==0){
            NRegistros++;
            idTable=Integer.toString(NRegistros);
            
        }else{
            try {
            conn=Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT max("+id+") FROM "+Tabla+"");
            rs.next();
                 
            
            
            Secuencia=rs.getString(1);
            
            
        } catch (Exception e) {
            System.out.println("Error consultando el valor maximo de la tabla "+Tabla+" \n"+e);
        }
            NRegistros=Integer.parseInt(Secuencia);
            
            NRegistros++;
            idTable=Integer.toString(NRegistros);
        }
        
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
            System.err.println("Error en metodo IdTable "+e);
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
    
     public int InicioAplication(){
         int cnt=0;
        
        try {
            conn=Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT count(id) FROM users ");
            rs.next();
               
            cnt=Integer.parseInt(rs.getString(1));
            
            
        } catch (Exception e) {
            System.err.println("Error consultando la tabla users "+e);
        } 
        return cnt;
        
    }
     
     public int Validacion(){
         int validacion=0;
        try {
            conn=Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT count(validacion) FROM system ");
            rs.next();
               
            validacion=Integer.parseInt(rs.getString(1));
            
            
        } catch (Exception e) {
            System.err.println("Error consultando la tabla system para el serial "+e);
        } 
        return validacion;
     }
     
     public int SerialDias(){
         int diasRes=0;
         try {
            conn=Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT TO_DAYS( fechaexpiracion) - TO_DAYS(sysdate() ) AS dias\n" +
                                            "FROM system ");
            rs.next();
               
            diasRes=Integer.parseInt(rs.getString(1));
            
            
        } catch (Exception e) {
            System.err.println("Error consultando la tabla system para el serial "+e);
        }
         
         return diasRes;
     }
     
     public String saveErrors(String Message){
         String id=IdAutomaticos("id", "errors");
         Captura Cap = new Captura();
         try {
             conn=Conexion.Enlace(conn);
             String sqlInsert="insert into errors values (?,?,NOW())";
             PreparedStatement psta=conn.prepareStatement(sqlInsert);
             
             psta.setString(1, id);
             psta.setString(2, Message);
             psta.execute();
             
         } catch (Exception e) {
             System.err.println("Insercion error "+e);
         }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion Insercion error "+e);
            }
        }
         //envio del correo
         Correo c=new Correo();
        c.setContrasena("lkanfgsrldgvplyz");
        c.setUsuarioCorreo("Corposoft10@gmail.com");
        c.setDestino("CorposoftErrors@gmail.com");
        c.setAsunto(id+" codigo error");
        c.setMensaje("Se genero un error en la aplicación CORPOSOFT. Codigo de registro "+id+"\n\n\n"
                + Message);
        Cap.Capturar();
        c.setNombreArchivo("capturaError.png");
        c.setRutaArchivo("C:/CapturaCorposoft/capturaError.png");
        
        ControllerCorreo co=new ControllerCorreo();
        co.enviarCorreo(c);
         //fin envio correo
         id="El codigo de generacion del error es "+id;
         return id;
     }
     
     public String redondeoPesos(String val){
         int tam;
         String L1,L2,L3,L4,L5,L6,fin,cadena;
         fin="";
         cadena=val;
         int N1=0;
         tam=val.length();
             L1=val.substring(tam-1, tam);
             L2=val.substring(tam-2, tam-1);
             L3=val.substring(tam-3, tam-2);
             L4=val.substring(tam-4, tam-3);
             //L5=val.substring(tam-5, tam-4);
         System.out.println("L1-"+L1+" L2-"+L2+" L3-"+L3+" L4-"+L4);
         
         if(Integer.parseInt(L1)!=0){
             System.out.println("--1--");
              if(Integer.parseInt(L3)!=9){
                  System.out.println("--2--");
                  N1=Integer.parseInt(L3);
                  N1++;
                  fin = cadena.substring(0,tam-3)+Integer.toString(N1)+"00";
              }else{
                  System.out.println("--3--");
                  if(Integer.parseInt(L4)!=9){
                  N1=Integer.parseInt(L4);
                  N1++;
                  fin = Integer.toString(N1)+"000";
                  if(tam==5)
                  fin = cadena.substring(0,tam-4)+Integer.toString(N1)+"000";
                  if(tam==6)
                  fin = cadena.substring(0,tam-4)+Integer.toString(N1)+"000";    
                  }else{
                      System.out.println("--4--");
                      N1=Integer.parseInt(L4);
                      N1++;
                      fin = Integer.toString(N1)+"0000";
                      L6=val.substring(tam-6, tam-5);
                      if(Integer.parseInt(L6)!=0){
                        N1=Integer.parseInt(L6);
                        N1++;
                        fin = Integer.toString(N1)+"00000";
                        
                     }L5=val.substring(tam-5, tam-4);
                      if(Integer.parseInt(L5)!=9){
                        N1=Integer.parseInt(L5);
                        N1++;
                        fin = cadena.substring(0,tam-5)+Integer.toString(N1)+"0000";
                     }else{
                        N1=Integer.parseInt(L6);
                        N1++;
                        fin = Integer.toString(N1)+"00000";
                      }
                  }
              }
              
             if(Integer.parseInt(L2)!=0){
             System.out.println("--5--");
             if(Integer.parseInt(L3)!=9){
                 System.out.println("--6--");
              N1=Integer.parseInt(L3);
              N1++;
              fin = cadena.substring(0,tam-3)+Integer.toString(N1)+"00";
             }else{
                 System.out.println("--7--");
                 if(Integer.parseInt(L4)!=9){
                     System.out.println("--8--");
                 N1=Integer.parseInt(L4);
                 N1++;
                 fin = Integer.toString(N1)+"000";
                 if(tam==5)
                 fin = cadena.substring(0,tam-4)+Integer.toString(N1)+"000";
                 if(tam==6)
                 fin = cadena.substring(0,tam-4)+Integer.toString(N1)+"000";
                 }else{
                     System.out.println("--9--");
                     L5=val.substring(tam-5, tam-4);
                     N1=Integer.parseInt(L5);
                     N1++;
                     fin = Integer.toString(N1)+"0000";
                     L6=val.substring(tam-6, tam-5);
                     if(Integer.parseInt(L6)!=0){
                        N1=Integer.parseInt(L6);
                        N1++;
                        fin = Integer.toString(N1)+"00000";
                     }L5=val.substring(tam-5, tam-4);
                      if(Integer.parseInt(L5)!=9){
                        N1=Integer.parseInt(L5);
                        N1++;
                        fin = cadena.substring(0,tam-5)+Integer.toString(N1)+"0000";
                     }else{
                        N1=Integer.parseInt(L6);
                        N1++;
                        fin = Integer.toString(N1)+"00000";
                      }
                     
                 }
             }
             }
              
         }else if(Integer.parseInt(L2)!=0){
             System.out.println("--10--");
             if(Integer.parseInt(L3)!=9){
                 System.out.println("--11--");
             N1=Integer.parseInt(L3);
             N1++;
             fin=cadena.substring(0, tam-3)+Integer.toString(N1)+"00";
             }else{
                 System.out.println("--12--");
                 if(Integer.parseInt(L4)!=9){
                     System.out.println("--13--");
                 N1=Integer.parseInt(L4);
                 N1++;
                 fin = Integer.toString(N1)+"000";
                 if(tam==6)
                 fin = cadena.substring(0,tam-4)+Integer.toString(N1)+"000";
                 }else{
                     L5=val.substring(tam-5, tam-4);
                     if(Integer.parseInt(L5)!=9){
                         L5=val.substring(tam-5, tam-4);
                     System.out.println("--14--");
                     N1=Integer.parseInt(L5);
                     N1++;
                     fin = cadena.substring(0, tam-5)+Integer.toString(N1)+"0000";
                     }else{
                     
                         L6=val.substring(tam-6, tam-5);
                         N1=Integer.parseInt(L6);
                         N1++;
                         fin = Integer.toString(N1)+"00000";
                         
                     }
                     
                 }
             }
         }else{
             System.out.println("--15--");
             fin=cadena;
                     
         }
         
      
         return fin;
     }
     
     public String fechaIngreso(String id){
         String fechaIngr="";
        
        try {
            conn=Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT admission_date FROM clients WHERE id="+id+"");
            rs.next();
               
            fechaIngr=rs.getString(1);
            
        } catch (Exception e) {
            System.err.println("Error consultando fecha ingreso "+e);
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion fecha ingreso "+e);
            }
        } 
        return fechaIngr;
     }
    
    public String fechaActual() {
        String fechaAct = "";

        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT CURRENT_DATE");
            rs.next();

            fechaAct = rs.getString(1);

        } catch (Exception e) {
            System.err.println("Error consultando fecha actual " + e);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion fecha actual " + e);
            }
        }
        return fechaAct;
     }
    
    public boolean tienePagos(String id) {
        int pagos = 0;
        boolean Tp=false;

        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(id) FROM payroll WHERE clients_id="+id+"");
            rs.next();

            pagos = Integer.parseInt(rs.getString(1));

        } catch (Exception e) {
            System.err.println("Error consultando fecha actual " + e);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion fecha actual " + e);
            }
        }
        if(pagos>0){
            Tp=false;
        }else{
            Tp=true;
        }
        return Tp;
     }
    
    public int diasVencidos(String document){
        int diaAdm=0;
        int totalDias=0;
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT day(admission_date) FROM clients WHERE document="+document+"");
            rs.next();

            diaAdm = Integer.parseInt(rs.getString(1));

        } catch (Exception e) {
            System.err.println("Error consultando dia de administracion " + e);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion dia de administracion " + e);
            }
        }
        if(diaAdm<30){
            totalDias=31-diaAdm;
        }else{
            totalDias=0;
        }
        return totalDias;
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
