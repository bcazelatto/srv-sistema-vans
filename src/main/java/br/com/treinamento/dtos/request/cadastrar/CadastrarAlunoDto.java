package br.com.treinamento.dtos.request.cadastrar;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarAlunoDto(
		
		@NotBlank
		String nome,
		
		@NotBlank
		@Email
		String email,
		
		@NotNull
		Integer idade,
		
		@NotNull
		Long cpf,
		
		@NotBlank
		String nomeEscola,
		
		@NotNull @Valid
		CadastrarEnderecoPessoalDto enderecoPessoal,
				
		@NotNull @Valid
		CadastrarEnderecoEscolarDto enderecoEscolar
		
		) {
	
}
