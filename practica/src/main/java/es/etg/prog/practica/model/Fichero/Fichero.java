package es.etg.prog.practica.model.fichero;

/**
 * 
 * @author Jorge
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.temporada.Arbitro;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.temporada.Temporada;
import es.etg.prog.practica.model.temporada.jugador.Jugador;
import es.etg.prog.practica.model.temporada.jugador.JugadorFactory;
import es.etg.prog.practica.model.temporada.partido.Partido;
import es.etg.prog.practica.model.temporada.partido.PartidoOficial;
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
        List<Arbitro> arbitrosLeidos = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constantes.RUTA_FICHEROS_ARBITROS))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    arbitrosLeidos.add(new Arbitro(linea.trim())); // Agregar un arbitro sin modificar la lista global
                }
            }
        } catch (IOException e) {
            throw new Excepciones.ArchivoNoEncontradoException();
        }

        return arbitrosLeidos;
    }

    @Override
    public void guardarResumen(Equipo equipo, Partido partido) throws ArchivoNoEncontradoException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTA_FICHEROS_EQUIPOS))) {
            StringBuilder stringBuilder = new StringBuilder();

            //Equipo ganador = partido.getGanador();

            stringBuilder.append(Constantes.MSG_NOMBRE).append(equipo.getNombre()).append(Constantes.MSG_BARRA_N);
            stringBuilder.append(Constantes.MSG_RESULTADO).append(partido.getResultadoLocal()).append(" - ")
                    .append(partido.getResultadoVisitante()).append(Constantes.MSG_BARRA_N);
            stringBuilder.append(Constantes.MSG_ARBITRO).append(partido.getArbitro().getNombre())
                    .append(Constantes.MSG_BARRA_N);
            //stringBuilder.append("Ganador: ").append(ganador.getNombre()).append(Constantes.MSG_BARRA_N);
            stringBuilder.append("Tipo: ").append(partido instanceof PartidoOficial ? "Oficial" : "Exhibición")
                    .append(Constantes.MSG_BARRA_N);

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
            throw new Excepciones.ArchivoNoEncontradoException();
        }
    }

    @Override
    public void guardarResumenUltimoPartido(Equipo equipo, Partido partido) throws ArchivoNoEncontradoException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTA_FICHEROS_RESUMEN_ULTIMO_PARTIDO))) {
            StringBuilder stringBuilder = new StringBuilder();
            Equipo ganador = partido.calcularResultado();

            stringBuilder.append(Constantes.MSG_NOMBRE).append(equipo.getNombre()).append(Constantes.MSG_BARRA_N);
            stringBuilder.append(Constantes.MSG_RESULTADO).append(partido.getEquipoLocal().getNombre()).append(" " + partido.getResultadoLocal()).append(" - ")
                    .append(partido.getResultadoVisitante()).append(" " + partido.getEquipoVisitante().getNombre()).append(Constantes.MSG_BARRA_N);
            stringBuilder.append(Constantes.MSG_ARBITRO).append(partido.getArbitro().getNombre())
                    .append(Constantes.MSG_BARRA_N);
            stringBuilder.append("Ganador: ").append(ganador.getNombre()).append(Constantes.MSG_BARRA_N);
            stringBuilder.append("Tipo: ").append(partido instanceof PartidoOficial ? "Oficial" : "Exhibición")
                    .append(Constantes.MSG_BARRA_N);

            bw.write(stringBuilder.toString());
            bw.write("---\n");
        } catch (IOException e) {
            throw new Excepciones.ArchivoNoEncontradoException();
        }
    }

    @Override
    public String leerUltimoPartido() throws ArchivoNoEncontradoException {
        StringBuilder resumen = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTA_FICHEROS_RESUMEN_ULTIMO_PARTIDO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                resumen.append(linea).append(Constantes.MSG_BARRA_N);
            }
        } catch (IOException e) {
            throw new Excepciones.ArchivoNoEncontradoException();
        }

        return resumen.toString();
    }

    @Override
    public void guardarResumenJugador(Equipo equipo) throws ArchivoNoEncontradoException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTA_FICHEROS_JUGADORES, true))) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(Constantes.MSG_ESTADISTICAS + Constantes.MSG_BARRA_N);
            for (Jugador jugador : equipo.getJugadores()) {
                stringBuilder.append(Constantes.MSG_NOMBRE).append(jugador.getNombre());
                stringBuilder.append(", " + "Tipo: ").append(jugador.getTipo());
                stringBuilder.append(", " + Constantes.MSG_PUNTOS).append(jugador.getPuntos());
                stringBuilder.append(", " + Constantes.MSG_FALTAS).append(jugador.getFaltas());
                stringBuilder.append(Constantes.MSG_BARRA_N);
            }

            bw.write(stringBuilder.toString());
            bw.write("---\n");
        } catch (IOException e) {
            throw new Excepciones.ArchivoNoEncontradoException();
        }
    }

    @Override
    public void guardarHistoricoTemporada() throws ArchivoNoEncontradoException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTA_FICHEROS_TEMPORADA, true))) {
            Temporada temporada = Temporada.getInstancia();
            StringBuilder stringBuilder = new StringBuilder();

            for (Partido partido : temporada.getPartidos()) {
                Equipo ganador = partido.getGanador();

                stringBuilder.append(Constantes.MSG_EQUIPO_LOCAL).append(partido.getEquipoLocal().getNombre())
                        .append(Constantes.MSG_BARRA_N);
                stringBuilder.append(Constantes.MSG_EQUIPO_VISITANTE).append(partido.getEquipoVisitante().getNombre())
                        .append(Constantes.MSG_BARRA_N);
                stringBuilder.append(Constantes.MSG_RESULTADO).append(partido.getResultadoLocal()).append(" - ")
                        .append(partido.getResultadoVisitante()).append(Constantes.MSG_BARRA_N);
                stringBuilder.append(Constantes.MSG_ARBITRO).append(partido.getArbitro().getNombre())
                        .append(Constantes.MSG_BARRA_N);
                stringBuilder.append("Ganador: ").append(ganador.getNombre()).append(Constantes.MSG_BARRA_N);
                stringBuilder.append("Tipo: ").append(partido instanceof PartidoOficial ? "Oficial" : "Exhibición")
                        .append(Constantes.MSG_BARRA_N);
                stringBuilder.append("---\n");
            }

            // Justo antes de escribir en el archivo
            System.out.println("Contenido a escribir: " + stringBuilder.toString());

            bw.write(stringBuilder.toString());
            bw.flush();
            // bw.write("---\n");
        } catch (IOException e) {
            throw new Excepciones.ArchivoNoEncontradoException();
        }
    }

    @Override
    public String leerHistoricoTemporada() throws ArchivoNoEncontradoException {
        StringBuilder resumen = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTA_FICHEROS_TEMPORADA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                resumen.append(linea).append(Constantes.MSG_BARRA_N);
            }
        } catch (IOException e) {
            throw new Excepciones.ArchivoNoEncontradoException();
        }
        return resumen.toString();
    }

    @Override
    public void guardarJugador(List<Jugador> jugadores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constantes.RUTA_FICHEROS_JUGADORES))) {
            for (Jugador jugador : jugadores) {
                String linea = jugador.getNombre() + ", Dorsal: " + jugador.getDorsal() + ", Altura: "
                        + jugador.getAltura() + ", Habilidad: " + jugador.getHabilidad();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los jugadores: " + e.getMessage());
        }
    }

    @Override
    public void eliminarJugador(int dorsal) throws ArchivoNoEncontradoException {
        // Leer todos los jugadores del archivo
        List<Jugador> jugadores = leerJugadores();

        // Buscar el jugador y eliminarlo de la lista
        Jugador jugadorAEliminar = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getDorsal() == dorsal) {
                jugadorAEliminar = jugador;
                break;
            }
        }

        // Si el jugador existe, lo eliminamos
        if (jugadorAEliminar != null) {
            jugadores.remove(jugadorAEliminar);
            // Reescribimos el archivo con la lista de jugadores actualizada
            guardarJugador(jugadores);
            System.out.println("Jugador eliminado correctamente.");
        } else {
            System.out.println("No se encontró el jugador con dorsal: " + dorsal);
        }
    }

    @Override
    public void mostrarJugadores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constantes.RUTA_FICHEROS_JUGADORES))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    @Override
    public List<Jugador> leerJugadores() {
        List<Jugador> jugadores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Constantes.RUTA_FICHEROS_JUGADORES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    // El formato de la línea es ahora diferente, así que dividimos según las comas
                    String[] partes = linea.split(", ");
                    if (partes.length == 4) {
                        try {
                            String nombre = partes[0].trim();
                            int dorsal = Integer.parseInt(partes[1].split(":")[1].trim());
                            int altura = Integer.parseInt(partes[2].split(":")[1].trim());
                            int habilidad = Integer.parseInt(partes[3].split(":")[1].trim());
                            Jugador jugador = JugadorFactory.crearJugador(nombre, dorsal, altura, habilidad);
                            jugadores.add(jugador);
                        } catch (Exception e) {
                            System.out.println("Error al procesar la línea: " + linea + ". " + e.getMessage());
                        }
                    } else {
                        System.out.println("Línea mal formateada: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer los jugadores: " + e.getMessage());
        }

        return jugadores;
    }
}
