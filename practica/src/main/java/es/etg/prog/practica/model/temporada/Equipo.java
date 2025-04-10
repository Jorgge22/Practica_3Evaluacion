package es.etg.prog.practica.model.temporada;

import java.util.List;

import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.util.Constantes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Equipo {
    private List<Jugador> jugadores;
    private String nombre;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
    }

    // Método para agregar un nuevo jugador
    public boolean agregarJugador(Jugador jugador) {
        if (jugadores.size() < 15) {
            jugadores.add(jugador);
            return true;
        }
        return false;
    }

    // Método para eliminar un jugador sin usar Iterator
    public boolean eliminarJugador(int dorsal) {
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i); // Obtener jugador por índice
            if (jugador.getDorsal() == dorsal) {
                jugadores.remove(i); // Eliminar el jugador por índice
                actualizarArchivoJugadores(); // Actualizar archivo después de eliminar
                return true; // Si se eliminó con éxito
            }
        }
        return false; // Si no se encontró el jugador con el dorsal
    }

    // Método para actualizar el archivo de jugadores
    private void actualizarArchivoJugadores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constantes.RUTA_FICHEROS_JUGADORES))) {
            for (Jugador jugador : jugadores) {
                writer.write(jugador.getNombre() + "," + jugador.getDorsal() + "," + jugador.getAltura() + "," + jugador.getHabilidad());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public String getNombre() {
        return nombre;
    }
}
