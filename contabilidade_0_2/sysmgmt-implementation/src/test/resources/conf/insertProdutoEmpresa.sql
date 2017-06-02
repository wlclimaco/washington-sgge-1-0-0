
INSERT INTO ProdutoEmpresa( id, prodId, informAdicionaisParaNFe, anotainternas, dataCadastro, tributacao, codigo, uniMed, categoria, marca, pesoBruto, pesoLiquido, modoUso,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1000, 1000, 'informAdicionaisParaNFe_0', 1000, 1473121358152, 1000, 'codigo_0', 1000, 1000, 1000, 10.00, 10.00, 'modoUso_0',10000,1,1,1,'system',1473121351031,'rod',1473121351031);

INSERT INTO ProdutoEmpresa( id, prodId, informAdicionaisParaNFe, anotainternas, dataCadastro, tributacao, codigo, uniMed, categoria, marca, pesoBruto, pesoLiquido, modoUso,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1001, 1001, 'informAdicionaisParaNFe_1', 1001, 1473121358152, 1001, 'codigo_1', 1001, 1001, 1001, 10.00, 10.00, 'modoUso_1',10000,1,1,1,'system',1473121353798,'rod',1473121353798);

INSERT INTO ProdutoEmpresa( id, prodId, informAdicionaisParaNFe, anotainternas, dataCadastro, tributacao, codigo, uniMed, categoria, marca, pesoBruto, pesoLiquido, modoUso,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1002, 1002,'informAdicionaisParaNFe_2', 1002, 1473121358152, 1002, 'codigo_2', 1002, 1002, 1002, 10.00, 10.00, 'modoUso_2',10000,1,1,1,'system',1473121355477,'rod',1473121355477);

INSERT INTO Estoque(estoqueTypeEnum, ultimoMov, quant,create_user,create_date,modify_user,modify_date)values
( 1000, 1473121358152, 10.00,'system',1473121358151,'rod',1473121358151);

INSERT INTO Estoque(estoqueTypeEnum, ultimoMov, quant,create_user,create_date,modify_user,modify_date)values
( 1001, 1473121358152, 10.00,'system',1473121358151,'rod',1473121358151);

INSERT INTO Estoque(estoqueTypeEnum, ultimoMov, quant,create_user,create_date,modify_user,modify_date)values
( 1002, 1473121358152, 10.00,'system',1473121358151,'rod',1473121358151);

INSERT INTO Preco(dataMarcacao, precoTypeEnum, valor, dataProInicial, dataProFinal,create_user,create_date,modify_user,modify_date)values
( 1473121358152, 1000, 10.00, 1473121358152, 1473121358152,'system',1473121358151,'rod',1473121358151);

INSERT INTO Preco(dataMarcacao, precoTypeEnum, valor, dataProInicial, dataProFinal,create_user,create_date,modify_user,modify_date)values
( 1473121358152, 1001, 10.00, 1473121358152, 1473121358152,'system',1473121358151,'rod',1473121358151);

INSERT INTO Preco(dataMarcacao, precoTypeEnum, valor, dataProInicial, dataProFinal,create_user,create_date,modify_user,modify_date)values
( 1473121358152, 1002, 10.00, 1473121358152, 1473121358152,'system',1473121358151,'rod',1473121358151);

INSERT INTO Custo(valor,create_user,create_date,modify_user,modify_date)values
( 10.00,'system',1473121358151,'rod',1473121358151);

INSERT INTO Custo(valor,create_user,create_date,modify_user,modify_date)values
( 10.00,'system',1473121358151,'rod',1473121358151);

INSERT INTO Custo(valor,create_user,create_date,modify_user,modify_date)values
( 10.00,'system',1473121358151,'rod',1473121358151);

INSERT INTO Porcao(valor,create_user,create_date,modify_user,modify_date)values
( 10.00,'system',1473121358151,'rod',1473121358151);

INSERT INTO Porcao(valor,create_user,create_date,modify_user,modify_date)values
( 10.00,'system',1473121358151,'rod',1473121358151);

INSERT INTO Porcao(valor,create_user,create_date,modify_user,modify_date)values
( 10.00,'system',1473121358151,'rod',1473121358151);


INSERT INTO Produto(cdBarras, nCM, cEST, excTabIPI, produto, dataCreate,create_user,create_date,modify_user,modify_date)values
( 'cdBarras_0', 'nCM_0', 'cEST_0', 0, 'produto_0', 1473121358152,'system',1473121358151,'rod',1473121358151);

INSERT INTO Produto(cdBarras, nCM, cEST, excTabIPI, produto, dataCreate,create_user,create_date,modify_user,modify_date)values
( 'cdBarras_1', 'nCM_1', 'cEST_1',1, 'produto_1', 1473121358152,'system',1473121358152,'rod',1473121358152);

INSERT INTO Produto(cdBarras, nCM, cEST, excTabIPI, produto, dataCreate,create_user,create_date,modify_user,modify_date)values
( 'cdBarras_2', 'nCM_2', 'cEST_2', 2, 'produto_2', 1473121358152,'system',1473121358152,'rod',1473121358152);
