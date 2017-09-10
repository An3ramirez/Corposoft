/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Model.*;
import com.sun.glass.events.KeyEvent;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author an3-r
 */
public class Estadisticas extends javax.swing.JDialog implements Runnable{
  static Connection conn=null;
  static Statement st=null;
  static ResultSet rs=null;
    
    
  String hora, minutos, segundos, ampm;
  Calendar calendario;
  Thread h1;
    
 java.util.Date now=new java.util.Date();
 java.text.SimpleDateFormat sdf=new
 java.text.SimpleDateFormat("dd-MM-yy");
 
 String fecha;
 int opcionSelect;
 boolean FiltroBus=false;

    /**
     * Creates new form Estadisticas
     */
    public Estadisticas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        //Iniciando formato fecha y el hilo de la fecha en tiempo real
        fecha=sdf.format(now);
        h1 = new Thread(this);
        h1.start();
        opcionSelect=0;
    }
    
    void mostrarEgresos(){
        
        cBoxFiltro.removeAllItems();
        cBoxFiltro.addItem("Buscar por");
        cBoxFiltro.addItem("id");
        cBoxFiltro.addItem("title");
        cBoxFiltro.addItem("detail");
        cBoxFiltro.addItem("value");
        cBoxFiltro.addItem("Users_id");
        
        if(!"Buscar por".equals(cBoxFiltro.getSelectedItem().toString())){
          FiltroBus=true;  
          System.out.println("Entro al if");
        }
          
        String busqueda = txtBuscar.getText();
        String filtro = cBoxFiltro.getSelectedItem().toString();
        String where = "";
        if(!"".equals(busqueda))
        {
            where = "WHERE "+filtro+" LIKE '" + busqueda +"%'";
        }
        DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("ID REGISTRO");
        modelo.addColumn("TITULO");
        modelo.addColumn("DETALLE");
        modelo.addColumn("VALOR $");
        modelo.addColumn("ID USUARIO");
        
        jTable1.setModel(modelo);
        String sql="";
        
            sql="SELECT id, title, detail, value, Users_id FROM expenditures "+where+" ORDER BY id ";
        
        
        
        String []datos = new String [5];
     try {
         conn=Conexion.Enlace(conn);
         Statement st = conn.createStatement();
         
         ResultSet rs= st.executeQuery(sql);
         ResultSetMetaData rsMd = rs.getMetaData();
         int cantidadColumnas = rsMd.getColumnCount();
         
         int[] anchos = {60,200,480,70,50};
        
        for (int i = 0; i < cantidadColumnas; i++) 
        {
             jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
         
         while(rs.next()){
             datos[0]=rs.getString(1);
             datos[1]=rs.getString(2);
             datos[2]=rs.getString(3);
             datos[3]=rs.getString(4);
             datos[4]=rs.getString(5);
             modelo.addRow(datos);
         }
         jTable1.setModel(modelo);
     } catch (SQLException ex) {
         System.out.println("Error consultado egresos "+ex);
     }
     
     //consultando el total
     try {
        conn=Conexion.Enlace(conn);
         Statement st = conn.createStatement();
         
         ResultSet rs= st.executeQuery("SELECT sum(value) FROM expenditures ");
         rs.next();
         
         txtTotal.setText("$ "+rs.getString(1));
         
         
        
    } catch (Exception e) {
    }
     
     
    }
    
    void mostrarFacturacion(){
        DefaultTableModel modelo= new DefaultTableModel();
        modelo.addColumn("Nº FACTURA");
        modelo.addColumn("NOMBRES");
        modelo.addColumn("Nº DOCUMENTO");
        modelo.addColumn("FECHA");
        modelo.addColumn("VALOR");
        
        jTable1.setModel(modelo);
        String sql="";
        
            sql="SELECT * FROM bill ORDER BY id ";
        
        
        
        String []datos = new String [5];
     try {
         conn=Conexion.Enlace(conn);
         Statement st = conn.createStatement();
         
         ResultSet rs= st.executeQuery(sql);
         while(rs.next()){
             datos[0]=rs.getString(1);
             datos[1]=rs.getString(2);
             datos[2]=rs.getString(3);
             datos[3]=rs.getString(4);
             datos[4]=rs.getString(5);
             modelo.addRow(datos);
         }
         jTable1.setModel(modelo);
     } catch (SQLException ex) {
         System.out.println("Error consultado las facturas "+ex);
     }
     
     //consultando el total
     try {
        conn=Conexion.Enlace(conn);
         Statement st = conn.createStatement();
         
         ResultSet rs= st.executeQuery("SELECT sum(valor) FROM bill ");
         rs.next();
         
         txtTotal.setText("$ "+rs.getString(1));
         
         
        
    } catch (Exception e) {
    }
     
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblRelog = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cBoxItemBusqueda = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        DateChooserIni = new com.toedter.calendar.JDateChooser();
        lblInicial = new javax.swing.JLabel();
        jDateChooserFinal = new com.toedter.calendar.JDateChooser();
        lblFinal = new javax.swing.JLabel();
        lblAnimacion = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        cBoxFiltro = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estadisticas");
        setIconImage(null);

        jLabel1.setFont(new java.awt.Font("Simson", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Estadisticas");

        lblRelog.setFont(new java.awt.Font("Sho-Card-Caps", 1, 36)); // NOI18N
        lblRelog.setForeground(new java.awt.Color(153, 153, 0));
        lblRelog.setText("00:00.00 AM");
        lblRelog.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha y Hora", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Estadistica_80x80px.png"))); // NOI18N

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setRowHeight(30);
        jTable1.setRowMargin(3);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(250);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Opciones", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        cBoxItemBusqueda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cBoxItemBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un opción", "Egresos", "Facturación" }));
        cBoxItemBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosMejorados/Comprobar/comprobar_norm.png"))); // NOI18N
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosMejorados/Comprobar/comprobar_press.png"))); // NOI18N
        btnBuscar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosMejorados/Comprobar/comprobar_roll.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblInicial.setText("Fecha Inicial.");

        lblFinal.setText("Fecha Final.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInicial)
                    .addComponent(DateChooserIni, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFinal)
                    .addComponent(jDateChooserFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblInicial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DateChooserIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cBoxItemBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cBoxItemBusqueda))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        lblAnimacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Grafico-Sectores-81762.gif"))); // NOI18N
        lblAnimacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnimacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAnimacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnimacionMouseClicked(evt);
            }
        });
        lblAnimacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lblAnimacionKeyTyped(evt);
            }
        });

        lblTotal.setFont(new java.awt.Font("Thunderstrike Condensed", 0, 36)); // NOI18N
        lblTotal.setText("Total:");

        txtTotal.setBackground(new java.awt.Color(0, 102, 102));
        txtTotal.setFont(new java.awt.Font("Orator Std", 0, 36)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(102, 255, 0));
        txtTotal.setText("$ 0");
        txtTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtBuscar.setBackground(new java.awt.Color(0, 102, 102));
        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        cBoxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(lblRelog, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cBoxFiltro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(lblAnimacion)
                                .addGap(62, 62, 62)))
                        .addComponent(lblLogo)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addGap(48, 48, 48))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRelog))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAnimacion)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cBoxFiltro, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))))
                    .addComponent(lblLogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if(cBoxItemBusqueda.getSelectedItem()=="Egresos"){
            mostrarEgresos();
            opcionSelect=1;
        }else if(cBoxItemBusqueda.getSelectedItem()=="Facturación"){
            mostrarFacturacion();
            opcionSelect=2;
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void lblAnimacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblAnimacionKeyTyped
        
        
    }//GEN-LAST:event_lblAnimacionKeyTyped

    private void lblAnimacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnimacionMouseClicked
        lblAnimacion.setVisible(false);
    }//GEN-LAST:event_lblAnimacionMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(evt.getClickCount() == 2){
            
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        char cTeclaPresionada=evt.getKeyChar();
        
        if(cTeclaPresionada!=KeyEvent.VK_ALT){
            if(opcionSelect==1){
                System.out.println("Valor del combo "+cBoxFiltro.getSelectedItem().toString());
                if(FiltroBus){
                   mostrarEgresos();
            
                }else{
                    JOptionPane.showMessageDialog(null,"No a seleccionado un ítem de búsqueda", "Error no hay ítem seleccionado", JOptionPane.ERROR_MESSAGE); 
                }
            
        }else if(opcionSelect==2){
            mostrarFacturacion();
        }
       }
        
        
    }//GEN-LAST:event_txtBuscarKeyTyped

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Estadisticas dialog = new Estadisticas(new javax.swing.JFrame(), true);
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
    private com.toedter.calendar.JDateChooser DateChooserIni;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cBoxFiltro;
    private javax.swing.JComboBox<String> cBoxItemBusqueda;
    private com.toedter.calendar.JDateChooser jDateChooserFinal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAnimacion;
    private javax.swing.JLabel lblFinal;
    private javax.swing.JLabel lblInicial;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblRelog;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
         Thread ct= Thread.currentThread();
        
        while(ct==h1){
            
            calcula();
            lblRelog.setText(fecha+"  "+hora+":"+minutos+":"+segundos+" "+ampm);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
    }
    
    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date fechaHoraActual = new java.util.Date();
        
        calendario.setTime(fechaHoraActual);
        ampm= calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
        
        if(ampm.equals("PM")){
            int h=calendario.get(Calendar.HOUR_OF_DAY)-12;
            hora = h>9?""+h:"0"+h;
        }else{
            hora= calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY); 
        }
        minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }
}
