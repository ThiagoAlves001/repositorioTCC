package br.com.repositoriotcc.repository;

import br.com.repositoriotcc.model.ConvidadoModel;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<ConvidadoModel, Long>{
	List<ConvidadoModel> findByNome(String nome);
}
