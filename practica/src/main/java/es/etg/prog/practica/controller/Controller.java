package es.etg.prog.practica.controller;

import java.util.ArrayList;
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
    
                List<Jugador> jugadoresGuardados = fichero.leerJugadores();
                if (jugadoresGuardados.isEmpty()) {
                    System.out.println("No hay jugadores cargados.");
                } else {
                    equipo.getJugadores().clear();
                    equipo.getJugadores().addAll(jugadoresGuardados);
                }
    
                gestorEntradaSalida.imprimirMensajeSeparado("Equipo " + nombreEquipo + " creado con éxito.");
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
    
                    // Guardar el jugador en el fichero
                    fichero.guardarJugador(jugador);
    
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
                        gestorEntradaSalida.imprimirMensajeSeparado(
                                (i + 1) + ". " + jugador2.getNombre() + " (Dorsal: " + jugador2.getDorsal() + ")");
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
                    break;
    
                // Otros casos...
                case 7:
                    salir = true;
                    gestorEntradaSalida.imprimirMensajeSeparado("Saliendo..."); // Para salir del bucle
                    break;
    
                default:
                    gestorEntradaSalida.imprimirMensaje("Opción no válida.");
                    break;
            }
        }
    }
    
}
