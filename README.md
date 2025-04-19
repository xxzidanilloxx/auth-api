# API de Autenticação de Usuários

Este projeto é uma API REST desenvolvida em **Java com Spring Boot**, que implementa um sistema de autenticação e autorização utilizando **JWT e Spring Security**. A aplicação permite o registro e login de usuários, garantindo a proteção das rotas por meio de filtros e validações.

## 🔧 Funcionalidades

- Registro de novos usuários
- Autenticação de usuários via login (usuário/senha)
- Geração e validação de tokens JWT
- Proteção de rotas com Spring Security
- Criptografia de senhas com BCrypt
- CRUD de parceiros
- Mapeamento Objeto-Relacional com JPA/Hibernate
- Integração com banco de dados PostgreSQL

## 💻 Tecnologias utilizadas

<div align="center">
    <a href="https://dev.java/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"></a>
    <a href="https://spring.io/projects/spring-boot" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white"></a>
    <a href="https://spring.io/projects/spring-security" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Spring%20Security-6DB33F.svg?style=for-the-badge&logo=Spring-Security&logoColor=white"></a>
    <a href="https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-boot-cli" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Spring%20CLI-6DB33F.svg?style=for-the-badge&logo=Spring&logoColor=white"></a>
    <a href="https://hibernate.org/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Hibernate-59666C.svg?style=for-the-badge&logo=Hibernate&logoColor=white"></a>
    <a href="https://maven.apache.org/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Apache%20Maven-C71A36.svg?style=for-the-badge&logo=Apache-Maven&logoColor=white"></a>
    <a href="https://jwt.io/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/JSON%20Web%20Tokens-000000.svg?style=for-the-badge&logo=JSON-Web-Tokens&logoColor=white"></a>
    <a href="https://www.postgresql.org/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/PostgreSQL-4169E1.svg?style=for-the-badge&logo=PostgreSQL&logoColor=white"></a>
    <a href="https://www.docker.com/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Docker-2496ED.svg?style=for-the-badge&logo=Docker&logoColor=white"></a>
</div>

## ⚙️ Como executar o projeto

Pré-requisitos: 
- Java 21
- Maven
- Docker e Docker Compose

```bash
# Clonar o repositório
git clone https://github.com/xxzidanilloxx/auth-api

# Acessar a pasta do projeto
cd auth-api

# Subir o container PostgreSQL
docker-compose up -d

# Instalar as dependências
mvn clean install

# Executar o projeto
mvn spring-boot:run
```

## 🔐 Configuração Inicial

> [!WARNING]
> Para possibilitar os cadastros futuros na aplicação, é necessário **inserir manualmente** um usuário com perfil `ADMIN` no banco de dados.  
> Este usuário terá permissão para registrar novos usuários e parceiros por meio da API.

### 👉 Gerando senha criptografada com Spring CLI

Utilize o comando a seguir no terminal para gerar o hash da senha utilizando o Spring CLI:

```bash
spring encodepassword admin
```

Ao digitar o comando `spring encodepassword` com a senha desejada, será gerado um hash similar a este:

```bash
$2a$10$KcC5gNtDPMv421F8CVNsjuGmrDXLcY97M0Ttr6rI4zA18H6wcX/Am
```

### 👉 Script SQL para inserção manual no banco de dados

Com o hash gerado, execute o seguinte comando SQL:

```SQL
INSERT INTO tb_users (user_login, user_password, user_role)
VALUES ('admin', '$2a$10$KcC5gNtDPMv421F8CVNsjuGmrDXLcY97M0Ttr6rI4zA18H6wcX/Am', 'ADMIN');
```

### 👉 Realizando o login com o usuário ADMIN

Após inserir o usuário administrador no banco de dados, já é possível realizar o login na aplicação.

Utilize o endpoint de login da API, passando as credenciais no corpo da requisição:

`POST localhost:8080/api/auth/login`

```JSON
{
  "login": "admin",
  "password": "admin"
}
```

Se as credenciais estiverem corretas, a API retornará um token JWT:
```JSON
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```

### 👉 Utilização do token

