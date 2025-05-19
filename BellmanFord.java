import java.util.*;

public class BellmanFord {

    public boolean detectarCiclosNegativos(int[][] grafo, List<String> nombres) {
        int n = grafo.length;
        boolean cicloNegativo = false;
        Set<String> ciclosDetectados = new HashSet<>();

        for (int origen = 0; origen < n; origen++) {
            int[] distancias = new int[n];
            int[] predecesores = new int[n];
            Arrays.fill(distancias, Integer.MAX_VALUE);
            Arrays.fill(predecesores, -1);
            distancias[origen] = 0;

            // Relajación
            for (int i = 0; i < n - 1; i++) {
                for (int u = 0; u < n; u++) {
                    for (int v = 0; v < n; v++) {
                        if (grafo[u][v] != 0 && distancias[u] != Integer.MAX_VALUE &&
                                distancias[u] + grafo[u][v] < distancias[v]) {
                            distancias[v] = distancias[u] + grafo[u][v];
                            predecesores[v] = u;
                        }
                    }
                }
            }

            // Verificación de ciclos negativos
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (grafo[u][v] != 0 && distancias[u] != Integer.MAX_VALUE &&
                            distancias[u] + grafo[u][v] < distancias[v]) {
                        cicloNegativo = true;

                        // Reconstrucción del ciclo
                        int cicloActual = v;
                        for (int i = 0; i < n; i++) {
                            cicloActual = predecesores[cicloActual];
                        }
                        int inicioCiclo = cicloActual;
                        List<String> ciclo = new ArrayList<>();
                        int nodo = inicioCiclo;

                        do {
                            ciclo.add(nombres.get(nodo));
                            nodo = predecesores[nodo];
                        } while (nodo != inicioCiclo && nodo != -1);

                        ciclo.add(nombres.get(inicioCiclo));
                        Collections.reverse(ciclo);

                        // Crear representación única
                        String cicloNormalizado = normalizarCiclo(ciclo);
                        if (!ciclosDetectados.contains(cicloNormalizado)) {
                            ciclosDetectados.add(cicloNormalizado);
                            System.out.println("Ciclo negativo detectado: " + String.join(" -> ", ciclo));
                        }
                    }
                }
            }
        }

        return cicloNegativo;
    }

    private String normalizarCiclo(List<String> ciclo) {
        // Genera una representación canónica del ciclo (rota al menor nodo al principio)
        List<String> minCiclo = new ArrayList<>(ciclo);
        int n = ciclo.size();
        for (int i = 0; i < n; i++) {
            List<String> rotado = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                rotado.add(ciclo.get((i + j) % n));
            }
            if (rotado.toString().compareTo(minCiclo.toString()) < 0) {
                minCiclo = rotado;
            }
        }
        return String.join("->", minCiclo);
    }
}