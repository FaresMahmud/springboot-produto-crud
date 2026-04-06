# Produto CRUD (Spring Boot)

Aplicacao web simples para cadastro e gerenciamento de produtos. Inclui telas
com Thymeleaf para criar, listar, editar e excluir registros.

## Funcionalidades
- Cadastro de produto com nome, descricao, valor, quantidade e imagem
- Listagem com acoes de editar e excluir
- Validacoes basicas no service
- Pagina inicial com atalhos para formulario e lista
- Rota de exemplo `/jogo`

## Stack
- Java 17
- Spring Boot (MVC, Data JPA, Thymeleaf)
- MySQL
- Maven

## Como rodar
1. Configure o banco em `src/main/resources/application.properties`.
   Use `src/main/resources/application.example.properties` como base.
2. Execute:
```
./mvnw spring-boot:run
```
3. Acesse:
- `http://localhost:8080/`
- `http://localhost:8080/produto/formulario`
- `http://localhost:8080/produto/listar`

## Rotas principais
- `GET /`
- `GET /produto/formulario`
- `POST /produto/salvar`
- `GET /produto/listar`
- `GET /produto/editar/{id}`
- `GET /produto/deletar/{id}`
- `GET /jogo`
