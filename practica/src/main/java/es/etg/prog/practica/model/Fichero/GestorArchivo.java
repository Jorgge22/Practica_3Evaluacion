package es.etg.prog.practica.model.fichero;

import java.util.List;

import es.etg.prog.practica.model.temporada.Equipo;

public interface GestorArchivo {
    public List<Equipo> leerEquipos();
    public void guardarResumen(Equipo equipo, String resumen);
}
