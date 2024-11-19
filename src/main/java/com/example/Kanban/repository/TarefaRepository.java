package com.example.Kanban.repository;

import com.example.Kanban.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    // Metodo para buscar tarefas por status
    List<Tarefa> findByStatus(String status);

    // Metodo para buscar tarefas por prioridade
    List<Tarefa> findByPrioridade(String prioridade);

    // Metodo para buscar tarefas com data limite antes da data fornecida
    List<Tarefa> findByDataLimite(LocalDate dataLimite);

    // Consulta personalizada para buscar tarefas por prioridade e data limite
    @Query("SELECT t FROM Tarefa t WHERE t.prioridade = :prioridade AND t.dataLimite = :dataLimite")
    List<Tarefa> findTarefasByPrioridadeAndDataLimite(String prioridade, LocalDate dataLimite);


}