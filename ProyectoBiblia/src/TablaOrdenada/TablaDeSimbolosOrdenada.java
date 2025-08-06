package TablaOrdenada;
import TablaOrdenada.Queue;
/**
 * Implementación de una tabla de símbolos ordenada utilizando arrays paralelos.
 * 
 * Esta estructura de datos mantiene pares clave-valor ordenados por clave,
 * utilizando dos arrays paralelos: uno para las claves ordenadas y otro para 
 * los valores correspondientes.
 * 
 * @param <Key> tipo de las claves, debe implementar Comparable
 * @param <Value> tipo de los valores asociados a las claves
 * @author cvaro
 */
public class TablaDeSimbolosOrdenada<Key extends Comparable<Key>, Value> {
    
    /** Array que almacena las claves en orden ascendente */
    private Key[] keys;
    
    /** Array que almacena los valores correspondientes a cada clave */
    private Value[] vals;
    
    /** Número actual de elementos en la tabla */
    private int N;

    /**
     * Construye una nueva tabla de símbolos ordenada con la capacidad especificada.
     * 
     * Inicializa los arrays internos con la capacidad dada. La tabla
     * comenzará vacía y podrá crecer hasta la capacidad máxima especificada.
     * 
     * @param capacity capacidad máxima de la tabla
     */
    public TablaDeSimbolosOrdenada(int capacity) { 
        keys = (Key[]) new Comparable[capacity]; 
        vals = (Value[]) new Object[capacity];
    }

    /**
     * Retorna el número de elementos en la tabla.
     * 
     * @return número de pares clave-valor almacenados
     */
    public int size() { 
        return N;
    }

    /**
     * Obtiene el valor asociado a la clave especificada.
     * 
     * Utiliza búsqueda binaria para localizar eficientemente la clave.
     * Si la clave no existe, retorna null.
     * 
     * @param key la clave cuyo valor se desea obtener
     * @return el valor asociado a la clave, o null si no existe
     */
    public Value get(Key key) { 
        if (isEmpty()) 
            return null;
        int i = rank(key); 
        if (i < N && keys[i].compareTo(key) == 0) 
            return vals[i]; 
        else 
            return null; 
    }

    /**
     * Encuentra la posición donde está o debería estar una clave.
     * 
     * Implementa búsqueda binaria para encontrar el rango de la clave.
     * Si la clave existe, retorna su posición exacta. Si no existe,
     * retorna la posición donde debería insertarse para mantener el orden.
     * 
     * @param key la clave cuyo rango se desea determinar
     * @return posición de la clave o posición de inserción
     */
    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    /**
     * Inserta un nuevo par clave-valor o actualiza el valor existente.
     * 
     * Si la clave ya existe, actualiza su valor. Si es nueva, la inserta
     * en la posición correcta para mantener el orden, desplazando elementos
     * según sea necesario.
     * 
     * @param key la clave a insertar o actualizar
     * @param val el valor a asociar con la clave
     */
    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) { 
            vals[i] = val;
            return;
        } 
        for (int j = N; j > i; j--) { 
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1]; 
        }
        keys[i] = key; 
        vals[i] = val; 
        N++;
    }

    /**
     * Verifica si la tabla está vacía.
     * 
     * 
     * @return true si la tabla no contiene elementos
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Elimina la clave especificada y su valor asociado.
     * 
     * Si la clave existe, la elimina y desplaza todos los elementos
     * posteriores una posición hacia la izquierda para mantener la compacidad.
     * 
     * @param key la clave a eliminar
     */
    public void delete(Key key) {
        if (isEmpty()) return;
        int i = rank(key);
        if (i >= N || keys[i].compareTo(key) != 0) return;
        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        N--;
        keys[N] = null;
        vals[N] = null;
    }

    /**
     * Elimina y retorna la clave mínima (primera en orden alfabético).
     * 
     * 
     * @return la clave mínima eliminada, o null si la tabla está vacía
     */
    public Key deletemin() {
        if (isEmpty())
            return null;
        Key min = keys[0];
        delete(min);
        return min;
    }

    /**
     * Elimina y retorna la clave máxima (última en orden alfabético).
     * 
     * 
     * @return la clave máxima eliminada, o null si la tabla está vacía
     */
    public Key deletemax() {
        if (isEmpty()) return null;
        
        Key max = keys[N - 1];
        delete(max);
        return max;
    }

    /**
     * Retorna la clave mínima (primera en orden alfabético).
     * 
     * 
     * @return la clave mínima, o null si la tabla está vacía
     */
    public Key min() {
        return keys[0];
    }

    /**
     * Retorna la clave máxima (última en orden alfabético).
     * 
     * 
     * @return la clave máxima, o null si la tabla está vacía
     */
    public Key max() {
        return keys[N - 1];
    }

    /**
     * Retorna la clave en la posición k (indexada desde 0).
     * 
     * 
     * @param k posición de la clave deseada
     * @return la clave en la posición k
     */
    public Key select(int k) { 
        return keys[k];
    }

    /**
     * Retorna la clave más grande menor o igual a la clave dada.
     * 
     * Operación útil para búsquedas aproximadas. Si la clave exacta
     * no existe, encuentra la clave inmediatamente inferior.
     * 
     * @param key la clave de referencia
     * @return la clave floor, o null si no existe ninguna clave menor o igual
     */
    public Key floor(Key key) {
        if (key == null) return null;
        if (isEmpty()) return null;
        
        int i = rank(key);
        
        if (i < N && keys[i].compareTo(key) == 0) {
            return keys[i];
        }
        
        if (i == 0) {
            return null;
        }
        
        return keys[i - 1];
    }

    /**
     * Retorna la clave más pequeña mayor o igual a la clave dada.
     * 
     * Operación útil para búsquedas aproximadas. Si la clave exacta
     * no existe, encuentra la clave inmediatamente superior.
     * 
     * @param key la clave de referencia
     * @return la clave ceiling, o null si no existe ninguna clave mayor o igual
     */
    public Key ceiling(Key key) {
        int i = rank(key); 
        return keys[i];
    }

    /**
     * Verifica si la tabla contiene la clave especificada.
     * 
     * 
     * @param key la clave a verificar
     * @return true si la clave existe en la tabla
     */
    public boolean contains(Key key) {
        if (key == null) return false;
        int i = rank(key);
        return i < N && keys[i].compareTo(key) == 0;
    }

    /**
     * Retorna todas las claves en el rango [lo, hi] en orden ascendente.
     * 
     * Utiliza una cola para coleccionar las claves del rango especificado
     * y permite iteración eficiente sobre el resultado.
     * 
     * @param lo límite inferior del rango (inclusivo)
     * @param hi límite superior del rango (inclusivo)
     * @return un Iterable con todas las claves en el rango especificado
     */
    public Iterable<Key> keys(Key lo, Key hi) { 
        Queue<Key> q = new Queue<Key>(); 
        for (int i = rank(lo); i < rank(hi); i++) 
            q.enqueue(keys[i]); 
        if (contains(hi)) 
            q.enqueue(keys[rank(hi)]); 
        return q; 
    }
}