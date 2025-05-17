//Implemenatacion del algoritmo de Dijkstra para calcular la ruta de menor perdida
// en un grafo ponderado. El grafo se representa como una matriz de adyacencia.
// El algoritmo de Dijkstra es un algoritmo de búsqueda de caminos más cortos
// que encuentra el camino más corto desde un nodo origen a todos los demás nodos
public class Dijkstra {

  // Método principal para calcular la ruta mínima
  public int calcularRutaMinima(int[][] grafo, int origen){

    int n = grafo.length;
    int[] distancias = new int[n];
    boolean[] visitado = new boolean[n];

    // Inicializar distancias
    for (int i = 0; i < n; i++) {
      distancias[i] = Integer.MAX_VALUE;
      visitado[i] = false;
    }
    distancias[origen] = 0;

    for (int i = 0; i< n - 1; i++) {
      int u = seleccionarNodoMinimo(distancias, visitado);
      visitado[u] = true;

      for (int v = 0; v < n; v++) {
        if (!visitado[v] && grafo[u][v] != 0 && distancias[u] != Integer.MAX_VALUE) {
          distancias[v] = Math.min(distancias[v], distancias[u] + grafo[u][v]);
        }
      }
    }
    // Por ejemplo, retorna la distancia mínima desde el origen a todos los nodos sumada
    int sumaDistancias = 0;
    for (int i = 0; i < n; i++) {
      if (distancias[i] != Integer.MAX_VALUE) {
        sumaDistancias += distancias[i];
      }
    }
    return sumaDistancias;
  }
  private int seleccionarNodoMinimo(int[] distancias, boolean[] visitado) {
    int min = Integer.MAX_VALUE;
    int minIndex = -1;
    // Encontrar el nodo con la distancia mínima
    for (int i = 0; i < distancias.length; i++) {
      if (!visitado[i] && distancias[i] <= min) {
        min = distancias[i];
        minIndex = i;
      }
    }
    return minIndex;
  }
}