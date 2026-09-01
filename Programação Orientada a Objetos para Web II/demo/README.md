# API Avaliador de Projetos

A aplicação foi construída utilizando Java 21 e Spring Boot 4, 
com suporte ao PostgreSQL para o armazenamento permanente dos 
dados. Para garantir o controle das alterações no banco de dados, 
o Flyway foi utilizado para executar as migrations automaticamente 
ao iniciar o projeto, criando as tabelas em formato singular (aluno, 
projeto e avaliador).  

## Estrutura do Projeto

***Model:*** Representa as entidades do banco de dados mapeadas com JPA.

***Repository:*** Interfaces responsáveis por executar as operações de 
banco de dados (create, read, delete).

***DTO (Data Transfer Object):*** Classes no formato Java record 
utilizadas para receber e devolver os dados da API com validações.

***Service:*** Contém as regras de negócio do sistema, como as 
verificações para impedir cadastros duplicados de e-mail ou matrícula.

***Controller:*** Camada que expõe as URLs e processa as 
requisições HTTP da aplicação.

## Funcionalidades da API

* ***Criar (POST):*** Recebe os dados e cadastra um novo registro no sistema.

* ***Listar (GET):*** Retorna uma lista com todos os registros cadastrados.

* ***Buscar por UUID (GET /{uuid}):*** Localiza e retorna os dados de um 
registro específico pelo seu UUID.

* ***Deletar (DELETE /{uuid}):*** Remove um registro do banco de dados 
utilizando seu UUID.

## Dependências Utilizadas

* ***Spring Web MVC***: Responsável pela criação dos endpoints REST e 
processamento das requisições HTTP.

* ***Spring Data JPA***: Abstração da camada de persistência 
para comunicação e operações no banco de dados.

* ***PostgreSQL Driver***: Driver para conexão entre a 
aplicação Java e o banco de dados PostgreSQL.

* ***Flyway***: Gerenciamento de versionamento do 
banco e execução automatizada das migrações SQL.

* ***Flyway PostgreSQL***: Suporte específico do 
Flyway para integração com a base PostgreSQL.

* ***Springdoc OpenAPI (Swagger UI)***: Gerador da 
interface interativa para documentação e teste 
das rotas no navegador.

* ***Lombok***: Biblioteca para redução de código
repetitivo através de anotações (getters, setters, etc.).