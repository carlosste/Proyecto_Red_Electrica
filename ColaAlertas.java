import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

//Clase ColaAlertas
//Esta clase se encarga de gestionar una cola de alertas
public class ColaAlertas {
    private final ConcurrentLinkedQueue<String> alertas; //Cola concurrente para las alertas
    private final int limiteMaximo; //Limite máximo de alertas en la cola

    //Constructor
    public ColaAlertas(int limiteMaximo) {
        this.alertas = new ConcurrentLinkedQueue<>();
        this.limiteMaximo = limiteMaximo;
    }

    //Método para agragar una aleta a la cola
    //Este método devuelve true si la alerta se ha agregado correctamente, false en caso contrario
    public boolean agregarAlerta(String mensaje) {
        if(alertas.size() < limiteMaximo) {
            alertas.add(mensaje);
            System.out.println("⚠️ Alerta agregada: " + mensaje);
            return true;
        } else {
            System.out.println("⚠️ No se puede agregar la alerta. Limite maximo alcanzado. ");
            return false;
        }
    }

    //Método para verificar si hay alertas pendientes
    //Este método devuelve true si hay alertas en la cola, false en caso contrario
    public boolean hayAlertas() {
        return !alertas.isEmpty();
    }


    //Método para procesar una alerta de la cola
    //Este método elimina la alerta de la cola y la devuelve
    public String procesarAlerta() {
        if(alertas.isEmpty()) {
            System.out.println("⚠️ No hay alertas para procesar. ");
            return null;
        }
        String alertaProcesada = alertas.poll();
        System.out.println("✅ Alerta procesada: " + alertaProcesada);
        return  alertaProcesada;
    }

    //Método para ver todas las alertas pendientes sin procesarlas
    public List<String> verAlertas() {
        return new ArrayList<>(alertas);    
    }
}