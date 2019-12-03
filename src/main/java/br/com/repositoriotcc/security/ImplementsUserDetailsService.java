/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.security;

import br.com.repositoriotcc.model.UsuarioModel;
import br.com.repositoriotcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Frank
 */
@Repository
public class ImplementsUserDetailsService implements UserDetailsService{

    @Autowired
    UsuarioService usuarioSer;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UsuarioModel usuario = usuarioSer.obterPorLogin(login);
        return usuario;
    }
    
}
