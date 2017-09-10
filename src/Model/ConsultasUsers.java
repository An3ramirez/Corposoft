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
public class ConsultasUsers extends Conexion{
    
    public boolean Registrar(Users Usr){
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="insert into Users values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
        
         psta.setInt(1, Usr.getId());
         psta.setInt(2, Usr.getInternal_company_id());
         psta.setInt(3, Usr.getRole_id());
         psta.setString(4, Usr.getFirst_name());
         psta.setString(5, Usr.getSecond_name());
         psta.setString(6, Usr.getFirst_last_name());
         psta.setString(7, Usr.getSecond_last_name());
         psta.setString(8, Usr.getGender());
         psta.setInt(9, Usr.getDocument_type_id());
         psta.setInt(10, Usr.getDocument());
         psta.setString(11, Usr.getContact_phone());
         psta.setString(12, Usr.getStatus());
         psta.setString(13, Usr.getPassword());
         psta.setString(14, Usr.getUsername());
         psta.setString(15, Usr.getEmail());
         psta.setString(16, Usr.getBirth_date());
         psta.setString(17, Usr.getRemember_token());
         
         psta.execute();
         psta.close();
         return true;
         
        } catch (Exception e) {
            System.out.println("Error Registrar Users "+e);
            return false;
        }
    }
    
    public boolean Modificar(Users Usr){
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="UPDATE Users SET internal_company_id=?, role_id=?,"
                + "first_name=?, second_name=?, first_last_name=?, second_last_name=?,"
                + "gender=?, document_type_id=?, document=?, contact_phone=?, status=?,"
                + "password=?, username=?, email=?, birth_date=?, remember_token=? WHERE id=?";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
        
         
         psta.setInt(1, Usr.getInternal_company_id());
         psta.setInt(2, Usr.getRole_id());
         psta.setString(3, Usr.getFirst_name());
         psta.setString(4, Usr.getSecond_name());
         psta.setString(5, Usr.getFirst_last_name());
         psta.setString(6, Usr.getSecond_last_name());
         psta.setString(7, Usr.getGender());
         psta.setInt(8, Usr.getDocument_type_id());
         psta.setInt(9, Usr.getDocument());
         psta.setString(10, Usr.getContact_phone());
         psta.setString(11, Usr.getStatus());
         psta.setString(12, Usr.getPassword());
         psta.setString(13, Usr.getUsername());
         psta.setString(14, Usr.getEmail());
         psta.setString(15, Usr.getBirth_date());
         psta.setString(16, Usr.getRemember_token());
         psta.setInt(17, Usr.getId());
         psta.execute();
         
         return true;
         
        } catch (Exception e) {
            System.out.println("Error update Users "+e);
            return false;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion update Users "+e);
            }
        }
    }
    
    public boolean Buscar(Users Usr){
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="SELECT * FROM Users WHERE id=?";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
        ResultSet rs = null;
         
         psta.setInt(1, Usr.getId());
         rs = psta.executeQuery();
         
         if(rs.next()){
             Usr.setId(Integer.parseInt(rs.getString("id")));
             Usr.setInternal_company_id(Integer.parseInt(rs.getString("internal_company_id")));
             Usr.setRole_id(Integer.parseInt(rs.getString("role_id")));
             Usr.setFirst_name(rs.getString("first_name"));
             Usr.setSecond_name(rs.getString("second_name"));
             Usr.setFirst_last_name(rs.getString("first_last_name"));
             Usr.setSecond_last_name(rs.getString("second_last_name"));
             Usr.setGender(rs.getString("gender"));
             Usr.setDocument_type_id(Integer.parseInt(rs.getString("document_type_id")));
             Usr.setDocument(Integer.parseInt(rs.getString("document")));
             Usr.setContact_phone(rs.getString("contact_phone"));
             Usr.setStatus(rs.getString("status"));
             Usr.setPassword(rs.getString("password"));
             Usr.setUsername(rs.getString("username"));
             Usr.setEmail(rs.getString("email"));
             Usr.setBirth_date(rs.getString("birth_date"));
             Usr.setRemember_token(rs.getString("remember_token"));
             
             return true;
         }
         return false;
         
        } catch (Exception e) {
            System.out.println("Error consulta Users "+e);
            return false;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion consulta Users "+e);
            }
        }
    }
    
    public boolean BuscarLogin(Users Usr){
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="SELECT * FROM Users WHERE username=?";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
        ResultSet rs = null;
         
         psta.setString(1, Usr.getUsername());
         rs = psta.executeQuery();
         
         if(rs.next()){
             Usr.setId(Integer.parseInt(rs.getString("id")));
             Usr.setInternal_company_id(Integer.parseInt(rs.getString("internal_company_id")));
             Usr.setRole_id(Integer.parseInt(rs.getString("role_id")));
             Usr.setFirst_name(rs.getString("first_name"));
             Usr.setSecond_name(rs.getString("second_name"));
             Usr.setFirst_last_name(rs.getString("first_last_name"));
             Usr.setSecond_last_name(rs.getString("second_last_name"));
             Usr.setGender(rs.getString("gender"));
             Usr.setDocument_type_id(Integer.parseInt(rs.getString("document_type_id")));
             Usr.setDocument(Integer.parseInt(rs.getString("document")));
             Usr.setContact_phone(rs.getString("contact_phone"));
             Usr.setStatus(rs.getString("status"));
             Usr.setPassword(rs.getString("password"));
             Usr.setUsername(rs.getString("username"));
             Usr.setEmail(rs.getString("email"));
             Usr.setBirth_date(rs.getString("birth_date"));
             Usr.setRemember_token(rs.getString("remember_token"));
             
             
             return true;
         }
         return false;
         
        } catch (Exception e) {
            System.out.println("Error Consulta Users Model "+e);
            return false;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion consulta Users "+e);
            }
        }
    }
    
    
    
}
