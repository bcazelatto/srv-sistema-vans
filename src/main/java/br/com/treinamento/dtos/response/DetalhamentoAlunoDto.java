package br.com.treinamento.dtos.response;

import br.com.treinamento.model.Aluno;
import br.com.treinamento.model.EnderecoEscolar;
import br.com.treinamento.model.EnderecoPessoal;

public record DetalhamentoAlunoDto(Long id, Boolean ativo, String nome, String email, Integer idade, Long cpf, String nomeEscola, EnderecoPessoal enderecoPessoal, EnderecoEscolar enderecoEscola) {
	
	public DetalhamentoAlunoDto(Aluno aluno) {
		this(aluno.getId(),aluno.getAtivo(), aluno.getNome(), aluno.getEmail(), aluno.getIdade(), aluno.getCpf(), aluno.getNomeEscola(), aluno.getEnderecoPessoal(), aluno.getEnderecoEscolar());
	}

}
