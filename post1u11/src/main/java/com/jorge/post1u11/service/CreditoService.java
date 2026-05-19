package com.jorge.post1u11.service;

import com.jorge.post1u11.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public class CreditoService {

    public String aprobarCredito(Cliente c, double monto) {

        if (c == null) return "RECHAZADO";

        if (!c.isActivo()) return "RECHAZADO";

        if (c.getScore() < 600) return "RECHAZADO";

        if (monto <= 0) return "RECHAZADO";

        if (monto > c.getLimiteCredito()) return "RECHAZADO";

        return "APROBADO";
    }
}