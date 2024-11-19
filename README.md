# Kanban – API REST para Gerenciamento de Tarefas

## Sobre o Projeto

Este é um sistema de gerenciamento de tarefas baseado no modelo Kanban. A API REST desenvolvida permite a autenticação para criação, atualização, movimentação e exclusão de tarefas organizadas em uma tabela gerada pelo terminal do console do projeto, que exibe as tarefas em linhas e colunas detalhadas e ordenadas por status e prioridades.

As tarefas podem ser movidas entre essas colunas de acordo com o progresso, permitindo uma gestão simples e eficiente:

	•	A Fazer
 	•	Em Progresso
  	•	Concluído

## Principais Tecnologias

	•	Java com Spring Boot
	•	Banco de dados H2 (em memória)
	•	Postman para testes de API
	•	JPA (Java Persistence API) para persistência e manipulação de dados
	•	Java Web para requisições HTTP

## Funcionalidades

### Requisitos Básicos

	•	Cadastro de Tarefas.
	•	Título: obrigatório.
	•	Descrição: opcional.
	•	Data de criação: gerada automaticamente.
	•	Status: “A Fazer”, “Em Progresso” ou “Concluído”.
	•	Prioridade: Baixa, Média ou Alta.
	•	Data limite: opcional.
	
##### Gerenciamento de Tarefas:

 	•	Adicionar novas tarefas na coluna “A Fazer”.
	•	Listar todas as tarefas organizadas por colunas.
	•	Mover tarefas entre colunas seguindo a sequência A Fazer → Em Progresso → Concluído.
	•	Editar informações das tarefas, como título, descrição e prioridade.
	•	Excluir tarefas.

### Funcionalidades Avançadas

	•	Ordenação: tarefas em cada coluna podem ser organizadas por prioridade.
	•	Filtragem: tarefas podem ser filtradas por prioridade ou data limite.
	•	Relatórios: geração de um relatório que destaca tarefas atrasadas e organiza por coluna.
 	•	Autenticação com JWT para manipular as tarefas.

### Regras do Sistema

	•	Novas tarefas só podem ser adicionadas na coluna “A Fazer”.
	•	A movimentação de tarefas deve seguir a ordem: A Fazer → Em Progresso → Concluído.
	•	O campo “título” é obrigatório, já a descrição e a data limite são opcionais.

## Endpoints da API

Tarefas (Porta: 8090)

### Criar usuário:
	•	POST /auth - Cria um novo usuário. 

### Efetuar login:
	•	POST /auth/login - Faz login com credenciais do usuário e recebe um token para autorizar as requisições seguintes.

### Criar tarefa:
	•	POST /tasks - Adiciona uma nova tarefa na coluna “A Fazer”.

### Listar tarefas:
	•	GET /tasks - Retorna todas as tarefas.

### Mover tarefa para o próximo status:
	•	PUT /tasks/{id}/move - Atualiza o status da tarefa para o próximo status, na seguinte ordem: A Fazer -> Em Progresso -> Concluído.

### Atualizar uma tarefa:
	•	PUT /tasks/{id} - Permite alterar os atributos da tarefa (título, descrição, prioridade etc).

### Excluir tarefa:
	•	DELETE /tasks/{id} - Exclui a tarefa correspondente ao id informado.

 ### Filtrar tarefas:
	•	GET /tasks/filter?prioridade={prioridade} - Filtra tarefas por prioridade.
 	•	GET /tasks/filter?dataLimite={aa-mm-dd} - Filtra tarefas por data limite (formato ano-mes-dia, exemplo: 2024-12-30).
  	•	GET /tasks/filter?prioridade={prioridade}&dataLimite={aa-mm-dd} - Filtra tarefas por prioridade e data limite.

### Exemplos de Requisições no Postman podem ser encontrados na pasta 'endpoints'.

## Como Configurar e Executar o Projeto

	1.	Clone o repositório para sua máquina local.
	2.	Abra o projeto na sua IDE de preferência (como IntelliJ ou Eclipse).
	3.	Compile e execute o projeto.
	4.	Utilize o Postman para enviar requisições e testar os endpoints disponíveis (https://web.postman.co/).

### Observações Finais

	•	Ao acessar o endpoint GET /tasks, um JSON contendo todas as tarefas será retornado, mas sem uma organização explícita por status ou prioridade. Essa visualização bem ordenada fica por conta da tabela gerada no console a cada requisição GET.
	•	Filtros por prioridade e/ou data limite também serão exibidos no terminal do console do projeto, quando aplicados.
 	•	Tarefas atrasadas, ou seja, cuja data limite é anterior à data atual da consulta, são marcadsa em vermelho na tabela exibida pelo terminal do console do projeto.
