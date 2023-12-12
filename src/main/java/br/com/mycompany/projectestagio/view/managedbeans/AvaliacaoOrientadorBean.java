package br.com.mycompany.projectestagio.view.managedbeans;

import br.com.mycompany.projectestagio.controller.services.AlunoService;
import br.com.mycompany.projectestagio.controller.services.AvaliacaoOrientadorService;
import br.com.mycompany.projectestagio.controller.services.OrientadorService;
import br.com.mycompany.projectestagio.model.entities.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AvaliacaoOrientadorBean implements Serializable {

    private AvaliacaoOrientador avaliacaoOrientadorSelecionado;

    private List<AvaliacaoOrientador> avaliacoes;
    private List<Orientador> orientadores;
    private List<Aluno> alunos;

    private final AlunoService alunoService;
    private final OrientadorService orientadorService;
    private final AvaliacaoOrientadorService avaliacaoOrientadorService;

    private final Integer insuficiente = 1;
    private final Integer regular = 2;
    private final Integer bom = 3;
    private final Integer muitoBom = 4;

    @Inject
    public AvaliacaoOrientadorBean(AlunoService alunoService, OrientadorService orientadorService, AvaliacaoOrientadorService avaliacaoOrientadorService) {
        this.alunoService = alunoService;
        this.orientadorService = orientadorService;
        this.avaliacaoOrientadorService = avaliacaoOrientadorService;
    }

    @PostConstruct
    public void init() {
        this.avaliacoes = this.avaliacaoOrientadorService.listar();
        this.alunos = this.alunoService.listar();
        this.orientadores = this.orientadorService.listar();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (avaliacaoOrientadorSelecionado.getId() == null) {
            this.avaliacaoOrientadorService.salvar(avaliacaoOrientadorSelecionado);
            context.addMessage(null, new FacesMessage("Estágio salvo com sucesso!"));
        } else {
            this.avaliacaoOrientadorService.editar(avaliacaoOrientadorSelecionado);
            context.addMessage(null, new FacesMessage("Estágio editado com sucesso!"));
        }
        novo();
        this.avaliacoes = this.avaliacaoOrientadorService.listar();
        PrimeFaces.current().executeScript("PF('manageAvaliacaoOrientadorDialog').hide()");
        PrimeFaces.current().ajax().update("formAvaliacaoOrientador:messages", "formAvaliacaoOrientador:avaliacoesTable");
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.avaliacaoOrientadorService.excluir(avaliacaoOrientadorSelecionado);
            this.avaliacoes.remove(avaliacaoOrientadorSelecionado);
            context.addMessage(null, new FacesMessage("Estágio excluído com sucesso!"));
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        this.avaliacoes = this.avaliacaoOrientadorService.listar();
        PrimeFaces.current().ajax().update("formAvaliacaoOrientador:messages", "formAvaliacaoOrientador:avaliacoesTable");
    }

    public void novo() {
        this.avaliacaoOrientadorSelecionado = new AvaliacaoOrientador();
        this.avaliacaoOrientadorSelecionado.setAluno(new Aluno());
        this.avaliacaoOrientadorSelecionado.setOrientador(new Orientador());
        this.avaliacaoOrientadorSelecionado.setAssiduidade(0L);
        this.avaliacaoOrientadorSelecionado.setDisciplina(0L);
        this.avaliacaoOrientadorSelecionado.setSociabilidade(0L);
        this.avaliacaoOrientadorSelecionado.setResponsabilidade(0L);
    }

    public AvaliacaoOrientador getAvaliacaoOrientadorSelecionado() {
        return avaliacaoOrientadorSelecionado;
    }

    public void setAvaliacaoOrientadorSelecionado(AvaliacaoOrientador avaliacaoOrientadorSelecionado) {
        this.avaliacaoOrientadorSelecionado = avaliacaoOrientadorSelecionado;
    }

    public List<AvaliacaoOrientador> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoOrientador> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Orientador> getOrientadores() {
        return orientadores;
    }

    public void setOrientadores(List<Orientador> orientadores) {
        this.orientadores = orientadores;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Integer getInsuficiente() {
        return insuficiente;
    }

    public Integer getRegular() {
        return regular;
    }

    public Integer getBom() {
        return bom;
    }

    public Integer getMuitoBom() {
        return muitoBom;
    }
}
