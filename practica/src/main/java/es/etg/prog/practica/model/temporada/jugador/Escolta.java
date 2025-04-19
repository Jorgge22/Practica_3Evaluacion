package es.etg.prog.practica.model.temporada.jugador;
/**
 * 
 * @author Jorge
 */
import es.etg.prog.practica.model.util.Constantes;

public class Escolta extends Jugador {
    public Escolta(String nombre, int dorsal, int altura, int habilidad) {
        super(nombre, Constantes.ESCOLTA, dorsal, altura, habilidad);
    }


}
