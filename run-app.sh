#!/bin/bash

docker-compose -f ./additional-config/docker/docker-compose.yml up -d
./mvnw package
java -jar ./target/app1-0.0.1-SNAPSHOT.jar &
java -jar ./app2-0.0.1-SNAPSHOT.jar