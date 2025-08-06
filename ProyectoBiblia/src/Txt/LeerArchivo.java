/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Txt;
/**
 * Utilidad para lectura y procesamiento de archivos de texto.
 * 
 * Esta clase proporciona métodos estáticos especializados en la lectura
 * de archivos de texto y el procesamiento de palabras para análisis de frecuencia.
 * 
 * @author cvaro
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import TablaOrdenada.TablaDeSimbolosOrdenada;

public class LeerArchivo{
    
    /**
     * Limpia y normaliza una palabra para procesamiento uniforme.
     * 
     * Aplica las siguientes transformaciones a la palabra de entrada:
     * 1. Conversión a minúsculas
     * 2. Eliminación de acentos (á→a, é→e, í→i, ó→o, ú→u, ü→u)
     * 3. Normalización de la letra ñ→n
     * 4. Eliminación de todos los caracteres no alfabéticos
     * 
     * Esta normalización permite un análisis consistente del texto,
     * tratando como equivalentes palabras que difieren solo en acentuación
     * o signos de puntuación.
     * 
     * @param palabra la palabra original a limpiar
     * @return la palabra limpia y normalizada, o cadena vacía si la entrada es null
     */
    public static String limpiarPalabra(String palabra) {
        if (palabra == null) return "";
        return  palabra.toLowerCase()
            .replace("á", "a")
            .replace("é", "e")
            .replace("í", "i")
            .replace("ó", "o")
            .replace("ú", "u")
            .replace("ü", "u")
            .replace("ñ", "n")  
            .replaceAll("[^a-z]", "");
    }
    /**
     * Procesa un archivo de texto completo y actualiza una tabla de símbolos con frecuencias.
     * 
     * Lee el archivo línea por línea de manera eficiente usando BufferedReader,
     * divide cada línea en palabras usando espacios en blanco como separadores,
     * limpia cada palabra usando limpiarPalabra(), y actualiza la tabla de símbolos 
     * con las frecuencias de aparición.
     * 
     * El procesamiento es incremental: si una palabra ya existe en la tabla,
     * incrementa su contador; si es nueva, la inicializa con frecuencia 1.
     * 
     * Manejo de errores: Captura y reporta IOException sin interrumpir 
     * la ejecución del programa.
     * 
     * @param archivo ruta del archivo de texto a procesar
     * @param tablaSimbolos tabla de símbolos donde almacenar las frecuencias.
     *                      Debe ser una instancia de TablaDeSimbolosOrdenada con 
     *                      String como clave e Integer como valor
     */
    public static void procesarArchivo(String archivo, TablaDeSimbolosOrdenada tablaSimbolos) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] palabras = linea.split("\\s+");
                for (String palabra : palabras) {
                    String limpia = limpiarPalabra(palabra);
                    if (!limpia.isEmpty()) {
                       Integer contador = (Integer)tablaSimbolos.get(limpia);
                        if (contador == null) {
                            tablaSimbolos.put(limpia, 1);
                        } else {
                            tablaSimbolos.put(limpia, contador + 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
