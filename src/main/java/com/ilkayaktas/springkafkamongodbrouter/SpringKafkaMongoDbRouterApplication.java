package com.ilkayaktas.springkafkamongodbrouter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

@EnableMongoRepositories
public class SpringKafkaMongoDbRouterApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaMongoDbRouterApplication.class, args);
	}

}
