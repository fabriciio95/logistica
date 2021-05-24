ALTER TABLE entrega ADD COLUMN motorista_id BIGINT;

ALTER TABLE entrega
ADD CONSTRAINT fk_motorista
FOREIGN KEY(id) REFERENCES motorista(id);