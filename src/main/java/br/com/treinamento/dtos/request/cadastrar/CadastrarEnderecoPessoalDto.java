package br.com.treinamento.dtos.request.cadastrar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

public record CadastrarEnderecoPessoalDto(
		
		@NotBlank
		String logradouroPessoal,
		
		@NotNull
		Integer numeroCasa,
		
		@NotBlank
		String bairro,
		
		@NotBlank
		String cidade,
		
		@NotBlank
		String uf,
		
		@NotNull
		Long cep,
		
		String complemento
		
		) {

}
