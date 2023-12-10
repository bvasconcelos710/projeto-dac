/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.controller.services;

import br.com.mycompany.projectestagio.controller.exceptions.BusinessException;
import br.com.mycompany.projectestagio.model.DAO.AlunoDAO;
import br.com.mycompany.projectestagio.model.entities.Aluno;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class AlunoService implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Inject
	private AlunoDAO alunoDAO;

	public Aluno search(Long id) {
		return this.alunoDAO.buscarPorId(id);
	}
	
	@Transactional
	public void salvar(Aluno aluno) throws BusinessException {
		if (aluno == null) {
			throw new BusinessException("Não foi possível salvar o Livro.");
		}
		this.alunoDAO.inserir(aluno);
	}
	
	@Transactional
	public void excluir(Aluno aluno) throws BusinessException {
		aluno = this.alunoDAO.buscarPorId(aluno.getId());
		if (aluno == null) {
			throw new BusinessException("Não é possível excluir o Livro!");
		}
		this.alunoDAO.remover(aluno.getId());
	}
	
	public List<Aluno> listar() {
		return this.alunoDAO.listar();
	}
}
