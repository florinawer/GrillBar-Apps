
package proyectoescritorio;

import java.util.ArrayList;

public class ProductosObject {
    private ArrayList<Productos> productos;

    public ProductosObject(ArrayList<Productos> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "ProductosObject{" + "productos=" + productos + '}';
    }

    public ProductosObject() {
    }

    public ArrayList<Productos> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Productos> productos) {
        this.productos = productos;
    }
    
}
