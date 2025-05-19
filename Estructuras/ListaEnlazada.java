
public class ListaEnlazada {
    private Nodo cabeza;

    // agregar nodo al final de la lista
    public void agregar(String nombre) {
        Nodo nuevoNodo = new Nodo(nombre);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
    }

    /*
     * Método para eliminar un nodo de la lista enlazada
     * 
     * 
     * @return true si el nodo existe, false en caso contrario
     */
    public boolean eliminar(String nombre) {
        if (cabeza == null)
            return false;

        if (cabeza.getNombre().equals(nombre)) {
            cabeza = cabeza.getSiguiente();
            return true;
        }

        Nodo actual = cabeza;
        while (actual.getSiguiente() != null && !actual.getSiguiente().getNombre().equals(nombre)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() != null) {
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
            return true;
        }
        return false;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    /*
     * Método para imprimir la lista enlazada
     */
    public void imprimir() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getNombre() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    /*
     * Método para saber si un nodo existe en la lista enlazada
     * 
     * @return true si el nodo existe, false en caso contrario
     */
    public boolean estaVacia() {
        return cabeza == null;
    }
}
