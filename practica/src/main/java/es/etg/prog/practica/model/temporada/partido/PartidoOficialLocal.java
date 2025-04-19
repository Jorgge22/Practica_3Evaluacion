package es.etg.prog.practica.model.temporada.partido;
/**
 * 
 * @author Jorge
 */
import es.etg.prog.practica.model.excepciones.Excepciones.ArbitrosNoDisponibles;
import es.etg.prog.practica.model.temporada.Equipo;

public class PartidoOficialLocal extends PartidoOficial {

    public PartidoOficialLocal(Equipo equipoLocal, Equipo equipoVisitante) throws ArbitrosNoDisponibles {
        super(equipoLocal, equipoVisitante);
    }

}
