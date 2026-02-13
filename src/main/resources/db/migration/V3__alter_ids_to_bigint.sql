-- Alterar chaves estrangeiras primeiro para evitar conflitos de constraint

-- Tabela: recomendacao
ALTER TABLE recomendacao ALTER COLUMN categoria_id TYPE BIGINT;
ALTER TABLE recomendacao ALTER COLUMN clima_id TYPE BIGINT;

-- Tabela: restaurante
ALTER TABLE restaurante ALTER COLUMN categoria_id TYPE BIGINT;

-- Tabela: link_delivery
ALTER TABLE link_delivery ALTER COLUMN restaurante_id TYPE BIGINT;

-- Agora, alterar as chaves primárias
ALTER TABLE categoria ALTER COLUMN id TYPE BIGINT;
ALTER TABLE clima ALTER COLUMN id TYPE BIGINT;
ALTER TABLE restaurante ALTER COLUMN id TYPE BIGINT;
ALTER TABLE link_delivery ALTER COLUMN id TYPE BIGINT;
ALTER TABLE recomendacao ALTER COLUMN id TYPE BIGINT;
