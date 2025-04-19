package es.etg.prog.practica.model.temporada.jugador;
/**
 * 
 * @author Jorge
 */
import es.etg.prog.practica.model.util.Constantes;

public class Pivot extends Jugador {
    public Pivot(String nombre, int dorsal, int altura, int habilidad) {
        super(nombre, Constantes.PIVOT, dorsal, altura, habilidad);
    }
}

