package com.videojuego2d;

public class InputManager {
    private MotorJuego motor;

    public InputManager(MotorJuego motor) {
        this.motor = motor;
    }

    public void procesarComando(String comando) {
        System.out.println("\n[INPUT] Usuario ejecuta acción: " + comando);
        
        if (motor.getEstadoActual() != EstadoJuego.JUGANDO) {
            if (comando.equalsIgnoreCase("INICIAR")) {
                motor.cambiarEstado(EstadoJuego.JUGANDO);
            }
            return;
        }

        switch (comando.toUpperCase()) {
            case "ARRIBA":
                motor.getJugador().setY(motor.getJugador().getY() - 1);
                break;
            case "ABAJO":
                motor.getJugador().setY(motor.getJugador().getY() + 1);
                break;
            case "DERECHA":
                motor.getJugador().setX(motor.getJugador().getX() + 1);
                break;
            case "PAUSA":
                motor.cambiarEstado(EstadoJuego.PAUSA);
                break;
            default:
                System.out.println("[LOG] Comando no reconocido.");
        }
    }
}
