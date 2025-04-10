package es.etg.prog.practica.controller;

import java.util.List;

import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresException;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresPosicionException;
import es.etg.prog.practica.model.excepciones.Excepciones.NumeroIncorrectoException;
import es.etg.prog.practica.model.fichero.Fichero;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.Temporada;
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
    }

    public void menu() throws MaximoJugadoresException, MaximoJugadoresPosicionException, NumeroIncorrectoException {
        int opcion;
        boolean salir = false;

        while (!salir) {
            if (equipo == null) {
                gestorEntradaSalida.imprimirMensajeSeparado("Introduce el nombre del equipo: ");
                String nombreEquipo = gestorEntradaSalida.leerLinea();
                equipo = new Equipo(nombreEquipo);

                Temporada.getInstancia().getEquipos().add(equipo);

                gestorEntradaSalida.imprimirMensajeSeparado("Equipo " + nombreEquipo + " creado con éxito.");
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
                    } /*
                       * catch (MaximoJugadoresException e) {
                       * gestorEntradaSalida.
                       * imprimirMensajeSeparado("Error: El equipo ya tiene el máximo de jugadores.");
                       * }
                       */
                    break;

                    case 2:
                    try {
                        gestorEntradaSalida.imprimirMensajeSeparado("Lista de jugadores:");
                
                        List<Jugador> jugadores = fichero.leerJugadores();
                
                        if (jugadores.isEmpty()) {
                            gestorEntradaSalida.imprimirMensajeSeparado("No hay jugadores en el archivo.");
                        } else {
                            // Mostrar la lista de jugadores cargados
                            for (int i = 0; i < jugadores.size(); i++) {
                                Jugador jugador = jugadores.get(i);
                                gestorEntradaSalida.imprimirMensajeSeparado((i + 1) + ". " + jugador.toString());
                            }
                
                            gestorEntradaSalida.imprimirMensaje("Dime el numero del jugador que quieres eliminar: ");
                            int numeroJugador = gestorEntradaSalida.leerInt();
                
                            // Validar que el número esté dentro del rango
                            if (numeroJugador < 1 || numeroJugador > jugadores.size()) {
                                gestorEntradaSalida.imprimirMensajeSeparado("Número incorrecto.");
                            } else {
                                // Llamar al método de eliminarJugador pasando el índice ajustado
                                Jugador jugadorEliminado = equipo.eliminarJugador(numeroJugador - 1); // Restamos 1 para obtener el índice correcto
                
                                gestorEntradaSalida.imprimirMensajeSeparado("Jugador eliminado: " + jugadorEliminado);
                            }
                        }
                    } catch (Excepciones.NumeroIncorrectoException e) {
                        gestorEntradaSalida.imprimirMensajeSeparado("Número incorrecto.");
                    }
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
