package es.etg.prog.practica.model.temporada.partido;
/**
 * 
 * @Author Jorge
 */
import java.util.List;

import es.etg.prog.practica.model.Temporada;
import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.temporada.Equipo;

public class PartidoOficial extends Partido {

    public PartidoOficial(Equipo equipoLocal, Equipo equipoVisitante, int resultado) {
        super(equipoLocal, equipoVisitante, resultado);
    }

    /**
     * Método que comprueba si el partido oficial ya se ha jugado.
     * 
     * @param rival
     * @throws Excepciones.PartidoYaJugadoException
     */
    public boolean verificarPartido(Equipo rival) throws Excepciones.PartidoYaJugadoException {
        List<Partido> partidosJugados = Temporada.getInstancia().getPartidos();

        for (Partido partido : partidosJugados) {
            if ((partido.getEquipoLocal().equals(this.getEquipoLocal()) && partido.getEquipoVisitante().equals(rival)) ||
                (partido.getEquipoVisitante().equals(this.getEquipoLocal()) && partido.getEquipoLocal().equals(rival))) {
                    return true;
            }
        }
        return false;
    }
}
