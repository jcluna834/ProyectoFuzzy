package Servicios;

import Estudiante.Estudiante;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servicios{

    List<Archivo> diccionarios;
    List<Estudiante> estudiantes;

    public Servicios() {
        super();
        diccionarios = new ArrayList<>();
        estudiantes = new ArrayList<>();
    }

  

    //guarda la palabra en el diccionario con el codigo ingresado, la palabra deben tener el formato:
    // codigo;palabraEnIngles;palabraEnEspañol
    public boolean guardarPalabra(String palabraGuardar, int codigo) {
        Archivo diccionario = obtenerDiccionario(codigo);
        if (diccionario != null) {
            return guardarPalabra(diccionario, palabraGuardar);
        } else {
            return false;
        }
    }

    //carga un diccionario desde la ubicacion ingresada y retorna el codigo del diccionario
    public int generarDiccionario(String ubicacion) {
        Archivo diccionario = new Archivo(ubicacion);
        diccionario.setCodigo((int) (Math.random() * 100));
        if (diccionarios.add(diccionario)) {
            return diccionario.getCodigo();
        } else {
            return -1;
        }
    }

    //obtiene una palabra en ingles de manera aleatoria entre todos los diccionarios
    public String getPalabraAletoria() {
        return obtenerPalabraAleatoria();
    }

    //recibe una palabra en ingles y un codigo de palabra, retorna true si el codigo pertenece a la misma palabra ingresada
    public boolean validarImagen(String palabra, int codigo) {
        String word = buscarCodigoPalabra(codigo);
        if (word != null) {
            return word.compareToIgnoreCase(palabra) == 0;
        }
        return false;
    }

    //obtiene la palabra que pertence al codigo ingresado
    public String getPalabra(int codigo) {
        String palabra = buscarCodigoLinea(codigo);
        if (palabra == null) {
            return "";
        } else {
            return palabra;
        }
    }

    //agrega un estudiante a la lista de estudiantes ingresados
    public boolean addEstudiante(Estudiante estudiante) {
        System.out.println("Puntos: "+ estudiante.puntos());
        System.out.println("Estudiante Agregado");
        return estudiantes.add(estudiante);
    }

    //elimina un estudiante de la lista de estudiantes ingresados
    public boolean deleteEstudiante(Estudiante estudiante) {
        System.out.println("Estudiante Eliminado");
        return estudiantes.remove(estudiante);
    }

    //busca una palabra en ingles y retorna su significado en español
    public String buscarPalabra(String palabraIngles) {
        String palabra = traducir(palabraIngles);
        if (palabra == null) {
            return "";
        } else {
            return palabra;
        }
    }

    //retorna de manera local el diccionario al cual pertenece el codigo
    private Archivo obtenerDiccionario(int codigo) {
        if (diccionarios.isEmpty()) {
            return null;
        } else {
            Archivo diccionario = null;
            for (Archivo a : diccionarios) {
                if (a.getCodigo() == codigo) {
                    diccionario = a;
                }
            }
            return diccionario;
        }
    }

    // permite cambiar una palabra del diccionario solicitado, la palabra nueva debe tener el patron:
    // codigo;palabraEnIngles;palabraEnEspañol
    // y la palabra anterior debe coincidir con la misma estructura y los mismos valores que la del diccionario
    private boolean cambiarPalabra(Archivo diccionario, String palabraAnterior, String palabraNueva) {
        try {
            List<String> palabrasDiccionario = diccionario.getElementos();
            int indx = -1, i = 0;
            for (String aux : palabrasDiccionario) {
                if (indx == -1) {
                    if (aux.compareToIgnoreCase(palabraAnterior) == 0) {
                        indx = i;
                    }
                    i++;
                } else {
                    break;
                }
            }
            if (indx != -1) {
                palabrasDiccionario.set(indx, palabraNueva);
                diccionario.setElementos(palabrasDiccionario);
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            return false;
        }
    }


    // guarda una nueva palabra en el diccionario solicitado
    // la palabra debe cumplir con la estructura:
    // codigo;palabraEnIngles;palabraEnEspañol
    private boolean guardarPalabra(Archivo diccionario, String palabraGuardar) {
        try {
            diccionario.ingresarLinea(palabraGuardar);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // obtiene una palabra aleatoria de la lista de palabras de todos los diccionarios
    // la palabra aleatoria contiene el formato:
    // palabraEnIngles
    private String obtenerPalabraAleatoria() {
        List<String> lineas = cargarPalabras();
        int maximo = lineas.size();
        if (maximo == 0) {
            return "";
        }
        int indice = ((int) (Math.random() * maximo) % (maximo));
        return lineas.get(indice);
    }

    // busca una palabra, por código, en todos los diccionarios
    private String buscarCodigoPalabra(int codigo) {
        List<String> lineas = cargarPalabras();
        String palabra = null;
        for (String linea : lineas) {
            String[] aux = linea.split(";");
            if (Integer.parseInt(aux[0]) == codigo) {
                palabra = aux[1];
            }
            if (palabra != null) {
                break;
            }
        }
        return palabra;
    }

    // retorna la traduccion de la palabra ingresada
    private String traducir(String palabraIngles) {
        List<String> lineas = cargarPalabras();
        String traduccion = null;
        for (String linea : lineas) {
            String[] aux = linea.split(";");
            if (palabraIngles.compareToIgnoreCase(aux[1]) == 0) {
                traduccion = aux[2];
            }
            if (traduccion != null) {
                break;
            }
        }
        return traduccion;
    }

    //carga en una sola lista, las palabras de todos los diccionarios

    private List<String> cargarPalabras() {
        List<String> lineas = new ArrayList<>();
        for(Archivo diccionario:diccionarios){
            try {
                for (String linea : diccionario.getElementos()) {
                    lineas.add(linea);
                }
            } catch (IOException ex) {
                Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lineas;
    }

    //busca un codigo de palabra y retorna toda la linea que contiene el codigo
    // con el formato: codigo;palabraEnIngles;palabraEnEspañol
    private String buscarCodigoLinea(int codigo) {
        List<String> lineas = cargarPalabras();
        String palabra = null;
        for (String linea : lineas) {
            String[] aux = linea.split(";");
            if (Integer.parseInt(aux[0]) == codigo) {
                palabra = linea;
            }
            if (palabra != null) {
                break;
            }
        }
        return palabra;
    }
}
