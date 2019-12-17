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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TrabalhoController implements Serializable{

    @Autowired
    private TrabalhoService service;    
    @Autowired
    private BancaService bancaService;
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
            @RequestParam("cursoID") Long cursoID, AlunoModel aluno, @RequestParam("co_orientadorID") Long co_orientadorID) {
        
        PessoaModel pessoa = pessoasService.obterPorId(orientadorID);
        
        if (co_orientadorID != 0) {
            PessoaModel co_pessoa = pessoasService.obterPorId(co_orientadorID);        
            trabalho.setCo_orientador(co_pessoa);
        }
        
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

        //Iterable<TrabalhoModel> listaDeTrabalhos = service.obterTodos();
        //model.addAttribute("trabalhos", listaDeTrabalhos);
        return "redirect:/listaTrabalhos";
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

        List<TrabalhoModel> listaDeTrabalhosSemBanca = new ArrayList<>();
        Iterable<TrabalhoModel> listaDeTrabalhos = service.obterTodosComDataDeApresentacaoMaiorQueDataAtual();
        for (TrabalhoModel trabalho : listaDeTrabalhos) {
            if (trabalho.getBancasQueFoiRelacionado().isEmpty()) {
                listaDeTrabalhosSemBanca.add(trabalho);
            }
        }
        model.addAttribute("trabalhos", listaDeTrabalhosSemBanca);
        Iterable<BancaModel> listaDeBancas = bancaService.obterTodos();
        model.addAttribute("bancas", listaDeBancas);
        
        return "trabalhos/listaTrabalhosCards";
    }
    @RequestMapping("listaTrabalhosConcluidos")
   public String listaTrabalhoConcluidos(Model model) {

        List<TrabalhoModel> listaDeTrabalhosSemBanca = new ArrayList<>();
        Iterable<TrabalhoModel> listaDeTrabalhos = service.obterTodosComDataDeApresentacaoMenorQueDataAtual();
        for (TrabalhoModel trabalho : listaDeTrabalhos) {
            if (trabalho.getBancasQueFoiRelacionado().isEmpty()) {
                listaDeTrabalhosSemBanca.add(trabalho);
            }
        }
        model.addAttribute("trabalhos", listaDeTrabalhosSemBanca);
        Iterable<BancaModel> listaDeBancas = bancaService.obterTodos();
        model.addAttribute("bancas", listaDeBancas);
        
        return "trabalhos/listaTrabalhosConcluidos";
    }
    
    @RequestMapping(value = "detalhesTrabalho", method = RequestMethod.GET)
    public String detalhesBanca(Model model, @RequestParam("idTrabalho") Long idTrabalho, @RequestParam("idBanca") Long idBanca) {
        TrabalhoModel trabalho = service.obterPorId(idTrabalho);
        model.addAttribute("trabalho", trabalho);
        model.addAttribute("idBanca", idBanca);
        return "trabalhos/detalhesTrabalho";
    }
    
    @RequestMapping(value = {"editarTrabalho"}, method = RequestMethod.GET)
    public String editarTrabalhoGet(Model model, @RequestParam("idTrabalho") Long idTrabalho) {

        TrabalhoModel trabalho = service.obterPorId(idTrabalho);
        model.addAttribute("trabalho", trabalho);
        Iterable<TrabalhoModel> listaDeTrabalhos = service.obterTodos();
        model.addAttribute("trabalhos", listaDeTrabalhos);
        model.addAttribute("orientadores", pessoasService.obterTodos());
        model.addAttribute("listaDeCursos", cursoService.obterTodos());
        model.addAttribute("listaDeAlunos", alunoService.obterTodos());
        model.addAttribute("aluno", new AlunoModel());
        
        return "trabalhos/editarTrabalho";
    }

    @RequestMapping(value = "editarTrabalho", method = RequestMethod.POST)
    public String editarTrabalhoPost(Model model, TrabalhoModel trabalho, @RequestParam("orientadorID") Long orientadorID, 
            @RequestParam("cursoID") Long cursoID, @RequestParam("co_orientadorID") Long co_orientadorID,
            @RequestParam("alunoID") Long alunoID) {
        
        PessoaModel pessoa = pessoasService.obterPorId(orientadorID);
        PessoaModel co_pessoa = pessoasService.obterPorId(co_orientadorID);
        CursoModel curso = cursoService.obterPorId(cursoID);
        AlunoModel aluno = alunoService.obterPorId(alunoID);
        trabalho.setOrientador(pessoa);
        trabalho.setCo_orientador(co_pessoa);
        trabalho.setCurso(curso);
        trabalho.setAluno(aluno);
        service.salvar(trabalho);
        
       return "redirect:/listaTrabalhos";
    }
}
