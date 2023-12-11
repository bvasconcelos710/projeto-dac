/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.controller.services;

import br.com.mycompany.projectestagio.controller.exceptions.BusinessException;
import br.com.mycompany.projectestagio.model.DAO.OrientadorDAO;
import br.com.mycompany.projectestagio.model.entities.Orientador;
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
public class OrientadorService implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private final OrientadorDAO orientadorDAO;

    @Inject
    public OrientadorService(OrientadorDAO orientadorDAO) {
        this.orientadorDAO = orientadorDAO;
    }

    public Orientador search(Long id) {
		return this.orientadorDAO.buscarPorId(id);
	}
	
	@Transactional
	public void salvar(Orientador orientador) throws BusinessException {
		if (orientador == null) {
			throw new BusinessException("Não foi possível salvar o Orientador.");
		}
		this.orientadorDAO.inserir(orientador);
	}
	
	@Transactional
	public void excluir(Orientador orientador) throws BusinessException {
		orientador = this.orientadorDAO.buscarPorId(orientador.getId());
		if (orientador == null) {
			throw new BusinessException("Não é possível excluir o Orientador!");
		}
		this.orientadorDAO.remover(orientador.getId());
	}

    @Transactional
    public void editar(Orientador orientador) throws BusinessException {
        if (orientador == null) {
            throw new BusinessException("Não foi possível editar o Orientador.");
        }
        this.orientadorDAO.atualizar(orientador);
    }

    @Transactional
    public void excluir(List<Orientador> orientadores) throws BusinessException {
        if (orientadores == null || orientadores.size() == 0) {
            throw new BusinessException("Não foi possível excluir os Orientadores!");
        }
        orientadores.forEach(orientador -> this.orientadorDAO.remover(orientador.getId()));
    }
	
	public List<Orientador> listar() {
		return this.orientadorDAO.listar();
	}
}
