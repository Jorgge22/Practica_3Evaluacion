package es.etg.prog.practica.model.util;

import java.util.Scanner;

public class GestorEntradaSalida {
    private Scanner scanner;

    public GestorEntradaSalida(Scanner scanner) {
        this.scanner = scanner;
    }

    public void imprimirMensajeSeparado(String mensaje) {
        System.out.println(mensaje);
    }

    public void imprimirMensaje(String mensaje) {
        System.out.print(mensaje);
    }

    public void imprimirMensajeConFormato(String formato) {
        System.out.printf(formato); // Usa printf para formatos complejos
    }

    public String leerLinea() {
        return scanner.nextLine();
    }

    public int leerInt() {
        int numero = scanner.nextInt();
        scanner.nextLine(); // limpiar el \n
        return numero;
    }

    public double leerDouble() {
        double numero = scanner.nextDouble();
        scanner.nextLine(); // limpiar el \n
        return numero;
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
