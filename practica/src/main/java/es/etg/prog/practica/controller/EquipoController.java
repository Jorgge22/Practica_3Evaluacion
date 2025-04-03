package es.etg.prog.practica.controller;

import es.etg.prog.practica.model.excepciones.Excepciones.ArbitrosNoDisponibles;
import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.excepciones.Excepciones.PartidoYaJugadoException;
import es.etg.prog.practica.model.fichero.GestorArchivo;
import es.etg.prog.practica.model.temporada.Arbitro;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.Temporada;
import es.etg.prog.practica.model.temporada.partido.Partido;

public class EquipoController {
    private final GestorArchivo gestorArchivo;

    public EquipoController(GestorArchivo gestorArchivo) {
        this.gestorArchivo = gestorArchivo;
    }

    public void jugarPartido(Equipo local, Equipo visitante, Arbitro arbitro, boolean esOficial) throws PartidoYaJugadoException, ArchivoNoEncontradoException, ArbitrosNoDisponibles {
        Temporada temporada = Temporada.getInstancia();
        try {
            temporada.jugarPartido(local, visitante, arbitro, esOficial);
        } catch (ArchivoNoEncontradoException e) {
            e.printStackTrace();
        }
    }

    public void mostrarResumenPartido(Equipo equipo, Partido partido) throws ArchivoNoEncontradoException {
        gestorArchivo.guardarResumen(equipo, partido);
    }

    public GestorArchivo getGestorArchivo() {
        return gestorArchivo;
    }
}
