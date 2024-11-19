package com.example.Kanban.service;

import com.example.Kanban.model.Tarefa;
import com.example.Kanban.repository.TarefaRepository;
import com.example.Kanban.util.TarefaConsolePrinter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;


@Service
public class TarefaService {

    private TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository repository) {
        this.tarefaRepository = repository;
    }

    // Cria uma nova tarefa
    public Tarefa criarTarefa(Tarefa tarefa) {
        // Valida a prioridade
        validarPrioridade(tarefa.getPrioridade());

        tarefa.setDataCriacao(LocalDate.now()); // Data de criação da tarefa
        tarefa.setStatus("A Fazer"); // Status inicial da tarefa
        return tarefaRepository.save(tarefa);
    }

    // Atualiza uma tarefa existente
    public Tarefa atualizarTarefa(int id, Tarefa tarefaDetalhes) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));

        // Valida a prioridade
        validarPrioridade(tarefaDetalhes.getPrioridade());

        tarefa.setTitulo(tarefaDetalhes.getTitulo());
        tarefa.setDescricao(tarefaDetalhes.getDescricao());
        tarefa.setPrioridade(tarefaDetalhes.getPrioridade());
        tarefa.setDataLimite(tarefaDetalhes.getDataLimite());

        return tarefaRepository.save(tarefa);
    }

    // Move tarefa para o próximo status
    public Tarefa moverTarefa(int id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));

        switch (tarefa.getStatus()) {
            case "A Fazer" -> tarefa.setStatus("Em Progresso");
            case "Em Progresso" -> tarefa.setStatus("Concluído");
            case "Concluído" -> throw new RuntimeException("Tarefa concluída.");
            default -> throw new RuntimeException("Status: inválido.");
        }

        return tarefaRepository.save(tarefa);
    }

    // Metodo para validar a prioridade
    private void validarPrioridade(String prioridade) {
        if (prioridade == null || (!prioridade.equalsIgnoreCase("Alta") &&
                !prioridade.equalsIgnoreCase("Média") &&
                !prioridade.equalsIgnoreCase("Baixa"))) {
            throw new IllegalArgumentException("Prioridade inválida. Use 'Alta', 'Média' ou 'Baixa'.");
        }
    }

    // Exclui uma tarefa
    public void excluirTarefa(int id) {
        if (!tarefaRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada com ID: " + id);
        }
        tarefaRepository.deleteById(id);
    }

    public List listarTarefas() {
        List<Tarefa> aFazer = tarefaRepository.findByStatus("A Fazer");
        List<Tarefa> emProgresso = tarefaRepository.findByStatus("Em Progresso");
        List<Tarefa> concluido = tarefaRepository.findByStatus("Concluído");

        return TarefaConsolePrinter.imprimirTarefasPorColuna(aFazer, emProgresso, concluido);
    }

    // Metodo para filtrar tarefas com base em prioridade e data limite exata
    public List<Tarefa> filtrarTarefas(String prioridade, LocalDate dataLimite) {
        List<Tarefa> tarefasFiltradas = null;

        // Filtra as tarefas com base nos parâmetros fornecidos
        if (prioridade != null && dataLimite != null) {
            // Se ambos os filtros forem fornecidos, consulta as tarefas com ambos os filtros
            tarefasFiltradas = tarefaRepository.findTarefasByPrioridadeAndDataLimite(prioridade, dataLimite);
        } else if (prioridade != null) {
            // Se apenas o filtro de prioridade for fornecido
            tarefasFiltradas = tarefaRepository.findByPrioridade(prioridade);
        } else if (dataLimite != null) {
            // Se apenas o filtro de dataLimite for fornecido
            tarefasFiltradas = tarefaRepository.findByDataLimite(dataLimite); // Consulta para dataLimite exata
        } else {
            // Se não houver filtro, retorna todas as tarefas
            tarefasFiltradas = tarefaRepository.findAll();
        }

        // Agora, separa as tarefas por status
        List<Tarefa> aFazer = tarefasFiltradas.stream().filter(t -> t.getStatus().equals("A Fazer")).collect(Collectors.toList());
        List<Tarefa> emProgresso = tarefasFiltradas.stream().filter(t -> t.getStatus().equals("Em Progresso")).collect(Collectors.toList());
        List<Tarefa> concluido = tarefasFiltradas.stream().filter(t -> t.getStatus().equals("Concluído")).collect(Collectors.toList());

        // Chama o método para imprimir a tabela no console
        TarefaConsolePrinter.imprimirTarefasPorColuna(aFazer, emProgresso, concluido);

        // Retorna as tarefas filtradas no formato JSON
        return tarefasFiltradas;
    }

}
