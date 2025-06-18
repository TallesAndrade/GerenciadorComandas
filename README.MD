# Gerenciador de Comandas

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring](https://img.shields.io/badge/Spring_Boot-3.5.0-green?style=for-the-badge&logo=spring)
![Status](https://img.shields.io/badge/Status-Em_Desenvolvimento-yellow?style=for-the-badge)

Sistema de gerenciamento de comandas e estoque construído com Spring Boot. Esta API permite o cadastro, consulta, atualização e exclusão lógica de produtos, seus respectivos estoques e comandas de clientes.

---

## 📋 Índice

- [🔎 Visão Geral](#-visão-geral)
- [🚀 Principais Funcionalidades](#-principais-funcionalidades)
- [💻 Tecnologias](#-tecnologias)
- [🔧 Instalação e Configuração](#-instalação-e-configuração)
- [📝 Uso da API](#-uso-da-api)
- [🗃️ Estrutura do Banco de Dados](#️-estrutura-do-banco-de-dados)
- [🧪 Testes Automatizados](#-testes-automatizados)
- [🔮 Implementações Futuras](#-implementações-futuras)
- [📚 Documentação da API](#-documentação-da-api)

---

## 🔎 Visão Geral

O Gerenciador de Comandas é um sistema de backend para o controle de estabelecimentos comerciais. Ele permite registrar produtos, controlar a quantidade em estoque e gerenciar o ciclo de vida das comandas dos clientes, desde a abertura até o fechamento ou cancelamento.

---

## 🚀 Principais Funcionalidades

### 🛍️ Gerenciamento de Produtos

- Cadastro de produtos com nome, preço e código de barras.
- Atualização e inativação (soft delete).
- Listagem de todos os produtos ativos.
- Consulta por ID.

### 📦 Controle de Estoque

- Criação automática de controle de estoque ao cadastrar um produto.
- Consulta, adição e subtração de saldo.
- Validações de saldo ao manipular comandas.

### 📋 Gestão de Comandas

- Abertura de comanda para clientes.
- Adição, edição e remoção de produtos.
- Cálculo automático do valor total.
- Alteração de status (ABERTA, FECHADA, CANCELADA).
- Devolução automática de estoque ao cancelar uma comanda.

---

## 💻 Tecnologias

- **Java 17**
- **Spring Boot 3.5.0**
- **Spring Data JPA**
- **MapStruct**
- **H2 Database**
- **Lombok**
- **Swagger/OpenAPI (springdoc)**
- **Maven**

---

## 🔧 Instalação e Configuração

### Pré-requisitos

- Java 17+
- Maven 3.6.3+

### Passos

```bash
git clone https://github.com/TallesAndrade/GerenciadorComandas.git
cd GerenciadorComandas
mvn clean install
mvn spring-boot:run
```

Acesse a aplicação em: `http://localhost:8080`

---

## 📝 Uso da API

### Endpoints de Produto

| Método  | Rota             | Descrição                           |
|---------|------------------|-------------------------------------|
| `POST`  | `/produtos`      | Cadastra um novo produto.           |
| `GET`   | `/produtos`      | Lista todos os produtos ativos.     |
| `GET`   | `/produtos/{id}` | Busca um produto por ID.            |
| `PATCH` | `/produtos/{id}` | Atualiza um produto existente.      |
| `DELETE`| `/produtos/{id}` | Inativa um produto (soft delete).   |

### Endpoints de Estoque

| Método  | Rota                         | Descrição                                  |
|---------|------------------------------|--------------------------------------------|
| `GET`   | `/estoque`                   | Lista todo o estoque.                      |
| `GET`   | `/estoque/{id}`              | Consulta o saldo de um produto.            |
| `PATCH` | `/estoque/{id}/adicionarsaldo` | Adiciona saldo ao estoque.                 |
| `PATCH` | `/estoque/{id}/removersaldo`  | Remove saldo do estoque.                   |
| `DELETE`| `/estoque/{id}`              | Remove o registro (se saldo for zero).     |

### Endpoints de Comanda

| Método  | Rota                        | Descrição                                     |
|---------|-----------------------------|-----------------------------------------------|
| `POST`  | `/comandas`                 | Cria uma nova comanda para um cliente.        |
| `POST`  | `/comandas/{id}/produtos`   | Adiciona produto à comanda.                   |
| `PATCH` | `/comandas/{id}/itens`      | Edita a quantidade de um produto.             |
| `DELETE`| `/comandas/{id}/itens/{id}` | Remove produto da comanda.                    |
| `PATCH` | `/comandas/{id}/status`     | Altera status para FECHADA ou CANCELADA.      |
| `GET`   | `/comandas/{id}`            | Busca comanda por ID.                         |
| `GET`   | `/comandas`                 | Lista comandas com status ABERTA.             |

---

## 🗃️ Estrutura do Banco de Dados

Gerenciada automaticamente com Spring Data JPA. Tabelas principais:

- `db_produtos` – Produtos cadastrados
- `db_estoque` – Controle de estoque
- `db_comandas` – Registro de comandas
- `db_produto_comanda` – Relação produto ↔ comanda

---

## 🧪 Testes Automatizados

O projeto inclui testes unitários com **JUnit 5** e **Mockito**, cobrindo:

- Cadastro de produtos
- Criação de estoque
- Listagem de produtos
- Retorno por ID

✅ Cobertura de testes está em expansão para comandas e estoque.

---

## 🔮 Implementações Futuras

- **Spring Security + JWT** para autenticação.
- **Relatórios em PDF** (comandas fechadas e estoque baixo).
- **Migração para PostgreSQL** no ambiente de produção.
- **Cobertura total de testes unitários e integração**.
- **Finalização da suíte completa de testes**.

---

## 📚 Documentação da API

Acesse após iniciar a aplicação:

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- Console H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    - JDBC URL: `jdbc:h2:file:./data/gerenciador-comandas-db`
    - Usuário: `sa`
    - Senha: `password`

---

> Projeto criado com foco em aprendizado e boas práticas. Em constante evolução. 🚧