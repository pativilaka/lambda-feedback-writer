package com.fiap.lambda.feedback.dto;

public class FeedbackDTO {

    private String descricao;
    private Integer nota;

    public FeedbackDTO() {
    }

    public FeedbackDTO(String descricao, Integer nota) {
        this.descricao = descricao;
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "descricao='" + descricao + '\'' +
                ", nota=" + nota +
                '}';
    }
    
}
