package com.jorge.post1u11.strategy;

import com.jorge.post1u11.entity.Pedido;

public interface EstrategiaEnvio {

    double calcularCosto(Pedido pedido);
}