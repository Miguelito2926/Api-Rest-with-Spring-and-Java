
-- Copiando estrutura para tabela teste_spring.pessoa
CREATE TABLE IF NOT EXISTS `pessoa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `sobre_nome` varchar(30) NOT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

