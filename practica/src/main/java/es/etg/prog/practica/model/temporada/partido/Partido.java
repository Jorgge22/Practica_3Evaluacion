package es.etg.prog.practica.model.temporada.partido;

import java.util.Random;

import es.etg.prog.practica.model.temporada.Equipo;

public class Partido {
    final int MAX_PUNTOS = 150;
    final int MIN_PUNTOS = 35; 
    protected int resultado;
    protected Equipo equipoLocal;
    protected Equipo equipoVisitante;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante, int resultado) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.resultado = resultado;
    }

    // Método para calcular el resultado
    public int calcularResultado() {
        Random rand = new Random();
        return rand.nextInt(116) + 35;  // Esto genera un número aleatorio entre 35 y 150
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getMAX_PUNTOS() {
        return MAX_PUNTOS;
    }

    public int getMIN_PUNTOS() {
        return MIN_PUNTOS;
    }
}
