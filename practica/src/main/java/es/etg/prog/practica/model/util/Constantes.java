package es.etg.prog.practica.model.util;

/**
 * 
 * @author Jorge
 */
public class Constantes {
    public static final String RUTA_FICHEROS_ARBITROS = "practica\\src\\main\\resources\\misFicheros\\arbitros.txt";
    public static final String RUTA_FICHEROS_PARTIDOS = "practica\\src\\main\\resources\\misFicheros\\partido.txt";
    public static final String RUTA_FICHEROS_EQUIPOS = "practica\\src\\main\\resources\\misFicheros\\equipos.txt";
    public static final String RUTA_FICHEROS_JUGADORES = "practica\\src\\main\\resources\\misFicheros\\jugadores.txt";
    public static final String RUTA_FICHEROS_TEMPORADA = "practica\\src\\main\\resources\\misFicheros\\temporada.txt";
    public static final String RUTA_FICHEROS_RESUMEN_ULTIMO_PARTIDO = "practica\\\\src\\\\main\\\\resources\\\\misFicheros\\\\resumenUltimoPartido.txt";

    public static final String MSG_EXCEPCION_EXPULSION = "El jugador ha sido expulsado por acumulación de faltas.";
    public static final String MSG_EXCEPCION_MAXIMO_JUGADORES = "No se puede agregar más jugadores. El equipo ya está completo.";
    public static final String MSG_EXCEPCION_MAXIMO_JUGADORES_POSICION = "No se puede agregar más jugadores a esta posición.";
    public static final String MSG_EXCEPCION_PARTIDO = "Este partido ya se ha jugado en la temporada.";

    public static final String MSG_ARBITROS_NO_DISPONIBLES = "Árbitros no disponibles.";
    public static final String MSG_ARCHIVO_NO_LEIDO = "No se pudo leer el archivo de equipos";
    public static final String MSG_NUMERO_INCORRECTO = "Número incorrecto.";
    public static final String MSG_ESTADISTICAS = "Estadisticas: ";
    public static final String MSG_NOMBRE = "Nombre: ";
    public static final String MSG_PUNTOS = "Puntos: ";
    public static final String MSG_FALTAS = "Faltas: ";
    public static final String MSG_BARRA_N = "\n";
    public static final String MSG_EQUIPO_LOCAL = "Equipo Local: ";
    public static final String MSG_EQUIPO_VISITANTE = "Equipo Visitante: ";
    public static final String MSG_ARBITRO = "Arbitro: ";
    public static final String MSG_RESULTADO = "Resultado: ";

    public static final String BASE = "Base";
    public static final String ESCOLTA = "Escolta";
    public static final String ALERO = "Alero";
    public static final String ALA_PIVOT = "Ala-Pivot";
    public static final String PIVOT = "Pivot";

    public static final int MAX_JUGADORES_POSICION = 3;
    public static final int MAX_JUGADORES = 15;

    public static final String MSG_MENU = """
            1. AÑADIR JUGADOR
            2. ELIMINAR JUGADOR
            3. JUGAR PARTIDO
            4. MOSTRAR RESUMEN ULTIMO PARTIDO
            5. MOSTRAR HISTORICO DE LA TEMPORADA
            6. MOSTRAR RESUMEN JUGADORES
            7. SALIR
            """;
    public static final String MSG_OPCION = "Elige una opción: ";

}
