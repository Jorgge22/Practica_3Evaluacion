package es.etg.prog.practica.model.temporada.jugador;

public class Escolta extends Jugador {
    public Escolta(String nombre, int dorsal, int altura, int habilidad) {
        super(nombre, dorsal, altura, habilidad);
    }

    @Override
    public String toString() {
        return "Escolta []";
    }
}
