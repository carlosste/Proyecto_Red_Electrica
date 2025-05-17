import java.util.Random;

/**
 * SimuladorConsumo es una clase que simula el consumo de energía en un grafo.
 * Implementa la interfaz Runnable para permitir la ejecución en un hilo separado.
 */
// Esta clase es responsable de generar un consumo aleatorio para cada nodo en el grafo
public class SimuladorDeFallos extends Thread {
    private final Grafo grafo;
    private final ColaAlertas alertas;
    private boolean ejecutando = false;

    /*
     * Constructor de la clase SimuladorConsumo.
     * @param grafo El grafo sobre el cual se simulará el consumo.
     * @param alertas La cola de alertas donde se registrarán los fallos.
     */
    public SimuladorDeFallos(Grafo grafo, ColaAlertas alertas) {
        this.grafo = grafo;
        this.alertas = alertas;
    }

    /*
     * Método para iniciar el simulador.
     * Este método inicia el hilo del simulador.
     */
    public void iniciar() {
        ejecutando = true;
        start();
    }

   

    
    /*
     * Método run que se ejecuta cuando se inicia el hilo.
     * Este método simula la detección de fallos en los nodos del grafo.
     */
    
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
            System.out.println("[SimuladorDeFallos] Fallo detectado en el nodo: " + nodo);
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
    }
}
