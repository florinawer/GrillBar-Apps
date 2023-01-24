
package proyectoescritorio;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MenuEditForm extends javax.swing.JFrame {
    
    public StockForm stock_form;
    public int eleccion ;
    public MenuEditForm(StockForm stockForm) {
        initComponents();
        
        stock_form = stockForm;
        btn_buscar_foto.setEnabled(false);
        eleccion = 0;
        lbl_validacion.setVisible(false);
        
        //posicionamiento centrado
        this.setLocationRelativeTo(null);     
        setIconImage(new ImageIcon(getClass().getResource("/images/logo2.png")).getImage());
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        panel_administracion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_drop_plato = new javax.swing.JButton();
        btn_add_plato = new javax.swing.JButton();
        input_nombre_plato = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_validar = new javax.swing.JButton();
        btn_buscar_foto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbl_imagen = new javax.swing.JLabel();
        lbl_ruta = new javax.swing.JLabel();
        btn_atras = new javax.swing.JButton();
        lbl_validacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(237, 212, 172));

        panel_administracion.setBackground(new java.awt.Color(237, 212, 172));

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 36)); // NOI18N
        jLabel1.setText("Administración Menú");

        btn_drop_plato.setText("Borrar Plato");
        btn_drop_plato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_drop_platoActionPerformed(evt);
            }
        });

        btn_add_plato.setText("Añadir Plato");
        btn_add_plato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_platoActionPerformed(evt);
            }
        });

        input_nombre_plato.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Nombre");

        btn_validar.setText("Validar");
        btn_validar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_validarActionPerformed(evt);
            }
        });

        btn_buscar_foto.setText("buscar");
        btn_buscar_foto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_fotoActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Imagen");

        lbl_ruta.setBackground(new java.awt.Color(204, 204, 204));
        lbl_ruta.setForeground(new java.awt.Color(51, 51, 51));
        lbl_ruta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_atras.setText("Atras");
        btn_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atrasActionPerformed(evt);
            }
        });

        lbl_validacion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbl_validacion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panel_administracionLayout = new javax.swing.GroupLayout(panel_administracion);
        panel_administracion.setLayout(panel_administracionLayout);
        panel_administracionLayout.setHorizontalGroup(
            panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_administracionLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panel_administracionLayout.createSequentialGroup()
                        .addGroup(panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel_administracionLayout.createSequentialGroup()
                                .addGroup(panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel_administracionLayout.createSequentialGroup()
                                        .addComponent(btn_drop_plato)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_add_plato))
                                    .addComponent(input_nombre_plato, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_administracionLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel_administracionLayout.createSequentialGroup()
                                        .addComponent(lbl_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_buscar_foto))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_administracionLayout.createSequentialGroup()
                                .addComponent(btn_atras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_validacion, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_validar)))
                        .addGap(117, 117, 117))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
        );
        panel_administracionLayout.setVerticalGroup(
            panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_administracionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_drop_plato)
                    .addComponent(btn_add_plato))
                .addGap(54, 54, 54)
                .addGroup(panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_administracionLayout.createSequentialGroup()
                        .addGroup(panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(input_nombre_plato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_buscar_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1))
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addComponent(lbl_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(panel_administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_validar)
                    .addComponent(btn_atras)
                    .addComponent(lbl_validacion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_administracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_administracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscar_fotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_fotoActionPerformed
        lbl_imagen.setVisible(true);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG)","jpg","jpeg");
        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        //File ruta = new File("");
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file = archivo.getSelectedFile();
            lbl_ruta.setText(String.valueOf(file));

            Image foto = getToolkit().getImage(lbl_ruta.getText());
            foto = foto.getScaledInstance(200, 130, Image.SCALE_DEFAULT);
            lbl_imagen.setIcon( new ImageIcon(foto));
        }
    }//GEN-LAST:event_btn_buscar_fotoActionPerformed

    private void btn_drop_platoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_drop_platoActionPerformed
        eleccion = 1;
        btn_drop_plato.setBackground(Color.GREEN);
        btn_add_plato.setBackground(Color.GRAY);
        btn_buscar_foto.setEnabled(false);
        lbl_validacion.setVisible(false);
        lbl_imagen.setIcon(null);
         lbl_ruta.setText("");
    }//GEN-LAST:event_btn_drop_platoActionPerformed

    private void btn_add_platoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_platoActionPerformed
        eleccion = 2;
        btn_add_plato.setBackground(Color.GREEN);
        btn_drop_plato.setBackground(Color.GRAY);
        btn_buscar_foto.setEnabled(true);
        lbl_validacion.setVisible(false);
        lbl_imagen.setIcon(null);
         lbl_ruta.setText("");

    }//GEN-LAST:event_btn_add_platoActionPerformed

    private void btn_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atrasActionPerformed
        stock_form.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_atrasActionPerformed

    private void btn_validarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_validarActionPerformed
        Conector_bbdd conexion = new Conector_bbdd();
        
        if(!input_nombre_plato.getText().equals("")){
           
            if(eleccion == 1){
                if(conexion.borrar_platos_carta(input_nombre_plato.getText())){
                    lbl_validacion.setVisible(true);
                    lbl_validacion.setText("Borrado");
                    lbl_validacion.setForeground(Color.BLACK);  
                    
                    input_nombre_plato.setText("");
                    
                }else{
                    lbl_validacion.setText("error");
                    lbl_validacion.setForeground(Color.RED);
                }                
                
            }else if(eleccion == 2 && lbl_ruta.getText() != ""){
                if(conexion.add_platos_carta(input_nombre_plato.getText(), String.valueOf(lbl_ruta.getText()))){
                    lbl_validacion.setVisible(true);
                    lbl_validacion.setText("Añadido");
                    lbl_validacion.setForeground(Color.BLACK);
                    
                    input_nombre_plato.setText("");
                    lbl_ruta.setText("");
                }else{
                    lbl_validacion.setText("error");
                    lbl_validacion.setForeground(Color.RED);
                }
            }else{
                lbl_validacion.setText("error");
                lbl_validacion.setForeground(Color.RED);
            }
        }else{
            lbl_validacion.setText("ERROR"); 
            lbl_validacion.setForeground(Color.RED);
            lbl_validacion.setVisible(true);

        }
    }//GEN-LAST:event_btn_validarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_plato;
    private javax.swing.JButton btn_atras;
    private javax.swing.JButton btn_buscar_foto;
    private javax.swing.JButton btn_drop_plato;
    private javax.swing.JButton btn_validar;
    private javax.swing.JTextField input_nombre_plato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_imagen;
    private javax.swing.JLabel lbl_ruta;
    private javax.swing.JLabel lbl_validacion;
    private javax.swing.JPanel panel_administracion;
    // End of variables declaration//GEN-END:variables
}
