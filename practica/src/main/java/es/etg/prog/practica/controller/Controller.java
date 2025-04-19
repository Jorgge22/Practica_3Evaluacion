package es.etg.prog.practica.controller;

/**
 * 
 * @author Jorge
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.ArbitrosNoDisponibles;
import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.excepciones.Excepciones.ErrorArchivoException;
import es.etg.prog.practica.model.excepciones.Excepciones.ErrorJugadoresException;
import es.etg.prog.practica.model.excepciones.Excepciones.ErrorLeerJugadoresException;
import es.etg.prog.practica.model.excepciones.Excepciones.ErrorLineaException;
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

    public void menu() throws MaximoJugadoresException, MaximoJugadoresPosicionException, NumeroIncorrectoException, ArchivoNoEncontradoException, ArbitrosNoDisponibles, ErrorJugadoresException, ErrorArchivoException, ErrorLineaException, IOException, ErrorLeerJugadoresException {
        String nombreEquipoLocal = "";
        int opcion;
        boolean salir = false;

        while (!salir) {
            if (equipo == null) {
                List<Equipo> todosLosEquipos = fichero.leerEquipos();
                Temporada.getInstancia().setEquipos(todosLosEquipos);

                fichero.leerArbitros();

                gestorEntradaSalida.imprimirMensaje(Constantes.MSG_NOMBRE_EQUIPO);
                nombreEquipoLocal = gestorEntradaSalida.leerLinea();
                equipo = new Equipo(nombreEquipoLocal);

                Temporada.getInstancia().getEquipos().add(equipo);

                List<Jugador> jugadoresGuardados = fichero.leerJugadores();
                if (jugadoresGuardados.isEmpty()) {
                    System.out.println(Constantes.MSG_JUGADORES_NO_CARGADOS);
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
                    gestorEntradaSalida.imprimirMensaje(Constantes.MSG_NOMBRE);
                    String nombre = gestorEntradaSalida.leerLinea();

                    gestorEntradaSalida.imprimirMensaje(Constantes.MSG_DORSAL);
                    int dorsal = gestorEntradaSalida.leerInt();

                    gestorEntradaSalida.imprimirMensaje(Constantes.MSG_ALTURA);
                    int altura = gestorEntradaSalida.leerInt();

                    gestorEntradaSalida.imprimirMensaje(Constantes.MSG_HABILIDAD);
                    int habilidad = gestorEntradaSalida.leerInt();

                    Jugador jugador = JugadorFactory.crearJugador(nombre, dorsal, altura, habilidad);

                    if (equipo.agregarJugador(jugador)) {
                        gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_JUGADOR_CREADO);
                    } else {
                        gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_JUGADOR__NO_CREADO);
                    }

                    fichero.guardarJugador(equipo.getJugadores());

                    // Leer todos los jugadores del fichero tras guardar el nuevo
                    List<Jugador> jugadoresActualizados = fichero.leerJugadores();

                    // Actualizar el equipo con los jugadores leídos
                    equipo.getJugadores().clear();
                    equipo.getJugadores().addAll(jugadoresActualizados);

                    fichero.mostrarJugadores();
                    break;

                case 2:
                    List<Jugador> jugadores = equipo.getJugadores();

                    if (jugadores.isEmpty()) {
                        gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_JUGADORES_VACIO);
                        break;
                    }

                    for (int i = 0; i < jugadores.size(); i++) {
                        Jugador jugador2 = jugadores.get(i);
                        gestorEntradaSalida.imprimirMensajeSeparado((i + 1) + ". " + jugador2.getNombre() + Constantes.MSG_DORSAL_MENU + jugador2.getDorsal() + ")");
                    }

                    gestorEntradaSalida.imprimirMensaje(Constantes.MSG_JUGADOR_ELIMINAR);
                    int numeroAEliminar = gestorEntradaSalida.leerInt();

                    if (numeroAEliminar < 1 || numeroAEliminar > jugadores.size()) {
                        gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_FUERA_RANGO);
                    } else {
                        Jugador jugadorAEliminar = jugadores.get(numeroAEliminar - 1);
                        equipo.eliminarJugador(jugadorAEliminar.getDorsal());
                        gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_JUGADOR_ELIMINADO);
                    }
                    break;

                case 3:
                    Equipo equipoLocal = Temporada.getInstancia().getEquipoPorNombre(nombreEquipoLocal);

                    // Preparamos la lista de todos los rivales posibles
                    List<Equipo> todosRivales = new ArrayList<>(Temporada.getInstancia().getEquipos());
                    todosRivales.remove(equipoLocal);

                    Equipo equipoVisitante = null;
                    boolean esOficial = false;

                    while (true) {
                        // Mostrar lista numerada de rivales
                        gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_EQUIPOS_DISPONIBLES);
                        for (int i = 0; i < todosRivales.size(); i++) {
                            gestorEntradaSalida.imprimirMensajeSeparado((i + 1) + ". " + todosRivales.get(i).getNombre());
                        }

                        gestorEntradaSalida.imprimirMensaje(Constantes.MSG_NOMBRE_VISITANTE);
                        int numeroRival = gestorEntradaSalida.leerInt();

                        if (numeroRival < 1 || numeroRival > todosRivales.size()) {
                            gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_OPCION_INVALIDA);
                            continue;
                        }
                        equipoVisitante = todosRivales.get(numeroRival - 1);

                        // Pedir tipo de partido
                        gestorEntradaSalida.imprimirMensaje(Constantes.MSG_TIPO_PARTIDO);
                        String tipo = gestorEntradaSalida.leerLinea().trim().toUpperCase();
                        if (tipo.equals("O")) {
                            esOficial = true;
                            // Si ya jugaste oficial contra este rival, error y repite
                            if (Temporada.getInstancia().verificarPartidoYaJugado(equipoLocal, equipoVisitante)) {
                                gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_FINAL_TEMPORADA + equipoVisitante.getNombre() + Constantes.MSG_OTRO_RIVAL);
                                continue;
                            }
                        } else if (tipo.equals("E")) {
                            esOficial = false;
                        } else {
                            gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_OPCION_INVALIDA);
                            continue;
                        }

                        break;
                    }

                    // Elegir árbitro
                    Arbitro arbitro;
                    try {
                        arbitro = Arbitro.elegirArbitro();
                        gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_ARBITRO + arbitro.getNombre());
                    } catch (Excepciones.ArbitrosNoDisponibles e) {
                        throw new Excepciones.ArbitrosNoDisponibles();
                    }

                    // Jugar el partido
                    try {
                        Partido partido = Temporada.getInstancia().jugarPartido(equipoLocal, equipoVisitante, arbitro, esOficial);

                        fichero.guardarResumen(partido.getEquipoLocal(), partido);
                        fichero.guardarResumenUltimoPartido(partido.getEquipoLocal(), partido);
                        fichero.guardarHistoricoTemporada();

                        // Si era oficial registrar para bloquearlo en el futuro
                        if (esOficial) {
                            Temporada.getInstancia().registrarPartidoJugado(partido);
                            if (Temporada.getInstancia().verificarFinTemporada(equipoLocal)) {
                                gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_FINAL_TEMPORADA);
                            }
                        }
                    } catch (Exception e) {
                        gestorEntradaSalida.imprimirMensajeSeparado("Error al jugar: " + e.getMessage());
                    }
                    break;

                case 4:
                    String resumen = fichero.leerUltimoPartido();
                    gestorEntradaSalida.imprimirMensaje(resumen);
                    break;

                case 5:
                    String resumenTemporada = fichero.leerHistoricoTemporada();
                    gestorEntradaSalida.imprimirMensajeSeparado(resumenTemporada);
                    break;

                case 6:
                    boolean existeTipo = false;

                    List<Jugador> jugadoresAMostrar = equipo.getJugadores();

                    gestorEntradaSalida.imprimirMensaje(Constantes.MSG_POSICION);
                    String tipoBuscado = gestorEntradaSalida.leerLinea();

                    for (Jugador jugadorBuscado : jugadoresAMostrar) {
                        if (jugadorBuscado.getTipo().equalsIgnoreCase(tipoBuscado)) {
                            existeTipo = true;
                            break;
                        }
                    }

                    if (existeTipo) {
                        fichero.guardarResumenJugador(equipo, tipoBuscado);
                    } else {
                        gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_ERROR_JUGADORES + tipoBuscado);
                    }
                    break;

                case 7:
                    salir = true;
                    gestorEntradaSalida.imprimirMensajeSeparado(Constantes.MSG_SALIR);
                    break;

                default:
                    gestorEntradaSalida.imprimirMensaje(Constantes.MSG_OPCION_INVALIDA);
                    break;
            }
        }
    }

}