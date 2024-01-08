package br.com.treinamento.model;

import br.com.treinamento.dtos.request.atualizar.AtualizarAlunoDto;
import br.com.treinamento.dtos.request.cadastrar.CadastrarAlunoDto;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "alunos")
@Entity(name = "aluno")
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Aluno {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String nome;
	
	private String email;
	
	private Integer idade;
	
	private Long cpf;
	
	private String nomeEscola;
	
	private Boolean ativo;
	
	@Embedded
	private EnderecoPessoal enderecoPessoal;
	
	@Embedded
	private EnderecoEscolar enderecoEscolar;
	
	public Aluno(CadastrarAlunoDto dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.idade = dados.idade();
		this.cpf = dados.cpf();
		this.nomeEscola = dados.nomeEscola();
		this.enderecoPessoal = new EnderecoPessoal(dados.enderecoPessoal());
		this.enderecoEscolar = new EnderecoEscolar(dados.enderecoEscolar());
		
	}
	
	public void atualizarInformacoes(AtualizarAlunoDto dados) {
	    this.nome = (dados.nome() != null) ? dados.nome() : this.nome;
	    this.email = (dados.email() != null) ? dados.email() : this.email;
	    this.idade = (dados.idade() != null) ? dados.idade() : this.idade;
	    this.cpf = (dados.cpf() != null) ? dados.cpf() : this.cpf;
	    this.nomeEscola = (dados.nomeEscola() != null) ? dados.nomeEscola() : this.nomeEscola;
	    
	    if (dados.enderecoPessoal() != null) {
	        this.enderecoPessoal.atualizarInformacoes(dados.enderecoPessoal());
	    }
	    
	    if (dados.enderecoEscolar() != null) {
	        this.enderecoEscolar.atualizarInformacoes(dados.enderecoEscolar());
	    }

	}

	public void excluir() {
		this.ativo = false;		
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
