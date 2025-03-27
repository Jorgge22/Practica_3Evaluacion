package es.etg.prog.practica.model;

import java.util.ArrayList;
import java.util.List;

import es.etg.prog.practica.model.Partido.Partido;

public class Temporada {
    private static Temporada instancia;
    private List<Partido> partidos;
    private List<Equipo> equipos;

    private Temporada() {
        partidos = new ArrayList<>();
        equipos = new ArrayList<>();
    }

    public static Temporada getInstancia() {
        if (instancia == null) {
            instancia = new Temporada();
        }
        return instancia;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

}