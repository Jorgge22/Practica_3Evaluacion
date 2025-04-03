package es.etg.prog.practica.model.util;

import java.util.Scanner;

public class GestorEntradaSalida {
    private Scanner scanner;

    public GestorEntradaSalida(Scanner scanner) {
        this.scanner = scanner;
    }

    public void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void imprimirMensajeSeparado(String mensaje) {
        System.out.print(mensaje);
    }

    public void imprimirMensajeConFormato(String formato) {
        System.out.printf(formato); // Usa printf para formatos complejos
    }

    public String leerLinea() {
        return scanner.nextLine();
    }

    public int leerInt() {
        return scanner.nextInt();
    }

    public double leerDouble() {
        return scanner.nextDouble();
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
