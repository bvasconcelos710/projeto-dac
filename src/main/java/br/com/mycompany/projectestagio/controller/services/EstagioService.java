package br.com.mycompany.projectestagio.controller.services;

import br.com.mycompany.projectestagio.model.DAO.EstagioDAO;
import br.com.mycompany.projectestagio.model.entities.Estagio;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Named
public class EstagioService implements Serializable {

    private static final long serialVersionUID = 1L;
    private final EstagioDAO estagioDAO;

    @Inject
    public EstagioService(EstagioDAO estagioDAO) {
        this.estagioDAO = estagioDAO;
    }

    public List<Estagio> listar() {
        return this.estagioDAO.listar();
    }

    public Estagio buscarPorId(Long id) {
        return this.estagioDAO.buscarPorId(id);
    }

    @Transactional
    public void salvar(Estagio estagio) {
        this.estagioDAO.inserir(estagio);
    }

    @Transactional
    public void editar(Estagio estagio) {
        this.estagioDAO.atualizar(estagio);
    }

    @Transactional
    public void excluir(Estagio estagio) {
        this.estagioDAO.remover(estagio.getId());
    }

    @Transactional
    public void excluir(List<Estagio> estagios) {
        for (Estagio estagio : estagios) {
            this.estagioDAO.remover(estagio.getId());
        }
    }

}
