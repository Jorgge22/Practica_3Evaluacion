package es.etg.prog.practica.model;

import java.util.ArrayList;
import java.util.List;

import es.etg.prog.practica.model.Excepciones.Excepciones;
import es.etg.prog.practica.model.Jugador.AlaPivot;
import es.etg.prog.practica.model.Jugador.Alero;
import es.etg.prog.practica.model.Jugador.Base;
import es.etg.prog.practica.model.Jugador.Escolta;
import es.etg.prog.practica.model.Jugador.Jugador;
import es.etg.prog.practica.model.Jugador.Pivot;
import es.etg.prog.practica.model.util.Constantes;

public class Equipo {
    private String nombre;
    private Jugador[] jugadores;
    private List<Equipo> equiposJugados;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new Jugador[Constantes.MAX_JUGADORES];
        this.equiposJugados = new ArrayList<>();
    }

    public boolean agregarJugador(Jugador j) throws Excepciones.MaximoJugadoresException{
        int bases = 0, escoltas = 0, aleros = 0, alaPivots = 0, pivots = 0;
        for (Jugador jugador : jugadores) {
            if (jugador instanceof Base) {
                bases++;
            } else if (jugador instanceof Escolta) {
                escoltas++;
            } else if (jugador instanceof Alero) {
                aleros++;
            } else if (jugador instanceof AlaPivot) {
                alaPivots++;
            } else if (jugador instanceof Pivot) {
                pivots++;
            }
        }
        if ((j instanceof Base && bases >= 3) || (j instanceof Escolta && escoltas >= 3) || (j instanceof Alero && aleros >= 3) || (j instanceof AlaPivot && alaPivots >= 3) || (j instanceof Pivot && pivots >= 3)) {
            return false;
        }

        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] == null) {
                jugadores[i] = j;
                return true;
            }
        }
        return false;
    }

    public boolean eliminarJugador(Jugador j){
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] != null && jugadores[i].equals(j)) {
                jugadores[i] = null;
                return true;
            }
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public List<Equipo> getEquiposJugados() {
        return equiposJugados;
    }

    public void setEquiposJugados(List<Equipo> equiposJugados) {
        this.equiposJugados = equiposJugados;
    }
}
