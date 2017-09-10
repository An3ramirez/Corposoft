/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.*;

/**
 *
 * @author an3-r
 */
public class Internal_companies extends Conexion{
    
    private int id;
    private int city_id;
    private int nit;
    private String name;
    private String comercial_name;
    private String address;
    private String contact_phone;
    private String contact_person;
    private String type_tax_payer;
    private String email_billing;
    private String bill_resolution;
    private String bill_consecutive;
    private String bill_footer;
    private int user_capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComercial_name() {
        return comercial_name;
    }

    public void setComercial_name(String comercial_name) {
        this.comercial_name = comercial_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getType_tax_payer() {
        return type_tax_payer;
    }

    public void setType_tax_payer(String type_tax_payer) {
        this.type_tax_payer = type_tax_payer;
    }

    public String getEmail_billing() {
        return email_billing;
    }

    public void setEmail_billing(String email_billing) {
        this.email_billing = email_billing;
    }

    public String getBill_resolution() {
        return bill_resolution;
    }

    public void setBill_resolution(String bill_resolution) {
        this.bill_resolution = bill_resolution;
    }

    public String getBill_consecutive() {
        return bill_consecutive;
    }

    public void setBill_consecutive(String bill_consecutive) {
        this.bill_consecutive = bill_consecutive;
    }

    public String getBill_footer() {
        return bill_footer;
    }

    public void setBill_footer(String bill_footer) {
        this.bill_footer = bill_footer;
    }

    public int getUser_capacity() {
        return user_capacity;
    }

    public void setUser_capacity(int user_capacity) {
        this.user_capacity = user_capacity;
    }
    
    public String toString() {

        return this.comercial_name;
    }
    
    public Vector<Internal_companies> mostrarNameCompInter(){
        String sql = "";
        sql = "SELECT id, comercial_name FROM internal_companies ORDER BY id ";
        
        Vector<Internal_companies> IntCompVet = new Vector<Internal_companies>();
        Internal_companies IntComp = null;
        
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            IntComp = new Internal_companies();
            IntComp.setId(0);
            IntComp.setComercial_name("Seleccione una Compañia Interna");
            IntCompVet.add(IntComp);

            while (rs.next()) {
                IntComp = new Internal_companies();
                IntComp.setId(rs.getInt("id"));
                IntComp.setComercial_name(rs.getString("comercial_name"));
                IntCompVet.add(IntComp);

            }
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println("Error consultado internal_companies " + ex);
        }
        return IntCompVet;

    }
}
