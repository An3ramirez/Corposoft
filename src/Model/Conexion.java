package Model;
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
    
    static String bd="mysql";
    static String login="root";
    static String password="4529";
    static String url="jdbc:mysql://localhost:3306/dbcorposoft";
    
    
    public static Connection Enlace(Connection conn)throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url,login,password);
            
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada o conexion fallida");
            JOptionPane.showMessageDialog(null,"Error al conetar con la base de datos", "Error conexion",JOptionPane.ERROR_MESSAGE);
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
        rs=st.executeQuery("select * from users");
        return rs;
    }
    
}
