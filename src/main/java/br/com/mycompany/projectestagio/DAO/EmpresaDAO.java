/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.DAO;

import br.com.mycompany.projectestagio.entities.Empresa;
import br.com.mycompany.projectestagio.factory.PersistenceFactory;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;


public class EmpresaDAO implements DAO<Empresa> {

    private EntityManager manager = PersistenceFactory.getEntityManager();
    
    @Override
    public void inserir(Empresa empresa) {
        manager.getTransaction().begin();
        manager.persist(empresa);
        manager.getTransaction().commit();
        manager.close(); 
    }
    
    @Override
    public Empresa buscarPorId(Long id){
         return manager.find(Empresa.class, id);
    }

    @Override
    public void atualizar(Empresa empresa) {
        manager.getTransaction().begin();
        manager.merge(empresa);
        manager.getTransaction().commit();
        manager.close(); 
    }

    @Override
    public void remover(Long id) {
        manager.getTransaction().begin();
        Empresa empresa = manager.find(Empresa.class, id);
        if (empresa != null) {
        manager.remove(empresa);
        }
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public List listar() {
        TypedQuery<Empresa> query = manager.createQuery("SELECT e FROM Empresa e", Empresa.class);
        List<Empresa> empresas = query.getResultList();
        manager.close();
        return empresas;
    }
    
}
