package br.com.treinamento.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treinamento.model.Aluno;

@Repository
public interface AlunosRepository extends JpaRepository<Aluno, Long> {

	List<Aluno> findAllByAtivoTrue();

}
