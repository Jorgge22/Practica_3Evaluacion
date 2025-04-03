package es.etg.prog.practica.view;

import java.util.Scanner;

import es.etg.prog.practica.controller.Controller;
import es.etg.prog.practica.model.util.GestorEntradaSalida;

/**
 * 
 * @author Jorge
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Crear el objeto gestorEntradaSalida
        GestorEntradaSalida gestorEntradaSalida = new GestorEntradaSalida(scanner);

        // Crear el controlador pasando el gestorEntradaSalida
        Controller controller = new Controller(gestorEntradaSalida);

        // Llamar al método menu del controlador
        try {
            controller.menu();  // Aquí llamamos al método menu() del Controller
        } catch (Exception e) {
            // Manejar las excepciones de acuerdo a lo que necesites
            e.printStackTrace();
        }
    }
}
