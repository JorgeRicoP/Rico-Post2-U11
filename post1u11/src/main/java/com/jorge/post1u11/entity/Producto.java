package com.jorge.post1u11.entity;

public class Producto {

    private Long id;
    private String nombre;
    private Double precio;

    public Producto(Long id, String nombre, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Double getPrecio() {
        return precio;
    }
}