
INSERT INTO ProdutoParent( id, parentId,  tributacao, dataValidade, localizacao, comissao,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1, 10001, 1,  1463354114930, 'localizacao_11', 'comissao_12',1,1,'system',1463354114930,'rod',1463354114930);

INSERT INTO ProdutoParent( id, parentId,  tributacao, dataValidade, localizacao, comissao,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 2, 10002, 2, 1463354114930, 'localizacao_11', 'comissao_12',1,1,'system',1463354114930,'rod',1463354114930);

INSERT INTO ProdutoParent( id, parentId, tributacao, dataValidade, localizacao, comissao,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 3, 10003,  3, 1463354114930, 'localizacao_11', 'comissao_12',1,1,'system',1463354114930,'rod',1463354114930);

INSERT INTO Estoque( id, parentId, estoqueTypeEnum, ultimoMov, quant,create_user,create_date,modify_user,modify_date)values
( 10001, 1, 1, 1, 1,'system',1463354114930,'rod',1463354114930);

INSERT INTO Estoque( id, parentId, estoqueTypeEnum, ultimoMov, quant,create_user,create_date,modify_user,modify_date)values
( 10002, 2, 2, 2, 2,'system',1463354114930,'rod',1463354114930);

INSERT INTO Estoque( id, parentId, estoqueTypeEnum, ultimoMov, quant,create_user,create_date,modify_user,modify_date)values
( 10003, 3, 3, 3, 3,'system',1463354114930,'rod',1463354114930);

INSERT INTO Preco( id, dataMarcacao, precoTypeEnum, tabelaEnumValue, valor, dataProInicial, dataProFinal, parentId, emprId,create_user,create_date,modify_user,modify_date)values
( 10001, 1463354114930, 1, 1, 1, 1463354114930, 1463354114930, 1, 1,'system',1463354114930,'rod',1463354114930);

INSERT INTO Preco( id, dataMarcacao, precoTypeEnum, tabelaEnumValue, valor, dataProInicial, dataProFinal, parentId, emprId,create_user,create_date,modify_user,modify_date)values
( 10002, 1463354114930, 2, 2, 2, 1463354114930, 1463354114930, 2, 2,'system',1463354114930,'rod',1463354114930);

INSERT INTO Preco( id, dataMarcacao, precoTypeEnum, tabelaEnumValue, valor, dataProInicial, dataProFinal, parentId, emprId,create_user,create_date,modify_user,modify_date)values
( 10003, 1463354114930, 3, 3, 3, 1463354114930, 1463354114930, 3, 3,'system',1463354114930,'rod',1463354114930);

INSERT INTO Custo( id, valor, parentId,create_user,create_date,modify_user,modify_date)values
( 10001, 1, 1,'system',1463354114930,'rod',1463354114930);

INSERT INTO Custo( id, valor, parentId,create_user,create_date,modify_user,modify_date)values
( 10002, 2, 2,'system',1463354114930,'rod',1463354114930);

INSERT INTO Custo( id, valor, parentId,create_user,create_date,modify_user,modify_date)values
( 10003, 3, 3,'system',1463354114930,'rod',1463354114930);

INSERT INTO Porcao( id, parentId, valor,create_user,create_date,modify_user,modify_date)values
( 10001, 1, 1,'system',1463354114930,'rod',1463354114930);

INSERT INTO Porcao( id, parentId, valor,create_user,create_date,modify_user,modify_date)values
( 10002, 2, 2,'system',1463354114930,'rod',1463354114930);

INSERT INTO Porcao( id, parentId, valor,create_user,create_date,modify_user,modify_date)values
( 10003, 3, 3,'system',1463354114930,'rod',1463354114930);

INSERT INTO Rentabilidade( id, parentId,create_user,create_date,modify_user,modify_date)values
( 10001, 1,'system',1463354114930,'rod',1463354114930);

INSERT INTO Rentabilidade( id, parentId,create_user,create_date,modify_user,modify_date)values
( 10002, 2,'system',1463354114930,'rod',1463354114930);

INSERT INTO Rentabilidade( id, parentId,create_user,create_date,modify_user,modify_date)values
( 10003, 3,'system',1463354114930,'rod',1463354114930);

INSERT INTO Produto( id, codigo, cdBarras, produto, dataCreate, aplicacao, fracao, classificacao, uniMed, grupo, subGrupo, porcao, pesoBruto, pesoLiquido, modoUso,create_user,create_date,modify_user,modify_date)values
( 10001, 'codigo_1', 'cdBarras_2', 'produto_3', 1463354114931, 'aplicacao_5', 'fracao_6', 1, 1, 1, 1, 1, 1, 1, 'modoUso_15','system',1463354114931,'rod',1463354114931);

INSERT INTO Produto( id, codigo, cdBarras, produto, dataCreate, aplicacao, fracao, classificacao, uniMed, grupo, subGrupo, porcao, pesoBruto, pesoLiquido, modoUso,create_user,create_date,modify_user,modify_date)values
( 10002, 'codigo_1', 'cdBarras_2', 'produto_3', 1463354114931, 'aplicacao_5', 'fracao_6', 2, 2, 2, 2, 2, 2, 2, 'modoUso_15','system',1463354114931,'rod',1463354114931);

INSERT INTO Produto( id, codigo, cdBarras, produto, dataCreate, aplicacao, fracao, classificacao, uniMed, grupo, subGrupo, porcao, pesoBruto, pesoLiquido, modoUso,create_user,create_date,modify_user,modify_date)values
( 10003, 'codigo_1', 'cdBarras_2', 'produto_3', 1463354114931, 'aplicacao_5', 'fracao_6', 3, 3, 3, 3, 3, 3, 3, 'modoUso_15','system',1463354114931,'rod',1463354114931);
