package br.com.mycompany.projectestagio.view.managedbeans;

import br.com.mycompany.projectestagio.controller.services.AlunoService;
import br.com.mycompany.projectestagio.model.DAO.AlunoDAO;
import br.com.mycompany.projectestagio.model.DAO.EmpresaDAO;
import br.com.mycompany.projectestagio.model.entities.Aluno;
import br.com.mycompany.projectestagio.model.entities.Empresa;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Bruno
 */
@Named
@ViewScoped
public class AlunoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
        @Inject
	private AlunoService alunoService;
	
	@Inject
	private AlunoDAO alunoDAO;
        
        @Inject
	private EmpresaDAO empresaDAO;
	
	
	private Aluno aluno = new Aluno();
        
        private List<Aluno> alunos = new ArrayList<>(); 
	private List<Empresa> empresas = new ArrayList<>();;

	@PostConstruct
	public void init() {
		this.empresas = this.empresaDAO.listar();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.alunoService.salvar(aluno);
			this.aluno = new Aluno();
			context.addMessage(null, new FacesMessage("ALuno salvo com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} 
	}
	
	
	public void listarAlunos() {
		this.alunos = this.alunoDAO.listar();
	}

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
        
        
        
        
        
}
