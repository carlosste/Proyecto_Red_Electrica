import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Encabezado {
public static void encabezadopgm() {
        // Datos del encabezado
        String nombre = "Carlos Steven Lozano Quintero";
        String campus = "Campus Cali, U. Cooperativa de Colombia";
        String repositorioGit = "https://github.com/carlosste/Trabajo_F_ED";

        // Obtener la fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaHora = ahora.format(formateador);

        // imprimir encabezado
        System.out.println();
        System.out.println("+----------------------------------------");
        System.out.println("| 👤 Nombres: ");
        System.out.println ("| Carlos Steven Lozano Quintero" );
        System.out.println ("| Ever Wisney Rodriguez Guzman" );
        System.out.println ("| Juan David Arboleda Molina" );
        System.out.println ("| Juan Diego Nieto Caceres" );
        System.out.println("| 🎓 Campus: " + campus);
        System.out.println("| 📅 Fecha y hora: " + fechaHora);
        System.out.println("| 📂 Repositorio Git: " + repositorioGit);
        System.out.println("+----------------------------------------");
        System.out.println();
    }

}
