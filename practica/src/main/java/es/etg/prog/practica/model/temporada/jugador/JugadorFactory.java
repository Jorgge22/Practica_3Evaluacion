package es.etg.prog.practica.model.temporada.jugador;

public class JugadorFactory {

    public static Jugador crearJugador(String nombre, int dorsal, int altura, int habilidad) {
        switch (altura) {
            case 1:
                return new Base(nombre, dorsal, altura, habilidad);
            case 2:
                return new Escolta(nombre, dorsal, altura, habilidad);
            case 3:
                return new Alero(nombre, dorsal, altura, habilidad);
            case 4:
                return new AlaPivot(nombre, dorsal, altura, habilidad);
            case 5:
                return new Pivot(nombre, dorsal, altura, habilidad);
            default:
                throw new IllegalArgumentException();
        }
    }
}

