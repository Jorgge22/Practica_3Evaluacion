package es.etg.prog.practica.model.fichero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.temporada.Arbitro;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.util.Constantes;

public class Fichero implements GestorArchivo {

    @Override
    public List<Equipo> leerEquipos() throws ArchivoNoEncontradoException {
        List<Equipo> equipos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTA_FICHEROS_EQUIPOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    equipos.add(new Equipo(linea.trim()));
                }
            }
        } catch (IOException e) {
            throw new Excepciones.ArchivoNoEncontradoException();
        }

        return equipos;
    }

    @Override
    public List<Arbitro> leerArbitros() throws ArchivoNoEncontradoException {
        List<Arbitro> arbitros = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constantes.RUTA_FICHEROS_ARBITROS))){
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                arbitros.add(new Arbitro(linea.trim()));
            }
        } catch (IOException e) {
            throw new Excepciones.ArchivoNoEncontradoException();
        }

        return arbitros;
    }

    @Override
    public void guardarResumen(Equipo equipo, String resumen) {
        throw new UnsupportedOperationException("Unimplemented method 'guardarResumen'");
    }

    @Override
    public void guardarResumenJugador(Jugador jugador, String resumen) {
        throw new UnsupportedOperationException("Unimplemented method 'guardarResumenJugador'");
    }

    @Override
    public void guardarHistoricoTemporada(String resumen) {
        throw new UnsupportedOperationException("Unimplemented method 'guardarHistoricoTemporada'");
    }

}
