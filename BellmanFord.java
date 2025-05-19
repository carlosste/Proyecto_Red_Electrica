//Implementacion del algoritmo Bellman-Ford para detectar los ciclos negativos

import java.util.*;

public class BellmanFord {

    // Método para detectar ciclos negativos
    public boolean detectarCiclosNegativos(int[][] grafo, List<String> nombres) {
        int n = grafo.length;
        boolean cicloNegativo = false;

        for (int origen = 0; origen < n; origen++) {
            // Inicializar distancias y predecesores
            int[] distancias = new int[n];
            int[] predecesores = new int[n];
            Arrays.fill(predecesores, -1);
            Arrays.fill(distancias, Integer.MAX_VALUE);
            distancias[origen] = 0; // Distancia al nodo origen es 0

            // Relajación de aristas
            for (int i = 0; i < n - 1; i++) {
                for (int u = 0; u < n; u++) {
                    for (int v = 0; v < n; v++) {
                        if (grafo[u][v] != 0 && distancias[u] != Integer.MAX_VALUE &&
                                distancias[u] + grafo[u][v] < distancias[v]) {
                            distancias[v] = distancias[u] + grafo[u][v];
                            predecesores[v] = u; // Actualizar el predecesor
                        }

                    }

                }

            }

            // Verificar ciclos negativos
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (grafo[u][v] != 0 && distancias[u] != Integer.MAX_VALUE &&
                            distancias[u] + grafo[u][v] < distancias[v]) {
                        cicloNegativo = true;

                        Set<Integer> visitados = new HashSet<>();
                        int cicloActual = v;
                        for (int i = 0; i < n; i++) {
                            cicloActual = predecesores[cicloActual];
                        }
                        int inicioCiclo = cicloActual;
                        List<String> ciclo = new ArrayList<>();
                        int nodo = cicloActual;
                        do {
                            ciclo.add(nombres.get(nodo));
                            nodo = predecesores[nodo];

                        } while (nodo != inicioCiclo && nodo != -1);
                        ciclo.add(nombres.get(inicioCiclo));
                        Collections.reverse(ciclo); // Invertir el ciclo para mostrarlo en el orden correcto
                        System.out.println("Ciclo negativo detectado: " + String.join(" -> ", ciclo));
                    }

                }

            }
        }
        return cicloNegativo;
    }

}