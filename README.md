# Motor de Videojuego Arcade 2D por Consola

**Temática:** Simulador de combate espacial arcade (*Scrolling Shooter*). El sistema simula una nave espacial protagonista que avanza de manera constante esquivando e impactando contra meteoritos y amenazas en una cuadrícula bidimensional.

---

## Arquitectura del Software

El sistema implementa una arquitectura desacoplada basada en la lógica de estados y la simulación por ticks temporales de un bucle de juego (*Game Loop*). Se divide en las siguientes clases:

* **`Main`:** Punto de entrada del programa. Simula las acciones en tiempo real del usuario enviando eventos controlados y ticks al motor.
* **`EstadoJuego` (Enum):** Controla el flujo general de la aplicación mediante los estados `MENU`, `JUGANDO`, `PAUSA` y `GAME_OVER`.
* **`MotorJuego`:** El cerebro del juego. Almacena las entidades dinámicas en una colección (`ArrayList`), gestiona el ciclo de actualización de estados y procesa las consecuencias de las colisiones. Contiene las de `Jugador` y `Enemigo` mediante clases internas.
* **`EntidadVideojuego` (Clase Abstracta):** Define las propiedades espaciales comunes de los objetos del juego (`x, y, w, h`), su nivel de salud y el método abstracto `actualizar()`. Integra el algoritmo matemático de colisiones.
* **`InputManager`:** Interpola los comandos de entrada simulados (pistas táctiles de dirección) traduciéndolos en modificaciones directas sobre las entidades gobernadas por el motor.

---

## Diagramas UML

### 1. Diagrama de Clases (Mermaid)

```mermaid
classDiagram
    class EstadoJuego {
        <<enumeration>>
        MENU
        JUGANDO
        PAUSA
        GAME_OVER
    }

    class EntidadVideojuego {
        <<abstract>>
        #int x
        #int y
        #int w
        #int h
        #String nombre
        #int vida
        +comprobarColision(otra: EntidadVideojuego) boolean
        +actualizar()* void
        +getNombre() String
        +getX() int
        +setX(x: int) void
        +getY() int
        +setY(y: int) void
        +getVida() int
        +setVida(v: int) void
    }

    class Jugador {
        +actualizar() void
    }

    class Enemigo {
        +actualizar() void
    }

    class MotorJuego {
        -EstadoJuego estadoActual
        -ArrayList~EntidadVideojuego~ entidades
        -EntidadVideojuego jugador
        -int enemigosEliminados
        +cambiarEstado(nuevo: EstadoJuego) void
        -inicializarPartida() void
        +actualizar() void
        +getEstadoActual() EstadoJuego
        +getJugador() EntidadVideojuego
    }

    class InputManager {
        -MotorJuego motor
        +procesarComando(comando: String) void
    }

    class Main {
        +main(args: String[]) void
    }

    EntidadVideojuego <|-- Jugador
    EntidadVideojuego <|-- Enemigo
    MotorJuego "1" *-- "0..*" EntidadVideojuego : almacena >
    MotorJuego "1" *-- "1" EstadoJuego : controla >
    InputManager --> MotorJuego : modifica >
    Main --> MotorJuego : inicializa >
    Main --> InputManager : interactúa >