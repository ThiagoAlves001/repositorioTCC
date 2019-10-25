package br.com.repositoriotcc.service;

import br.com.repositoriotcc.model.ConvidadoModel;
import br.com.repositoriotcc.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvidadoService {

    @Autowired
    private ConvidadoRepository repository;


    public Iterable<ConvidadoModel> obterTodos(){
        Iterable<ConvidadoModel> convidados = repository.findAll();
        return convidados;
    }

    public Iterable<ConvidadoModel> obterTodosPorNome(String nome){
        Iterable<ConvidadoModel> convidados = repository.findByNome(nome);
        return convidados;
    }
    
    public void salvar(ConvidadoModel convidado){
        repository.save(convidado);
    }
    
    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}
