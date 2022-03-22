#!/bin/bash

./gradlew build

containerId=$(docker stop kafkamongodbrouter)

# Remove container
docker rm $containerId

# Remove image
docker rmi ilkayaktas/kafkamongodbrouter

# Build image
docker build --build-arg JAR_FILE=build/libs/\*.jar -t ilkayaktas/kafkamongodbrouter .


# Run container
docker run --name kafkamongodbrouter --restart=unless-stopped --net mcc-network ilkayaktas/kafkamongodbrouter
