// Este código implementa el algoritmo de Floyd-Warshall para encontrar las distancias mínimas entre todos los pares de vértices en un grafo ponderado.
public class FloydWarshall {
    /**
     * Calcula las distancias mínimas entre todos los pares de vértices en un grafo ponderado.
     *
     * @param grafo Matriz de adyacencia del grafo, donde grafo[i][j] es el peso de la arista de i a j.
     *              Un valor de 0 indica que no hay arista entre i y j (excepto para la diagonal).
     * @return Matriz de distancias mínimas entre todos los pares de vértices.
     */
  public int[][] calcularDistanciasMinimas(int[][] grafo) {
    int n = grafo.length;
    int[][] distancias = new int[n][n];

    // Inicializar distancias
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j) {
          distancias[i][j] = 0;
        } else if (grafo[i][j] != 0) {
          distancias[i][j] = grafo[i][j];
        } else {
          distancias[i][j] = Integer.MAX_VALUE;
        }
      }
    }

    // Aplicar el algoritmo de Floyd-Warshall
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (distancias[i][k] != Integer.MAX_VALUE && distancias[k][j] != Integer.MAX_VALUE) {
            distancias[i][j] = Math.min(distancias[i][j], distancias[i][k] + distancias[k][j]);
          }
        }
      }
    }

    return distancias;
  }
}