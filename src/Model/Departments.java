/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author an3-r
 */
public class Departments extends Conexion {

    private int id;
    private String name;
    private int department_code;

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

    public int getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(int department_code) {
        this.department_code = department_code;
    }

    

    public String toString() {

        return this.name;
    }

    public Vector<Departments> mostrarDepartments() {
        String sql = "";
        sql = "SELECT id, name, department_code FROM departments ORDER BY id ";

        Vector<Departments> datos = new Vector<Departments>();
        Departments dat = null;

        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            dat = new Departments();
            dat.setId(0);
            dat.setName("Seleccione un departamento");
            datos.add(dat);

            while (rs.next()) {
                dat = new Departments();
                dat.setId(rs.getInt("id"));
                dat.setName(rs.getString("name"));
                datos.add(dat);

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado los paises " + ex);
        }
        return datos;

    }
}
