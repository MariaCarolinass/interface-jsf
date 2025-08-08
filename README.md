# Projeto Java EE - Sistema de Gestão de Pessoas com JSF e PostgreSQL (Payara + Docker)

## Descrição

Aplicação web desenvolvida em Java EE com JSF para interface, hospedada no servidor **Payara Server** dentro de container Docker, com banco de dados **PostgreSQL** também em container. A aplicação permite importar dados de arquivos Excel para o banco, visualizar e gerenciar pessoas com suporte a edição, exclusão e cadastro.

---

## Funcionalidades

- Interface web JSF para cadastro, listagem e manipulação dos dados de pessoas.
- Importação de dados via arquivo Excel para o banco PostgreSQL.
- Visualização dinâmica dos dados importados.
- Mensagens de validação, sucesso e erro na importação.
- Conexão configurada com PostgreSQL via datasource no Payara.
- Deploy simplificado usando Docker Compose com containers para Payara e PostgreSQL.

---

## Tecnologias Utilizadas

- Java EE 8
- JSF (JavaServer Faces)
- CDI (Context and Dependency Injection)
- Payara Server (servidor de aplicações em container Docker)
- PostgreSQL (em container Docker)
- Maven (gerenciamento de dependências)
- Apache POI (leitura de arquivos Excel)
- Docker e Docker Compose (para orquestração dos containers)

---

## Estrutura do Projeto

- `/src/main/java/com/projeto/aplicacao/jsf`: código Java com beans, serviços e modelos.
- `/src/main/webapp`: arquivos JSF (xhtml) e recursos estáticos.
- `Dockerfile` e `docker-compose.yml` configurados para deploy no Payara e PostgreSQL.

---

## Configuração do Ambiente

### Pré-requisitos

- Docker instalado e em execução.
- Docker Compose instalado.

### Como executar

1. Clone o projeto.

2. Construa e inicie os containers:

```bash
docker-compose up --build
```

3. O Payara Server ficará disponível na porta `8080` do localhost:

```
http://localhost:8080/
```

4. O PostgreSQL está disponível dentro do container na porta `5432`.

### Banco de Dados PostgreSQL

- Os dados do banco são persistidos em volume Docker (definido no docker-compose).
- Configure as credenciais e URL de conexão no arquivo `persistence.xml` para usar o datasource JNDI configurado no Payara.

### Datasource no Payara

- O datasource `jdbc/PostgresDS` é configurado automaticamente via script de inicialização no container Payara (`init-datasource.sh`).
- O container Payara se conecta ao container PostgreSQL pelo hostname definido no docker-compose (`postgres_db`) e porta 5432.

---

## Comandos úteis

```bash
# Subir containers e construir imagens
docker-compose up --build

# Subir containers em background (detached)
docker-compose up -d

# Parar e remover containers e volumes
docker-compose down -v

# Ver logs do container Payara
docker logs -f payara_app

# Acessar container Payara via terminal
docker exec -it payara_app bash

# Acessar container PostgreSQL via terminal
docker exec -it postgres_db psql -U seu_usuario -d seu_banco
```

---

## Uso

### Importar dados Excel

- Acesse a aplicação no navegador (`http://localhost:8080`).
- Use o formulário para importar arquivo Excel.
- Dados importados serão exibidos na listagem.

### Visualizar e gerenciar pessoas

- Acesse a lista de pessoas.
- Utilize os botões para editar, excluir ou adicionar registros.

---

## Contato

Dúvidas e sugestões: **Carolina Soares**

---

## Licença

Uso acadêmico e pessoal.