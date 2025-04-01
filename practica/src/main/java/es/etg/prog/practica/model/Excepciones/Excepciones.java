package es.etg.prog.practica.model.excepciones;

import es.etg.prog.practica.model.util.Constantes;

public class Excepciones extends Exception {
    public static class ExpulsionJugadorException extends Exception {
        public ExpulsionJugadorException() {
            super(Constantes.MSG_EXCEPCION_EXPULSION);
        }
    }

    public static class MaximoJugadoresException extends Exception {
        public MaximoJugadoresException() {
            super(Constantes.MSG_EXCEPCION_MAXIMO_JUGADORES);
        }
    }

    public static class MaximoJugadoresPosicionException extends Exception{
        public MaximoJugadoresPosicionException(){
            super(Constantes.MSG_EXCEPCION_MAXIMO_JUGADORES_POSICION);
        }
    }

    public static class PartidoYaJugadoException extends Exception {
        public PartidoYaJugadoException() {
            super(Constantes.MSG_EXCEPCION_PARTIDO);
        }
    }

    public static class ArbitrosNoDisponibles extends Exception {
        public ArbitrosNoDisponibles() {
            super(Constantes.MSG_ARBITROS_NO_DISPONIBLES);
        }
    }
    public static class ArchivoNoEncontradoException extends Exception {
        public ArchivoNoEncontradoException() {
            super(Constantes.MSG_ARCHIVO_NO_LEIDO);
        }
    }
}
