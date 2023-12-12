package br.com.mycompany.projectestagio.view.managedbeans;

import br.com.mycompany.projectestagio.controller.services.AlunoService;
import br.com.mycompany.projectestagio.controller.services.EmpresaService;
import br.com.mycompany.projectestagio.controller.services.EstagioService;
import br.com.mycompany.projectestagio.controller.services.OrientadorService;
import br.com.mycompany.projectestagio.model.entities.Aluno;
import br.com.mycompany.projectestagio.model.entities.Empresa;
import br.com.mycompany.projectestagio.model.entities.Estagio;
import br.com.mycompany.projectestagio.model.entities.Orientador;
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
public class EstagioBean implements Serializable {

    private final EstagioService estagioService;
    private final EmpresaService empresaService;
    private final AlunoService alunoService;
    private final OrientadorService orientadorService;

    private List<Estagio> estagios;
    private Estagio estagioSelecionado;
    private List<Estagio> estagiosSelecionados;

    // Form estágio
    private List<Empresa> empresas;
    private List<Orientador> orientadores;
    private List<Aluno> alunos;

    // Novo estagio
    private Empresa empresa;
    private Orientador orientador;
    private Aluno aluno;

    @Inject
    public EstagioBean(EstagioService estagioService, EmpresaService empresaService, AlunoService alunoService, OrientadorService orientadorService) {
        this.estagioService = estagioService;
        this.empresaService = empresaService;
        this.alunoService = alunoService;
        this.orientadorService = orientadorService;
    }

    @PostConstruct
    public void init() {
        this.estagios = this.estagioService.listar();
        this.empresas = this.empresaService.listar();
        this.alunos = this.alunoService.listar();
        this.orientadores = this.orientadorService.listar();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (estagioSelecionado.getId() == null) {
            this.estagioService.salvar(estagioSelecionado);
            context.addMessage(null, new FacesMessage("Estágio salvo com sucesso!"));
        } else {
            this.estagioService.editar(estagioSelecionado);
            context.addMessage(null, new FacesMessage("Estágio editado com sucesso!"));
        }
        novo();
        this.estagios = this.estagioService.listar();
        PrimeFaces.current().executeScript("PF('manageEstagioDialog').hide()");
        PrimeFaces.current().ajax().update("formEstagio:messages", "formEstagio:estagiosTable");
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.estagioService.excluir(estagioSelecionado);
            this.estagios.remove(estagioSelecionado);
            context.addMessage(null, new FacesMessage("Estágio excluído com sucesso!"));
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        this.estagios = this.estagioService.listar();
        PrimeFaces.current().ajax().update("formEstagio:messages", "formEstagio:estagiosTable");
    }

    public void excluirSelecionados() {
        this.estagioService.excluir(estagiosSelecionados);
        this.estagios = this.estagioService.listar();
    }

    public boolean hasEstagiosSelecionados() {
        return this.estagiosSelecionados != null && !this.estagiosSelecionados.isEmpty();
    }

    public void novo() {
        this.estagioSelecionado = new Estagio();
        this.estagioSelecionado.setOrientador(new Orientador());
        this.estagioSelecionado.setEmpresa(new Empresa());
        this.estagioSelecionado.setAluno(new Aluno());
    }

    public Estagio getEstagioSelecionado() {
        return estagioSelecionado;
    }

    public void setEstagioSelecionado(Estagio estagioSelecionado) {
        this.estagioSelecionado = estagioSelecionado;
    }


    public List<Estagio> getEstagios() {
        return estagios;
    }

    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }

    public List<Estagio> getEstagiosSelecionados() {
        return estagiosSelecionados;
    }

    public void setEstagiosSelecionados(List<Estagio> estagiosSelecionados) {
        this.estagiosSelecionados = estagiosSelecionados;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
