package br.com.treinamento.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.treinamento.dtos.request.atualizar.AtualizarAlunoDto;
import br.com.treinamento.dtos.request.cadastrar.CadastrarAlunoDto;
import br.com.treinamento.dtos.request.cadastrar.CadastrarEnderecoEscolarDto;
import br.com.treinamento.dtos.request.cadastrar.CadastrarEnderecoPessoalDto;
import br.com.treinamento.dtos.response.DetalhamentoAlunoDto;
import br.com.treinamento.model.Aluno;
import br.com.treinamento.repositories.AlunosRepository;

@ExtendWith(MockitoExtension.class)
class AlunoControllerTest {

	@Mock
	private AlunosRepository repository;
	
	@InjectMocks
	private AlunoController alunoController;
	
	@Test
	void testeListarTodosRetornandoNull() {
		
		when(repository.findAll()).thenReturn(null).thenThrow(NullPointerException.class);
		
		//ResponseEntity<List<DetalhamentoAlunoDto>> responseEntity = alunoController.listarTodos();
		
	    assertThrows(NullPointerException.class, () -> {
	    	alunoController.listarTodos();
	    });
	}
	
	@Test
	void testeCadastrar() throws URISyntaxException {
		//var dados = new Aluno(new CadastrarAlunoDto("teste1", "teste1@teste.com", 10, 36281744810L, "Escola1", new CadastrarEnderecoPessoalDto(null, null, null, null, null, null, null), new CadastrarEnderecoEscolarDto(null, null, null, null, null, null, null)));
		var dadosDto = new CadastrarAlunoDto("teste1", "teste1@teste.com", 10, 36281744810L, "Escola1", new CadastrarEnderecoPessoalDto(null, null, null, null, null, null, null), new CadastrarEnderecoEscolarDto(null, null, null, null, null, null, null));
		UriComponentsBuilder uriBuilder = mock(UriComponentsBuilder.class);
	    UriComponents uriComponents = mock(UriComponents.class);

		
	    when(uriBuilder.path(anyString())).thenReturn(uriBuilder);
	    when(uriBuilder.buildAndExpand(any(Object[].class))).thenReturn(uriComponents);

	    when(uriComponents.toUri()).thenReturn(new URI("http://localhost/alunos/1")); // Adiciona esta linha

	    when(repository.save(any(Aluno.class))).thenAnswer(invocation -> {
	        Aluno alunoSalvo = invocation.getArgument(0);
	        alunoSalvo.setId(1L);
	        return alunoSalvo;
	    });

	    ResponseEntity<?> responseEntity = alunoController.cadastrar(dadosDto, uriBuilder);

	    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	    assertEquals("http://localhost/alunos/1", responseEntity.getHeaders().getLocation().toString());
	}
	
	@Test
	void testeListarTodos() {
		Aluno aluno1 = new Aluno(new CadastrarAlunoDto("teste1", "teste1@teste.com", 10, 36281744810L, "Escola1", new CadastrarEnderecoPessoalDto(null, null, null, null, null, null, null), new CadastrarEnderecoEscolarDto(null, null, null, null, null, null, null)));
		Aluno aluno2 = new Aluno(new CadastrarAlunoDto("teste2", "teste2@teste.com", 20, 36281744810L, "Escola2", new CadastrarEnderecoPessoalDto(null, null, null, null, null, null, null), new CadastrarEnderecoEscolarDto(null, null, null, null, null, null, null)));
		
		List<Aluno> alunosMocks = Arrays.asList(aluno1, aluno2);
		
		when(repository.findAll()).thenReturn(alunosMocks);
		
		ResponseEntity<List<DetalhamentoAlunoDto>> responseEntity = alunoController.listarTodos();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	void testeListarAtivos() {
		Aluno aluno1 = new Aluno(new CadastrarAlunoDto("teste1", "teste1@teste.com", 10, 36281744810L, "Escola1", new CadastrarEnderecoPessoalDto(null, null, null, null, null, null, null), new CadastrarEnderecoEscolarDto(null, null, null, null, null, null, null)));
		Aluno aluno2 = new Aluno(new CadastrarAlunoDto("teste2", "teste2@teste.com", 20, 36281744810L, "Escola2", new CadastrarEnderecoPessoalDto(null, null, null, null, null, null, null), new CadastrarEnderecoEscolarDto(null, null, null, null, null, null, null)));
	
		aluno1.setAtivo(true);
		aluno2.setAtivo(false);
		
		List<Aluno> alunosMocks = Arrays.asList(aluno1, aluno2);
		when(repository.findAllByAtivoTrue()).thenReturn(alunosMocks.stream().filter(Aluno ::getAtivo).collect(Collectors.toList()));
		
		ResponseEntity<List<DetalhamentoAlunoDto>> responseEntity = alunoController.listarAtivos();
		
		assertTrue(responseEntity.getBody().get(0).ativo());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	void testeAtualizar() {
		Aluno aluno = new Aluno(new CadastrarAlunoDto("teste1", "teste1@teste.com", 10, 36281744810L, "Escola1", new CadastrarEnderecoPessoalDto(null, null, null, null, null, null, null), new CadastrarEnderecoEscolarDto(null, null, null, null, null, null, null)));
		aluno.setId(1L);
		
		AtualizarAlunoDto update = new AtualizarAlunoDto(1L, "teste1Atualizado", null, null, null, null, null, null);
		
		aluno.atualizarInformacoes(update);
		
		when(repository.getReferenceById(update.id())).thenReturn(aluno);
		
		ResponseEntity<?> responseEntity = alunoController.atualizar(update);
		
	    DetalhamentoAlunoDto detalhamentoAlunoDto = (DetalhamentoAlunoDto) responseEntity.getBody();
		
		assertEquals("teste1Atualizado", aluno.getNome());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    assertEquals(aluno.toString().getClass(), detalhamentoAlunoDto.toString().getClass());

		
	}
	
	@Test
	void testeDeletar() {
		Aluno aluno = new Aluno(new CadastrarAlunoDto("teste1", "teste1@teste.com", 10, 36281744810L, "Escola1", new CadastrarEnderecoPessoalDto(null, null, null, null, null, null, null), new CadastrarEnderecoEscolarDto(null, null, null, null, null, null, null)));
		aluno.setId(1L);
		aluno.excluir();
		
		when(repository.getReferenceById(anyLong())).thenReturn(aluno);
		
		ResponseEntity<?> responseEntity = alunoController.excluir(1L);

		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		
	}
	
	@Test
	void testeListarUmAluno() {
		Aluno aluno1 = new Aluno(new CadastrarAlunoDto("teste1", "teste1@teste.com", 10, 36281744810L, "Escola1", new CadastrarEnderecoPessoalDto(null, null, null, null, null, null, null), new CadastrarEnderecoEscolarDto(null, null, null, null, null, null, null)));
		Aluno aluno2 = new Aluno(new CadastrarAlunoDto("teste2", "teste2@teste.com", 20, 36281744810L, "Escola2", new CadastrarEnderecoPessoalDto(null, null, null, null, null, null, null), new CadastrarEnderecoEscolarDto(null, null, null, null, null, null, null)));
		aluno1.setId(1L);
		aluno2.setId(2L);
		
		List<Aluno> alunosMocks = Arrays.asList(aluno1, aluno2);
		
		when(repository.getReferenceById(anyLong())).thenReturn(alunosMocks.get(0));
		
		ResponseEntity<?> responseEntity = alunoController.listarUmAluno(1L);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    assertEquals(responseEntity.getBody().toString().getClass(), aluno1.toString().getClass());

	}
}
