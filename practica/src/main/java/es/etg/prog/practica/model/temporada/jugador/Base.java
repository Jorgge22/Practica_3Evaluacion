package es.etg.prog.practica.model.temporada.jugador;
/**
 * 
 * @Author Jorge 
 */
import es.etg.prog.practica.model.util.Constantes;

public class Base extends Jugador {
    public Base(String nombre, int dorsal, int altura, int habilidad) {
        super(nombre, Constantes.BASE, dorsal, altura, habilidad);
    }
}

