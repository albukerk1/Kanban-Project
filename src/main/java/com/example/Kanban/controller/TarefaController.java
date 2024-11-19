package com.example.Kanban.controller;

import com.example.Kanban.model.Tarefa;
import com.example.Kanban.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    public TarefaController(TarefaService service) {
        this.tarefaService = service;
    }

    // Cria uma nova tarefa
    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa){
        return tarefaService.criarTarefa(tarefa);
    }

    // Lista todas as tarefas
    @GetMapping
    public List mostrarTarefas() {
        return tarefaService.listarTarefas();
    }

    // Atualiza tarefa existente
    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable int id, @RequestBody Tarefa tarefaDetalhes) {
        return tarefaService.atualizarTarefa(id, tarefaDetalhes);
    }

    // Move tarefa para o próximo status
    @PutMapping("/{id}/move")
    public Tarefa moverTarefa(@PathVariable int id) {
        return tarefaService.moverTarefa(id);
    }

    // Exclui tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable int id) {
        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }

    // Exceção global para capturar a exceção de prioridade inválida
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Endpoint para filtrar tarefas por prioridade e data limite
    @GetMapping("/filter")
    public List<Tarefa> filtrarTarefas(
            @RequestParam(required = false) String prioridade,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataLimite) {

        return tarefaService.filtrarTarefas(prioridade, dataLimite); // Chama o serviço para obter e imprimir as tarefas
    }

}
