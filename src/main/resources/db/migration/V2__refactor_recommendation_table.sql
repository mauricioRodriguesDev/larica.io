-- 1. Adicionar a nova coluna de período do dia
ALTER TABLE categoria_clima ADD COLUMN periodo_dia VARCHAR(255);

-- 2. Preencher a nova coluna com um valor padrão (ex: NOITE) para os dados existentes
UPDATE categoria_clima SET periodo_dia = 'NOITE';

-- 3. Tornar a nova coluna não nula
ALTER TABLE categoria_clima ALTER COLUMN periodo_dia SET NOT NULL;

-- 4. Renomear a tabela
ALTER TABLE categoria_clima RENAME TO recomendacao;

-- 5. Adicionar uma coluna de ID e torná-la a chave primária
ALTER TABLE recomendacao DROP CONSTRAINT categoria_clima_pkey;
ALTER TABLE recomendacao ADD COLUMN id BIGSERIAL PRIMARY KEY;
