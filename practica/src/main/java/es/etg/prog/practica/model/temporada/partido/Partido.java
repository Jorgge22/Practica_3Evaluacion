package es.etg.prog.practica.model.temporada.partido;

/**
 * 
 * @author Jorge
 */
import java.util.List;
import java.util.Random;

import es.etg.prog.practica.model.temporada.Arbitro;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
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

        // 1) Generar puntuación "base" para visitante y local
        int puntuacionVisitante = generarPuntuacion(random);
        int puntuacionLocal = generarPuntuacion(random);

        // 2) Ventaja de local: +0–5 puntos con un 55% de probabilidad
        if (random.nextDouble() < 0.55) {
            puntuacionLocal += random.nextInt(6);
        }

        // 3) Clamp a los límites 35–150
        puntuacionLocal = Math.min(150, Math.max(35, puntuacionLocal));
        puntuacionVisitante = Math.min(150, Math.max(35, puntuacionVisitante));

        this.resultadoLocal = puntuacionLocal;
        this.resultadoVisitante = puntuacionVisitante;

        // 4) Repartir puntos entre los jugadores del equipo local
        List<Jugador> lista = equipoLocal.getJugadores();
        int restante = puntuacionLocal;
        for (int i = 0; i < lista.size(); i++) {
            Jugador j = lista.get(i);
            int asignados;
            if (i < lista.size() - 1) {
                // media de lo que queda, con ±2 de variación
                int media = restante / (lista.size() - i);
                asignados = Math.max(0, media + random.nextInt(5) - 2);
            } else {
                // al último, le damos todo lo que quede
                asignados = restante;
            }
            j.setPuntos(asignados);
            j.setFaltas(random.nextInt(5)); // faltas 0–4
            restante -= asignados;
        }

        // 5) Determinar ganador
        if (resultadoLocal > resultadoVisitante) {
            ganador = equipoLocal;
        } else if (resultadoVisitante > resultadoLocal) {
            ganador = equipoVisitante;
        } else {
            ganador = random.nextBoolean() ? equipoLocal : equipoVisitante;
        }

        return ganador;
    }

    /**
     * Genera un total sesgado hacia valores "comunes" (60–90),
     * pero permitiendo extremos dentro de 35–150.
     */
    private int generarPuntuacion(Random random) {
        double p = random.nextDouble();
        if (p < 0.10) {
            // 10% de probabilidad para muy bajo: 35–59
            return random.nextInt(25) + 35;
        } else if (p < 0.80) {
            // 70% para rango común: 60–90
            return random.nextInt(31) + 60;
        } else if (p < 0.95) {
            // 15% para alto moderado: 91–110
            return random.nextInt(20) + 91;
        } else {
            // 5% para extremos altos: 111–150
            return random.nextInt(40) + 111;
        }
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
