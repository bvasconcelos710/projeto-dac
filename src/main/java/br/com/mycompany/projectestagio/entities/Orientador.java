/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Orientador implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String disciplina;

    @OneToMany
    private Set<Aluno> alunos = new HashSet<>();

    public Orientador() {
    }

    public Orientador(String nome, String disciplina) {
        this.nome = nome;
        this.disciplina = disciplina;
    }

    public Orientador(Long id, String nome, String disciplina) {
        this.id = id;
        this.nome = nome;
        this.disciplina = disciplina;
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

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
                
    public Set<Aluno> getAlunos() {
        return alunos;
    }
   
   }
