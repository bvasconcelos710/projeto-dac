package br.com.mycompany.projectestagio.controller.services;

import br.com.mycompany.projectestagio.model.DAO.AvaliacaoOrientadorDAO;
import br.com.mycompany.projectestagio.model.entities.AvaliacaoOrientador;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Named
public class AvaliacaoOrientadorService implements Serializable {

    private final AvaliacaoOrientadorDAO avalicaoOrientadorDAO;

    @Inject
    public AvaliacaoOrientadorService(AvaliacaoOrientadorDAO avalicaoOrientadorDAO) {
        this.avalicaoOrientadorDAO = avalicaoOrientadorDAO;
    }

    @Transactional
    public void salvar(AvaliacaoOrientador avaliacaoOrientador) {
        avalicaoOrientadorDAO.inserir(avaliacaoOrientador);
    }

    @Transactional
    public void excluir(AvaliacaoOrientador avaliacaoOrientador) {
        avalicaoOrientadorDAO.remover(avaliacaoOrientador.getId());
    }

    @Transactional
    public void editar(AvaliacaoOrientador avaliacaoOrientador) {
        avalicaoOrientadorDAO.atualizar(avaliacaoOrientador);
    }

    public AvaliacaoOrientador buscarPorId(Long id) {
        return avalicaoOrientadorDAO.buscarPorId(id);
    }

    public List<AvaliacaoOrientador> listar() {
        return avalicaoOrientadorDAO.listar();
    }

}
