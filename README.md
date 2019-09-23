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
# Accessando o Swagger

- Abra um browser e na barra de endereços, digite:

http://localhost:8080/swagger-ui.html

# Importando a planilha de Produtos

# Consultando o log de processamento da planilhada

# Consultando todos os produtos cadastrados

# Consultando produto pelo id

# Atualizando produto

# Deletando produto
