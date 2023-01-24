
package proyectoescritorio;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Home extends javax.swing.JFrame {
    
//panel fondo con foto
    FondoPanel fondo = new FondoPanel();
    public LiveForm newLive ;
    
    //constructor
    public Home() {
        this.setContentPane(fondo);   
                
        initComponents();  
        
        buton_historial.setVisible(false);
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/images/logo2.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel_home = new FondoPanel();
        butonGoLive = new javax.swing.JButton();
        buton_stock = new javax.swing.JButton();
        buton_historial = new javax.swing.JButton();
        label_logo = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_home.setForeground(new java.awt.Color(204, 204, 204));

        butonGoLive.setBackground(new java.awt.Color(255, 255, 255));
        butonGoLive.setForeground(new java.awt.Color(0, 0, 0));
        butonGoLive.setText("Go LIVE");
        butonGoLive.setName(""); // NOI18N
        butonGoLive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonGoLiveActionPerformed(evt);
            }
        });

        buton_stock.setText("Administración");
        buton_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buton_stockActionPerformed(evt);
            }
        });

        buton_historial.setText("Historial");
        buton_historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buton_historialActionPerformed(evt);
            }
        });

        label_logo.setFont(new java.awt.Font("Segoe Script", 1, 24)); // NOI18N
        label_logo.setForeground(new java.awt.Color(255, 51, 51));
        label_logo.setText("Machala's grill");

        javax.swing.GroupLayout panel_homeLayout = new javax.swing.GroupLayout(panel_home);
        panel_home.setLayout(panel_homeLayout);
        panel_homeLayout.setHorizontalGroup(
            panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_homeLayout.createSequentialGroup()
                .addGroup(panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_homeLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(butonGoLive)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buton_historial)
                        .addGap(65, 65, 65)
                        .addComponent(buton_stock))
                    .addGroup(panel_homeLayout.createSequentialGroup()
                        .addContainerGap(154, Short.MAX_VALUE)
                        .addComponent(label_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
        panel_homeLayout.setVerticalGroup(
            panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_homeLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(label_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                .addGroup(panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butonGoLive)
                    .addComponent(buton_historial)
                    .addComponent(buton_stock))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //boton que abre JFrame del stock
    private void buton_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buton_stockActionPerformed
        //crea nuevo stockForm y le pasa este para poder volver a la misma sesión
        StockForm newStock = new StockForm(this);
        
        newStock.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_buton_stockActionPerformed
    //boton que abre el JFrame de LiveForm
    private void butonGoLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonGoLiveActionPerformed
        
        buton_historial.setVisible(true);
        
        //crea nuevo LiveForm y le pasa este para poder volver a la misma sesión
        newLive = new LiveForm(this);
        
        newLive.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_butonGoLiveActionPerformed

    //crea un actividad nueva donde nos muestra el progreso diario 
    private void buton_historialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buton_historialActionPerformed
        
        ArrayList<Productos> ordenados = new ArrayList<>();
         
        
        if(newLive.getProductos_CSV().size() > 0){
            ordenados = newLive.getProductos_CSV();
            
            this.setVisible(false);
            HistorialDia history = new HistorialDia(this, ordenados);
            history.setVisible(true);
        }
    }//GEN-LAST:event_buton_historialActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonGoLive;
    private javax.swing.JButton buton_historial;
    private javax.swing.JButton buton_stock;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label_logo;
    private javax.swing.JPanel panel_home;
    // End of variables declaration//GEN-END:variables

}

