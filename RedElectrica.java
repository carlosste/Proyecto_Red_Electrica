import java.util.*;

public class RedElectrica {

    static ListaEnlazada nodos = new ListaEnlazada();

    /*
     * Este programa simula la gestion de una red electrica, permitiendo calcular
     * rutas de menor perdida, analizar la red, detectar perdidas economicas
     * negativas y simular fallos y consumo.
     * Metodo para mostrar el menu de opciones al usuario.
     */
    public static void mostrarMenu() {
        System.out.println("\n===== MENU DE GESTION DE RED ELECTRICA =====");
        System.out.println("1.Calcular ruta de menor perdida (Dijkstra)");
        System.out.println("2.Analisis global de la red (Floyd-Warshall)");
        System.out.println("3.Detectar perdidas economicas negativas (Bellman-Ford) ");
        System.out.println("4.Iniciar simulacion de fallos");
        System.out.println("5.Iniciar simulacion de Consumo");
        System.out.println("6.Ver alertas");
        System.out.println("7.Ver nodos");
        System.out.println("8.Salir");
        System.out.println("Seleccione una opcion: ");
        System.out.println("================================================");
    }

    /*
     * Este metodo inicia el menu de gestion de la red electrica, permitiendo al
     * usuario seleccionar diferentes opciones para interactuar con la red.
     */
    public static void iniciarMenu(Grafo grafo) {
        ColaAlertas alertas = new ColaAlertas(10);
        SimuladorDeFallos simuladorDeFallos = new SimuladorDeFallos(grafo, alertas);
        SimuladorConsumo simuladorConsumo = new SimuladorConsumo(grafo, alertas);
        boolean salir = false;

        try (Scanner scanner = new Scanner(System.in)) {
            while (!salir) {
                mostrarMenu();
                int opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 1 -> {
                        case1(grafo);
                    }

                    case 2 -> {
                        case2(grafo);
                    }

                    case 3 -> {
                        case3(grafo, scanner);
                    }

                    case 4 -> {
                        case4(scanner, simuladorDeFallos);
                    }

                    case 5 -> {
                        case5(scanner, simuladorConsumo);
                    }
                    case 6 -> {
                        case6(alertas);
                    }
                    case 7 -> {
                        case7(scanner, grafo);

                    }
                    case 8 -> {
                        System.out.println("Saliendo del programa...");
                        salir = true;
                    }
                    default -> System.out.println("Opcion no valida.");
                }
            }
        }
    }

    // FUNCIONES --------------------------------------------------------------
    // ----------------------------------------------------------------- case 1
    /*
     * Este metodo calcula la ruta de menor perdida entre dos nodos en la red
     * electrica utilizando el algoritmo de Dijkstra.
     * 
     * @param grafo El grafo que representa la red electrica.
     */
    public static void case1(Grafo grafo) {
        System.out.println("\n===== CALCULO DE RUTA DE MENOR PERDIDA (DIJKSTRA) =====");
        System.out.println("Ingrese el nodo de origen: ");
        Scanner origen = new Scanner(System.in);
        String nodoOrigen = origen.nextLine().trim().toUpperCase();
        System.out.println("Ingrese el nodo de destino: ");
        Scanner destino = new Scanner(System.in);
        String nodoDestino = destino.nextLine().trim().toUpperCase();
        int origenIndex = grafo.getNodoIndex(nodoOrigen);
        int destinoIndex = grafo.getNodoIndex(nodoDestino);

        // Verificar si los nodos de origen y destino son válidos
        if (origenIndex == -1 || destinoIndex == -1) {
            System.out.println("Nodo de origen o destino no valido.");
            return;
        }

        Dijkstra dijkstra = new Dijkstra();
        int[][] matriz = grafo.toMatrix();
        try {
            int[] distancias = dijkstra.calcularRutaMinima(matriz, origenIndex);
            List<Integer> ruta = dijkstra.obtenerRuta(matriz, origenIndex, destinoIndex);
            // Verificar si hay una ruta disponible
            if (ruta.isEmpty()) {
                System.out.println("No hay ruta disponible entre " + nodoOrigen + " y " + nodoDestino);
            } else {
                System.out.println("Ruta de menor perdida entre " + nodoOrigen + " y " + nodoDestino + ":");
                // Imprimir la ruta
                for (int i = 0; i < ruta.size(); i++) {
                    System.out.print(grafo.getNodos().get(ruta.get(i)));
                    if (i < ruta.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println("\nValor total: " + distancias[destinoIndex]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("=======================================================\n");
    }

    // ----------------------------------------------------------------- case 2
    /*
     * Este metodo realiza un analisis global de la red electrica utilizando el
     * algoritmo de Floyd-Warshall para calcular las distancias minimas entre todos
     * los nodos.
     * 
     * @param grafo El grafo que representa la red electrica.
     */
    public static void case2(Grafo grafo) {
        System.out.println("\n=====ANALISIS GLOBAL DE LA RED (FLOYD-WARSHALL)=====");
        FloydWarshall floydWarshall = new FloydWarshall();
        int[][] distancias = floydWarshall.calcularDistanciasMinimas(grafo.toMatrix());
        System.out.println("\n\033[1mMatriz de distancias mínimas:\033[0m");

        int ancho = 4; // Ancho de cada columna

        List<String> nodos = grafo.getNodos();

        // Imprimir encabezado de columnas
        System.out.print(" ".repeat(ancho));// Espacio para la esquina superior izquierda
        for (String nodo : nodos) {
            boolean activo = grafo.getEstadoNodo(nodo);
            String color = activo ? "\033[32m" : "\033[31m"; // Verde para activo, rojo para inactivo
            System.out.print(color);
            System.out.printf("%" + ancho + "s", nodo);
            System.out.print("\033[0m");
        }
        System.out.println();

        // Imprimir filas con encabezados
        for (int i = 0; i < distancias.length; i++) {
            String nodo = grafo.getNodos().get(i);
            boolean activo = grafo.getEstadoNodo(nodo);
            String color = activo ? "\033[32m" : "\033[31m"; // Verde para activo, rojo para inactivo
            System.out.print(color);
            System.out.printf("%" + ancho + "s", nodo);
            System.out.print("\033[0m");

            for (int j = 0; j < distancias[i].length; j++) {
                String valor = (distancias[i][j] == Integer.MAX_VALUE) ? "∞" : String.valueOf(distancias[i][j]);
                System.out.printf("%" + ancho + "s", valor);
            }
            System.out.println();
        }
        System.out.println("================================================================\n");
    }

    // ----------------------------------------------------------------- case 3
    /*
     * Este metodo detecta perdidas economicas negativas en la red electrica
     * utilizando el algoritmo de Bellman-Ford.
     * 
     * @param grafo El grafo que representa la red electrica.
     * 
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     */
    public static void case3(Grafo grafo, Scanner scanner) {
        System.out.println("\n===== DETECCION DE PERDIDAS ECONOMICAS NEGATIVAS (BELLMAN-FORD) =====");

        BellmanFord bellmanFord = new BellmanFord();
        boolean tieneCiclos = bellmanFord.detectarCiclosNegativos(grafo.toMatrix(), grafo.getNodos());
        grafo.toMatrix();
        grafo.getNodos();

        if (tieneCiclos) {
            System.out.println("Se han detectado ciclos negativos en la red electrica.");
        } else {
            System.out.println("No se han detectado ciclos negativos en la red electrica.");
        }

        System.out.println("=======================================================\n");
    }

    // ----------------------------------------------------------------- case 4
    /*
     * Este metodo inicia la simulacion de fallos en la red electrica.
     * 
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * 
     * @param simuladorDeFallos El objeto SimuladorDeFallos para gestionar la
     * simulacion.
     */
    public static void case4(Scanner scanner, SimuladorDeFallos simuladorDeFallos) {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n===== SIMULACION DE FALLAS =====");
            System.out.println("1.Iniciar simulacion de fallas");
            System.out.println("2.Salir al menú principal");
            int opcionSimulacion = scanner.nextInt();
            scanner.nextLine();
            if (opcionSimulacion == 1) {
                simuladorDeFallos.iniciar();
                System.out.println("Simulacion de fallas iniciada.");
            } else if (opcionSimulacion == 2) {
                volver = true;
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    // ----------------------------------------------------------------- case 5
    /*
     * Este metodo inicia la simulacion de consumo en la red electrica.
     * 
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * 
     * @param simuladorConsumo El objeto SimuladorConsumo para gestionar la
     * simulacion.
     */
    public static void case5(Scanner scanner, SimuladorConsumo simuladorConsumo) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n===== SIMULACION DE CONSUMO =====");
            System.out.println("1.Iniciar simulacion de consumo");
            System.out.println("2.Salir al menú principal");
            int opcionSimulacion = scanner.nextInt();
            scanner.nextLine();
            if (opcionSimulacion == 1) {
                simuladorConsumo.iniciar();
                System.out.println("Simulacion de consumo iniciada.");
            } else if (opcionSimulacion == 2) {
                volver = true;
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    // ----------------------------------------------------------------- case 6
    /*
     * Este metodo muestra las alertas generadas durante la simulacion de fallos o
     * consumo.
     * 
     * @param alertas La cola de alertas generada durante la simulacion.
     */
    public static void case6(ColaAlertas alertas) {
        System.out.println("\n===== ALERTAS =====");
        // Procesar Y mostrar las alertas
        while (alertas.hayAlertas()) {
            String alertaProcesada = alertas.procesarAlerta();
        }
        System.out.println("\n No hay mas alertas pendientes.");
    }

    public static void case7(Scanner scanner, Grafo grafo) {
        int opcion = -1;

        do {

            grafo.mostrarEstadosNodos();

            System.out.println("\n================================================");
            System.out.println("\n 📋 Opciones ");
            System.out.println("1. Activar nodo");
            System.out.println("2. Desactivar nodo");
            System.out.println("3. Volver al menu principal");
            System.out.println("seleccione una opcion: ");
            System.out.println("================================================");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada invalida. Intente de nuevo.");
                continue;
            }

            switch (opcion) {
                case 1 -> grafo.cambiarEstadoNodo(true, scanner);
                case 2 -> grafo.cambiarEstadoNodo(false, scanner);
                case 3 -> System.out.println("Volviendo al menu principal. . .");
                default -> System.out.println("⚠️ Opcion no valida. ");

            }

        } while (opcion != 3);
    }

    // --------------------------------------------------
    // Main
    public static void main(String[] args) {
        // Crear un grafo de ejemplo
        Grafo grafo = new Grafo();
        grafo.agregarNodo("A");
        grafo.agregarNodo("B");
        grafo.agregarNodo("C");
        grafo.agregarNodo("D");
        grafo.agregarNodo("E");
        grafo.agregarNodo("F");
        grafo.agregarNodo("G");
        grafo.agregarAristaAleatoria("A", "C");
        grafo.agregarAristaAleatoria("A", "B");
        grafo.agregarArista("B", "C", -3);
        grafo.agregarArista("C", "A", 2);
        grafo.agregarArista("D", "E", 5);
        grafo.agregarArista("F", "G", 3);
        grafo.agregarArista("G", "D", -2);
        grafo.agregarArista("A", "D", -1);
        grafo.agregarArista("B", "E", -2);
        grafo.agregarArista("C", "F", 3);
        grafo.agregarArista("E", "F", 2);
        grafo.agregarAristaAleatoria("F", "G");

        Encabezado.encabezadopgm();
        // iniciar el menu de gestion de la red electrica
        iniciarMenu(grafo);
    }

}
