# Spring Boot Produto CRUD

Aplicacao web em Spring Boot para gerenciamento de produtos e categorias, com interface server-side em Thymeleaf e persistencia em MySQL.

## O que o projeto faz

- CRUD completo de `Produto`
- CRUD completo de `Categoria`
- Relacao `ManyToOne` entre produto e categoria
- Selecao de categoria no formulario de produto
- Exibicao da categoria na listagem de produtos
- Carga automatica das categorias padrao `Action Figure` e `Outros` na inicializacao
- Pagina inicial com atalhos para navegar no sistema
- Rota adicional `/jogo`

## Tecnologias

- Java 17
- Spring Boot 4
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- Maven
- Lombok

## Estrutura

- `controller`: controla as rotas web de produtos, categorias e a rota `/jogo`
- `service`: aplica validacoes e regras de negocio
- `repository`: acesso ao banco com JPA
- `model`: entidades `Produto` e `Categoria`
- `templates`: telas HTML com Thymeleaf
- `config`: carga inicial de categorias

## Requisitos

- Java 17
- Maven Wrapper do projeto
- MySQL em execucao

## Configuracao

1. Configure o banco em `src/main/resources/application.properties`.
2. Se preferir, use `src/main/resources/application.example.properties` como base.

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.thymeleaf.cache=false
server.port=8080
```

## Como executar

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Em Linux/macOS:

```bash
./mvnw spring-boot:run
```

## Como testar

```powershell
.\mvnw.cmd test
```

## Rotas principais

- `GET /`
- `GET /produto/formulario`
- `POST /produto/salvar`
- `GET /produto/listar`
- `GET /produto/editar/{id}`
- `GET /produto/deletar/{id}`
- `GET /categoria/formulario`
- `POST /categoria/salvar`
- `GET /categoria/listar`
- `GET /categoria/editar/{id}`
- `GET /categoria/deletar/{id}`
- `GET /jogo`

## Acessos uteis

- `http://localhost:8080/`
- `http://localhost:8080/produto/listar`
- `http://localhost:8080/produto/formulario`
- `http://localhost:8080/categoria/listar`
- `http://localhost:8080/categoria/formulario`

## Observacoes

- Ao iniciar a aplicacao, as categorias `Action Figure` e `Outros` sao inseridas automaticamente se ainda nao existirem.
- O projeto usa `spring.jpa.hibernate.ddl-auto=update`, entao o schema pode ser atualizado automaticamente conforme as entidades.
