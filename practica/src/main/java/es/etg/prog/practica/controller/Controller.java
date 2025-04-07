package es.etg.prog.practica.controller;

import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresException;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresPosicionException;
import es.etg.prog.practica.model.fichero.Fichero;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.util.Constantes;
import es.etg.prog.practica.model.util.GestorEntradaSalida;

/**
 * 
 * @author Jorge
 */
public class Controller {
    private final GestorEntradaSalida gestorEntradaSalida;
    private final Fichero fichero;

    public Controller(GestorEntradaSalida gestorEntradaSalida) {
        this.gestorEntradaSalida = gestorEntradaSalida;
        this.fichero = new Fichero();
    }

    public void menu() throws MaximoJugadoresException, MaximoJugadoresPosicionException {
        int opcion;
        boolean salir = false;

        while (!salir) {
            gestorEntradaSalida.imprimirMensajeConFormato(Constantes.MSG_MENU);
            gestorEntradaSalida.imprimirMensajeConFormato(Constantes.MSG_OPCION);
            opcion = gestorEntradaSalida.leerInt();

            gestorEntradaSalida.leerLinea(); // Salto de línea

            switch (opcion) {
                case 1:
                    gestorEntradaSalida.imprimirMensaje("Nombre: ");
                    String nombre = gestorEntradaSalida.leerLinea();

                    gestorEntradaSalida.imprimirMensaje("Dorsal: ");
                    int dorsal = gestorEntradaSalida.leerInt();

                    gestorEntradaSalida.leerLinea();

                    gestorEntradaSalida.imprimirMensaje("Altura (1-5): ");
                    int altura = gestorEntradaSalida.leerInt();

                    gestorEntradaSalida.leerLinea();

                    gestorEntradaSalida.imprimirMensaje("Habilidad (1-5): ");
                    int habilidad = gestorEntradaSalida.leerInt();

                    gestorEntradaSalida.leerLinea();

                    Jugador jugador = new Jugador(nombre, dorsal, altura, habilidad){
                        
                    };

                    gestorEntradaSalida.imprimirMensaje("Nombre del equipo: ");
                    String nombreEquipo = gestorEntradaSalida.leerLinea();
                    Equipo equipo = new Equipo(nombreEquipo);

                    boolean jugadorAgregado = equipo.agregarJugador(jugador);

                    if (jugadorAgregado) {
                        gestorEntradaSalida.imprimirMensajeSeparado("Jugador agregado con exito");
                    } else {
                        gestorEntradaSalida.imprimirMensajeSeparado("No se pudo agregar al jugador");
                    }
                    break;
                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;
                default:
                    break;
            }

        }
    }
}
