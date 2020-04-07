# Spring Config Server

Servidor que armazena e centraliza as configurações de todos os serviços.

Os arqivos de configuração de todas as aplicações estão armazenados na pasta config-files do projeto

# Para mais informações:

https://medium.com/@mool.smreeti/spring-cloud-config-server-414310d01ceb

# Docker: Para executar a aplicação em um container, executar os seguintes comandos na pasta "/auth":

mvn clean package spring-boot:repackage

docker build --build-arg JAR_FILE=target/*.jar -t cervejaria-config-server .

docker run --name cervejaria-config-server -d -p 8888:8888 cervejaria-config-server
