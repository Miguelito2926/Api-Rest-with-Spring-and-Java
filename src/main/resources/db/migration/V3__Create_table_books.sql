CREATE TABLE IF NOT EXISTS `livro` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `autor` VARCHAR(255) NOT NULL,
  `data_lancamento` datetime,
  `preco` DECIMAL(10,2) NOT NULL,
  `titulo` VARCHAR(255) NOT NULL,
  `data_criacao` timestamp DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);