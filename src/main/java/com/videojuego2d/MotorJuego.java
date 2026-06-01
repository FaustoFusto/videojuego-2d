package com.videojuego2d;

import java.util.ArrayList;
import java.util.Iterator;

public class MotorJuego {
    private EstadoJuego estadoActual;
    private ArrayList<EntidadVideojuego> entidades;
    private EntidadVideojuego jugador;
    private int enemigosEliminados = 0;

    // Subclases internas para cumplir con la herencia obligatoria del examen
    private static class Jugador extends EntidadVideojuego {
        public Jugador(int x, int y) { super("Nave-Hero", x, y, 1, 1, 3); }
        @Override
        public void actualizar() {
            System.out.println(" -> " + nombre + " en posición (" + x + "," + y + ") | Vida: " + vida);
        }
    }

    private static class Enemigo extends EntidadVideojuego {
        public Enemigo(int x, int y) { super("Meteorito", x, y, 1, 1, 1); }
        @Override
        public void actualizar() { x -= 1; } 
    }

    public MotorJuego() {
        this.estadoActual = EstadoJuego.MENU;
        this.entidades = new ArrayList<>();
    }

    public void cambiarEstado(EstadoJuego nuevoEstado) {
        this.estadoActual = nuevoEstado;
        System.out.println("[MOTOR] Estado cambiado a: " + nuevoEstado);
        if (nuevoEstado == EstadoJuego.JUGANDO && entidades.isEmpty()) {
            inicializarPartida();
        }
    }

    private void inicializarPartida() {
        jugador = new Jugador(0, 2);
        entidades.add(jugador);
        entidades.add(new Enemigo(3, 2)); 
        System.out.println("[MOTOR] Entidades cargadas en el mapa.");
    }

    public void actualizar() {
        if (estadoActual != EstadoJuego.JUGANDO) return;

        System.out.println("\n--- [TICK DE JUEGO] ---");
        
        Iterator<EntidadVideojuego> iter = entidades.iterator();
        while (iter.hasNext()) {
            EntidadVideojuego entidad = iter.next();
            entidad.actualizar();

            if (entidad != jugador && jugador.comprobarColision(entidad)) {
                System.out.println("[¡COLISIÓN!] Chocaste contra " + entidad.getNombre());
                jugador.setVida(jugador.getVida() - 1);
                iter.remove(); 
                enemigosEliminados++;
                
                // Funcionalidad Avanzada: Sistema de logros
                if (enemigosEliminados == 1) {
                    System.out.println("[LOGRO] -> 'Primer Impacto': Sobrevive a tu primer obstáculo.");
                }

                if (jugador.getVida() <= 0) {
                    cambiarEstado(EstadoJuego.GAME_OVER);
                    break;
                }
            }
        }
    }

    public EstadoJuego getEstadoActual() { return estadoActual; }
    public EntidadVideojuego getJugador() { return jugador; }
}
