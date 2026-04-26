# Relatorio de Alteracoes no Projeto CRUD

Este arquivo resume tudo o que foi alterado no projeto desde o primeiro pedido deste chat, explicando o que foi feito e por que cada mudanca foi necessaria.

## 1. Objetivo inicial

O pedido inicial foi:

- criar a entidade `Categoria`
- implementar o CRUD completo de `Categoria`
- ajustar o CRUD de `Produto` para incluir o atributo `categoria`
- criar a relacao `ManyToOne` entre `Produto` e `Categoria`
- atualizar o formulario de `Produto` para selecionar uma categoria via `<select>`
- atualizar a listagem de produtos para exibir a categoria associada
- manter organizacao em camadas: `Controller`, `Service` e `Repository`

## 2. Analise inicial do projeto

Primeiro foi feita uma leitura da estrutura do projeto para entender o que ja existia.

Foi identificado que:

- o projeto ja possuia CRUD basico de `Produto`
- ja existia uma classe `Categoria`, mas incompleta
- o `Produto` ja tinha um campo `categoria` com anotacoes JPA, mas a aplicacao ainda nao tinha fluxo completo para usar isso
- o `ProdutoController` acessava o `Repository` diretamente em alguns pontos
- nao existiam `CategoriaController`, `CategoriaService`, `CategoriaRepository` nem paginas HTML de categoria

Motivo dessa etapa:

- evitar alterar o projeto sem entender a estrutura real
- adaptar a solucao ao codigo existente em vez de reescrever tudo sem necessidade

## 3. Estruturacao completa da entidade Categoria

### Arquivo alterado

- `src/main/java/com/example/crud/model/Categoria.java`

### O que foi feito

- foi mantida a entidade `Categoria`
- foi adicionado `@Data` do Lombok
- foi definido `id` como chave primaria com geracao automatica
- foi configurado o atributo `nome` com restricoes:
  - obrigatorio
  - unico
  - tamanho maximo de 100 caracteres
- foram adicionados construtores

### Por que foi feito

- a entidade precisava estar pronta para persistencia correta no banco
- o atributo `nome` precisava seguir regras basicas de integridade
- os construtores facilitaram a criacao manual de categorias em outros pontos do projeto

## 4. Criacao do repository de Categoria

### Arquivo criado

- `src/main/java/com/example/crud/repository/CategoriaRepository.java`

### O que foi feito

- foi criado `CategoriaRepository` extendendo `JpaRepository<Categoria, Long>`

### Por que foi feito

- o CRUD de categoria precisava de acesso ao banco
- `JpaRepository` ja fornece operacoes basicas como salvar, listar, buscar por id e excluir

## 5. Criacao do service de Categoria

### Arquivo criado

- `src/main/java/com/example/crud/service/CategoriaService.java`

### O que foi feito

- foi criada a camada de servico da categoria com os metodos:
  - `salvar`
  - `listarTodas`
  - `buscarPorId`
  - `excluir`
- foi adicionada validacao para impedir nome vazio

### Por que foi feito

- a atividade pedia organizacao em camadas
- regras de negocio devem ficar no service, nao no controller
- isso melhora manutencao e clareza da arquitetura

## 6. Criacao do controller de Categoria

### Arquivo criado

- `src/main/java/com/example/crud/controller/CategoriaController.java`

### O que foi feito

- foi criado o controller web com rotas para:
  - abrir formulario
  - salvar categoria
  - listar categorias
  - editar categoria
  - excluir categoria

### Rotas adicionadas

- `/categoria/formulario`
- `/categoria/salvar`
- `/categoria/listar`
- `/categoria/editar/{id}`
- `/categoria/deletar/{id}`

### Por que foi feito

- a entidade `Categoria` precisava de CRUD completo
- era necessario permitir operacoes pela interface HTML, nao apenas pelo banco

## 7. Criacao das paginas HTML de Categoria

### Arquivos criados

- `src/main/resources/templates/categoria-formulario.html`
- `src/main/resources/templates/categoria-lista.html`

### O que foi feito

- foi criada uma pagina para cadastro e edicao de categoria
- foi criada uma pagina para listagem e exclusao de categoria
- foram adicionados links de navegacao entre categorias e produtos

### Por que foi feito

- a atividade exigia paginas HTML para cadastro, listagem, edicao e exclusao
- o CRUD precisava estar acessivel pela interface do sistema

## 8. Ajuste da entidade Produto

### Arquivo alterado

- `src/main/java/com/example/crud/model/Produto.java`

### O que foi feito

- foi mantida a relacao:
  - `@ManyToOne`
  - `@JoinColumn(name = "categoria_id")`
- o tipo do campo `id` foi alterado de `int` para `Integer`

### Por que foi feito

- a relacao com categoria precisava estar corretamente mapeada no JPA
- usar `Integer` em vez de `int` e mais adequado para entidades JPA porque o id pode ser `null` antes de persistir

## 9. Reestruturacao do service de Produto

### Arquivo recriado

- `src/main/java/com/example/crud/service/ProdutoService.java`

### O que foi feito

- o service passou a receber:
  - `ProdutoRepository`
  - `CategoriaRepository`
- foram mantidas validacoes de produto
- foi adicionada validacao para exigir categoria
- antes de salvar, a categoria selecionada no formulario passou a ser buscada no banco
- foram adicionados metodos:
  - `listarTodos`
  - `buscarPorId`
  - `excluir`

