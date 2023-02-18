package com.desafiobtg.lojareport.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Pedido implements Serializable {
    private ObjectId id;
    private long codigoPedido;
    private Cliente cliente;
    private List<Produto> listProduto;
}
