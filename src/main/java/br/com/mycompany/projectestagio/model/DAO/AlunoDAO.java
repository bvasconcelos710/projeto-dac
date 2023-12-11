/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.model.DAO;

import br.com.mycompany.projectestagio.model.utils.PersistenceFactory;
import br.com.mycompany.projectestagio.model.entities.Aluno;

import java.util.List;

import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;

@Named
public class AlunoDAO implements DAO<Aluno>, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(Aluno aluno) {
        manager.persist(aluno);
    }

    @Override
    public Aluno buscarPorId(Long id) {
        return manager.find(Aluno.class, id);
    }

    @Override
    public void atualizar(Aluno aluno) {
        manager.merge(aluno);
    }

    @Override
    public void remover(Long id) {
        Aluno aluno = manager.find(Aluno.class, id);
        if (aluno != null) {
            manager.remove(aluno);
        }
    }

    @Override
    public List<Aluno> listar() {
        TypedQuery<Aluno> query = manager.createQuery("SELECT a FROM Aluno a", Aluno.class);
        List<Aluno> alunos = query.getResultList();
        return alunos;
    }

}
