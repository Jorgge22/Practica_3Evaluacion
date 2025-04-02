package es.etg.prog.practica.model.fichero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.etg.prog.practica.model.Temporada;
import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.temporada.Arbitro;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.temporada.partido.Partido;
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

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constantes.RUTA_FICHEROS_ARBITROS))) {
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
    public void guardarResumen(Equipo equipo, Partido partido, String resumen) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTA_FICHEROS_EQUIPOS, true))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Constantes.MSG_NOMBRE).append(equipo.getNombre()).append(Constantes.MSG_BARRA_N);
            stringBuilder.append(Constantes.MSG_RESULTADO).append(partido.getResultadoLocal()).append(" - ").append(partido.getResultadoVisitante()).append(Constantes.MSG_BARRA_N);
            stringBuilder.append(Constantes.MSG_ARBITRO).append(partido.getArbitro().getNombre()).append(Constantes.MSG_BARRA_N);
            // stringBuilder.append("Tipo: ").append(partido.getTipo()).append(Constantes.MSG_BARRA_N);

            stringBuilder.append(Constantes.MSG_ESTADISTICAS + Constantes.MSG_BARRA_N);
            for (Jugador jugador : equipo.getJugadores()) {
                stringBuilder.append(Constantes.MSG_NOMBRE).append(jugador.getNombre());
                stringBuilder.append(", " + Constantes.MSG_PUNTOS).append(jugador.getPuntos());
                stringBuilder.append(", " + Constantes.MSG_FALTAS).append(jugador.getFaltas());
                stringBuilder.append(Constantes.MSG_BARRA_N);
            }

            bw.write(stringBuilder.toString());
            bw.write("---\n");
        } catch (IOException e) {
            e.printStackTrace(); // Manejo básico de la excepción
        }
    }

    @Override
    public void guardarResumenJugador(Equipo equipo, String resumen) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTA_FICHEROS_JUGADORES, true))) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(Constantes.MSG_ESTADISTICAS + Constantes.MSG_BARRA_N);
            for (Jugador jugador : equipo.getJugadores()) {
                stringBuilder.append(Constantes.MSG_NOMBRE).append(jugador.getNombre());
                stringBuilder.append(", " + Constantes.MSG_PUNTOS).append(jugador.getPuntos());
                stringBuilder.append(", " + Constantes.MSG_FALTAS).append(jugador.getFaltas());
                stringBuilder.append(Constantes.MSG_BARRA_N);
            }

            resumen = stringBuilder.toString();

            bw.write(resumen);
            bw.write("---\n");
        } catch (Exception e) {

        }
    }

    @Override
    public void guardarHistoricoTemporada(Temporada temporada, String resumen) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTA_FICHEROS_TEMPORADA))) {
            StringBuilder stringBuilder = new StringBuilder();

            for (Partido partido : temporada.getPartidos()) {
                stringBuilder.append(Constantes.MSG_EQUIPO_LOCAL).append(partido.getEquipoLocal().getNombre()).append(Constantes.MSG_BARRA_N);
                stringBuilder.append(Constantes.MSG_EQUIPO_VISITANTE).append(partido.getEquipoVisitante().getNombre()).append(Constantes.MSG_BARRA_N);
                stringBuilder.append(Constantes.MSG_RESULTADO).append(partido.getResultadoLocal()).append(" - ").append(partido.getResultadoVisitante()).append(Constantes.MSG_BARRA_N);
                stringBuilder.append(Constantes.MSG_ARBITRO).append(partido.getArbitro().getNombre()).append(Constantes.MSG_BARRA_N);
                stringBuilder.append("---\n"); 
            }

            resumen = stringBuilder.toString();

            bw.write(resumen);
            bw.write("---\n");
        } catch (Exception e) {
            
        }
    }

}
