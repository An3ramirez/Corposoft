/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controlador.*;
import java.io.*;
import java.sql.*;
/**
 *
 * @author an3-r
 */
public class ConsultasPayroll extends Conexion{
    Metodos MT=new Metodos();
    
    public boolean Registrar(Payroll Pay){
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="insert into Payroll values (?,?,?,now(),?)";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
        
         psta.setInt(1, Pay.getId());
         psta.setString(2, Pay.getNumber());
         psta.setString(3, Pay.getObservations());
         psta.setInt(4, Pay.getClients_id());
         
         psta.execute();
         return true;
         
        } catch (Exception e) {
            System.out.println("Error Registrar Payroll "+e);
            MT.saveErrors("Error Registrar Payroll "+e);
            return false;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion Registrar Payroll "+e);
            }
        }
    }
    
    public boolean Modificar(Payroll Pay){
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="UPDATE Payroll SET number=?, observations=? WHERE id=?";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
         
         psta.setString(1, Pay.getNumber());
         psta.setString(2, Pay.getObservations());
         psta.setInt(3, Pay.getId());
         psta.execute();
         
         return true;
         
        } catch (Exception e) {
            System.out.println("Error update Payroll "+e);
            MT.saveErrors("Error update Payroll "+e);
            return false;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion update Payroll "+e);
            }
        }
    }
    
    public boolean Buscar(Payroll Pay){
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="SELECT number, observations, date_registro, clients_id FROM Payroll WHERE id=?";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
        ResultSet rs = null;
         
         psta.setInt(1, Pay.getId());
         rs = psta.executeQuery();
         
         if(rs.next()){
             
             Pay.setNumber(rs.getString("number"));
             Pay.setObservations("observations");
             Pay.setDate_registro(rs.getString("date_registro"));
             Pay.setClients_id(Integer.parseInt(rs.getString("clients_id")));
             
             return true;
         }
         return false;
         
        } catch (Exception e) {
            System.out.println("Error consulta Payroll "+e);
            MT.saveErrors("Error consulta Payroll "+e);
            return false;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion consulta Payroll "+e);
            }
        }
    }
    
}