Este token deve ser enviado no cabeçalho das requisições futuras para acessar endpoints protegidos, utilizando o esquema Bearer:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR...
```

Dessa forma, a aplicação garante que apenas usuários autenticados com permissão adequada (como o perfil `ADMIN`) possam acessar rotas restritas.

> [!IMPORTANT]
> Esse procedimento é necessário **apenas na primeira execução da aplicação**, para criação do usuário administrador inicial.
> Após isso, novos cadastros poderão ser realizados por meio da API, desde que o usuário autenticado possua o perfil `ADMIN`.

## 🏁 Endpoints

Endpoints disponíveis para autenticação de usuários e gerenciamento de parceiros.

### 📝 `POST localhost:8080/api/auth/login`
Realiza o login do usuário e retorna um token JWT em caso de sucesso.

> [!IMPORTANT]
> Qualquer usuário pode acessar sem autenticação.

#### Exemplo do corpo da requisição (JSON):
```JSON
{
    "login": "admin",
    "password": "admin"
}
```

#### Demonstração do envio da requisição no Postman:
![post-login](https://github.com/user-attachments/assets/23afe11d-6e91-45eb-8458-1832280311d3)

### 📝 `POST localhost:8080/api/auth/register`
Cadastra um novo usuário com os dados fornecidos.

> [!IMPORTANT]
> Apenas usuários autenticados com perfil `ADMIN` podem registrar novos usuários.

#### Exemplo do corpo da requisição (JSON):
```JSON
{
    "login": "user",
    "password": "senha",
    "role": "USER"
}
```

#### Demonstração do envio da requisição no Postman:
![post-register](https://github.com/user-attachments/assets/e61ab174-40a2-406e-aadd-b932eacfb33c)

### 📝 `POST localhost:8080/api/partners`
Cadastra um novo parceiro com os dados fornecidos.

> [!IMPORTANT]
> Acessível apenas por usuários autenticados com perfil `ADMIN`.

#### Exemplo do corpo da requisição (JSON):
```JSON
{
    "name": "empresa",
    "email": "teste@teste.com",
    "website": "teste.com"
}
```

#### Demonstração do envio da requisição no Postman:
![post-partner](https://github.com/user-attachments/assets/1716e486-4ae4-4082-8dd5-a1f76b05bd05)

### 🔍 `GET localhost:8080/api/partners`
Lista todos os parceiros cadastrados.

> [!IMPORTANT]
> Acessível por qualquer usuário autenticado (`ADMIN` ou `USER`).

#### Demonstração do envio da requisição no Postman:
![get-partners](https://github.com/user-attachments/assets/bc51db42-96e6-46e9-8c16-7a0b4341b01e)

### ✏️ `PUT localhost:8080/api/partners/{id}`
Atualiza os dados de um parceiro cadastrado a partir do id fornecido.

> [!IMPORTANT]
> Acessível apenas por usuários autenticados com perfil `ADMIN`.

#### Exemplo do corpo da requisição (JSON):
```JSON
{
    "name": "empresa",
    "email": "teste@teste.com",
    "website": "teste.com.br"
}
```

#### Demonstração do envio da requisição no Postman:
![put-partner](https://github.com/user-attachments/assets/4c21a95d-9cb5-4c4e-a4e3-610044b85f82)

### ❌ `DELETE localhost:8080/api/partners/{id}`
Exclui os dados de um parceiro cadastrado a partir do id fornecido.

> [!IMPORTANT]
> Acessível apenas por usuários autenticados com perfil `ADMIN`.

#### Demonstração do envio da requisição no Postman:
![delete-partner](https://github.com/user-attachments/assets/8296a83f-268f-4fed-b29e-e1df0974171c)

## 🗂️ Estrutura do Projeto
A estrutura foi organizada em camadas para facilitar a manutenção e seguir boas práticas no desenvolvimento.

```plaintext
auth-api/
└── src/
    └── main/
        └── java/
            └── br.gov.sp.cps.api.pixel/
                ├── controller/
                │   ├── AuthenticationController
                │   └── PartnerController
                ├── dto/
                │   ├── AuthenticationRequestDTO
                │   └── LoginResponseDTO
                │   └── PartnerRequestDTO
                │   └── PartnerResponseDTO
                │   └── RegisterRequestDTO
                ├── entity/
                │   └── Partner
                │   └── User
                ├── enumeration/
                │   └── UserRole
                ├── mapper/
                │   └── PartnerMapper
                ├── repository/
                │   └── PartnerRepository
                │   └── UserRepository
                ├── security/
                │   ├── AuthorizationService
                │   └── SecurityConfiguration
                │   └── SecurityFilter
                │   └── TokenService
                ├── service/
                │   ├── AuthenticationService
                │   └── PartnerService
                └── AuthApiApplication.java
```

## 📄 Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para obter mais informações.

## 👤 Autor

Danillo Wesley da Costa Silva

https://www.linkedin.com/in/danillowesley
