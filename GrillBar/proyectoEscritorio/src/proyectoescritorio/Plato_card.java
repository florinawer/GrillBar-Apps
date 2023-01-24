
package proyectoescritorio;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Plato_card extends javax.swing.JPanel {
   
    int numeroColor = 1;
    int minutos = 0; 
    int segundos = 0;
    int horas = 0;
    int salida = 0;
    String punto;
    
    public Plato_card(String nombre, String side, String punto, String salsa, int numero_repeticiones) {
        this.punto = punto;
        
        initComponents();    
        //icono campanita 
        label_campanita.setIcon(new ImageIcon(getClass().getResource("/images/black.png")));
        
        //se imprimen los datos recogidos y calculados 
        label_texto_prep.setVisible(false);      
        label_nombre_plato.setText(nombre);
        label_temperatura_cook.setText(getRecomendacion(punto));
        label_side_butter_cook.setText(getSideButter(side, salsa));        
        label_numero_platos.setText(String.valueOf(numero_repeticiones));
        label_recomendacion_cook_tiempo.setText(getRecomendacionMinutos(punto));
        
        this.setVisible(true);        
    }
    
    //imprime la temperatura aproximada de la cocción interna del producto
    public String getRecomendacion(String punto){
        String rec = "";       
        
        switch(punto){
            case("AZUL"):
                aprox_punto.setText("Azul");
                rec = "min & 40ºC";
                break;
            case("POCO HECHO"):
                aprox_punto.setText("Poco Hecho");
                rec = "min & 50ºC";
                break;
            case("AL PUNTO"):
                aprox_punto.setText("Al Punto");
                rec = "min & 58ºC";
                break;
            case("HECHO"):
                aprox_punto.setText("Hecho");
                rec = "min & 64ºC";
                break;
            case("MUY HECHO"):
                aprox_punto.setText("Muy Hecho");
                rec = "min & 70ºC";
                break;        
        }        
        return rec;
    }
    
    //devuelve en minutos la cocción recomendada
    public String getRecomendacionMinutos(String punto){
        String rec = "";       
        
        switch(punto){
            case("AZUL"):               
                rec = "4";
                break;
            case("POCO HECHO"):               
                rec = "6";
                break;
            case("AL PUNTO"):                
                rec = "10";
                break;
            case("HECHO"):                
                rec = "13";
                break;
            case("MUY HECHO"):                
                rec = "15";
                break;        
        }        
        
        return rec;
    }

    //imprime la etiqueta guarniciones y salsas 
    public String getSideButter(String side, String salsa){
        String rec = side +" & "+ salsa ;
                
        return rec;
    } 
    
    //dependiendo del tiempo transcurrido mostrará una campanita 
    //el color lo marcará la diferencia del tiempo recomedado y el transcurrido
    //y así indicando el estado actual de cada plato
    //negro sin arrancar o acabado de arrancar 
    //naranja supera la mitad de su cocción recomendada 
    //ha superado su tiempo recomendado     
    public void updateCampanilla(){       
                        
        int tiempoRecomendado = Integer.parseInt(getRecomendacionMinutos(punto));             
        
        //para evitar errores hago la comprobación solamente cuando la longitud de la cadena de tiempo sea mayor que 3
        //así me indica que hablamos de minutos 
        if(label_tiempo_enmarcha.getText().length() > 3){            
            
            //capturo el indice 1 , que es donde se cuentan los minutos 
            int minuto = Character.getNumericValue(label_tiempo_enmarcha.getText().charAt(1));                       
                        
            //se determina el momento del cambio de campanilla dependieendod el tiempo transcurrido/recomendado
            if(minuto >= (int)((50*tiempoRecomendado) /100 ) && minuto <= (int)((3*tiempoRecomendado)/4)){
        
                label_campanita.setIcon(new ImageIcon(getClass().getResource("/images/orange.png")));
            }
            else if(minuto >= tiempoRecomendado){
                label_campanita.setIcon(new ImageIcon(getClass().getResource("/images/red.png")));
            }
        }
    }
 
    //se le llamam 1 vez por segundo y se van actualizando los valores 
    public void updateTimeOpenFor(){               
                 
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
    }
    
    //se aplicaa ala etiqueta tiempo los valores actualizados 
    public void actualizarHora(){
        
        //hago una serie de ifs /else para hacer que los tiempos se vean lo más normales posible
        if(horas == 0){
           if(minutos == 0){               
               if(segundos < 10){
                   label_tiempo_enmarcha.setText(String.valueOf("0"+ segundos));    
               }
               else{
                   label_tiempo_enmarcha.setText(String.valueOf(segundos));    
               }
           }
           else if(minutos < 10 && minutos > 0){
               if(segundos < 10){
                   label_tiempo_enmarcha.setText(String.valueOf("0"+minutos+" : 0"+ segundos));    
               }
               else{
                   label_tiempo_enmarcha.setText(String.valueOf("0"+minutos+" : "+ segundos));
               }
           }           
           else{
               if(segundos < 10){
                   label_tiempo_enmarcha.setText(String.valueOf(minutos +"0"+segundos));    
               }
               else{
                   label_tiempo_enmarcha.setText(String.valueOf(minutos+ " : "+segundos));    
               }  
           }                       
        }else{
            label_tiempo_enmarcha.setText(String.valueOf("0"+horas +" : "+minutos +" : "+ segundos));    
        }        
    }
    
    //creo un bucle infinito de hilos para ir actualziando los tiempos 
    //si usara el otro metodoo de hilos creo que se sobrecargaría y la actualización se vería afectada  
    private void arrancar_hilos() {                                                   

        salida = 0;       
        
        Thread t1 = new Thread(new Runnable(){
           
            public void run(){
                do{ // crea bucle infinito
                    
                    try 
                    {                                
                        //ejecutados 1 vez por segundo 
                        Thread.sleep(1000); // Espera 1 segundo
                        updateTimeOpenFor(); 
                        actualizarHora();
                        updateCampanilla();

                    }
                    catch (InterruptedException ex) {
                        Logger.getLogger(LiveForm.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                }while(salida != 1);
            } 
        });
        t1.start(); // Inicia el hilo
    }                                                  
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel_cambio_color_click = new javax.swing.JPanel();
        label_nombre_plato = new javax.swing.JLabel();
        label_numero_platos = new javax.swing.JLabel();
        label_texto_prep = new javax.swing.JLabel();
        label_tiempo_enmarcha = new javax.swing.JLabel();
        label_campanita = new javax.swing.JLabel();
        aprox_punto = new javax.swing.JLabel();
        label_recomendacion_cook_tiempo = new javax.swing.JLabel();
        label_side_butter_cook = new javax.swing.JLabel();
        label_temperatura_cook = new java.awt.Label();

        setBackground(new java.awt.Color(102, 102, 102));
        setMaximumSize(new java.awt.Dimension(450, 58));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setMaximumSize(new java.awt.Dimension(450, 58));

        panel_cambio_color_click.setBackground(new java.awt.Color(153, 153, 153));
        panel_cambio_color_click.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_cambio_color_clickMouseClicked(evt);
            }
        });

        label_nombre_plato.setBackground(new java.awt.Color(153, 153, 153));
        label_nombre_plato.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label_nombre_plato.setForeground(new java.awt.Color(0, 0, 0));

        label_numero_platos.setForeground(new java.awt.Color(0, 0, 0));

        label_texto_prep.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        label_texto_prep.setForeground(new java.awt.Color(0, 0, 0));
        label_texto_prep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_texto_prep.setText("prep.");

        label_tiempo_enmarcha.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        label_tiempo_enmarcha.setForeground(new java.awt.Color(0, 0, 0));
        label_tiempo_enmarcha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout panel_cambio_color_clickLayout = new javax.swing.GroupLayout(panel_cambio_color_click);
        panel_cambio_color_click.setLayout(panel_cambio_color_clickLayout);
        panel_cambio_color_clickLayout.setHorizontalGroup(
            panel_cambio_color_clickLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cambio_color_clickLayout.createSequentialGroup()
                .addComponent(label_nombre_plato, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_numero_platos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(label_texto_prep, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_tiempo_enmarcha, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_campanita, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        panel_cambio_color_clickLayout.setVerticalGroup(
            panel_cambio_color_clickLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cambio_color_clickLayout.createSequentialGroup()
                .addComponent(label_numero_platos, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panel_cambio_color_clickLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_cambio_color_clickLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(label_tiempo_enmarcha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_texto_prep, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_campanita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(panel_cambio_color_clickLayout.createSequentialGroup()
                .addComponent(label_nombre_plato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        aprox_punto.setBackground(new java.awt.Color(153, 153, 153));
        aprox_punto.setForeground(new java.awt.Color(51, 51, 51));
        aprox_punto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        aprox_punto.setToolTipText("");
        aprox_punto.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        label_recomendacion_cook_tiempo.setBackground(new java.awt.Color(153, 153, 153));
        label_recomendacion_cook_tiempo.setForeground(new java.awt.Color(0, 0, 0));
        label_recomendacion_cook_tiempo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_recomendacion_cook_tiempo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        label_side_butter_cook.setBackground(new java.awt.Color(153, 153, 153));
        label_side_butter_cook.setForeground(new java.awt.Color(51, 51, 51));
        label_side_butter_cook.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_side_butter_cook.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_side_butter_cook, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aprox_punto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(label_recomendacion_cook_tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_temperatura_cook, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panel_cambio_color_click, javax.swing.GroupLayout.PREFERRED_SIZE, 450, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(panel_cambio_color_click, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_side_butter_cook, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                    .addComponent(aprox_punto, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                    .addComponent(label_temperatura_cook, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(label_recomendacion_cook_tiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void panel_cambio_color_clickMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_cambio_color_clickMouseClicked
        if(numeroColor == 0){
            this.panel_cambio_color_click.setBackground(Color.gray);
            numeroColor += 1;
            label_texto_prep.setVisible(false);
        }
        else if(numeroColor == 1){
            this.panel_cambio_color_click.setBackground(new Color(246, 214, 175));
            label_tiempo_enmarcha.setText("");
            numeroColor += 1;
            label_texto_prep.setVisible(true);
            arrancar_hilos();
            actualizarHora();
        }        
        else if(numeroColor == 2){
            this.panel_cambio_color_click.setBackground(Color.green);
            numeroColor = 0;
            label_texto_prep.setVisible(false);
            salida = 1;
            
        }
    }//GEN-LAST:event_panel_cambio_color_clickMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aprox_punto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label_campanita;
    private javax.swing.JLabel label_nombre_plato;
    private javax.swing.JLabel label_numero_platos;
    private javax.swing.JLabel label_recomendacion_cook_tiempo;
    private javax.swing.JLabel label_side_butter_cook;
    private java.awt.Label label_temperatura_cook;
    private javax.swing.JLabel label_texto_prep;
    private javax.swing.JLabel label_tiempo_enmarcha;
    private javax.swing.JPanel panel_cambio_color_click;
    // End of variables declaration//GEN-END:variables
}
