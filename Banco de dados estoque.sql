CREATE DATABASE IF NOT EXISTS estoque;
SHOW DATABASES;
SHOW TABLES;
USE estoque;
SELECT * FROM entrada;
SELECT * FROM produto; 

SELECT * FROM funcionarios;

select * from entrada;

SELECT entrada.*, produto.nomeProdutos as nomeProdutos FROM entrada
LEFT JOIN produto on entrada.id_Produtos = produto.idProduto

SELECT  saida.*, produto.nomeProdutos as nomeProdutos FROM saida
LEFT JOIN produto on saida.id_Produtos = produto.idProduto;

SELECT saida.*, produto.nomeProdutos as nomeProdutos, concat(saida.id_Produtos, ' - ', produto.nomeProdutos) as idNomeProduto
FROM saida
LEFT JOIN produto on saida.id_Produtos = produto.idProduto
WHERE idSaida = 1;

select * from produto where idProduto = 4;

SELECT nomeFuncionario, cargo FROM funcionarios WHERE upper(nomeFuncionario) like upper('%tes%');

SELECT idFuncionarios FROM funcionarios WHERE nomeFuncionario = '1' AND cargo = '2' LIMIT 1;

ALTER TABLE funcionarios DROP INDEX idFuncionarios;

CREATE TABLE IF NOT EXISTS produto (
    idProduto INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    d VARCHAR(200) UNIQUE,
    categoria VARCHAR(200),
    preco DOUBLE NOT NULL,
    quantidade INT NOT NULL,
);

CREATE TABLE IF NOT EXISTS funcionarios
    idFuncionarios INT PRIMARY KEY AUTO_INCREMENT,
    nomeFuncionario VARCHAR(200),
    cargo VARCHAR(200) NOT NULL,
    login VARCHAR(200),
    senha VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS entrada (
    idEntrada INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_Produtos INT,
    quantidade INT NOT NULL,
    dataEntrada DATE NOT NULL,
    fornecedor VARCHAR(200) NOT NULL,
    FOREIGN KEY (id_Produtos) REFERENCES produto (idProduto)
);

CREATE TABLE IF NOT EXISTS saida (
    idSaida INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_Produtos INT,
    quantidade INT NOT NULL,
    dataSaida DATE NOT NULL, 
    destinatario VARCHAR(200) NOT NULL,
    FOREIGN KEY (id_Produtos) REFERENCES produto (idProduto)
);

CREATE TABLE IF NOT EXISTS relatorio (
    idRelatorio INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    tipo VARCHAR(200) NOT NULL,
    dataRelatorio DATE NOT NULL,
    detalhes VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS consultas (
	nomeProdutos VARCHAR (200) unique,
	categoria VARCHAR (200),
    estoque INT NOT NULL,
    fornecedor VARCHAR (200) NOT NULL,
    disponivel INT NOT NULL
    );
    
    
ALTER TABLE produto ADD COLUMN fornecedor VARCHAR(255);