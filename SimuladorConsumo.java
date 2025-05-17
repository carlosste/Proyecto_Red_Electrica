import java.util.Random;

/**
 * SimuladorConsumo es una clase que simula el consumo de energía en un grafo.
 * Implementa la interfaz Runnable para permitir la ejecución en un hilo separado.
 */
public class SimuladorConsumo implements Runnable {
    private final Grafo grafo;
    private boolean ejecutando = false;

    /**
     * Constructor de la clase SimuladorConsumo.
     * @param grafo El grafo que representa la red de nodos.
     */
    public SimuladorConsumo(Grafo grafo) {
        this.grafo = grafo;
    }   

    /**
     * Inicia la simulación de consumo de energía.
     */
    public void iniciar() {
        if(!ejecutando) {
            ejecutando = true;
            new Thread(this).start();
        }
    }

    /**
     * Método run que se ejecuta en un hilo separado.
     * Simula el consumo de energía en los nodos del grafo.
     */
  @Override
    public void run() {
        Random random = new Random();
        long tiempoInicio = System.currentTimeMillis();
        while (ejecutando && (System.currentTimeMillis() - tiempoInicio < 10000)) { // 10 segundos
            for(String nodo : grafo.getNodos()) {
                int consumo = random.nextInt(100); // Simulando un consumo aleatorio
                System.out.println("[SimuladorConsumo] Nodo:" + nodo + ", Consumo: " + consumo);
            }
            try {
                Thread.sleep(1000); // Espera 1 segundo antes de la siguiente interación
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        ejecutando = false;
        System.out.println("[SimuladorConsumo] Simulación detenida.");
    }
}