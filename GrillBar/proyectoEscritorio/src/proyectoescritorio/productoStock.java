
package proyectoescritorio;

public class productoStock {
   
    private String nombre;
    private int cantidad;
    private String fecha_Cad;

    //clase de productos que ayuda a tener controlada las cantidades
    public productoStock(String nombre, int cantidad, String fecha_Cad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fecha_Cad = fecha_Cad;
    }

    public productoStock() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_Cad() {
        return fecha_Cad;
    }

    public void setFecha_Cad(String fecha_Cad) {
        this.fecha_Cad = fecha_Cad;
    }
        
}
