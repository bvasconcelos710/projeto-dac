package br.com.mycompany.projectestagio.view.managedbeans;

import br.com.mycompany.projectestagio.controller.services.AlunoService;
import br.com.mycompany.projectestagio.controller.services.AvaliacaoEmpresaService;
import br.com.mycompany.projectestagio.controller.services.EmpresaService;
import br.com.mycompany.projectestagio.controller.services.OrientadorService;
import br.com.mycompany.projectestagio.model.entities.Aluno;
import br.com.mycompany.projectestagio.model.entities.AvaliacaoEmpresa;
import br.com.mycompany.projectestagio.model.entities.Empresa;
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
public class AvaliacaoEmpresaBean implements Serializable {

    private AvaliacaoEmpresa avaliacaoEmpresaSelecionado;

    private List<AvaliacaoEmpresa> avaliacoes;
    private List<Empresa> empresas;
    private List<Aluno> alunos;

    private final AlunoService alunoService;
    private final EmpresaService empresaService;
    private final AvaliacaoEmpresaService avaliacaoEmpresaService;

    private final Integer insuficiente = 1;
    private final Integer regular = 2;
    private final Integer bom = 3;
    private final Integer muitoBom = 4;

    @Inject
    public AvaliacaoEmpresaBean(AlunoService alunoService, EmpresaService empresaService, AvaliacaoEmpresaService avaliacaoEmpresaService) {
        this.alunoService = alunoService;
        this.empresaService = empresaService;
        this.avaliacaoEmpresaService = avaliacaoEmpresaService;
    }

    @PostConstruct
    public void init() {
        this.avaliacoes = this.avaliacaoEmpresaService.listar();
        this.alunos = this.alunoService.listar();
        this.empresas = this.empresaService.listar();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (avaliacaoEmpresaSelecionado.getId() == null) {
            this.avaliacaoEmpresaService.salvar(avaliacaoEmpresaSelecionado);
            context.addMessage(null, new FacesMessage("Avaliação salva com sucesso!"));
        } else {
            this.avaliacaoEmpresaService.editar(avaliacaoEmpresaSelecionado);
            context.addMessage(null, new FacesMessage("Avaliação salva com sucesso!"));
        }
        novo();
        this.avaliacoes = this.avaliacaoEmpresaService.listar();
        PrimeFaces.current().executeScript("PF('manageAvaliacaoEmpresaDialog').hide()");
        PrimeFaces.current().ajax().update("formAvaliacaoEmpresa:messages", "formAvaliacaoEmpresa:avaliacoesTable");
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.avaliacaoEmpresaService.excluir(avaliacaoEmpresaSelecionado);
            this.avaliacoes.remove(avaliacaoEmpresaSelecionado);
            context.addMessage(null, new FacesMessage("Avaliação excluída com sucesso!"));
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        this.avaliacoes = this.avaliacaoEmpresaService.listar();
        PrimeFaces.current().ajax().update("formAvaliacaoEmpresa:messages", "formAvaliacaoEmpresa:avaliacoesTable");
    }

    public void novo() {
        this.avaliacaoEmpresaSelecionado = new AvaliacaoEmpresa();
        this.avaliacaoEmpresaSelecionado.setAluno(new Aluno());
        this.avaliacaoEmpresaSelecionado.setEmpresa(new Empresa());
        this.avaliacaoEmpresaSelecionado.setAprendizagem(0L);
        this.avaliacaoEmpresaSelecionado.setDesempenho(0L);
        this.avaliacaoEmpresaSelecionado.setRendimento(0L);
        this.avaliacaoEmpresaSelecionado.setConhecimento(0L);
        this.avaliacaoEmpresaSelecionado.setCumprimentoTarefas(0L);
    }

    public AvaliacaoEmpresa getAvaliacaoEmpresaSelecionado() {
        return avaliacaoEmpresaSelecionado;
    }

    public void setAvaliacaoEmpresaSelecionado(AvaliacaoEmpresa avaliacaoEmpresaSelecionado) {
        this.avaliacaoEmpresaSelecionado = avaliacaoEmpresaSelecionado;
    }

    public List<AvaliacaoEmpresa> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoEmpresa> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
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
