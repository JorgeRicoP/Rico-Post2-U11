package com.jorge.post1u11.service;

import com.jorge.post1u11.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public class CreditoService {

    public String aprobarCredito(Cliente c, double monto) {

        if (c != null) {

            if (c.isActivo()) {

                if (c.getScore() >= 600) {

                    if (monto > 0) {

                        if (monto <= c.getLimiteCredito()) {

                            return "APROBADO";
                        }
                    }
                }
            }
        }

        return "RECHAZADO";
    }
}