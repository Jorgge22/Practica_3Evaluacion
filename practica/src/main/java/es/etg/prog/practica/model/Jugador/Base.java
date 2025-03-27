package es.etg.prog.practica.model.Jugador;

import es.etg.prog.practica.model.Excepciones.Excepciones;
import es.etg.prog.practica.model.Excepciones.Excepciones.ExpulsionJugadorException;

public class Base extends Jugador {

    public Base(String nombre, int dorsal, int altura, int habilidad, int puntos, int faltas) {
        super(nombre, dorsal, altura, habilidad, puntos, faltas);
    }

    @Override
    public void anotarPuntos(int puntos) {
        if (puntos == 1 || puntos == 2 || puntos == 3) {
            this.puntos += puntos;
        }
    }

    @Override
    public void hacerFalta() throws ExpulsionJugadorException{
        this.faltas++;
        if (this.faltas >= 5) {
            throw new Excepciones.ExpulsionJugadorException();
        }
    }

}
