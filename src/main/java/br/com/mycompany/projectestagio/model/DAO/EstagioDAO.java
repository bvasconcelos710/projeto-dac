/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.model.DAO;

import br.com.mycompany.projectestagio.model.entities.Estagio;
import br.com.mycompany.projectestagio.model.utils.PersistenceFactory;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author Bruno
 */
public class EstagioDAO implements DAO<Estagio> {

    private EntityManager manager = PersistenceFactory.getEntityManager();
    
    @Override
    public void inserir(Estagio estagio) {
        manager.getTransaction().begin();
        manager.persist(estagio);
        manager.getTransaction().commit();
        manager.close(); 
    }
    
    @Override
    public Estagio buscarPorId(Long id){
         return manager.find(Estagio.class, id);
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
