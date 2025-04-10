import es.etg.prog.practica.model.excepciones.Excepciones.ArchivoNoEncontradoException;
import es.etg.prog.practica.model.temporada.Arbitro;
import es.etg.prog.practica.model.temporada.Equipo;
import es.etg.prog.practica.model.fichero.Fichero;
import es.etg.prog.practica.model.util.Constantes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FicheroTest {

    private Fichero fichero;

    @BeforeEach
    public void setUp() {
        fichero = new Fichero();
    }

    @Test
    public void testLeerEquipos() throws IOException, ArchivoNoEncontradoException {
        // Preparamos un archivo de prueba
        File testFile = new File(Constantes.RUTA_FICHEROS_EQUIPOS);
        testFile.delete();  // Asegurarnos de que el archivo de prueba está limpio antes de la ejecución

        // Escribimos algunos equipos en el archivo para la prueba
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFile))) {
            bw.write("Equipo A\n");
            bw.write("Equipo B\n");
        }

        // Ahora leemos los equipos
        List<Equipo> equipos = fichero.leerEquipos();

        // Verificamos que los equipos se han leído correctamente
        assertNotNull(equipos);
        assertEquals(2, equipos.size());
        assertEquals("Equipo A", equipos.get(0).getNombre());
        assertEquals("Equipo B", equipos.get(1).getNombre());
    }

    @Test
    public void testLeerArbitros() throws IOException, ArchivoNoEncontradoException {
        // Preparamos un archivo de prueba
        File testFile = new File(Constantes.RUTA_FICHEROS_ARBITROS);
        testFile.delete();  // Asegurarnos de que el archivo de prueba está limpio antes de la ejecución

        // Escribimos algunos árbitros en el archivo para la prueba
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFile))) {
            bw.write("Arbitro A\n");
            bw.write("Arbitro B\n");
        }

        // Ahora leemos los arbitros
        List<Arbitro> arbitros = fichero.leerArbitros();

        // Verificamos que los árbitros se han leído correctamente
        assertNotNull(arbitros);
        assertEquals(2, arbitros.size());
        assertEquals("Arbitro A", arbitros.get(0).getNombre());
        assertEquals("Arbitro B", arbitros.get(1).getNombre());
    }

    /*@Test
    public void testGuardarResumen() throws IOException {
        // Creamos objetos para la prueba
        Equipo equipo = new Equipo("Equipo A");
        Partido partido = new Partido(equipo, equipo);
        partido.setResultadoLocal(80);
        partido.setResultadoVisitante(75);
        Arbitro arbitro = new Arbitro("Arbitro A");
        partido.setArbitro(arbitro);

        // Añadimos un jugador para la estadística
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugador = new Jugador("Jugador 1", 20, 2);
        jugadores.add(jugador);
        equipo.setJugadores(jugadores);

        // Preparamos el archivo de salida
        File file = new File(Constantes.RUTA_FICHEROS_EQUIPOS);
        file.delete();  // Aseguramos que el archivo está limpio antes de escribir

        // Guardamos el resumen
        fichero.guardarResumen(equipo, partido, "");

        // Verificamos que el archivo se ha creado correctamente
        assertTrue(file.exists());

        // Leemos el contenido del archivo para verificar que la escritura fue correcta
        List<String> lines = Files.readAllLines(file.toPath());
        assertTrue(lines.size() > 0);  // Al menos una línea debería estar escrita
        assertTrue(lines.get(0).contains("Equipo A"));
        assertTrue(lines.get(1).contains("80 - 75"));
        assertTrue(lines.get(2).contains("Arbitro A"));
    }*/

    /*@Test
    public void testGuardarResumenJugador() throws IOException {
        // Creamos un equipo con un jugador
        Equipo equipo = new Equipo("Equipo A");
        Jugador[] jugadores = new Jugador[10];
        Jugador jugador = new Jugador("Jugador 1", 20, 2);
        jugadores.add(jugadores);
        equipo.setJugadores(jugadores);

        // Preparamos el archivo de salida
        File file = new File(Constantes.RUTA_FICHEROS_JUGADORES);
        file.delete();  // Aseguramos que el archivo está limpio antes de escribir

        // Guardamos el resumen del jugador
        fichero.guardarResumenJugador(equipo, "");

        // Verificamos que el archivo se ha creado correctamente
        assertTrue(file.exists());

        // Leemos el contenido del archivo para verificar que la escritura fue correcta
        List<String> lines = Files.readAllLines(file.toPath());
        assertTrue(lines.size() > 0);  // Al menos una línea debería estar escrita
        assertTrue(lines.get(0).contains("Jugador 1"));
    }*/

    /*@Test
    public void testGuardarHistoricoTemporada() throws IOException, ArchivoNoEncontradoException {
        // Creamos una temporada con partidos
        Temporada temporada = new Temporada();
        List<Partido> partidos = new ArrayList<>();
        
        Partido partido = new Partido(null, null);
        partido.setResultadoLocal(80);
        partido.setResultadoVisitante(75);
        Arbitro arbitro = new Arbitro("Arbitro 1");
        partido.setArbitro(arbitro);
        Equipo equipoLocal = new Equipo("Equipo A");
        Equipo equipoVisitante = new Equipo("Equipo B");
        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partidos.add(partido);
        
        temporada.setPartidos(partidos);

        // Preparamos el archivo de salida
        File file = new File(Constantes.RUTA_FICHEROS_TEMPORADA);
        file.delete();  // Aseguramos que el archivo está limpio antes de escribir

        // Guardamos el histórico de temporada
        fichero.guardarHistoricoTemporada(temporada, "");

        // Verificamos que el archivo se ha creado correctamente
        assertTrue(file.exists());

        // Leemos el contenido del archivo para verificar que la escritura fue correcta
        List<String> lines = Files.readAllLines(file.toPath());
        assertTrue(lines.size() > 0);  // Al menos una línea debería estar escrita
        assertTrue(lines.get(0).contains("Equipo A"));
        assertTrue(lines.get(1).contains("Equipo B"));
    }*/
}
