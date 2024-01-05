CREATE TABLE alunos(

    id bigint not null auto_increment,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    idade INT NOT NULL,
    cpf BIGINT NOT NULL,
    nome_escola VARCHAR(255) NOT NULL,
	ativo tinyint,
    logradouro_pessoal VARCHAR(255) NOT NULL,
    numero_casa INT,
    bairro VARCHAR(255),
    cidade VARCHAR(255) NOT NULL,
    uf CHAR(2) NOT NULL,
    cep INT NOT NULL,
    complemento VARCHAR(255),
    logradouro_escola VARCHAR(255) NOT NULL,
    numero_escola INT,
    bairro_escola VARCHAR(255) NOT NULL,
    cidade_escola VARCHAR(255) NOT NULL,
    uf_escola CHAR(2) NOT NULL,
    cep_escola INT NOT NULL,
    complemento_escola VARCHAR(255),
	
	primary key(id)
    
);