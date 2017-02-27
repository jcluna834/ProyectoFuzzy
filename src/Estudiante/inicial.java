package Estudiante;
import javax.swing.JOptionPane;
public class inicial {

    public static void main(String[] args) {
            Estudiante estudianteImpl = new Estudiante();
            String user = JOptionPane.showInputDialog(null, "Ingresa tu nombre de usuario");
            estudianteImpl.setNombre(user);
            Cliente estudiante = new Cliente(user, estudianteImpl);
            estudiante.setVisible(true);
    }
}
