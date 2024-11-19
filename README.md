API REST para Gerenciamento de Tarefas - Kanban

Sobre o Projeto

Este é um sistema de gerenciamento de tarefas baseado no modelo Kanban. A API REST desenvolvida permite a criação, atualização, movimentação e exclusão de tarefas organizadas em três colunas:
	•	A Fazer (To Do)
	•	Em Progresso (In Progress)
	•	Concluído (Done)

As tarefas podem ser movidas entre essas colunas de acordo com o progresso, permitindo uma gestão simples e eficiente.

Principais Tecnologias

	•	Java com Spring Boot
	•	Banco de dados H2 (em memória)
	•	Postman para testes de API
	•	JPA (Java Persistence API) para persistência e manipulação de dados
	•	Java Web para requisições HTTP

Funcionalidades

Requisitos Básicos

	•	Cadastro de Tarefas
	•	Título: obrigatório.
	•	Descrição: opcional.
	•	Data de criação: gerada automaticamente.
	•	Status: pode ser “A Fazer”, “Em Progresso” ou “Concluído”.
	•	Prioridade: baixa, média ou alta.
	•	Data limite: opcional.
	•	Gerenciamento de Tarefas
	•	Adicionar novas tarefas na coluna “A Fazer”.
	•	Listar todas as tarefas organizadas por colunas.
	•	Mover tarefas entre colunas seguindo a sequência A Fazer → Em Progresso → Concluído.
	•	Editar informações das tarefas, como título, descrição e prioridade.
	•	Remover tarefas.

Funcionalidades Avançadas

	•	Ordenação: tarefas em cada coluna podem ser organizadas por prioridade.
	•	Filtragem: tarefas podem ser filtradas por prioridade ou data limite.
	•	Relatórios: geração de um relatório que destaca tarefas atrasadas e organiza por coluna.

Regras do Sistema

	•	Novas tarefas só podem ser adicionadas na coluna “A Fazer”.
	•	A movimentação de tarefas deve seguir a ordem: A Fazer → Em Progresso → Concluído.
	•	O campo “título” é obrigatório; já a descrição e a data limite são opcionais.

Endpoints da API

Tarefas (Porta: 8080)

	•	Criar uma tarefa:
POST /tasks - Adiciona uma nova tarefa na coluna “A Fazer”.
	•	Listar tarefas:
GET /tasks - Retorna todas as tarefas organizadas por colunas.
	•	Mover tarefa para a próxima coluna:
PUT /tasks/{id}/move
	•	Atualizar uma tarefa:
PUT /tasks/{id} - Permite alterar título, descrição, prioridade, etc.
	•	Excluir tarefa:
DELETE /tasks/{id}

Exemplos de Requisições no Postman

Criar uma nova tarefa

POST /tasks
{
    "title": "Planejar reunião",
    "description": "Preparar agenda e participantes",
    "dueDate": "2024-11-25",
    "priority": "alta"
}

Atualizar uma tarefa existente

PUT /tasks/{id}
{
    "title": "Planejar reunião (atualizado)",
    "description": "Agenda detalhada com tópicos principais",
    "priority": "média",
    "status": "Em Progresso"
}

Como Configurar e Executar o Projeto

	1.	Clone o repositório para sua máquina local.
	2.	Abra o projeto na sua IDE de preferência (como IntelliJ ou Eclipse).
	3.	Compile e execute o projeto utilizando o Spring Boot.
	4.	Utilize ferramentas como Postman ou cURL para testar os endpoints disponíveis.

Observações Finais

	•	Ao acessar o endpoint GET /tasks, um JSON contendo todas as tarefas será retornado, mas sem uma organização explícita por status ou prioridade.
	•	Filtros por prioridade ou data limite serão exibidos no terminal do console do projeto, quando aplicados.
