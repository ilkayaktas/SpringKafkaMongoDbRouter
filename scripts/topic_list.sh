#!/bin/bash

docker exec kafka kafka-topics  --list --bootstrap-server localhost:9092
