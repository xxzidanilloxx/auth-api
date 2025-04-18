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

# Subir o container MySQL
docker-compose up -d

# Instalar as dependências
mvn clean install

# Executar o projeto
mvn spring-boot:run
```

## 🗂️ Estrutura do Projeto
A estrutura foi organizada em camadas para facilitar a organização e seguir boas práticas no desenvolvimento.

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
