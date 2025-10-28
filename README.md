# kronos-spring-api-sql

## Índice
- [📓 Sobre](#-sobre)
- [🚀 Tecnologias](#-tecnologias)
- [✨ Funcionalidades](#-funcionalidades)
- [⚙️ Instalação](#-instalação)
- [🧱 Estrutura do Projeto](#-estrutura-do-projeto)
- [📄 Licença](#-licença)
- [💻 Autores](#-autores)

</br>

## 📓 Sobre
Kronos-spring-api-sql é o backend (API REST) do sistema Kronos, desenvolvido com Spring Boot para fornecer uma robusta infraestrutura de gerenciamento de tarefas e recursos humanos. A API foi projetada com foco em segurança (JWT), performance (JPA) e escalabilidade, fornecendo endpoints RESTful para todas as operações necessárias da plataforma.

</br>

## 🚀 Tecnologias
- Backend:
    - Java (17)
    - Spring Boot (3.2.5)
    - Spring Security com JWT
    - Spring Data JPA
    - PostgreSQL
    - OpenAPI (Swagger)
    - Lombok
    - Maven

- Ferramentas e Build:
    - Maven
    - Java Development Kit (JDK) (17)

</br>

## ✨ Funcionalidades
- Autenticação e autorização com JWT
- Gestão completa de usuários e perfis
- Controle de cargos e setores
- Gerenciamento de habilidades
- Sistema de mensagens interno
- Administração de empresas
- Controle avançado de tarefas
- Sistema de logs para atribuições
- Gestão de planos de pagamento
- Sistema de vantagens
- Geração de relatórios
- API RESTful documentada

</br>

## ⚙️ Instalação
É necessário ter Java +17, Maven e PostgreSQL instalados.
```
# clonar o repositório
git clone https://github.com/Systems-Kronos/kronos-spring-api-sql.git

# entrar no diretório
cd kronos-spring-api-sql

# configure as variáveis de ambiente no arquivo '.env':
DATABASE_URL=jdbc:postgresql://localhost:5432/seu_banco
DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha
JWT_SECRET=seu_segredo_jwt

# execute o projeto:
mvn spring-boot:run

# o projeto será inicado em: http://localhost:8080
# a documentação estará disponível em: http://localhost:8080/swagger-ui.html
```

</br>


## 🧱 Estrutura do Projeto
```
kronos-spring-api-sql
├── /src
│   ├── /main
│   │   ├── /java/com/kronosapisql
│   │   │   ├── /config          # Configurações do projeto
│   │   │   ├── /controller      # Controladores REST
│   │   │   ├── /dto             # Objetos de Transferência de Dados
│   │   │   ├── /model           # Entidades JPA
│   │   │   ├── /repository      # Repositórios JPA
│   │   │   ├── /security        # Configurações de Segurança
│   │   │   └── /service         # Lógica de Negócios
│   │   └── /resources
│   │       └── application.properties
├── pom.xml                      # Dependências Maven
├── Dockerfile                   # Configuração Docker
└── README.md                    # Documentação
```

</br>

## 📄 Licença
Este projeto está licenciado sob a licença MIT — veja o arquivo LICENSE para mais detalhes.

</br>

## 💻 Autores
- [Carlos Perrud](https://github.com/CaduPerrudGerminare)
- [Yasmin Barbosa](https://github.com/yassbarbosa)
- [Matheus Hideki](https://github.com/Hideki1202)
