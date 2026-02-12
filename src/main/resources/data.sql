-- Inserir Climas
INSERT INTO clima (id, condicao, descricao_amigavel) VALUES (1, 'Rain', 'Chuvoso');
INSERT INTO clima (id, condicao, descricao_amigavel) VALUES (2, 'Clear', 'Céu Limpo');
INSERT INTO clima (id, condicao, descricao_amigavel) VALUES (3, 'Clouds', 'Nublado');
INSERT INTO clima (id, condicao, descricao_amigavel) VALUES (5, 'Drizzle', 'Garoa');

-- Inserir Categorias
INSERT INTO categoria (id, nome, descricao, imagem_url) VALUES (1, 'Sopas e Caldos', 'Aqueça sua alma', 'url_sopa');
INSERT INTO categoria (id, nome, descricao, imagem_url) VALUES (2, 'Massas', 'Conforto em forma de comida', 'url_massa');
INSERT INTO categoria (id, nome, descricao, imagem_url) VALUES (3, 'Açaí', 'Refresque seu dia', 'url_acai');
INSERT INTO categoria (id, nome, descricao, imagem_url) VALUES (4, 'Saladas', 'Leve e saudável', 'url_salada');
INSERT INTO categoria (id, nome, descricao, imagem_url) VALUES (5, 'Hambúrguer', 'Clássico é clássico', 'url_burguer');
INSERT INTO categoria (id, nome, descricao, imagem_url) VALUES (6, 'Pizza', 'Para compartilhar (ou não)', 'url_pizza');

-- Associar Categorias com Climas (Tabela categoria_clima)
-- Clima Chuvoso (Rain, Drizzle) -> Sopas, Massas, Pizza
INSERT INTO categoria_clima (clima_id, categoria_id) VALUES (1, 1); -- Rain -> Sopas e Caldos
INSERT INTO categoria_clima (clima_id, categoria_id) VALUES (1, 2); -- Rain -> Massas
INSERT INTO categoria_clima (clima_id, categoria_id) VALUES (1, 6); -- Rain -> Pizza
INSERT INTO categoria_clima (clima_id, categoria_id) VALUES (5, 1); -- Drizzle -> Sopas e Caldos
INSERT INTO categoria_clima (clima_id, categoria_id) VALUES (5, 2); -- Drizzle -> Massas

-- Clima Céu Limpo (Clear) -> Açaí, Saladas, Hambúrguer
INSERT INTO categoria_clima (clima_id, categoria_id) VALUES (2, 3); -- Clear -> Açaí
INSERT INTO categoria_clima (clima_id, categoria_id) VALUES (2, 4); -- Clear -> Saladas
INSERT INTO categoria_clima (clima_id, categoria_id) VALUES (2, 5); -- Clear -> Hambúrguer

-- Clima Nublado (Clouds) -> Hambúrguer, Pizza
INSERT INTO categoria_clima (clima_id, categoria_id) VALUES (3, 5); -- Clouds -> Hambúrguer
INSERT INTO categoria_clima (clima_id, categoria_id) VALUES (3, 6); -- Clouds -> Pizza
