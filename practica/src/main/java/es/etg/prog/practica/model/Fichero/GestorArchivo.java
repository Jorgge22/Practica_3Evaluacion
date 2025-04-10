package es.etg.prog.practica.model.fichero;
/**
 * 
 * @author Jorge
 */
import java.util.List;

import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.temporada.Arbitro;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.Temporada;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.temporada.partido.Partido;

public interface GestorArchivo {
    public List<Equipo> leerEquipos() throws ArchivoNoEncontradoException;
    public List<Arbitro> leerArbitros() throws ArchivoNoEncontradoException;
    public void guardarResumen(Equipo equipo, Partido partido) throws ArchivoNoEncontradoException;
    public void guardarResumenJugador(Equipo equipo) throws ArchivoNoEncontradoException;
    public void guardarHistoricoTemporada(Temporada temporada) throws ArchivoNoEncontradoException;
    public void guardarJugador(Jugador jugador);
    public void guardarJugadores(List<Jugador> jugadors);
    public void mostrarJugadores();
    public List<Jugador> leerJugadores();
}
