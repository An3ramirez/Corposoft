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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author daniel_granobles
 */
public class CtrlExpenditures implements ActionListener {

    private Expenditures mod;
    private ConsultasExpenditures modC;
    private Egreso frm;
    Metodos idAutomaticos = new Metodos();

    public CtrlExpenditures(Expenditures mod, ConsultasExpenditures modC, Egreso frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btn_guardar.addActionListener(this);
        this.frm.btn_Buscar.addActionListener(this);
        this.frm.btn_editar1.addActionListener(this);
        this.frm.btn_editar.addActionListener(this);
    }

    public void Iniciar() {
        frm.setTitle("Registro Egreso");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btn_guardar) {
            mod.setId(Integer.parseInt(idAutomaticos.IdAutomaticos("id", "Expenditures")));

            if (frm.txtTitulo.getText().equals("") || frm.txtDetalle.getText().equals("") || frm.txtValor.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingresar todos los datos");
            } else {
                mod.setTitle(frm.txtTitulo.getText());
                mod.setDetail(frm.txtDetalle.getText());
                mod.setValue(Double.parseDouble(frm.txtValor.getText()));
                mod.setUsers_id(Integer.parseInt(String.valueOf(DataLogin.getSoyUnico().getId())));

                if (modC.registrar(mod)) {
                    JOptionPane.showMessageDialog(null, "Se guardo la informacion", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == frm.btn_Buscar) {
            frm.jPanel1.setVisible(false);
            frm.jPanel2.setVisible(true);
            frm.jTableegresos.getTableHeader().setReorderingAllowed(false);
            llenarTabla(frm.jTableegresos);
        }

        if (e.getSource() == frm.btn_editar) {
            if (frm.txtTitulo.getText().equals("") || frm.txtDetalle.getText().equals("") || frm.txtValor.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingresar todos los datos");
            } else {
                mod.setTitle(frm.txtTitulo.getText());
                mod.setDetail(frm.txtDetalle.getText());
                mod.setValue(Double.parseDouble(frm.txtValor.getText()));
                mod.setUsers_id(Integer.parseInt(String.valueOf(DataLogin.getSoyUnico().getId())));

                if (modC.Actualizar(mod)) {
                    JOptionPane.showMessageDialog(null, "Se guardo la informacion", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    frm.btn_editar.setVisible(false);
                    frm.btn_guardar.setVisible(true);
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error al actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == frm.btn_editar1) {
            int fila = frm.jTableegresos.getSelectedRow();
            if (fila >= 0) {
                frm.jPanel2.setVisible(false);
                frm.jPanel1.setVisible(true);
                frm.btn_guardar.setVisible(false);
                frm.btn_editar.setVisible(true);
                Editegresos();
            } else {
                JOptionPane.showMessageDialog(null, "No ha seleccionado");
            }
        }

    }

    public void limpiar() {
        frm.txtTitulo.setText(null);
        frm.txtDetalle.setText(null);
        frm.txtValor.setText(null);
    }

    public void llenarTabla(JTable tablaexpenditures) {
        DefaultTableModel modelot = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int ColIndex) {
                return false;
            }
        };
        tablaexpenditures.setModel(modelot);
        tablaexpenditures.setRowHeight(25);

        modelot.addColumn("Id");
        modelot.addColumn("Titulo");
        modelot.addColumn("Detalle");
        modelot.addColumn("Valor");
        modelot.addColumn("Usuario");

        Object[] columna = new Object[5];

        int numRegistros = modC.listExpenditures().size();

        for (int i = 0; i < numRegistros; i++) {

            columna[0] = modC.listExpenditures().get(i).getId();
            columna[1] = modC.listExpenditures().get(i).getTitle();
            columna[2] = modC.listExpenditures().get(i).getDetail();
            columna[3] = modC.listExpenditures().get(i).getValue();
            columna[4] = modC.listExpenditures().get(i).getUsers_id();
            modelot.addRow(columna);
        }
    }

    public void Editegresos() {
        int fil = frm.jTableegresos.getSelectedRow();
        mod.setId((int) frm.jTableegresos.getValueAt(fil, 0));
        mod.setTitle((String) frm.jTableegresos.getValueAt(fil, 1));
        mod.setDetail((String) frm.jTableegresos.getValueAt(fil, 2));
        mod.setValue((Double) frm.jTableegresos.getValueAt(fil, 3));
        frm.txtTitulo.setText(mod.getTitle());
        frm.txtDetalle.setText(mod.getDetail());
        frm.txtValor.setText(String.valueOf(mod.getValue()));
    }

}
