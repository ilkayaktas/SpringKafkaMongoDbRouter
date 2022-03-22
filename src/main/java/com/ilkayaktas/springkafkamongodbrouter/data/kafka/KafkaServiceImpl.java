package com.ilkayaktas.springkafkamongodbrouter.data.kafka;

import com.google.gson.Gson;
import com.ilkayaktas.springkafkamongodbrouter.model.KafkaPayload;
import com.ilkayaktas.springkafkamongodbrouter.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by ilkayaktas on 7.07.2021 at 08:10.
 */

@Service
public class KafkaServiceImpl implements KafkaService{
   private Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

   @Autowired
   private KafkaTemplate<String, String> kafkaTemplate;

   @Autowired
   private KafkaTemplate<String, KafkaPayload> kafkaCustomPayloadTemplate;


   @Override
   public void publishTopic(String topicName, KafkaPayload message) {
      ListenableFuture<SendResult<String, KafkaPayload>> future =
              kafkaCustomPayloadTemplate.send(topicName, message);

      future.addCallback(new ListenableFutureCallback<>() {

         @Override
         public void onSuccess(SendResult<String, KafkaPayload> result) {
            logger.debug("Sent message=[" + message +
                    "] with offset=[" + result + "]");
         }

         @Override
         public void onFailure(Throwable ex) {
            logger.error("Unable to send message=["
                    + message + "] due to : " + ex.getMessage());
         }

      });
   }

   @Override
   public void publishTopic(String topicName, String message) {
      ListenableFuture<SendResult<String, String>> future =
              kafkaTemplate.send(topicName, message);

      future.addCallback(new ListenableFutureCallback<>() {

         @Override
         public void onSuccess(SendResult<String, String> result) {
            logger.debug("Sent message=[" + message +
                    "] with offset=[" + result.getRecordMetadata().offset() + "]");
         }

         @Override
         public void onFailure(Throwable ex) {
            logger.error("Unable to send message=["
                    + message + "] due to : " + ex.getMessage());
         }

      });
   }

   @KafkaListener(
           topics = "GuvercinTopic",
           containerFactory = "kafkaCustomPayloadListenerContainerFactory")
   public void subscribeTopic(String message) {
      logger.info("Received KafkaPayload Message in group 1001: " + message);

      Message msg = new Gson().fromJson(message, Message.class);

      logger.info(new Gson().toJson(msg));
   }

}
