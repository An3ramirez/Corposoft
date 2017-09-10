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
public class Compensation_box extends Conexion{
    
    private int id;
    private String name;
    private double valor;

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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String toString(){
        return this.name;
    }
    
    public Vector<Compensation_box> mostrarCompBox(){
        
        String sql="";
        sql = "SELECT id, name, valor FROM compensation_box ORDER BY id ";
        
        Vector<Compensation_box> datosVect = new Vector<Compensation_box>();
        Compensation_box dat = null;
        
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            dat = new Compensation_box();
            dat.setId(0);
            dat.setName("Seleccione Caja de compensacion");
            datosVect.add(dat);

            while (rs.next()) {
                dat = new Compensation_box();
                dat.setId(rs.getInt("id"));
                dat.setName(rs.getString("name"));
                datosVect.add(dat);

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado Compensation_box " + ex);
        }
        return datosVect;
    }
    
}
