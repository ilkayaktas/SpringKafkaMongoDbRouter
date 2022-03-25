package com.ilkayaktas.springkafkamongodbrouter.data.kafka.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ilkayaktas on 8.03.2022 at 23:55.
 */

public class KafkaErrorHandler implements CommonErrorHandler {
   private Logger logger = LoggerFactory.getLogger(KafkaErrorHandler.class);
   /**
    * Method prevents serialization error freeze
    *
    * @param e
    * @param consumer
    */
   private void seekSerializeException(Exception e, Consumer<?, ?> consumer) {
      String p = ".*partition (.*) at offset ([0-9]*).*";
      Pattern r = Pattern.compile(p);

      Matcher m = r.matcher(e.getMessage());

      if (m.find()) {
         int idx = m.group(1).lastIndexOf("-");
         String topics = m.group(1).substring(0, idx);
         int partition = Integer.parseInt(m.group(1).substring(idx));
         int offset = Integer.parseInt(m.group(2));

         TopicPartition topicPartition = new TopicPartition(topics, partition);

         consumer.seek(topicPartition, (offset + 1));
         consumer.commitSync();

         logger.error("Skipped message in partition {} from offset {} to offset {}", partition, offset, (offset+1));
      }
   }

   @Override
   public void handleRecord(Exception e, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
      CommonErrorHandler.super.handleRecord(e, record, consumer, container);
      logger.error("Error in process with Exception {} and the record is {}", e, record);

      if (e instanceof SerializationException)
         seekSerializeException(e, consumer);
   }

   @Override
   public boolean remainingRecords() {
      return CommonErrorHandler.super.remainingRecords();
   }

}
