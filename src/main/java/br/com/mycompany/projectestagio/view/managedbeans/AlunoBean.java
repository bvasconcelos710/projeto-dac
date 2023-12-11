package br.com.mycompany.projectestagio.view.managedbeans;

import br.com.mycompany.projectestagio.controller.services.AlunoService;
import br.com.mycompany.projectestagio.model.DAO.AlunoDAO;
import br.com.mycompany.projectestagio.model.entities.Aluno;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author Bruno
 */
@Named
@ViewScoped
public class AlunoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private AlunoService alunoService;
    private AlunoDAO alunoDAO;

    private List<Aluno> alunos = new ArrayList<>();

    private Aluno alunoSelecionado;
    private List<Aluno> alunosSelecionados;

    @Inject
    public AlunoBean(AlunoService alunoService, AlunoDAO alunoDAO) {
        this.alunoService = alunoService;
        this.alunoDAO = alunoDAO;
    }

    @PostConstruct
    public void init() {
        this.alunos = this.alunoDAO.listar();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (alunoSelecionado.getId() == null) {
                this.alunoService.salvar(alunoSelecionado);
                context.addMessage(null, new FacesMessage("Aluno salvo com sucesso!"));
            } else {
                this.alunoService.editar(alunoSelecionado);
                context.addMessage(null, new FacesMessage("Aluno editado com sucesso!"));
            }
            this.alunos = this.alunoDAO.listar();
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        PrimeFaces.current().executeScript("PF('manageAlunoDialog').hide()");
        PrimeFaces.current().ajax().update("formAluno:messages", "formAluno:alunosTable");
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.alunoService.excluir(alunoSelecionado);
            this.alunos.remove(alunoSelecionado);
            context.addMessage(null, new FacesMessage("Aluno excluído com sucesso!"));
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        PrimeFaces.current().ajax().update("formAluno:messages", "formAluno:alunosTable");
    }

    public void novo() {
        this.alunoSelecionado = new Aluno();
    }

    public void excluirAlunosSelecionados() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.alunoService.excluir(alunosSelecionados);
            this.alunos.removeAll(alunosSelecionados);
            this.alunosSelecionados = null;
            context.addMessage(null, new FacesMessage("Alunos excluídos com sucesso!"));
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        PrimeFaces.current().ajax().update("formAluno:messages", "formAluno:alunosTable");
    }

    public boolean hasAlunosSelecionados() {
        return this.alunosSelecionados != null && !this.alunosSelecionados.isEmpty();
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Aluno> getAlunosSelecionados() {
        return alunosSelecionados;
    }

    public void setAlunosSelecionados(List<Aluno> alunosSelecionados) {
        this.alunosSelecionados = alunosSelecionados;
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }
}
