package es.etg.prog.practica.model.temporada.jugador;

public class Escolta extends Jugador {
    public Escolta(String nombre, String tipo, int dorsal, int altura, int habilidad) {
        super(nombre, tipo, dorsal, altura, habilidad);
    }

    @Override
    public String toString() {
        return "Escolta []";
    }
}
