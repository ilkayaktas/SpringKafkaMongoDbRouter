#!/bin/bash

docker exec kafka kafka-topics --create --topic group-create-topic --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1
docker exec kafka kafka-topics --create --topic group-create-topic --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1 --property parse.key=true --property key.separator=":"

# Replication factor defines the number of copies of a topic in a Kafka cluster.