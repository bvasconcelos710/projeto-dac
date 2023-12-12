package br.com.mycompany.projectestagio.controller.services;

import br.com.mycompany.projectestagio.model.DAO.AvaliacaoEmpresaDAO;
import br.com.mycompany.projectestagio.model.entities.AvaliacaoEmpresa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Named
public class AvaliacaoEmpresaService implements Serializable {

    private final AvaliacaoEmpresaDAO avaliacaoEmpresaDAO;

    @Inject
    public AvaliacaoEmpresaService(AvaliacaoEmpresaDAO avaliacaoEmpresaDAO) {
        this.avaliacaoEmpresaDAO = avaliacaoEmpresaDAO;
    }

    @Transactional
    public void salvar(AvaliacaoEmpresa avaliacaoEmpresa) {
        avaliacaoEmpresaDAO.inserir(avaliacaoEmpresa);
    }

    @Transactional
    public void excluir(AvaliacaoEmpresa avaliacaoEmpresa) {
        avaliacaoEmpresaDAO.remover(avaliacaoEmpresa.getId());
    }

    @Transactional
    public void editar(AvaliacaoEmpresa avaliacaoEmpresa) {
        avaliacaoEmpresaDAO.atualizar(avaliacaoEmpresa);
    }

    public AvaliacaoEmpresa buscarPorId(Long id) {
        return avaliacaoEmpresaDAO.buscarPorId(id);
    }

    public List<AvaliacaoEmpresa> listar() {
        return avaliacaoEmpresaDAO.listar();
    }

}
