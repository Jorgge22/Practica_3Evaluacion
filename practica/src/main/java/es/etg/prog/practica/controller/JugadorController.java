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

public class JugadorController {
    private final GestorArchivo gestorArchivo;

    public JugadorController(GestorArchivo gestorArchivo) {
        this.gestorArchivo = gestorArchivo;
    }

    public void anyadirJugador(Equipo equipo, String nombre, int dorsal, int altura, int habilidad) throws MaximoJugadoresException, MaximoJugadoresPosicionException {
        equipo.agregarJugador(nombre, dorsal, altura, habilidad);
    }

    public void eliminarJugador(Equipo equipo, String nombreJuagdor) {
        equipo.eliminarJugador(nombreJuagdor);
    }

    public void mostrarResumenJugador(Equipo equipo) throws ArchivoNoEncontradoException {
        gestorArchivo.guardarResumenJugador(equipo);
    }

    public GestorArchivo getGestorArchivo() {
        return gestorArchivo;
    }

    
}
