/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.controller;

import br.com.repositoriotcc.model.AlunoModel;
import br.com.repositoriotcc.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlunoController {

    @Autowired
    private AlunoService service;

    //@RequestMapping("/")
    //public String index() {
      //  return "index";
   // }
    

    @RequestMapping("listaAlunos")
    public String listaAlunos(Model model) {

        Iterable<AlunoModel> listaDeAlunos = service.obterTodos();
        model.addAttribute("alunos", listaDeAlunos);

        return "alunos/listaAlunos";
    }

    @RequestMapping(value = "salvarAluno", method = RequestMethod.POST)
    public String salvarAluno(Model model, AlunoModel aluno) {// @RequestParam("nome") String nome,
        // @RequestParam("email") String email,
        // @RequestParam("telefone") String telefone ) {

        service.salvar(aluno);

        //new EmailService().enviar(convidado.getNome(), convidado.getEmail(), "remetentesenha", "remetenteemail");

        Iterable<AlunoModel> listaDeAlunos = service.obterTodos();
        model.addAttribute("alunos", listaDeAlunos);
        return "alunos/listaAlunos";
    }

    @RequestMapping("deletarAluno")
    public String deletarAluno(Model model, @RequestParam("id") Long id) {
        service.deletarPorId(id);

        Iterable<AlunoModel> listaDeAlunos = service.obterTodos();
        model.addAttribute("alunos", listaDeAlunos);
        return "alunos/listaAlunos";
    }

     @RequestMapping(value = "detalhesAluno", method = RequestMethod.GET)
    public String detalhesAluno(Model model, @RequestParam("idAluno") Long idAluno) {
        AlunoModel aluno = service.obterPorId(idAluno);
        model.addAttribute("aluno", aluno);
        return "alunos/detalhesAluno";
    }
    
    @RequestMapping("buscarAluno")
    public String buscarAluno(Model model, @RequestParam("nome") String nome) {
        Iterable<AlunoModel> listaDeAlunos;
        if (nome.equals("")) {
            listaDeAlunos = service.obterTodos();
        } else {
            listaDeAlunos = service.obterTodosPorNome(nome);
        }
        model.addAttribute("alunos", listaDeAlunos);
        return "alunos/listaAlunos";
    }
     @RequestMapping(value = {"editarAluno"}, method = RequestMethod.GET)
    public String editarAlunoGet(Model model, @RequestParam("idAluno") Long idAluno) {
        Iterable<AlunoModel> listaDeAlunos = service.obterTodos();
        model.addAttribute("alunos", listaDeAlunos);
        
        AlunoModel aluno = service.obterPorId(idAluno);
        model.addAttribute("aluno", aluno);
        
        
        return "alunos/editarAluno";
    }

    @RequestMapping(value = "editarAluno", method = RequestMethod.POST)
    public String editarAlunoPost(Model model, AlunoModel aluno) {
        
        
        service.salvar(aluno);
        
       return "redirect:/listaAlunos";
    }
}