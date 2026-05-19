package com.jorge.post1u11.strategy;

import com.jorge.post1u11.entity.Pedido;
import org.springframework.stereotype.Component;

@Component("EXPRESS")
public class EnvioExpress implements EstrategiaEnvio {

    @Override
    public double calcularCosto(Pedido pedido) {

        return 12.99;
    }
}