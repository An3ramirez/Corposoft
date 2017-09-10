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
public class ConsultasExpenditures extends Conexion{
    
    public boolean registrar(Expenditures Exp) {
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="insert into Expenditures values (?,?,?,?,?)";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
        
         psta.setInt(1, Exp.getId());
         psta.setString(2, Exp.getTitle());
         psta.setString(3, Exp.getDetail());
         psta.setDouble(4, Exp.getValue());
         psta.setInt(5, Exp.getUsers_id());
         psta.execute();
         
         return true;
         
        } catch (Exception e) {
            System.out.println("Error Registrar Expenditures "+e);
            return false;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion Registrar Expenditures "+e);
            }
        
    }
    }
    
    public boolean Mostrar(Expenditures Exp) {
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="SELECT * FROM Expenditures";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
        ResultSet rs = null;
        
         rs = psta.executeQuery();
         
         while(rs.next()){
             
         }
         
         return true;
         
        } catch (Exception e) {
            System.out.println("Error Registrar Expenditures "+e);
            return false;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion Registrar Expenditures "+e);
            }
        
    }
    }
}
