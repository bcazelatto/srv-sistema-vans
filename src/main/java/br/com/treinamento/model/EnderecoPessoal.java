package br.com.treinamento.model;

import br.com.treinamento.dtos.request.atualizar.AtualizarEnderecoPessoalDto;
import br.com.treinamento.dtos.request.cadastrar.CadastrarEnderecoPessoalDto;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoPessoal {

	private String logradouroPessoal;
	
	private Integer numeroCasa;
	
	private String bairro;
	
	private String cidade;
	
	private String uf;
	
	private Long cep;
	
	private String complemento;
	
	public EnderecoPessoal(CadastrarEnderecoPessoalDto endereco) {
		this.logradouroPessoal = endereco.logradouroPessoal();
		this.numeroCasa = endereco.numeroCasa();
		this.bairro = endereco.bairro();
		this.cidade = endereco.cidade();
		this.uf = endereco.uf();
		this.cep = endereco.cep();
		this.complemento = endereco.complemento();
	}

	public void atualizarInformacoes(AtualizarEnderecoPessoalDto dados) {
	    this.logradouroPessoal = (dados.logradouroPessoal() != null) ? dados.logradouroPessoal() : this.logradouroPessoal;
	    this.numeroCasa = (dados.numeroCasa() != null) ? dados.numeroCasa() : this.numeroCasa;
	    this.bairro = (dados.bairro() != null) ? dados.bairro() : this.bairro;
	    this.cidade = (dados.cidade() != null) ? dados.cidade() : this.cidade;
	    this.uf = (dados.uf() != null) ? dados.uf() : this.uf;
	    this.cep = (dados.cep() != null) ? dados.cep() : this.cep;
	    this.complemento = (dados.complemento() != null) ? dados.complemento() : this.complemento;
	}
	
}
