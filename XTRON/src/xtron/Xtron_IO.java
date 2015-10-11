package xtron;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Xtron_IO {

    public String[] leerTextoArchivo(String nombreArchivo) {
        String program[];
        int cont = 0;
        int cont2 = 0;
//   String actual="";//para asignar cada vez que la linea del txt no este vacia

        String texto = "";
        FileReader archivo = null;
        String linea = "";
        FileReader asignate = null;
        String linea2 = "";

        try {
            asignate = new FileReader(nombreArchivo);
            BufferedReader lector2 = new BufferedReader(asignate);

            archivo = new FileReader(nombreArchivo);
            BufferedReader lector = new BufferedReader(archivo);
            while ((linea = lector.readLine()) != null) {

                cont++;
//          System.out.println(linea);

                texto += linea + "\n";
            }
            String memory2[] = new String[cont];
            String actual = "";//para asignar cada vez que la linea del txt no este vacia

            while ((linea = lector2.readLine()) != null) {
                actual = linea;
                memory2[cont2] = actual;
                cont2++;
            }

            program = memory2;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo no encontrado");
        } catch (IOException e) {
            throw new RuntimeException("Ocurrio un error de entrada/salida");
        } finally {
            if (archivo != null) {
                try {
                    archivo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return program;
    }

    public void escribirTextoArchivo(String nombreArchivo, String texto) {
        FileWriter salida = null;
        try {
            salida = new FileWriter(nombreArchivo);
            BufferedWriter escritor = new BufferedWriter(salida);
            escritor.write(texto);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (salida != null) {
                try {
                    salida.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

//   public static void main(String[] args) {
////    ArchivoMuestra archivo = new ArchivoMuestra();
//    String entrada = leerTextoArchivo("Prueba.txt");
//    System.out.println(entrada);
//    escribirTextoArchivo("PruebaCopia.txt", entrada);
//} 
}
