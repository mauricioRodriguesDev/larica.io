# Larica.io API 🍽️

![Java CI with Maven](https://github.com/mauricioRodriguesDev/larica.io/actions/workflows/build.yml/badge.svg)

**Larica.io** é o backend de um agregador de descoberta de comida focado em resolver o clássico problema da "paralisia de escolha". A API atua como um curador inteligente, sugerindo categorias de comida que combinam com o clima atual e o período do dia.

## 🚀 Funcionalidades Principais

- **Recomendações Inteligentes**: O endpoint principal (`/api/v1/recomendacoes`) consulta uma API de clima externa (OpenWeatherMap) e, com base no clima atual (ex: "Chuva") e no período do dia (ex: "NOITE"), sugere categorias de comida apropriadas (ex: "Pizza", "Massas").
- **Gerenciamento de Restaurantes**: API REST completa para gerenciar restaurantes, incluindo operações de Criar, Ler, Atualizar e Deletar (CRUD).
- **Paginação**: As listas de restaurantes são paginadas para garantir performance e escalabilidade.
- **Validação de Dados**: A entrada de dados é validada para garantir a integridade e a qualidade das informações no banco de dados.
- **Tratamento de Erros Centralizado**: Respostas de erro padronizadas e informativas para uma melhor experiência de desenvolvimento.

## 🛠️ Stack Tecnológica

- **Linguagem**: Java 17
- **Framework**: Spring Boot 3.x
  - **Módulos**: Spring Web, Spring Data JPA, Spring Validation
- **Banco de Dados**: PostgreSQL
- **Migrações de Banco**: Flyway
- **Mapeamento de Objetos**: MapStruct
- **Documentação da API**: Springdoc (Swagger UI)
- **Build & Dependências**: Maven
- **CI/CD**: GitHub Actions

## ⚙️ Pré-requisitos

Para rodar este projeto localmente, você precisará ter instalado:

- [Java (JDK) 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop/) (para rodar o banco de dados PostgreSQL)

## 🏁 Como Rodar o Projeto

1.  **Clone o Repositório**
    ```bash
    git clone https://github.com/mauricioRodriguesDev/larica.io.git
    cd larica.io
    ```

2.  **Inicie o Banco de Dados com Docker**
    Execute o comando abaixo no seu terminal para iniciar um container Docker com o PostgreSQL já configurado:
    ```bash
    docker run --name larica-postgres -e POSTGRES_PASSWORD=minhasenha -e POSTGRES_DB=larica_db -p 5432:5432 -d postgres
    ```
    *(**Nota**: Se a porta 5432 já estiver em uso, você pode alterá-la, por exemplo: `-p 5433:5432`)*

3.  **Configure as Variáveis de Ambiente**
    O projeto precisa de uma chave de API para o serviço OpenWeatherMap.

    - **(Recomendado)** Crie um arquivo na raiz do projeto chamado `.env` e adicione a seguinte linha:
      ```
      OPENWEATHERMAP_API_KEY=sua_chave_aqui
      ```
      *(O `.gitignore` já está configurado para ignorar este arquivo, mantendo seu segredo seguro).*

    - **(Alternativa)** Você pode alterar diretamente o arquivo `src/main/resources/application.properties`, mas lembre-se de não commitar sua chave.

4.  **Ajuste a Senha do Banco**
    Verifique se a senha no arquivo `src/main/resources/application.properties` corresponde à que você definiu no comando Docker (`minhasenha` no exemplo acima).
    ```properties
    spring.datasource.password=minhasenha
    ```

5.  **Execute a Aplicação**
    Use o Maven para compilar e rodar o projeto:
    ```bash
    mvn spring-boot:run
    ```
    A aplicação estará disponível em `http://localhost:8080`.

## 📖 Documentação da API (Swagger)

Com a aplicação rodando, você pode acessar a documentação interativa da API (Swagger UI) no seguinte endereço:

[**http://localhost:8080/swagger-ui.html**](http://localhost:8080/swagger-ui.html)

A partir desta interface, você pode explorar e testar todos os endpoints disponíveis.

### Principais Endpoints

- `GET /api/v1/recomendacoes`: Retorna uma lista de categorias de comida sugeridas.
- `GET /api/v1/restaurantes`: Retorna uma lista paginada de todos os restaurantes.
- `POST /api/v1/restaurantes`: Cria um novo restaurante.
- `PUT /api/v1/restaurantes/{id}`: Atualiza um restaurante existente.
- `DELETE /api/v1/restaurantes/{id}`: Deleta um restaurante.

## 🔄 CI/CD com GitHub Actions

Este projeto está configurado com um pipeline de Integração Contínua usando GitHub Actions (`.github/workflows/build.yml`). A cada `push` ou `pull request` para as branches `main` e `develop`, o pipeline irá automaticamente:

1.  Fazer o checkout do código.
2.  Configurar o ambiente Java 17.
3.  Compilar o projeto com o Maven.
4.  Executar todos os testes de unidade e integração.

Isso garante a qualidade e a estabilidade do código antes que ele seja integrado à branch principal.
