/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.controller;

import br.com.repositoriotcc.model.UsuarioModel;
import br.com.repositoriotcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    
    

    @RequestMapping("listaUsuarios")
    public String listaUsuarios(Model model) {

        Iterable<UsuarioModel> listaDeUsuarios = service.obterTodos();
        model.addAttribute("usuarios", listaDeUsuarios);

        return "usuarios/listaUsuarios";
    }

    @RequestMapping(value = "salvarUsuario", method = RequestMethod.POST)
    public String salvarUsuario(Model model, UsuarioModel usuario) {// @RequestParam("nome") String nome,
        // @RequestParam("email") String email,
        // @RequestParam("telefone") String telefone ) {

        service.salvar(usuario);

        //new EmailService().enviar(convidado.getNome(), convidado.getEmail(), "remetentesenha", "remetenteemail");

        Iterable<UsuarioModel> listaDeUsuarios = service.obterTodos();
        model.addAttribute("usuarios", listaDeUsuarios);
        return "listaUsuarios";
    }

    @RequestMapping("deletarUsuario")
    public String deletarUsuario(Model model, @RequestParam("id") Long id) {
        service.deletarPorId(id);

        Iterable<UsuarioModel> listaDeUsuarios = service.obterTodos();
        model.addAttribute("usuarios", listaDeUsuarios);
        return "listaUsuarios";
    }

    @RequestMapping("buscarUsuario")
    public String buscarUsuario(Model model, @RequestParam("login") String login) {
        Iterable<UsuarioModel> listaDeUsuarios;
        if (login.equals("")) {
            listaDeUsuarios = service.obterTodos();
        } else {
            //listaDeUsuarios = service.obterTodosPorLogin(login);
        }
        //model.addAttribute("usuarios", listaDeUsuarios);
        return "listaUsuarios";
    }
}
