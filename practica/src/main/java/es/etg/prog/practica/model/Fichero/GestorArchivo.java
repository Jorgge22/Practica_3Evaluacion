package es.etg.prog.practica.model.fichero;

import java.util.List;

import es.etg.prog.practica.model.Temporada;
import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.temporada.Arbitro;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.partido.Partido;

public interface GestorArchivo {
    public List<Equipo> leerEquipos() throws ArchivoNoEncontradoException;
    public List<Arbitro> leerArbitros() throws ArchivoNoEncontradoException;
    public void guardarResumen(Equipo equipo, Partido partido, String resumen);
    public void guardarResumenJugador(Equipo equipo, String resumen);
    public void guardarHistoricoTemporada(Temporada temporada, String resumen);
}
