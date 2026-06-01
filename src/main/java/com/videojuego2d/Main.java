package com.videojuego2d;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== BIENVENIDO AL MOTOR 2D ===");
        
        // 1. Inicializamos el cerebro del juego y el gestor de comandos
        MotorJuego motor = new MotorJuego();
        InputManager input = new InputManager(motor);

        // 2. Simulación: El jugador inicia la partida desde el menú
        input.procesarComando("INICIAR");
        
        // 3. Simulación del ciclo de juego (Game Loop por consola)
        // Primer tick: Las entidades se posicionan y se mueven
        motor.actualizar(); 
        
        // Segundo tick: El jugador pulsa el botón para moverse a la derecha
        input.procesarComando("DERECHA"); 
        motor.actualizar(); // Esto provocará la colisión con el meteorito automáticamente
        
        // Tercer tick: Siguiente frame tras el impacto
        motor.actualizar(); 
        
        // Cuarto tick: El jugador decide pausar la partida
        input.procesarComando("PAUSA");
    }
}