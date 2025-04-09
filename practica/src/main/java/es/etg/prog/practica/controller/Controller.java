package es.etg.prog.practica.controller;

import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresException;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresPosicionException;
import es.etg.prog.practica.model.fichero.Fichero;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.temporada.jugador.JugadorFactory;
import es.etg.prog.practica.model.util.Constantes;
import es.etg.prog.practica.model.util.GestorEntradaSalida;

public class Controller {
    private final GestorEntradaSalida gestorEntradaSalida;
    private final Fichero fichero;
    private Equipo equipo;

    public Controller(GestorEntradaSalida gestorEntradaSalida) {
        this.gestorEntradaSalida = gestorEntradaSalida;
        this.fichero = new Fichero();
        this.equipo = new Equipo("Equipo de Ejemplo");
    }

    public void menu() throws MaximoJugadoresException, MaximoJugadoresPosicionException {
        int opcion;
        boolean salir = false;

        while (!salir) {
            if (equipo == null) {
                // Si el equipo no está creado, pedimos el nombre
                gestorEntradaSalida.imprimirMensaje("Introduce el nombre del equipo: ");
                String nombreEquipo = gestorEntradaSalida.leerLinea();
                equipo = new Equipo(nombreEquipo); 
                gestorEntradaSalida.imprimirMensaje("Equipo " + nombreEquipo + " creado con éxito.");
            }
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

                    try {
                        Jugador jugador = JugadorFactory.crearJugador(nombre, dorsal, altura, habilidad);
                        equipo.agregarJugador(nombre, dorsal, altura, habilidad);
                        gestorEntradaSalida.imprimirMensajeSeparado("Jugador agregado con éxito.");
                        fichero.guardarJugador(jugador);
                        fichero.mostrarJugadores();
                    } catch (MaximoJugadoresPosicionException e) {
                        gestorEntradaSalida.imprimirMensajeSeparado("Error: Ya hay 3 jugadores en esta posición.");
                    } /*catch (MaximoJugadoresException e) {
                        gestorEntradaSalida.imprimirMensajeSeparado("Error: El equipo ya tiene el máximo de jugadores.");
                    }*/
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
