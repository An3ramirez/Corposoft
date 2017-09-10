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
public class Type_documents extends Conexion{
    
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

    public Vector<Type_documents> mostrarTypeDocument() {
        String sql = "";
        sql = "SELECT id, name FROM type_documents ORDER BY id ";

        Vector<Type_documents> datosVect = new Vector<Type_documents>();
        Type_documents dat = null;

        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            dat = new Type_documents();
            dat.setId(0);
            dat.setName("Seleccione un Tipo de Documento");
            datosVect.add(dat);

            while (rs.next()) {
                dat = new Type_documents();
                dat.setId(rs.getInt("id"));
                dat.setName(rs.getString("name"));
                datosVect.add(dat);

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado type_document " + ex);
        }
        return datosVect;

    }
    
}
