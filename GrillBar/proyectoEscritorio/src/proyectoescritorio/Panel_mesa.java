
package proyectoescritorio;

import java.util.ArrayList;

public class Panel_mesa extends javax.swing.JPanel {
    
    //variables necesarias
    boolean completado;
    int minutos = 0; 
    int segundos = 0;
    int horas = 0;
        
    private ArrayList<Plato_card>platos;
    
    //constructor
    public Panel_mesa() {                         
                       
        initComponents();
                
        completado = false;
        platos = new ArrayList<>();                                 
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
     
    //llega la información y se transforma en Objetos de tipo Plato_card
    public void capturar_info(ArrayList<Productos>ordenes){        
             
        //en la etiqueta mesa se imprime el numero de la mesa 
        label_numero_mesa.setText(String.valueOf(ordenes.get(0).getNumero()));
        label_atiende.setText(ordenes.get(0).getAtiende());
        label_atiende.setVisible(true);
        label_numero_mesa.setVisible(true);
        
        //recorro el ArrayList ordenes y creo un Objeto de tipo Plato_card por cada indice
        for(int i = 0; i < ordenes.size(); i++){
            platos.add(new Plato_card(ordenes.get(i).getNombre(), 
                    ordenes.get(i).getSide(), 
                    ordenes.get(i).getPunto(),
                    ordenes.get(i).getSalsa(), 
                    ordenes.get(i).getNumero_platos_repetidos()));                     
        }    
    }    
    
    //se agregan al panel todos los Plato_cards con un Layout de tipo  grid layout
    public void addPlatos(){
        
        for(int i = 0; i < platos.size(); i++){                
            panel_encima_scroll3.add(platos.get(i));
        }    
    }
 
    //se calcula y se imprime el tiempo de apertura de cada comanda 
    public void updateTimeOpenFor(){               
        
        //calculo de segundo /minutos/ horas
        if(minutos != 59){
            if(segundos != 59){
                segundos += 1;                
            }else{
                segundos = 0;
                minutos +=1;
            }
        }else{
            horas += 1;
        }
                
        //se imprime en la etiqueta de tiempo.
        if(horas == 0){
           if(minutos == 0){               
               if(segundos < 10){
                   label_open_for_time.setText(String.valueOf("0"+ segundos));    
               }
               else{
                   label_open_for_time.setText(String.valueOf(segundos));    
               }
           }
           else if(minutos < 10 && minutos > 0){
               if(segundos < 10){
                   label_open_for_time.setText(String.valueOf("0"+minutos+" : 0"+ segundos));    
               }
               else{
                   label_open_for_time.setText(String.valueOf("0"+minutos+" : "+ segundos));
               }
           }           
           else{
               if(segundos < 10){
                   label_open_for_time.setText(String.valueOf(minutos +": 0"+segundos));    
               }
               else{
                   label_open_for_time.setText(String.valueOf(minutos+ ":"+segundos));    
               }  
           }                       
        }else{
            label_open_for_time.setText(String.valueOf("0"+horas +" : "+minutos +" : "+ segundos));    
        }        
    }           
          
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel_bottom = new javax.swing.JPanel();
        button_aparcar_orden = new javax.swing.JButton();
        button_llamar_camarero = new javax.swing.JButton();
        button_imprimir_orden = new javax.swing.JButton();
        button_completar_orden = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel_encima_scroll3 = new javax.swing.JPanel();
        panel1 = new java.awt.Panel();
        label_open_for_time = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        label_numero_mesa = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelasd = new javax.swing.JLabel();
        label_atiende = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(467, 243));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        button_aparcar_orden.setBackground(new java.awt.Color(204, 204, 204));
        button_aparcar_orden.setForeground(new java.awt.Color(0, 0, 0));
        button_aparcar_orden.setText("Futuras");
        button_aparcar_orden.setMaximumSize(new java.awt.Dimension(104, 32));
        button_aparcar_orden.setMinimumSize(new java.awt.Dimension(104, 32));

