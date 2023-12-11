/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.model.DAO;

import br.com.mycompany.projectestagio.model.entities.Estagio;
import br.com.mycompany.projectestagio.model.utils.PersistenceFactory;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.*;

/**
 * @author Bruno
 */
@Named
public class EstagioDAO implements DAO<Estagio> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(Estagio estagio) {
        manager.persist(estagio);
    }

    @Override
    public Estagio buscarPorId(Long id) {
        return manager.find(Estagio.class, id);
    }

    @Override
    public void atualizar(Estagio estagio) {
        manager.merge(estagio);
    }

    @Override
    public void remover(Long id) {
        Estagio estagio = manager.find(Estagio.class, id);
        if (estagio != null) {
            manager.remove(estagio);
        }
    }

    @Override
    public List<Estagio> listar() {
        TypedQuery<Estagio> query = manager.createQuery("SELECT e FROM Estagio e", Estagio.class);
        List<Estagio> estagios = query.getResultList();
        return estagios;
    }

    public Estagio filtrarEstagioPorMatricula(String matricula) {
        TypedQuery<Estagio> query = manager.createQuery(
                "SELECT e FROM Estagio e WHERE e.aluno.matricula = :matricula",
                Estagio.class
        );
        query.setParameter("matricula", matricula);
        Estagio estagio = query.getSingleResult();
        return estagio;
    }

}
