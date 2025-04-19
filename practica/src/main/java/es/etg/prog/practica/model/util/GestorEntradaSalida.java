package es.etg.prog.practica.model.util;
/**
 * 
 * @author Jorge
 */
import java.util.Scanner;

public class GestorEntradaSalida {
    private Scanner scanner;

    public GestorEntradaSalida(Scanner scanner) {
        this.scanner = scanner;
    }

    public void imprimirMensajeSeparado(String mensaje) {
        System.out.println(mensaje);
    }

    public void imprimirMensajeSeparadoInt(int numero) {
        System.out.println(numero);
    }

    public void imprimirMensaje(String mensaje) {
        System.out.print(mensaje);
    }

    public void imprimirMensajeConFormato(String formato) {
        System.out.printf(formato); // Usa printf para formatos complejos
    }

    public String leerLinea() {
        return scanner.nextLine().trim();
    }

    public int leerInt() {
        int numero = scanner.nextInt();  // Leer el número
        scanner.nextLine();  // Limpiar el salto de línea residual
        return numero;
    }
    

    public double leerDouble() {
        double numero = scanner.nextDouble();
        scanner.nextLine(); 
        return numero;
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
