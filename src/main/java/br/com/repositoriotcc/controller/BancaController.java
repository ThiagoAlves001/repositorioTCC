/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.controller;

import br.com.repositoriotcc.model.AlunoModel;
import br.com.repositoriotcc.model.BancaModel;
import br.com.repositoriotcc.model.PessoaModel;
import br.com.repositoriotcc.model.TrabalhoModel;
import br.com.repositoriotcc.service.AlunoService;
import br.com.repositoriotcc.service.BancaService;
import br.com.repositoriotcc.service.CursoService;
import br.com.repositoriotcc.service.PessoaService;
import br.com.repositoriotcc.service.TrabalhoService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BancaController {

    @Autowired
    private BancaService service;
    @Autowired
    private TrabalhoService trabalhoService;
    @Autowired
    private PessoaService pessoasService;

    @RequestMapping("listaBancas")
    public String listaBanca(Model model) {

        Iterable<BancaModel> listaDeBancas = service.obterTodos();
        model.addAttribute("bancas", listaDeBancas);
        //model.addAttribute("orientadores", pessoasService.obterTodos());
        model.addAttribute("listaDeTrabalhos", trabalhoService.obterTodos());
        // model.addAttribute("listaDeCursos", cursoService.obterTodos());
        //model.addAttribute("listaDeAlunos", alunoService.obterTodos());
        //model.addAttribute("aluno", new AlunoModel());

        return "bancas/listaBancas";
    }

    @RequestMapping(value = "salvarBanca", method = RequestMethod.POST)
    public String salvarBanca(Model model, BancaModel banca, PessoaModel pessoasBanca, @RequestParam("trabalhoID") Long trabalhoID) {
        List<PessoaModel> listaDePessoas = retirarIdsPessoas(pessoasBanca.getNomesParaBuscaEmAtividades());
        if (listaDePessoas.size() == pessoasBanca.getNomesParaBuscaEmAtividades().size()) {
            banca.setPessoasQueParticiparamDaBanca(listaDePessoas);
            TrabalhoModel trabalho = trabalhoService.obterPorId(trabalhoID);
            banca.setTrabalho(trabalho);
            service.salvar(banca);
        }
        return "redirect:/listaBancas";
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

    @RequestMapping(value = "detalhesBanca", method = RequestMethod.GET)
    public String detalhesBanca(Model model, @RequestParam("idBanca") Long idBanca) {
        BancaModel banca = service.obterPorId(idBanca);
        model.addAttribute("banca", banca);
        return "bancas/detalhesBanca";
    }

    @RequestMapping(value = "editarBanca", method = RequestMethod.GET)
    public String editarBanca(Model model, @RequestParam("idBanca") Long idBanca) {
        BancaModel banca = service.obterPorId(idBanca);
        model.addAttribute("banca", banca);
        model.addAttribute("listaDeTrabalhos", trabalhoService.obterTodos());
        PessoaModel pessoa = new PessoaModel();
        List<String> nomes = new ArrayList<String>();
        for (PessoaModel pessoaMod : banca.getPessoasQueParticiparamDaBanca()) {
            nomes.add(pessoaMod.getNome() + "-" + pessoaMod.getId());
        }
        pessoa.setNomesParaBuscaEmAtividades(nomes);
        model.addAttribute("pessoa", pessoa);
        
        return "bancas/editarBanca";
    }
    
    @RequestMapping(value = "editarBanca", method = RequestMethod.POST)
    public String editarBancaPost(Model model, BancaModel banca, PessoaModel pessoasBanca, @RequestParam("trabalhoID") Long trabalhoID) {
        List<PessoaModel> listaDePessoas = retirarIdsPessoas(pessoasBanca.getNomesParaBuscaEmAtividades());
        if (listaDePessoas.size() == pessoasBanca.getNomesParaBuscaEmAtividades().size()) {
            banca.setPessoasQueParticiparamDaBanca(listaDePessoas);
            TrabalhoModel trabalho = trabalhoService.obterPorId(trabalhoID);
            banca.setTrabalho(trabalho);
            service.salvar(banca);
        }
        return "redirect:/listaBancas";
    }

    @RequestMapping(value = "/buscarPessoaPorAjax", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Iterator<PessoaModel> buscarPessoaPorAjax(@RequestBody(required = true) Map<String, Object> corpo) {
        Iterable<PessoaModel> pessoas = pessoasService.buscarPorNome(corpo.get("nomeParaBusca").toString());
        return pessoas.iterator();
    }

    private List<PessoaModel> retirarIdsPessoas(List<String> pessoasRelacionadas) {
        List<PessoaModel> listaDePessoas = new ArrayList<PessoaModel>();
        PessoaModel pessoaOptional;
        Long idPessoa;
        String id;
        for (String pessoaRel : pessoasRelacionadas) {
            id = pessoaRel.substring(pessoaRel.indexOf('-') + 1, pessoaRel.length());
            idPessoa = Long.parseLong(id.trim());
            pessoaOptional = pessoasService.obterPorId(idPessoa);

            listaDePessoas.add(pessoaOptional);
        }

        return listaDePessoas;
    }
}
