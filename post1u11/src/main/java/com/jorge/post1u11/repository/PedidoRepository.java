package com.jorge.post1u11.repository;

import com.jorge.post1u11.entity.Pedido;
import com.jorge.post1u11.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    default Producto findProductoById(Long id) {
        return new Producto(id, "Producto Demo", 100.0);
    }
}