/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "banca_Avaliadora")
public class BancaModel implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
        
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_trabalho", insertable=true, updatable=true)
    @Fetch(org.hibernate.annotations.FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private TrabalhoModel trabalho;//chave estrangeira de pessoa
    
    @DateTimeFormat(pattern="dd/mm/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataDaBanca;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_aluno", insertable=true, updatable=true)
    @Fetch(org.hibernate.annotations.FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private AlunoModel aluno;//chave estrangeira de aluno
    
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
        
    //chave estrangeira de trabalho
    //chave estrangeira de pessoas que compoe banca
    //chave estrangeira de aluno
    //chave estrangeira de pessoa, que no caso é orientador

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

     public TrabalhoModel getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(TrabalhoModel trabalho) {
        this.trabalho = trabalho;
    }
    
    public Calendar getDataDaBanca() {
        return dataDaBanca;
    }

    public void setDataDaBanca(Calendar dataDaBanca) {
        this.dataDaBanca = dataDaBanca;
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
    
    public CursoModel getCurso() {
        return curso;
    }

    public void setCurso(CursoModel curso) {
        this.curso = curso;
    }

   
    
   
}
   