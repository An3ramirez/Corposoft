/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Animacion.Animacion;
import Controlador.Captura;
import redondeocentenas.RedondeoCentenas;
import Controlador.Metodos;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author an3-r
 */
public class BarAnimacion extends javax.swing.JDialog {
    Metodos MT=new Metodos();

    /**
     * Creates new form Prueba
     */
    public BarAnimacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        
    }
    
    public void img_up(){
        ImageIcon img = new ImageIcon(getClass().getResource("subir_azul2.png"));
        etq_icono.setIcon(img);
    }
    
    public void img_down(){
        ImageIcon img = new ImageIcon(getClass().getResource("bajar_azul2.png"));
        etq_icono.setIcon(img);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        etq_icono = new javax.swing.JLabel();
        panelbar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtprueba = new javax.swing.JTextField();
        MenuBarUsers = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuItemCerrarsecion = new javax.swing.JMenuItem();
        MenuItemUsuarios = new javax.swing.JMenuItem();
        MenuItemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        MenuItemEstadisticas = new javax.swing.JMenuItem();
        Acerca = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etq_icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/bajar_azul2.png"))); // NOI18N
        etq_icono.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        etq_icono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                etq_iconoMouseReleased(evt);
            }
        });
        panelPrincipal.add(etq_icono, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, -1, -1));

        panelbar.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout panelbarLayout = new javax.swing.GroupLayout(panelbar);
        panelbar.setLayout(panelbarLayout);
        panelbarLayout.setHorizontalGroup(
            panelbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        panelbarLayout.setVerticalGroup(
            panelbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panelPrincipal.add(panelbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -100, 820, 100));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosMejorados/Aceptar/acep_norm.png"))); // NOI18N
        jButton1.setToolTipText("");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosMejorados/Aceptar/acep_press.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosMejorados/Aceptar/acep_roll.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelPrincipal.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelPrincipal.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        txtprueba.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtprueba.setText("3320");
        panelPrincipal.add(txtprueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 200, 40));

        MenuBarUsers.setBackground(new java.awt.Color(255, 255, 255));
        MenuBarUsers.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        MenuBarUsers.setToolTipText("Barra de Herramientas");
        MenuBarUsers.setAlignmentX(2.0F);
        MenuBarUsers.setAutoscrolls(true);
        MenuBarUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuBarUsers.setFont(new java.awt.Font("Orator Std", 0, 18)); // NOI18N
        MenuBarUsers.setOpaque(false);
        MenuBarUsers.setPreferredSize(new java.awt.Dimension(208, 51));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setBorder(null);
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/btn-ajustes-170x45.png"))); // NOI18N
        jMenu1.setToolTipText("Ajustes de la aplicacion");
        jMenu1.setDisabledSelectedIcon(null);
        jMenu1.setFocusPainted(true);
        jMenu1.setFont(new java.awt.Font("Lithos Pro Regular", 0, 14)); // NOI18N
        jMenu1.setIconTextGap(0);
        jMenu1.setName(""); // NOI18N
        jMenu1.setOpaque(true);
        jMenu1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/btn-ajustes-press-170x45.png"))); // NOI18N
        jMenu1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/btn-ajustes-press-170x45.png"))); // NOI18N

        MenuItemCerrarsecion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        MenuItemCerrarsecion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MenuItemCerrarsecion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Equis.png"))); // NOI18N
        MenuItemCerrarsecion.setText("Cerrar secion");
        MenuItemCerrarsecion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemCerrarsecionActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemCerrarsecion);

        MenuItemUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MenuItemUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Document.png"))); // NOI18N
        MenuItemUsuarios.setText("Administrar Usuarios");
        MenuItemUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemUsuariosActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemUsuarios);

        MenuItemSalir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MenuItemSalir.setText("Salir");
        MenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemSalir);

        MenuBarUsers.add(jMenu1);

        jMenu2.setBorder(null);
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Engranes.png"))); // NOI18N
        jMenu2.setText("Editar");
        jMenu2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        MenuBarUsers.add(jMenu2);

        jMenu3.setBorder(null);
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ver-los-detalles16x16.png"))); // NOI18N
        jMenu3.setText("Opciones");
        jMenu3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agregar.png"))); // NOI18N
        jMenuItem1.setText("Registrar Factura");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agregar.png"))); // NOI18N
        jMenuItem3.setText("Registrar Egreso");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        MenuItemEstadisticas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MenuItemEstadisticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ver-los-detalles16x16.png"))); // NOI18N
        MenuItemEstadisticas.setText("Estadisticas");
        MenuItemEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemEstadisticasActionPerformed(evt);
            }
        });
        jMenu3.add(MenuItemEstadisticas);

        MenuBarUsers.add(jMenu3);

        Acerca.setBorder(null);
        Acerca.setText("Acerca de");
        Acerca.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Acerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcercaActionPerformed(evt);
            }
        });

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem4.setText("Informacion");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        Acerca.add(jMenuItem4);

        MenuBarUsers.add(Acerca);

        setJMenuBar(MenuBarUsers);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void etq_iconoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etq_iconoMouseReleased
        
        int posicion = etq_icono.getY();
        if(posicion > 0){
            Animacion.subir(0,-100,2,panelbar);
            Animacion.subir(85, 0, 2, etq_icono);
            img_down();
        }else{
            Animacion.bajar(-100, 0, 2, panelbar);
            Animacion.bajar(0, 85, 2, etq_icono);
            img_up();
        }
    }//GEN-LAST:event_etq_iconoMouseReleased

    private void MenuItemCerrarsecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemCerrarsecionActionPerformed
        
    }//GEN-LAST:event_MenuItemCerrarsecionActionPerformed

    private void MenuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemUsuariosActionPerformed
        Registro R = new Registro();
        R.setVisible(true);
    }//GEN-LAST:event_MenuItemUsuariosActionPerformed

    private void MenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_MenuItemSalirActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void MenuItemEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemEstadisticasActionPerformed
        
    }//GEN-LAST:event_MenuItemEstadisticasActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void AcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcercaActionPerformed

    }//GEN-LAST:event_AcercaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                       // TODO add your handling code here:
                       
                       RedondeoCentenas RdC = new RedondeoCentenas();
                       
                       System.out.println("Redondiado "+RdC.enteroCentenas(Integer.parseInt(txtprueba.getText())));
                       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int valEps=32100;
        
        valEps=(valEps/30)*2 ;
        System.out.println("Valor es "+valEps);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BarAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BarAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BarAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BarAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BarAnimacion dialog = new BarAnimacion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Acerca;
    private javax.swing.JMenuBar MenuBarUsers;
    private javax.swing.JMenuItem MenuItemCerrarsecion;
    private javax.swing.JMenuItem MenuItemEstadisticas;
    private javax.swing.JMenuItem MenuItemSalir;
    private javax.swing.JMenuItem MenuItemUsuarios;
    public javax.swing.JLabel etq_icono;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelbar;
    private javax.swing.JTextField txtprueba;
    // End of variables declaration//GEN-END:variables
}