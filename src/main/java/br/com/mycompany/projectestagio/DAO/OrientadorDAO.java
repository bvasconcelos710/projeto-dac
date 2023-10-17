/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.DAO;

import br.com.mycompany.projectestagio.entities.Orientador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class OrientadorDAO implements DAO<Orientador> {
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("my_persistence_unit");
    EntityManager manager = factory.createEntityManager();

    @Override
    public void inserir(Orientador orientador) {
        manager.getTransaction().begin();
        manager.persist(orientador);
        manager.getTransaction().commit();
        manager.close(); 
    }
    
    @Override
    public Orientador buscarPorId(Long id){
         return manager.find(Orientador.class, id);
    }

    @Override
    public void atualizar(Orientador orientador) {
        manager.getTransaction().begin();
        manager.merge(orientador);
        manager.getTransaction().commit();
        manager.close(); 
    }

    @Override
    public void remover(Long id) {
        manager.getTransaction().begin();
        Orientador orientador = manager.find(Orientador.class, id);
        if (orientador != null) {
        manager.remove(orientador);
        }
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List listar() {
        TypedQuery<Orientador> query = manager.createQuery("SELECT o FROM Orientador o", Orientador.class);
        List<Orientador> orientadores = query.getResultList();
        manager.close();
        return orientadores;
    }
    
}
