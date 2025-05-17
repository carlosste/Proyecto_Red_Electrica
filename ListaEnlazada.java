public class ListaEnlazada{
    private static class Nodo{
        private String nombre;
        private Nodo siguiente;

        public Nodo(String nombre){
            this.nombre = nombre;
            this.siguiente = null;
        }
    }
    private Nodo cabeza;

    //agregar nodo al final de la lista 
    public void agregar(String nombre){
        Nodo nuevoNodo = new Nodo(nombre);
        if(cabeza == null){
            cabeza = nuevoNodo;
        }else{
            Nodo actual = cabeza;
            while(actual.siguiente != null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }
    //imprimir la lista
    public void imprimir(){
        Nodo actual = cabeza;
        while(actual != null){
            System.out.print(actual.nombre + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    //Eliminar nodo por nombre
    public boolean eliminar(String nombre){
        if(cabeza == null) return false;

        if(cabeza.nombre.equals(nombre)){
            cabeza = cabeza.siguiente;
            return true;
        }

        Nodo actual = cabeza;
        while(actual.siguiente != null && !actual.siguiente.nombre.equals(nombre)){
            actual = actual.siguiente;
        }

        if(actual.siguiente != null){
            actual.siguiente = actual.siguiente.siguiente;
            return true;
        }
        return false;
    }
    //Verificar si la lista está vacía
    public boolean estaVacia(){
        return cabeza == null;
    }
}