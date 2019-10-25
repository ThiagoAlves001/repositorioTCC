/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.controller;

import br.com.repositoriotcc.model.CursoModel;
import br.com.repositoriotcc.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CursoController {

    @Autowired
    private CursoService service;

    @RequestMapping("listaCursos")
    public String listaCursos(Model model) {

        Iterable<CursoModel> listaDeCursos = service.obterTodos();
        model.addAttribute("cursos", listaDeCursos);

        return "cursos/listaCursos";
    }

    @RequestMapping(value = "salvarCurso", method = RequestMethod.POST)
    public String salvarCurso(Model model, CursoModel curso) {// @RequestParam("nome") String nome,
        // @RequestParam("email") String email,
        // @RequestParam("telefone") String telefone ) {
        service.salvar(curso);

        Iterable<CursoModel> listaDeCursos = service.obterTodos();
        model.addAttribute("cursos", listaDeCursos);
        return "cursos/listaCursos";
    }

    @RequestMapping("deletarCurso")
    public String deletarCurso(Model model, @RequestParam("id") Long id) {
        service.deletarPorId(id);

        Iterable<CursoModel> listaDeCursos = service.obterTodos();
        model.addAttribute("cursos", listaDeCursos);
        return "cursos/listaCursos";
    }

    @RequestMapping("buscarCurso")
    public String buscarCurso(Model model, @RequestParam("nome") String nome) {
        Iterable<CursoModel> listaDeCursos;
        if (nome.equals("")) {
            listaDeCursos = service.obterTodos();
        } else {
            listaDeCursos = service.obterTodosPorNome(nome);
        }
        model.addAttribute("cursos", listaDeCursos);
        return "cursos/listaCursos";
    }
}
