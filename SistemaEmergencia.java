// SistemaEmergencia.java
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SistemaEmergencia {

    public static void main(String[] args) {
        VectorHeap<Paciente> colaEmergencia = new VectorHeap<>();

        try {
            File archivoPacientes = new File("pacientes.txt");
            Scanner lector = new Scanner(archivoPacientes);
            
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] datos = linea.split(",");
                if(datos.length == 3) {
                    String nombre = datos[0].trim();
                    String sintoma = datos[1].trim();
                    char codigoEmergencia = datos[2].trim().toUpperCase().charAt(0);
                    colaEmergencia.add(new Paciente(nombre, sintoma, codigoEmergencia));
                }
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo pacientes.txt no encontrado.");
            e.printStackTrace();
            return;
        }

        while (!colaEmergencia.isEmpty()) {
            Paciente paciente = colaEmergencia.remove();
            System.out.println("Atendiendo a: " + paciente);
            // Pausa simulada para la atención
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
