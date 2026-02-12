CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE,
    descricao VARCHAR(255),
    imagem_url VARCHAR(255)
);

CREATE TABLE clima (
    id SERIAL PRIMARY KEY,
    condicao VARCHAR(50) NOT NULL UNIQUE,
    descricao_amigavel VARCHAR(100)
);

CREATE TABLE categoria_clima (
    categoria_id INT NOT NULL,
    clima_id INT NOT NULL,
    PRIMARY KEY (categoria_id, clima_id),
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    FOREIGN KEY (clima_id) REFERENCES clima(id)
);

CREATE TABLE restaurante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria_id INT NOT NULL,
    endereco VARCHAR(255),
    rating DECIMAL(2, 1),
    ativo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE link_delivery (
    id SERIAL PRIMARY KEY,
    restaurante_id INT NOT NULL,
    plataforma VARCHAR(50) NOT NULL,
    url_destino TEXT NOT NULL,
    FOREIGN KEY (restaurante_id) REFERENCES restaurante(id)
);
