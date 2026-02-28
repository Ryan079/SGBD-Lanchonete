# SGBD-Lanchonete

## Contribuintes
- Ryan Oliveira Marques
- Thayson Guedes de Medeiros
- Luigi Mateo e Silva
- Lucas Henrique de Andrade Silva
- Felipe Mendes Soares Silva

## Instruções de Execução do Projeto
### Pré-requisitos
- Docker Desktop instalado
- Docker Compose
- Visual Studio Code (opcional)

⚠️ Caso exista uma instalação local do PostgreSQL em execução, recomenda-se **parar o serviço** para evitar conflito de portas com o Docker.

---

### Passo a passo para execução

1. Instale o **Docker Desktop** e conclua todas as etapas do instalador.

2. Clone este repositório ou faça o download dos arquivos do projeto.

3. Abra o terminal no diretório do projeto e execute:
```bash
docker-compose up -d
```

3.1 (Opcional) Caso utilize o **Visual Studio Code**:
   - Instale a extensão **Docker / Container Tools**.
   - Abra o arquivo `docker-compose.yml`.
   - Clique em **Run All Services** para iniciar os containers.

3.2 Portas de Acesso
- Após subir os containers, a aplicação estará disponível nas seguintes portas:
- Frontend: `http://localhost:4200`
- Backend:** `http://localhost:8080`
- Banco de Dados:** `localhost:5432`

## Modelo Conceitual

![Modelo Conceitual do projeto](imagens/modeloConceitual.png)

📄 [Versão em PDF](docs/Modelo%20Conceitual.pdf)

## Modelo Entidade Relacionamento

![Modelo Entidade Relacionamento do projeto](imagens/ER_dark.jpeg)

📄 [Versão em PDF](docs/ER_dark.pdf)

## Dicionário de Dados

📄 [Dicionário de Dados (PDF)](docs/Dicionário%20de%20Dados%20-%20Banco%20de%20Dados%20Att.pdf)

## DML
O banco de dados foi povoado através de comandos scripts contidos nos arquivos `01_ddl_criacao.sql` e `02_dml_povoamento.sql` executados automaticamente na primeira execução do container
