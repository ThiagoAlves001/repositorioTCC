/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.controller;

import br.com.repositoriotcc.model.AlunoModel;
import br.com.repositoriotcc.model.BancaModel;
import br.com.repositoriotcc.model.CursoModel;
import br.com.repositoriotcc.model.PessoaModel;
import br.com.repositoriotcc.model.TrabalhoModel;
import br.com.repositoriotcc.service.AlunoService;
import br.com.repositoriotcc.service.BancaService;
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
public class BancaController {

    @Autowired
    private BancaService service;  
    @Autowired
    private TrabalhoService trabalhoService;    
    @Autowired
    private PessoaService pessoasService;    
    @Autowired
    private CursoService cursoService;
    @Autowired
    private AlunoService alunoService;        
    
   @RequestMapping("listaBancas")
    public String listaBanca(Model model) {

        Iterable<BancaModel> listaDeBancas = service.obterTodos();
        model.addAttribute("bancas", listaDeBancas);
        model.addAttribute("orientadores", pessoasService.obterTodos());
        model.addAttribute("listaDeTrabalhos", trabalhoService.obterTodos());
        model.addAttribute("listaDeCursos", cursoService.obterTodos());
        model.addAttribute("listaDeAlunos", alunoService.obterTodos());
        model.addAttribute("aluno", new AlunoModel());
        
        return "bancas/listaBancas";
    }

    @RequestMapping(value = "salvarBanca", method = RequestMethod.POST)
    public String salvarBanca(Model model, BancaModel banca, @RequestParam("orientadorID") Long orientadorID,
            @RequestParam("trabalhoID") Long trabalhoID, 
            @RequestParam("cursoID") Long cursoID, AlunoModel aluno) {
        
        
        
        
        PessoaModel pessoa = pessoasService.obterPorId(orientadorID);
        TrabalhoModel trabalho = trabalhoService.obterPorId(trabalhoID);
        CursoModel curso = cursoService.obterPorId(cursoID);
        aluno = alunoService.obterPorId(aluno.getId());
        banca.setId(null);
        banca.setOrientador(pessoa);
        banca.setTrabalho(trabalho);
        banca.setCurso(curso);
        banca.setAluno(aluno);
        service.salvar(banca);
        
        Iterable<BancaModel> listaDeBancas = service.obterTodos();
        model.addAttribute("bancas", listaDeBancas);
        model.addAttribute("trabalhos", trabalhoService.obterTodos());
        model.addAttribute("orientadores", pessoasService.obterTodos());
        model.addAttribute("listaDeCursos", cursoService.obterTodos());
        model.addAttribute("listaDeAlunos", alunoService.obterTodos());
        model.addAttribute("aluno", new AlunoModel());
        return "bancas/listaBancas";
    }

    
    
            
    @RequestMapping("deletarBanca")
    public String deletarBanca(Model model, @RequestParam("id") Long id) {
        service.deletarPorId(id);

        Iterable<BancaModel> listaDeBancas = service.obterTodos();
        model.addAttribute("bancas", listaDeBancas);
        return "bancas/listaBancas";
    }

    @RequestMapping("buscarBanca")
    public String buscarBanca(Model model, @RequestParam("dataDaBanca") String dataDaBanca) {
        Iterable<BancaModel> listaDeBancas;
        if (dataDaBanca.equals("")) {
            listaDeBancas = service.obterTodos();
        } else {
            listaDeBancas = service.obterTodosPorData(dataDaBanca);
        }
        model.addAttribute("bancas", listaDeBancas);
        return "bancas/listaBancas";
    }
    
    
}

    
    
    
