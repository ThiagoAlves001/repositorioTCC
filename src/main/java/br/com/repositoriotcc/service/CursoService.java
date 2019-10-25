/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.service;

import br.com.repositoriotcc.model.CursoModel;
import br.com.repositoriotcc.model.PessoaModel;
import br.com.repositoriotcc.repository.CursoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;


    public Iterable<CursoModel> obterTodos(){
        Iterable<CursoModel> cursos = repository.findAll();
        return cursos;
    }

    public Iterable<CursoModel> obterTodosPorNome(String nome){
        Iterable<CursoModel> cursos = repository.findByNome(nome);
        return cursos;
    }
    
     public CursoModel obterPorId(Long id){
        Optional<CursoModel> cursoOptional = repository.findById(id);
        CursoModel curso = new CursoModel();
        if(cursoOptional.isPresent()){
            curso = cursoOptional.get();
        }
        
        return curso;
    }
    
    public void salvar(CursoModel curso){
        repository.save(curso);
    }
    
    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}
