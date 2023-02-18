package com.desafiobtg.lojareport.api.v1;

import com.desafiobtg.lojareport.domain.dto.PedidoDTO;
import com.desafiobtg.lojareport.domain.model.Pedido;
import com.desafiobtg.lojareport.infrastructure.service.PedidoServiceImpl;
import kong.unirest.json.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pedidos")
@Log4j2
public class PedidoController {

    private final PedidoServiceImpl pedidoService;

    public PedidoController(PedidoServiceImpl pedidoService){
        this.pedidoService = pedidoService;
    }
    @GetMapping(value = "/pedido/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable int id){
        PedidoDTO pedido = pedidoService.getPedidoById(id);
        return ResponseEntity.ok(pedido);
    }
}
