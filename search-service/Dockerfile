# Dockerfile

# Usa uma imagem base com JDK 17
FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o arquivo JAR gerado pela aplicação
COPY target/search-service-0.0.1-SNAPSHOT.jar search-service.jar

# Expõe a porta que o Spring Boot usará
EXPOSE 8080

# Comando de execução da aplicação
ENTRYPOINT ["java", "-jar", "search-service.jar"]
