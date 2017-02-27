package Servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Archivo {

    private final File f;
    private int codigo;

    public Archivo(String archivo) {
        f = new File(archivo);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void ingresarLinea(String line) throws IOException {
        FileWriter fw = new FileWriter(f, true);
        fw.write(line + "\r\n");
        fw.close();
    }

    public void leerArchivo() throws IOException {
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String p;
        while ((p = br.readLine()) != null) {
            System.out.println(p);
        }
        br.close();

    }

    public int CantidadLineas() throws IOException {
        int x = 0;
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String p = null;
        while ((p = br.readLine()) != null) {
            x++;
        }
        br.close();
        return x;
    }

    // devuelve la linea que corresponde a la posicion
    public String getElemento(int pos) throws IOException {
        if (pos < 0 || pos > CantidadLineas()) {
            return null;
        } else {
            String x;
            String p;
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            int i = 0;
            p = br.readLine();
            while (i < pos) {
                p = br.readLine();
                i++;
            }
            x = p;
            return x;
        }
    }

    // devuelve todas las lineas del archivo en una lista de strings
    public List<String> getElementos() throws IOException {
        List<String> x = new ArrayList<>();
        String p;
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        while ((p = br.readLine()) != null) {
            x.add(p);
        }
        return x;
    }

    // genera un archivo a partir de la lista de elementos
    public void setElementos(List<String> elementos) throws IOException {
        FileWriter fw = new FileWriter(f, false);
        fw.write("");
        fw.close();
        for (String l : elementos) {
            fw = new FileWriter(f, true);
            fw.write(l + "\r\n");
            fw.close();
        }
    }
}
