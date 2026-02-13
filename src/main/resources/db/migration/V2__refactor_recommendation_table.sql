-- Remove a tabela antiga, que era a tabela de junção do ManyToMany
DROP TABLE IF EXISTS categoria_clima;

-- Recria a tabela com o novo nome e a estrutura correta desde o início
CREATE TABLE recomendacao (
    id BIGSERIAL PRIMARY KEY,
    categoria_id INT NOT NULL,
    clima_id INT NOT NULL,
    periodo_dia VARCHAR(255) NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    FOREIGN KEY (clima_id) REFERENCES clima(id)
);
