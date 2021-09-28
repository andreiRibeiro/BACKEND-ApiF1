# BACKEND-ApiF1

Esta API tem com objetivo integrar com a API Ergast para fornecer dados sobre F1, e processar questões de um desafio.

O serviço é executado em Spring e conta com endpoint's RestFull.

A API possui um controlador privado para consulta dos dados F1,e um controlador publico para processamento dos dados do desafio.

O processo de autenticatição é feito via header com <code>Authorization Basic aWNhcnJvczppY2Fycm9z</code> (user: icarros, password: icarros).

Utiliza banco H2 (memória) portanto sempre que sistema é reiniciado os dados são apagados.

Utiliza JPA para a maioria das operações, porem devido a um item do desafio, tambem possui operação direta com JDBC.

#### 1) Formas de execução do sistema e testes

   ##### Executar com Jar 
   ><code>java -jar icarros-0.0.1-SNAPSHOT.jar</code>

   ##### Executar testes 
   ><code>mvn test</code>

#### 2) Interface de requisições (Postman)
><code>[PostmanCollection.json](iCarros.postman_collection.json)</code>

#### 3) Swagger
><code>http://localhost:8282/icarros/api/swagger-ui.html</code>

#### 4) Acesso ao banco local
><code>http://localhost:8282/icarros/api/h2-console/</code>
><code>jdbc:h2:mem:icarrosdb</code>
><code>user: icarros, password: icarros</code>

#### 5) Imagem DockerHub
><code>docker pull andreiaar/icarros:latest</code>
