# Sistema de Inscrição em Eventos

## 📌 Descrição
O **Sistema de Inscrição em Eventos** 
é uma aplicação desenvolvida em **Java** com **Spring Boot**, projetada para facilitar o gerenciamento de eventos e inscrições de usuários. 
A solução permite a criação, consulta e controle de eventos, além da geração de relatórios detalhados sobre inscritos e indicações.

## 🚀 Tecnologias Utilizadas
- **Linguagem:** Java 17+
- **Framework:** Spring Boot
- **Banco de Dados:** MySQL
- **ORM:** Spring Data JPA
- **Validações:** Spring Validation
- **Migrations:** Flyway
- **Utilitários:** Lombok
- **Containerização:** Docker (opcional)

## ✨ Funcionalidades
- **Gerenciamento de Eventos:**
  - Cadastro, consulta e listagem de eventos
  - Busca por ID e URL
- **Inscrição de Usuários:**
  - Registro de participantes nos eventos
  - Controle de inscrições por indicação
- **Validação de Regras de Negócio:**
  - Limitação de inscrições
  - Garantia de integridade de dados
- **Relatórios Gerenciais:**
  - Total de inscritos por evento
  - Inscrições realizadas por indicação
  - Ranking de usuários mais ativos

## ⚙️ Configuração do Ambiente
### 1️⃣ Requisitos
- **JDK 17+**
- **Maven 3.8+**
- **MySQL Server**
- **Docker** (opcional, para execução do banco de dados)

### 2️⃣ Configuração do Banco de Dados
Para rodar o MySQL via Docker:
```sh
docker run --name mysql-eventos -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=eventos -p 3306:3306 -d mysql:latest
```

### 3️⃣ Configuração da Aplicação
Edite o arquivo `application.properties` ou `application.yml` conforme necessário:
```properties
spring.application.name=events
spring.datasource.username= root
spring.datasource.password= mysql
spring.datasource.url= jdbc:mysql://localhost:3306/db_events
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql= true
```

### 4️⃣ Executando a Aplicação
Para iniciar a aplicação, execute:
```sh
mvn spring-boot:run
```
Ou utilize sua IDE para rodar a classe principal `Application.java`.

## 📡 Endpoints da API
| Método | Endpoint               | Descrição                            |
|--------|------------------------|-------------------------------------|
| **GET**    | `/events`            | Lista todos os eventos             |
| **GET**    | `/events/{prettyName}`       | Busca evento por prettyName  |
| **GET**    | `/eventos/url/{url}`  | Busca evento por URL               |
| **POST**   | `/events`            | Cadastra um novo evento            |
| **POST**   | `/subscription/{prettyName}`         | Inscreve um usuário no evento      |
| **GET**    | `/subscription/{prettyName}/ranking` | Gera relatório de inscritos        |
| **GET**    | `/subscription/{prettyName}/ranking/{userId}` | Inscrições realizadas por indicação |

## 🤝 Contribuição
Contribuições são bem-vindas! Para colaborar:
1. Faça um **fork** do repositório
2. Crie uma **branch** para sua funcionalidade (`git checkout -b minha-feature`)
3. **Commit** suas alterações (`git commit -m 'Adiciona nova feature'`)
4. Envie para o repositório remoto (`git push origin minha-feature`)
5. Abra um **Pull Request**

## 📝 Licença
Este projeto está licenciado sob a **MIT License**. Para mais detalhes, consulte o arquivo `LICENSE`.

---

📌 **Desenvolvido com dedicação por Carlos Gomes** 🚀

