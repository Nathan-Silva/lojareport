package com.desafiobtg.lojareport.domain.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private String descProduto;
    private int quantidadeProduto;
    private double preco;
}
