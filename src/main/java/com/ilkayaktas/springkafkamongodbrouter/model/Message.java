package com.ilkayaktas.springkafkamongodbrouter.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ilkayaktas on 18.01.2022 at 16:05.
 */
@Builder
@Document("message")
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @Getter @Setter private String id;
    @Getter @Setter private String to;
    @Getter @Setter private String from;
    @Getter @Setter private String body;
    @Getter @Setter private Integer messageType;
    @Getter @Setter private String groupId;
    @Getter @Setter private Long currentTimeInMilis;
}
