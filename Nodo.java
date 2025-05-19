public class Nodo {
    // Atributos de la clase Nodo
    private String nombre; // Nombre o identificador del nodo
    private double consumo; // Consumo del energía del nodo
    private boolean activo;
    private Nodo siguiente; // Referencia al siguiente nodo

    /*
     * Constructor de la clase Nodo
     * 
     * @param nombre Nombre del nodo
     */
    public Nodo(String nombre) {
        this.nombre = nombre;
        this.consumo = 0;
        this.activo = true;
        this.siguiente = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /*
     * Metodo para obtener el consumo del nodo
     * 
     * @return consumo Consumo del nodo
     */
    public double getConsumo() {
        return consumo;
    }

    /*
     * Metodo para establecer el consumo del nodo
     * 
     * @param consumo Consumo del nodo
     */
    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    /*
     * Metodo para obtener el siguiente nodo
     * 
     * @param siguiente Siguiente nodo
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /*
     * Metodo para establecer el siguiente nodo
     * 
     * @param siguiente Siguiente nodo
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public boolean isActivo() {
        return activo;
    }

    public void SetActivo(boolean activo) {
        this.activo = activo;
    }

}
