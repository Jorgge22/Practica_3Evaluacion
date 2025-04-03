package es.etg.prog.practica.model.temporada;

/**
 * 
 * @author Jorge
 */
import java.util.ArrayList;
import java.util.List;

import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.fichero.Fichero;
import es.etg.prog.practica.model.fichero.GestorArchivo;
import es.etg.prog.practica.model.temporada.partido.Partido;
import es.etg.prog.practica.model.temporada.partido.PartidoExibicion;
import es.etg.prog.practica.model.temporada.partido.PartidoOficial;

public class Temporada {
    private static Temporada instancia;
    private List<Partido> partidos;
    private List<Equipo> equipos;

    public Temporada() {
        partidos = new ArrayList<>();
        equipos = new ArrayList<>();
    }

    public static Temporada getInstancia() {
        if (instancia == null) {
            instancia = new Temporada();
        }
        return instancia;
    }

    // Método para registrar un partido jugado
    public void registrarPartidoJugado(Partido partido) {
        partidos.add(partido);
    }

    /**
     * Método que simula la jugada de un partido entre dos equipos.
     * Dependiendo de si el partido es oficial o no, se realiza la comprobación de
     * si ya se ha jugado el partido
     * en la temporada. Si el partido es oficial y ya se jugó, se lanza una
     * excepción. Si no es oficial, no se realiza
     * ninguna comprobación y el partido se juega de inmediato.
     * 
     * @param equipoLocal     El equipo que juega como local en el partido.
     * @param equipoVisitante El equipo que juega como visitante en el partido.
     * @param esOficial       Booleano que indica si el partido es oficial o no. Si
     *                        es oficial, se realiza la verificación de si el
     *                        partido ya fue jugado.
     * @throws Excepciones.PartidoYaJugadoException Si el partido es oficial y ya se
     *                                              ha jugado previamente, se lanza
     *                                              esta excepción.
     * @throws ArchivoNoEncontradoException 
     */
    public void jugarPartido(Equipo local, Equipo visitante, Arbitro arbitro, boolean esOficial) throws ArchivoNoEncontradoException {
        Partido partido;
        
        if (esOficial) {
            partido = new PartidoOficial(local, visitante);
        } else {
            partido = new PartidoExibicion(local, visitante);
        }

        // Calcular el resultado del partido
        Equipo ganador = partido.calcularResultado();

        // Crear el resumen del partido
        StringBuilder resumen = new StringBuilder();
        resumen.append("Resultado del partido: " + local.getNombre() + " " + partido.getResultadoLocal() + " - " + partido.getResultadoVisitante() + " " + visitante.getNombre() + "\n");
        resumen.append("Árbitro: " + arbitro.getNombre() + "\n");
        resumen.append("El ganador es: " + ganador.getNombre() + "\n");

        // Guardar el resumen en un archivo
        GestorArchivo gestorArchivo = new Fichero();
        gestorArchivo.guardarResumen(ganador, partido);
    }

    /**
     * Método que verifica si un partido oficial entre el equipo actual y un equipo
     * rival ya ha sido jugado en la temporada.
     * 
     * Se revisa la lista de partidos jugados en la temporada para comprobar si
     * el equipo actual ya se ha enfrentado al equipo rival como local o visitante.
     * 
     * @param rival El equipo contrario que se quiere verificar si ya ha jugado
     *              contra el equipo actual.
     * 
     * @return true si el partido ya se ha jugado, false en caso contrario.
     * @throws Excepciones.PartidoYaJugadoException Si el partido ya ha sido jugado
     *                                              previamente.
     */

    public boolean verificarPartidoYaJugado(Equipo equipo1, Equipo equipo2) {
        for (Partido partido : partidos) {
            if ((partido.getEquipoLocal().equals(equipo1) && partido.getEquipoVisitante().equals(equipo2)) ||
                    (partido.getEquipoVisitante().equals(equipo1) && partido.getEquipoLocal().equals(equipo2))) {
                return true;
            }
        }
        return false;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

}