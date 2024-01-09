package br.com.treinamento.dtos.request.cadastrar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarEnderecoEscolarDto(
		
		@NotBlank
		String logradouroEscola,
		
		@NotNull
		Long numeroEscola,
		
		@NotBlank
		String bairroEscola,
		
		@NotBlank
		String cidadeEscola,
		
		@NotBlank
		String ufEscola,
		
		@NotNull
		Long cepEscola,
		
		String complementoEscola
		
		) {

}
