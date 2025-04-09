package es.etg.prog.practica.model.temporada;

import java.util.*;

import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresPosicionException;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.temporada.jugador.JugadorFactory;
import es.etg.prog.practica.model.util.Constantes;

public class Equipo {
    protected String nombre;
    protected List<Jugador> jugadores; // Lista para almacenar los jugadores
    protected Map<String, Integer> jugadoresPorPosicion; // Mapa para llevar el conteo de jugadores por posición

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
        this.jugadoresPorPosicion = new HashMap<>();
    }

    public void agregarJugador(String nombre, int dorsal, int altura, int habilidad) throws MaximoJugadoresPosicionException {
        Jugador jugador = JugadorFactory.crearJugador(nombre, dorsal, altura, habilidad);

        // Obtener el tipo de posición del jugador
        String tipoPosicion = jugador.getTipo();

        // Comprobar cuántos jugadores hay en esta posición
        int jugadoresEnPosicion = jugadoresPorPosicion.getOrDefault(tipoPosicion, 0);

        System.out.println("Jugadores en la posición " + tipoPosicion + ": " + jugadoresEnPosicion);

        if (jugadoresEnPosicion >= Constantes.MAX_JUGADORES_POSICION) {
            throw new MaximoJugadoresPosicionException();
        }

        jugadores.add(jugador);

        // Actualizar el contador de jugadores por posición
        jugadoresPorPosicion.put(tipoPosicion, jugadoresEnPosicion + 1);
    }

    public Jugador eliminarJugador(String nombreJugador) {
        for (Jugador jugador2 : jugadores) {
            if (nombreJugador != null) {
                if (jugador2.getNombre().equals(nombreJugador)) {
                    // Si encontramos el jugador, eliminamos de la lista
                    jugadores.remove(jugador2);
        
                    // Actualizamos el contador de jugadores en la posición
                    String tipoPosicion = jugador2.getTipo();
                    int jugadoresEnPosicion = jugadoresPorPosicion.getOrDefault(tipoPosicion, 0);
        
                    // Reducimos el contador de jugadores en esa posición
                    if (jugadoresEnPosicion > 0) {
                        jugadoresPorPosicion.put(tipoPosicion, jugadoresEnPosicion - 1);
                    }
        
                    // Devolvemos el jugador eliminado
                    return jugador2;
                }
            }
        }
    
        // Si no se encuentra el jugador, devolver null
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Map<String, Integer> getJugadoresPorPosicion() {
        return jugadoresPorPosicion;
    }

    public void setJugadoresPorPosicion(Map<String, Integer> jugadoresPorPosicion) {
        this.jugadoresPorPosicion = jugadoresPorPosicion;
    }

}
