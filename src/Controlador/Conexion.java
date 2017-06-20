package Controlador;
import java.io.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author an3-r
 */
public class Conexion {
    
    static Connection conn=null;
    static Statement st=null;
    static ResultSet rs=null;
    
    static  String bd="XE";
    static String login="System";
    static String password="4529";
    static String url="jdbc:oracle:thin:@localhost:1521:XE";
    
    
    public static Connection Enlace(Connection conn)throws SQLException{
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection(url,login,password);
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada o conexion fallida");
        }
        return conn;
    }
    
    public static Statement sta(Statement st)throws SQLException{
       conn=Enlace(conn);
       st=conn.createStatement();
               return st;
    }
    
    public static ResultSet EnlEst(ResultSet rs)throws SQLException{
        st=sta(st);
        rs=st.executeQuery("select * from login");
        return rs;
    }
    
}
