package com.desafiobtg.lojareport.infrastructure.queues;

import com.desafiobtg.lojareport.domain.model.Cliente;
import com.desafiobtg.lojareport.domain.model.Pedido;
import com.desafiobtg.lojareport.infrastructure.service.PedidoServiceImpl;
import com.google.gson.Gson;
import kong.unirest.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class QueueConsumer {

    private final Gson gson;
    private final PedidoServiceImpl pedidoService;
    @RabbitListener(queues = {"${queue.name}"})
    public void receiver(@Payload String fileBody) {
        System.out.println("Message: " + fileBody);

        JSONObject jsonObject = new JSONObject(fileBody);


        Pedido pedido = Pedido.builder()
                .codigoPedido(jsonObject.getLong("codigoPedido"))
                .cliente(Cliente.builder().codigoCliente(jsonObject.getLong("codigoCliente")).build())
                .listProduto(jsonObject.getJSONArray("itens").toList())
                .build();

        pedidoService.savePadido(pedido);

    }
}
