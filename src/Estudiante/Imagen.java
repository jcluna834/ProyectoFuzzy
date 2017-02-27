package Estudiante;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author JulioCésar
 */
public class Imagen extends javax.swing.JPanel {
    private String ruta="";
    private int codigo;
    
    public String getRuta() {
        return ruta;
    }
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }    
    public Imagen(String ruta, int codigo) {
        this.setSize(100, 98);
        this.ruta = ruta;
        this.codigo = codigo;
    }
    
    @Override
    public void paint(Graphics grafico) {
        Dimension dimensiones = getSize();
        ImageIcon imagen = new ImageIcon(ruta);
        grafico.drawImage(imagen.getImage(), 0, 0, dimensiones.width, dimensiones.height, null);
        setOpaque(false);
        super.paintComponent(grafico);
    }

}
