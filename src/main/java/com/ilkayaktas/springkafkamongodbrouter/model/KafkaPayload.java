package com.ilkayaktas.springkafkamongodbrouter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by ilkayaktas on 7.07.2021 at 09:48.
 */
@Setter
@Getter
@AllArgsConstructor
public class KafkaPayload {
   private String groupId;
   private String groupName;
   private List<String> memberChatAddress;
   private String groupChatAddress;
   private List<String> adminChatAddressList;
}
