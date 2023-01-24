package com.example.flops;

public class Plato {
    private int idPlato;
    private String nombre;
    private int cantidad;

    public Plato(int idPlato, String name, int cantidad) {
        this.nombre = name;
        this.cantidad = cantidad;
        this.idPlato = idPlato;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }


}

