package es.etg.prog.practica.model.temporada.jugador;

import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresPosicionException;

public class JugadorFactory {
    public static Jugador crearJugador(String nombre, int dorsal, int altura, int habilidad) throws MaximoJugadoresPosicionException {
        if (altura <= 2 && habilidad >= 2) {
            return new Base(nombre, "Base", dorsal, altura, habilidad);
        } else if (altura > 2 && altura <= 3 && habilidad >= 2) {
            return new Escolta(nombre, "Escolta", dorsal, altura, habilidad);
        } else if (altura > 2 && altura <= 3 && habilidad <= 3) {
            return new Alero(nombre, "Alero", dorsal, altura, habilidad);
        } else if (altura > 3 && altura <= 4 && habilidad <= 2) {
            return new AlaPivot(nombre, "Ala-Pivot", dorsal, altura, habilidad);
        } else if (altura > 4 && altura <= 5 && habilidad <= 2) {
            return new Pivot(nombre, "Pivot", dorsal, altura, habilidad);
        } else {
            throw new MaximoJugadoresPosicionException(); // o puedes crear una excepción personalizada si lo prefieres
        }
    }
}
