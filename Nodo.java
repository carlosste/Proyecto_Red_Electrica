public class Nodo {
    private String nombre; //Nombre o identificador del nodo
    private double consumo; //Consumo del energía del nodo
    private Nodo siguiente; //Referencia al siguiente nodo

    /*
     * Constructor de la clase Nodo
     * @param nombre Nombre del nodo
     */
    public Nodo(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }
    
    public String getNombre() {
        return nombre;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    }