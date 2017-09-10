/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Model.*;
import Vista.*;
import java.awt.event.*;
import javax.swing.Action;
import javax.swing.JOptionPane;
/**
 *
 * @author an3-r
 */
public class CtrlPayroll {
    
    public Payroll mod;
    public ConsultasPayroll modC;
    
    public CtrlPayroll(Payroll mod, ConsultasPayroll modC){
        
        this.mod = mod;
        this.modC = modC;
        
    }
    
}
