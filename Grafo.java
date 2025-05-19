import java.util.*;

// Clase que representa un grafo dirigido y ponderado.
// Se utiliza un mapa de adyacencias para almacenar los nodos y sus conexiones.

public class Grafo {
    private final int pesoMinimo = -5; // Peso mínimo para las aristas
    private final int pesoMaximo = 7; // Peso máximo para las aristas

    private final Random random = new Random(); // Generador de números aleatorios

    private final Map<String, Map<String, Integer>> adyacencias;
    private Map<String, Boolean> estadosNodos = new HashMap<>();

    public Grafo() {
        this.adyacencias = new HashMap<>();
    }

    public void agregarNodo(String nodo) {
        adyacencias.putIfAbsent(nodo, new HashMap<>());
        estadosNodos.putIfAbsent(nodo, true);
    }

    public void agregarAristaAleatoria(String origen, String destino) {

        // Generar un peso aleatorio entre pesoMinimo y pesoMaximo
        int peso = random.nextInt(pesoMaximo - pesoMinimo + 1) + pesoMinimo;

        // Agregar la arista al grafo
        agregarArista(origen, destino, peso);
    }

    // metodo para agregar aristas
    public void agregarArista(String origen, String destino, int peso) {

        adyacencias.putIfAbsent(origen, new HashMap<>());
        adyacencias.get(origen).put(destino, peso);
    }

    public void cambiarEstadoNodo(boolean activar, Scanner scanner) {
        System.out.println("Ingrese el nombre del nodo que desea" + (activar ? " activar" : "desactivar") + ": ");

        String nombre = scanner.nextLine().trim().toUpperCase();

        if (!adyacencias.containsKey(nombre)) {
            System.out.println("Nodo no encontrado.");
            return;
        }
        estadosNodos.put(nombre, activar);
        System.out.println("El nodo " + nombre + " ha sido " + (activar ? "activado" : "desactivado") + ".");
    }

    public void mostrarEstadosNodos() {
        System.out.println("Listado de nodos con su estado:");
        for (String nodo : adyacencias.keySet()) {
            boolean activo = estadosNodos.getOrDefault(nodo, true);
            System.out.println("Nodo: " + nodo + ", Estado: " + (activo ? "Activo 🟢" : "Desactivado 🔴"));
        }
    }

    public boolean getEstadoNodo(String nombre) {
        return estadosNodos.getOrDefault(nombre, true);
    }

    /**
     * Método para obtener la lista de nodos en el grafo.
     * 
     * @return Una lista de los nombres de los nodos.
     */
    public List<String> getNodos() {
        return new ArrayList<>(adyacencias.keySet());
    }

    /**
     * Método para eliminar una arista entre dos nodos.
     * 
     * @param origen  El nodo de origen.
     * @param destino El nodo de destino.
     */
    public Map<String, Integer> getAdyacentes(String nodo) {
        return adyacencias.getOrDefault(nodo, new HashMap<>());
    }

    /**
     * Método para convertir el grafo a una matriz de adyacencia.
     */
    public int[][] toMatrix() {
        int n = adyacencias.size();
        int[][] matriz = new int[n][n];

        // Inicializar la matriz con infinito
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               if (i == j) {
                matriz[i][j] = 0; // La distancia a sí mismo es 0
               } else {
                   String origen = getNodos().get(i);
                   String destino = getNodos().get(j);
                   Integer peso = adyacencias.get(origen).get(destino);
                   matriz[i][j] = (peso != null) ? peso : Integer.MAX_VALUE; // Si no hay arista, usar infinito
               }
           }
       }

       return matriz;

    }

    /**
     * Método para obtener el índice de un nodo dado su nombre.
     * 
     * @param nodo El nombre del nodo.
     */
    public int getNodoIndex(String nodo) {
        List<String> nodos = new ArrayList<>(adyacencias.keySet());
        return nodos.indexOf(nodo);
    }

    /**
     * Método para obtener el nombre de un nodo dado su índice.
     * 
     * @param index El índice del nodo.
     */
    // Método para obtener el nombre de un nodo dado su índice.
    public String getNodoName(int index) {
        List<String> nodos = new ArrayList<>(adyacencias.keySet());
        return nodos.get(index);
    }

    // Método para obtener el número de nodos en el grafo.

    public int getNumeroNodos() {
        return adyacencias.size();
    }
}
