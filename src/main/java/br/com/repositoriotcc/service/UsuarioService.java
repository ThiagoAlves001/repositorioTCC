/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.service;

import br.com.repositoriotcc.model.UsuarioModel;
import br.com.repositoriotcc.repository.UsuarioRepository;
import java.util.List;
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

    public UsuarioModel obterPorLogin(String login){
        List<UsuarioModel> usuarios = repository.findByLogin(login);
        if (usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(0);
    }
    
    public void salvar(UsuarioModel usuario){
        repository.save(usuario);
    }
    
    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}

