
package proyectoescritorio;


import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class StockForm extends javax.swing.JFrame {

    //creo enlace al Jframe anterios y las variables necesarias 
    private Home enlaceHome;
    String[][] info;
    String[][] info2;
        
    //constructor inicial
    public StockForm(Home home) {
        initComponents();
        
        //Logo Jframe, locacion centrada 
        setIconImage(new ImageIcon(getClass().getResource("/images/logo2.png")).getImage());      
        this.setLocationRelativeTo(null);
        label_confirmacion_stock.setVisible(false);
        button_validar_pedido.setVisible(false);
        enlaceHome = home;
                
        obtenerStock();
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        button_back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        button_validar_pedido = new javax.swing.JButton();
        button_anyadir_pedido = new javax.swing.JButton();
        label_stock_actual_actualizado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_ingredientes = new javax.swing.JTable();
        label_confirmacion_stock = new javax.swing.JLabel();
        btnAdministrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(237, 212, 172));

        button_back.setText("Back");
        button_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_backActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Stock");

        button_validar_pedido.setText("Validar");
        button_validar_pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_validar_pedidoActionPerformed(evt);
            }
        });

        button_anyadir_pedido.setText("Add Pedido");
        button_anyadir_pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_anyadir_pedidoActionPerformed(evt);
            }
        });

        label_stock_actual_actualizado.setFont(new java.awt.Font("Segoe Script", 1, 18)); // NOI18N
        label_stock_actual_actualizado.setForeground(new java.awt.Color(0, 0, 0));
        label_stock_actual_actualizado.setText("STOCK ACTUAL");

        tabla_ingredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Cantidad", "Fecha Caducidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_ingredientes.setRowHeight(30);
        tabla_ingredientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabla_ingredientes);
        if (tabla_ingredientes.getColumnModel().getColumnCount() > 0) {
            tabla_ingredientes.getColumnModel().getColumn(0).setResizable(false);
            tabla_ingredientes.getColumnModel().getColumn(1).setResizable(false);
            tabla_ingredientes.getColumnModel().getColumn(2).setResizable(false);
        }

        label_confirmacion_stock.setForeground(new java.awt.Color(0, 204, 0));
        label_confirmacion_stock.setText("Confirmado");

        btnAdministrar.setText("Editar Carta");
        btnAdministrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(button_back)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAdministrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label_confirmacion_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button_anyadir_pedido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(button_validar_pedido)))
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(label_stock_actual_actualizado, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(219, 219, 219))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(label_stock_actual_actualizado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_validar_pedido)
                    .addComponent(button_back)
                    .addComponent(button_anyadir_pedido)
                    .addComponent(label_confirmacion_stock)
                    .addComponent(btnAdministrar))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //vuelve al JFrame anterior 
    private void button_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_backActionPerformed
        enlaceHome.setVisible(true);
        dispose();
    }//GEN-LAST:event_button_backActionPerformed

    //captura la informacion introducida en la tabla y si es correcta ejecuta una consulta y lo graba en bbdd
    private void button_validar_pedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_validar_pedidoActionPerformed
        
        
        Conector_bbdd conexion = new Conector_bbdd();
               
        ArrayList<productoStock> productosActualizados = new ArrayList<>();       
        
        Integer errorCheck = 0;
        //captura los datos de la tabla 
        for (int i = 0; i < (tabla_ingredientes.getRowCount()); i++) {
         
            String nombreTMP = (String)( tabla_ingredientes.getValueAt(i, 0)); 
            String cantidadTMP = (String)( tabla_ingredientes.getValueAt(i, 1)); 
            String fechaTMP = (String)( tabla_ingredientes.getValueAt(i, 2)); 
                     
            if (cantidadTMP != null && fechaTMP != null ) {                                
                
                if(dateIsValid(fechaTMP) && isInteger(cantidadTMP)){
                    //creo un temporal de tipo productoStock 15.20 16,85
                    productoStock temporal = new productoStock();

                    temporal.setNombre(nombreTMP);
                    temporal.setCantidad(Integer.parseInt(cantidadTMP));
                    temporal.setFecha_Cad(fechaTMP);
                    
                    label_confirmacion_stock.setForeground(Color.green); 
                    label_confirmacion_stock.setText("CORRECTO");
                    label_confirmacion_stock.setVisible(true);

                    //añado el temporal en el ArrayList de productos actualizados 
                    productosActualizados.add(temporal);
                    button_validar_pedido.setVisible(false);
                    button_anyadir_pedido.setVisible(true);

                } else {
                    //fecha no valida - haz cosas
                    //añadir color rojo al guy - mas tarde (o nunca)
                    label_confirmacion_stock.setForeground(Color.red);                  
                    label_confirmacion_stock.setText("ERROR");
                    label_confirmacion_stock.setVisible(true);
                            
                    errorCheck++;
                }                
            }  
        }
        
        
        
        if(errorCheck != 0){
            //mal
           
        } else {           
            //bien
            //actualiza bbdd con la informacion nueva 
            conexion.actualizarBBDD(productosActualizados);
            obtenerStock();
            button_validar_pedido.setVisible(false);
            button_anyadir_pedido.setVisible(true);
        }
    }//GEN-LAST:event_button_validar_pedidoActionPerformed

    //compruebo si texto es entero
    public boolean isInteger(String currentInt){
        return currentInt.matches("\\d+");
    }
    
    //compruebo formato fecha
    public boolean dateIsValid(String dateStr) {
        System.out.println(dateStr);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    //abre una ventana nueva para captar pedido nuevo 
    private void button_anyadir_pedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_anyadir_pedidoActionPerformed
        Conector_bbdd conexion= new Conector_bbdd();
        tabla_ingredientes.setEnabled(true);
        
        label_confirmacion_stock.setVisible(false);
        button_anyadir_pedido.setVisible(false);
        button_validar_pedido.setVisible(true);
        label_stock_actual_actualizado.setVisible(false);
        //creo un vector con los productos existentes y relleno la tabla 
        ArrayList<String> productos = conexion.obtenerPlatosCarta();
        info2 = new String[productos.size()][3];        
        
        for(int i = 0; i < info2.length; i++){
            info2[i][0] = productos.get(i); 
            info[i][1] = "0";
        }
        
        //actualizo la tabla con los nombres de los platos 
        tabla_ingredientes.setModel(new javax.swing.table.DefaultTableModel(
            info2,
            new String [] {
                "Nombre", "Cantidad", "Fecha_Cad"
            }
        ));
    }//GEN-LAST:event_button_anyadir_pedidoActionPerformed

    private void btnAdministrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrarActionPerformed
        MenuEditForm editor = new MenuEditForm(this);
        editor.setVisible(true);
         
        this.setVisible(false);
    }//GEN-LAST:event_btnAdministrarActionPerformed
    //devuelve las existencias de la bbdd con sus cantidades y respectivas fechas 
    //capturación de stock e impresión 
    private void obtenerStock() {
       
        ArrayList<productoStock> stock = new ArrayList<>();                        
        Conector_bbdd conexion = new Conector_bbdd();             
        
        //iguala el ArrayList al devuelto por la bbdd
        stock = conexion.obtenerStock();
                
        conexion.close();        
        
        //matriz donde guardo los datos de bbdd
        info = new String[stock.size()][3];
        
        //guardo la información de stock en una matriz 
        for(int i = 0; i < stock.size(); i++){            
            info[i][0] = stock.get(i).getNombre();
            info[i][1] = Integer.toString(stock.get(i).getCantidad());
            info[i][2] = stock.get(i).getFecha_Cad();          
        }
        
        //imprimo en la tabla la información de la matriz 
        tabla_ingredientes.setModel(new javax.swing.table.DefaultTableModel(
            info,
            new String [] {
                "Nombre", "Cantidad", "Fecha_Cad"
            }
        ));
        tabla_ingredientes.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdministrar;
    private javax.swing.JButton button_anyadir_pedido;
    private javax.swing.JButton button_back;
    private javax.swing.JButton button_validar_pedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_confirmacion_stock;
    private javax.swing.JLabel label_stock_actual_actualizado;
    private javax.swing.JTable tabla_ingredientes;
    // End of variables declaration//GEN-END:variables

}