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
public class Clients_type extends Conexion{
    
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
    
    public String toString() {

        return this.name;
    }

    public Vector<Clients_type> mostrarClientType() {
        String sql = "";
        sql = "SELECT id, name FROM Clients_type ORDER BY id ";

        Vector<Clients_type> datosVect = new Vector<Clients_type>();
        Clients_type dat = null;

        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            dat = new Clients_type();
            dat.setId(0);
            dat.setName("Seleccione un Tipo de Cliente");
            datosVect.add(dat);

            while (rs.next()) {
                dat = new Clients_type();
                dat.setId(rs.getInt("id"));
                dat.setName(rs.getString("name"));
                datosVect.add(dat);

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado Clients_type " + ex);
        }
        return datosVect;

    }
    
}
