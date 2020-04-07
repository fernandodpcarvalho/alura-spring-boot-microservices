# Microsserviço fornecedor

Disponibiliza catálogo de produtos e recebe solicitações de reserva.


# Docker: Para executar a aplicação em um container, executar os seguintes comandos na pasta "/auth":

mvn clean package spring-boot:repackage

docker build --build-arg JAR_FILE=target/*.jar -t cervejaria-fornecedor .

docker run --name cervejaria-fornecedor -d -p 8081:8081 cervejaria-fornecedor
