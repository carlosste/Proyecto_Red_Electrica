import java.util.Random;

// simula el consumo de energia en nodos de un grafo en un hilo separado.
public class SimuladorConsumo implements Runnable {
    private final Grafo grafo;
    private final ColaAlertas alertas;
    private boolean ejecutando = false;

    // Crea el simulador con un grafo y una Cola de alertas.
    public SimuladorConsumo(Grafo grafo, ColaAlertas alertas) {
        this.alertas = alertas;
        this.grafo = grafo;
    }

    // Inicia la simulacion en un hilo si no esta ya en ejecucion.
    public void iniciar() {
        if (!ejecutando) {
            ejecutando = true;
            new Thread(this).start();
        }
    }

    // Ejecuta la simulacion de consumo durante 10s, con alertas si excede
    public void run() {
        Random random = new Random();
        long tiempoInicio = System.currentTimeMillis();
        while (ejecutando && (System.currentTimeMillis() - tiempoInicio < 10000)) { // 10 segundos
            for (String nodo : grafo.getNodos()) {
                int consumo = random.nextInt(100); // Simulando un consumo aleatorio
                System.out.println("=============================================================");
                System.out.println("⚠️ [SimuladorConsumo] Nodo:" + nodo + ", Consumo: " + consumo + " KW");

                if (80 < consumo) {

                    alertas.agregarAlerta("Consumo en el nodo " + nodo + ": " + consumo + " KW");
                }
            }
            try {
                Thread.sleep(2000); // Espera 2 segundos antes de la siguiente interación
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        ejecutando = false;
        System.out.println("[SimuladorConsumo] Simulación detenida.");
        System.out.println("\n1.Iniciar simulacion de consumo");
        System.out.println("2.Salir al menú principal");
    }
}
