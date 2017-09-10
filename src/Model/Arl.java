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
public class Arl extends Conexion{
    
    private int id;
    private String nivel;
    private int name_id;
    private double valor;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName_id() {
        return name_id;
    }

    public void setName_id(int name_id) {
        this.name_id = name_id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String toString() {

        return this.nivel;
    }
    
    
    public Vector<Arl> mostrarArl(Integer idArl) {
        String sql = "";
        sql = "SELECT id, nivel FROM arl WHERE name_id="+idArl+"  ORDER BY id";

        Vector<Arl> datos = new Vector<Arl>();
        Arl dat = null;

        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            dat = new Arl();
            dat.setId(0);
            dat.setNivel("Seleccione Nivel Arl");
            datos.add(dat);

            while (rs.next()) {
                dat = new Arl();
                dat.setId(rs.getInt("id"));
                dat.setNivel(rs.getString("nivel"));
                datos.add(dat);

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado arlNivel " + ex);
        }
        return datos;

    }
}
