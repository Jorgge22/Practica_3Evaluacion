# Proyecto de Gestión de Equipo de Basket

Este proyecto en Java gestiona un equipo de baloncesto, incluyendo jugadores, partidos y árbitros. Permite registrar partidos, controlar el rendimiento de los jugadores y guardar los resúmenes de los partidos en archivos.

## Estructura del Proyecto

- **Equipo**: Representa un equipo con hasta 15 jugadores. Puede agregar jugadores y verificar si ha jugado contra otro equipo.
  
- **Jugador**: Representa a un jugador con nombre, altura, habilidad y métodos para anotar puntos y hacer faltas.

- **Partido**: Representa un partido entre dos equipos. Los resultados se generan aleatoriamente dentro de un rango de puntos (35 a 150), con preferencia por resultados cercanos a 70-100.

- **Arbitro**: Representa al árbitro de un partido. Puede estar enfermo, lo que impide que arbitre un partido.

- **GestorArchivo**: Interfaz para leer equipos desde un archivo y guardar resúmenes de partidos.

## Lógica Utilizada

### 1. **Gestión de Equipos y Jugadores**:
   - Los equipos tienen un **máximo de 15 jugadores**. Se dividen en 5 tipos de posiciones: base, escolta, alero, ala-pívot y pívot, con **3 jugadores por cada tipo**.
   - **Asignación de posiciones**: La posición de cada jugador depende de su **altura** y **habilidad**. Los jugadores más bajos con mayor habilidad ocupan posiciones como base o escolta, mientras que los más altos con menos habilidad juegan como pívot.
   
### 2. **Generación de Resultados de Partidos**:
   - Los partidos pueden ser de **exhibición** o **oficiales**. Los oficiales no se pueden jugar contra equipos con los que ya se haya jugado antes.
   - **Resultados aleatorios**: Los puntos de los partidos se generan de forma aleatoria entre **35 y 150 puntos**, con una mayor probabilidad de obtener resultados cercanos a **70-100 puntos** (valores más comunes en partidos históricos de la ACB).
   - **Equipo local vs visitante**: El equipo local tiene un **porcentaje mayor de ganar** debido a la ventaja de jugar en casa.

### 3. **Árbitros**:
   - Los árbitros pueden estar **enfermos**, lo que impide que sean seleccionados para arbitrar un partido. Esto se gestiona con una propiedad booleana `enfermo` en la clase **Arbitro**.

### 4. **Manejo de Archivos**:
   - Al inicio de la aplicación, los **equipos** se leen desde el archivo `equipos.txt`.
   - Después de cada partido, el resumen de los **resultados** y el **rendimiento de los jugadores** se guarda en el archivo `historicoEquipo.txt` para futuras consultas.
   - La interfaz **GestorArchivo** facilita la lectura y escritura de archivos, separando las operaciones de archivos de la lógica del negocio.

## Flujo de la Aplicación

1. **Lectura de Equipos**: Se leen los equipos desde el archivo `equipos.txt`.
2. **Asignación de Jugadores**: Los jugadores se asignan a sus posiciones según su altura y habilidad.
3. **Selección de Árbitros**: Los árbitros son seleccionados aleatoriamente, teniendo en cuenta si están enfermos.
4. **Generación de Partido**: El partido se genera aleatoriamente con un resultado dentro del rango permitido, y se determina si es exhibición o oficial.
5. **Guardado de Resúmenes**: Después de cada partido, se guarda el resumen de los resultados en `historicoEquipo.txt`.

Elección de Map para la gestión de jugadores por posición

La decisión de usar un Map<String, Integer> para gestionar los jugadores por posición se toma en base a las siguientes razones:

    Eficiencia en la búsqueda: Utilizando un Map, podemos verificar rápidamente cuántos jugadores hay en una posición dada. Los Map permiten búsquedas, inserciones y actualizaciones en tiempo constante (O(1)).

    Claridad y organización: Al usar un Map, la relación entre las posiciones y los jugadores se expresa de forma clara y estructurada. Cada clave en el Map (el tipo de jugador, como "Base", "Escolta", etc.) está asociada a un valor que representa la cantidad de jugadores en esa posición.

    Escalabilidad: Si en el futuro se desea cambiar el número máximo de jugadores por posición o agregar nuevas posiciones, modificar el Map es sencillo y no requiere cambios complejos en el código.

    Flexibilidad: Esta estructura permite un control eficiente sobre el número de jugadores por posición, garantizando que no se pueda exceder el límite preestablecido, lo que facilita la gestión del equipo sin necesidad de recorrer listas enteras para verificar cuántos jugadores hay en cada posición.