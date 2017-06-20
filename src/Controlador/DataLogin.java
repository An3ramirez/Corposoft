/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import javax.swing.*;

/**
 *
 * @author an3-r
 */
public class DataLogin {
    private String idClient;
    private JTable clientes;
    private static DataLogin soyUnico;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private DataLogin(String NumeroCli) {
        this.idClient = NumeroCli;
        
    }
    
   

    public static DataLogin getSingletonInstance(String nombre) {
        
            soyUnico = new DataLogin(nombre);
       
        
        return soyUnico;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public static DataLogin getSoyUnico() {
        return soyUnico;
    }

    public static void setSoyUnico(DataLogin soyUnico) {
        DataLogin.soyUnico = soyUnico;
    }

    public void DatosGerente(JTable cliente){
        this.clientes=cliente;
    }
    
    
    
    
}
