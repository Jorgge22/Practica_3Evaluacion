package es.etg.prog.practica.model.fichero;
import java.io.IOException;
/**
 * 
 * @author Jorge
 */
import java.util.List;

import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.excepciones.Excepciones.ErrorArchivoException;
import es.etg.prog.practica.model.excepciones.Excepciones.ErrorJugadorDorsalException;
import es.etg.prog.practica.model.excepciones.Excepciones.ErrorJugadoresException;
import es.etg.prog.practica.model.excepciones.Excepciones.ErrorLeerJugadoresException;
import es.etg.prog.practica.model.excepciones.Excepciones.ErrorLineaException;
import es.etg.prog.practica.model.temporada.Arbitro;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.temporada.partido.Partido;

public interface GestorArchivo {
    public List<Equipo> leerEquipos() throws ArchivoNoEncontradoException;
    public List<Arbitro> leerArbitros() throws ArchivoNoEncontradoException;
    public List<Jugador> leerJugadores() throws ErrorLineaException, IOException, ErrorLeerJugadoresException;
    public String leerUltimoPartido() throws ArchivoNoEncontradoException;
    public String leerHistoricoTemporada() throws ArchivoNoEncontradoException;
    
    public void guardarResumen(Equipo equipo, Partido partido) throws ArchivoNoEncontradoException;
    public void guardarResumenUltimoPartido(Equipo equipo, Partido partido) throws ArchivoNoEncontradoException;
    public void guardarResumenJugador(Equipo equipo, String tipo) throws ArchivoNoEncontradoException;
    public void guardarHistoricoTemporada() throws ArchivoNoEncontradoException;
    public void guardarJugador(List<Jugador> jugadores) throws ErrorJugadoresException;
    public void eliminarJugador(int dorsal) throws ArchivoNoEncontradoException, ErrorJugadoresException, ErrorJugadorDorsalException, ErrorLineaException, IOException, ErrorLeerJugadoresException;
    public void mostrarJugadores() throws ErrorArchivoException;
}
