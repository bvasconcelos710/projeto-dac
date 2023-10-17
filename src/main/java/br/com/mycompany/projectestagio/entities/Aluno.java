/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Aluno implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String matricula;
    
     public Aluno() {
    }

    public Aluno(String nome, String matricula) {
       
        this.nome = nome;
        this.matricula = matricula;
    }

    public Aluno(Long id, String nome, String matricula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
    }
    
           
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


}
