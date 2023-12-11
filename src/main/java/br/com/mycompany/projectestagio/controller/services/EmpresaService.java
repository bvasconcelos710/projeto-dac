/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.controller.services;

import br.com.mycompany.projectestagio.controller.exceptions.BusinessException;
import br.com.mycompany.projectestagio.model.DAO.EmpresaDAO;
import br.com.mycompany.projectestagio.model.entities.Empresa;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bruno
 */
@Named
public class EmpresaService implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private final EmpresaDAO empresaDAO;

    @Inject
    public EmpresaService(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }

    public Empresa search(Long id) {
		return this.empresaDAO.buscarPorId(id);
	}
	
	@Transactional
	public void salvar(Empresa empresa) throws BusinessException {
		if (empresa == null) {
			throw new BusinessException("Não foi possível salvar a Empresa.");
		}
		this.empresaDAO.inserir(empresa);
	}
	
	@Transactional
	public void excluir(Empresa empresa) throws BusinessException {
		empresa = this.empresaDAO.buscarPorId(empresa.getId());
		if (empresa == null) {
			throw new BusinessException("Não é possível excluir a Empresa!");
		}
		this.empresaDAO.remover(empresa.getId());
	}

    @Transactional
    public void editar(Empresa empresa) throws BusinessException {
        if (empresa == null) {
            throw new BusinessException("Não foi possível editar a Empresa.");
        }
        this.empresaDAO.atualizar(empresa);
    }

    @Transactional
    public void excluir(List<Empresa> empresas) throws BusinessException {
        if (empresas == null || empresas.size() == 0) {
            throw new BusinessException("Não foi possível excluir as Empresas!");
        }
        empresas.forEach(empresa -> this.empresaDAO.remover(empresa.getId()));
    }
	
	public List<Empresa> listar() {
		return this.empresaDAO.listar();
	}
}
