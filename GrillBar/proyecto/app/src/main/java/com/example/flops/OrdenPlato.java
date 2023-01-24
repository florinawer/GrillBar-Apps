package com.example.flops;

public class OrdenPlato {
    private String nombre;
    private String butter;
    private String punto;
    private String side;

    //constructor
    public OrdenPlato(String nombre, String butter, String punto, String side) {
        this.nombre = nombre;
        this.butter = butter;
        this.punto = punto;
        this.side = side;
    }

    //setters y getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getButter() {
        return butter;
    }

    public void setButter(String butter) {
        this.butter = butter;
    }

    public String getPunto() {
        return punto;
    }

    public void setPunto(String punto) {
        this.punto = punto;
    }

    public String getSide() {
        return side;
    }

    //método toString
    @Override
    public String toString() {
        return "OrdenPlato{" +
                "nombre='" + nombre + '\'' +
                ", butter='" + butter + '\'' +
                ", punto='" + punto + '\'' +
                ", side='" + side + '\'' +
                '}';
    }
}
