package es.etg.prog.practica.model;

/**
 * 
 * @Author Jorge
 */
import java.util.ArrayList;
import java.util.List;

import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.partido.Partido;

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

    // Obtener la lista de partidos jugados
    public List<Partido> getPartidosJugados() {
        return partidos;
    }

    // Método para registrar un partido jugado
    public void registrarPartidoJugado(Partido partido) {
        partidos.add(partido);
    }

    public void jugarPartido(Equipo equipo1, Equipo equipo2, boolean esOficial) {

    }

    public void mostrarResumenUltimoPartido() {

    }

    public void mostrarHistoricoTemporada() {

    }

    public void mostrarResumenJugadores(String tipo) {

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