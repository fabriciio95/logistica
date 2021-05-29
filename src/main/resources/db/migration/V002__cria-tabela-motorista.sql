CREATE TABLE motorista
(
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(60) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	telefone VARCHAR(20) NOT NULL,
	cpf VARCHAR(15) NOT NULL UNIQUE,
	PRIMARY KEY(id)
);