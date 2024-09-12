CREATE TABLE IF NOT EXISTS `pessoa` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `sobrenome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) UNIQUE,
  `telefone` VARCHAR(15),
  `endereco` VARCHAR(150),
  `sexo` VARCHAR(1) NOT NULL,  -- Mantido como VARCHAR(1) para valores 'M' e 'F'
  `ativo` TINYINT(1) DEFAULT 1,    -- Mantido como TINYINT(1) para valores booleanos
  `data_nascimento` DATE,          -- Mantido como DATE para compatibilidade com datas
  `data_criacao` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);
