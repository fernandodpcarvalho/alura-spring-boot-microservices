# Spring Eureka Server

Projeto Spring que implementa os padrões de Service discovery e Service registry.

Service registry é um servidor central, onde todos os microsserviços ficam cadastrados (nome e IP/porta)

Service discovery é um mecanismo de descoberta do IP do microsserviço pelo nome

Dessa forma, nenhum microsserviço fica acoplado ao outro pelo IP/porta

Os serviços se registram automaticamente no Eureka.

# Para mais informações:
https://medium.com/swlh/spring-cloud-service-discovery-with-eureka-16f32068e5c7


# Docker: Para executar a aplicação em um container, executar os seguintes comandos na pasta "/eureka-server":

mvn clean package spring-boot:repackage

docker build --build-arg JAR_FILE=target/*.jar -t cervejaria-eureka .

docker run --name cervejaria-eureka -d -p 8761:8761 cervejaria-eureka
