package com.pedro.dev.todolistwidget.portlet.tarefa.vo;

import com.pedro.dev.tarefa.model.Tarefa;

public class TarefaVo {

    private Tarefa tarefa;
    private long totalSubTarefaPendente;
    private long getTotalSubTarefaConcluida;
    private long tarefaId;

    public TarefaVo() {
    }

    public TarefaVo(Tarefa tarefa, long totalSubTarefaPendente, long getTotalSubTarefaConcluida) {
        this.tarefa = tarefa;
        this.totalSubTarefaPendente = totalSubTarefaPendente;
        this.getTotalSubTarefaConcluida = getTotalSubTarefaConcluida;
        setTarefaId(tarefa.getTarefaId());
    }


    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public long getGetTotalSubTarefaConcluida() {
        return getTotalSubTarefaConcluida;
    }

    public void setGetTotalSubTarefaConcluida(long getTotalSubTarefaConcluida) {
        this.getTotalSubTarefaConcluida = getTotalSubTarefaConcluida;
    }

    public long getTotalSubTarefaPendente() {
        return totalSubTarefaPendente;
    }

    public void setTotalSubTarefaPendente(long totalSubTarefaPendente) {
        this.totalSubTarefaPendente = totalSubTarefaPendente;
    }

    public long getTarefaId() {
        return tarefa.getTarefaId();
    }

    private void setTarefaId(long tarefaId) {
        this.tarefaId = tarefaId;
    }
}
