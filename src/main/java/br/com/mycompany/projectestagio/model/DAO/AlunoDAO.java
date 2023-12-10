/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.model.DAO;

import br.com.mycompany.projectestagio.model.utils.PersistenceFactory;
import br.com.mycompany.projectestagio.model.entities.Aluno;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;


public class AlunoDAO implements DAO<Aluno>, Serializable {
    private static final long serialVersionUID = 1L;
    
    private EntityManager manager = PersistenceFactory.getEntityManager();

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
    public List<Aluno> listar() {
        TypedQuery<Aluno> query = manager.createQuery("SELECT a FROM Aluno a", Aluno.class);
        List<Aluno> alunos = query.getResultList();
        manager.close();
        return alunos;
    }
    
}
