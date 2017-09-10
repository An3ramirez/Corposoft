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
public class Funeral_insurance extends Conexion{
    
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
    
    public Vector<Funeral_insurance> mostrarFuneral_insurance(){
        String sql="";
        sql = "SELECT id, name, valor FROM Funeral_insurance ORDER BY id ";
        
        Vector<Funeral_insurance> datosVect = new Vector<Funeral_insurance>();
        Funeral_insurance dat = null;
        
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            dat = new Funeral_insurance();
            dat.setId(0);
            dat.setName("Seleccione un Servicio Funerario");
            datosVect.add(dat);

            while (rs.next()) {
                dat = new Funeral_insurance();
                dat.setId(rs.getInt("id"));
                dat.setName(rs.getString("name"));
                datosVect.add(dat);

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado Funeral_insurance " + ex);
        }
        return datosVect;

    }
}
