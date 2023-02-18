package com.desafiobtg.lojareport.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProdutoDTO {

    private String produto;

    private int quantidade;

    private double preco;
}
