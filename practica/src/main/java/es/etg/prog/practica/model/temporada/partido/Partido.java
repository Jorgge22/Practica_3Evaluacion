package es.etg.prog.practica.model.temporada.partido;

import java.util.Random;

import es.etg.prog.practica.model.temporada.Arbitro;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.excepciones.Excepciones.ArbitrosNoDisponibles;

public class Partido {
    protected final int MAX_PUNTOS = 150;
    protected final int MIN_PUNTOS = 35;

    protected Arbitro arbitro;
    protected int resultadoLocal;
    protected int resultadoVisitante;
    protected Equipo ganador;
    protected Equipo equipoLocal;
    protected Equipo equipoVisitante;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante) throws ArbitrosNoDisponibles {
        this.resultadoLocal = 0;
        this.resultadoVisitante = 0;
        this.ganador = null; // El ganador se asignará después
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.arbitro = Arbitro.elegirArbitro(); 
    }

    /**
     * Método para calcular el resultado del partido de manera aleatoria
     * y determinar el ganador.
     * 
     * @return El equipo ganador.
     */
    public Equipo calcularResultado() {
        Random random = new Random();

        this.resultadoLocal = random.nextInt(MAX_PUNTOS - MIN_PUNTOS + 1) + MIN_PUNTOS;
        this.resultadoVisitante = random.nextInt(MAX_PUNTOS - MIN_PUNTOS + 1) + MIN_PUNTOS;

        if (random.nextDouble() < 0.6) {
            this.resultadoLocal += 5; // El equipo local recibe una bonificación del 5%
        }

        // Determinar el ganador basado en los resultados
        if (resultadoLocal > resultadoVisitante) {
            this.ganador = equipoLocal;
        } else if (resultadoLocal < resultadoVisitante) {
            this.ganador = equipoVisitante;
        } else {
            // Si el resultado es empate, se decide un ganador aleatorio
            this.ganador = random.nextBoolean() ? equipoLocal : equipoVisitante;
        }

        return ganador;
    }

    public int getResultadoLocal() {
        return resultadoLocal;
    }

    public void setResultadoLocal(int resultadoLocal) {
        this.resultadoLocal = resultadoLocal;
    }

    public int getResultadoVisitante() {
        return resultadoVisitante;
    }

    public void setResultadoVisitante(int resultadoVisitante) {
        this.resultadoVisitante = resultadoVisitante;
    }

    public Equipo getGanador() {
        return ganador;
    }

    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
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

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public int getMAX_PUNTOS() {
        return MAX_PUNTOS;
    }

    public int getMIN_PUNTOS() {
        return MIN_PUNTOS;
    }
}
