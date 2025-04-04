package com.pedro.dev.todolistwidget.portlet.tarefa.vo;

import com.pedro.dev.tarefa.model.Tarefa;

import java.util.List;

public class TarefaVo {

    private Tarefa tarefa;
    private List<Tarefa> subTarefas;

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public List<Tarefa> getSubTarefas() {
        return subTarefas;
    }

    public void setSubTarefas(List<Tarefa> subTarefas) {
        this.subTarefas = subTarefas;
    }
}
