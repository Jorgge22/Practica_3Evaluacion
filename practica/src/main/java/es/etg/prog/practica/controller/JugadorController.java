package es.etg.prog.practica.controller;
/**
 * 
 * @author Jorge
 */
import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresException;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresPosicionException;
import es.etg.prog.practica.model.fichero.GestorArchivo;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.jugador.Jugador;

/**
 * 
 * @author Jorge
 */
public class JugadorController {
    private final GestorArchivo gestorArchivo;

    public JugadorController(GestorArchivo gestorArchivo) {
        this.gestorArchivo = gestorArchivo;
    }

    public void anyadirJugador(Equipo equipo, Jugador jugador) throws MaximoJugadoresException, MaximoJugadoresPosicionException {
        equipo.agregarJugador(jugador);
    }

    public void eliminarJugador(Equipo equipo, Jugador jugador) {
        equipo.eliminarJugador(jugador);
    }

    public void mostrarResumenJugador(Equipo equipo) throws ArchivoNoEncontradoException {
        gestorArchivo.guardarResumenJugador(equipo);
    }

    public GestorArchivo getGestorArchivo() {
        return gestorArchivo;
    }

    
}
