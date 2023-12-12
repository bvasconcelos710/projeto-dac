package br.com.mycompany.projectestagio.model.DAO;

import br.com.mycompany.projectestagio.model.entities.AvaliacaoEmpresa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Named
public class AvaliacaoEmpresaDAO implements DAO<AvaliacaoEmpresa> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(AvaliacaoEmpresa entidade) {
        manager.persist(entidade);
    }

    @Override
    public AvaliacaoEmpresa buscarPorId(Long id) {
        return manager.find(AvaliacaoEmpresa.class, id);
    }

    @Override
    public void atualizar(AvaliacaoEmpresa entidade) {
        manager.merge(entidade);
    }

    @Override
    public void remover(Long id) {
        AvaliacaoEmpresa AvaliacaoEmpresa = manager.find(AvaliacaoEmpresa.class, id);
        if (AvaliacaoEmpresa != null) {
            manager.remove(AvaliacaoEmpresa);
        }
    }

    @Override
    public List<AvaliacaoEmpresa> listar() {
        return manager.createQuery("SELECT a FROM AvaliacaoEmpresa a", AvaliacaoEmpresa.class).getResultList();
    }
}
