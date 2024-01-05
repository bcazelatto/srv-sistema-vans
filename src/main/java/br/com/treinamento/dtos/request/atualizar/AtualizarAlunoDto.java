package br.com.treinamento.dtos.request.atualizar;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AtualizarAlunoDto(
		
		@NotNull
		Long id,
		
		String nome,
		
		@Email
		String email,
		
		Integer idade,
		
		Long cpf,
		
		String nomeEscola,
		
		AtualizarEnderecoPessoalDto enderecoPessoal,
				
		AtualizarEnderecoEscolarDto enderecoEscolar
		
		) {

}
