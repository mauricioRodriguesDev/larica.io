-- Inserir Climas
INSERT INTO clima (condicao, descricao_amigavel) VALUES
('Rain', 'Chuvoso'),
('Clear', 'Céu Limpo'),
('Clouds', 'Nublado'),
('Drizzle', 'Garoa');

-- Inserir Categorias
INSERT INTO categoria (nome, descricao, imagem_url) VALUES
('Sopas e Caldos', 'Aqueça sua alma', 'url_sopa'),
('Massas', 'Conforto em forma de comida', 'url_massa'),
('Açaí', 'Refresque seu dia', 'url_acai'),
('Saladas', 'Leve e saudável', 'url_salada'),
('Hambúrguer', 'Clássico é clássico', 'url_burguer'),
('Pizza', 'Para compartilhar (ou não)', 'url_pizza');

-- Inserir Recomendações (Clima + Categoria + Período)
INSERT INTO recomendacao (clima_id, categoria_id, periodo_dia) VALUES
(1, 1, 'NOITE'), (1, 2, 'NOITE'), (1, 6, 'NOITE'),
(4, 1, 'NOITE'), (4, 2, 'NOITE'),
(1, 2, 'ALMOCO'), (4, 2, 'ALMOCO'),
(2, 3, 'TARDE'), (2, 4, 'ALMOCO'), (2, 5, 'NOITE'),
(3, 5, 'ALMOCO'), (3, 5, 'NOITE'),
(3, 6, 'NOITE');

-- Inserir Restaurantes
INSERT INTO restaurante (nome, categoria_id, endereco, rating, ativo) VALUES
('B de Burger', 5, 'R. Teixeira de Melo, 21 - Ipanema', 4.7, true),
('T.T. Burger', 5, 'R. Dias Ferreira, 135 - Leblon', 4.6, true),
('Polis Sucos', 3, 'R. Maria Quitéria, 70 - Ipanema', 4.8, true),
('La Boticella', 2, 'R. Jangadeiros, 44 - Ipanema', 4.5, true),
('Sopão 95', 1, 'R. Farme de Amoedo, 95 - Ipanema', 4.4, true),
('Delírio Tropical', 4, 'R. Garcia d''Avila, 48 - Ipanema', 4.6, true);

-- Inserir Links de Delivery
INSERT INTO link_delivery (restaurante_id, plataforma, url_destino) VALUES
(1, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/b-de-burger-ipanema/'),
(2, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/tt-burger-leblon/'),
(3, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/polis-sucos-ipanema/'),
(4, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/la-boticella-ipanema/'),
(5, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/sopao-95-ipanema/'),
(6, 'iFood', 'https://www.ifood.com.br/delivery/rio-de-janeiro-rj/delirio-tropical-ipanema/');
