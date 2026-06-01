package com.videojuego2d;

public abstract class EntidadVideojuego {
    protected int x, y, w, h;
    protected String nombre;
    protected int vida;

    public EntidadVideojuego(String nombre, int x, int y, int w, int h, int vida) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.vida = vida;
    }

    // Funcionalidad Avanzada: Detector de Colisiones Matemático
    public boolean comprobarColision(EntidadVideojuego otra) {
        return this.x < otra.x + otra.w &&
               this.x + this.w > otra.x &&
               this.y < otra.y + otra.h &&
               this.y + this.h > otra.y;
    }

    public abstract void actualizar();

    public String getNombre() { return nombre; }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
}
