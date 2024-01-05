package br.com.treinamento.dtos.request.atualizar;

public record AtualizarEnderecoPessoalDto(
		
		String logradouroPessoal,
		
		Integer numeroCasa,
		
		String bairro,
		
		String cidade,

		String uf,
		
		Long cep,
		
		String complemento
		
		) {

}
