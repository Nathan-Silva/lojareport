package com.desafiobtg.lojareport.infrastructure.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j2;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class MongoConnectionConfig {
    private static final String DB_NAME = "DBBTG";

    private static String mongo = "mongodb://localhost:27017";


    @Bean(name = "mongoDataSource")
    public MongoDatabase dataSource() {
        MongoClient mongoClient = MongoClients.create(mongo);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_NAME);
        return mongoDatabase;
    }
}
