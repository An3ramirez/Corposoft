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
public class Prividerarl extends Conexion{
    
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString(){
        return this.name;
    }
    
    public Vector<Prividerarl> mostrarArlName(){
        String sql="";
        sql = "SELECT id, name FROM prividerarl ORDER BY id ";
        
        Vector<Prividerarl> datosVect = new Vector<Prividerarl>();
        Prividerarl dat = null;
        
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            dat = new Prividerarl();
            dat.setId(0);
            dat.setName("Seleccione Arl");
            datosVect.add(dat);

            while (rs.next()) {
                dat = new Prividerarl();
                dat.setId(rs.getInt("id"));
                dat.setName(rs.getString("name"));
                datosVect.add(dat);

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado ArlPrivider " + ex);
        }
        return datosVect;

    }
    
}
