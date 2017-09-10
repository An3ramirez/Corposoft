/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import javax.swing.*;

/**
 *
 * @author an3-r
 */
public class DataLogin {
    private int id;
    private int internal_company_id;
    private int role_id;
    private String first_name;
    private String second_name;
    private String first_last_name;
    private String second_last_name;
    private String gender;
    private int document_type_id;
    private int document;
    private String contact_phone;
    private String status;
    private String password;
    private String username;
    private String birth_date;
    private String remember_token;
    private static DataLogin UserLogin;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private DataLogin(int idCli, int InterComp, int rolid, String firsName, String SecondName,
                       String apellido1, String apellido2, String gender, int documentTypeid, 
                       int numDocument, String contactPhone, String status, String pass, String UserName, 
                       String birthDate, String remenberToken) {
        this.id = idCli;
        this.internal_company_id = InterComp;
        this.role_id = rolid;
        this.first_name = firsName;
        this.second_name = SecondName;
        this.first_last_name = apellido1;
        this.second_last_name = apellido2;
        this.gender = gender;
        this.document_type_id = documentTypeid;
        this.document =  numDocument;
        this.contact_phone = contactPhone;
        this.status = status;
        this.password = pass;
        this.username = UserName;
        this.birth_date = birthDate;
        this.remember_token = remenberToken;
        
    }
    
   

    public static DataLogin getSingletonInstance(int idCli, int InterComp, int rolid, String firsName, 
            String SecondName, String apellido1, String apellido2, String gender, int documentTypeid, 
            int numDocument, String contactPhone, String status, String pass, String UserName, 
                       String birthDate, String remenberToken) {
        
            UserLogin = new DataLogin(idCli, InterComp, rolid, firsName, SecondName, apellido1, 
                                    apellido2, gender, documentTypeid, numDocument, contactPhone, 
                                    status, pass, UserName, birthDate, remenberToken);
       
        
        return UserLogin;
    }

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

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
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

    public int getDocument_type_id() {
        return document_type_id;
    }

    public void setDocument_type_id(int document_type_id) {
        this.document_type_id = document_type_id;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

   

    public static DataLogin getSoyUnico() {
        return UserLogin;
    }

    public static void setSoyUnico(DataLogin soyUnico) {
        DataLogin.UserLogin = soyUnico;
    }

   
}
