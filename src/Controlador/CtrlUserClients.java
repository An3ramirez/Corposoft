/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Model.*;
import Vista.*;
import java.awt.AWTEvent;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.text.DecimalFormat;
import org.jdesktop.swingx.autocomplete.*;
import redondeocentenas.RedondeoCentenas;
/**
 *
 * @author an3-r
 */
public class CtrlUserClients implements ActionListener, ChangeListener, KeyListener, ItemListener {
    
    static Connection conn = null;
    static Statement st = null;
    static ResultSet rs = null;
    public Clients mod;
    public ConsultasClients modC;
    private User frm;
    Metodos MT = new Metodos();
    Clients Cli = new Clients();
    RedondeoCentenas RdC = new RedondeoCentenas();
    ValidarCampos VC = new ValidarCampos();
    ConsultasPayroll CtPay = new ConsultasPayroll();
    Payroll modPay = new Payroll();
    int UserUpdatePayroll;
    String fecha;
    DecimalFormat formatoMiles = new DecimalFormat("###,###.##");
    public int total, modifAdminist;
    boolean banMdAdmis=false;
    
    public CtrlUserClients(Clients mod,ConsultasClients modC, User frm){
        
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnCargar.addActionListener(this);
        this.frm.btnUpdate.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnCargarPayroll.addActionListener(this);
        this.frm.btnCargarNoPayroll.addActionListener(this);
        this.frm.btnGuardarPayroll.addActionListener(this);
        this.frm.btnUpdatePayroll.addActionListener(this);
        this.frm.btnResetTable1.addActionListener(this);
        this.frm.btnEditar.addActionListener(this);
        this.frm.TabbedPaneNoPlanillas.addChangeListener(this);
        this.frm.TabbedPaneUser.addChangeListener(this);
        this.frm.txtBuscarTbEdit.addKeyListener(this);
        this.frm.txtNombre1.addKeyListener(this);
        this.frm.txtNombre2.addKeyListener(this);
        this.frm.txtApel1.addKeyListener(this);
        this.frm.txtApel2.addKeyListener(this);
        this.frm.txtNumDocumento.addKeyListener(this);
        this.frm.txtSalary.addKeyListener(this);
        this.frm.mItemStateMora.addActionListener(this);
        this.frm.mItemStateRetirado.addActionListener(this);
        this.frm.mItemStateUserNew.addActionListener(this);
        this.frm.cBoxFiltroUsers.addItemListener(this);
        
        cargarTbClients("");
        cargarCombox();
        total=0;
           
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == frm.btnGuardar)
        {
           if(frm.cBoxEmpInter.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione una empresa interna ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtNombre1.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Error el campo Primer nombre no puede quedar vacio","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxEmpExter.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione una empresa externa ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxTipeDocument.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione un tipo documento ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtApel1.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Error el campo Primer apellido no puede quedar vacio","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxTypeClient.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione tipo cliente ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtNumDocumento.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Error el campo Numero documento no puede quedar vacio","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxGender.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione un genero ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtSalary.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Error el campo Sueldo no puede quedar vacio","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxDepart.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione departamento ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtEmail.getText().length()>0 && false == Cli.esEmail(frm.txtEmail.getText())){
            JOptionPane.showMessageDialog(null,"Error el Correo ingresado no es correcto ","Error datos",JOptionPane.ERROR_MESSAGE); 
        }else if(frm.cBoxCiudad.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione ciudad ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtDireccion.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Error el campo direccion no puede quedar vacio","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxArl.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione arl ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxFuneral.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione un servicio funerario ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxNivelArl.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione un nivel arl ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxEps.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione eps ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxPension.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione pension ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxCompBox.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione soat ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else{ 
            
           Departments cbPais = (Departments) frm.cBoxDepart.getSelectedItem();
           Internal_companies cbIntComp = (Internal_companies) frm.cBoxEmpInter.getSelectedItem();
           External_companies cbExtComp = (External_companies) frm.cBoxEmpExter.getSelectedItem();
           Type_documents cbTpDc = (Type_documents) frm.cBoxTipeDocument.getSelectedItem();
           Clients_type cbClitp = (Clients_type) frm.cBoxTypeClient.getSelectedItem();
           Cities Ciud = (Cities) frm.cBoxCiudad.getSelectedItem();
           Eps eps = (Eps) frm.cBoxEps.getSelectedItem();
           Arl arl = (Arl) frm.cBoxNivelArl.getSelectedItem();
           Pension_entities PenEnt = (Pension_entities) frm.cBoxPension.getSelectedItem();
           Compensation_box CompBox = (Compensation_box) frm.cBoxCompBox.getSelectedItem();
           Funeral_insurance FunEns = (Funeral_insurance) frm.cBoxFuneral.getSelectedItem();
           StatusClient Status = (StatusClient) frm.cBoxStatus.getSelectedItem();
           Date date = frm.CalendarBirth_date.getDate();
             long d = date.getTime();
             java.sql.Date fecha = new java.sql.Date(d);
           
           frm.lblIdClient.setText(MT.IdAutomaticos("id", "clients"));
           frm.lblIdClient.setForeground(new java.awt.Color(0, 0, 0));
           mod.setId(Integer.parseInt(frm.lblIdClient.getText()));
           mod.setInternal_company_id(cbIntComp.getId());
           mod.setExternal_company_id(cbExtComp.getId());
           mod.setType_document_id(cbTpDc.getId());
           mod.setClient_type_id(cbClitp.getId());
           mod.setFirst_name(frm.txtNombre1.getText());
           mod.setSecond_name(frm.txtNombre2.getText());
           mod.setFirst_last_name(frm.txtApel1.getText());
           mod.setSecond_last_name(frm.txtApel2.getText());
           mod.setGender(String.valueOf(frm.cBoxGender.getSelectedItem()));
           mod.setDocument(frm.txtNumDocumento.getText());
           mod.setEmail(frm.txtEmail.getText());
           mod.setAddress(frm.txtDireccion.getText());
           mod.setSalary(Double.parseDouble(frm.txtSalary.getText()));
           mod.setCity_id(Ciud.getId());
           mod.setEps_id(eps.getId());
           mod.setRisk_id(arl.getId());
           mod.setPension_entity_id(PenEnt.getId());
           mod.setCompensation_box_id(CompBox.getId());
           mod.setFuneral_insurance_id(FunEns.getId());
           mod.setBirth_date(fecha);
           if(frm.TextAreaObservations.getText().length() == 0)
               mod.setObservations("");
           mod.setObservations(frm.TextAreaObservations.getText());
           
           
           if(modC.Registrar(mod))
           {
               JOptionPane.showMessageDialog(null,"Se guardo el cliente", "Correcto Guardar", JOptionPane.INFORMATION_MESSAGE);
               limpiar();
               cargarTableEditClients();
               frm.lblIdClient.setText(MT.IdAutomaticos("id", "clients"));
           }else{
               JOptionPane.showMessageDialog(null,"Hubo un error al guardar\n\nEl código del error generado es: "
                       + MT.IdTable("id", "errors")+"\n\nGuarde este código para informar al técnico de soporte", "Error Guardar", JOptionPane.ERROR_MESSAGE);
               
           }
        
        }
    }else if(e.getSource() == frm.btnLimpiar)
    {
        limpiar();
    }else if(e.getSource() == frm.btnUpdate)
    {
              if(frm.cBoxEmpInter.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione una empresa interna ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtNombre1.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Error el campo Primer nombre no puede quedar vacio","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxEmpExter.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione una empresa externa ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxTipeDocument.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione un tipo documento ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtApel1.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Error el campo Primer apellido no puede quedar vacio","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxTypeClient.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione tipo cliente ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtNumDocumento.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Error el campo Numero documento no puede quedar vacio","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxGender.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione un genero ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtSalary.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Error el campo Sueldo no puede quedar vacio","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxDepart.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione departamento ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtEmail.getText().length()>0 && false == Cli.esEmail(frm.txtEmail.getText())){
            JOptionPane.showMessageDialog(null,"Error el Correo ingresado no es correcto ","Error datos",JOptionPane.ERROR_MESSAGE); 
        }else if(frm.cBoxCiudad.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione ciudad ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.txtDireccion.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Error el campo direccion no puede quedar vacio","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxArl.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione arl ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxFuneral.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione un servicio funerario ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxNivelArl.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione un nivel arl ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxEps.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione eps ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxPension.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione pension ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(frm.cBoxCompBox.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Error por favor seleccione soat ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else{ 
            
           Departments cbPais = (Departments) frm.cBoxDepart.getSelectedItem();
           Internal_companies cbIntComp = (Internal_companies) frm.cBoxEmpInter.getSelectedItem();
           External_companies cbExtComp = (External_companies) frm.cBoxEmpExter.getSelectedItem();
           Type_documents cbTpDc = (Type_documents) frm.cBoxTipeDocument.getSelectedItem();
           Clients_type cbClitp = (Clients_type) frm.cBoxTypeClient.getSelectedItem();
           Cities Ciud = (Cities) frm.cBoxCiudad.getSelectedItem();
           Eps eps = (Eps) frm.cBoxEps.getSelectedItem();
           Arl arl = (Arl) frm.cBoxNivelArl.getSelectedItem();
           Pension_entities PenEnt = (Pension_entities) frm.cBoxPension.getSelectedItem();
           Compensation_box CompBox = (Compensation_box) frm.cBoxCompBox.getSelectedItem();
           Funeral_insurance FunEns = (Funeral_insurance) frm.cBoxFuneral.getSelectedItem();
           StatusClient Status = (StatusClient) frm.cBoxStatus.getSelectedItem();
             
           Date date = frm.CalendarBirth_date.getDate();
             long d = date.getTime();
             java.sql.Date fecha = new java.sql.Date(d);
                  
           mod.setId(Integer.parseInt(frm.lblIdClient.getText()));
           mod.setInternal_company_id(cbIntComp.getId());
           mod.setExternal_company_id(cbExtComp.getId());
           mod.setType_document_id(cbTpDc.getId());
           mod.setClient_type_id(cbClitp.getId());
           mod.setFirst_name(frm.txtNombre1.getText());
           mod.setSecond_name(frm.txtNombre2.getText());
           mod.setFirst_last_name(frm.txtApel1.getText());
           mod.setSecond_last_name(frm.txtApel2.getText());
           mod.setGender(String.valueOf(frm.cBoxGender.getSelectedItem()));
           mod.setDocument(frm.txtNumDocumento.getText());
           mod.setEmail(frm.txtEmail.getText());
           mod.setAddress(frm.txtDireccion.getText());
           mod.setSalary(Double.parseDouble(frm.txtSalary.getText()));
           mod.setCity_id(Ciud.getId());
           mod.setEps_id(eps.getId());
           mod.setRisk_id(arl.getId());
           mod.setPension_entity_id(PenEnt.getId());
           mod.setCompensation_box_id(CompBox.getId());
           mod.setFuneral_insurance_id(FunEns.getId());
           mod.setBirth_date(fecha);
           mod.setObservations(frm.TextAreaObservations.getText());
           mod.setStatus_id(Status.getId());
           
           if(modC.Modificar(mod))
           {
               JOptionPane.showMessageDialog(null,"Se edito el cliente", "Correcto Editar", JOptionPane.INFORMATION_MESSAGE);
               limpiar();
               cargarTableEditClients();
               frm.lblIdClient.setText(MT.IdAutomaticos("id", "clients"));
               frm.lblIdClient.setForeground(new java.awt.Color(0, 0, 0));
           }else{
               JOptionPane.showMessageDialog(null,"Hubo un error al editar\n\nEl código del error generado es: "
                       + MT.IdTable("id", "errors")+"\n\nGuarde este código para informar al técnico de soporte", "Error Editar", JOptionPane.ERROR_MESSAGE);
               
           }
           
         }
              
        
    }else if(e.getSource() == frm.btnCargar){
        
        java.util.Date now=new java.util.Date();
        Date date = now;
             long d = date.getTime();
             java.sql.Date fecha = new java.sql.Date(d);
        String dato="";
        int User=0;
        int fila=frm.TblUsersEdit.getSelectedRow();
        if(fila>=0){
           frm.btnGuardar.setEnabled(false);
           dato=(frm.TblUsersEdit.getValueAt(fila, 0).toString());
           User=Integer.parseInt(dato);
           mod.setId(User);
           if(modC.Buscar(mod))
           {
               //Cargamos todos los cBox
                Departments ct = new Departments();
                Cities ciud = new Cities();
                Internal_companies objInterComp = new Internal_companies();
                External_companies objExterComp = new External_companies();
                Type_documents objTpDoc = new Type_documents();
                Clients_type objCliType = new Clients_type();
                Eps objEps = new Eps();
                Arl objArl = new Arl();
                Pension_entities objPens = new Pension_entities();
                Compensation_box objCompBox = new Compensation_box();
                Funeral_insurance objFuIns = new Funeral_insurance();
                StatusClient Status = new StatusClient();
                DefaultComboBoxModel modelocBoxDept = new DefaultComboBoxModel(ct.mostrarDepartments());
                DefaultComboBoxModel modlCiudad = new DefaultComboBoxModel(ciud.mostrarCities(buscarPaisId(mod.getCity_id())));
                DefaultComboBoxModel modelocBoxIntComp = new DefaultComboBoxModel(objInterComp.mostrarNameCompInter());
                DefaultComboBoxModel modelocBoxExtComp = new DefaultComboBoxModel(objExterComp.mostrarNameCompExter());
                DefaultComboBoxModel modelocBoxTpDoc = new DefaultComboBoxModel(objTpDoc.mostrarTypeDocument());
                DefaultComboBoxModel modelocBoxCliType = new DefaultComboBoxModel(objCliType.mostrarClientType());
                DefaultComboBoxModel modelocBoxEps = new DefaultComboBoxModel(objEps.mostrarEps());
                DefaultComboBoxModel modelocBoxArl = new DefaultComboBoxModel(objArl.mostrarArl(buscarArlId(mod.getRisk_id())));
                DefaultComboBoxModel modelocBoxPens = new DefaultComboBoxModel(objPens.mostrarPension_entities());
                DefaultComboBoxModel modelocBoxCompBox = new DefaultComboBoxModel(objCompBox.mostrarCompBox());
                DefaultComboBoxModel modelocBoxFuIns = new DefaultComboBoxModel(objFuIns.mostrarFuneral_insurance());
                DefaultComboBoxModel modelocBoxStatus = new DefaultComboBoxModel(Status.mostrarEstadoClients());
                frm.cBoxDepart.setModel(modelocBoxDept);
                frm.cBoxCiudad.setModel(modlCiudad);
                frm.cBoxEmpInter.setModel(modelocBoxIntComp);
                frm.cBoxEmpExter.setModel(modelocBoxExtComp);
                frm.cBoxTipeDocument.setModel(modelocBoxTpDoc);
                frm.cBoxTypeClient.setModel(modelocBoxCliType);
                frm.cBoxEps.setModel(modelocBoxEps);
                frm.cBoxNivelArl.setModel(modelocBoxArl);
                frm.cBoxPension.setModel(modelocBoxPens);
                frm.cBoxCompBox.setModel(modelocBoxCompBox);
                frm.cBoxFuneral.setModel(modelocBoxFuIns);
                frm.cBoxStatus.setModel(modelocBoxStatus);
               
               frm.lblIdClient.setText(String.valueOf(mod.getId()));
               frm.lblIdClient.setForeground(new java.awt.Color(0, 204, 0));
               frm.txtNombre1.setText(mod.getFirst_name());
               frm.txtNombre2.setText(mod.getSecond_name());
               frm.txtApel1.setText(mod.getFirst_last_name());
               frm.txtApel2.setText(mod.getSecond_last_name());
               frm.txtNumDocumento.setText(String.valueOf(mod.getDocument()));
               frm.txtDireccion.setText(mod.getAddress());
               frm.txtEmail.setText(mod.getEmail());
               frm.txtSalary.setText(String.valueOf(mod.getSalary()));
               if(mod.getBirth_date()!= null){
                  frm.CalendarBirth_date.setDate(mod.getBirth_date()); 
               }else{
                  frm.CalendarBirth_date.setDate(fecha);
               }
               
               frm.TextAreaObservations.setText(mod.getObservations());
               frm.cBoxEmpInter.setSelectedIndex(mod.getInternal_company_id());
               frm.cBoxEmpExter.setSelectedIndex(mod.getExternal_company_id());
               frm.cBoxTipeDocument.setSelectedIndex(mod.getType_document_id());
               frm.cBoxTypeClient.setSelectedIndex(mod.getClient_type_id());
               frm.cBoxGender.setSelectedItem(mod.getGender());
               frm.cBoxDepart.setSelectedIndex(buscarPaisId(mod.getCity_id()));
               //frm.cBoxCuidad.setSelectedIndex(mod.getCity_id());
               frm.cBoxEps.setSelectedIndex(mod.getEps_id());
               frm.cBoxArl.setSelectedIndex(buscarArlId(mod.getRisk_id()));
               frm.cBoxNivelArl.setSelectedIndex(mod.getRisk_id());
               frm.cBoxPension.setSelectedIndex(mod.getPension_entity_id());
               frm.cBoxCompBox.setSelectedIndex(mod.getCompensation_box_id());
               frm.cBoxFuneral.setSelectedIndex(mod.getFuneral_insurance_id());
               frm.cBoxStatus.setSelectedIndex(mod.getStatus_id());
               
               
           }
           
       }else{
           JOptionPane.showMessageDialog(null,"Primero debe selecionar un cliente de la tabla");
           
       }
    }else if(e.getSource() == frm.btnCargarPayroll){
        String dato="";
        int User=0;
        int fila=frm.tblClientsPayrroll.getSelectedRow();
        if(fila>=0){
           frm.btnGuardarPayroll.setEnabled(true);
           dato=(frm.tblClientsPayrroll.getValueAt(fila, 0).toString());
           User=Integer.parseInt(dato);
           mod.setId(User);
           if(modC.Buscar(mod))
           {
               frm.lblIdNameCLientSelected.setText(String.valueOf(mod.getId())+"  "+
                       mod.getFirst_name()+" "+mod.getSecond_name()+" "+mod.getFirst_last_name()+
                       " "+mod.getSecond_last_name());
               frm.lblIdRegistro.setText(MT.IdAutomaticos("id", "payroll"));
               frm.txtNoPayroll.setText(null);
               
               
           }else{
               JOptionPane.showMessageDialog(null,"No se encontro registro");
           }
           cargarTablePayroll(mod.getId());
           frm.TabbedPaneNoPlanillas.setSelectedIndex(1);
           }else{
           JOptionPane.showMessageDialog(null,"No a selecionado un cliente de la tabla");
           
       }
        
    }else if(e.getSource() == frm.btnCargarNoPayroll){
        String dato="";
        int User=0;
        int fila=frm.tblNoPayroll.getSelectedRow();
        if(fila>=0){
           frm.btnGuardarPayroll.setEnabled(false);
           dato=(frm.tblNoPayroll.getValueAt(fila, 0).toString());
           User=Integer.parseInt(dato);
           modPay.setId(User);
            System.out.println("Id "+modPay.getId());
           if(CtPay.Buscar(modPay))
           {
               frm.lblIdRegistro.setText(String.valueOf(modPay.getId()));
               frm.lblIdRegistro.setForeground(new java.awt.Color(0, 204, 0));
               frm.txtNoPayroll.setText(modPay.getNumber());
               frm.TextAreaObservationsPayroll.setText(modPay.getObservations());
               
           }
          
           }else{
           JOptionPane.showMessageDialog(null,"No a selecionado un No de planilla");
           
       }
    }else if(e.getSource() == frm.btnGuardarPayroll){
        String labelId;
        String idSub;
        labelId=frm.lblIdNameCLientSelected.getText();
        idSub=labelId.substring(0, 1);
            
        
         if(frm.txtNoPayroll.getText().length()==0){
            JOptionPane.showMessageDialog(null,"El campo No planilla no puede quedar vacio ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(idSub.equals("0")){
            JOptionPane.showMessageDialog(null,"Debe seleccionar un cliente de la tabla ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else{      
         
             
             modPay.setId(Integer.parseInt(frm.lblIdRegistro.getText()));
             modPay.setNumber(frm.txtNoPayroll.getText());
             modPay.setObservations(frm.TextAreaObservationsPayroll.getText());
             modPay.setClients_id(mod.getId());
             
             if(CtPay.Registrar(modPay))
           {
               JOptionPane.showMessageDialog(null,"Se guardo la información", "Correcto Guardar", JOptionPane.INFORMATION_MESSAGE);
               frm.txtNoPayroll.setText(null);
               frm.TextAreaObservationsPayroll.setText(null);
               frm.TabbedPaneNoPlanillas.setSelectedIndex(1);
               cargarTablePayroll(mod.getId());
               frm.lblIdRegistro.setText(MT.IdAutomaticos("id", "payroll"));
               
           }else{
               JOptionPane.showMessageDialog(null,"Disculpa hubo un error al guardar\n\nEl código del error generado es: "
                       + MT.IdTable("id", "errors")+"\n\nGuarde este código para informar al técnico de soporte", "Error Guardar", JOptionPane.ERROR_MESSAGE);
               
           }
         }
    }else if(e.getSource() == frm.btnUpdatePayroll){
        
        String labelId;
        String idSub;
        labelId=frm.lblIdNameCLientSelected.getText();
        idSub=labelId.substring(0, 1);
            
        
         if(frm.txtNoPayroll.getText().length()==0){
            JOptionPane.showMessageDialog(null,"El campo No planilla no puede quedar vacio ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else if(idSub.equals("0")){
            JOptionPane.showMessageDialog(null,"Debe seleccionar un cliente de la tabla ","Faltan datos",JOptionPane.ERROR_MESSAGE);
        }else{      
         
             
             modPay.setId(Integer.parseInt(frm.lblIdRegistro.getText()));
             modPay.setNumber(frm.txtNoPayroll.getText());
             modPay.setObservations(frm.TextAreaObservationsPayroll.getText());
             
             
             if(CtPay.Modificar(modPay))
           {
               JOptionPane.showMessageDialog(null,"Se edito la información", "Correcto Editar", JOptionPane.INFORMATION_MESSAGE);
               frm.txtNoPayroll.setText(null);
               frm.TextAreaObservationsPayroll.setText(null);
               cargarTablePayroll(UserUpdatePayroll);
               
               
           }else{
               JOptionPane.showMessageDialog(null,"Disculpa hubo un error al editar\n\nEl código del error generado es: "
                       + MT.IdTable("id", "errors")+"\n\nGuarde este código para informar al técnico de soporte", "Error Editar", JOptionPane.ERROR_MESSAGE);
               
           }
         }
    }else if(e.getSource() == frm.mItemStateRetirado){
        // Asignar estado retirado
        String idrow="";
        int fila=frm.TableClients.getSelectedRow();
        if(fila>=0){
            idrow=frm.TableClients.getValueAt(fila, 0).toString();
        //se invoca el metodo para hacer el update
            updateEstadoCli(1, idrow);
        }else{
            JOptionPane.showMessageDialog(null,"Primero debe selecionar un cliente de la tabla","Error",JOptionPane.ERROR_MESSAGE);
        }
        //Termina el metodo
    }else if(e.getSource() == frm.mItemStateUserNew){
        // Asignar estado nuevo
        String idrow="";
        int fila=frm.TableClients.getSelectedRow();
        if(fila>=0){
            idrow=frm.TableClients.getValueAt(fila, 0).toString();
        //se invoca el metodo para hacer el update
            updateEstadoCli(2, idrow);
        }else{
            JOptionPane.showMessageDialog(null,"Primero debe selecionar un cliente de la tabla","Error",JOptionPane.ERROR_MESSAGE);
        }
        //Termina el metodo
    }else if(e.getSource() == frm.mItemStateMora){
        // Asignar estado mora
        String idrow="";
        int fila=frm.TableClients.getSelectedRow();
        if(fila>=0){
            idrow=frm.TableClients.getValueAt(fila, 0).toString();
        //se invoca el metodo para hacer el update
            updateEstadoCli(3, idrow);
        }else{
            JOptionPane.showMessageDialog(null,"Primero debe selecionar un cliente de la tabla","Error",JOptionPane.ERROR_MESSAGE);
        }
        //Termina el metodo
    }else if(e.getSource() == frm.btnResetTable1){
        cargarTbClients("");
    }else if(e.getSource() == frm.btnEditar){
            
            if(banMdAdmis){
            total=total-modifAdminist;
            modifAdminist=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el nuevo valor","Cambiar valor de administracion",JOptionPane.INFORMATION_MESSAGE));
            total=total+modifAdminist;
            frm.lblTotal.setText("$ "+formatoMiles.format(total));
            }else{
            modifAdminist=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el nuevo valor","Cambiar valor de administracion",JOptionPane.INFORMATION_MESSAGE));
            total=total-31500+modifAdminist;
            frm.lblTotal.setText("$ "+formatoMiles.format(total));
            banMdAdmis=true;
            }
            
            
    }
    
    }
    
    
    public void limpiar(){
        frm.btnGuardar.setEnabled(true);
        frm.lblIdClient.setText(MT.IdAutomaticos("id", "clients"));
        frm.lblIdClient.setForeground(new java.awt.Color(0, 0, 0));
        frm.txtNombre1.setText(null);
        frm.txtNombre2.setText(null);
        frm.txtApel1.setText(null);
        frm.txtApel2.setText(null);
        frm.txtNumDocumento.setText(null);
        frm.txtEmail.setText(null);
        frm.txtDireccion.setText(null);
        frm.txtSalary.setText(null);
        frm.TextAreaObservations.setText(null);
        //Reseteamos los combox
        frm.cBoxEmpExter.setSelectedIndex(0);
        frm.cBoxEmpInter.setSelectedIndex(0);
        frm.cBoxArl.setSelectedIndex(0);
        frm.cBoxNivelArl.setSelectedIndex(0);
        frm.cBoxCiudad.removeAllItems();
        frm.cBoxDepart.setSelectedIndex(0);
        frm.cBoxEps.setSelectedIndex(0);
        frm.cBoxFuneral.setSelectedIndex(0);
        frm.cBoxGender.setSelectedIndex(0);
        frm.cBoxPension.setSelectedIndex(0);
        frm.cBoxTipeDocument.setSelectedIndex(0);
        frm.cBoxTypeClient.setSelectedIndex(0);
        frm.cBoxCompBox.setSelectedIndex(0);
        frm.cBoxStatus.setSelectedIndex(0);
    }
    
    
    public void cargarCombox(){
        Departments ct = new Departments();
        Internal_companies objInterComp = new Internal_companies();
        External_companies objExterComp = new External_companies();
        Type_documents objTpDoc = new Type_documents();
        Clients_type objCliType = new Clients_type();
        Eps objEps = new Eps();
        Prividerarl objArl = new Prividerarl();
        Pension_entities objPens = new Pension_entities();
        Compensation_box objCompBox = new Compensation_box();
        Funeral_insurance objFuIns = new Funeral_insurance();
        StatusClient Status = new StatusClient();
        DefaultComboBoxModel modelocBoxPaises = new DefaultComboBoxModel(ct.mostrarDepartments());
        DefaultComboBoxModel modelocBoxIntComp = new DefaultComboBoxModel(objInterComp.mostrarNameCompInter());
        DefaultComboBoxModel modelocBoxExtComp = new DefaultComboBoxModel(objExterComp.mostrarNameCompExter());
        DefaultComboBoxModel modelocBoxTpDoc = new DefaultComboBoxModel(objTpDoc.mostrarTypeDocument());
        DefaultComboBoxModel modelocBoxCliType = new DefaultComboBoxModel(objCliType.mostrarClientType());
        DefaultComboBoxModel modelocBoxEps = new DefaultComboBoxModel(objEps.mostrarEps());
        DefaultComboBoxModel modelocBoxArl = new DefaultComboBoxModel(objArl.mostrarArlName());
        DefaultComboBoxModel modelocBoxPens = new DefaultComboBoxModel(objPens.mostrarPension_entities());
        DefaultComboBoxModel modelocBoxCompBox = new DefaultComboBoxModel(objCompBox.mostrarCompBox());
        DefaultComboBoxModel modelocBoxFuIns = new DefaultComboBoxModel(objFuIns.mostrarFuneral_insurance());
        DefaultComboBoxModel modelocBoxStatus = new DefaultComboBoxModel(Status.mostrarEstadoClients());
        
        frm.cBoxDepart.setModel(modelocBoxPaises);
        frm.cBoxEmpInter.setModel(modelocBoxIntComp);
        frm.cBoxEmpExter.setModel(modelocBoxExtComp);
        frm.cBoxTipeDocument.setModel(modelocBoxTpDoc);
        frm.cBoxTypeClient.setModel(modelocBoxCliType);
        frm.cBoxEps.setModel(modelocBoxEps);
        frm.cBoxArl.setModel(modelocBoxArl);
        frm.cBoxPension.setModel(modelocBoxPens);
        frm.cBoxCompBox.setModel(modelocBoxCompBox);
        frm.cBoxFuneral.setModel(modelocBoxFuIns);
        frm.cBoxStatus.setModel(modelocBoxStatus);
        frm.cBoxFiltroUsers.setModel(modelocBoxStatus);
    }
    
    public void updateEstadoCli(int idStatus, String idrow){
        try {
        conn=Conexion.Enlace(conn);
        String sqlinsertar="UPDATE clients SET status_id=? WHERE id=?";
        PreparedStatement psta=conn.prepareStatement(sqlinsertar);
        psta.setInt(1, idStatus);
        psta.setString(2, idrow);
        psta.execute();
        JOptionPane.showMessageDialog(null,"Se actualizo el estado de este cliente correctamente","Correcto",JOptionPane.INFORMATION_MESSAGE);
        cargarTbClients("");
        } catch (Exception e) {
            System.out.println("Error update Clients "+e);
            MT.saveErrors("Error update Clients "+e);
            JOptionPane.showMessageDialog(null,"Disculpa hubo un error\n\nMensaje: "+e+" \n\nEl código del error generado es: "
                       + MT.IdTable("id", "errors")+"\n\nGuarde este código para informar al técnico de soporte", "Error Editar", JOptionPane.ERROR_MESSAGE);
           
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion update Clients "+e);
            }
        }
    }

    public int buscarPaisId(int idCd){
        int idPais=0;
        String sql="";
        sql = "SELECT dp.id FROM departments dp, cities cd WHERE dp.id=cd.Department_id AND cd.id="+idCd+" ";
        
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            

            if (rs.next()) {
                
                idPais=rs.getInt("id");
                

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado id de pais " + ex);
        }
        return idPais;
    }
    
    public int buscarArlId(int idArl){
        int idAr=0;
        String sql="";
        sql = "SELECT al.name_id FROM prividerarl pr, arl al WHERE pr.id=al.name_id AND al.id="+idArl+" ";
        
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            

            if (rs.next()) {
                
                idAr=rs.getInt("name_id");
                
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado id provider arl " + ex);
        }
        return idAr;
    }
    
    public void cargarTableEditClients(){
        
        String busqueda = frm.txtBuscarTbEdit.getText();
        DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nº DOCUMENTO");
        modelo.addColumn("NOMBRES");
        modelo.addColumn("APELLIDOS");
        
        String where = "";
        if(!"".equals(busqueda))
        {
            where = "WHERE firs_name LIKE '"+ busqueda +"%'" ;
        }
        
        frm.TblUsersEdit.setModel(modelo);
        frm.tblClientsPayrroll.setModel(modelo);
        String sql="";
        
            sql="SELECT id, document, first_name, second_name, first_last_name, second_last_name FROM clients "+where+" ORDER BY id ";
        
        String []datos = new String [4];
     try {
         conn=Conexion.Enlace(conn);
         Statement st = conn.createStatement();
         
         ResultSet rs= st.executeQuery(sql);
         ResultSetMetaData rsMd = rs.getMetaData();
         int cantidadColumnas = 4;
         
        int[] anchos = {40,170,200,200};
        
        for (int i = 0; i < cantidadColumnas; i++) 
        {
             frm.TblUsersEdit.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
             frm.tblClientsPayrroll.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
         
         while(rs.next()){
             datos[0]=rs.getString(1);
             datos[1]=rs.getString(2);
             datos[2]=rs.getString(3)+" "+rs.getString(4);
             datos[3]=rs.getString(5)+" "+rs.getString(6);
             modelo.addRow(datos);
         }
         frm.TblUsersEdit.setModel(modelo);
         frm.tblClientsPayrroll.setModel(modelo);
     } catch (SQLException ex) {
         System.out.println("Error consultado ClientsTableEdit "+ex);
     }
    }
    
    public void cargarTablePayroll(Integer idCli){
        
        DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nº PLANILLA");
        modelo.addColumn("Observaciones");
        modelo.addColumn("FECHA");
        
        frm.tblNoPayroll.setModel(modelo);
        
        String sql="";
        
            sql="SELECT pay.id, pay.number, pay.observations, Date_format(pay.date_registro ,'%Y-%M-%d') FROM payroll pay, clients cl WHERE cl.id=pay.clients_id AND cl.id="+idCli+" ORDER BY pay.id ";
        
        String []datos = new String [4];
     try {
         conn=Conexion.Enlace(conn);
         //Pasamos a español colombia las fechas
         PreparedStatement psta=conn.prepareStatement("SET lc_time_names = es_CO");
         psta.execute();
         
         Statement st = conn.createStatement();
         
         ResultSet rs= st.executeQuery(sql);
         ResultSetMetaData rsMd = rs.getMetaData();
         int cantidadColumnas = rsMd.getColumnCount();;
         
        int[] anchos = {30,100,600,120};
        
        for (int i = 0; i < cantidadColumnas; i++) 
        {
             frm.tblNoPayroll.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
             
        }
         
         while(rs.next()){
             datos[0]=rs.getString(1);
             datos[1]=rs.getString(2);
             datos[2]=rs.getString(3);
             datos[3]=rs.getString(4);
             modelo.addRow(datos);
         }
         frm.tblNoPayroll.setModel(modelo);
         
     } catch (SQLException ex) {
         System.out.println("Error consultado tblNoPayroll "+ex);
     }finally{
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error cerrando conexion consultado tblNoPayroll "+e);
            }
        }
    }
    
    public String cargarNameCity(int idCity){
        
        String nameCity="";
        String sql="";
        sql = "SELECT name FROM cities WHERE id="+idCity+" ";
        
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            

            if (rs.next()) {
                
                nameCity=rs.getString("name");
                

            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado name_city=>ID " + ex);
        }
        return nameCity;
    }
    
    public void cargarTbClients(String vfiltro){
        //cargamos el coBoxStados
        /*StatusClient stcli = new StatusClient();
        DefaultComboBoxModel modelocBoxstate = new DefaultComboBoxModel(stcli.mostrarEstadoClients());
        frm.cBoxFiltroUsers.setModel(modelocBoxstate);*/
        
        DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("PRIMER NOMBRE");
        modelo.addColumn("SEGUNDO NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("COMPAÑIA INTERNA");
        modelo.addColumn("COMPAÑIA EXTERNA");
        modelo.addColumn("Nº DOCUMENTO");
        modelo.addColumn("ESTADO");
        
        String where = "";
        if(!"".equals(vfiltro))
        {
            where = "AND cl.status_id = " + vfiltro +"";
        }
        frm.TableClients.setModel(modelo);
        String sql="";
        
            sql="SELECT cl.id, cl.first_name, cl.second_name, cl.first_last_name, ext.comercial_name, inc.comercial_name, cl.document, st.name " +
"FROM clients cl, statusClient st, external_companies ext, internal_companies inc " +
"WHERE ext.id=cl.external_company_id AND inc.id=cl.internal_company_id AND st.id=cl.status_id "+where+" ORDER BY cl.id";
        
        String []datos = new String [8];
     try {
         conn=Conexion.Enlace(conn);
         Statement st = conn.createStatement();
         
         ResultSet rs= st.executeQuery(sql);
         ResultSetMetaData rsMd = rs.getMetaData();
         int cantidadColumnas = rsMd.getColumnCount();
         
         int[] anchos = {20,80,90,130,250,250,140,110};
        
        for (int i = 0; i < cantidadColumnas; i++) 
        {
             frm.TableClients.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
         
         while(rs.next()){
             datos[0]=rs.getString(1);
             datos[1]=rs.getString(2);
             datos[2]=rs.getString(3);
             datos[3]=rs.getString(4);
             datos[4]=rs.getString(5);
             datos[5]=rs.getString(6);
             datos[6]=rs.getString(7);
             datos[7]=rs.getString(8);
             modelo.addRow(datos);
         }
         frm.TableClients.setModel(modelo);
     } catch (SQLException ex) {
         System.out.println("Error consultado UsersEstados "+ex);
     } 
    }
    
    public void cargarTbUsersEdit(){
        String busqueda = frm.txtBuscarTbEdit.getText();    
        DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("No DOCUMENTO");
        modelo.addColumn("NOMBRES");
        modelo.addColumn("APELLIDOS");
        
        String where = "";
        if(!"".equals(busqueda))
        {
            where = "WHERE first_name LIKE '"+ busqueda +"%'" ;
        }
        
        frm.TblUsersEdit.setModel(modelo);
        String sql="";
        
            sql="SELECT id, document, first_name, second_name, first_last_name, second_last_name FROM clients "+where+" ORDER BY id ";
        
        String []datos = new String [4];
     try {
         conn=Conexion.Enlace(conn);
         Statement st = conn.createStatement();
         
         ResultSet rs= st.executeQuery(sql);
         ResultSetMetaData rsMd = rs.getMetaData();
         int cantidadColumnas = 4;
         
         int[] anchos = {40,170,200,200};
        
        for (int i = 0; i < cantidadColumnas; i++) 
        {
             frm.TblUsersEdit.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
         
         while(rs.next()){
             datos[0]=rs.getString(1);
             datos[1]=rs.getString(2);
             datos[2]=rs.getString(3)+" "+rs.getString(4);
             datos[3]=rs.getString(5)+" "+rs.getString(6);
             modelo.addRow(datos);
         }
         frm.TblUsersEdit.setModel(modelo);
     } catch (SQLException ex) {
         System.out.println("Error consultado UsersTableEstado "+ex);
     }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
         
        
        if(frm.TabbedPaneUser.getSelectedIndex() == 0){
            //pestaña principal
            cargarTbClients("");
            
        }else if(frm.TabbedPaneUser.getSelectedIndex() == 1){
            //pestaña editar clients
            cargarCombox();
            cargarTbUsersEdit();
        }else if(frm.TabbedPaneUser.getSelectedIndex() == 2){
            //pestaña payroll
            
            if(frm.TabbedPaneNoPlanillas.getSelectedIndex() == 0){
                //pestaña clientes en table planilla
                cargarTableEditClients();
        }else if(frm.TabbedPaneNoPlanillas.getSelectedIndex() == 1){
            //pestaña numero de planilla
            
            String dato="";
        UserUpdatePayroll=0;
        int fila=frm.tblClientsPayrroll.getSelectedRow();
        if(fila>=0){
           dato=(frm.tblClientsPayrroll.getValueAt(fila, 0).toString());
           UserUpdatePayroll=Integer.parseInt(dato);
           cargarTablePayroll(UserUpdatePayroll);
           frm.lblIdNameCLientSelected.setText(frm.tblClientsPayrroll.getValueAt(fila, 0).toString()+
                   "  "+frm.tblClientsPayrroll.getValueAt(fila, 2).toString()+
                   " "+frm.tblClientsPayrroll.getValueAt(fila, 3).toString());
        }else{
            JOptionPane.showMessageDialog(null, "No a seleccionado un cliente", "Error", JOptionPane.ERROR_MESSAGE);
            frm.TabbedPaneNoPlanillas.setSelectedIndex(0);
        }
                
        }
            
        }else if(frm.TabbedPaneUser.getSelectedIndex() == 3){
            //pestaña factura individual
            String id="";
            banMdAdmis=false;
            int valEps, valPen, valComp, valFun, valArl, valOtros, adminis;
            valEps = valPen = valComp = valFun = valArl = valOtros = adminis =0;
            JPanel panelDialog = new JPanel();
            //panelDialog.add(new JButton("Click"));
            JLabel lblNumero = new JLabel("Documento: ");
            JTextField txtDocumento = new JTextField(10);
            txtDocumento.setFont(new java.awt.Font("Arial Black", 1, 18));
            lblNumero.setFont(new java.awt.Font("Dubai", 0, 18));
            panelDialog.add(lblNumero);
            panelDialog.add(txtDocumento);
            
            int i =JOptionPane.showConfirmDialog(null,panelDialog,"¿Desea Generar una Factura?",JOptionPane.YES_NO_OPTION);
            if(i == 0)
            {
                frm.panelImprimir.setVisible(true);
                frm.PanelOpciones.setVisible(true);
                String Document="";
                Document=txtDocumento.getText();
                
                String sql="";
        sql = "SELECT cl.first_name, cl.second_name, cl.first_last_name, cl.second_last_name, tpdoc.name, cl.document, cl.email, \n" +
"cl.address, extcom.comercial_name, ep.name , round((cl.salary)*(ep.valor),0), pset.name as Pension, round((cl.salary)*(pset.valor),0), comp.name, round((cl.salary)*(comp.valor),0), fun.name, fun.valor, \n" +
"par.name, ar.nivel, round((cl.salary)*(ar.valor),0), cl.id FROM clients cl, type_documents tpdoc, external_companies extcom, eps ep, pension_entities pset, \n" +
"compensation_box comp, funeral_insurance fun,  prividerarl par, arl ar WHERE tpdoc.id=cl.type_document_id AND extcom.id=cl.external_company_id \n" +
"AND ep.id=cl.eps_id AND pset.id=cl.pension_entity_id AND comp.id=cl.compensation_box_id AND fun.id=cl.funeral_insurance_id\n" +
"AND par.id=ar.name_id AND ar.id=cl.risk_id AND cl.document="+Document+"";
        
        try {
            conn = Conexion.Enlace(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            

            if (rs.next()) {
                
                frm.txtNombres.setText(rs.getString(1)+" "+rs.getString(2));
                frm.txtApellidos.setText(rs.getString(3)+" "+rs.getString(4));
                frm.txtTpDocument.setText(rs.getString(5));
                frm.txtDocument.setText(rs.getString(6));
                frm.txtEmail1.setText(rs.getString(7));
                frm.txtDireccion1.setText(rs.getString(8));
                frm.txtEmpreClient.setText(rs.getString(9));
                frm.lblEps.setText(rs.getString(10));
                valEps=Integer.parseInt(rs.getString(11));
                frm.lblPension.setText(rs.getString(12));
                valPen=Integer.parseInt(rs.getString(13));
                frm.lblCajaComp.setText(rs.getString(14));
                valComp=Integer.parseInt(rs.getString(15));
                frm.lblPlanEx.setText(rs.getString(16));
                valFun=Integer.parseInt(rs.getString(17));
                frm.lblArl.setText(rs.getString(18));
                frm.lblArl1.setText(rs.getString(19));
                valArl=Integer.parseInt(rs.getString(20));
                id=rs.getString(21);
                
                //valOtros=Integer.parseInt(frm.txtOtros.getText());
                
                
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error consultado la factura " + ex);
            MT.saveErrors("Error Consulta factura "+e);
        }
        
        //Calculando el total
        //redondeamos los valores
        System.out.println("Eps = "+valEps);
        System.out.println("Pen = "+valPen);
        System.out.println("Comp = "+valComp);
        System.out.println("Arl = "+valArl);
        System.out.println("-------------------");
        valEps= RdC.enteroCentenas(valEps);
        valPen= RdC.enteroCentenas(valPen);
        valComp= RdC.enteroCentenas(valComp);
        valArl= RdC.enteroCentenas(valArl);
        adminis=31500;
        System.out.println("Eps = "+valEps);
        System.out.println("Pen = "+valPen);
        System.out.println("Comp = "+valComp);
        System.out.println("Arl = "+valArl);
        //fin redondeo
        //Verificamos tipo de cobro ingreso - cuota
        if(MT.fechaActual().equalsIgnoreCase(MT.fechaIngreso(id))){
            System.out.println("Primera vez");
            frm.ComboBoxTipoP.setSelectedIndex(2);
            total=65000;
            frm.lblTotal.setText("$ "+formatoMiles.format(total));
            
        }else if(MT.tienePagos(id)){
            System.out.println("No tiene pagos");
            frm.ComboBoxTipoP.setSelectedIndex(1);
            int diasVen=MT.diasVencidos(Document);
            if(diasVen>1){
            valEps=(valEps/30)*diasVen;
            valPen=(valPen/30)*diasVen;
            valArl=(valArl/30)*diasVen;
            valComp=(valComp/30)*diasVen;
            DesktopNotify.showDesktopMessage("Se ejecuto una acción", "Se acaba de calcular "+diasVen+" días vencidos para este cliente, según la tabla de pro rateo.", DesktopNotify.INFORMATION, 10000L);
            }
            total=valEps+valPen+valComp+valArl+valFun+adminis;
            frm.lblTotal.setText("$ "+formatoMiles.format(total));
            
            
        }else{
            System.out.println("ya pago");
            frm.ComboBoxTipoP.setSelectedIndex(1);
            total=valEps+valPen+valComp+valArl+valFun+adminis;
            frm.lblTotal.setText("$ "+formatoMiles.format(total));
        }
        //fin tipo cobro
        
                
            }else{
                frm.panelImprimir.setVisible(true);
                frm.PanelOpciones.setVisible(true);
            }
        }
            
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        if(e.getSource() == frm.txtBuscarTbEdit){
            cargarTbUsersEdit();
        }else if(e.getSource() == frm.txtNombre1){
            String Texto=frm.txtNombre1.getText();
            VC.validarSoloLetras(e);
            if(Texto.length()>0){
                char LetraMayus=Texto.charAt(0);
                Texto=Character.toUpperCase(LetraMayus)+Texto.substring(1, Texto.length());
                frm.txtNombre1.setText(Texto);
            }
            //con estas lineas convertimos las casillas de nombres y apellidos
            //las primeras letras en mayusculas y validadmos el tipo de caracter
        }else if(e.getSource() == frm.txtNombre2){
            String Texto=frm.txtNombre2.getText();
            VC.validarSoloLetras(e);
            if(Texto.length()>0){
                char LetraMayus=Texto.charAt(0);
                Texto=Character.toUpperCase(LetraMayus)+Texto.substring(1, Texto.length());
                frm.txtNombre2.setText(Texto);
            }
        }else if(e.getSource() == frm.txtApel1){
            String Texto=frm.txtApel1.getText();
            VC.validarSoloLetras(e);
            if(Texto.length()>0){
                char LetraMayus=Texto.charAt(0);
                Texto=Character.toUpperCase(LetraMayus)+Texto.substring(1, Texto.length());
                frm.txtApel1.setText(Texto);
            }
        }else if(e.getSource() == frm.txtApel2){
            String Texto=frm.txtApel2.getText();
            VC.validarSoloLetras(e);
            if(Texto.length()>0){
                char LetraMayus=Texto.charAt(0);
                Texto=Character.toUpperCase(LetraMayus)+Texto.substring(1, Texto.length());
                frm.txtApel2.setText(Texto);
            }
        }else if(e.getSource() == frm.txtNumDocumento){
                VC.validarSoloNumeros(e);
                
        }else if(e.getSource() == frm.txtSalary){
                VC.validarSoloNumeros(e);
            
        }
        //Termina la convercion de mayusculas y validacion
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {

            if(frm.cBoxFiltroUsers.getSelectedIndex()==1){
                cargarTbClients("1");
            }else if(frm.cBoxFiltroUsers.getSelectedIndex()==2){
                cargarTbClients("2");
            }else if(frm.cBoxFiltroUsers.getSelectedIndex()==3){
                cargarTbClients("3");
            }
        }
        
    }

    
}