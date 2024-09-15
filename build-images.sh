#!/bin/bash

cd notification-service
rm -f target/*.jar
mvn package
cd ..

cd reservation-service
rm -f target/*.jar
mvn package
cd ..

cd search-service
rm -f target/*.jar
mvn package
cd ..

docker build -t notification-service:latest ./notification-service

docker build -t reservation-service:latest ./reservation-service

docker build -t search-service:latest ./search-service
