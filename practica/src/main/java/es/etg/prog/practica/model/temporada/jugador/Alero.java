package es.etg.prog.practica.model.temporada.jugador;
/**
 * 
 * @author Jorge
 */
import es.etg.prog.practica.model.util.Constantes;

public class Alero extends Jugador {
    public Alero(String nombre, int dorsal, int altura, int habilidad) {
        super(nombre, Constantes.ALERO, dorsal, altura, habilidad);
    }
}
