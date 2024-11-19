package com.example.Kanban.util;

import com.example.Kanban.model.Tarefa;

import java.time.LocalDate;
import java.util.List;
import java.util.Comparator;

public class TarefaConsolePrinter {

    // Código ANSI para vermelho e reset
    private static final String VERMELHO = "\u001B[31m"; // Vermelho
    private static final String RESET = "\u001B[0m"; // Reset

    public static List imprimirTarefasPorColuna(List<Tarefa> aFazer, List<Tarefa> emProgresso, List<Tarefa> concluido) {
        System.out.println("========= TABELA DE TAREFAS ==========");
        System.out.printf("%-4s | %-12s | %-11s | %-10s |\n", "ID", "Data Criação", "Data Limite", "Prioridade");
        System.out.println("-------------------------------------------------------------");

        // Ordena as tarefas por prioridade
        ordenarPorPrioridade(aFazer);
        ordenarPorPrioridade(emProgresso);
        ordenarPorPrioridade(concluido);

        if (!aFazer.isEmpty()) {
            System.out.println("Status: A FAZER");
            for (Tarefa tarefa : aFazer) {
                imprimirLinha(tarefa);
            }
        }

        if (!emProgresso.isEmpty()) {
            System.out.println("Status: EM PROGRESSO");
            for (Tarefa tarefa : emProgresso) {
                imprimirLinha(tarefa);
            }
        }

        if (!concluido.isEmpty()) {
            System.out.println("Status: CONCLUÍDO");
            for (Tarefa tarefa : concluido) {
                imprimirLinha(tarefa);
            }
        }
        return null;
    }

    private static void imprimirLinha(Tarefa tarefa) {
        // Verifica se a tarefa está atrasada e se o status não é "Concluído"
        boolean atrasada = tarefa.getDataLimite() != null && tarefa.getDataLimite().isBefore(LocalDate.now()) && !"Concluído".equals(tarefa.getStatus());

        // Se a tarefa estiver atrasada, aplica o estilo vermelho
        String cor = atrasada ? VERMELHO : RESET;

        // Imprime a linha com a cor adequada
        System.out.printf(
                "%-4d | %-12s | %-11s | %-10s |\n",
                tarefa.getId(),
                tarefa.getDataCriacao() != null ? tarefa.getDataCriacao().toString() : "N/A",
                tarefa.getDataLimite() != null ? cor + tarefa.getDataLimite().toString() + RESET : "N/A",
                tarefa.getPrioridade() != null ? cor + tarefa.getPrioridade() + RESET : "N/A"
        );
    }

    // Ordena as tarefas de acordo com a prioridade
    private static void ordenarPorPrioridade(List<Tarefa> tarefas) {
        tarefas.sort(Comparator.comparingInt(tarefa -> {
            switch (tarefa.getPrioridade().toLowerCase()) {
                case "alta":
                    return 1;
                case "média":
                    return 2;
                case "baixa":
                    return 3;
                default:
                    return Integer.MAX_VALUE; // Caso o valor da prioridade seja inválido
            }
        }));
    }
}