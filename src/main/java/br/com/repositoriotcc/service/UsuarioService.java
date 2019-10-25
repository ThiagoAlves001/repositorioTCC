/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.service;

import br.com.repositoriotcc.model.UsuarioModel;
import br.com.repositoriotcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    public Iterable<UsuarioModel> obterTodos(){
        Iterable<UsuarioModel> usuarios = repository.findAll();
        return usuarios;
    }

    public Iterable<UsuarioModel> obterTodosPorLogin(String login){
        Iterable<UsuarioModel> usuarios = repository.findByLogin(login);
        return usuarios;
    }
    
    public void salvar(UsuarioModel usuario){
        repository.save(usuario);
    }
    
    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}

