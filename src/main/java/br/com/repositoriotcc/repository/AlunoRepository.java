/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.repository;

import br.com.repositoriotcc.model.AlunoModel;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AlunoRepository extends CrudRepository<AlunoModel, Long>{
	List<AlunoModel> findByNome(String nome);
}
