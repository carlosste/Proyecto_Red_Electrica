import java.util; 
public class RedElectrica {
    
    public static void mostrarMenu(){
        System.out.println("\n=====MENU DE GESTION DE RED ELECTRICA =====");
        System.out.println("1.Calcular ruta de menor perdida (Dijkstra)");
        System.out.println("2.Analisis global de la red (Floyd-Warshall)");
        System.out.println("3.Detectar perdidas economicas negativas (Bellman-Ford) ");
        System.out.println("4.Iniciar  /Detener simulacion de fallosd");
        System.out.println("5.Iniciar  /Detener simulacion de Consumo");
        System.out.println("6.ver y procesar alertas");
        System.out.println("7.Salir");
        System.out.println("Seleccione una opcion")
    }

}   

    public static void iniciarMenu(Grafo grafo) {
        ColaAlertas alertas = new ColaAlertas(10);
        SimuladorDeFallos simuladorDeFallos = new SimuladorDeFallos(grafo, alertas);
        SimuladorConsumo simuladorConsumo = new SimuladorConsumo(grafo);

        boolean salir = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (!salir) {
                mostrarMenu();
                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {

                    case 1 -> {
                        System.out.println("\n===== DISTANCIAS ENTRE TODOS LOS NODOS =====");
                        Dijkstra dijkstra = new Dijkstra(grafo);
                        for (int origen = 0; origen < grafo.getNumeroNodos(); origen++) {
                            int[] distancias = dijkstra.calcularRutaMinima(grafo.toMatrix(), origen);
                            System.out.println("\nDesde el nodo " + grafo.getNodoName(origen) + ":");
                            System.out.println("%-10s %-10s\n", "Nodo", "Distancia");
                            System.out.println("====================================");
                            for (int destino = 0; destino < distancias.length; destino++) {
                               System.out.printf("%-10s %-10s\n", grafo.getNodoName(destino), distancias[destino] == Integer.MAX_VALUE ? "INF" : distancias[destino]);
                            }
                        }
                        System.out.println("=======================================================\n");
                        
                    }
                    
                    case 2-> {
                        System.out.println("\n=====ANALISIS GLOBAL DE LA RED (FLOYD-WARSHALL)=====");
                        FloydWarshall floydWarshall = new FloydWarshall();
                        int[][] distancia = FloydWarshall.calcularRutasMinimas(grafo.toMatrix());
                        System.out.println("\nMatriz de distancia minimas:")
                        for (int [] fila :distancias){
                            for (int valor :fila){
                                System.out.println((valor == Integer.MAX_VALUE?"INF":valor)+" ");    
                            }
                            System.out.println();
                        }
                        System.out.println("================================================================\n");  
                    } 
                    
                    case 3 -> {
                        System.out.println("\n===== DETECCION DE PERDIDAS ECONOMICAS NEGATIVAS (BELLMAN-FORD) =====");
                        System.out.println("Ingrese el nodo de origen: ");
                        String origen = scanner.nextLine();
                        BellmanFord bellmanFord = new BellmanFord();
                        boolean tieneCiclos = bellmanFord.detectarCiclosNegativos(grafo.toMatrix, grafo.getNodoIndex(origen));
                        if (tieneCiclos) {
                            System.out.println("\nSe detectaron ciclos negativos en la red electrica.");
                        } else {
                            System.out.println("\nNo se detectaron ciclos negativos en la red electrica.");
                            
                        }
                        System.out.println("=======================================================\n");
                        
                    }

                    case 4 -> {
                        System.out.println("\n===== SIMULACION DE FALLAS =====");
                        System.out.println("1.Iniciar simulacion de fallas");
                        System.out.println("2.Detener simulacion de fallas");
                        int opcionSimulacion = scanner.nextInt();
                        scanner.nextLine(); 
                        if (opcionSimulacion == 1) {
                            simuladorDeFallos.iniciarSimulacion();
                            System.out.println("Simulacion de fallas iniciada.");
                        } else if (opcionSimulacion == 2) {
                            simuladorDeFallos.detenerSimulacion();
                            System.out.println("Simulacion de fallas detenida.");
                        } else {
                            System.out.println("Opcion invalida.");
                        }
                    }

                    case 5 -> {
                        System.out.println("\n===== SIMULACION DE CONSUMO =====");
                        System.out.println("1.Iniciar simulacion de consumo");
                        System.out.println("2.Detener simulacion de consumo");
                        int opcionSimulacion = scanner.nextInt();
                        scanner.nextLine(); 
                        if (opcionSimulacion == 1) {
                            simuladorConsumo.iniciarSimulacion();
                            System.out.println("Simulacion de consumo iniciada.");
                        } else if (opcionSimulacion == 2) {
                            simuladorConsumo.detenerSimulacion();
                            System.out.println("Simulacion de consumo detenida.");
                        } else {
                            System.out.println("Opcion invalida.");
                        }
                    }

                    case 6 -> {
                        System.out.println("\n===== ALERTAS =====");
                        //Generar alertas de prueba
                        alertas.agregarAlerta("Alerta 1:", "Sobrecarga detectada en el nodo A.");
                        alertas.agregarAlerta("Alerta 2:", "Falla en la conexión entre los nodos B y C.");
                        alertas.agregarAlerta("Alerta 3:", "Consumo excesivo en el nodo C.");

                        // Procesar Y mostrar las alertas
                        while (alertas.hayAlertas()) {
                            String alertaProcesada = alertas.procesarAlerta();
                            System.out.println(alertaProcesada);   
                        }
                        System.out.println("No hay alertas pendientes.");
                    }
                    case 7 -> {
                        System.out.println("Saliendo del programa...");
                        salir = true;
                    }
                }
                    
            }
        }
        grafo.agregarNodo("D");
        grafo.agregarNodo("E");
        grafo.agregarNodo("F");
        grafo.agregarNodo("D","E",6);
        grafo.agregarNodo("E","F",7);
        grafo.agregarNodo("F","D",8);
        grafo.agregarNodo("A","D",9);
        grafo.agregarNodo("B","E",10);
        grafo.agregarNodo("C","F",11);

        System.out.println("Se han agregado nuevos nodos y conexiones a la red.");
    }
    default -> System.out.println("Opcion no valida."); 
    
    public static void main(String [] args) {
        Grafo grafo = new Grafo();
        grafo.agregarNodo("A");
        grafo.agregarNodo("B");
        grafo.agregarNodo("C");
        grafo.agregaArista("A", "B", 4);
        grafo.agregaArista("B", "C", 3);
        grafo.agregaArista("A", "C", 5);
        grafo.agregaArista("C", "A", 2);

        grafo.agregarNodo("D");
        grafo.agregarNodo("E");
        grafo.agregarNodo("F");
        grafo.agregarNodo("G");
        grafo.agregarNodo("H");
        grafo.agregarNodo("I");

        grafo.agregaArista("A", DE",61)
        grafo.agregaArista("D, "E", 7);

        grafo.agregaArista("F, "G", 9);
        grafo.agregaArista("G, "H", 10);
        grafo.agregaArista("H, "I", 11);
        grafo.agregaArista("I, "A", 12);
        grafo.agregaArista("B, "F", 5);
        grafo.agregaArista("C, "G", 4);
        
        iniciar(grafo);
    }


}

