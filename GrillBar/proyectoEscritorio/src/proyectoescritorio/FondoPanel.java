
package proyectoescritorio;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class FondoPanel extends JPanel{
    private Image imagen;
    
    @Override
    public void paint(Graphics g) {
        imagen = new ImageIcon(getClass().getResource("/images/home.jpeg")).getImage();
        g.drawImage(imagen, 0, 0,getWidth(), getHeight(), this);
        setOpaque(false);
        
        super.paint(g);      
    }
}
