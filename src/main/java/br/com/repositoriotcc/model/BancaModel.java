/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "banca_Avaliadora")
public class BancaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_trabalho", insertable = true, updatable = true)
    @Fetch(org.hibernate.annotations.FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private TrabalhoModel trabalho;//chave estrangeira de pessoa

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataDaBanca;

    @ManyToMany
    @JoinTable(name = "banca_pessoasdabanca",
            joinColumns = @JoinColumn(name = "id_banca"),
            inverseJoinColumns = @JoinColumn(name = "id_pessoa"))
    private List<PessoaModel> pessoasQueParticiparamDaBanca;// ok

   
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

    public List<PessoaModel> getPessoasQueParticiparamDaBanca() {
        return pessoasQueParticiparamDaBanca;
    }

    public void setPessoasQueParticiparamDaBanca(List<PessoaModel> pessoasQueParticiparamDaBanca) {
        this.pessoasQueParticiparamDaBanca = pessoasQueParticiparamDaBanca;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
