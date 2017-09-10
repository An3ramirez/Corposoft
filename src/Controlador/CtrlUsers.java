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
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;

/**
 *
 * @author an3-r
 */
public class CtrlUsers implements ActionListener {

    public Users mod;
    public ConsultasUsers modC;
    private Login frm;
    private String AmPm;
    private String Saludo;

    public CtrlUsers(Users mod, ConsultasUsers modC, Login frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnEntrar.addActionListener(this);

    }

    public void Iniciar() {
        frm.setTitle("Inicio de sección");
        frm.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnEntrar) {

            mod.setUsername(frm.txtUsuer.getText());

            if (modC.BuscarLogin(mod)) {
                DataLogin user = DataLogin.getSingletonInstance(mod.getId(), mod.getInternal_company_id(),
                        mod.getRole_id(), mod.getFirst_name(), mod.getSecond_name(),
                        mod.getFirst_last_name(), mod.getSecond_last_name(), mod.getGender(),
                        mod.getDocument_type_id(), mod.getDocument(), mod.getContact_phone(),
                        mod.getStatus(), mod.getPassword(), mod.getUsername(), mod.getBirth_date(),
                        mod.getRemember_token());

                //Validando la entrada
                if (mod.getPassword().equals(frm.Password.getText())) {

                    User obj = new User();
                    
                    AmPm=frm.lblRelog.getText();
                    AmPm=AmPm.substring(18, 20);
                    if(AmPm.equals("AM")){
                        Saludo="Buenos dias";
                    }else{
                        Saludo="Buenas tardes";
                    }
                    
                    JOptionPane.showMessageDialog(null, Saludo+" " + DataLogin.getSoyUnico().getFirst_name() + " " + DataLogin.getSoyUnico().getFirst_last_name() + " bienvenido a CorpoSoft!", "BIENVENIDO", JOptionPane.INFORMATION_MESSAGE);
                    Clients mod = new Clients();
                    ConsultasClients modC = new ConsultasClients();
                    CtrlUserClients ctrl = new CtrlUserClients(mod, modC, obj);
                    obj.setVisible(true);
                    frm.dispose();
                    DesktopNotify.showDesktopMessage("Soy tu asistente de ayuda", "Aquí te brindare información importante acerca de la aplicación,\nEspero que hoy tengas un día muy productivo.", DesktopNotify.HELP, 30000L);
                    DesktopNotify.showDesktopMessage(Saludo+" "+ DataLogin.getSoyUnico().getFirst_name() + " " + DataLogin.getSoyUnico().getFirst_last_name() ," "
                            + "\nTe presentamos el sistema de notificaciones en tiempo real aquí se mostrara toda información relevante que esté sucediendo dentro de la aplicación Corposoft", DesktopNotify.TIP, 29000L);

                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña o Usuario Incorrecto", "Error Datos Incorrectos", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "El usuario no existe, por favor verifiquelo", "Error Usuario Incorrecto", JOptionPane.WARNING_MESSAGE);
                System.out.println("No se encontro usaurio");
            }
            
            
        }
    }

}
