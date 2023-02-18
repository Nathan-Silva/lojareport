package com.desafiobtg.lojareport.domain.service;

import com.desafiobtg.lojareport.domain.dto.PedidoDTO;
import com.desafiobtg.lojareport.domain.model.Pedido;
import kong.unirest.json.JSONObject;

public interface PedidoService {
    public PedidoDTO getPedidoById(int id);

    public void savePadido(Pedido pedido);
}
