CREATE TABLE ocorrencia 
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	entrega_id BIGINT NOT NULL,
	descricao TEXT NOT NULL,
	data_registro DATETIME NOT NULL,
	FOREIGN KEY(entrega_id) REFERENCES entrega(id)
);