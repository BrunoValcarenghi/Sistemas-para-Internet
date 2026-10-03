# CRUD - API REST

## Iniciando o projeto

### Dependencias
Considerando as instruções do simulado tem que colocar as seguintes dependencias no spring initializer  
![Imagem initializer](img/initializer.png) 

    Lombok   
    Flyway Migration  
    Spring Data JPA  
    PostgreSQL Driver  
    Validation  
    SpringDoc OpenAPI
    Adicionar: Spring Web  

além disso devido a versão do spring incluir também a dependencia
```xml

<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-database-postgresql</artifactId>
</dependency>

```

### properties

para configurar o application.properties colocamos as seguintes instruções.

```properties

#Servidor
server.port=8080

#postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=postgres
spring.datasource.password=1234
spring.datasource.driver-class-name=org.postgresql.Driver

#jpa
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

#flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true

#swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

```

também é importante lembrar que é necessario a criação da base de dados

## migrações

no simulado pede duas migrações separadas, uma com create table e outra com insert  
as migration devem ser feitas em um arquivo .sql dentro da pasta resources/db.migrations 
iniciadas por exemplo V1__ , imporante que seja dois underlines e V maisuculo 

### V1__create_table_sala

```sql

    create table sala(

        id_sala serial primary key,
        nome varchar(50) unique not null,
        qnt_aluno int not null,
        qnt_pc int,
        ano int not null,
        area decimal(10,2) not null,
        situacao varchar(30) not null
        
    );

```

para criar um enum se coloca no banco como varchar  
primary key ja faz a coluna ser unique

### V2__insert_into_sala

```sql

    insert into sala (nome, qnt_aluno, qnt_pc, ano, area, situacao) values
        ('Bloco F sala 109', 'f109', 40, 38, 1998, 65.8, 'DISPONIVEL'),
        ('Bloco F sala 209', 'f209', 30, 32, 1998, 42.7, 'EM_REFORMA'),
        ('Bloco G sala 109', 'g109', 20, 15, 2015, 20, 'INTERDITADA');

```

aqui é necessario informar as colunas que serao populadas visto q chave primaria é auto
incrementavel

### Configurar data souce

após criado os arquivos contendo as migration é necessario configurar o data souce do intelij
esta marcado com vermelho campos e opções necessarias
![Data Souce](img/data.png)

## Model

## enum

para fazer uma tabela com enum deve-se criar uma classe java com o nome do campo, nesse caso Situacao.java
dentro da pasta model e dentro da classe criar o enum

```java

    public enum Situacao{
    
        DISPONIVEL,
        EM_REFORMA,
        INTERDITADA
    
    }

```
## model

### classe

para a classe usamos as seguintes anotações

```bash
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name="sala") //nome da tabela no banco
    @Schema(description = "entidade que representa sala de aula") //descricao com swagger
```

### atributos

para todos os atributos usaremos as anotações ```@Column(name="nome_coluna_no_banco")``` e ```@Schema(description="documentacao_swagger")```

iniciamos com o ID, que será do tipo long  
também sera usado a anotação ```@GeneratedValue(strategy = GenerationType.IDENTITY)``` além de ```@Id```

outras anotações que iremos usar será   

| Anotação | Descricao dela                          |
| :--- |:----------------------------------------|
```@NotBlank(message="doc_desc")``` | para strings que nao podem ser vazias
```@NonNull(message="doc_desc")``` | para numeros que nao podem ser vazios
```@Max(value=100, message="doc_desc")``` | para definir maximo  
```@Min(value=0, message="doc_desc")``` | para definir minimo  
```@Size(min=0, max=100, message="doc_desc")``` | para definir minimo e o maximo de uma string
```@DecimalMin(value=0.0, inclusive=false, message="doc_desc")``` | para definir minimo em um tipo decimal

observações:

    - size funciona para strings mas para inteiros deve usar min e max
    - para num usar o tipo enum referente ao campo
    - para enum deve se usar notnull e nao notblank
    - quando for deicmal usar value entre aspas "0.0"
    
    