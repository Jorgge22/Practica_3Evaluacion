# 🏀 Proyecto Java: Simulador de Temporada de Baloncesto

Este proyecto es una aplicación orientada a objetos desarrollada en **Java** que simula una temporada de baloncesto. Utiliza un modelo completo basado en el patrón **MVC** y emplea **Singleton**, **Herencia**, **Polimorfismo** y gestión de archivos para registrar estadísticas de jugadores y equipos.

## 🎯 Objetivo

El objetivo principal del proyecto es permitir la gestión de equipos, simulación de partidos (oficiales y de exhibición), registro de estadísticas y control de una temporada completa de forma estructurada.

---

## 🧱 Estructura del Proyecto

### 📁 Modelo (`Model`)

Contiene las clases lógicas del dominio:

#### 🔹 `Equipo`

- Gestiona los jugadores de un equipo.
- Limita el número máximo por posición (Base, Escolta, etc).
- Permite añadir y eliminar jugadores.
- Actualiza los datos del equipo en los archivos.

#### 🔹 `Jugador` (clase abstracta) y sus subclases

- Define atributos como nombre, tipo, dorsal, habilidad, puntos, faltas.
- Las subclases (`Base`, `Escolta`, etc.) permiten la especialización por posición.

#### 🔹 `Partido` (abstracta)

- Define la lógica para simular un partido.
- Gestiona el arbitraje, puntos y determina el ganador.
- Subclases:
  - `PartidoOficial`: valida que un equipo no juegue más de dos veces contra otro.
  - `PartidoExhibicion`: sin restricciones de partidos jugados.

#### 🔹 `Arbitro`

- Gestiona los árbitros disponibles.
- Simula si un árbitro puede estar enfermo y elige uno aleatorio.

#### 🔹 `Temporada` (Singleton)

- Contiene la lógica principal de control.
- Gestiona los partidos jugados, verifica cuándo termina la temporada y evita duplicados.
- Permite acceder a equipos y obtener información clave como el último partido.

#### 🔹 `GestorArchivo` (interfaz) & `Fichero` (implementación)

- Gestionan la lectura/escritura de datos.
- Permiten guardar jugadores, históricos, partidos y resúmenes.

---

### 📁 Vista (`View`)

- `Main`: punto de entrada del programa. Llama al controlador y muestra menús.

---

### 📁 Controlador (`Controller`)

- `Controller`: gestiona la lógica del programa y las acciones del usuario.
- Comunica la vista con el modelo.
- Controla el menú, acciones como jugar partido, mostrar estadísticas o gestionar jugadores.

---

## ⚙️ Lógica Principal del Proyecto

### 🔸 Jugar un Partido

1. Se seleccionan los equipos y el árbitro.
2. Se decide si es oficial o de exhibición.
3. Se simula el partido con puntuaciones aleatorias y se elige un ganador.
4. Se registran estadísticas del partido y jugadores.
5. Si es oficial, se verifica que no se hayan jugado más de 2 veces entre los mismos equipos.
  
- *Cuando seleccionas las opciones 4 y 5 se muestra por consola el resultado pero si quieres verlo mejor, en la carpeta `misFicheros` encontrarás los .txt `resumenUltimoPartido` en el que se encuentra el resumen del último partido y `temporada` donde se encuentra el resumen de todos los partidos de la temporada*

### 🔸 Gestión de Jugadores

- Cada equipo puede tener un máximo de 15 jugadores.
- Solo se permiten hasta 3 jugadores por posición.
- Se pueden añadir o eliminar jugadores y ver su rendimiento.
- Cuando seleccionas la opción 6 y eliges la posición que quieres ver las estadisticas debes irte al fichero de la carpeta `misFicheros` y en e fichero `resumenJugadores` lo encontrarás.

### 🔸 Archivos y Persistencia

- El sistema guarda:
  - Último partido jugado.
  - Historial de temporada completo.
  - Estadísticas individuales por tipo de jugador.
  - Jugadores de cada equipo.
- Todo esto se guarda con el sistema de archivos de Java y se lee al iniciar.

---

## 🧠 Patrón Singleton en Temporada

La clase `Temporada` utiliza el patrón Singleton para asegurar que solo exista una instancia durante toda la ejecución del programa. Esto permite un control centralizado de la lógica de temporada, partidos y equipos.

```java
private static Temporada instancia;

public static Temporada getInstancia() {
    if (instancia == null) {
        instancia = new Temporada();
    }
    return instancia;
}
````
📊 Diagrama de Clases

El diseño de clases se encuentra en la carpeta `disenyo`.

✅ Funcionalidades Finales

    Simulación de partidos oficiales y de exhibición.

    Gestión completa de equipos y jugadores.

    Registro y visualización de estadísticas de partidos y temporada.

    Sistema de árbitros con posibilidad de estar enfermos.

    Interfaz por consola limpia y sencilla.

📌 Notas

   Te dejo el codigo en github por si lo quieres ver ya que en todo momento he usado git: https://github.com/Jorgge22/Practica_3Evaluacion.git

📬 Autor

Desarrollado por Jorge Barrera García-RIvera, estudiante de 1ºDAW.