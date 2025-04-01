package es.etg.prog.practica.model.fichero;

import java.util.List;

import es.etg.prog.practica.model.temporada.Equipo;

public class Fichero implements GestorArchivo{

    @Override
    public List<Equipo> leerEquipos() {
        throw new UnsupportedOperationException("Unimplemented method 'leerEquipos'");
    }

    @Override
    public void guardarResumen(Equipo equipo, String resumen) {
        throw new UnsupportedOperationException("Unimplemented method 'guardarResumen'");
    }
    
}
