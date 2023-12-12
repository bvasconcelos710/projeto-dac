package br.com.mycompany.projectestagio.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class AvaliacaoEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Empresa empresa;

    @OneToOne
    private Aluno aluno;

    private Long rendimento;
    private Long conhecimento;
    private Long cumprimentoTarefas;
    private Long aprendizagem;
    private Long desempenho;

    public AvaliacaoEmpresa() {
    }

    public AvaliacaoEmpresa(Long id, Empresa empresa, Aluno aluno, Long rendimento, Long conhecimento, Long cumprimentoTarefas, Long aprendizagem, Long desempenho) {
        this.id = id;
        this.empresa = empresa;
        this.aluno = aluno;
        this.rendimento = rendimento;
        this.conhecimento = conhecimento;
        this.cumprimentoTarefas = cumprimentoTarefas;
        this.aprendizagem = aprendizagem;
        this.desempenho = desempenho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Long getRendimento() {
        return rendimento;
    }

    public void setRendimento(Long rendimento) {
        this.rendimento = rendimento;
    }

    public Long getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(Long conhecimento) {
        this.conhecimento = conhecimento;
    }

    public Long getCumprimentoTarefas() {
        return cumprimentoTarefas;
    }

    public void setCumprimentoTarefas(Long cumprimentoTarefas) {
        this.cumprimentoTarefas = cumprimentoTarefas;
    }

    public Long getAprendizagem() {
        return aprendizagem;
    }

    public void setAprendizagem(Long aprendizagem) {
        this.aprendizagem = aprendizagem;
    }

    public Long getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(Long desempenho) {
        this.desempenho = desempenho;
    }
}
