package es.etg.prog.practica.model;

import java.util.ArrayList;
import java.util.List;

import es.etg.prog.practica.model.Jugador.Jugador;
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

    public boolean agregarJugador(Jugador j) {
        for (int i = 0; i < jugadores.length; i++) {
            
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
