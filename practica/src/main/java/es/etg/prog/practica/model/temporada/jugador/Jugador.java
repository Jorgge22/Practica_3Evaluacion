package es.etg.prog.practica.model.temporada.jugador;

import es.etg.prog.practica.model.excepciones.Excepciones;
import es.etg.prog.practica.model.excepciones.Excepciones.ExpulsionJugadorException;

public abstract class Jugador implements Comparable<Jugador> {
    protected String nombre;
    protected int dorsal;
    protected int altura;
    protected int habilidad;
    protected int puntos;
    protected int faltas;

    public Jugador(String nombre, int dorsal, int altura, int habilidad) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.altura = altura;
        this.habilidad = habilidad;
        this.puntos = 0;
        this.faltas = 0;
    }

    public void anotarPuntos(int puntos) {
        if (puntos == 1 || puntos == 2 || puntos == 3) {
            this.puntos += puntos;
        }
    }

    public void hacerFalta() throws ExpulsionJugadorException {
        this.faltas++;
        if (this.faltas >= 5) {
            throw new Excepciones.ExpulsionJugadorException();
        }
    }

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

    @Override
    public int compareTo(Jugador otro) {
        return Integer.compare(this.habilidad, otro.habilidad);
    }

    @Override
    public String toString() {
        return "Jugador [nombre=" + nombre + ", dorsal=" + dorsal + ", altura=" + altura + ", habilidad=" + habilidad
                + ", puntos=" + puntos + ", faltas=" + faltas + "]";
    }

}
