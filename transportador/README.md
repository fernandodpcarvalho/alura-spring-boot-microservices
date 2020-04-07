# Microsserviço transportador

Recebe solicitação de reserva para transporte e gera voucher.


# Docker: Para executar a aplicação em um container, executar os seguintes comandos na pasta "/auth":

mvn clean package spring-boot:repackage

docker build --build-arg JAR_FILE=target/*.jar -t cervejaria-transportador .

docker run --name cervejaria-transportador -d -p 8082:8082 cervejaria-transportador
