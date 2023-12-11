/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.view.managedbeans;

import br.com.mycompany.projectestagio.controller.services.EmpresaService;
import br.com.mycompany.projectestagio.model.DAO.EmpresaDAO;
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
import org.primefaces.PrimeFaces;

/**
 *
 * @author Bruno
 */
@Named
@ViewScoped
public class EmpresaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private EmpresaService empresaService;
    private EmpresaDAO empresaDAO;

    private List<Empresa> empresas = new ArrayList<>();

    private Empresa empresaSelecionada;
    private List<Empresa> empresasSelecionadas;

    @Inject
    public EmpresaBean(EmpresaService empresaService, EmpresaDAO empresaDAO) {
        this.empresaService = empresaService;
        this.empresaDAO = empresaDAO;
    }

    @PostConstruct
    public void init() {
        this.empresas = this.empresaDAO.listar();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (empresaSelecionada.getId() == null) {
                this.empresaService.salvar(empresaSelecionada);
                context.addMessage(null, new FacesMessage("Empresa salva com sucesso!"));
            } else {
                this.empresaService.editar(empresaSelecionada);
                context.addMessage(null, new FacesMessage("Empresa editada com sucesso!"));
            }
            this.empresas = this.empresaDAO.listar();
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        PrimeFaces.current().executeScript("PF('manageEmpresaDialog').hide()");
        PrimeFaces.current().ajax().update("formempresa:messages", "formEmpresa:empresasTable");
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.empresaService.excluir(empresaSelecionada);
            this.empresas.remove(empresaSelecionada);
            context.addMessage(null, new FacesMessage("Empresa excluída com sucesso!"));
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        PrimeFaces.current().ajax().update("formEmpresa:messages", "formEmpresa:empresasTable");
    }

    public void novo() {
        this.empresaSelecionada = new Empresa();
    }

    public void excluirEmpresasSelecionadas() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.empresaService.excluir(empresasSelecionadas);
            this.empresas.removeAll(empresasSelecionadas);
            this.empresasSelecionadas = null;
            context.addMessage(null, new FacesMessage("Empresas excluídas com sucesso!"));
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        PrimeFaces.current().ajax().update("formEmpresa:messages", "formEmpresa:empresasTable");
    }

    public boolean hasEmpresasSelecionadas() {
        return this.empresasSelecionadas != null && !this.empresasSelecionadas.isEmpty();
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Empresa> getEmpresasSelecionadas() {
        return empresasSelecionadas;
    }

    public void setEmpresasSelecionadas(List<Empresa> empresasSelecionadas) {
        this.empresasSelecionadas = empresasSelecionadas;
    }

    public Empresa getEmpresaSelecionada() {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(Empresa empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
    }
}
