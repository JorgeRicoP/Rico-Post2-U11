package com.jorge.post1u11.service;

import com.jorge.post1u11.entity.Pedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnvioServiceTest {

    private final EnvioService service = new EnvioService();

    @Test
    void calcularEnvio_estandar_conTotalAlto_debeSerGratis() {

        Pedido p = new Pedido();
        p.setTotal(60.0);

        assertEquals(0.0,
                service.calcularEnvio(p, "ESTANDAR"),
                0.001);
    }
}