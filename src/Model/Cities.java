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
import java.util.Vector;

/**
 *
 * @author an3-r
 */
public class Cities {
    
    private int id;
    private int country_id;
    private String name;
    private int city_code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCity_code() {
        return city_code;
    }

    public void setCity_code(int city_code) {
        this.city_code = city_code;
    }
    
    public String toString() {

        return this.name;
    }
    
    public Vector<Cities> mostrarCities(Integer idDep) {
        String sql = "";
        sql = "SELECT id, name, city_code FROM cities WHERE department_id="+idDep+"  ORDER BY id";

        Vector<Cities> datos = new Vector<Cities>();
        Cities dat = null;

        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            dat = new Cities();
            dat.setId(0);
            dat.setName("Seleccione una Ciudad");
            datos.add(dat);

            while (rs.next()) {
                dat = new Cities();
                dat.setId(rs.getInt("id"));
                dat.setName(rs.getString("name"));
                datos.add(dat);

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado las ciudades " + ex);
        }
        return datos;

    }
    
}
