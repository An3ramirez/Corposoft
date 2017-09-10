/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controlador.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;
/**
 *
 * @author an3-r
 */
public class ConsultasClients extends Conexion{
    Metodos MT=new Metodos();
    
    public boolean Registrar(Clients Cli){
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="insert into clients (id, internal_company_id, external_company_id, "
        + "type_document_id, client_type_id, first_name, second_name, first_last_name, "
        + "second_last_name, gender, document, email, address, salary, city_id, eps_id, "
        + "risk_id, pension_entity_id, compensation_box_id, funeral_insurance_id, "
        + "admission_date, birth_date, observations, discount, status_id) "
        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,0,2)";
        
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
        
         psta.setInt(1, Cli.getId());
         psta.setInt(2, Cli.getInternal_company_id());
         psta.setInt(3, Cli.getExternal_company_id());
         psta.setInt(4, Cli.getType_document_id());
         psta.setInt(5, Cli.getClient_type_id());
         psta.setString(6, Cli.getFirst_name());
         psta.setString(7, Cli.getSecond_name());
         psta.setString(8, Cli.getFirst_last_name());
         psta.setString(9, Cli.getSecond_last_name());
         psta.setString(10, Cli.getGender());
         psta.setString(11, Cli.getDocument());
         psta.setString(12, Cli.getEmail());
         psta.setString(13, Cli.getAddress());
         psta.setDouble(14, Cli.getSalary());
         psta.setInt(15, Cli.getCity_id());
         psta.setInt(16, Cli.getEps_id());
         psta.setInt(17, Cli.getRisk_id());
         psta.setInt(18, Cli.getPension_entity_id());
         psta.setInt(19, Cli.getCompensation_box_id());
         psta.setInt(20, Cli.getFuneral_insurance_id());
         
         java.util.Date date = Cli.getBirth_date();
             long d = date.getTime();
             java.sql.Date fecha = new java.sql.Date(d);
         
         psta.setDate(21, fecha);
         psta.setString(22, Cli.getObservations());
         
         
         psta.execute();
         return true;
         
        } catch (Exception e) {
            System.out.println("Error Registrar Clients "+e);
            MT.saveErrors("Error Registrar Clients "+e);
            return false;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion Registrar Clients "+e);
            }
        }
    }
    
    public boolean Modificar(Clients Cli){
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="UPDATE clients SET internal_company_id=?, external_company_id=?,"
                + "type_document_id=?, client_type_id=?, first_name=?, second_name=?, first_last_name=?, second_last_name=?,"
                + "gender=?, document=?, email=?, address=?, salary=?, city_id=?, eps_id=?, risk_id=?,"
                + "pension_entity_id=?, compensation_box_id=?, funeral_insurance_id=?, Birth_date=?, observations=?, status_id=? WHERE id=?";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
         
         psta.setInt(1, Cli.getInternal_company_id());
         psta.setInt(2, Cli.getExternal_company_id());
         psta.setInt(3, Cli.getType_document_id());
         psta.setInt(4, Cli.getClient_type_id());
         psta.setString(5, Cli.getFirst_name());
         psta.setString(6, Cli.getSecond_name());
         psta.setString(7, Cli.getFirst_last_name());
         psta.setString(8, Cli.getSecond_last_name());
         psta.setString(9, Cli.getGender());
         psta.setString(10, Cli.getDocument());
         psta.setString(11, Cli.getEmail());
         psta.setString(12, Cli.getAddress());
         psta.setDouble(13, Cli.getSalary());
         psta.setInt(14, Cli.getCity_id());
         psta.setInt(15, Cli.getEps_id());
         psta.setInt(16, Cli.getRisk_id());
         psta.setInt(17, Cli.getPension_entity_id());
         psta.setInt(18, Cli.getCompensation_box_id());
         psta.setInt(19, Cli.getFuneral_insurance_id());
         java.util.Date date = Cli.getBirth_date();
             long d = date.getTime();
             java.sql.Date fecha = new java.sql.Date(d);
         
         psta.setDate(20, fecha);
         psta.setString(21, Cli.getObservations());
         psta.setInt(22, Cli.getStatus_id());
         psta.setInt(23, Cli.getId());
         psta.execute();
         
         return true;
         
        } catch (Exception e) {
            System.out.println("Error update Clients "+e);
            MT.saveErrors("Error update Clients "+e);
            return false;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion update Clients "+e);
            }
        }
    }
    
    public boolean Buscar(Clients Cli){
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="SELECT internal_company_id, external_company_id,"
                + "type_document_id, client_type_id, first_name, second_name, first_last_name, second_last_name,"
                + "gender, document, email, address, salary, city_id, eps_id, risk_id,"
                + "pension_entity_id, compensation_box_id, funeral_insurance_id, Birth_date, observations, status_id FROM Clients WHERE id=?";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
        ResultSet rs = null;
         
         psta.setInt(1, Cli.getId());
         rs = psta.executeQuery();
         
         if(rs.next()){
             
             Cli.setInternal_company_id(Integer.parseInt(rs.getString("internal_company_id")));
             Cli.setExternal_company_id(Integer.parseInt(rs.getString("external_company_id")));
             Cli.setType_document_id(Integer.parseInt(rs.getString("type_document_id")));
             Cli.setClient_type_id(Integer.parseInt(rs.getString("client_type_id")));
             Cli.setFirst_name(rs.getString("first_name"));
             Cli.setSecond_name(rs.getString("second_name"));
             Cli.setFirst_last_name(rs.getString("first_last_name"));
             Cli.setSecond_last_name(rs.getString("second_last_name"));
             Cli.setGender(rs.getString("gender"));
             Cli.setDocument(rs.getString("document"));
             Cli.setEmail(rs.getString("email"));
             Cli.setAddress(rs.getString("address"));
             Cli.setSalary(Double.parseDouble(rs.getString("salary")));
             Cli.setCity_id(Integer.parseInt(rs.getString("city_id")));
             Cli.setEps_id(Integer.parseInt(rs.getString("eps_id")));
             Cli.setRisk_id(Integer.parseInt(rs.getString("risk_id")));
             Cli.setPension_entity_id(Integer.parseInt(rs.getString("pension_entity_id")));
             Cli.setCompensation_box_id(Integer.parseInt(rs.getString("compensation_box_id")));
             Cli.setFuneral_insurance_id(Integer.parseInt(rs.getString("funeral_insurance_id")));
             Cli.setBirth_date(rs.getDate("birth_date"));
             Cli.setObservations(rs.getString("observations"));
             Cli.setStatus_id(Integer.parseInt(rs.getString("status_id")));
             return true;
         }
         return false;
         
        } catch (Exception e) {
            System.out.println("Error consulta Clients "+e);
            MT.saveErrors("Error consulta Clients "+e);
            return false;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion consulta Clients "+e);
            }
        }
    }
    
    
    
}
