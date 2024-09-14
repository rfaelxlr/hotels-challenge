#!/bin/bash

# Navegar para o diretório do notification-service e empacotar
cd notification-service
mvn package
cd ..

# Navegar para o diretório do reservation-service e empacotar
cd reservation-service
mvn package
cd ..

# Navegar para o diretório do search-service e empacotar
cd search-service
mvn package
cd ..

# Construir a imagem do notification-service
docker build -t notification-service:latest ./notification-service

# Construir a imagem do reservation-service
docker build -t reservation-service:latest ./reservation-service

# Construir a imagem do search-service
docker build -t search-service:latest ./search-service
