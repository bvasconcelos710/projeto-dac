/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.DAO;

import br.com.mycompany.projectestagio.entities.Estagio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Bruno
 */
public class EstagioDAO implements DAO<Estagio> {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("my_persistence_unit");
    EntityManager manager = factory.createEntityManager();
    
    @Override
    public void inserir(Estagio estagio) {
        manager.getTransaction().begin();
        manager.persist(estagio);
        manager.getTransaction().commit();
        manager.close(); 
    }

    @Override
    public void atualizar(Estagio estagio) {
        manager.getTransaction().begin();
        manager.merge(estagio);
        manager.getTransaction().commit();
        manager.close(); 
    }

    @Override
    public void remover(Long id) {
        manager.getTransaction().begin();
        Estagio estagio = manager.find(Estagio.class, id);
        if (estagio != null) {
        manager.remove(estagio);
        }
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List listar() {
        TypedQuery<Estagio> query = manager.createQuery("SELECT e FROM Estagio e", Estagio.class);
        List<Estagio> estagios = query.getResultList();
        manager.close();
        return estagios;
    }
    
}
