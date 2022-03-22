package com.ilkayaktas.springkafkamongodbrouter.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ilkayaktas on 18.01.2022 at 16:05.
 */
@Builder
@Document("chatmessages")
public class Message {
    @Getter @Setter private String id;
    @Getter @Setter private String to;
    @Getter @Setter private String from;
    @Getter @Setter private String body;
    @Getter @Setter private Integer messageType;
    @Getter @Setter private String groupId;
    @Getter @Setter private Long currentTimeInMilis;
}
