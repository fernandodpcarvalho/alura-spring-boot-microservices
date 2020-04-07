# Spring Zuul - API Gateway

Centraliza todas as requisições externas e direciona para os microserviços correspondentes.

Sem uma API Gateway, os clientes precisariam saber os endereços de cada serviço, e perguntar ao Eureka.

Zuul = Recebe uma requisição, conversa com o Eureka.

Os serviços usam o Eureka para descoberta.

o cliente precisa do API Gateway para descoberta.

Não faz sentido expor o Eureka na web



# Docker: Para executar a aplicação em um container, executar os seguintes comandos na pasta "/zuul":

mvn clean package -DSPRING_PROFILES_ACTIVE=dev -DCONFIG_SERVER_CONTEXT=localhost   spring-boot:repackage

docker build --build-arg JAR_FILE=target/*.jar -t zull-server .

docker run --name zull-server -d -p 5555:5555 --network microservice-network zull-server
