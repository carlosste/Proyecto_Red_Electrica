import java.util.Random;

// simula fallos aleatorios en nodos de un grafo en un hilo independiente.
public class SimuladorDeFallos extends Thread {
    private final Grafo grafo;
    private final ColaAlertas alertas;
    private boolean ejecutando = false;

    // crea el simulador con un grafo y una cola de alertas.
    public SimuladorDeFallos(Grafo grafo, ColaAlertas alertas) {
        this.grafo = grafo;
        this.alertas = alertas;
    }

    // Inicia la simulacion en un nuevo hilo.

    public void iniciar() {
        ejecutando = true;
        start();
    }

    // Ejecuta fallos aleatorios cada 2s duarnte 10s.
    @Override
    public void run() {
        Random random = new Random();
        long tiempoInicio = System.currentTimeMillis();
        while (ejecutando && (System.currentTimeMillis() - tiempoInicio < 10000)) { // 10 segundos
            String nodo = grafo.getNodos().stream()
                    .skip(random.nextInt(grafo.getNodos().size()))
                    .findFirst()
                    .orElse(null);
            if (nodo != null) {
                alertas.agregarAlerta("Fallo detectado en el nodo: " + nodo);
                System.out.println("⚠️ [SimuladorDeFallos] Fallo detectado en el nodo: " + nodo);
                System.out.println("=============================================================");
            } else {
                System.out.println("[SimuladorDeFallos] No se pudo detectar un nodo para simular el fallo.");
            }
            try {
                Thread.sleep(2000); // Espera 2 segundos antes de simular el siguiente fallo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        ejecutando = false;
        System.out.println("[SimuladorDeFallos] Simulador detenido.");

        System.out.println("\n1.Iniciar simulacion de fallas");
        System.out.println("2.Salir al menú principal");

    }
}
