//Implementacion del algoritmo Bellman-Ford para detectar los ciclos negativos
public class BellmanFord {

    // Clase interna para representar una arista
    public static class Edge {
        public int origen, destino, peso;
        public Edge(int origen, int destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }
    }

    // Método para detectar ciclos negativos
    public static boolean detectarCiclosNegativos(java.util.List<Edge> aristas, int n, int origen) {
        int[] distancias = new int[n];

        // Inicializar distancias
        for (int i = 0; i < n; i++) {
            distancias[i] = Integer.MAX_VALUE;
        }
        distancias[origen] = 0;

        // Relajación de aristas
        for (int i = 0; i < n - 1; i++) {
            for (Edge arista : aristas) {
                if (distancias[arista.origen] != Integer.MAX_VALUE &&
                    distancias[arista.origen] + arista.peso < distancias[arista.destino]) {
                    distancias[arista.destino] = distancias[arista.origen] + arista.peso;
                }
            }
        }

        // Verificar ciclos negativos
        for (Edge arista : aristas) {
            if (distancias[arista.origen] != Integer.MAX_VALUE &&
                distancias[arista.origen] + arista.peso < distancias[arista.destino]) {
                return true; // Ciclo negativo detectado
            }
        }
        return false;
    }
}