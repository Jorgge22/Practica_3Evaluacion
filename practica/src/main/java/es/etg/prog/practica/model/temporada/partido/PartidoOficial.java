package es.etg.prog.practica.model.temporada.partido;

import es.etg.prog.practica.model.Temporada;
import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.PartidoYaJugadoException;
import es.etg.prog.practica.model.temporada.Equipo;

public class PartidoOficial extends Partido {

    public PartidoOficial(Equipo equipoLocal, Equipo equipoVisitante) {
        super(equipoLocal, equipoVisitante);
    }

    public boolean verificarPartido() {
        Temporada temporada = Temporada.getInstancia();
        return temporada.verificarPartidoYaJugado(this.equipoLocal, this.equipoVisitante);
    }

    public void jugarPartido() throws PartidoYaJugadoException {
        if (verificarPartido()) {
            throw new Excepciones.PartidoYaJugadoException();
        }

        calcularResultado();

        Temporada.getInstancia().registrarPartidoJugado(this);
    }
}
