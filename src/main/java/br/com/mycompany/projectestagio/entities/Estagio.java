/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Estagio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String inicio;
    private String fim;
    private int cargaHoraria;
    private int totalHoras;
    private String status;

    @OneToOne
    private Aluno aluno;

    @OneToOne
    private Empresa empresa;

    @OneToOne
    private Orientador orientador;

    public Estagio(String inicio, String fim, int cargaHoraria, int totalHoras, String status, Aluno aluno, Empresa empresa, Orientador orientador) {
        this.inicio = inicio;
        this.fim = fim;
        this.cargaHoraria = cargaHoraria;
        this.totalHoras = totalHoras;
        this.status = status;
        this.aluno = aluno;
        this.empresa = empresa;
        this.orientador = orientador;
    }

    public Estagio(Long id, String inicio, String fim, int cargaHoraria, int totalHoras, String status, Aluno aluno, Empresa empresa, Orientador orientador) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.cargaHoraria = cargaHoraria;
        this.totalHoras = totalHoras;
        this.status = status;
        this.aluno = aluno;
        this.empresa = empresa;
        this.orientador = orientador;
    }
    
    
    public Estagio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }
        
    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }
        
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
        
    public int getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(int totalHoras) {
        this.totalHoras = totalHoras;
    }
      
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
       
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
       
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }
   
        
}
