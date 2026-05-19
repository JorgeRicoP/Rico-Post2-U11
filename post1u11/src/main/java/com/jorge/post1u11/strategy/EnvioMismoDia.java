package com.jorge.post1u11.strategy;

import com.jorge.post1u11.entity.Pedido;
import org.springframework.stereotype.Component;

@Component("MISMO_DIA")
public class EnvioMismoDia implements EstrategiaEnvio {

    @Override
    public double calcularCosto(Pedido pedido) {

        return 24.99;
    }
}