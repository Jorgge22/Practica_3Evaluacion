package es.etg.prog.practica.model.temporada;

import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresPosicionException;
import es.etg.prog.practica.model.temporada.jugador.AlaPivot;
import es.etg.prog.practica.model.temporada.jugador.Alero;
import es.etg.prog.practica.model.temporada.jugador.Base;
import es.etg.prog.practica.model.temporada.jugador.Escolta;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.temporada.jugador.Pivot;
import es.etg.prog.practica.model.util.Constantes;

public class Equipo {
    private String nombre;
    private int numJugadores;
    private Jugador[] jugadores;
    
    // Contadores de jugadores por posición
    private int bases;
    private int escoltas;
    private int aleros;
    private int alaPivots;
    private int pivots;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.numJugadores = 0;
        this.jugadores = new Jugador[Constantes.MAX_JUGADORES];
        // Inicializar los contadores de posiciones
        this.bases = 0;
        this.escoltas = 0;
        this.aleros = 0;
        this.alaPivots = 0;
        this.pivots = 0;
    }

    public boolean agregarJugador(Jugador j) throws Excepciones.MaximoJugadoresException, MaximoJugadoresPosicionException {
        if (numJugadores >= 15) {
            throw new Excepciones.MaximoJugadoresException();
        }

        // Asignar posición según altura y habilidad
        if (j.getAltura() <= 2 && j.getHabilidad() >= 2) {
            if (bases >= 3) {
                throw new MaximoJugadoresPosicionException();
            }
            bases++;
            j = new Base(j.getNombre(), j.getTipo(), j.getDorsal(), j.getAltura(), j.getHabilidad());
            j.setTipo("Base");
        } else if (j.getAltura() > 2 && j.getAltura() <= 3 && j.getHabilidad() >= 2) {
            if (escoltas >= 3) {
                throw new MaximoJugadoresPosicionException();
            }
            escoltas++;
            j = new Escolta(j.getNombre(), j.getTipo(), j.getDorsal(), j.getAltura(), j.getHabilidad());
            j.setTipo("Escolta");
        } else if (j.getAltura() > 2 && j.getAltura() <= 3 && j.getHabilidad() <= 3) {
            if (aleros >= 3) {
                throw new MaximoJugadoresPosicionException();
            }
            aleros++;
            j = new Alero(j.getNombre(), j.getTipo(), j.getDorsal(), j.getAltura(), j.getHabilidad());
            j.setTipo("Alero");
        } else if (j.getAltura() > 3 && j.getAltura() <= 4 && j.getHabilidad() <= 2) {
            if (alaPivots >= 3) {
                throw new MaximoJugadoresPosicionException();
            }
            alaPivots++;
            j = new AlaPivot(j.getNombre(), j.getTipo(), j.getDorsal(), j.getAltura(), j.getHabilidad());
            j.setTipo("Ala-Pivot");
        } else if (j.getAltura() > 4 && j.getAltura() <= 5 && j.getHabilidad() <= 2) {
            if (pivots >= 3) {
                throw new MaximoJugadoresPosicionException();
            }
            pivots++;
            j = new Pivot(j.getNombre(), j.getTipo(), j.getDorsal(), j.getAltura(), j.getHabilidad());
            j.setTipo("Pivot");
        }
    
        // Añadir el jugador al equipo
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] == null) {  
                jugadores[i] = j;
                numJugadores++;
                return true; 
            }
        }
    
        return false; 
    }

    public boolean eliminarJugador(Jugador j) {
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] != null && jugadores[i].equals(j)) {
                jugadores[i] = null;
                numJugadores--;
                return true;
            }
        }
        return false;
    }

    private Jugador modificarJugador(Jugador j) {
        if (j.getAltura() > 2) {
            j.setAltura(j.getAltura() - 2);
        } else {
            j.setHabilidad(j.getHabilidad() - 2);
        }
        return j;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }
}
