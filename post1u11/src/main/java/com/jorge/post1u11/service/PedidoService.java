package com.jorge.post1u11.service;

import com.jorge.post1u11.entity.Pedido;
import com.jorge.post1u11.repository.PedidoRepository;
import com.jorge.post1u11.valueobject.DatosCliente;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository repo;
    private final NotificacionService notificacion;

    public PedidoService(PedidoRepository repo,
                         NotificacionService notificacion) {

        this.repo = repo;
        this.notificacion = notificacion;
    }

    public String procesarPedido(DatosCliente cliente,
                                 double total,
                                 boolean urgente,
                                 String descuento) {

        double totalFinal = aplicarDescuento(total, descuento);

        notificacion.notificarPedido(cliente, urgente);

        return persistirPedido(cliente, totalFinal);
    }

    private double aplicarDescuento(double total, String descuento) {

        if ("VIP10".equals(descuento)) {
            return total * 0.90;
        }

        if ("NEW20".equals(descuento)) {
            return total * 0.80;
        }

        return total;
    }

    private String persistirPedido(DatosCliente cliente, double total) {

        Pedido pedido = new Pedido(1L, cliente.getNombre(), total);

        return "OK_" + repo.save(pedido).getId();
    }
}