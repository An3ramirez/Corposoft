/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
/**
 *
 * @author an3-r
 */
public class Clients {
    
    private int id;
    private int internal_company_id;
    private int external_company_id;
    private int type_document_id;
    private int client_type_id;
    private String first_name;
    private String second_name;
    private String first_last_name;
    private String second_last_name;
    private String gender;
    private String document;
    private String email;
    private String address;
    private double salary;
    private int city_id;
    private int eps_id;
    private int risk_id;
    private int pension_entity_id;
    private int compensation_box_id;
    private int funeral_insurance_id;
    private String admission_date;
    private String retirement_date;
    private Date birth_date;
    private String observations;
    private int status_id;

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

    public int getExternal_company_id() {
        return external_company_id;
    }

    public void setExternal_company_id(int external_company_id) {
        this.external_company_id = external_company_id;
    }

    public int getType_document_id() {
        return type_document_id;
    }

    public void setType_document_id(int tipe_document_id) {
        this.type_document_id = tipe_document_id;
    }

    public int getClient_type_id() {
        return client_type_id;
    }

    public void setClient_type_id(int client_type_id) {
        this.client_type_id = client_type_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getFirst_last_name() {
        return first_last_name;
    }

    public void setFirst_last_name(String first_last_name) {
        this.first_last_name = first_last_name;
    }

    public String getSecond_last_name() {
        return second_last_name;
    }

    public void setSecond_last_name(String second_last_name) {
        this.second_last_name = second_last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getEps_id() {
        return eps_id;
    }

    public void setEps_id(int eps_id) {
        this.eps_id = eps_id;
    }

    public int getRisk_id() {
        return risk_id;
    }

    public void setRisk_id(int risk_id) {
        this.risk_id = risk_id;
    }

    public int getPension_entity_id() {
        return pension_entity_id;
    }

    public void setPension_entity_id(int pension_entity_id) {
        this.pension_entity_id = pension_entity_id;
    }

    public int getCompensation_box_id() {
        return compensation_box_id;
    }

    public void setCompensation_box_id(int compensation_box_id) {
        this.compensation_box_id = compensation_box_id;
    }

    public int getFuneral_insurance_id() {
        return funeral_insurance_id;
    }

    public void setFuneral_insurance_id(int funeral_ensurance_id) {
        this.funeral_insurance_id = funeral_ensurance_id;
    }

    public String getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(String admission_date) {
        this.admission_date = admission_date;
    }

    public String getRetirement_date() {
        return retirement_date;
    }

    public void setRetirement_date(String retirement_date) {
        this.retirement_date = retirement_date;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
    
    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status) {
        this.status_id = status;
    }
    
    public boolean esEmail(String correo) {
		
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		
        Matcher mather = pattern.matcher(correo);
		
        return mather.find();
		
}
    
}
