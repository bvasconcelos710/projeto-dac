/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.model.DAO;

import br.com.mycompany.projectestagio.model.entities.Empresa;
import br.com.mycompany.projectestagio.model.utils.PersistenceFactory;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

import jakarta.persistence.*;

import java.io.Serializable;

@Named
public class EmpresaDAO implements DAO<Empresa>, Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(Empresa empresa) {
        manager.persist(empresa);
    }
    
    @Override
    public Empresa buscarPorId(Long id){
         return manager.find(Empresa.class, id);
    }

    @Override
    public void atualizar(Empresa empresa) {
        manager.merge(empresa);
    }

    @Override
    public void remover(Long id) {
        Empresa empresa = manager.find(Empresa.class, id);
        if (empresa != null) {
        manager.remove(empresa);
        }
    }

    @Override
    public List<Empresa> listar() {
        TypedQuery<Empresa> query = manager.createQuery("SELECT e FROM Empresa e", Empresa.class);
        List<Empresa> empresas = query.getResultList();
        return empresas;
    }
    
}
