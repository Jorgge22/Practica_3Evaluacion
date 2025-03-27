package es.etg.prog.practica.model.Jugador;

public abstract class Jugador {
    protected String nombre;
    protected int altura;
    protected int habilidad;

    public abstract void anotarPuntos(int puntos);
    public abstract void hacerFalta();
}
