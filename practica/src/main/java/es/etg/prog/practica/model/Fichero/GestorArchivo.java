package es.etg.prog.practica.model.Fichero;

import java.util.List;

import es.etg.prog.practica.model.Equipo;

public interface GestorArchivo {
    public List<Equipo> leerEquipos();
    public void guardarResumen(Equipo equipo, String resumen);
}
