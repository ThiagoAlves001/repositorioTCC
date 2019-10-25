/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.service;

import br.com.repositoriotcc.model.PessoaModel;
import br.com.repositoriotcc.repository.PessoaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;


    public Iterable<PessoaModel> obterTodos(){
        Iterable<PessoaModel> pessoas = repository.findAll();
        return pessoas;
    }

    public Iterable<PessoaModel> obterTodosPorNome(String nome){
        Iterable<PessoaModel> pessoas = repository.findByNome(nome);
        return pessoas;
    }
    
    public PessoaModel obterPorId(Long id){
        Optional<PessoaModel> pessoaOptional = repository.findById(id);
        PessoaModel pessoa = new PessoaModel();
        if(pessoaOptional.isPresent()){
            pessoa = pessoaOptional.get();
        }
        
        return pessoa;
    }
    
    public void salvar(PessoaModel pessoa){
        repository.save(pessoa);
    }
    
    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}