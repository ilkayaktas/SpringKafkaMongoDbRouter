package com.ilkayaktas.springkafkamongodbrouter.data.kafka;

import com.ilkayaktas.springkafkamongodbrouter.model.KafkaPayload;

/**
 * Created by ilkayaktas on 7.07.2021 at 08:09.
 */

public interface KafkaService {
    void publishTopic(String topicName, KafkaPayload message);
    void publishTopic(String topicName, String message);
}
