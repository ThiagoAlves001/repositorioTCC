/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.service;

import br.com.repositoriotcc.model.AlunoModel;
import br.com.repositoriotcc.model.PessoaModel;
import br.com.repositoriotcc.repository.AlunoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;


    public Iterable<AlunoModel> obterTodos(){
        Iterable<AlunoModel> alunos = repository.findAll();
        return alunos;
    }

    public Iterable<AlunoModel> obterTodosPorNome(String nome){
        Iterable<AlunoModel> alunos = repository.findByNome(nome);
        return alunos;
    }
    
    public AlunoModel obterPorId(Long id){
        Optional<AlunoModel> pessoaOptional = repository.findById(id);
        AlunoModel pessoaAluno = new AlunoModel();
        if(pessoaOptional.isPresent()){
            pessoaAluno = pessoaOptional.get();
        }
        
        return pessoaAluno;
    }
    
    public void salvar(AlunoModel aluno){
        repository.save(aluno);
    }
    
    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}