### Por que foi feito

- o formulario envia apenas o `id` da categoria
- era preciso transformar esse `id` em uma entidade `Categoria` valida antes de salvar o produto
- o controller nao deveria acessar o repository diretamente

## 10. Reestruturacao do controller de Produto

### Arquivo recriado

- `src/main/java/com/example/crud/controller/ProdutoController.java`

### O que foi feito

- o controller passou a depender de:
  - `ProdutoService`
  - `CategoriaService`
- no formulario de produto, passou a enviar a lista de categorias para a view
- na edicao de produto, tambem passou a enviar as categorias
- a listagem e exclusao passaram a usar o service em vez do repository direto

### Por que foi feito

- a tela precisava da lista de categorias para montar o `<select>`
- a arquitetura em camadas exigia reduzir acesso direto ao repository dentro do controller

## 11. Atualizacao do formulario de Produto

### Arquivo recriado

- `src/main/resources/templates/formulario.html`

### O que foi feito

- o formulario foi mantido para cadastro de produto
- foi adicionado um campo `<select>` para categoria
- o binding do Thymeleaf foi configurado com:
  - `th:field="*{categoria.id}"`
- a lista de categorias passou a ser exibida dinamicamente com `th:each`

### Por que foi feito

- a atividade exigia que o produto pudesse ser associado a uma categoria ja cadastrada
- o uso de `<select>` no Thymeleaf era um requisito direto do enunciado

## 12. Atualizacao da listagem de Produto

### Arquivo recriado

- `src/main/resources/templates/lista.html`

### O que foi feito

- foi adicionada a coluna `Categoria`
- passou a ser exibido o nome da categoria associada ao produto
- foram adicionados links para navegar tambem para a area de categorias

### Por que foi feito

- o enunciado exigia mostrar a categoria na listagem de produtos
- isso comprova visualmente que a associacao esta funcionando

## 13. Validacao da aplicacao

### Comando executado

- `.\mvnw.cmd test`

### Resultado

- build com sucesso
- contexto Spring iniciado corretamente
- repositories reconhecidos
- aplicacao consistente apos as alteracoes

### Por que foi feito

- era necessario confirmar que as alteracoes nao quebraram a compilacao
- isso valida minimamente a integracao entre controller, service, repository e entidades

## 14. Esclarecimento sobre a rota /listar

Depois, surgiu a duvida de que `http://localhost:8080/listar` nao funcionava.

### O que foi identificado

- o sistema nao tinha rota `/listar`
- a rota correta era:
  - `/produto/listar`

### Por que isso acontece

- o `ProdutoController` possui `@RequestMapping("/produto")`
- por isso o endpoint final da listagem fica com o prefixo `/produto`

### O que foi respondido

- foi explicado que o problema nao era a logica do CRUD, e sim a URL acessada

## 15. Explicacao detalhada para apresentacao

Em seguida, foi preparado um passo a passo textual para ajudar na explicacao ao professor.

### O que foi entregue

- descricao de cada camada criada
- explicacao da relacao `ManyToOne`
- explicacao do funcionamento do `<select>` com Thymeleaf
- explicacao do fluxo de salvamento do produto com categoria

### Por que foi feito

- o usuario pediu apoio para conseguir apresentar e justificar tecnicamente as mudancas

## 16. Inclusao automatica de categorias padrao

Depois, foi pedido para adicionar as categorias:

- `Action Figure`
- `Outros`

### Arquivos alterados ou criados

- `src/main/java/com/example/crud/repository/CategoriaRepository.java`
- `src/main/java/com/example/crud/config/CategoriaDataLoader.java`

### O que foi feito

- foi adicionado ao repository o metodo:
  - `existsByNome(String nome)`
- foi criado um `CommandLineRunner` para inserir automaticamente:
  - `Action Figure`
  - `Outros`
- antes de inserir, o sistema verifica se a categoria ja existe

### Por que foi feito

- o usuario pediu para criar essas categorias
- foi escolhida uma solucao automatica para evitar cadastro manual
- a verificacao por nome impede duplicacao cada vez que a aplicacao reinicia

## 17. Nova validacao

### Comando executado

- `.\mvnw.cmd test`

### Resultado

- build com sucesso novamente

### Por que foi feito

- confirmar que a carga automatica de categorias nao introduziu erro de compilacao ou inicializacao

## 18. Resumo geral das mudancas

Em termos práticos, o projeto passou a ter:

- CRUD completo de `Categoria`
- integracao real entre `Produto` e `Categoria`
- selecao de categoria no formulario de produto
- exibicao da categoria na listagem de produtos
- melhor separacao entre controller, service e repository
- duas categorias padrao carregadas automaticamente no inicio da aplicacao

## 19. Resultado final esperado no sistema

Apos todas as alteracoes, o sistema passou a permitir:

- cadastrar categorias
- editar categorias
- excluir categorias
- listar categorias
- cadastrar produtos com categoria
- editar produtos mantendo ou trocando categoria
- listar produtos exibindo a categoria associada

## 20. Observacao importante para uso

As rotas principais do sistema ficaram:

- `/produto/listar`
- `/produto/formulario`
- `/categoria/listar`
- `/categoria/formulario`

Se a aplicacao for reiniciada, as categorias `Action Figure` e `Outros` serao inseridas automaticamente se ainda nao existirem.
