# Exemplo de Aplicação - Teste Java - Leroy Merlin

Autor: Carlos Maciel<br>
Email: carloshfmaciel@gmail.com<br>
Linkedin: https://www.linkedin.com/in/carlos-maciel-64796117<br>

## A Aplicação fornece as seguintes funcionalidades:
- Cadastro de Produtos via planilha excel(xlsx) - Assíncrono via fila
- CRUD dos Produtos
- Log de status do processamento assíncrono da planilha
- Interface Gráfica para consumo das apis (Swagger)
- Interface Gráfica para consulta direta ao banco de dados (H2)

## Tecnologias utilizadas

- Spring Boot 2
- Spring Data
- H2 - SGBD Embedded
- Active MQ 5 (Message Broker Embedded)
- Swagger
- Apache POI


## Arquitetura/Patterns usados

- Controller
- Service
- Repository
- Consumer/Producer
- Utils

## Testes unitários

- Testes unitários realizados apenas sobre os métodos que continham algum lógica condicional(IF).
- Nomes do métodos escritos no Padrão BDD (Given/When/Then)
- Tecnologias utilizadas(Junit e Mockito)

# Baixando o projeto

git clone https://github.com/carloshfmaciel/product-service-leroy-merlin.git

# Startando a aplicação

- Via console, acessar o diretório target e executar o comando:

```java
java -jar product-service-1.0.0-SNAPSHOT.jar
```
![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/start_app.PNG)

# Accessando o Swagger

- Abra um browser e na barra de endereços, digite:

http://localhost:8080/swagger-ui.html

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger.PNG)

# Importando a planilha de Produtos

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_01.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_02.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_03.PNG)

# Consultando o log de processamento da planilhada

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_04.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_05.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_06.PNG)

# Consultando todos os produtos cadastrados

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_07.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_09.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_10.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_10.PNG)

# Consultando produto pelo id

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_11.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_12.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_13.PNG)

# Atualizando produto

# Deletando produto
