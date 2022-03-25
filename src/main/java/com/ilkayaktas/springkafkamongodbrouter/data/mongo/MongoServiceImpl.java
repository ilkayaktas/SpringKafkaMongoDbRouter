package com.ilkayaktas.springkafkamongodbrouter.data.mongo;

import com.ilkayaktas.springkafkamongodbrouter.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ilkayaktas on 22.03.2022 at 13:29.
 */
@Service
public class MongoServiceImpl implements MongoService{
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveMessage(Message message) {
        mongoTemplate.save(message);
        System.out.println("Saved!");
    }

    @Override
    public List<Message> getMessages() {
        return mongoTemplate.findAll(Message.class);
    }
}
