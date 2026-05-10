# 🛒 Spring Boot Produto CRUD

Aplicação web completa para **gerenciamento de produtos e categorias**, desenvolvida com Spring Boot, Thymeleaf e MySQL.

---

## 📸 Funcionalidades

- ✅ CRUD completo de **Produtos**
- ✅ CRUD completo de **Categorias**
- ✅ Relacionamento `ManyToOne` entre produto e categoria
- ✅ Seleção de categoria no formulário de produto
- ✅ Carga automática de categorias padrão na inicialização
- ✅ Interface web com Thymeleaf (server-side rendering)
- ✅ Página inicial com navegação entre seções

---

## 🛠️ Tecnologias utilizadas

| Tecnologia | Versão |
|---|---|
| Java | 17 |
| Spring Boot | 3.x |
| Spring MVC | — |
| Spring Data JPA | — |
| Thymeleaf | — |
| MySQL | 8+ |
| Maven | — |
| Lombok | — |

---

## 📁 Estrutura do projeto

```
src/
├── controller/     → Rotas web (produtos, categorias)
├── service/        → Regras de negócio e validações
├── repository/     → Acesso ao banco via JPA
├── model/          → Entidades Produto e Categoria
├── config/         → Carga inicial de dados
└── templates/      → Telas HTML com Thymeleaf
```

---

## ⚙️ Como executar

### Pré-requisitos

- Java 17+
- MySQL rodando localmente
- Maven (ou use o wrapper do projeto)

### 1. Clone o repositório

```bash
git clone https://github.com/FaresMahmud/springboot-produto-crud.git
cd springboot-produto-crud
```

### 2. Configure o banco de dados

Copie o arquivo de exemplo e edite com suas credenciais:

```bash
cp src/main/resources/application.example.properties src/main/resources/application.properties
```

Edite `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.thymeleaf.cache=false
server.port=8080
```

### 3. Execute a aplicação

**Windows:**
```bash
.\mvnw.cmd spring-boot:run
```

**Linux/macOS:**
```bash
./mvnw spring-boot:run
```

### 4. Acesse no navegador

```
http://localhost:8080/
```

---

## 🗺️ Rotas disponíveis

| Método | Rota | Descrição |
|---|---|---|
| GET | `/` | Página inicial |
| GET | `/produto/listar` | Lista todos os produtos |
| GET | `/produto/formulario` | Formulário de novo produto |
| POST | `/produto/salvar` | Salva produto |
| GET | `/produto/editar/{id}` | Edita produto |
| GET | `/produto/deletar/{id}` | Remove produto |
| GET | `/categoria/listar` | Lista categorias |
| GET | `/categoria/formulario` | Formulário de nova categoria |
| POST | `/categoria/salvar` | Salva categoria |
| GET | `/categoria/editar/{id}` | Edita categoria |
| GET | `/categoria/deletar/{id}` | Remove categoria |

---

## 📝 Observações

- As categorias **Action Figure** e **Outros** são criadas automaticamente na primeira execução
- O schema do banco é atualizado automaticamente via `ddl-auto=update`

---

## 👨‍💻 Autor

**Fares Mahmud** — Estudante de Sistemas de Informação

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/fares-mahmud-412693376)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white)](https://github.com/FaresMahmud)
