# Auth server

Servidor de autenticação

AuthenticationManager e UserDetailService são os beans expostos do Spring Security e injetados no Adapter do Spring Cloud OAuth2: o AuthorizationServerConfigurerAdapter. Mais especificamente, a integração é feita no método configure deste adapter.

A autenticação é passada no header. 
Quando um serviço recebe uma requisição, ao chamar outro serviço ele deve repassar o token.
Isto é feito inserindo um RequestInterceptor que captura o token recebido na requisição e insere na requisição da chamada ao outro serviço.
Hystrix: Quando faz uma requisição a outro serviço, ele cria nova thread. NEste caso, o hystrix deve compartilhar os dados de header da thread principal para as demais. Para isso usa-se a configuração shareSecurityContext

Implementar um filtro do Feign para repassar o token do usuário nas chamadas entre microsserviços


# Docker: Para executar a aplicação em um container, executar os seguintes comandos na pasta "/auth":

mvn clean package spring-boot:repackage

docker build --build-arg JAR_FILE=target/*.jar -t auth-server .

docker run --name auth-server -d -p 8088:8088 --network microservice-network auth-server
