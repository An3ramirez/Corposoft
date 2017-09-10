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
public class External_companies extends Conexion{
    
    private int id;
    private int internal_company_id;
    private int city_id;
    private String name;
    private String comercial_name;
    private int nit;
    private String type_tax_payer;
    private String address;
    private String contact_phone;
    private String contact_person;
    private String contact_person_bill;
    private String contact_phone_bill;
    private String email_billing;
    private int bill_accumulate;
    private int bill_accumulate_number;
    private String client_specifications;
    private String external_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInternal_company_id() {
        return internal_company_id;
    }

    public void setInternal_company_id(int internal_company_id) {
        this.internal_company_id = internal_company_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
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

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getType_tax_payer() {
        return type_tax_payer;
    }

    public void setType_tax_payer(String type_tax_payer) {
        this.type_tax_payer = type_tax_payer;
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

    public String getContact_person_bill() {
        return contact_person_bill;
    }

    public void setContact_person_bill(String contact_person_bill) {
        this.contact_person_bill = contact_person_bill;
    }

    public String getContact_phone_bill() {
        return contact_phone_bill;
    }

    public void setContact_phone_bill(String contact_phone_bill) {
        this.contact_phone_bill = contact_phone_bill;
    }

    public String getEmail_billing() {
        return email_billing;
    }

    public void setEmail_billing(String email_billing) {
        this.email_billing = email_billing;
    }

    public int getBill_accumulate() {
        return bill_accumulate;
    }

    public void setBill_accumulate(int bill_accumulate) {
        this.bill_accumulate = bill_accumulate;
    }

    public int getBill_accumulate_number() {
        return bill_accumulate_number;
    }

    public void setBill_accumulate_number(int bill_accumulate_number) {
        this.bill_accumulate_number = bill_accumulate_number;
    }

    public String getClient_specifications() {
        return client_specifications;
    }

    public void setClient_specifications(String client_specifications) {
        this.client_specifications = client_specifications;
    }

    public String getExternal_type() {
        return external_type;
    }

    public void setExternal_type(String external_type) {
        this.external_type = external_type;
    }

    public String toString() {

        return this.comercial_name;
    }
    
    public Vector<External_companies> mostrarNameCompExter(){
        String sql = "";
        sql = "SELECT id, comercial_name FROM external_companies ORDER BY id ";
        
        Vector<External_companies> ExtCompVet = new Vector<External_companies>();
        External_companies ExtComp = null;
        
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            ExtComp = new External_companies();
            ExtComp.setId(0);
            ExtComp.setComercial_name("Seleccione una Compañia Externa");
            ExtCompVet.add(ExtComp);

            while (rs.next()) {
                ExtComp = new External_companies();
                ExtComp.setId(rs.getInt("id"));
                ExtComp.setComercial_name(rs.getString("comercial_name"));
                ExtCompVet.add(ExtComp);

            }
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println("Error consultado external_companies " + ex);
        }
        return ExtCompVet;

    }
    
}
