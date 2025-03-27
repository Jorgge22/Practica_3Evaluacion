package es.etg.prog.practica.model;

public class Arbitro {
    private String nombre;
    private boolean enfermo;

    public Arbitro(String nombre, boolean enfermo) {
        this.nombre = nombre;
        this.enfermo = enfermo;
    }

    public boolean estaEnfermo(){
        if (enfermo) {
            return true;
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEnfermo() {
        return enfermo;
    }

    public void setEnfermo(boolean enfermo) {
        this.enfermo = enfermo;
    }

}
