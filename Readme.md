# Login Java-Angular

## 📄 Descrição

**Login Java-Angular** é uma aplicação web full-stack desenvolvida para gerenciar autenticação de usuários de forma segura e eficiente. Com uma interface moderna construída em Angular e um backend robusto em Spring Boot, o projeto integra-se perfeitamente com um banco de dados PostgreSQL. A documentação da API é fornecida via Swagger UI, facilitando a interação e o teste das funcionalidades disponíveis.

## 🚀 Funcionalidades

- **Autenticação de Usuários:**
  - Cadastro de novos usuários.
  - Login seguro com autenticação básica.
  - Gestão de sessões e tokens de acesso.

- **Dashboard:**
  - Interface intuitiva para gerenciamento de dados.
  - Visualização de informações em tempo real.

- **Documentação da API:**
  - Acesso completo à documentação interativa via Swagger UI.
  - Testes diretos das endpoints da API.

- **Segurança:**
  - Proteção contra ataques CSRF.
  - Configurações de CORS para comunicação segura entre frontend e backend.

## 🛠️ Tecnologias Utilizadas

- **Frontend:**
  - [Angular](https://angular.io/) - Framework para construção de interfaces web.

- **Backend:**
  - [Spring Boot](https://spring.io/projects/spring-boot) - Framework para desenvolvimento de aplicações Java.
  - [Spring Security](https://spring.io/projects/spring-security) - Módulo para autenticação e autorização.
  - [Springdoc OpenAPI 3](https://springdoc.org/) - Integração do Swagger UI para documentação da API.

- **Banco de Dados:**
  - [PostgreSQL](https://www.postgresql.org/) - Sistema de gerenciamento de banco de dados relacional.

- **Containerização:**
  - [Docker](https://www.docker.com/) - Plataforma para desenvolvimento, envio e execução de aplicações em containers.
  - [Docker Compose](https://docs.docker.com/compose/) - Ferramenta para definir e executar aplicações multi-container.

## 🔒 Métodos de Segurança

- **Autenticação e Autorização:**
  - Utilização do Spring Security para gerenciar autenticação de usuários e controlar o acesso às endpoints da API.
  
- **Proteção contra CSRF:**
  - Configurações específicas para desabilitar CSRF em ambientes de desenvolvimento, com recomendações para habilitação em produção.

- **Configuração de CORS:**
  - Definição de políticas de CORS para permitir comunicação segura entre o frontend e o backend.

## 📚 Documentação da API

A documentação da API está disponível via Swagger UI, proporcionando uma interface interativa para explorar e testar as endpoints da aplicação.

### 📌 Acesso à Documentação
```
http://localhost:8080/swagger-ui/index.html
```
### 📌 Rotas Principais da API

- **Autenticação:**
- `POST /auth/register` - Cadastro de novos usuários.
- `POST /auth/login` - Login de usuários existentes.

- **Usuários:**
- `GET /users` - Listar todos os usuários (requer autenticação).
- `GET /users/{id}` - Obter detalhes de um usuário específico (requer autenticação).
- `PUT /users/{id}` - Atualizar informações de um usuário (requer autenticação).
- `DELETE /users/{id}` - Remover um usuário (requer autenticação).

- **Dashboard:**
- `GET /dashboard/data` - Obter dados para o dashboard (requer autenticação).

## 🛠️ Como Iniciar o Projeto

### 📦 Pré-requisitos

- [Docker](https://www.docker.com/get-started) instalado na sua máquina e o mesmo deve estar sendo executado.
- [Docker Compose](https://docs.docker.com/compose/install/) instalado.

### 🔧 Passos para Iniciar com Docker

1. **Clonar o Repositório:**

```
 git clone https://github.com/RamonDFerreira/Login---Java-Angular.git
 cd Login---Java-Angular
 docker-compose up --build
```

2. **Acessar a Aplicação:**

- Frontend (Angular via Nginx):

  - Abra o navegador e acesse:
```
http://localhost
```
- Backend (API Spring Boot):

  - A documentação da API estará disponível em:
```
http://localhost:8080/swagger-ui/index.html
```
