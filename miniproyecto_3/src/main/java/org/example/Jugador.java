package org.example;

import javax.swing.*;

public class Jugador {

    private String nombre;
    private String categoria;
    private String dificultad;
    private int intentos;
    private int intentosFallidos;

    /**
     * Constructor de la clase Jugador.
     * Inicializa un objeto Jugador con valores predeterminados.
     */
    public void Jugador(){
        this.nombre = "";
        this.categoria = "";
        this.dificultad = "";
        this.intentos = 0;
        this.intentosFallidos = 0;
    }
    /**
    * Constructor de la clase Jugador que acepta parámetros para inicializar sus propiedades.
    * */
    public void Jugador(String nombre, String categoria, String dificultad, int intentos, int intentosFallidos){

        this.nombre=nombre;
        this.categoria = categoria;
        this.dificultad = dificultad;
        this.intentos = intentos;
        this.intentosFallidos = intentosFallidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }
}
