package com.desafiobtg.lojareport.infrastructure.service;

import com.desafiobtg.lojareport.domain.dto.PedidoDTO;
import com.desafiobtg.lojareport.domain.dto.ProdutoDTO;
import com.desafiobtg.lojareport.domain.model.Cliente;
import com.desafiobtg.lojareport.domain.model.Pedido;
import com.desafiobtg.lojareport.domain.model.Produto;
import com.desafiobtg.lojareport.domain.service.PedidoService;
import com.desafiobtg.lojareport.infrastructure.config.MongoConnectionConfig;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PedidoServiceImpl implements PedidoService {

    private final MongoConnectionConfig mongoConnectionConfig;
    private final ProdutoDTO produtoDTO;
    private PedidoDTO pedidoDTO;

    @Override
    public PedidoDTO getPedidoById(int id) {
        Document query = new Document("codigoPedido", id);
            FindIterable<Document> iterable = mongoConnectionConfig.dataSource().getCollection("DBBTGCOLLECTION").find(query);
            for (Document current : iterable) {
                JSONArray jsonProduto = new JSONArray(current.get("itens").toString());

                List<ProdutoDTO> produtoList = new ArrayList<>();

                for(int i = 0; i < jsonProduto.length(); i++){
                    ProdutoDTO produto = new ProdutoDTO();
                    JSONObject jsonObject = new JSONObject(jsonProduto.get(i));

                    produto.setProduto(jsonObject.getJSONObject("element").getString("produto"));
                    produto.setQuantidade(jsonObject.getJSONObject("element").getInt("quantidade"));
                    produto.setPreco(jsonObject.getJSONObject("element").getDouble("preco"));

                    produtoList.add(produto);
                }

                pedidoDTO = PedidoDTO.builder()
                        .codigoPedido(current.getLong("codigoPedido"))
                        .codigoCliente(current.getLong("codigoCliente"))
                        .itens(produtoList)
                        .build();
            }
        return pedidoDTO;
    }

    @Override
    public void savePadido(Pedido pedido) {
        Document document = new Document();
        document.append("codigoPedido", pedido.getCodigoPedido());
        document.append("codigoCliente", pedido.getCliente().getCodigoCliente());
        document.append("itens", pedido.getListProduto().toString());

        log.error("CONTEUDO DOCUMENTO = " + document);

        mongoConnectionConfig.dataSource().getCollection("DBBTGCOLLECTION").insertOne(document);

    }


}
