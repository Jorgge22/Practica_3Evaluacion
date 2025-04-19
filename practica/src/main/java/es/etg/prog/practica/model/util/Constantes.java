package es.etg.prog.practica.model.util;

/**
 * 
 * @author Jorge
 */
public class Constantes {
    public static final String RUTA_FICHEROS_ARBITROS = "practica\\src\\main\\resources\\misFicheros\\arbitros.txt";
    public static final String RUTA_FICHEROS_PARTIDOS = "practica\\src\\main\\resources\\misFicheros\\partido.txt";
    public static final String RUTA_FICHEROS_RESUMEN = "practica\\src\\main\\resources\\misFicheros\\resumenPartido.txt";
    public static final String RUTA_FICHEROS_EQUIPOS = "practica\\src\\main\\resources\\misFicheros\\equipos.txt";
    public static final String RUTA_FICHEROS_JUGADORES = "practica\\src\\main\\resources\\misFicheros\\jugadores.txt";
    public static final String RUTA_FICHEROS_RESUMEN_JUGADORES = "practica\\src\\main\\resources\\misFicheros\\resumenJugadores.txt";
    public static final String RUTA_FICHEROS_TEMPORADA = "practica\\src\\main\\resources\\misFicheros\\temporada.txt";
    public static final String RUTA_FICHEROS_RESUMEN_ULTIMO_PARTIDO = "practica\\\\src\\\\main\\\\resources\\\\misFicheros\\\\resumenUltimoPartido.txt";
    public static final String RUTA_FICHEROS_RESUMEN_PARTIDO = "practica\\\\src\\\\main\\\\resources\\\\misFicheros\\\\resumenPartido.txt";

    public static final String MSG_EXCEPCION_EXPULSION = "El jugador ha sido expulsado por acumulación de faltas.";
    public static final String MSG_EXCEPCION_MAXIMO_JUGADORES = "No se puede agregar más jugadores. El equipo ya está completo.";
    public static final String MSG_EXCEPCION_MAXIMO_JUGADORES_POSICION = "No se puede agregar más jugadores a esta posición.";
    public static final String MSG_EXCEPCION_PARTIDO = "Este partido ya se ha jugado en la temporada.";
    public static final String MSG_EXCEPCION_JUGADORES = "Error al guardar los jugadores.";
    public static final String MSG_EXCEPCION_LEER_JUGADORES = "Error al leer los jugadores.";
    public static final String MSG_EXCEPCION_JUGADORES_DORSAL = "No se encontró ningún jugador con ese dorsal.";
    public static final String MSG_EXCEPCION_ARCHIVO = "Error al leer el archivo.";
    public static final String MSG_EXCEPCION_LINEA = "Error al leer la línea.";

    public static final String MSG_ARBITROS_NO_DISPONIBLES = "Árbitros no disponibles.";
    public static final String MSG_ARCHIVO_NO_LEIDO = "No se pudo leer el archivo de equipos";
    public static final String MSG_NUMERO_INCORRECTO = "Número incorrecto.";
    public static final String MSG_ESTADISTICAS = "Estadisticas: ";
    public static final String MSG_NOMBRE = "Nombre: ";
    public static final String MSG_PUNTOS = "Puntos: ";
    public static final String MSG_FALTAS = "Faltas: ";
    public static final String MSG_DORSAL = "Dorsal: ";
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
    public static final String MSG_NOMBRE_EQUIPO = "Introduce el nombre del equipo: ";
    public static final String MSG_JUGADORES_NO_CARGADOS = "No hay jugadores cargados.";
    public static final String MSG_ALTURA = "Altura (1-5): ";
    public static final String MSG_HABILIDAD = "Habilidad (1-5): ";
    public static final String MSG_JUGADOR_CREADO = "Jugador creado con exito.";
    public static final String MSG_JUGADOR__NO_CREADO = "No creado, ya hay 3 jugadores en esa posicion.";
    public static final String MSG_JUGADORES_VACIO = "No hay jugadores para eliminar.";
    public static final String MSG_JUGADOR_ELIMINAR = "Seleccione el número del jugador a eliminar: ";
    public static final String MSG_JUGADOR_ELIMINADO = "Jugador eliminado con éxito.";
    public static final String MSG_FUERA_RANGO = "Error. Número fuera de rango.";
    public static final String MSG_EQUIPOS_DISPONIBLES = "Equipos disponibles para enfrentarse:";
    public static final String MSG_NOMBRE_VISITANTE = "Selecciona el número del equipo rival: ";
    public static final String MSG_NOMBRE_ARBITRO = "El árbitro seleccionado es: ";
    public static final String MSG_TIPO_PARTIDO = "El partido es Oficial (O) o de Exibición (E): ";
    public static final String MSG_OPCION_INVALIDA = "Opción no válida.";
    public static final String MSG_FINAL_TEMPORADA = "¡Has jugado contra todos los equipos! La temporada ha terminado.";
    public static final String MSG_OTRO_RIVAL = "! Elige otro rival o tipo Exhibición.";
    public static final String MSG_ERROR_JUGAR = "Error al jugar el partido: ";
    public static final String MSG_PARTIDO_REPETIDO = "Ya se ha jugado 2 veces.";
    public static final String MSG_POSICION = "Qué posición quieres ver: ";
    public static final String MSG_ERROR_JUGADORES = "No hay jugadores del tipo: ";
    public static final String MSG_SALIR = "Saliendo...";
    public static final String MSG_DORSAL_MENU = " (Dorsal: ";

    public static final String MSG_FICHERO_TIPO = "Tipo: ";
    public static final String MSG_FICHERO_GANADOR = "Ganador: ";
    public static final String MSG_FICHERO_PARTIDO_OFICIAL = "Oficial";
    public static final String MSG_FICHERO_PARTIDO_EXHIBICION = "Exhibición";
    public static final String MSG_FICHERO_ALTURA = ", Altura: ";
    public static final String MSG_FICHERO_HABILIDAD = ", Habilidad: ";
    public static final String MSG_FICHERO_DORSAL = ", Dorsal: ";
    public static final String MSG_FICHERO_COMA = ", ";

}
