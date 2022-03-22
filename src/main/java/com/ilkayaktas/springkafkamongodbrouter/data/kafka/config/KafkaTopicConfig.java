package com.ilkayaktas.springkafkamongodbrouter.data.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ilkayaktas on 6.07.2021 at 15:20.
 */

@Configuration
public class KafkaTopicConfig {

   @Autowired
   private KafkaProperties kafkaProperties;

   @Bean
   public KafkaAdmin kafkaAdmin() {
      Map<String, Object> configs = new HashMap<>();
      configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
      return new KafkaAdmin(configs);
   }

}