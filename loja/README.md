# Microsserviço loja

Disponibiliza o serviço de compra envolvendo os demais microserviços de fornecedor e transportador.


# Docker: Para executar a aplicação em um container, executar os seguintes comandos na pasta "/auth":

mvn clean package spring-boot:repackage

docker build --build-arg JAR_FILE=target/*.jar -t cervejaria-loja .

docker run --name cervejaria-loja -d -p 8080:8080 cervejaria-loja
