// Este código implementa el algoritmo de Floyd-Warshall para encontrar las distancias mínimas entre todos los pares de vértices en un grafo ponderado.
public class FloydWarshall {

  // El método calcularDistanciasMinimas toma una matriz de adyacencia que
  // representa un grafo ponderado y devuelve una matriz de distancias mínimas
  // entre todos los pares de vértices.
  // La matriz de adyacencia debe contener valores enteros, donde
  // Integer.MAX_VALUE representa la ausencia de una arista entre dos vértices.
  // @param grafo La matriz de adyacencia del grafo ponderado.

  public int[][] calcularDistanciasMinimas(int[][] grafo) {
    int n = grafo.length;
    int[][] distancias = new int[n][n];

    // Inicializar distancias
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        distancias[i][j] = grafo[i][j]; // Copiar la matriz de adyacencia
      }
      distancias[i][i] = 0; // La distancia a sí mismo es 0
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
