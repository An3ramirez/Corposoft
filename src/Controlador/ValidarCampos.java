/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;

/**
 *
 * @author an3-r
 */
public class ValidarCampos {
    
    public void validarSoloLetras(KeyEvent e){
                char c = e.getKeyChar();
                if(Character.isDigit(c)){
                    e.consume();
                    
                    DesktopNotify.showDesktopMessage("Solo letras", "Este campo ha sido configurado para aceptar solo caracteres de tipo TEXTO", DesktopNotify.ERROR, 5000L);
                }
        
    }
    
    public void validarSoloNumeros(KeyEvent e){
                char c = e.getKeyChar();
                if(c!=KeyEvent.VK_BACK_SPACE){
                if(!Character.isDigit(c)){
                    e.consume();
                    
                    DesktopNotify.showDesktopMessage("Solo numeros", "Este campo ha sido configurado para aceptar solo caracteres de tipo NUMERICO", DesktopNotify.ERROR, 5000L);
                }
                 }
        
    }
    
    public void limitarCaracteres(JTextField campo, int cantidad){
        campo.addKeyListener(new KeyAdapter() {
            public void KeyTyped(KeyEvent e){
                char c = e.getKeyChar();
                int tam = campo.getText().length();
                if(tam>=cantidad){
                    e.consume();
                    DesktopNotify.showDesktopMessage("Error límite de caracteres", "En este campo solo se permiten "+cantidad+" caracteres como máximo ", DesktopNotify.ERROR, 5000L);
                }
            }
        
        });
    }
}
