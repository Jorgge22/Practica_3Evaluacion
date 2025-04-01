package es.etg.prog.practica.model.temporada;
/*
 * 
 * @Author Jorge
 */
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

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.numJugadores = 0;
        this.jugadores = new Jugador[Constantes.MAX_JUGADORES];
    }

    public boolean agregarJugador(Jugador j) throws Excepciones.MaximoJugadoresException, MaximoJugadoresPosicionException {
        int bases = 0, escoltas = 0, aleros = 0, alaPivots = 0, pivots = 0;
        if (numJugadores > 15) {
            throw new Excepciones.MaximoJugadoresException();
        }

        if (j.getAltura() <= 2 && j.getHabilidad() >= 2) {
            if (bases > 3) {
                j = modificarJugador(j);
                throw new Excepciones.MaximoJugadoresPosicionException();
            }
            j = new Base(j.getNombre(), j.getDorsal(), j.getAltura(), j.getHabilidad());

        } else if (j.getAltura() > 1 && j.getAltura() <= 3 && j.getHabilidad() >= 2) {
            if (escoltas > 3) {
                j = modificarJugador(j);
                throw new Excepciones.MaximoJugadoresPosicionException();
            }
            j = new Escolta(j.getNombre(), j.getDorsal(), j.getAltura(), j.getHabilidad());

        } else if (j.getAltura() > 2 && j.getAltura() <= 3 && j.getHabilidad() <= 3) {
            if (aleros > 3) {
                j = modificarJugador(j);
                throw new Excepciones.MaximoJugadoresPosicionException();
            }
            j = new Alero(j.getNombre(), j.getDorsal(), j.getAltura(), j.getHabilidad());

        } else if (j.getAltura() > 3 && j.getAltura() <= 4 && j.getHabilidad() <= 2) {
            if (alaPivots > 3) {
                j = modificarJugador(j);
                throw new Excepciones.MaximoJugadoresPosicionException();
            }
            j = new AlaPivot(j.getNombre(), j.getDorsal(), j.getAltura(), j.getHabilidad());

        } else if (j.getAltura() > 4 && j.getAltura() <= 5 && j.getHabilidad() <= 2) {
            if (pivots > 3) {
                j = modificarJugador(j);
                throw new Excepciones.MaximoJugadoresPosicionException();
            }
            j = new Pivot(j.getNombre(), j.getDorsal(), j.getAltura(), j.getHabilidad());

        }

        // Añadir el jugador al equipo
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores == null) {
                jugadores[numJugadores] = j;
                numJugadores++;
            }
        }

        return true;
    }

    public boolean eliminarJugador(Jugador j) {
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i] != null && jugadores[i].equals(j)) {
                jugadores[i] = null;
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
