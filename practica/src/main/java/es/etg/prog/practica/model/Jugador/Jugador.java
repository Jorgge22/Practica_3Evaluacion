package es.etg.prog.practica.model.Jugador;

public abstract class Jugador {
    protected String nombre;
    protected int dorsal;
    protected int altura;
    protected int habilidad;

    public Jugador(String nombre, int dorsal, int altura, int habilidad) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.altura = altura;
        this.habilidad = habilidad;
    }

    public abstract void anotarPuntos(int puntos);
    public abstract void hacerFalta();

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
}
