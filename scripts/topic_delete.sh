#!/bin/bash

if [ $# -ne 1 ]
then
    echo "Missing argument"
    exit 0
else 
    docker exec kafka kafka-topics --create --topic $1 --bootstrap-server kafka:9092
fi
