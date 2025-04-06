package com.pedro.dev.tarefa.service.constants;


import java.util.Arrays;

public enum TarefaStatus {

    PENDENTE(StatusCode.PENDENTE, "Pendente"),
    CONCLUIDO(StatusCode.CONCLUIDO, "Concluído");

    private final int codigo;
    private final String descricao;

    TarefaStatus(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TarefaStatus fromCodigo(int codigo) {
        return Arrays.stream(TarefaStatus.values())
                .filter(status -> status.getCodigo() == codigo)
                .findFirst()
                .orElse(PENDENTE);
    }

    public static TarefaStatus fromDescricao(String descricao) {
        return Arrays.stream(TarefaStatus.values())
                .filter(status -> status.getDescricao().equalsIgnoreCase(descricao))
                .findFirst()
                .orElse(PENDENTE);
    }

    public static class StatusCode {
        public static final int PENDENTE = 1;
        public static final int CONCLUIDO = 2;
    }
}