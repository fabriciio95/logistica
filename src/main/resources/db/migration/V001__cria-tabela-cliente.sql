CREATE TABLE cliente
(
	id bigint not null auto_increment,
	nome varchar(60) not null,
	email VARCHAR(255) NOT NULL UNIQUE,
	telefone varchar(20) not null,
	cpf VARCHAR(15) NOT NULL UNIQUE,
	
	primary key(id)
);