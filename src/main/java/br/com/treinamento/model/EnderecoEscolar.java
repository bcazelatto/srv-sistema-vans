package br.com.treinamento.model;

import br.com.treinamento.dtos.request.atualizar.AtualizarEnderecoEscolarDto;
import br.com.treinamento.dtos.request.cadastrar.CadastrarEnderecoEscolarDto;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEscolar {

	private String logradouroEscola;
	
	private Long numeroEscola;
	
	private String bairroEscola;
	
	private String cidadeEscola;
	
	private String ufEscola;
	
	private Long cepEscola;
	
	private String complementoEscola;
	
	public EnderecoEscolar(CadastrarEnderecoEscolarDto endereco) {
		this.logradouroEscola = endereco.logradouroEscola();
		this.numeroEscola = endereco.numeroEscola();
		this.bairroEscola = endereco.bairroEscola();
		this.cidadeEscola = endereco.cidadeEscola();
		this.ufEscola = endereco.ufEscola();
		this.cepEscola = endereco.cepEscola();
		this.complementoEscola = endereco.complementoEscola();
	}

	public void atualizarInformacoes(AtualizarEnderecoEscolarDto dados) {
	    this.logradouroEscola = (dados.logradouroEscola() != null) ? dados.logradouroEscola() : this.logradouroEscola;
	    this.numeroEscola = (dados.numeroEscola() != null) ? dados.numeroEscola() : this.numeroEscola;
	    this.bairroEscola = (dados.bairroEscola() != null) ? dados.bairroEscola() : this.bairroEscola;
	    this.cidadeEscola = (dados.cidadeEscola() != null) ? dados.cidadeEscola() : this.cidadeEscola;
	    this.ufEscola = (dados.ufEscola() != null) ? dados.ufEscola() : this.ufEscola;
	    this.cepEscola = (dados.cepEscola() != null) ? dados.cepEscola() : this.cepEscola;
	    this.complementoEscola = (dados.complementoEscola() != null) ? dados.complementoEscola() : this.complementoEscola;
	}
	
}
