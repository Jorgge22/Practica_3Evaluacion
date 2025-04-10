package es.etg.prog.practica.model.temporada;

import java.util.*;

import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresPosicionException;
import es.etg.prog.practica.model.excepciones.Excepciones.NumeroIncorrectoException;
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

    public void agregarJugador(String nombre, int dorsal, int altura, int habilidad)
            throws MaximoJugadoresPosicionException {
        Jugador jugador = JugadorFactory.crearJugador(nombre, dorsal, altura, habilidad);

        String tipoPosicion = jugador.getTipo();
        int jugadoresEnPosicion = jugadoresPorPosicion.getOrDefault(tipoPosicion, 0);

        System.out.println("Jugadores en la posición " + tipoPosicion + ": " + jugadoresEnPosicion);

        if (jugadoresEnPosicion >= Constantes.MAX_JUGADORES_POSICION) {
            throw new MaximoJugadoresPosicionException();
        }

        jugadores.add(jugador);
        jugadoresPorPosicion.put(tipoPosicion, jugadoresEnPosicion + 1);

        System.out.println("Jugador agregado con éxito.");
    }

    public Jugador eliminarJugador(int numeroJugador) throws NumeroIncorrectoException {
        // Comprobar si la lista de jugadores está vacía
        if (jugadores.isEmpty()) {
            throw new Excepciones.NumeroIncorrectoException();
        }
    
        // Verificar si el número de jugador es válido
        if (numeroJugador < 0 || numeroJugador >= jugadores.size()) {
            throw new Excepciones.NumeroIncorrectoException(); 
        }
    
        // Eliminar al jugador usando el índice ajustado
        Jugador jugadorEliminado = jugadores.get(numeroJugador); // El índice ya se ajusta en el controlador
    
        // Eliminar al jugador de la lista
        jugadores.remove(jugadorEliminado);
    
        // Actualizar los jugadores por posición
        String tipoPosicion = jugadorEliminado.getTipo();
        int jugadoresEnPosicion = jugadoresPorPosicion.getOrDefault(tipoPosicion, 0);
        if (jugadoresEnPosicion > 0) {
            jugadoresPorPosicion.put(tipoPosicion, jugadoresEnPosicion - 1); // Reducir el contador de jugadores en esa posición
        }
    
        return jugadorEliminado;
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
