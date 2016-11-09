
INSERT INTO doisvalortype(id,tipo, descricao,  create_date, create_user)
    VALUES (10001,'ICMS - SITUAÇÃO TRIBUTARIA', 'ICMS - SITUAÇÃO TRIBUTARIA', 1477075035443, 'System'),
	(10002,'ICMS - ORIGEM', 'ICMS - ORIGEM', 1477075035443, 'System'),
    (10003,'ICMS - ORIGEM', 'ICMS - ORIGEM', 1477075035443, 'System');
	
INSERT INTO DoisValor( id,doisvalortype, nome, descricao, parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1001,10001, 'nome_1', 'descricao_1', 10000,1,1,1,'system',1469734163819,'rod',1469734163819);

INSERT INTO DoisValor( id,doisvalortype, nome, descricao, parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1002,10002, 'nome_2', 'descricao_2', 10000,1,1,1,'system',1469734163819,'rod',1469734163819);

INSERT INTO DoisValor( id,doisvalortype, nome, descricao,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1003,10003, 'nome_3', 'descricao_3', 10000,1,1,1,'system',1469734163819,'rod',1469734163819);
