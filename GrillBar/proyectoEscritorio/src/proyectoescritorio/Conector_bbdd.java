
package proyectoescritorio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conector_bbdd {
    
    Connection conexion = null;
    
    //realiza la conexion al servidor y a la bbdd
    public Conector_bbdd(){

        try{

            String url = "jdbc:mysql://localhost:3306/ordenes";
            String user = "root";
            String password = "";

            conexion = DriverManager.getConnection(url, user, password);

        }catch(SQLException ex){
            System.out.println("ERROR: No se ha podido conectar con el servidor.");
            ex.printStackTrace();
        }
    }
     
    //captura de stock bbdd
    public ArrayList<productoStock> obtenerStock(){
        
        ArrayList<productoStock> stock = new ArrayList<>();
                                    
        try{
            //consulta a ejecutar para obtener stock
            String query = "SELECT nombre, cantidad, fechaCaducidad FROM productos";
             
            //creo un statement y ResultSet
            Statement stmt = conexion.createStatement();                               
            ResultSet result = stmt.executeQuery(query);
            
            //mientras encuentre algo
            while(result.next()){
               
                //creo un objeto temporal donde guardo la información del Result
                productoStock temporal = new productoStock();
                              
                temporal.setNombre(result.getString("nombre"));               
                temporal.setCantidad(result.getInt("cantidad"));
                temporal.setFecha_Cad(dbToGuiDateFormat(result.getString("fechaCaducidad")));
                
                //grabo objeto temporal en ArrayList de tipo productoStock
                stock.add(temporal);                  
            }
            //cierro conexión
            stmt.close();
            result.close();                       
                                   
        }catch(SQLException ex) {
            System.out.println("ERROR: No se ha podido capturar stock" + ex.toString());
        }                
        return stock;
    }
    public String dbToGuiDateFormat(String dbDate){
        try {
            SimpleDateFormat dbFromat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat guiFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dbFromat.parse(dbDate);
            return guiFormat.format(date);
        } catch (ParseException ex) {
            System.out.println("ERROR: Formato de fecha desconocido. " + dbDate);
            Logger.getLogger(Conector_bbdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    //metodo añadir nuevo pedido a la bbdd
    //recibe ArrayList de tipo productoStock y manda los datos a través de un UPDATE
    public void actualizarBBDD(ArrayList<productoStock> productosActualizados){        
                
        for(int i = 0; i < productosActualizados.size(); i++){  
            
            int cantidadActualStock = cantidadActualStock = getCantidadActual(productosActualizados.get(i).getNombre());
            
            int cantidadFinal = cantidadActualStock+ productosActualizados.get(i).getCantidad();
            
            String query = "UPDATE productos SET cantidad = \'"+cantidadFinal+"\', "
                   + "fechaCaducidad = \'"+guiToDbDateFormat(productosActualizados.get(i).getFecha_Cad())+"\' "
                   + "WHERE nombre = \'"+productosActualizados.get(i).getNombre()+"\'";
                   
            try{  
                PreparedStatement ps = conexion.prepareStatement(query);                                          
                    
                ps.executeUpdate();             
                          
                ps.close();     

            }catch(SQLException ex) {
                System.out.println("ERROR: Al hacer un insert en productos" + ex.toString());
            }  
        }      
    }
    
    public String guiToDbDateFormat(String dbDate){
        try {
            SimpleDateFormat dbFromat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat guiFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = guiFormat.parse(dbDate);
            return dbFromat.format(date);
        } catch (ParseException ex) {
            System.out.println("ERROR: Formato de fecha desconocido. " + dbDate);
            Logger.getLogger(Conector_bbdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "0000-00-00";
    }
    
    public int obtenerIdProducto(String nombre){   
        
        int idProducto = 0;
        String query = "SELECT idProducto FROM productos WHERE nombre = \'"+nombre+"\'";
        
        try{    
            
            Statement stmt = conexion.createStatement();           

            ResultSet result = stmt.executeQuery(query);
            
            while(result.next()){               
                idProducto = result.getInt("idProducto");
            }                

            stmt.close();
            result.close();

        }catch (SQLException ex) {
            System.out.println("ERROR: al extraer codigo");
            ex.printStackTrace();
        }                                 
        return idProducto;
    }
    
    public int getCantidadActual(String nombre){
        int cantidad = 0;
        
        try{    
            String query = "SELECT cantidad FROM productos WHERE nombre = \'"+nombre+"\'";

            Statement stmt = conexion.createStatement();           

            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                cantidad = result.getInt("cantidad");
            }                

            stmt.close();
            result.close();

        }catch (SQLException ex) {
            System.out.println("ERROR: al extraer cantidad de bbdd");
            ex.printStackTrace();
        }         
        
        return cantidad;
    }
    
    public ArrayList<Productos> obtener_ordenes(){
        ArrayList<Productos> orden = new ArrayList<>();
        
        try{
            String query = "SELECT nombre, salsa, punto, side, numero, atiende FROM mesa";
                        
            Statement stmt = conexion.createStatement();           
                    
            ResultSet result = stmt.executeQuery(query);
            
            while(result.next()){
               
                //creo un objeto temporal donde guardo la información del Result
                Productos temporal = new Productos();
                              
                temporal.setNombre(result.getString("nombre"));               
                temporal.setSalsa(result.getString("salsa"));
                temporal.setPunto(result.getString("punto"));
                temporal.setSide(result.getString("side"));
                temporal.setNumero(result.getInt("numero"));
                temporal.setAtiende(result.getString("atiende"));
                
                //asignamos hora de entrada 
                temporal.setHora_pedido();
                
                //grabo objeto temporal en ArrayList de tipo Productos
                orden.add(temporal);                 
                                
            }
            
            stmt.close();
            result.close();
            
            System.out.println("bbdd comprobado");
                                   
        }catch(SQLException ex) {
            System.out.println("ERROR: No se ha podido capturar stock" + ex.toString());
        }           
        
        return orden;
    }
    
    //captura las ordenes registradas en la bbdd
    public ArrayList<String> obtenerPlatosCarta(){
        
        ArrayList<String> platos = new ArrayList<>();
        
        try{
            String query = "SELECT nombre FROM productos";
                        
            Statement stmt = conexion.createStatement();           
                    
            ResultSet result = stmt.executeQuery(query);
            
            while(result.next()){
               
                //creo un objeto temporal donde guardo la información del Result
                String temporal = result.getString("nombre");                            
                                              
                //grabo objeto temporal en ArrayList 
                platos.add(temporal);                                                 
            }
            
            stmt.close();
            result.close();
            
            System.out.println("platos capturados");
                                   
        }catch(SQLException ex) {
            System.out.println("ERROR: No se ha podido capturar platos" + ex.toString());
        }           
        
        return platos;
    }
    
    public ArrayList<Productos> obtener_ordenes_back_up(){
        ArrayList<Productos> orden = new ArrayList<>();
        
        try{
            String query = "SELECT nombre, salsa, punto, side, numero, atiende FROM back_up_mesas";
                        
            Statement stmt = conexion.createStatement();           
                    
            ResultSet result = stmt.executeQuery(query);
            
            while(result.next()){
               
                //creo un objeto temporal donde guardo la información del Result
                Productos temporal = new Productos();
                              
                temporal.setNombre(result.getString("nombre"));               
                temporal.setSalsa(result.getString("salsa"));
                temporal.setPunto(result.getString("punto"));
                temporal.setSide(result.getString("side"));
                temporal.setNumero(result.getInt("numero"));
                temporal.setAtiende(result.getString("atiende"));
                
                //asignamos hora de entrada 
                temporal.setHora_pedido();
                
                //grabo objeto temporal en ArrayList de tipo Productos
                orden.add(temporal);                                                 
            }
            
            stmt.close();
            result.close();
            
            System.out.println("bbdd back up comprobado");
                                   
        }catch(SQLException ex) {
            System.out.println("ERROR: No se ha podido capturar stock" + ex.toString());
        }           
        
        return orden;
    }
    
    public void borrar_reserva(int numero){
        
        String query = "DELETE FROM mesa WHERE numero = \'"+numero+"\'";
                   
        try{  
            PreparedStatement ps = conexion.prepareStatement(query);                                          

            ps.executeUpdate();             

            ps.close();     

        }catch(SQLException ex) {
            System.out.println("ERROR: al borrar reservas" + ex.toString());
        }  
        
    }    
    public boolean add_platos_carta(String nombre, String foto){
        boolean ejecutado = true;        
        String cantidad = "0";
        
        FileInputStream archivofoto;
        String query = "INSERT INTO productos(`nombre`, `cantidad`, `fechaCaducidad`, `img`) VALUES (?, ?, ?,?)";
    
        try {
            
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setString(2, cantidad);
            pstmt.setString(3, "2020/01/01");         
            
            archivofoto = new FileInputStream(foto);
            pstmt.setBinaryStream(4, archivofoto);
            
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conector_bbdd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conector_bbdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ejecutado;
    }
        
    public boolean borrar_platos_carta(String nombre){
        
        boolean ejecutado = true;
        String query = "DELETE FROM productos WHERE nombre = \'"+nombre+"\'";
                   
        try{              
            PreparedStatement ps = conexion.prepareStatement(query);                                          
            
            ps.executeUpdate();                         
            ps.close();     
            
        }catch(SQLException ex) {
            ejecutado = false;
            System.out.println("ERROR: al borrar platos" + ex.toString());
        }  
        return ejecutado;
    }    
    
    public void eliminarBackUp(int numero){
        
        String query = "DELETE FROM back_up_mesas WHERE numero = \'"+numero+"\'";
                   
        try{  
            PreparedStatement ps = conexion.prepareStatement(query);                                          

            ps.executeUpdate();             
            ps.close();     

        }catch(SQLException ex) {
            System.out.println("ERROR: Al borrar back up mesas" + ex.toString());
        }          
    }  
    public void vaciar_ordenes_pendientes(){
       
        String query = "DELETE FROM mesa ";
                   
        try{  
            PreparedStatement ps = conexion.prepareStatement(query);                                          

            ps.executeUpdate();             
            ps.close();     

        }catch(SQLException ex) {
            System.out.println("ERROR: Al borrar mesas" + ex.toString());
        }
    }
    
    public void resetAuto(){
        
        try{    
            String query = "ALTER TABLE mesa AUTO_INCREMENT = 1";         
            
            PreparedStatement ps = conexion.prepareStatement(query);
                
            ps.executeUpdate();         
            System.out.println("Reset realizado");
            ps.close();           

        }catch (SQLException ex) {
            System.out.println("ERROR: al extraer cantidad de bbdd");
            ex.printStackTrace();
        }         
    }
    
    public boolean comprobar_empleado(String nombre, String codigo){
        boolean existe = false;
    
        try{    
            String query = "SELECT nombre, codigo FROM empleados WHERE nombre = \'"+nombre+"\' AND codigo = \'"+codigo+"\'";

            Statement stmt = conexion.createStatement();           

            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                existe = true;
            }                

            stmt.close();
            result.close();

        }catch (SQLException ex) {
            System.out.println("ERROR: al extraer cantidad de bbdd");
            ex.printStackTrace();
        }         
        
        return existe;
    }
    
    public void close(){

       try{
           conexion.close();        

       }catch(SQLException ex){
           System.out.print("ERROR: Al cerrar la conexión.");
           ex.printStackTrace();
       }

    }
}
