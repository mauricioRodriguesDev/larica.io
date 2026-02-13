-- Limpar dados antigos para evitar conflitos com as novas migrações
TRUNCATE TABLE link_delivery, restaurante, recomendacao, categoria, clima RESTART IDENTITY CASCADE;

-- Inserir Climas
INSERT INTO clima (id, condicao, descricao_amigavel) VALUES
(1, 'Rain', 'Chuvoso'),
(2, 'Clear', 'Céu Limpo'),
(3, 'Clouds', 'Nublado'),
(4, 'Drizzle', 'Garoa');

-- Inserir Categorias
INSERT INTO categoria (id, nome, descricao, imagem_url) VALUES
(1, 'Sopas e Caldos', 'Aqueça sua alma', 'url_sopa'),
(2, 'Massas', 'Conforto em forma de comida', 'url_massa'),
(3, 'Açaí', 'Refresque seu dia', 'url_acai'),
(4, 'Saladas', 'Leve e saudável', 'url_salada'),
(5, 'Hambúrguer', 'Clássico é clássico', 'url_burguer'),
(6, 'Pizza', 'Para compartilhar (ou não)', 'url_pizza');

-- Inserir Recomendações (Clima + Categoria + Período)
-- Chuva (Rain/Drizzle) -> Pede algo quente
INSERT INTO recomendacao (clima_id, categoria_id, periodo_dia) VALUES
(1, 1, 'NOITE'), (1, 2, 'NOITE'), (1, 6, 'NOITE'), -- Chuva à noite: Sopa, Massa, Pizza
(4, 1, 'NOITE'), (4, 2, 'NOITE'), -- Garoa à noite: Sopa, Massa
(1, 2, 'ALMOCO'), (4, 2, 'ALMOCO'); -- Chuva no almoço: Massa

-- Céu Limpo (Clear) -> Pede algo leve ou rápido
INSERT INTO recomendacao (clima_id, categoria_id, periodo_dia) VALUES
(2, 3, 'TARDE'), (2, 4, 'ALMOCO'), (2, 5, 'NOITE'); -- Sol: Açaí de tarde, Salada no almoço, Hambúrguer à noite

-- Nublado (Clouds) -> Pede um clássico
INSERT INTO recomendacao (clima_id, categoria_id, periodo_dia) VALUES
(3, 5, 'ALMOCO'), (3, 5, 'NOITE'), -- Nublado: Hambúrguer no almoço ou noite
(3, 6, 'NOITE'); -- Nublado à noite: Pizza

-- Inserir Restaurantes
INSERT INTO restaurante (id, nome, categoria_id, endereco, rating, ativo) VALUES
(1, 'B de Burger', 5, 'R. Teixeira de Melo, 21 - Ipanema', 4.7, true),
(2, 'T.T. Burger', 5, 'R. Dias Ferreira, 135 - Leblon', 4.6, true),
(3, 'Polis Sucos', 3, 'R. Maria Quitéria, 70 - Ipanema', 4.8, true),
(4, 'La Boticella', 2, 'R. Jangadeiros, 44 - Ipanema', 4.5, true),
(5, 'Sopão 95', 1, 'R. Farme de Amoedo, 95 - Ipanema', 4.4, true),
(6, 'Delírio Tropical', 4, 'R. Garcia d''Avila, 48 - Ipanema', 4.6, true);

-- Inserir Links de Delivery
INSERT INTO link_delivery (restaurante_id, plataforma, url_destino) VALUES
(1, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/b-de-burger-ipanema/'),
(2, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/tt-burger-leblon/'),
(3, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/polis-sucos-ipanema/'),
(4, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/la-boticella-ipanema/'),
(5, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/sopao-95-ipanema/'),
(6, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/delirio-tropical-ipanema/');
