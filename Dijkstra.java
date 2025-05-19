//Implementacion del algoritmo de Dijkstra para calcular la ruta de menor perdida
import java.util.*;

public class Dijkstra {

  // Método principal para calcular la ruta mínima
  public int[] calcularRutaMinima(int[][] grafo, int origen){

     // Validar el grafo
    for (int i = 0; i < grafo.length; i++) {
      for (int j = 0; j < grafo[i].length; j++) {
        if (grafo[i][j] < 0 && grafo[i][j] != Integer.MAX_VALUE) {
          throw new IllegalArgumentException("El grafo contiene pesos negativos.");
        }
      }
    }

    int n = grafo.length;
    int[] distancias = new int[n];
    boolean[] visitado = new boolean[n];

    Arrays.fill(distancias, Integer.MAX_VALUE);
    distancias[origen] = 0;


    // Inicializar distancias
    for (int i = 0; i < n - 1; i++) {
      int u = minDistancia(distancias, visitado);
      if (u == -1) 
        break; // No hay más nodos alcanzables
      visitado[u] = true;
      for (int v = 0; v < n; v++) {
        if (!visitado[v] && grafo[u][v] != Integer.MAX_VALUE && distancias[u] != Integer.MAX_VALUE && distancias[u] + grafo[u][v] < distancias[v]) {
          distancias[v] = distancias[u] + grafo[u][v];
        }
      }
    }
    return distancias;
  }

  /*
   * Método auxiliar para encontrar el índice del nodo con la distancia mínima
   * que no ha sido visitado.
   */
  // Devuelve el índice del nodo con la distancia mínima
  private int minDistancia(int[] distancias, boolean[] visitado) {
    int min = Integer.MAX_VALUE;
    int minIndex = -1;

    for (int i = 0; i < distancias.length; i++) {
      if (!visitado[i] && distancias[i] <= min) {
        min = distancias[i];
        minIndex = i;
      }
    }
    return minIndex;
  }

  /*
   * Método para obtener la ruta de menor pérdida entre dos nodos en el grafo.
   * Devuelve una lista de enteros que representan los nodos en la ruta.
   */
  public List<Integer> obtenerRuta(int[][] grafo, int origen, int destino) {

   
    
    int[] distancias = calcularRutaMinima(grafo, origen);
    int[] predecesores = new int[grafo.length];
    Arrays.fill(predecesores, -1);
    boolean[] visitado = new boolean[grafo.length];

    PriorityQueue<Integer> cola = new PriorityQueue<>((a, b) -> Integer.compare(distancias[a], distancias[b]));
    cola.add(origen);

    // Inicializar la distancia del nodo origen
    while (!cola.isEmpty()) {
      int u = cola.poll();
      visitado[u] = true;
      // Si hemos llegado al destino, salimos del bucle
      for (int v = 0; v < grafo.length; v++) {
        if (grafo[u][v] != Integer.MAX_VALUE && !visitado[v]) {
          if (distancias[u] + grafo[u][v] == distancias[v]) {
            predecesores[v] = u;
            cola.add(v);
          }
        }
      }
    }

    // Reconstruir la ruta desde el nodo de destino
    List<Integer> ruta = new ArrayList<>();
    for (int v = destino; v != -1; v = predecesores[v]) {
      ruta.add(v);
    }

    Collections.reverse(ruta);
    
    if (ruta.size() == 1 && ruta.get(0) != origen) {
      return new ArrayList<>(); // No hay ruta
    }
    return ruta;
  }

}
