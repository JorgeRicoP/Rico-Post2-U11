package com.jorge.post1u11.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditoServiceTest {

    private final CreditoService service = new CreditoService();

    @Test
    void aprobarCredito_clienteNulo_debeRechazar() {

        assertEquals("RECHAZADO",
                service.aprobarCredito(null, 1000));
    }
}