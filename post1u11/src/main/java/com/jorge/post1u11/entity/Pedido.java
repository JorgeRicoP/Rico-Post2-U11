package com.jorge.post1u11.entity;

import jakarta.persistence.*;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;
    private String clienteNombre;
    private Double total;

    public Pedido() {
    }

    public Pedido(Long clienteId, String clienteNombre, Double total) {
        this.clienteId = clienteId;
        this.clienteNombre = clienteNombre;
        this.total = total;
    }

    public Long getId() {
        return id;
    }
}