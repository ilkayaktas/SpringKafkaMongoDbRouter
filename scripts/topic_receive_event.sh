#!/bin/bash


docker exec -it kafka bash

kafka-console-consumer --topic GuvercinTopic  --bootstrap-server localhost:9092 --from-beginning --property print.key=true --property key.separator=" : "