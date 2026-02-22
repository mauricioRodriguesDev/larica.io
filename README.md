# Larica.io API 🍽️

![Java CI with Maven](https://github.com/mauricioRodriguesDev/larica.io/actions/workflows/build.yml/badge.svg)

Projeto de backend para um agregador de descoberta de comida, desenvolvido com foco em boas práticas de engenharia de software, escalabilidade e manutenibilidade. A API atua como um curador inteligente, sugerindo categorias de comida com base em fatores contextuais como clima e período do dia.

Este projeto foi construído como um estudo de caso prático, demonstrando a aplicação de uma stack de tecnologia moderna e a implementação de um fluxo de desenvolvimento profissional.

## ✨ Decisões de Arquitetura e Boas Práticas

- **API RESTful**: Design de API seguindo os princípios REST para a manipulação de recursos.
- **Banco de Dados Relacional com PostgreSQL**: Escolhido pela robustez, confiabilidade e recursos avançados.
- **Migrações com Flyway**: Garante um versionamento de banco de dados consistente e automatizado entre todos os ambientes.
- **Injeção de Dependência e Inversão de Controle**: Utilização dos princípios do Spring para um código desacoplado e testável.
- **Padrão DTO (Data Transfer Object)**: Separação clara entre as entidades de domínio (JPA) e os objetos expostos pela API, com mapeamento automatizado via **MapStruct**.
- **Tratamento de Exceções Centralizado**: Uso de `@RestControllerAdvice` para criar respostas de erro padronizadas e informativas.
- **Validação de Dados**: Implementada com `spring-boot-starter-validation` para garantir a integridade dos dados na camada de entrada da API.
- **Paginação**: Endpoints de listagem utilizam o `Pageable` do Spring Data para garantir performance e escalabilidade.
- **Segurança de Credenciais**: Chaves de API e outros segredos são carregados a partir de variáveis de ambiente, e não "hard-coded" no código-fonte.
- **Testes Automatizados**:
  - **Testes de Unidade**: Foco na lógica de negócio crítica, com dependências mockadas usando **Mockito**.
  - **Testes de Integração**: Validação do fluxo completo da API (Controller -> Service -> DB) usando **`@SpringBootTest`** e **`MockMvc`**.
- **CI/CD com GitHub Actions**: Pipeline de integração contínua que compila, testa e valida o projeto a cada `push`, garantindo a estabilidade do código.

## 🛠️ Stack Tecnológica

- **Linguagem**: Java 17
- **Framework**: Spring Boot 3.x (Web, Data JPA, Validation)
- **Banco de Dados**: PostgreSQL
- **Migrações**: Flyway
- **Testes**: JUnit 5, Mockito
- **Documentação**: Springdoc (Swagger UI)
- **Build**: Maven

## 🏁 Como Rodar o Projeto

1.  **Pré-requisitos**:
    - Java (JDK) 17+
    - Maven
    - Docker

2.  **Clone o Repositório**
    ```bash
    git clone https://github.com/mauricioRodriguesDev/larica.io.git
    cd larica.io
    ```

3.  **Inicie o Banco de Dados com Docker**
    ```bash
    docker run --name larica-postgres -e POSTGRES_PASSWORD=minhasenha -e POSTGRES_DB=larica_db -p 5432:5432 -d postgres
    ```

4.  **Configure as Variáveis de Ambiente**
    O projeto precisa de uma chave de API para o OpenWeatherMap. A forma mais segura é configurar uma variável de ambiente no seu sistema:
    ```bash
    export OPENWEATHERMAP_API_KEY="sua_chave_aqui"
    ```
    Além disso, ajuste a senha do banco no arquivo `src/main/resources/application.properties` para corresponder à definida no comando Docker.
    ```properties
    spring.datasource.password=minhasenha
    ```

5.  **Execute a Aplicação**
    ```bash
    mvn spring-boot:run
    ```

## 📖 Documentação da API

Com a aplicação rodando, a documentação interativa da API (Swagger UI) está disponível em:

[**http://localhost:8080/swagger-ui.html**](http://localhost:8080/swagger-ui.html)

Autor: [mauricioRodriguesDev](https://github.com/mauricioRodriguesDev)
Linkedin: [Mauricio Rodrigues](https://www.linkedin.com/in/mauriciorodrigues-exe/)
