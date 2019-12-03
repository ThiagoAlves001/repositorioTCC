/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.controller;


import br.com.repositoriotcc.model.PessoaModel;
import br.com.repositoriotcc.service.PessoaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PessoaController {

    @Autowired
    private PessoaService service;

   //@RequestMapping("/")
    //public String index() {
      //  return "index";
    //}
   //@RequestMapping("lista")
   // public String pessoa() {
    //    return "listaPessoas";
   //}
    @RequestMapping("listaPessoas")
    public String listaPessoa(Model model) {

        Iterable<PessoaModel> listaDePessoas = service.obterTodos();
        model.addAttribute("pessoas", listaDePessoas);

        return "pessoas/listaPessoas";
    }

    @RequestMapping(value = "salvarPessoa", method = RequestMethod.POST)
    public String salvarPessoa(Model model, PessoaModel pessoa) {// @RequestParam("nome") String nome,
        // @RequestParam("email") String email,
        // @RequestParam("telefone") String telefone ) {

        service.salvar(pessoa);

        //new EmailService().enviar(convidado.getNome(), convidado.getEmail(), "remetentesenha", "remetenteemail");

        Iterable<PessoaModel> listaDePessoas = service.obterTodos();
        model.addAttribute("pessoas", listaDePessoas);
        return "pessoas/listaPessoas";
    }

    @RequestMapping("deletarPessoa")
    public String deletarPessoa(Model model, @RequestParam("id") Long id) {
        service.deletarPorId(id);

        Iterable<PessoaModel> listaDePessoas = service.obterTodos();
        model.addAttribute("pessoas", listaDePessoas);
        return "pessoas/listaPessoas";
    }

    @RequestMapping("buscarPessoa")
    public String buscarPessoa(Model model, @RequestParam("nome") String nome) {
        Iterable<PessoaModel> listaDePessoas;
        if (nome.equals("")) {
            listaDePessoas = service.obterTodos();
        } else {
            listaDePessoas = service.obterTodosPorNome(nome);
        }
        model.addAttribute("pessoas", listaDePessoas);
        return "pessoas/listaPessoas";
    }
    
    @RequestMapping("testeValidacao")
    public String testeValidacao(Model model) {
         
         Iterable<PessoaModel> testeValidacao = service.obterTodos();
         model.addAttribute("pessoas", testeValidacao);

        return "pessoas/testeValidacao";
        
    }
    
     
    @RequestMapping(value = "detalhesPessoa", method = RequestMethod.GET)
    public String detalhesPessoa(Model model, @RequestParam("idPessoa") Long idPessoa) {
        PessoaModel pessoa = service.obterPorId(idPessoa);
        model.addAttribute("pessoa", pessoa);
        return "pessoas/detalhesPessoa";
    }
    @RequestMapping("pesquisaOrientador")
    public String oesquisaOrientador(Model model) {
         
         Iterable<PessoaModel> testeValidacao = service.obterTodos();
         model.addAttribute("pessoas", testeValidacao);

        return "pessoas/pesquisaPessoas";
    }    
}
