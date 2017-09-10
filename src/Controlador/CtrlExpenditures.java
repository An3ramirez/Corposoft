/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Model.Expenditures;
import Model.ConsultasExpenditures;
import Vista.Egreso;
import java.awt.event.*;
import javax.swing.Action;
import javax.swing.JOptionPane;

/**
 *
 * @author an3-r
 */
public class CtrlExpenditures implements ActionListener{
    
    private Expenditures mod;
    private ConsultasExpenditures modC;
    private Egreso frm;
    Metodos idAutomaticos=new Metodos();
    
    public CtrlExpenditures(Expenditures mod, ConsultasExpenditures modC, Egreso frm){
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btn_guardar.addActionListener(this);
    }
    
    public void Iniciar(){
        frm.setTitle("Registro Egreso");
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == frm.btn_guardar){
            mod.setId(Integer.parseInt(idAutomaticos.IdAutomaticos("id", "Expenditures")));
            mod.setTitle(frm.txtTitulo.getText());
            mod.setDetail(frm.txtDetalle.getText());
            mod.setValue(Double.parseDouble(frm.txtValor.getText()));
            mod.setUsers_id(Integer.parseInt(String.valueOf(DataLogin.getSoyUnico().getId())));
            
            if(modC.registrar(mod)){
                JOptionPane.showMessageDialog(null,"Se guardo la informacion", "Exito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null,"Hubo un error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
                    }
    }
    
    public void limpiar(){
        frm.txtTitulo.setText(null);
        frm.txtDetalle.setText(null);
        frm.txtValor.setText(null);
    }
}
