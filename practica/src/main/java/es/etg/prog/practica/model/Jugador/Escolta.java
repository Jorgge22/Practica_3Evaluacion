package es.etg.prog.practica.model.Jugador;

public class Escolta extends Jugador {
    public Escolta(String nombre, int dorsal, int altura, int habilidad, int puntos, int faltas) {
        super(nombre, dorsal, altura, habilidad, puntos, faltas);
    }

    @Override
    public void anotarPuntos(int puntos) {
        throw new UnsupportedOperationException("Unimplemented method 'anotarPuntos'");
    }

    @Override
    public void hacerFalta() {
        throw new UnsupportedOperationException("Unimplemented method 'hacerFalta'");
    }
}
