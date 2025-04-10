package es.etg.prog.practica.controller;

import java.util.List;

import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresException;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresPosicionException;
import es.etg.prog.practica.model.excepciones.Excepciones.NumeroIncorrectoException;
import es.etg.prog.practica.model.fichero.Fichero;
import es.etg.prog.practica.model.temporada.Arbitro;
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
                gestorEntradaSalida.imprimirMensaje("Introduce el nombre del equipo: ");
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

                    Jugador jugador = JugadorFactory.crearJugador(nombre, dorsal, altura, habilidad);

                    // Guardar el jugador en el fichero
                    fichero.guardarJugador(jugador);

                    // Leer todos los jugadores del fichero tras guardar el nuevo
                    List<Jugador> jugadoresActualizados = fichero.leerJugadores();

                    // Actualizar el equipo con los jugadores leídos
                    equipo.getJugadores().clear();
                    equipo.getJugadores().addAll(jugadoresActualizados);

                    // Reconstruir el mapa jugadoresPorPosicion
                    equipo.getJugadoresPorPosicion().clear();
                    for (Jugador j : jugadoresActualizados) {
                        String tipo = j.getTipo();
                        int cantidad = equipo.getJugadoresPorPosicion().getOrDefault(tipo, 0);
                        equipo.getJugadoresPorPosicion().put(tipo, cantidad + 1);
                    }

                    gestorEntradaSalida.imprimirMensajeSeparado("Jugador agregado con éxito.");
                    fichero.mostrarJugadores();
                    break;

                    case 2:
                    gestorEntradaSalida.imprimirMensajeSeparado("Lista de jugadores:");
                
                    // Leer jugadores desde archivo
                    List<Jugador> jugadores = fichero.leerJugadores();
                
                    if (jugadores.isEmpty()) {
                        gestorEntradaSalida.imprimirMensajeSeparado("No hay jugadores en el archivo.");
                    } else {
                        gestorEntradaSalida.imprimirMensajeSeparado("Jugadores disponibles para eliminar:");
                        for (int i = 0; i < jugadores.size(); i++) {
                            Jugador jugador1 = jugadores.get(i);
                            gestorEntradaSalida.imprimirMensajeSeparado((i + 1) + ". " + jugador1.toString());
                        }
                
                        gestorEntradaSalida.imprimirMensaje(
                                "Introduce el número del jugador a eliminar (1-" + jugadores.size() + "): ");
                        int numeroJugador = gestorEntradaSalida.leerInt();
                
                        if (numeroJugador < 1 || numeroJugador > jugadores.size()) {
                            gestorEntradaSalida.imprimirMensajeSeparado("Número incorrecto.");
                        } else {
                            // Eliminar el jugador de la lista
                            Jugador jugadorEliminado = jugadores.remove(numeroJugador - 1);
                
                            // Actualizar los datos del equipo con los jugadores nuevos
                            equipo.getJugadores().clear();
                            equipo.getJugadores().addAll(jugadores);
                
                            // Reconstruir el mapa jugadoresPorPosicion
                            equipo.getJugadoresPorPosicion().clear();
                            for (Jugador j : jugadores) {
                                String tipo = j.getTipo();
                                int cantidad = equipo.getJugadoresPorPosicion().getOrDefault(tipo, 0);
                                equipo.getJugadoresPorPosicion().put(tipo, cantidad + 1);
                            }
                
                            // Guardar en el fichero los jugadores actualizados (sobrescribir)
                            fichero.guardarJugadores(jugadores); // Aquí sobrescribimos todo el archivo
                
                            gestorEntradaSalida.imprimirMensajeSeparado("Jugador eliminado: " + jugadorEliminado);
                
                        }
                    }
                    break;
                

                case 3:

                    gestorEntradaSalida.imprimirMensaje("Nombre del equipo visitante: ");
                    String nombreVisitante = gestorEntradaSalida.leerLinea();

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
