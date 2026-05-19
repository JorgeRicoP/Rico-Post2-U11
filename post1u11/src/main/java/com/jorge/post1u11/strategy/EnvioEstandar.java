package com.jorge.post1u11.strategy;

import com.jorge.post1u11.entity.Pedido;
import org.springframework.stereotype.Component;

@Component("ESTANDAR")
public class EnvioEstandar implements EstrategiaEnvio {

    @Override
    public double calcularCosto(Pedido pedido) {

        return pedido.getTotal() > 50 ? 0.0 : 5.99;
    }
}