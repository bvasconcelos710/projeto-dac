/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.model.DAO;

import br.com.mycompany.projectestagio.model.entities.Orientador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class OrientadorDAO implements DAO<Orientador> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(Orientador orientador) {
        manager.persist(orientador);
    }

    @Override
    public Orientador buscarPorId(Long id) {
        return manager.find(Orientador.class, id);
    }

    @Override
    public void atualizar(Orientador orientador) {
        manager.merge(orientador);
    }

    @Override
    public void remover(Long id) {
        Orientador orientador = manager.find(Orientador.class, id);
        if (orientador != null) {
            manager.remove(orientador);
        }
    }

    @Override
    public List<Orientador> listar() {
        TypedQuery<Orientador> query = manager.createQuery("SELECT o FROM Orientador o", Orientador.class);
        List<Orientador> orientadores = query.getResultList();
        return orientadores;
    }

}
