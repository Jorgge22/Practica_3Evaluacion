package es.etg.prog.practica.controller;

import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.fichero.GestorArchivo;
import es.etg.prog.practica.model.temporada.Temporada;

public class TemporadaController {
    private final GestorArchivo gestorArchivo;

    public TemporadaController(GestorArchivo gestorArchivo) {
        this.gestorArchivo = gestorArchivo;
    }

    public void mostrarResumenHistorico() throws ArchivoNoEncontradoException {
        Temporada temporada = Temporada.getInstancia();
        gestorArchivo.guardarHistoricoTemporada(temporada);
    }

    public GestorArchivo getGestorArchivo() {
        return gestorArchivo;
    }
}
