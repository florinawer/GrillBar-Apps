
package proyectoescritorio;


import com.csvreader.CsvWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


public class LiveForm extends javax.swing.JFrame {
    
    //creo enlace a la actividad anterior así poder volver y usar la misma versión de la misma 
    private Home enlaceHome;
    
    //variables necesarias
    public boolean back_up_realizado ;
    
    private ArrayList<Panel_mesa> paneles;
    private ArrayList<Productos>productos_CSV;
    private int salida = 0;    
    public int posicion = 1;
    
    //panel personalziado para así añadir foto de fondo
    public Panel_mesa panel_temporal;
    
    //constructor    
    public LiveForm(Home home) {
        
        initComponents();        
        System.out.println("Event: LiveForm");
        back_up_realizado = false;
        
        start_hilos();       
                                
        //setLogo esquina izquierda
        setIconImage(new ImageIcon(getClass().getResource("/images/logo2.png")).getImage());
         
        //posicionamiento centrado
        this.setLocationRelativeTo(null);        
        
        //guardo referencia a Jframe anterior
        enlaceHome = home;
        
        //inicializo Arrays
        productos_CSV = new ArrayList<>();
        paneles = new ArrayList<>(); 
        
       
    }

    public ArrayList<Productos> getProductos_CSV() {
        return productos_CSV;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        button_back = new javax.swing.JButton();
        button_cerrar_dia = new javax.swing.JButton();
        panel_encima_scrolll = new javax.swing.JScrollPane();
        panel_encima_scroll = new javax.swing.JPanel();
        label_comprobando = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);

