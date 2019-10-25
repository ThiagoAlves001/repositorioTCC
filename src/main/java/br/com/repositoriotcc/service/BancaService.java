/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.service;

import br.com.repositoriotcc.model.BancaModel;
import br.com.repositoriotcc.repository.BancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BancaService {

    @Autowired
    private BancaRepository repository;


    public Iterable<BancaModel> obterTodos(){
        Iterable<BancaModel> bancas = repository.findAll();
        return bancas;
    }

    public Iterable<BancaModel> obterTodosPorData(String dataDaBanca){
        Iterable<BancaModel> bancas = repository.findBydataDaBanca(dataDaBanca);
        return bancas;
    }
    
     public BancaModel obterPorId(Long id){
        Optional<BancaModel> bancaOptional = repository.findById(id);
        BancaModel banca = new BancaModel();
        if(bancaOptional.isPresent()){
            banca = bancaOptional.get();
        }
        
        return banca;
    }
    public void salvar(BancaModel banca){
        repository.save(banca);
    }
    
    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}

