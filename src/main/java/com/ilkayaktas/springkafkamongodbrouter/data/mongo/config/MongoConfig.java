package com.ilkayaktas.springkafkamongodbrouter.data.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by ilkayaktas on 7.03.2022 at 11:15.
 */

@Configuration
@EnableConfigurationProperties(MongoProperties.class)
public class MongoConfig extends AbstractMongoClientConfiguration {

   @Autowired
   MongoProperties mongoProperties;

   @Bean
   MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
      return new MongoTransactionManager(dbFactory);
   }

   @Bean
   public MongoClient mongo() {
      ConnectionString connectionString = new ConnectionString(mongoProperties.getUri());
      MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
              .applyConnectionString(connectionString)
              .build();

      return MongoClients.create(mongoClientSettings);
   }

   @Bean
   public MongoTemplate mongoTemplate(){
      return new MongoTemplate(mongo(), mongoProperties.getDatabase());
   }

   @Override
   protected String getDatabaseName() {
      return mongoProperties.getDatabase();
   }


  /* @Override
   protected void configureClientSettings(MongoClientSettings.Builder builder) {

      builder.credential(MongoCredential.createCredential(mongoProperties.getUsername(),
                      mongoProperties.getDatabase(),
                      mongoProperties.getPassword()))
              .applyToClusterSettings(settings  -> {
                 settings.hosts(Collections.singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort())));

              });
   }*/
}
