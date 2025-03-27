package es.etg.prog.practica.model.Jugador;

import es.etg.prog.practica.model.Excepciones.Excepciones.ExpulsionJugadorException;

public abstract class Jugador {
    protected String nombre;
    protected int dorsal;
    protected int altura;
    protected int habilidad;
    protected int puntos;
    protected int faltas;

    public Jugador(String nombre, int dorsal, int altura, int habilidad, int puntos, int faltas) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.altura = altura;
        this.habilidad = habilidad;
        this.puntos = puntos;
        this.faltas = faltas;
    }

    public abstract void anotarPuntos(int puntos);
    public abstract void hacerFalta() throws ExpulsionJugadorException;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(int habilidad) {
        this.habilidad = habilidad;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }
}
