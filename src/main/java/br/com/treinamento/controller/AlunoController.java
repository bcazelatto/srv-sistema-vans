package br.com.treinamento.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.treinamento.dtos.request.atualizar.AtualizarAlunoDto;
import br.com.treinamento.dtos.request.cadastrar.CadastrarAlunoDto;
import br.com.treinamento.dtos.response.DetalhamentoAlunoDto;
import br.com.treinamento.model.Aluno;
import br.com.treinamento.repositories.AlunosRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "alunos")
@Schema(name = "Aluno Controller", description = "Controller Principal")
public class AlunoController {
	
	@Autowired
	AlunosRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid CadastrarAlunoDto dados, UriComponentsBuilder uriBuilder) {
		var aluno = new Aluno(dados);
		repository.save(aluno);
		
		var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DetalhamentoAlunoDto(aluno));
	}
	
	@GetMapping("/todos")
	public ResponseEntity<List<DetalhamentoAlunoDto>> listarTodos() {
		List<DetalhamentoAlunoDto> alunos = repository.findAll().stream().map(DetalhamentoAlunoDto::new).collect(Collectors.toList());
		
		return ResponseEntity.ok(alunos);
	}
	
	@GetMapping("/ativos")
	public ResponseEntity<List<DetalhamentoAlunoDto>> listarAtivos() {
		List<DetalhamentoAlunoDto> alunosAtivos = repository.findAllByAtivoTrue().stream().map(DetalhamentoAlunoDto::new).collect(Collectors.toList());
		
		return ResponseEntity.ok(alunosAtivos);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid AtualizarAlunoDto dados) {
		var aluno = repository.getReferenceById(dados.id());
		aluno.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DetalhamentoAlunoDto(aluno));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var aluno = repository.getReferenceById(id);
		aluno.excluir();
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity listarUmAluno(@PathVariable Long id) {
		var aluno = repository.getReferenceById(id);
		return ResponseEntity.ok(new DetalhamentoAlunoDto(aluno));
	}
	
	
	
}
