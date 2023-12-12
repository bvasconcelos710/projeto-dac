package br.com.mycompany.projectestagio.model.DAO;

import br.com.mycompany.projectestagio.model.DAO.DAO;
import br.com.mycompany.projectestagio.model.entities.AvaliacaoOrientador;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Named
public class AvaliacaoOrientadorDAO implements DAO<AvaliacaoOrientador> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(AvaliacaoOrientador entidade) {
        manager.persist(entidade);
    }

    @Override
    public AvaliacaoOrientador buscarPorId(Long id) {
        return manager.find(AvaliacaoOrientador.class, id);
    }

    @Override
    public void atualizar(AvaliacaoOrientador entidade) {
        manager.merge(entidade);
    }

    @Override
    public void remover(Long id) {
        AvaliacaoOrientador avaliacaoOrientador = manager.find(AvaliacaoOrientador.class, id);
        if (avaliacaoOrientador != null) {
            manager.remove(avaliacaoOrientador);
        }
    }

    @Override
    public List<AvaliacaoOrientador> listar() {
        return manager.createQuery("SELECT a FROM AvaliacaoOrientador a", AvaliacaoOrientador.class).getResultList();
    }
}
