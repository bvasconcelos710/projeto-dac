/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.projectestagio.factory;

import br.com.mycompany.projectestagio.DAO.AlunoDAO;
import br.com.mycompany.projectestagio.DAO.EmpresaDAO;
import br.com.mycompany.projectestagio.DAO.EstagioDAO;
import br.com.mycompany.projectestagio.DAO.OrientadorDAO;
import br.com.mycompany.projectestagio.entities.Aluno;
import br.com.mycompany.projectestagio.entities.Empresa;
import br.com.mycompany.projectestagio.entities.Estagio;
import br.com.mycompany.projectestagio.entities.Orientador;

import java.util.List;

public class Factory {

    public static void main(String[] args) {

//          Inserindo aluno
        AlunoDAO alunoDao = new AlunoDAO();
//        
//          Aluno aluno = new Aluno("Bruno", "748954");
//          alunoDao.inserir(aluno);


//          Atualizando aluno;
//          Aluno a = new Aluno(3L, "Joaquim da Silva", "748599");
//          alunoDao.atualizar(a);


//          Remover aluno
//          alunoDao.remover(6L);


//          Listando alunos
//          List<Aluno> alunos = alunoDao.listar();
//          for(Aluno a: alunos){
//             System.out.println("Nome: " + a.getNome());
//             System.out.println("Matricula: " + a.getMatricula());
//            }

//---------------------------------------------------------------------------------------------------
//         Inserindo empresa
        EmpresaDAO empresaDao = new EmpresaDAO();
//         Empresa empresa = new Empresa("Casas Bahia", "12456");          
//         empresaDao.inserir(empresa);


 //        Associando aluno a empresa
//           Empresa empresa = empresaDao.buscarPorId(8L);
//           Aluno aluno = alunoDao.buscarPorId(4L);
//            
//            empresa.getAlunos().add(aluno);
//            empresaDao.atualizar(empresa);


 //        Atualizando empresa
//           Empresa empresa = new Empresa(8L, "Casa", "42157");
//           empresaDao.atualizar(empresa);


//         Removendo empresa
//           empresaDao.remover(5L);


 //        Listar empresas
//            List<Empresa> empresas = empresaDao.listar();
//              for(Empresa e: empresas){
//               System.out.println("Nome: " + e.getNome());
//               System.out.println("Cnpj: " + e.getCnpj());
//            }

//------------------------------------------------------------------------------------------------
//         Inserindo orientador

          OrientadorDAO orientadorDao = new OrientadorDAO();
//           Orientador orientador = new Orientador("Daladier", "Redes");
//           orientadorDao.inserir(orientador);


//         Associando aluno a orientador

//           Orientador orientador = orientadorDao.buscarPorId(5L);
//           Aluno aluno = alunoDao.buscarPorId(5L);
//            
//            orientador.getAlunos().add(aluno);
//            orientadorDao.atualizar(orientador);


//         Atualizando orientador

//           Orientador orientador = new Orientador(3L, "Cristiano Silva", "dac");
//           orientadorDao.atualizar(orientador);


//         Removendo orientador

//           orientadorDao.remover(5L);


//         Listar orientadores

//          List<Orientador> orientadores = orientadorDao.listar();
//             for(Orientador o: orientadores){
//               System.out.println("Nome: " + o.getNome());
//               System.out.println("Disciplina: " + o.getDisciplina());
//            }

// ----------------------------------------------------------------------------------------------------
//         Inserindo estágio

           EstagioDAO estagioDao = new EstagioDAO();
           
//           Aluno aluno = alunoDao.buscarPorId(5L);
//           Empresa empresa = empresaDao.buscarPorId(8L);
//           Orientador orientador = orientadorDao.buscarPorId(1L);
//           
//           Estagio estagio = new Estagio("01/01/23", "01/07/23", 6, 60, "finalizado", aluno, empresa, orientador );
//           estagioDao.inserir(estagio);


//          Atualizando estágio

//           Aluno aluno = alunoDao.buscarPorId(2L);
//           Empresa empresa = empresaDao.buscarPorId(2L);
//           Orientador orientador = orientadorDao.buscarPorId(2L);
//            Estagio estagio = new Estagio(1L,"01/06/23", "01/12/23", 6, 60, "finalizado", aluno, empresa, orientador );
//            estagioDao.atualizar(estagio);


//           Removendo estágio
//
//             estagioDao.remover(1L);


//           Listar estagios

//           List<Estagio> estagios = estagioDao.listar();
//             for(Estagio e: estagios){
//               System.out.println("Aluno: " + e.getAluno().getNome());
//               System.out.println("Empresa: " + e.getEmpresa().getNome());
//               System.out.println("Orientador: " + e.getOrientador().getNome());
//               System.out.println("Status: " + e.getStatus());
//               System.out.println("Carga Horaria: " + e.getCargaHoraria());
//               System.out.println("Total de Horas: " + e.getTotalHoras());
//               System.out.println("Inicio: " + e.getInicio());
//               System.out.println("Fim: " + e.getFim());
//               }

//-----------------------------------------------------------------------------------------
//           Filtragens

             Estagio estagio = estagioDao.filtrarEstagioPorMatricula("748599");
             System.out.println("Nome: " + estagio.getAluno().getNome());
             System.out.println("Status: " + estagio.getStatus());



        
        
        
        
        

    }
}
