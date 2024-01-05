package br.com.treinamento.dtos.request.atualizar;

public record AtualizarEnderecoEscolarDto(
		
		String logradouroEscola,
		
		Long numeroEscola,
		
		String bairroEscola,
		
		String cidadeEscola,
		
		String ufEscola,
		
		Long cepEscola,
		
		String complementoEscola
		
		) {

}
