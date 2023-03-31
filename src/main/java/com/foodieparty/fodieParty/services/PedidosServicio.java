package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.PedidoDTO;

import com.foodieparty.fodieParty.models.Pedido;


import org.springframework.security.core.Authentication;



import java.util.List;
import java.util.Optional;

public interface PedidosServicio {
    List<PedidoDTO> getPedidos();
    Optional<PedidoDTO> getPedidoPorId(Long id);
    List<PedidoDTO> getPedidosUsuario(Authentication authentication);
    void save(Pedido pedido);
}
