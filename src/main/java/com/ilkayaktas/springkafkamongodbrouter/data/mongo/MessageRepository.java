package com.ilkayaktas.springkafkamongodbrouter.data.mongo;

import com.ilkayaktas.springkafkamongodbrouter.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by ilkayaktas on 7.03.2022 at 13:59.
 */

@EnableMongoRepositories(basePackages = "tr.com.aselsan.mcc.guvercinwebserver.data.service.database.mongodb")
public interface MessageRepository extends MongoRepository<Message, String> {
}
