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
public class Pension_entities extends Conexion{
    
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
    
    public Vector<Pension_entities> mostrarPension_entities(){
        String sql="";
        sql = "SELECT id, name, valor FROM Pension_entities ORDER BY id ";
        
        Vector<Pension_entities> datosVect = new Vector<Pension_entities>();
        Pension_entities dat = null;
        
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            dat = new Pension_entities();
            dat.setId(0);
            dat.setName("Seleccione Pension");
            datosVect.add(dat);

            while (rs.next()) {
                dat = new Pension_entities();
                dat.setId(rs.getInt("id"));
                dat.setName(rs.getString("name"));
                datosVect.add(dat);

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado Pension_entities " + ex);
        }
        return datosVect;

    }
}
