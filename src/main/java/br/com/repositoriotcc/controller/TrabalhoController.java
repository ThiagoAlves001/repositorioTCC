/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.controller;

import br.com.repositoriotcc.model.AlunoModel;
import br.com.repositoriotcc.model.CursoModel;
import br.com.repositoriotcc.model.PessoaModel;
import br.com.repositoriotcc.model.TrabalhoModel;
import br.com.repositoriotcc.service.AlunoService;
import br.com.repositoriotcc.service.CursoService;
import br.com.repositoriotcc.service.PessoaService;
import br.com.repositoriotcc.service.TrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TrabalhoController {

    @Autowired
    private TrabalhoService service;    
    @Autowired
    private PessoaService pessoasService;    
    @Autowired
    private CursoService cursoService;
    @Autowired
    private AlunoService alunoService;        
    
   @RequestMapping("listaTrabalhos")
    public String listaTrabalho(Model model) {

        Iterable<TrabalhoModel> listaDeTrabalhos = service.obterTodos();
        model.addAttribute("trabalhos", listaDeTrabalhos);
        model.addAttribute("orientadores", pessoasService.obterTodos());
        model.addAttribute("listaDeCursos", cursoService.obterTodos());
        model.addAttribute("listaDeAlunos", alunoService.obterTodos());
        model.addAttribute("aluno", new AlunoModel());
        
        return "trabalhos/listaTrabalhos";
    }

    @RequestMapping(value = "salvarTrabalho", method = RequestMethod.POST)
    public String salvarTrabalho(Model model, TrabalhoModel trabalho, @RequestParam("orientadorID") Long orientadorID, 
            @RequestParam("cursoID") Long cursoID, AlunoModel aluno) {
        
        PessoaModel pessoa = pessoasService.obterPorId(orientadorID);
        CursoModel curso = cursoService.obterPorId(cursoID);
        aluno = alunoService.obterPorId(aluno.getId());
        trabalho.setId(null);
        trabalho.setOrientador(pessoa);
        trabalho.setCurso(curso);
        trabalho.setAluno(aluno);
        service.salvar(trabalho);
        
        Iterable<TrabalhoModel> listaDeTrabalhos = service.obterTodos();
        model.addAttribute("trabalhos", listaDeTrabalhos);
        model.addAttribute("orientadores", pessoasService.obterTodos());
        model.addAttribute("listaDeCursos", cursoService.obterTodos());
        model.addAttribute("listaDeAlunos", alunoService.obterTodos());
        model.addAttribute("aluno", new AlunoModel());
        return "trabalhos/listaTrabalhos";
    }

    @RequestMapping("deletarTrabalho")
    public String deletarTrabalho(Model model, @RequestParam("id") Long id) {
        service.deletarPorId(id);

        Iterable<TrabalhoModel> listaDeTrabalhos = service.obterTodos();
        model.addAttribute("trabalhos", listaDeTrabalhos);
        return "trabalhos/listaTrabalhos";
    }

    @RequestMapping("buscarTrabalho")
    public String buscarTrabalho(Model model, @RequestParam("tema") String tema) {
        Iterable<TrabalhoModel> listaDeTrabalhos;
        if (tema.equals("")) {
            listaDeTrabalhos = service.obterTodos();
        } else {
            listaDeTrabalhos = service.obterTodosPorTema(tema);
        }
        model.addAttribute("trabalhos", listaDeTrabalhos);
        return "trabalhos/listaTrabalhos";
    }
    
    @RequestMapping("listaTrabalhosCards")
    public String listaTrabalhoCards(Model model) {

        Iterable<TrabalhoModel> listaDeTrabalhos = service.obterTodos();
        model.addAttribute("trabalhos", listaDeTrabalhos);
        
        return "trabalhos/listaTrabalhosCards";
    }
}
