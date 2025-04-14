package es.etg.prog.practica.model.temporada;

import java.util.List;

import es.etg.prog.practica.model.temporada.jugador.AlaPivot;
import es.etg.prog.practica.model.temporada.jugador.Alero;
import es.etg.prog.practica.model.temporada.jugador.Base;
import es.etg.prog.practica.model.temporada.jugador.Escolta;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.temporada.jugador.JugadorFactory;
import es.etg.prog.practica.model.temporada.jugador.Pivot;
import es.etg.prog.practica.model.util.Constantes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Equipo {
    private List<Jugador> jugadores;
    private String nombre;
    int contadorBases;
    int contadorEscolta;
    int contadorAlero;
    int contadorAlaPivot;
    int contadorPivot;

    

    public Equipo(String nombre) {
        this.jugadores = new ArrayList<>();
        this.nombre = nombre;
        this.contadorBases = 0;
        this.contadorEscolta = 0;
        this.contadorAlero = 0;
        this.contadorAlaPivot = 0;
        this.contadorPivot = 0;
    }

    // Método para agregar un nuevo jugador
    public boolean agregarJugador(Jugador jugador) {
        contarJugadoresPosicion();
        if (jugador instanceof Base && contadorBases >= 3) {
            return false;
        } else if (jugador instanceof Escolta && contadorEscolta >= 3) {
            return false;
        } else if (jugador instanceof Alero && contadorAlero >= 3) {
            return false;
        } else if (jugador instanceof AlaPivot && contadorAlaPivot >= 3) {
            return false;
        } else if (jugador instanceof Pivot && contadorPivot >= 3) {
            return false;
        }

        if (jugadores.size() < 15) {
            jugadores.add(jugador);
            return true;
        }
        return false;
    }

    // Método para eliminar un jugador sin usar Iterator
    public boolean eliminarJugador(int dorsal) {
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i); 
            if (jugador.getDorsal() == dorsal) {
                if (jugador instanceof Base && contadorBases >= 3) {
                    contadorBases--;
                } else if (jugador instanceof Escolta) {
                    contadorEscolta--;
                } else if (jugador instanceof Alero) {
                    contadorAlero--;
                } else if (jugador instanceof AlaPivot) {
                    contadorAlaPivot--;
                } else if (jugador instanceof Pivot) {
                    contadorPivot--;
                }

                jugadores.remove(i); 
                actualizarArchivoJugadores(); // Actualizar archivo después de eliminar
                return true; 
            }
        }
        return false; 
    }

    // Método para actualizar el archivo de jugadores
    private void actualizarArchivoJugadores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constantes.RUTA_FICHEROS_JUGADORES))) {
            for (Jugador jugador : jugadores) {
                String linea = jugador.getNombre() + ", Dorsal: " + jugador.getDorsal() + ", Altura: "+ jugador.getAltura() + ", Habilidad: " + jugador.getHabilidad();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void contarJugadoresPosicion(){
        contadorBases = 0;
        contadorEscolta = 0;
        contadorAlero = 0;
        contadorAlaPivot = 0;
        contadorPivot = 0;
        for (Jugador jugador : jugadores) {
            if (jugador instanceof Base) {
                contadorBases++;
            } else if (jugador instanceof Escolta) {
                contadorEscolta++;
            } else if (jugador instanceof Alero) {
                contadorAlero++;
            } else if (jugador instanceof AlaPivot) {
                contadorAlaPivot++;
            } else if (jugador instanceof Pivot) {
                contadorPivot++;
            }
        }
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getContadorBases() {
        return contadorBases;
    }

    public void setContadorBases(int contadorBases) {
        this.contadorBases = contadorBases;
    }

    public int getContadorEscolta() {
        return contadorEscolta;
    }

    public void setContadorEscolta(int contadorEscolta) {
        this.contadorEscolta = contadorEscolta;
    }

    public int getContadorAlero() {
        return contadorAlero;
    }

    public void setContadorAlero(int contadorAlero) {
        this.contadorAlero = contadorAlero;
    }

    public int getContadorAlaPivot() {
        return contadorAlaPivot;
    }

    public void setContadorAlaPivot(int contadorAlaPivot) {
        this.contadorAlaPivot = contadorAlaPivot;
    }

    public int getContadorPivot() {
        return contadorPivot;
    }

    public void setContadorPivot(int contadorPivot) {
        this.contadorPivot = contadorPivot;
    }
}
