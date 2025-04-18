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

                    // gestorEntradaSalida.imprimirMensajeSeparado("Jugador agregado con éxito.");
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
                        gestorEntradaSalida.imprimirMensajeSeparado((i + 1) + ". " + jugador2.getNombre() + " (Dorsal: " + jugador2.getDorsal() + ")");
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
                
                    List<Equipo> rivalesDisponibles = Temporada.getInstancia().mostrarEquiposDisponibles(equipoLocal);
                
                    if (rivalesDisponibles.isEmpty()) {
                        gestorEntradaSalida.imprimirMensajeSeparado("Ya has jugado contra todos los equipos. ¡Temporada terminada!");
                        break;
                    }
                
                    gestorEntradaSalida.imprimirMensajeSeparado("Equipos disponibles para enfrentarse:");
                    for (int i = 0; i < rivalesDisponibles.size(); i++) {
                        gestorEntradaSalida.imprimirMensajeSeparado((i + 1) + ". " + rivalesDisponibles.get(i).getNombre());
                    }
                
                    gestorEntradaSalida.imprimirMensaje("Selecciona el número del equipo rival: ");
                    int numeroEquipoRival = Integer.parseInt(gestorEntradaSalida.leerLinea());
                
                    if (numeroEquipoRival < 1 || numeroEquipoRival > rivalesDisponibles.size()) {
                        gestorEntradaSalida.imprimirMensajeSeparado("Opción inválida.");
                        break;
                    }
                
                    Equipo equipoVisitante = rivalesDisponibles.get(numeroEquipoRival - 1);
                
                    Arbitro arbitro = null;
                    try {
                        arbitro = Arbitro.elegirArbitro();
                        gestorEntradaSalida.imprimirMensajeSeparado("El árbitro seleccionado es: " + arbitro.getNombre());
                    } catch (Excepciones.ArbitrosNoDisponibles e) {
                        gestorEntradaSalida.imprimirMensajeSeparado(e.getMessage());
                        break;
                    }
                
                    gestorEntradaSalida.imprimirMensaje("El partido es Oficial (O) o de Exhibición (E): ");
                    String respuesta = gestorEntradaSalida.leerLinea();
                
                    boolean esOficial = false;
                    if (respuesta.equalsIgnoreCase("O")) {
                        esOficial = true;
                    } else if (respuesta.equalsIgnoreCase("E")) {
                        esOficial = false;
                    } else {
                        gestorEntradaSalida.imprimirMensajeSeparado("Opción no válida.");
                        break;
                    }
                
                    if (!Temporada.getInstancia().verificarPartidoYaJugado(equipoLocal, equipoVisitante)) {
                        try {
                            Partido partido = Temporada.getInstancia().jugarPartido(equipoLocal, equipoVisitante, arbitro, esOficial);
                
                            fichero.guardarResumen(partido.getEquipoLocal(), partido);
                            fichero.guardarResumenUltimoPartido(partido.getEquipoLocal(), partido);
                            fichero.guardarHistoricoTemporada();
                
                            //String resumen = Temporada.getInstancia().generarResumenPartido(partido);
                            //gestorEntradaSalida.imprimirMensaje(resumen);
                
                            // Verifica si ya se ha jugado contra todos (solo cuenta oficiales)
                            if (esOficial && Temporada.getInstancia().verificarFinTemporada(equipoLocal)) {
                                gestorEntradaSalida.imprimirMensajeSeparado("¡Has jugado contra todos los equipos! La temporada ha terminado.");
                            }
                
                        } catch (Exception e) {
                            gestorEntradaSalida.imprimirMensajeSeparado("Error al jugar el partido: " + e.getMessage());
                        }
                    } else {
                        gestorEntradaSalida.imprimirMensajeSeparado("Ya se ha jugado 2 veces este partido.");
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

                    gestorEntradaSalida.imprimirMensaje("Qué posición quieres ver: ");
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
                        gestorEntradaSalida.imprimirMensajeSeparado("No hay jugadores del tipo: " + tipoBuscado);
                    }
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