-- Inserir Climas
INSERT INTO clima (id, condicao, descricao_amigavel) VALUES (1, 'Rain', 'Chuvoso'), (2, 'Clear', 'Céu Limpo'), (3, 'Clouds', 'Nublado'), (5, 'Drizzle', 'Garoa');

-- Inserir Categorias
INSERT INTO categoria (id, nome, descricao, imagem_url) VALUES
(1, 'Sopas e Caldos', 'Aqueça sua alma', 'url_sopa'),
(2, 'Massas', 'Conforto em forma de comida', 'url_massa'),
(3, 'Açaí', 'Refresque seu dia', 'url_acai'),
(4, 'Saladas', 'Leve e saudável', 'url_salada'),
(5, 'Hambúrguer', 'Clássico é clássico', 'url_burguer'),
(6, 'Pizza', 'Para compartilhar (ou não)', 'url_pizza');

-- Associar Categorias com Climas
INSERT INTO categoria_clima (clima_id, categoria_id) VALUES
(1, 1), (1, 2), (1, 6), -- Chuva -> Sopa, Massa, Pizza
(5, 1), (5, 2), -- Garoa -> Sopa, Massa
(2, 3), (2, 4), (2, 5), -- Céu Limpo -> Açaí, Salada, Hambúrguer
(3, 5), (3, 6); -- Nublado -> Hambúrguer, Pizza

-- Inserir Restaurantes (Zona Sul - RJ)
-- Hambúrguer (Categoria 5)
INSERT INTO restaurante (id, nome, categoria_id, endereco, rating, ativo) VALUES
(1, 'B de Burger', 5, 'R. Teixeira de Melo, 21 - Ipanema', 4.7, true),
(2, 'T.T. Burger', 5, 'R. Dias Ferreira, 135 - Leblon', 4.6, true);

-- Açaí (Categoria 3)
INSERT INTO restaurante (id, nome, categoria_id, endereco, rating, ativo) VALUES
(3, 'Polis Sucos', 3, 'R. Maria Quitéria, 70 - Ipanema', 4.8, true);

-- Massas (Categoria 2)
INSERT INTO restaurante (id, nome, categoria_id, endereco, rating, ativo) VALUES
(4, 'La Boticella', 2, 'R. Jangadeiros, 44 - Ipanema', 4.5, true);

-- Sopas e Caldos (Categoria 1)
INSERT INTO restaurante (id, nome, categoria_id, endereco, rating, ativo) VALUES
(5, 'Sopão 95', 1, 'R. Farme de Amoedo, 95 - Ipanema', 4.4, true);

-- Saladas (Categoria 4)
INSERT INTO restaurante (id, nome, categoria_id, endereco, rating, ativo) VALUES
(6, 'Delírio Tropical', 4, 'R. Garcia d''Avila, 48 - Ipanema', 4.6, true);


-- Inserir Links de Delivery
-- B de Burger
INSERT INTO link_delivery (restaurante_id, plataforma, url_destino) VALUES
(1, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/b-de-burger-ipanema/'),
(1, 'Rappi', 'https://www.rappi.com.br/restaurantes/b-de-burger-ipanema');

-- T.T. Burger
INSERT INTO link_delivery (restaurante_id, plataforma, url_destino) VALUES
(2, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/tt-burger-leblon/');

-- Polis Sucos
INSERT INTO link_delivery (restaurante_id, plataforma, url_destino) VALUES
(3, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/polis-sucos-ipanema/');

-- Sopão 95
INSERT INTO link_delivery (restaurante_id, plataforma, url_destino) VALUES
(5, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/sopao-95-ipanema/');

-- Delírio Tropical
INSERT INTO link_delivery (restaurante_id, plataforma, url_destino) VALUES
(6, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/delirio-tropical-ipanema/');
