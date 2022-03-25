package com.ilkayaktas.springkafkamongodbrouter.data.mongo;

import com.ilkayaktas.springkafkamongodbrouter.model.Message;

import java.util.List;

/**
 * Created by ilkayaktas on 22.03.2022 at 13:28.
 */

public interface MongoService {
    void saveMessage(Message message);

    List<Message> getMessages();
}
