package es.etg.prog.practica.controller;

import java.util.List;

import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.ArbitrosNoDisponibles;
import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresException;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresPosicionException;
import es.etg.prog.practica.model.excepciones.Excepciones.NumeroIncorrectoException;
import es.etg.prog.practica.model.fichero.Fichero;
import es.etg.prog.practica.model.temporada.Arbitro;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.Temporada;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.temporada.jugador.JugadorFactory;
import es.etg.prog.practica.model.temporada.partido.Partido;
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

    public void menu() throws MaximoJugadoresException, MaximoJugadoresPosicionException, NumeroIncorrectoException,
            ArchivoNoEncontradoException, ArbitrosNoDisponibles {
        String nombreEquipoLocal = "";
        int opcion;
        boolean salir = false;

        while (!salir) {
            if (equipo == null) {
                gestorEntradaSalida.imprimirMensaje("Introduce el nombre del equipo: ");
                nombreEquipoLocal = gestorEntradaSalida.leerLinea();
                equipo = new Equipo(nombreEquipoLocal);

                Temporada.getInstancia().getEquipos().add(equipo);

                List<Jugador> jugadoresGuardados = fichero.leerJugadores();
                if (jugadoresGuardados.isEmpty()) {
                    System.out.println("No hay jugadores cargados.");
                } else {
                    equipo.getJugadores().clear();
                    equipo.getJugadores().addAll(jugadoresGuardados);
                }

                gestorEntradaSalida.imprimirMensajeSeparado("Equipo " + nombreEquipoLocal + " creado con éxito.");
            }

            gestorEntradaSalida.imprimirMensajeConFormato(Constantes.MSG_MENU);
            gestorEntradaSalida.imprimirMensajeConFormato(Constantes.MSG_OPCION);
            opcion = gestorEntradaSalida.leerInt();

            switch (opcion) {
                case 1:
                    gestorEntradaSalida.imprimirMensaje("Nombre: ");
                    String nombre = gestorEntradaSalida.leerLinea();

                    gestorEntradaSalida.imprimirMensaje("Dorsal: ");
                    int dorsal = gestorEntradaSalida.leerInt();

                    gestorEntradaSalida.imprimirMensaje("Altura (1-5): ");
                    int altura = gestorEntradaSalida.leerInt();

                    gestorEntradaSalida.imprimirMensaje("Habilidad (1-5): ");
                    int habilidad = gestorEntradaSalida.leerInt();

                    Jugador jugador = JugadorFactory.crearJugador(nombre, dorsal, altura, habilidad);

                    equipo.getJugadores().add(jugador);

                    fichero.guardarJugador(equipo.getJugadores());

                    // Leer todos los jugadores del fichero tras guardar el nuevo
                    List<Jugador> jugadoresActualizados = fichero.leerJugadores();

                    // Actualizar el equipo con los jugadores leídos
                    equipo.getJugadores().clear();
                    equipo.getJugadores().addAll(jugadoresActualizados);

                    gestorEntradaSalida.imprimirMensajeSeparado("Jugador agregado con éxito.");
                    fichero.mostrarJugadores();
                    break;

                case 2:
                    List<Jugador> jugadores = equipo.getJugadores();

                    if (jugadores.isEmpty()) {
                        gestorEntradaSalida.imprimirMensajeSeparado("No hay jugadores para eliminar.");
                        break;
                    }

                    for (int i = 0; i < jugadores.size(); i++) {
                        Jugador jugador2 = jugadores.get(i);
                        gestorEntradaSalida.imprimirMensajeSeparado((i + 1) + ". " + jugador2.getNombre() + " (Dorsal: " + jugador2.getDorsal() + ")");
                    }

                    gestorEntradaSalida.imprimirMensaje("Seleccione el número del jugador a eliminar: ");
                    int numeroAEliminar = gestorEntradaSalida.leerInt();

                    if (numeroAEliminar < 1 || numeroAEliminar > jugadores.size()) {
                        gestorEntradaSalida.imprimirMensajeSeparado("Error. Número fuera de rango.");
                    } else {
                        Jugador jugadorAEliminar = jugadores.get(numeroAEliminar - 1);
                        equipo.eliminarJugador(jugadorAEliminar.getDorsal());
                        gestorEntradaSalida.imprimirMensajeSeparado("Jugador eliminado con éxito.");
                    }
                    break;

                case 3:
                    gestorEntradaSalida.imprimirMensaje("Nombre del equipo visitante: ");
                    String nombreVisitante = gestorEntradaSalida.leerLinea();

                    List<Arbitro> arbitros = fichero.leerArbitros();
                    Arbitro arbitro = null;
                    try {
                        arbitro = Arbitro.elegirArbitro();
                        gestorEntradaSalida.imprimirMensajeSeparado("El árbitro seleccionado es: " + arbitro.getNombre());
                    } catch (Excepciones.ArbitrosNoDisponibles e) {
                        gestorEntradaSalida.imprimirMensajeSeparado(e.getMessage());
                    }

                    gestorEntradaSalida.imprimirMensaje("El partido es Oficial (O) o de Exibición (E): ");
                    String respuesta = gestorEntradaSalida.leerLinea();

                    boolean esOficial = false;
                    if (respuesta.equalsIgnoreCase("O")) {
                        esOficial = true;
                    } else if (respuesta.equalsIgnoreCase("E")) {
                        esOficial = false;
                    } else {
                        gestorEntradaSalida.imprimirMensajeSeparado("Opción no válida.");
                    }

                    Equipo equipoLocal = Temporada.getInstancia().getEquipoPorNombre(nombreEquipoLocal);
                    Equipo equipoVisitante = Temporada.getInstancia().getEquipoPorNombre(nombreVisitante);

                    if (!Temporada.getInstancia().verificarPartidoYaJugado(equipoLocal, equipoVisitante)) {
                        try {
                            // Ejecutar el partido y obtener el resumen
                            String resumen = Temporada.getInstancia().jugarPartido(equipoLocal, equipoVisitante, arbitro, esOficial);
                            gestorEntradaSalida.imprimirMensaje(resumen);
                        } catch (IllegalStateException | ArchivoNoEncontradoException | ArbitrosNoDisponibles e) {
                            gestorEntradaSalida.imprimirMensajeSeparado("Error al jugar el partido: " + e.getMessage());
                        }

                    } else {
                        gestorEntradaSalida.imprimirMensajeSeparado("Ya se ha jugado 2 veces.");
                    }
                    break;

                case 4:

                    break;

                case 7:
                    salir = true;
                    gestorEntradaSalida.imprimirMensajeSeparado("Saliendo...");
                    break;

                default:
                    gestorEntradaSalida.imprimirMensaje("Opción no válida.");
                    break;
            }
        }
    }

}
