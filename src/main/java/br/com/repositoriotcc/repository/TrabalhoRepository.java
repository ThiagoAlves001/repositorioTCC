/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.repository;

import br.com.repositoriotcc.model.TrabalhoModel;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TrabalhoRepository extends CrudRepository<TrabalhoModel, Long>{
	List<TrabalhoModel> findByTema(String tema);
        
        @Query("select a from TrabalhoModel a where a.dataApresentacao >= :dataApresentacao")
	List<TrabalhoModel> findAllByDataApresentacaoAfter(@Param("dataApresentacao") Calendar dataApresentacao);
}
