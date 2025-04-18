package es.etg.prog.practica.model.temporada;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.etg.prog.practica.model.excepciones.Excepciones.ArbitrosNoDisponibles;

/**
 * 
 * @author Jorge
 */
public class Arbitro {
    private String nombre;
    private boolean enfermo;
    private static List<Arbitro> arbitros = new ArrayList<>();

    public Arbitro(String nombre) {
        this.nombre = nombre;

        Random random = new Random();
        this.enfermo = random.nextInt(5) == 0; 
        arbitros.add(this);
    }

    /**
     * Método para verificar si el árbitro está enfermo.
     * 
     * @return true si está enfermo, false en caso contrario
     */
    public boolean estaEnfermo() {
        return enfermo;
    }

    /**
     * Método para elegir un árbitro disponible (no enfermo).
     * 
     * @throws ArbitrosNoDisponibles si no hay árbitros disponibles
     * @return un árbitro que no esté enfermo
     */
    public static Arbitro elegirArbitro() throws ArbitrosNoDisponibles {
        List<Arbitro> arbitrosDisponibles = new ArrayList<>();
        for (Arbitro arbitro : arbitros) {
            if (!arbitro.estaEnfermo()) {
                arbitrosDisponibles.add(arbitro);
            }
        }

        if (arbitrosDisponibles.isEmpty()) {
            throw new ArbitrosNoDisponibles(); 
        }

        Random random = new Random();
        int indice = random.nextInt(arbitrosDisponibles.size());
        return arbitrosDisponibles.get(indice);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEnfermo() {
        return enfermo;
    }

    public void setEnfermo(boolean enfermo) {
        this.enfermo = enfermo;
    }

    public static List<Arbitro> getArbitros() {
        return arbitros;
    }
}
