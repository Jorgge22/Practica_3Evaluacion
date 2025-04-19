package es.etg.prog.practica.model.excepciones;
/**
 * 
 * @author Jorge
 */
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

    public static class MaximoJugadoresPosicionException extends Exception {
        public MaximoJugadoresPosicionException() {
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

    public static class NumeroIncorrectoException extends Exception {
        public NumeroIncorrectoException() {
            super(Constantes.MSG_NUMERO_INCORRECTO);
        }
    }

    public static class ErrorJugadoresException extends Exception {
        public ErrorJugadoresException() {
            super(Constantes.MSG_EXCEPCION_JUGADORES);
        }
    }

    public static class ErrorJugadorDorsalException extends Exception {
        public ErrorJugadorDorsalException() {
            super(Constantes.MSG_EXCEPCION_JUGADORES_DORSAL);
        }
    }

    public static class ErrorArchivoException extends Exception {
        public ErrorArchivoException() {
            super(Constantes.MSG_EXCEPCION_ARCHIVO);
        }
    }

    public static class ErrorLineaException extends Exception {
        public ErrorLineaException() {
            super(Constantes.MSG_EXCEPCION_LINEA);
        }
    }

    public static class ErrorLeerJugadoresException extends Exception{
        public ErrorLeerJugadoresException(){
            super(Constantes.MSG_EXCEPCION_LEER_JUGADORES);
        }
    }
}
