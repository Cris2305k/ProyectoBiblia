/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaz;
import Txt.LeerArchivo;
import TablaOrdenada.*;
import java.util.Scanner;
/**
* Aplicación principal del Proyecto Biblia para análisis de frecuencia de palabras.
 * 
 * Esta clase implementa un sistema completo de análisis de texto que carga un archivo
 * de texto, procesa las palabras y permite realizar múltiples operaciones sobre una 
 * tabla de símbolos ordenada.
 * 
 * @author cvaro
 */
public class ProyectoBiblia {

    /** Tabla de símbolos ordenada que almacena las palabras y sus frecuencias */
    private static TablaDeSimbolosOrdenada<String, Integer> tablaPalabras;
    
    /** Scanner para entrada de datos del usuario */
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Método principal que inicia la aplicación.
     * 
     * Inicializa la tabla de símbolos, carga el archivo de texto,
     * procesa las palabras y muestra el menú interactivo para el usuario.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("=== PROYECTO BIBLIA ===");
        System.out.println("Analisis completo de frecuencia de palabras");
        
        try {
            
            tablaPalabras = new TablaDeSimbolosOrdenada<>(100000);
            
            System.out.println("Cargando y procesando archivo sagradas.txt...");
            cargarArchivo();
            
            mostrarMenu();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
  /**
     * Carga y procesa el archivo de texto directamente en la tabla de símbolos.
     * 
     * Utiliza la clase LeerArchivo para procesar el archivo "sagradas.txt"
     * y almacenar cada palabra única junto con su frecuencia de aparición.
     * 
     * @throws Exception si ocurre un error durante la lectura o procesamiento del archivo
     */
    private static void cargarArchivo() throws Exception {
        LeerArchivo.procesarArchivo("src/Txt/sagradas.txt", tablaPalabras);
        System.out.println("Archivo procesado. Palabras unicas: " + tablaPalabras.size());
        totalnropalabras();
    }
    /**
     * Muestra el menú principal y gestiona la interacción con el usuario.
     * 
     * Presenta un menú con 20 opciones diferentes que permiten al usuario
     * probar todas las funcionalidades de la tabla de símbolos ordenada:
     * operaciones básicas, operaciones de orden, iteración y búsquedas especiales.
     */
    private static void mostrarMenu() {
        while (true) {
            System.out.println("\n=== MENU COMPLETO ===");
            System.out.println("1.  get() - Buscar frecuencia de una palabra");
            System.out.println("2.  put() - Agregar/modificar palabra manualmente");
            System.out.println("3.  delete() - Eliminar una palabra");
            System.out.println("4.  contains() - Verificar si existe una palabra");
            System.out.println("5.  isEmpty() - Verificar si la tabla esta vacia");
            System.out.println("6.  size() - Mostrar tamanio de la tabla");
            System.out.println("7.  min() - Mostrar primera palabra alfabeticamente");
            System.out.println("8.  max() - Mostrar ultima palabra alfabeticamente");
            System.out.println("9.  deletemin() - Eliminar primera palabra");
            System.out.println("10. deletemax() - Eliminar ultima palabra");
            System.out.println("11. select() - Seleccionar palabra por posicion");
            System.out.println("12. rank() - Obtener posicion de una palabra");
            System.out.println("13. floor() - Palabra mas grande ≤ a una dada");
            System.out.println("14. ceiling() - Palabra mas pequenia ≥ a una dada");
            System.out.println("15. keys() - Mostrar todas las palabras");
            System.out.println("16. keys(lo, hi) - Mostrar palabras en rango");
            System.out.println("17. Estadisticas generales");
            System.out.println("18. Buscar por substring");
            System.out.println("19. Buscar palabras que comiecen con");
            System.out.println("20. Salir");
            System.out.print("Opcion: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1 -> Get();
                case 2 -> Put();
                case 3 -> Delete();
                case 4 -> Contains();
                case 5 -> IsEmpty();
                case 6 -> Size();
                case 7 -> Min();
                case 8 -> Max();
                case 9 -> Deletemin();
                case 10 -> Deletemax();
                case 11 -> Select();
                case 12 -> Rank();
                case 13 -> Floor();
                case 14 -> Ceiling();
                case 15 -> Keys();
                case 16 -> KeysRange();
                case 17 -> mostrarEstadisticas();
                case 18 -> buscarPorSubstring();
                case 19 -> buscarPorInicio();
                case 20 -> {
                    System.out.println("¡Hasta luego!");
                    return;
                }
                default -> System.out.println("Opcion invalida");
            }
        }
    }
    
    /**
     * Prueba la operación get() de la tabla de símbolos.
     * 
     * Solicita al usuario una palabra y muestra su frecuencia de aparición
     * en el texto, o indica si la palabra no existe.
     */
    private static void Get() {
        System.out.print("Palabra a buscar: ");
        String palabra = scanner.nextLine().toLowerCase().trim();
        
        Integer frecuencia = tablaPalabras.get(palabra);
        if (frecuencia != null) {
            System.out.println("get(\"" + palabra + "\") = " + frecuencia + " veces");
        } else {
            System.out.println("get(\"" + palabra + "\") = null (no encontrada)");
        }
    }
    
    /**
     * Prueba la operación put() de la tabla de símbolos.
     * 
     * Permite al usuario agregar una nueva palabra con su frecuencia
     * o modificar la frecuencia de una palabra existente.
     */
    private static void Put() {
        System.out.print("Palabra a agregar/modificar: ");
        String palabra = scanner.nextLine().toLowerCase().trim();
        System.out.print("Frecuencia: ");
        int frecuencia = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Ejecutando: put(\"" + palabra + "\", " + frecuencia + ")");
        tablaPalabras.put(palabra, frecuencia);
        System.out.println("✓ Palabra agregada/modificada");
    }
    
    /**
     * Prueba la operación delete() de la tabla de símbolos.
     * 
     * Solicita una palabra al usuario y la elimina de la tabla si existe,
     * mostrando un mensaje de confirmación o error según corresponda.
     */
    private static void Delete() {
        System.out.print("Palabra a eliminar: ");
        String palabra = scanner.nextLine().toLowerCase().trim();
        
        if (tablaPalabras.contains(palabra)) {
            System.out.println("Ejecutando: delete(\"" + palabra + "\")");
            tablaPalabras.delete(palabra);
            System.out.println("✓ Palabra eliminada");
        } else {
            System.out.println("La palabra \"" + palabra + "\" no existe en la tabla");
        }
    }
    
    /**
     * Prueba la operación contains() de la tabla de símbolos.
     * 
     * Verifica si una palabra específica existe en la tabla
     * y muestra el resultado booleano.
     */
    private static void Contains() {
        System.out.print("Palabra a verificar: ");
        String palabra = scanner.nextLine().toLowerCase().trim();
        
        boolean existe = tablaPalabras.contains(palabra);
        System.out.println("contains(\"" + palabra + "\") = " + existe);
    }
    
    /**
     * Prueba la operación isEmpty() de la tabla de símbolos.
     * 
     * Verifica si la tabla está vacía y muestra información
     * adicional sobre el tamaño actual.
     */
    private static void IsEmpty() {
        boolean vacia = tablaPalabras.isEmpty();
        System.out.println("isEmpty() = " + vacia);
        if (vacia) {
            System.out.println("La tabla esta vacia");
        } else {
            System.out.println("La tabla contiene " + tablaPalabras.size() + " palabras");
        }
    }
    
    /**
     * Prueba la operación size() de la tabla de símbolos.
     * 
     * Muestra el número total de palabras únicas almacenadas.
     */
    private static void Size() {
        int tamaño = tablaPalabras.size();
        System.out.println("size() = " + tamaño + " palabras unicas");
    }
    
    /**
     * Prueba la operación min() de la tabla de símbolos.
     * 
     * Muestra la primera palabra en orden alfabético junto con su frecuencia.
     */
    private static void Min() {
        if (tablaPalabras.isEmpty()) {
            System.out.println("min() = null (tabla vacia)");
        } else {
            String minima = tablaPalabras.min();
            System.out.println("min() = \"" + minima + "\" (frecuencia: " + tablaPalabras.get(minima) + ")");
        }
    }
    
    /**
     * Prueba la operación max() de la tabla de símbolos.
     * 
     * Muestra la última palabra en orden alfabético junto con su frecuencia.
     */
    private static void Max() {
        if (tablaPalabras.isEmpty()) {
            System.out.println("max() = null (tabla vacia)");
        } else {
            String maxima = tablaPalabras.max();
            System.out.println("max() = \"" + maxima + "\" (frecuencia: " + tablaPalabras.get(maxima) + ")");
        }
    }
    
     /**
     * Prueba la operación deletemin() de la tabla de símbolos.
     * 
     * Elimina y muestra la primera palabra alfabéticamente,
     * indicando cuál es la nueva primera palabra después de la eliminación.
     */
    private static void Deletemin() {
        if (tablaPalabras.isEmpty()) {
            System.out.println("deletemin() = null (tabla vacia)");
        } else {
            String eliminada = tablaPalabras.deletemin();
            System.out.println("deletemin() = \"" + eliminada + "\" (eliminada)");
            System.out.println("Nueva primera palabra: " + (tablaPalabras.isEmpty() ? "ninguna" : tablaPalabras.min()));
        }
    }
    
     /**
     * Prueba la operación deletemax() de la tabla de símbolos.
     * 
     * Elimina y muestra la última palabra alfabéticamente,
     * indicando cuál es la nueva última palabra después de la eliminación.
     */
    private static void Deletemax() {
        if (tablaPalabras.isEmpty()) {
            System.out.println("deletemax() = null (tabla vacia)");
        } else {
            String eliminada = tablaPalabras.deletemax();
            System.out.println("deletemax() = \"" + eliminada + "\" (eliminada)");
            System.out.println("Nueva ultima palabra: " + (tablaPalabras.isEmpty() ? "ninguna" : tablaPalabras.max()));
        }
    }
    
    /**
     * Prueba la operación select() de la tabla de símbolos.
     * 
     * Permite al usuario seleccionar una palabra por su posición
     * en el orden alfabético (índice de 0 a size()-1).
     */
    private static void Select() {
        if (tablaPalabras.isEmpty()) {
            System.out.println("select() no disponible (tabla vacia)");
            return;
        }
        
        System.out.println("Tamanio actual: " + tablaPalabras.size() + " palabras");
        System.out.print("Posicion a seleccionar (0 a " + (tablaPalabras.size()-1) + "): ");
        int k = scanner.nextInt();
        scanner.nextLine();
        
        if (k >= 0 && k < tablaPalabras.size()) {
            String palabra = tablaPalabras.select(k);
            System.out.println("select(" + k + ") = \"" + palabra + "\" (frecuencia: " + tablaPalabras.get(palabra) + ")");
        } else {
            System.out.println("Posicion invalida. Debe estar entre 0 y " + (tablaPalabras.size()-1));
        }
    }
    
    /**
     * Prueba la operación rank() de la tabla de símbolos.
     * 
     * Muestra la posición que ocupa o ocuparía una palabra
     * en el orden alfabético de la tabla.
     */
    private static void Rank() {
        System.out.print("Palabra para obtener su posicion: ");
        String palabra = scanner.nextLine().toLowerCase().trim();
        
        int posicion = tablaPalabras.rank(palabra);
        System.out.println("rank(\"" + palabra + "\") = " + posicion);
        
        if (tablaPalabras.contains(palabra)) {
            System.out.println("La palabra esta en la posicion " + posicion);
        } else {
            System.out.println("La palabra no existe, estaria en la posicion " + posicion + " si se insertara");
        }
    }
    
    /**
     * Prueba la operación floor() de la tabla de símbolos.
     * 
     * Encuentra la palabra más grande que sea menor o igual
     * a la palabra de referencia proporcionada.
     */
    private static void Floor() {
        System.out.print("Palabra de referencia para floor: ");
        String palabra = scanner.nextLine().toLowerCase().trim();
        
        String floor = tablaPalabras.floor(palabra);
        if (floor != null) {
            System.out.println("floor(\"" + palabra + "\") = \"" + floor + "\" (frecuencia: " + tablaPalabras.get(floor) + ")");
        } else {
            System.out.println("floor(\"" + palabra + "\") = null (no hay palabras menores o iguales)");
        }
    }
    
    /**
     * Prueba la operación ceiling() de la tabla de símbolos.
     * 
     * Encuentra la palabra más pequeña que sea mayor o igual
     * a la palabra de referencia proporcionada.
     */
    private static void Ceiling() {
        System.out.print("Palabra de referencia para ceiling: ");
        String palabra = scanner.nextLine().toLowerCase().trim();
        
        String ceiling = tablaPalabras.ceiling(palabra);
        if (ceiling != null) {
            System.out.println("ceiling(\"" + palabra + "\") = \"" + ceiling + "\" (frecuencia: " + tablaPalabras.get(ceiling) + ")");
        } else {
            System.out.println("ceiling(\"" + palabra + "\") = null (no hay palabras mayores o iguales)");
        }
    }
    
    /**
     * Prueba la operación keys() para mostrar todas las palabras.
     * 
     * Muestra todas las palabras de la tabla en orden alfabético.
     * 
     */
    private static void Keys() {
        if (tablaPalabras.isEmpty()) {
            System.out.println("keys() = [] (tabla vacia)");
            return;
        }
        
        System.out.println("keys() - Todas las palabras alfabeticamente:");
        int contador = 0;
        for (String palabra : tablaPalabras.keys("a","{")) {
            System.out.println((contador+1) + ". " + palabra + " (" + tablaPalabras.get(palabra) + " veces)");
            contador++;
            System.out.println("\nTotal mostrado: " + contador + " palabras");
        }
    }
    
     /**
     * Prueba la operación keys(lo, hi) para mostrar palabras en un rango.
     * 
     * Solicita al usuario un rango de palabras (desde 'lo' hasta 'hi')
     * y muestra todas las palabras que se encuentran en ese rango alfabético.
     */
    private static void KeysRange() {
        if (tablaPalabras.isEmpty()) {
            System.out.println("keys(lo, hi) no disponible (tabla vacia)");
            return;
        }
        
        System.out.print("Palabra inicial (lo): ");
        String lo = scanner.nextLine().toLowerCase().trim();
        System.out.print("Palabra final (hi): ");
        String hi = scanner.nextLine().toLowerCase().trim();
        
        System.out.println("keys(\"" + lo + "\", \"" + hi + "\"):");
        int contador = 0;
        for (String palabra : tablaPalabras.keys(lo, hi)) {
            System.out.println("- " + palabra + " (" + tablaPalabras.get(palabra) + " veces)");
            contador++;
        }
        System.out.println("Total en rango: " + contador + " palabras");
    }
    
    /**
     * Muestra estadísticas completas del corpus de texto procesado.
     * 
     * Presenta un resumen completo que incluye: estado de la tabla,
     * número de palabras únicas, total de palabras procesadas,
     * palabras que aparecen solo una vez, palabras repetidas,
     * y primera y última palabra alfabéticamente.
     */
    private static void mostrarEstadisticas() {
        System.out.println("\n=== ESTADISTICAS COMPLETAS ===");
        System.out.println("isEmpty(): " + tablaPalabras.isEmpty());
        System.out.println("size(): " + tablaPalabras.size() + " palabras unicas");
        totalnropalabras();
        totalnrosinrepetir();
        totalrepetidos();
        if (!tablaPalabras.isEmpty()) {
            System.out.println("min(): \"" + tablaPalabras.min() + "\"");
            System.out.println("max(): \"" + tablaPalabras.max() + "\"");
            System.out.println("select(0): \"" + tablaPalabras.select(0) + "\" (primera)");
            System.out.println("select(" + (tablaPalabras.size()-1) + "): \"" + tablaPalabras.select(tablaPalabras.size()-1) + "\" (ultima)");
        }
    }
    
    /**
     * Implementa búsqueda por substring dentro de las palabras.
     * 
     * Permite al usuario buscar todas las palabras que contengan
     * un texto específico como substring. 
     */
    private static void buscarPorSubstring() {
        System.out.print("Texto a buscar dentro de las palabras: ");
        String texto = scanner.nextLine().toLowerCase().trim();
        
        System.out.println("\nPalabras que contienen '" + texto + "':");
        int contador = 0;
        
        for (String palabra : tablaPalabras.keys("a","{")) {
            if (palabra.contains(texto)) {
                System.out.println(palabra + " (" + tablaPalabras.get(palabra) + " veces)");
                contador++;
            }
        }
        
        if (contador == 0) {
            System.out.println("No se encontraron palabras que contengan '" + texto + "'");
        } else {
            System.out.println("\nTotal encontrado: " + contador + " palabras");
        }
    }
    /**
     * Implementa búsqueda por prefijo de palabras.
     * 
     * Encuentra todas las palabras que comienzan con un prefijo específico
     * utilizando las operaciones de rango de la tabla de símbolos.
     * Utiliza una técnica que incrementa el último carácter para definir el rango.
     */
    private static void buscarPorInicio(){    
            if (tablaPalabras.isEmpty()) {
            System.out.println("no disponible (tabla vacia)");
               return;
        }
        
        System.out.println("La palabra inicia con:");
        String lo = scanner.nextLine().toLowerCase().trim();
        char lastchar = (char)(lo.charAt(lo.length()-1)+1);
        String hi = lo.substring(0,lo.length()-1 )+lastchar;
        
        System.out.println("keys(\"" + lo + "\", \"" + hi + "\"):");
        int contador = 0;
        for (String palabra : tablaPalabras.keys(lo, hi)) {
            if (palabra.equals(hi)) break;
            System.out.println("- " + palabra + " (" + tablaPalabras.get(palabra) + " veces)");
            contador++;
        }
        
        System.out.println("Total en rango: " + contador + " palabras");
   }
    /**
     * Calcula y muestra el total de palabras procesadas incluyendo repeticiones.
     * 
     * Suma todas las frecuencias de todas las palabras para obtener
     * el número total de palabras en el texto original.
     */
    private static void totalnropalabras(){ 
        int contador = 0;
        for (String palabra : tablaPalabras.keys("a", "{")){
           
           contador += tablaPalabras.get(palabra);  
        }
        
        System.out.println("Total: " + contador + " palabras");        
    }
    /**
     * Calcula y muestra el número de palabras que aparecen exactamente una vez.
     * 
     * Cuenta las palabras únicas que tienen frecuencia igual a 1,
     * útil para análisis de vocabulario y riqueza lexical.
     */
    private static void totalnrosinrepetir(){ 
        int contador = 0;
        for (String palabra : tablaPalabras.keys("a", "{")) {
        if (tablaPalabras.get(palabra) == 1) {
            contador++;
        }
    }
    System.out.println("Total de palabras unicas que estan solo una vez: " + contador);
        
    }
     /**
     * Calcula y muestra el número de palabras que aparecen más de una vez.
     * 
     * Cuenta las palabras únicas que tienen frecuencia mayor a 1,
     * complementario al método totalnrosinrepetir().
     */
    private static void totalrepetidos(){ 
        int contador = 0;
        for (String palabra : tablaPalabras.keys("a", "{")) {
        if (tablaPalabras.get(palabra) > 1) {
            contador++;
        }
    }
    System.out.println("Total de palabras unicas que estan repetidas: " + contador);
        
    }
}
