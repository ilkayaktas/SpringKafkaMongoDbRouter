#!/bin/bash

docker exec -it kafka kafka-console-producer --topic group-create-topic --bootstrap-server kafka:9092
