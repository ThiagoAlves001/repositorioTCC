package br.com.repositoriotcc.controller;

//import java.util.Optional;

import br.com.repositoriotcc.model.ConvidadoModel;
import br.com.repositoriotcc.service.ConvidadoService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConvidadoController implements Serializable{

    @Autowired
    private ConvidadoService service;

    @RequestMapping(value={"/", "index", ""})
    public String index() {
        return "redirect:/listaTrabalhosCards";
    }
    
    @RequestMapping("login")
    public String login() {
        return "/index";
    }

    @RequestMapping("listaConvidados")
    public String listaConvidados(Model model) {

        Iterable<ConvidadoModel> listaDeConvidados = service.obterTodos();
        model.addAttribute("convidados", listaDeConvidados);

        return "convidados/listaConvidados";
    }

    @RequestMapping(value = "salvarConvidado", method = RequestMethod.POST)
    public String salvarConvidado(Model model, ConvidadoModel convidado) {// @RequestParam("nome") String nome,
        // @RequestParam("email") String email,
        // @RequestParam("telefone") String telefone ) {

        service.salvar(convidado);

        //new EmailService().enviar(convidado.getNome(), convidado.getEmail(), "remetentesenha", "remetenteemail");

        Iterable<ConvidadoModel> listaDeConvidados = service.obterTodos();
        model.addAttribute("convidados", listaDeConvidados);
        return "listaConvidados";
    }

    @RequestMapping("deletarConvidado")
    public String deletarConvidado(Model model, @RequestParam("id") Long id) {
        service.deletarPorId(id);

        Iterable<ConvidadoModel> listaDeConvidados = service.obterTodos();
        model.addAttribute("convidados", listaDeConvidados);
        return "listaConvidados";
    }

    @RequestMapping("buscarConvidado")
    public String buscarConvidado(Model model, @RequestParam("nome") String nome) {
        Iterable<ConvidadoModel> listaDeConvidados;
        if (nome.equals("")) {
            listaDeConvidados = service.obterTodos();
        } else {
            listaDeConvidados = service.obterTodosPorNome(nome);
        }
        model.addAttribute("convidados", listaDeConvidados);
        return "listaConvidados";
    }
}
