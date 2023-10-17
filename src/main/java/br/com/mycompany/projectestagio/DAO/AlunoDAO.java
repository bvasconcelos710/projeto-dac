/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.DAO;

import br.com.mycompany.projectestagio.entities.Aluno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class AlunoDAO implements DAO<Aluno> {
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("my_persistence_unit");
    EntityManager manager = factory.createEntityManager();

    @Override
    public void inserir(Aluno aluno) {
        manager.getTransaction().begin();
        manager.persist(aluno);
        manager.getTransaction().commit();
        manager.close(); 
    }
    
    @Override
    public Aluno buscarPorId(Long id){
         return manager.find(Aluno.class, id);
    }

    @Override
    public void atualizar(Aluno aluno) {
     manager.getTransaction().begin();
        manager.merge(aluno);
        manager.getTransaction().commit();
        manager.close(); 
    }

    @Override
    public void remover(Long id) {
        manager.getTransaction().begin();
        Aluno aluno = manager.find(Aluno.class, id);
        if (aluno != null) {
        manager.remove(aluno);
        }
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List listar() {
        TypedQuery<Aluno> query = manager.createQuery("SELECT a FROM Aluno a", Aluno.class);
        List<Aluno> alunos = query.getResultList();
        manager.close();
        return alunos;
    }
    
}
