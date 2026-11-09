# 🎧 Central de Suporte Acadêmico em Java

Sistema de gerenciamento de chamados de suporte de TI em ambiente acadêmico, desenvolvido em Java como projeto prático de estudos em desenvolvimento Backend, Programação Orientada a Objetos (POO) e Banco de Dados. O cenário simula o suporte a plataformas como Moodle, Lyceum, Zoom e Teams.

## 🚀 Funcionalidades

* Listagem de chamados com a categoria de cada um
* Abertura de chamados
* Atualização de status (aberto, em andamento, resolvido)
* Validação das entradas do usuário
* Validação de usuário e categoria existentes
* Relatório de chamados por categoria
* Resumo de chamados por status

## 🛠️ Tecnologias

* Java
* SQLite
* JDBC (driver `sqlite-jdbc`)
* Eclipse IDE
* DB Browser for SQLite

## 🗄️ Modelo do banco de dados

* `usuario` (id, nome, email, perfil)
* `categoria` (id, nome)
* `chamado` (id, titulo, descricao, status, data_abertura, usuario_id, categoria_id)

Um usuário abre vários chamados e uma categoria agrupa vários chamados (relacionamentos 1:N ligados por chaves estrangeiras).

## 📚 Conceitos praticados

* Programação Orientada a Objetos
* Encapsulamento
* Construtores, getters e `toString`
* `ArrayList`
* `Scanner`
* Conexão com banco de dados via JDBC
* `PreparedStatement` (prevenção de SQL Injection)
* SQL: `INNER JOIN`, `LEFT JOIN`, `GROUP BY`, `COUNT`
* Integridade de dados: PK, FK, `UNIQUE`, `NOT NULL` e `CHECK`
* Validação de dados
* Tratamento de exceções

## ▶️ Como executar

1. Clone este repositório.
2. Baixe o driver `sqlite-jdbc` e adicione ao Build Path do projeto.
3. Mantenha o arquivo `suporte.db` na raiz do projeto.
4. Abra o projeto em uma IDE Java, como Eclipse.
5. Execute a classe `Menu`.
6. Utilize o menu apresentado no console.

## 🎯 Objetivo

Projeto desenvolvido para consolidar conhecimentos de Java, POO e SQL, praticando a integração entre uma aplicação e um banco de dados relacional, com regras de integridade e relatórios.

## 🔄 Próximos passos

Como evolução do projeto, futuramente poderão ser aplicados conceitos de interface web, API REST, integração com o Moodle via web services e histórico de alterações dos chamados.

## 👨‍💻 Vinicius Severio

[GitHub](https://github.com/ViniciusSeverio) • [LinkedIn](https://www.linkedin.com/in/vinicius-sousa-644b32214/)
