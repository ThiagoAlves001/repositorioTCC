/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.service;

import br.com.repositoriotcc.model.TrabalhoModel;
import br.com.repositoriotcc.repository.TrabalhoRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TrabalhoService {

    @Autowired
    private TrabalhoRepository repository;


    public Iterable<TrabalhoModel> obterTodos(){
        Iterable<TrabalhoModel> trabalhos = repository.findAll();
        return trabalhos;
    }

    public Iterable<TrabalhoModel> obterTodosPorTema(String tema){
        Iterable<TrabalhoModel> trabalhos = repository.findByTema(tema);
        return trabalhos;
    }
    
     public TrabalhoModel obterPorId(Long id){
        Optional<TrabalhoModel> trabalhoOptional = repository.findById(id);
        TrabalhoModel trabalho = new TrabalhoModel();
        if(trabalhoOptional.isPresent()){
            trabalho = trabalhoOptional.get();
        }
        
        return trabalho;
    }
     
     public String getDateTime() { 
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	Date date = new Date(); 
        
	return dateFormat.format(date); 
}
     
    
    public void salvar(TrabalhoModel trabalho){
        repository.save(trabalho);
    }
    
    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}