        button_back.setText("Back");
        button_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_backActionPerformed(evt);
            }
        });

        button_cerrar_dia.setText("Cerrar Día");
        button_cerrar_dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_cerrar_diaActionPerformed(evt);
            }
        });

        panel_encima_scrolll.setPreferredSize(new java.awt.Dimension(10, 10));

        panel_encima_scroll.setLayout(new java.awt.GridLayout(0, 4, 1, 2));
        panel_encima_scrolll.setViewportView(panel_encima_scroll);

        label_comprobando.setText("Comprobando...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(button_back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 455, Short.MAX_VALUE)
                        .addComponent(label_comprobando, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button_cerrar_dia))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_encima_scrolll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_encima_scrolll, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_back)
                    .addComponent(button_cerrar_dia)
                    .addComponent(label_comprobando))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //recibe el ArrayList con las mesas ordenadas 
    //dibuja los paneles_mesa y guarda la información de cada pedido
    public void ejecutar(ArrayList<Productos> ordenes){            
        
        if(ordenes.size() > 0){                  
            
            panel_temporal = new Panel_mesa();                                                             
            
            //elimino platos pero sumo en +1 el plato repetido 
            eliminar_duplicados(ordenes);                                   
                        
            //captura la informacion sobre los platos ordenados            
            panel_temporal.capturar_info(ordenes);                                                                                                                                                                            
                       
            //agrego el nuevo panel_temporal 
            panel_encima_scroll.add(panel_temporal);                                                                               
                                  
            this.posicion++;                                                 
            
            //guardo los paneles en un ArrayList, guardo los platos y refresco el panel_temporal
            paneles.add(panel_temporal);                         
            panel_temporal.addPlatos();  
            panel_encima_scroll.updateUI();
            
            //recorro todas las ordenes y las agrego al ArrayList CSV
            for(int i = 0; i < ordenes.size(); i++){
                productos_CSV.add(ordenes.get(i));
            }
            //vacio el array de ordenes a la espera de ordenes nuevas 
   
            ordenes.clear();
        }                      
    }     
    
    //elimino platos pero sumo en +1 el plato repetido 
    public void eliminar_duplicados(ArrayList<Productos>ordenes){
   
        ArrayList<Integer>borrables = new ArrayList<>();               
         
        //recorro ordenes
        for (int i = 0; i < ordenes.size(); i++){
            for (int j = i+1; j < ordenes.size(); j++){    
                
                //hago un equals de Objetos y sumo platos repetidos en caso de equals
                if(ordenes.get(i).equals(ordenes.get(j))){                    
                    ordenes.get(i).setNumero_platos_repetidos(1);                    
                    //si el indice j no existe en la lista de borrables que lo añada
                    if(borrables.indexOf(j) == -1){
                        borrables.add(j);
                    }                   
                }
            }
        } 
        //ordeno el ArrayList así al borrar no altero el orden y no borro el que no toca 
        Collections.sort(borrables);
         
        //recorriendo al revés el ArrayList no se mueven de sitio los indices buscados 
        //Ej: indices buscados 2, 7, 9 -si borrara el 2 primero el 7 pasaría a ser 6 , y cuando llegue el turno de 9 sería 7
        for (int f = borrables.size()-1; f >= 0; f--){
            ordenes.remove((int)borrables.get(f));
        }               
    }
    
    //dejo atrás el Frame actual y vuelvo al anterior 
    private void button_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_backActionPerformed
            
        salida =1;
        enlaceHome.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_button_backActionPerformed

    //hago un set a 0 del AUTO_INCREMENTAL de la base de datos al final del día para evitar que suba hasta el infinito 
    private void button_cerrar_diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cerrar_diaActionPerformed
                        
        Calendar date = Calendar.getInstance();
        
        label_comprobando.setVisible(true);
        label_comprobando.setText("Día cerrado.");
        
        String dia = Integer.toString(date.get(Calendar.DATE));
        String mes = Integer.toString(date.get(Calendar.MONTH)+1);
        String year = Integer.toString(date.get(Calendar.YEAR));       
        CsvWriter csvWriter = new CsvWriter(dia+"-"+mes+"-"+year + ".csv", ';', Charset.forName("UTF-8"));
        //CsvWriter csvWriter = new CsvWriter(dia+"-"+mes+"-"+year + ".csv");
        
        String[] modelo = {"Numero Mesa", "Nombre", "Punto", "Guarnición", "Salsa", "Cantidad", "Atiende", "Hora pedido"};
        
        try {
            csvWriter.writeRecord(modelo);
        } catch (IOException ex) {
            Logger.getLogger(LiveForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Productos producto : productos_CSV){
            String [] datos = producto.getArray();
            try {
                               
                csvWriter.writeRecord(datos);
                
            } catch (IOException ex) {
                Logger.getLogger(LiveForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
             
        csvWriter.close();
        
        Conector_bbdd conexion = new Conector_bbdd();
        
        button_cerrar_dia.setText("Cerrado");
        salida = 1;
        
        //Autonumerico reseteado a 1
        conexion.resetAuto();
    }//GEN-LAST:event_button_cerrar_diaActionPerformed
    
    //este metodo es el que hace la conexion entre la comprobación de la bbdd y la manipulación de los datos 
    public void comprobar_update(){        
       
        ArrayList<Productos> ordenes = new ArrayList<>();      
        
        Conector_bbdd conexion = new Conector_bbdd();               
                
        //el nuevo ArrayList creado capta los datos devueltos por la bbdd
        ordenes = conexion.obtener_ordenes();        
         
        //compruebo que haya valores en el ArrayList 
        if(ordenes.size() > 0){
            
            for(int i = 0; i < ordenes.size(); i++){               
                System.out.println(ordenes.get(i).getNombre());
                                             
                //ya está guardado en el ArrayList de ordenes y procedo a su borrado
                conexion.borrar_reserva(ordenes.get(0).getNumero()); 
            }
        }       
        //cierro conexión
        conexion.close();    
        
        //mando el ArrayList al metodo ejecutar 
        ejecutar(ordenes);        
    }
       
    public void start_hilos(){                
              
        salida = 0;   
        label_comprobando.setVisible(true);
        label_comprobando.setText("Comprobando...");                       
                 
        Thread t1 = new Thread(new Runnable(){
           
            public void run(){               
                while(salida != 1){
                    
                    try{                        
                        System.out.println("Comprobando..."); 
                        
                        Thread.sleep(1000); // Espera 1 segundo
                        
                        //llama al metodo                                                  
                        if(!back_up_realizado){
                            realizar_back_up_ordenes();
                            back_up_realizado = true;
                     
                        }
                        comprobar_update(); 
                        
                        //si encuentra algún panel_temporal hace update
                        if(paneles.size() != 0){
                            for(int i = 0; i < paneles.size(); i++){
                                paneles.get(i).updateTimeOpenFor();
                                if(paneles.get(i).isCompletado()){
                                    panel_encima_scroll.remove(paneles.get(i));
                                    panel_encima_scroll.updateUI();
                                }
                            }
                        }                       
                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(LiveForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                                        
                }    //mientras no se pulse el boton atras seguirá trabajando
            } 
        });
        t1.start();                          
    }
    
    public void realizar_back_up_ordenes(){               
       
        Conector_bbdd conexion = new Conector_bbdd();   
        ArrayList<Productos> ordenes = new ArrayList<>();                                  
                        
        conexion.vaciar_ordenes_pendientes();
               
        //el nuevo ArrayList creado captura los datos devueltos por la bbdd
        ordenes = conexion.obtener_ordenes_back_up();        
       
        //cierro conexión
        conexion.close();                  
        
        ArrayList<ProductosObject> productosObject = new ArrayList<>();                        
        ArrayList<Integer>numerosFinales = new ArrayList<>();

        //guardo los numeros de mesas en un array
        ArrayList<Productos>temporal_productos;        
               
        //en numeros finales de mesas guardo solamente 1 vez cada numero
        for(int i = 0; i < ordenes.size(); i++){  
            if(numerosFinales.indexOf(ordenes.get(i).getNumero()) == -1){
                numerosFinales.add(ordenes.get(i).getNumero());
            }                                  
        }                          
        //busco en los datos devueltos por la bbdd todas las comandas que coincidan 
        //en el numero de mesa y los guardo juntas en un indice del ArrayList de tipo ProductosObject                    
        for(int j = 0; j < numerosFinales.size(); j++){            
            temporal_productos = new ArrayList<Productos>();
            for(int i = 0; i < ordenes.size(); i++){            
                if(numerosFinales.get(j) == ordenes.get(i).getNumero()){
                    temporal_productos.add(ordenes.get(i));                    
                }
            }                                  
            productosObject.add(new ProductosObject(temporal_productos));          
        }
        //recorro el arraylist y ejecuto para cada indice existente  
        //pasandole al método ejecutar un arraylist de tipo Productos
               
        for(int i = 0; i < productosObject.size(); i++){   
                       
            ejecutar(productosObject.get(i).getProductos());            
        }
    }
    
    //metodo para recuperar ordenes en marcha 
    //si hay un cierre inesperado o un corte de luz 
    //detiene los hilos y toda la actualización del programa 
    //supuestamente es para el final del día 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_back;
    private javax.swing.JButton button_cerrar_dia;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label_comprobando;
    private javax.swing.JPanel panel_encima_scroll;
    private javax.swing.JScrollPane panel_encima_scrolll;
    // End of variables declaration//GEN-END:variables
}
