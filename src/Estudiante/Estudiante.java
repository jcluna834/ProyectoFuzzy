package Estudiante;

public class Estudiante {
    private Cliente cliente;
    private int puntos;
    public String nombre;
    
    public Estudiante() {
        super();
        puntos = 0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // obtiene la cantidad de puntos acumulados
    public int puntos() {
        return puntos;
    }

    // suma los nuevos puntos a los puntos actuales
    public void puntos(int newPuntos) {
        puntos = puntos + newPuntos;
        if(cliente!=null)
            cliente.iniciarNuevoJuego();
    }

}