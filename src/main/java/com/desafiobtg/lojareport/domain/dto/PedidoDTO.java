package com.desafiobtg.lojareport.domain.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PedidoDTO {

    private long codigoPedido;
    private long codigoCliente;

    @SerializedName("itens")
    private List<ProdutoDTO> itens;
}
