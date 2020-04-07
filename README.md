# Microserviços - Sistema de cervejaria artesanal online

Sistema de vendas on line de cervejas artesanais

#MySql: Instruções

# Para subir o container com o MySql, digite o seguinte comando na pasta do projeto, onde se encontra o arquivo docker-compose.yaml

docker-compose up -d


# Para acessar o bash do container do MySql, executar o seguinte comando:

docker exec -it cervejaria-mysql bash


# Para logar no MySql:

mysql -u cervejaria -p 

password: cervejaria


######## Spring 

# Spring Cloud Netflix Ribbon

Implementa o Client Side Load Balancing (CSLB). Permite que o cliente HTTP (que faz a requisição) decida qual microsserviço recebe a requisição, dentre as instâncias disponíveis registradas no Eureka.

Colocar a anotação @LoadBalanced, na criação do RestTemplate.

RestTemplate do Spring: Permite chamadas HTTP de alto nível.

DiscoveryClient: Interface que permite obter as instâncias de um serviço.


# Spring Feign

Alternativa ao RestTemplate. Já usa o Ribbon para CSLB

Exige apenas uma interface, com a definição e mapeamento dos métodos que executarão a requisição



# Para saber mais:

https://spring.io/guides/gs/client-side-load-balancing/

https://medium.com/@melardev/spring-cloud-with-ribbon-load-balancer-f334d87e3d66

https://medium.com/java-e-arquitetura-de-forma-mastigada/iniciando-com-feign-19c2b0d7d1c3


#Logs Distribuídos

Cada microsserviço (e instância dele) possui o seu log

Aagregadores de log = Unifica os logs. Ex: Papertrail

Logback gera os logs e envia ao agregador, atravez de um appender.

https://www.papertrail.com/

https://my.papertrailapp.com/events


# Correlation-id (Trace ID)

Identificador da transação, que é passada de requisição pra requisição. Permite rastrear quais requisições fazem parte da mesma transação

Spring Sleuth: https://spring.io/projects/spring-cloud-sleuth

TraceID = Mesmo valor para todas as transações da requisição.

SpanID = Um valor diferente para cada serviço chamado.

https://my.papertrailapp.com/events


#Spring Hystrix:

Implementa o padrão circuit breaker. 

Defini-se um tempo limite para processar uma requisição (Padrão = 1s). Após isso, retorna erro ou executa o fallback.

Fallback: Medida a ser tomada caso um microserviço falhe. Pode ser um cache.

Quando percebe que um serviço está dando muito erro, ele passa a chamar o fallback diretamente. De tempos em tempos ele volta a chamar o serviço pra ve se voltou.


# Bulkhead:

Aloca grupos de threads para cada requisição. Isso impede que o excesso de uma requisição não trave a execução de outras.

Um serviço pode atender diversos tipos de requisições, de forma simultânea (POST, GET, etc). 
A aplicação aloca cada requisição em uma thread. Se receber um número muito elevado de uma determinada requisição, esta poderá usar todas as threads disponíveis e a aplicação não conseguirá atender outras requisições, ocasionando retornos com erro.

Se um serviço S1 faz uma requisição a outro serviço S2, e este demora pra responder, pode haver um acúmulo de threads em uso aguardando resposta até que não haja mais threads disponíveis. 

Comando: threadPoolKey
