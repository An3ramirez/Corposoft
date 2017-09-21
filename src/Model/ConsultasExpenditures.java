/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Vista.*;

import Controlador.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author an3-r
 */
public class ConsultasExpenditures extends Conexion {

    public boolean registrar(Expenditures Exp) {
        try {
            conn = Conexion.Enlace(conn);
            String sqlinsertar = "insert into Expenditures values (?,?,?,?,?)";
            PreparedStatement psta = conn.prepareStatement(sqlinsertar);

            psta.setInt(1, Exp.getId());
            psta.setString(2, Exp.getTitle());
            psta.setString(3, Exp.getDetail());
            psta.setDouble(4, Exp.getValue());
            psta.setInt(5, Exp.getUsers_id());
            psta.execute();

            return true;

        } catch (Exception e) {
            System.out.println("Error Registrar Expenditures " + e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion Registrar Expenditures " + e);
            }

        }
    }

    public boolean Actualizar(Expenditures Exp) {
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE expenditures set title='" + Exp.getTitle() + "',detail='" + Exp.getDetail()
                    + "',value=" + Exp.getValue() + ",Users_id="+Exp.getUsers_id()+" where id=" + Exp.getId());

            return true;
            
        } catch (Exception e) {
            System.out.println("Error Actualizar Expenditures " + e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion Actualizar Expenditures " + e);
            }
        }
    }

    public ArrayList<Expenditures> listExpenditures() {
        ArrayList listaExpenditures = new ArrayList();
        Expenditures expenditures;

        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM expenditures");

            while (rs.next()) {
                expenditures = new Expenditures();
                expenditures.setId(rs.getInt(1));
                expenditures.setTitle(rs.getString(2));
                expenditures.setDetail(rs.getString(3));
                expenditures.setValue(rs.getDouble(4));
                expenditures.setUsers_id(rs.getInt(5));
                listaExpenditures.add(expenditures);
            }

        } catch (SQLException ex) {
            System.out.println("Error consultando egresos " + ex);
        }
        return listaExpenditures;
    }

}
