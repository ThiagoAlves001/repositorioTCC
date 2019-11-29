/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pessoa")
public class PessoaModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private int total_alunos_orientados;
    private String cpf;
    private String link_lattes;
    private String area_conhecimento;
    private String email;
    private int vagas_orientacao;
    private String pessoa_externo;
    private String siape;
    private String funcao;
    private String instituicao;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "pessoasQueParticiparamDaBanca")
    private List<BancaModel> bancasQueParticipou;
    // private int id_pessoa;

    @Transient
    private List<String> nomesParaBuscaEmAtividades;

    public List<String> getNomesParaBuscaEmAtividades() {
        return nomesParaBuscaEmAtividades;
    }

    public void setNomesParaBuscaEmAtividades(List<String> nomesParaBuscaEmAtividades) {
        this.nomesParaBuscaEmAtividades = nomesParaBuscaEmAtividades;
    }
    
    
    
    public List<BancaModel> getBancasQueParticipou() {
        return bancasQueParticipou;
    }

    public void setBancasQueParticipou(List<BancaModel> bancasQueParticipou) {
        this.bancasQueParticipou = bancasQueParticipou;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

     public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTotal_alunos_orientados() {
        return total_alunos_orientados;
    }

    public void setTotal_alunos_orientados(int total_alunos_orientados) {
        this.total_alunos_orientados = total_alunos_orientados;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLink_lattes() {
        return link_lattes;
    }

    public void setLink_lattes(String link_lattes) {
        this.link_lattes = link_lattes;
    }

    public String getArea_conhecimento() {
        return area_conhecimento;
    }

    public void setArea_conhecimento(String area_conhecimento) {
        this.area_conhecimento = area_conhecimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVagas_orientacao() {
        return vagas_orientacao;
    }

    public void setVagas_orientacao(int vagas_orientacao) {
        this.vagas_orientacao = vagas_orientacao;
    }

   
    public String getPessoa_externo() {
        return pessoa_externo;
    }

    public void setPessoa_externo(String pessoa_externo) {
        this.pessoa_externo = pessoa_externo;
    }
    
    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    

    
   
}