        button_llamar_camarero.setBackground(new java.awt.Color(204, 204, 204));
        button_llamar_camarero.setForeground(new java.awt.Color(0, 0, 0));
        button_llamar_camarero.setText("Funciones");

        button_imprimir_orden.setBackground(new java.awt.Color(204, 204, 204));
        button_imprimir_orden.setForeground(new java.awt.Color(0, 0, 0));
        button_imprimir_orden.setText("Imprimir");
        button_imprimir_orden.setMaximumSize(new java.awt.Dimension(104, 32));
        button_imprimir_orden.setMinimumSize(new java.awt.Dimension(104, 32));
        button_imprimir_orden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_imprimir_ordenActionPerformed(evt);
            }
        });

        button_completar_orden.setBackground(new java.awt.Color(204, 204, 204));
        button_completar_orden.setForeground(new java.awt.Color(0, 0, 0));
        button_completar_orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/comprobar_1.png"))); // NOI18N
        button_completar_orden.setText("Completar");
        button_completar_orden.setMaximumSize(new java.awt.Dimension(104, 32));
        button_completar_orden.setMinimumSize(new java.awt.Dimension(104, 32));
        button_completar_orden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_completar_ordenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_bottomLayout = new javax.swing.GroupLayout(panel_bottom);
        panel_bottom.setLayout(panel_bottomLayout);
        panel_bottomLayout.setHorizontalGroup(
            panel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_bottomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_aparcar_orden, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_llamar_camarero)
                .addGap(34, 34, 34)
                .addComponent(button_imprimir_orden, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_completar_orden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
        panel_bottomLayout.setVerticalGroup(
            panel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_bottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_aparcar_orden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_llamar_camarero)
                    .addComponent(button_imprimir_orden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_completar_orden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_encima_scroll3.setBackground(new java.awt.Color(204, 204, 204));
        panel_encima_scroll3.setLayout(new javax.swing.BoxLayout(panel_encima_scroll3, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panel_encima_scroll3);

        panel1.setBackground(new java.awt.Color(173, 202, 250));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Mesa");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Abierto hace :");

        label_numero_mesa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label_numero_mesa.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("PLATOS :");

        labelasd.setForeground(new java.awt.Color(0, 0, 0));
        labelasd.setText("Atiende :");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label_open_for_time, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_numero_mesa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 290, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelasd, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_atiende, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(label_numero_mesa, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_open_for_time, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelasd)
                    .addComponent(label_atiende)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_bottom, javax.swing.GroupLayout.PREFERRED_SIZE, 454, Short.MAX_VALUE))
                    .addComponent(panel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents
    
    //manda señal a la impresora conectada 
    private void button_imprimir_ordenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_imprimir_ordenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_imprimir_ordenActionPerformed
    
    //elimina la mesa al estar completada 
    private void button_completar_ordenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_completar_ordenActionPerformed
        
        //this.setVisible(false); 
        this.completado = true; 
        
        Conector_bbdd conexion = new Conector_bbdd();
        String numeroMesa = label_numero_mesa.getText().toString();
        conexion.eliminarBackUp(Integer.parseInt(numeroMesa));
        
        
        //panel_encima_scroll.updateUI();
        
    }//GEN-LAST:event_button_completar_ordenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_aparcar_orden;
    private javax.swing.JButton button_completar_orden;
    private javax.swing.JButton button_imprimir_orden;
    private javax.swing.JButton button_llamar_camarero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_atiende;
    private javax.swing.JLabel label_numero_mesa;
    private javax.swing.JLabel label_open_for_time;
    private javax.swing.JLabel labelasd;
    private java.awt.Panel panel1;
    private javax.swing.JPanel panel_bottom;
    private javax.swing.JPanel panel_encima_scroll3;
    // End of variables declaration//GEN-END:variables
}
