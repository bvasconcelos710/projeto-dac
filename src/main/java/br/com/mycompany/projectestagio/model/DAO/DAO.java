/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.model.DAO;

import java.util.List;

/**
 *
 * @author Bruno
 */
public interface DAO<T> {
    void inserir(T entidade);
    
    T buscarPorId(Long id);
    
    void atualizar(T entidade);
    
    void remover(Long id);
    
    List<T> listar();
}
