
package proyectoescritorio;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Productos {
    
    private String nombre;
    private String salsa;
    private String punto;
    private String side;
    private int numero;
    private int numero_platos_repetidos = 1;
    private String atiende;
    private String hora_pedido = "";

    public Productos(int numero, String nombre, String punto, String side, String salsa, String atiende) {
        this.nombre = nombre;
        this.salsa = salsa;
        this.punto = punto;
        this.side = side;
        this.numero = numero;
        this.atiende = atiende;        
    }
    
    public String [] getArray(){
       String[] datos = {String.valueOf(numero), nombre, punto, side, salsa, String.valueOf(numero_platos_repetidos), atiende, hora_pedido};
       
       return datos;
    }
    
    //devuelve ture o false dependiendo si las propiedades de los objetos coinciden
    public boolean equals (Object obj) {
     
        Productos temporal = (Productos) obj;

        return ((temporal.nombre.equals(this.nombre)) 
                && (temporal.salsa.equals(this.salsa)) 
                && (temporal.side.equals(this.side)) 
                && (temporal.punto.equals(this.punto)));        
    }
    
    public String uniqueString (){
        return this.nombre + " // " +
                this.salsa + " // " +
                this.side + " // " +
                this.punto; 
    }
    
    @Override
    public String toString() {
        return "Productos{" + "nombre=" + nombre + ", salsa=" + salsa + ", punto=" + punto 
                + ", side=" + side + ", numero=" + numero + ", numero_platos_repetidos=" + numero_platos_repetidos + "\n" +'}';
    }

    public void setHora_pedido(){
        
        Calendar calendario = new GregorianCalendar();
        int hora =calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);

        this.hora_pedido = Integer.toString(hora) +" : "+ Integer.toString(minutos);
    
    }
    public int getNumero_platos_repetidos() {
        return numero_platos_repetidos;
    }

    public String getHora_pedido() {
        return hora_pedido;
    }

    public String getAtiende() {
        return atiende;
    }

    public void setAtiende(String atiende) {
        this.atiende = atiende;
    }

    public void setNumero_platos_repetidos(int numero) {
        this.numero_platos_repetidos +=  numero;
    }

    public Productos() {
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSalsa() {
        return salsa;
    }

    public void setSalsa(String salsa) {
        this.salsa = salsa;
    }

    public String getPunto() {
        return punto;
    }

    public void setPunto(String punto) {
        this.punto = punto;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
    
}
