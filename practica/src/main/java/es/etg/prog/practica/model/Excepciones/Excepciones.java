package es.etg.prog.practica.model.Excepciones;

public class Excepciones extends Exception {
    public static class ExpulsionJugadorException extends Exception {
        public ExpulsionJugadorException() {
            super("El jugador ha sido expulsado por acumulación de faltas.");
        }
    }

    public static class MaximoJugadoresException extends Exception {
        public MaximoJugadoresException() {
            super("No se puede agregar más jugadores. El equipo ya está completo.");
        }
    }

    public static class PartidoYaJugadoException extends Exception {
        public PartidoYaJugadoException() {
            super("Este partido ya se ha jugado en la temporada.");
        }
    }
}
