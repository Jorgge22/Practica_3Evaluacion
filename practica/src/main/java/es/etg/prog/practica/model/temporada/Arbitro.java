package es.etg.prog.practica.model.temporada;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.ArbitrosNoDisponibles;

/**
 * 
 * @Author Jorge
 */
public class Arbitro {
    private String nombre;
    private boolean enfermo;
    private List<Arbitro> arbitros;

    public Arbitro(String nombre) {
        this.nombre = nombre;
        this.arbitros = new ArrayList<>();

        Random random = new Random();
        this.enfermo = random.nextBoolean();
    }

    public boolean estaEnfermo() {
        if (enfermo) {
            return true;
        }
        return false;
    }

    public Arbitro elegirArbitro() throws ArbitrosNoDisponibles{
        for (Arbitro arbitro : arbitros) {
            if (!estaEnfermo()) {
                return arbitro;
            }
        }
        throw new Excepciones.ArbitrosNoDisponibles();
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

}
