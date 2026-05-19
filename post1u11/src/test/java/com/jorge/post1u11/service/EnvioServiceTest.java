package com.jorge.post1u11.service;

import com.jorge.post1u11.entity.Pedido;
import com.jorge.post1u11.strategy.EnvioEstandar;
import com.jorge.post1u11.strategy.EstrategiaEnvio;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnvioServiceTest {

    private final com.jorgerico.post1u11.service.EnvioService service;

    public EnvioServiceTest() {

        Map<String, EstrategiaEnvio> estrategias = new HashMap<>();

        estrategias.put("ESTANDAR", new EnvioEstandar());

        service = new com.jorgerico.post1u11.service.EnvioService(estrategias);
    }

    @Test
    void calcularEnvio_estandar_conTotalAlto_debeSerGratis() {

        Pedido p = new Pedido();

        p.setTotal(60.0);

        assertEquals(
                0.0,
                service.calcularEnvio(p, "ESTANDAR"),
                0.001
        );
    }
}