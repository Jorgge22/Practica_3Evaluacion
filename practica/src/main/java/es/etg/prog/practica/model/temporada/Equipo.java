package es.etg.prog.practica.model.temporada;

import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresException;
import es.etg.prog.practica.model.excepciones.Excepciones.MaximoJugadoresPosicionException;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.util.Constantes;

public class Equipo {
    private String nombre;
    private int numJugadores;
    private Jugador[] jugadores;
    
    private int bases;
    private int escoltas;
    private int aleros;
    private int alaPivots;
    private int pivots;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.numJugadores = 0;
        this.jugadores = new Jugador[Constantes.MAX_JUGADORES];
        this.bases = 0;
        this.escoltas = 0;
        this.aleros = 0;
        this.alaPivots = 0;
        this.pivots = 0;
    }


    public boolean agregarJugador(Jugador j) throws MaximoJugadoresException, MaximoJugadoresPosicionException {
        if (numJugadores >= Constantes.MAX_JUGADORES) {
            throw new MaximoJugadoresException();
        }

        String tipo = j.getTipo();

        switch (tipo) {
            case Constantes.BASE:
                if (bases >= 3) {
                    modificarJugador(j);
                    throw new MaximoJugadoresPosicionException();
                }
                bases++;
                break;
            case Constantes.ESCOLTA:
                if (escoltas >= 3) {
                    modificarJugador(j);
                    throw new MaximoJugadoresPosicionException();
                }
                escoltas++;
                break;
            case Constantes.ALERO:
                if (aleros >= 3) {
                    modificarJugador(j);
                    throw new MaximoJugadoresPosicionException();
                }
                aleros++;
                break;
            case Constantes.ALA_PIVOT:
                if (alaPivots >= 3) {

                    throw new MaximoJugadoresPosicionException();
                }
                alaPivots++;
                break;
            case Constantes.PIVOT:
                if (pivots >= 3) {
                    modificarJugador(j);
                    throw new MaximoJugadoresPosicionException();
                }
                pivots++;
                break;
            default:
                break;
        }

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

                String tipo = j.getTipo();

                switch (tipo) {
                    case Constantes.BASE:
                        bases--;
                        break;
                    case Constantes.ESCOLTA:
                        escoltas--;
                        break;
                    case Constantes.ALERO:
                        aleros--;
                        break;
                    case Constantes.ALA_PIVOT:
                        alaPivots--;
                        break;
                    case Constantes.PIVOT:
                        pivots--;
                        break;
                    default:
                        break;
                }

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
