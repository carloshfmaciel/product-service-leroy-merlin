# Exemplo de Aplicação - Leroy Merlin

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

1 - Expando o endpoint abaixo e clique no ***Try it out***

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_01.PNG)

2 - Clique no botão ***Escolher arquivo*** e selecione o arquivo ***java leroy.xlsx***, que está no diretório ***arquivos*** do projeto e clique no botão ***Executar***.

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_02.PNG)

3 - A API devolverá uma resposta com HTTP Status 200 e no corpo o id do processamento assíncrono com uma mensagem de sucesso.

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_03.PNG)

# Consultando o log de processamento da planilhada

1 - Expando o endpoint abaixo e clique no ***Try it out***

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_04.PNG)

2 - Informe o id do processamento e clique no botão ***Executar***

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_05.PNG)

3 - A API retornará os dados de log de processamento da plabilha.

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_06.PNG)

# Consultando todos os produtos cadastrados

1 - Expando o endpoint abaixo e clique no ***Try it out***

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_07.PNG)

2 - A consulta de todos os produtos é paginada e poderá ser informada o número da página e o tamanho da página. Caso não seja informado nenhum valor, a API assumirá o valor default(pageNumber = 0 e pageSize = 25). 

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_09.PNG)

3 - A API retornará uma lista de todos os produtos cadastrados.

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_10.PNG)

# Consultando produto pelo id

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_11.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_12.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_13.PNG)

# Atualizando produto

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_14.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_15.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_16.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_17.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_18.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_19.PNG)

# Deletando produto

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_20.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_21.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_22.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_23.PNG)

# Acessando o banco de dados

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_24.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_25.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_26.PNG)

![image](https://github.com/carloshfmaciel/product-service-leroy-merlin/blob/master/screenshots/swagger_27.PNG)
