/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "trabalho")
public class TrabalhoModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String area_da_pesquisa;
        private String status_de_conclusao;
        private String pdf_tcc;
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="id_pessoa", insertable=true, updatable=true)
        @Fetch(org.hibernate.annotations.FetchMode.JOIN)
        @Cascade(CascadeType.SAVE_UPDATE)
        private PessoaModel orientador;//chave estrangeira de pessoa
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="id_curso", insertable=true, updatable=true)
        @Fetch(org.hibernate.annotations.FetchMode.JOIN)
        @Cascade(CascadeType.SAVE_UPDATE)
        private CursoModel curso;//chave estrangeira de pessoa
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="id_aluno", insertable=true, updatable=true)
        @Fetch(org.hibernate.annotations.FetchMode.JOIN)
        @Cascade(CascadeType.SAVE_UPDATE)
        private AlunoModel aluno;//chave estrangeira de aluno
        
        @JsonIgnore
	@OneToMany(mappedBy="trabalho", fetch = FetchType.EAGER)
	private List<BancaModel> bancasQueFoiRelacionado;
        
        private String co_orientador;//chave estrangeira de pessoa
        private String breve_resumo;
        private String tema;
        
        @DateTimeFormat(pattern="dd/MM/yyyy")
        @Temporal(TemporalType.TIMESTAMP)
        private Calendar dataApresentacao;
        
        private String nota_da_apresentacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea_da_pesquisa() {
        return area_da_pesquisa;
    }

    public void setArea_da_pesquisa(String area_da_pesquisa) {
        this.area_da_pesquisa = area_da_pesquisa;
    }

    public String getStatus_de_conclusao() {
        return status_de_conclusao;
    }

    public void setStatus_de_conclusao(String status_de_conclusao) {
        this.status_de_conclusao = status_de_conclusao;
    }

    public CursoModel getCurso() {
        return curso;
    }

    public void setCurso(CursoModel curso) {
        this.curso = curso;
    }

    public String getPdf_tcc() {
        return pdf_tcc;
    }

    public void setPdf_tcc(String pdf_tcc) {
        this.pdf_tcc = pdf_tcc;
    }

    public PessoaModel getOrientador() {
        return orientador;
    }

    public void setOrientador(PessoaModel orientador) {
        this.orientador = orientador;
    }

    public AlunoModel getAluno() {
        return aluno;
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;
    }

    public String getCo_orientador() {
        return co_orientador;
    }

    public void setCo_orientador(String co_orientador) {
        this.co_orientador = co_orientador;
    }

    public String getBreve_resumo() {
        return breve_resumo;
    }

    public void setBreve_resumo(String breve_resumo) {
        this.breve_resumo = breve_resumo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Calendar getDataApresentacao() {
        return dataApresentacao;
    }

    public void setDataApresentacao(Calendar dataApresentacao) {
        this.dataApresentacao = dataApresentacao;
    }

    public String getNota_da_apresentacao() {
        return nota_da_apresentacao;
    }

    public void setNota_da_apresentacao(String nota_da_apresentacao) {
        this.nota_da_apresentacao = nota_da_apresentacao;
    }

    public List<BancaModel> getBancasQueFoiRelacionado() {
        return bancasQueFoiRelacionado;
    }

    public void setBancasQueFoiRelacionado(List<BancaModel> bancasQueFoiRelacionado) {
        this.bancasQueFoiRelacionado = bancasQueFoiRelacionado;
    } 
	
}