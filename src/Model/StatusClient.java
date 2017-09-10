/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Conexion.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author an3-r
 */
public class StatusClient {
    
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
    
    public Vector<StatusClient> mostrarEstadoClients(){
        String sql = "";
        sql = "SELECT id, name FROM statusClient  ORDER BY id ";
        
        Vector<StatusClient> datos = new Vector<StatusClient>();
        StatusClient stCli = null;
        
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            stCli = new StatusClient();
            stCli.setId(0);
            stCli.setName("Seleccione un Estado");
            datos.add(stCli);

            while (rs.next()) {
                stCli = new StatusClient();
                stCli.setId(rs.getInt("id"));
                stCli.setName(rs.getString("name"));
                datos.add(stCli);

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado estados clientes " + ex);
        }
        return datos;
    }
    
}
